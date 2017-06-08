package chat.archer.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import chat.archer.chat.R;
import chat.archer.chat.adapter.AdapterGuideViewPager;
import android.widget.Button;
/**
 * Created by 连浩逵 on 2017/6/8.
 */

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private AdapterGuideViewPager adapterGuideViewPager;
    private List<View> viewList;
    private ImageView imageViews[]=new ImageView[3];
    private int[] indicatorDotIds={R.id.iv_indicator_dot1,R.id.iv_indicator_dot2,R.id.iv_indicator_dot3};
    private Button btnToMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.actvity_guide);
        initViews();
    }

    private void initViews(){
        final LayoutInflater inflater= LayoutInflater.from(this);
        viewList=new ArrayList<>();
        viewList.add(inflater.inflate(R.layout.guide_page1,null));
        viewList.add(inflater.inflate(R.layout.guide_page2,null));
        viewList.add(inflater.inflate(R.layout.guide_page3,null));

        //绑定imageView
        for(int i=0;i<indicatorDotIds.length;i++){
            imageViews[i]=(ImageView)findViewById(indicatorDotIds[i]);
        }

        adapterGuideViewPager=new AdapterGuideViewPager(this,viewList);
        viewPager=(ViewPager)findViewById(R.id.vp_guide);
        viewPager.setAdapter(adapterGuideViewPager);
        viewPager.addOnPageChangeListener(this);

        btnToMain=(Button)(viewList.get(2).findViewById(R.id.btn_to_main));
        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,LoginOrRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<indicatorDotIds.length;i++){
            if(i!=position){
                imageViews[i].setImageResource(R.drawable.unselected);
            }else{
                imageViews[i].setImageResource(R.drawable.selected);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
