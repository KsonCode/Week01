package com.laoxu.week01.view.fragment;

import android.view.View;
import android.widget.Toast;

import com.laoxu.week01.R;
import com.laoxu.week01.base.BaseFragment;
import com.laoxu.week01.contract.INewsContract;
import com.laoxu.week01.model.entity.ProductEntity;
import com.laoxu.week01.presenter.NewsPresenter;

public class NewsFragment extends BaseFragment implements INewsContract.IView {


    private NewsPresenter newsPresenter;

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.fragment_news_layout;
    }

    @Override
    protected void initData() {

        newsPresenter = new NewsPresenter(this);


        if (isNet()){
            newsPresenter.getProductListData("http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword?keyword=1&count=5&page=1");

        }else{
            //显示展位图
        }



    }

    /**
     * 成功得到最终需要的数据，然后设置adapter
     * @param productEntity
     */
    @Override
    public void success(ProductEntity productEntity) {

        Toast.makeText(getActivity(),productEntity.message,Toast.LENGTH_SHORT).show();

    }

    /**
     *
     * @param throwable
     */
    @Override
    public void failure(Throwable throwable) {
        Toast.makeText(getActivity(),"网络异常",Toast.LENGTH_SHORT).show();
    }
}
