package com.example.sdu.myflag.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sdu.myflag.R;
import com.example.sdu.myflag.adapter.SampleViewPagerAdapter;
import com.example.sdu.myflag.base.BaseActivity;
import com.example.sdu.myflag.fragment.MainFragment;
import com.example.sdu.myflag.widget.CustomViewPager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    CustomViewPager viewPager;
    List<Fragment> fragmentList;
    SampleViewPagerAdapter sampleViewPagerAdapter;
    ImageView main_img, community_img, myself_img;
    TextView main_tv, community_tv, myself_tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initView();
        init();
    }

    public void initView(){
        main_img = (ImageView) findViewById(R.id.tab_main_img);
        community_img = (ImageView) findViewById(R.id.tab_community_img);
        myself_img = (ImageView) findViewById(R.id.tab_myself_img);
        viewPager = (CustomViewPager) findViewById(R.id.main_view_pager);

        main_tv = (TextView) findViewById(R.id.tab_main_tv);
        community_tv = (TextView) findViewById(R.id.tab_community_tv);
        myself_tv = (TextView) findViewById(R.id.tab_myself_tv);
    }

    private void init(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new MainFragment());
        sampleViewPagerAdapter = new SampleViewPagerAdapter(this.getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(sampleViewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
        setMainTabSelected();
    }

    private void setMainTabSelected(){
        main_img.setImageDrawable(getResources().getDrawable(R.drawable.main_page_selected));
        main_tv.setTextColor(getResources().getColor(R.color.tab_text_color_blue));

        community_img.setImageDrawable(getResources().getDrawable(R.drawable.community_default));
        community_tv.setTextColor(getResources().getColor(R.color.tab_text_color_gray));

        myself_img.setImageDrawable(getResources().getDrawable(R.drawable.myself_default));
        myself_tv.setTextColor(getResources().getColor(R.color.tab_text_color_gray));
    }

    private void setCommunityTabSelected(){
        main_img.setImageDrawable(getResources().getDrawable(R.drawable.main_page_default));
        main_tv.setTextColor(getResources().getColor(R.color.tab_text_color_gray));

        community_img.setImageDrawable(getResources().getDrawable(R.drawable.community_selected));
        community_tv.setTextColor(getResources().getColor(R.color.tab_text_color_blue));

        myself_img.setImageDrawable(getResources().getDrawable(R.drawable.myself_default));
        myself_tv.setTextColor(getResources().getColor(R.color.tab_text_color_gray));
    }

    private void setMySelfTabSelected(){
        main_img.setImageDrawable(getResources().getDrawable(R.drawable.main_page_default));
        main_tv.setTextColor(getResources().getColor(R.color.tab_text_color_gray));

        community_img.setImageDrawable(getResources().getDrawable(R.drawable.community_default));
        community_tv.setTextColor(getResources().getColor(R.color.tab_text_color_gray));

        myself_img.setImageDrawable(getResources().getDrawable(R.drawable.myself_selected));
        myself_tv.setTextColor(getResources().getColor(R.color.tab_text_color_blue));
    }

    public void onMainTabClick(View view) {
        setMainTabSelected();
        viewPager.setCurrentItem(0);
    }

    public void onCommunityTabClick(View view) {
        setCommunityTabSelected();
        //viewPager.setCurrentItem(1);
    }

    public void onMySelfTabClick(View view) {
        setMySelfTabSelected();
        //viewPager.setCurrentItem(2);
    }
}
