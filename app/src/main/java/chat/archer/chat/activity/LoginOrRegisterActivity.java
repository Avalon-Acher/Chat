package chat.archer.chat.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Button;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chat.archer.chat.R;
import chat.archer.chat.server.ServerManager;

/**
 * Created by 连浩逵 on 2017/6/8.
 */

public class LoginOrRegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TabHost tabHost;
    private Button btnLogin;
    private EditText etLoginUsername;
    private EditText etLoginPassword;

    private Button btnRegister;
    private EditText etRegisterUsername;
    private EditText etRegisterPassword;
    private EditText etInsurePassword;

    private String username=null;
    private String password=null;

    private ServerManager serverManager = ServerManager.getServerManager();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_or_register);
        initViews();
    }

    private void initViews(){
        serverManager.start();
        tabHost=(TabHost)findViewById(R.id.tabHost);
        btnLogin=(Button)findViewById(R.id.btn_login);
        etLoginUsername=(EditText)findViewById(R.id.ed_login_username);
        etLoginPassword=(EditText)findViewById(R.id.et_login_password);

        btnRegister=(Button)findViewById(R.id.btn_register);
        etRegisterUsername=(EditText)findViewById(R.id.et_register_username);
        etRegisterPassword=(EditText)findViewById(R.id.et_register_password);
        etInsurePassword=(EditText)findViewById(R.id.et_insure_password);

        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("Login").setIndicator("Login").setContent(R.id.layout_login));
        tabHost.addTab(tabHost.newTabSpec("Register").setIndicator("Register").setContent(R.id.layout_register));

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private static final int LOGIN_TRUE=1;
    private static final int LOGIN_FALSE=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case LOGIN_TRUE:
                    serverManager.setUsername(username);
                    Intent intent=new Intent(LoginOrRegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case LOGIN_FALSE:
                    etLoginUsername.setText("");
                    etLoginPassword.setText("");
                    break;
            }

        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:{
                username=etLoginUsername.getText().toString();
                password=etLoginPassword.getText().toString();
                //第一种 尝试 Handler+Thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(login(username,password)){
                            handler.sendEmptyMessage(LOGIN_TRUE);
                        }else{
                            handler.sendEmptyMessage(LOGIN_FALSE);
                        }
                    }
                }).start();

                /*if(login(username,password)){
                    serverManager.setUsername(username);
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    etLoginUsername.setText("");
                    etLoginPassword.setText("");
                }*/

            }
                break;
            case R.id.btn_register:{
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
                break;
        }
    }

    private boolean login(String username,String password){
        if(username==null||username.length()>10||password.length()>20){
            return false;
        }
        String msg = "[LOGIN]:[" + username + ", " + password + "]";
        serverManager.sendMessage(this,msg);
        String ack=serverManager.getMessage();
        if(ack==null){
            return false;
        }
        serverManager.setMessage(null);
        String p = "\\[ACKLOGIN\\]:\\[(.*)\\]";
        Pattern pattern=Pattern.compile(p);
        Matcher matcher=pattern.matcher(ack);
        return matcher.find()&&matcher.group(1).equals("1");

    }

    //第二种尝试方式
    static class LoginTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }
    }

}
