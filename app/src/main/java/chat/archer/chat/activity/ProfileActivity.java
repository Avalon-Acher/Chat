package chat.archer.chat.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import chat.archer.chat.R;
import chat.archer.chat.view.TitleBar;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 连浩逵 on 2017/6/9.
 */

public class ProfileActivity extends AppCompatActivity {
    private TitleBar tbProfile;
    private EditText etUsername;
    private EditText etSign;
    private EditText etPassward;
    private EditText etInsurePassward;
    private Button btnSubmit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        initViews();
    }

    private void initViews(){
        tbProfile=(TitleBar)findViewById(R.id.tb_profile);
        etUsername=(EditText)findViewById(R.id.et_profile_username);
        etSign=(EditText)findViewById(R.id.et_profile_sign);
        etPassward=(EditText)findViewById(R.id.et_profile_password);
        etInsurePassward=(EditText)findViewById(R.id.et_profile_insure_password);
        btnSubmit=(Button)findViewById(R.id.btn_profile_submit);

        tbProfile.setTitleBarClickListener(new TitleBar.titleBarClickListener() {
            @Override
            public void leftButtonClick() {
                finish();
            }

            @Override
            public void rightButtonClick() {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this,"submit success",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
