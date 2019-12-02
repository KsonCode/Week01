package com.laoxu.week01.view.fragment;

import android.view.View;
import android.widget.Button;

import com.laoxu.week01.R;
import com.laoxu.week01.base.BaseFragment;
import com.laoxu.week01.view.activity.MainActivity;

public class HomeFragment extends BaseFragment {
    private Button button;
    @Override
    protected void initView(View view) {

        button = view.findViewById(R.id.toNews);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotoNews();
            }
        });
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fragment_home_layout;
    }

    @Override
    protected void initData() {

    }
}
