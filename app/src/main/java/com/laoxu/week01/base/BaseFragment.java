package com.laoxu.week01.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.NetworkUtils;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(bindLayoutId(),container,false);

        initView(view);
        return view;
    }


    protected abstract void initView(View view);


    protected abstract int bindLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();


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
