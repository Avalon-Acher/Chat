package chat.archer.chat.view;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import chat.archer.chat.R;
import chat.archer.chat.activity.DressUpActivity;
import chat.archer.chat.activity.SettingActivity;

/**
 * Created by 连浩逵 on 2017/6/8.
 */

public class SlideLayout extends FrameLayout {
    private Context context;

    private PicAndTextBtn dressUp;
    private PicAndTextBtn prifile;
    private PicAndTextBtn setting;
    private PicAndTextBtn night;

    private boolean nightMode=false;


    public SlideLayout(Context context){
        super(context);
        this.context=context;
        initViews();
    }
    public SlideLayout(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        this.context=context;
        initViews();
    }
    public SlideLayout(Context context,AttributeSet attributeSet,int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
        this.context=context;
        initViews();
    }

    private void initViews(){
        this.addView(LayoutInflater.from(context).inflate(R.layout.layout_slide,null));

        dressUp=(PicAndTextBtn)findViewById(R.id.patb_dressup);
        prifile=(PicAndTextBtn)findViewById(R.id.patb_profile);
        setting=(PicAndTextBtn)findViewById(R.id.patb_setting);
        night=(PicAndTextBtn)findViewById(R.id.patb_night);

        dressUp.setOnClickListener(new PicAndTextBtn.picAndTextBtnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DressUpActivity.class);
                context.startActivity(intent);
            }
        });

        prifile.setOnClickListener(new PicAndTextBtn.picAndTextBtnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SettingActivity.class);
                context.startActivity(intent);
            }
        });

        setting.setOnClickListener(new PicAndTextBtn.picAndTextBtnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,SettingActivity.class);
                context.startActivity(intent);
            }
        });

        night.setOnClickListener(new PicAndTextBtn.picAndTextBtnClickListener() {
            @Override
            public void onClick(View view) {
                if(nightMode){
                    findViewById(R.id.layout_slide).setBackgroundColor(0xff878787);
                    nightMode=false;
                }else{
                    findViewById(R.id.layout_slide).setBackgroundColor(0xffe8e8e8);
                    nightMode=true;
                }
            }
        });

    }

}
