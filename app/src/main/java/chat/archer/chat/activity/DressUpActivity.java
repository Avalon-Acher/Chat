package chat.archer.chat.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import chat.archer.chat.R;
import chat.archer.chat.adapter.AdapterAvatar;
import chat.archer.chat.adapter.AdapterBackground;
import chat.archer.chat.util.ImageManager;
import chat.archer.chat.util.model.ImageMsg;
import chat.archer.chat.view.TitleBar;
import android.widget.Button;
/**
 * Created by 连浩逵 on 2017/6/9.
 */

public class DressUpActivity extends AppCompatActivity {
    private TitleBar titleBar;
    private RecyclerView rvAvatar;
    private RecyclerView rvBackground;
    private Button btnSave;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dress_up);

        initViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initViews() {
        titleBar = (TitleBar) findViewById(R.id.tb_dress_up);
        rvAvatar = (RecyclerView) findViewById(R.id.rv_avatar);
        rvBackground = (RecyclerView) findViewById(R.id.rv_background);
        btnSave = (Button) findViewById(R.id.btn_save);

        addAvatarView();

        addBackgroundView();

       titleBar.setTitleBarClickListener(new TitleBar.titleBarClickListener() {
           @Override
           public void leftButtonClick() {
               finish();
           }

           @Override
           public void rightButtonClick() {

           }
       });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DressUpActivity.this, "saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addAvatarView() {
        List<ImageMsg> imageMsgs = new ArrayList<>();
        for (int anImagesAvatar : ImageManager.imagesAvatar) {
            ImageMsg imagemsg = new ImageMsg();
            imagemsg.setImageID(anImagesAvatar);
            imageMsgs.add(imagemsg);
        }

        AdapterAvatar avatarAdapter = new AdapterAvatar(this, imageMsgs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvAvatar.setLayoutManager(layoutManager);
        rvAvatar.setAdapter(avatarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addBackgroundView() {
        List<ImageMsg> imageMsgs = new ArrayList<>();
        for (int anImagesBackground : ImageManager.imagesBackground) {
            ImageMsg imagemsg = new ImageMsg();
            imagemsg.setImageID(anImagesBackground);
            imageMsgs.add(imagemsg);
        }

        AdapterBackground adapterBackground = new AdapterBackground(this, imageMsgs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvBackground.setLayoutManager(layoutManager);
        rvBackground.setAdapter(adapterBackground);
    }
}
