package com.laoxu.week01.model;

import com.google.gson.Gson;
import com.laoxu.week01.contract.INewsContract;
import com.laoxu.week01.model.entity.ProductEntity;
import com.laoxu.week01.presenter.NewsPresenter;
import com.laoxu.week01.utils.NetUtils;

/**
 * model层，数据模型层
 */
public class NewsModel implements INewsContract.IModel {

    /**
     * 请求商品列表数据
     * @param url
     * @param modelCallback
     */
    @Override
    public void getProductListData(String url, final ModelCallback modelCallback) {
        NetUtils.getInstance().getJson(url, new NetUtils.MyCallBack() {
            @Override
            public void onGetJson(String json) {

                ProductEntity productEntity = new Gson().fromJson(json,ProductEntity.class);
                modelCallback.success(productEntity);

            }
        });
    }
}
