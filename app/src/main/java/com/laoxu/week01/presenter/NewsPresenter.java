package com.laoxu.week01.presenter;

import com.laoxu.week01.contract.INewsContract;
import com.laoxu.week01.model.NewsModel;
import com.laoxu.week01.model.entity.ProductEntity;

public class NewsPresenter implements INewsContract.IPresenter {

    private NewsModel newsModel;
    private INewsContract.IView iView;

    /**
     * 构造方法初始化view对象
     * @param iView
     */
    public NewsPresenter(INewsContract.IView iView){
        this.iView = iView;
    }
    @Override
    public void getProductListData(String url) {
        newsModel = new NewsModel();
        newsModel.getProductListData(url, new INewsContract.IModel.ModelCallback() {
            /**
             * p层得到了model的数据
             * @param productEntity
             */
            @Override
            public void success(ProductEntity productEntity) {

                //把数据给view层用作展示
                iView.success(productEntity);

            }
        });

    }
}
