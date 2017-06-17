package chat.archer.chat.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import chat.archer.chat.R;

/**
 * Created by 连浩逵 on 2017/6/8.
 */

public class WelcomeActivity extends AppCompatActivity {

    private static final int DELAY=2000;//延迟转换页面时间
    private static final int GO_HOME=1;//前往Home界面的标志消息
    private static final int GO_GUIDE=0;//前往Guide的界面标志消息

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        initLoad();
    }

    //引导界面只在第一次打开时显示
    private void initLoad(){
        SharedPreferences sharedPreferences=getSharedPreferences("chat",MODE_PRIVATE);
        boolean welcome=sharedPreferences.getBoolean("welcome",true);
        if(!welcome){
            handler.sendEmptyMessageDelayed(GO_HOME,DELAY);
            //goHome();
        }else {
            handler.sendEmptyMessageDelayed(GO_GUIDE,DELAY);
            //编辑Sharepreference中welcome变量,表示第一次已经进入过了
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("welcome",false);
            editor.apply();
            //goGuide();
        }
    }
    private void goHome(){
        Intent intent=new Intent(this,LoginOrRegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void goGuide(){
        Intent intent=new Intent(this,GuideActivity.class);
        startActivity(intent);
        finish();
    }


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_HOME: goHome();
                    break;
                case GO_GUIDE: goGuide();
                    break;
            }
        }
    };



}
