package com.laoxu.week01.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.NetworkUtils;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        initView();
        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    /**
     * 绑定根布局
     * @return
     */
    protected abstract int bindLayoutId();


    /**
     * 是否有网
     * @return
     */
    public boolean isNet(){

        if (NetworkUtils.is4G()){
            return true;
        }else if (NetworkUtils.isWifiConnected()){
            return true;
        }
        return false;

    }

}
