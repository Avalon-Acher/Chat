package chat.archer.chat.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.archer.chat.R;
import chat.archer.chat.adapter.AdapterChatMsg;
import chat.archer.chat.server.ServerManager;
import chat.archer.chat.util.model.ChatMsg;
import chat.archer.chat.view.TitleBar;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 连浩逵 on 2017/6/10.
 */

public class ChatRoomActivity extends AppCompatActivity{

    private TitleBar titleBar;
    private ListView listView;
    private EditText myMsg;
    private Button btnSend;
    public static List<ChatMsg> chatMsgList = new ArrayList<>();
    private AdapterChatMsg adapterChatMsgList;
    private String chatObj;
    private String group;

    private static final int SEND_TEXT_TRUE=1;
    private static final int SEND_TEXT_FALSE=0;

    private static Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SEND_TEXT_TRUE:
                    break;
                case SEND_TEXT_FALSE:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_chat_room);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViews();
    }

    private void initViews(){
        titleBar=(TitleBar) findViewById(R.id.tb_chat_room);
        listView=(ListView)findViewById(R.id.lv_chat_room);
        myMsg=(EditText)findViewById(R.id.myMsg);
        btnSend=(Button)findViewById(R.id.btnSend);
        chatMsgList.clear();

        chatObj=getIntent().getStringExtra("username");
        Log.d("ChatRoomActivity",chatObj);
        titleBar.setTitleText(chatObj);
        loadChatMsg();
        adapterChatMsgList=new AdapterChatMsg(ChatRoomActivity.this,R.layout.chat_other,chatMsgList);
        listView.setAdapter(adapterChatMsgList);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=myMsg.getText().toString();
                if(!content.isEmpty()){
                    ChatMsg msg=new ChatMsg();
                    msg.setContent(content);
                    msg.setUsername(ServerManager.getServerManager().getUsername());
                    msg.setIconID(ServerManager.getServerManager().getIconID());
                    msg.setMyInfo(true);
                    msg.setChatObj(chatObj);
                    msg.setGroup(group.equals("0")?chatObj:"");
                    if(sendToChatObj(msg.getContent())){
                        ChatMsg.chatMsgList.add(msg);
                        chatMsgList.add(msg);
                        myMsg.setText("");
                    }else{
                        Toast.makeText(ChatRoomActivity.this,"send failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        titleBar.setTitleBarClickListener(new TitleBar.titleBarClickListener() {
            @Override
            public void leftButtonClick() {
                finish();
            }
            @Override
            public void rightButtonClick() {}
        });
    }
    private boolean sendToChatObj(String content) {
        String msg = "[CHATMSG]:[" + chatObj + ", " + content + ", " + ServerManager.getServerManager().getIconID() +", Text]";
        ServerManager serverManager = ServerManager.getServerManager();
        serverManager.sendMessage(this, msg);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String ack = serverManager.getMessage();
        if (ack == null) {
            return false;
        }
        String p = "\\[ACKCHATMSG\\]:\\[(.*)\\]";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(ack);
        return matcher.find() && matcher.group(1).equals("1");
    }

    private void loadChatMsg() {
        if (group == "0") {
            for (ChatMsg chatMsg : ChatMsg.chatMsgList) {
                if (chatMsg.getGroup().equals(chatObj)) {
                    chatMsgList.add(chatMsg);
                }
            }
        } else {
            for (ChatMsg chatMsg : ChatMsg.chatMsgList) {
                if (chatMsg.getChatObj().equals(chatObj) && chatMsg.getGroup().equals(" ")) {
                    chatMsgList.add(chatMsg);
                }
            }
        }
    }

}
