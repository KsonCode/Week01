package com.laoxu.week01.contract;

import com.laoxu.week01.model.entity.ProductEntity;

/**
 * mvp统一接口管理类，契约类，一共包含三个接口，正好对应mvp
 */
public interface INewsContract {

    /**
     * 处理数据，网络请求，进行网络请求
     */
    interface IModel {

        //获取网络数据，或者其他数据
        void getProductListData(String url,ModelCallback modelCallback);

        //model层的数据回调到p层的接口类
        interface ModelCallback {
            void success(ProductEntity productEntity);
        }

    }

    interface IView {

        //得到成功或失败结果的
        void success(ProductEntity productEntity);//得到对象数据

        void failure(Throwable throwable);

    }

    interface IPresenter {

        //传递数据和逻辑处理
        void getProductListData(String url);
//        void getBanner(String url);

    }

}
