package com.laoxu.week01.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.laoxu.week01.R;
import com.laoxu.week01.base.BaseActivity;
import com.laoxu.week01.view.fragment.HomeFragment;
import com.laoxu.week01.view.fragment.MyFragment;
import com.laoxu.week01.view.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private List<Fragment> fragmentList ;
    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new MyFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });



    }

    @Override
    protected void initView() {

        viewPager = findViewById(R.id.viewpager);
        radioGroup = findViewById(R.id.radiogroup);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(position);
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                   case R.id.radio_home:
                    viewPager.setCurrentItem(0);
                    break;
                    case R.id.radio_news:
                        viewPager.setCurrentItem(1);
                    break;
                    case  R.id.radio_my:
                        viewPager.setCurrentItem(2);
                    break;
                }
            }
        });

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 跳转到新闻列表
     */
    public void gotoNews(){
        viewPager.setCurrentItem(1);
    }
}
