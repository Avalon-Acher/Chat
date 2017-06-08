package chat.archer.chat.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import chat.archer.chat.R;
import chat.archer.chat.adapter.AdapterMainViewPager;
import chat.archer.chat.view.LayoutChats;
import chat.archer.chat.view.LayoutContacts;
import chat.archer.chat.view.LayoutMoments;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<TabLayout.Tab> tabList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        drawerLayout=(DrawerLayout)findViewById(R.id.dl_main);
        viewPager=(ViewPager)findViewById(R.id.vp_main);
        tabLayout=(TabLayout)findViewById(R.id.tl_main);
        tabList=new ArrayList<>();

        AdapterMainViewPager adapterMainViewPager=new AdapterMainViewPager(getSupportFragmentManager());
        adapterMainViewPager.addFragment(new LayoutChats());
        adapterMainViewPager.addFragment(new LayoutContacts());
        adapterMainViewPager.addFragment(new LayoutMoments());
        viewPager.setAdapter(adapterMainViewPager);

        tabLayout.setupWithViewPager(viewPager);

        tabList.add(tabLayout.getTabAt(0));
        tabList.add(tabLayout.getTabAt(1));
        tabList.add(tabLayout.getTabAt(2));

        tabList.get(0).setIcon(R.drawable.icon).setText("Chats");
        tabList.get(1).setIcon(R.drawable.icon).setText("Contacts");
        tabList.get(2).setIcon(R.drawable.icon).setText("Moments");


    }
}
