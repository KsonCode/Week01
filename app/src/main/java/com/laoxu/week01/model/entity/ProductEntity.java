package com.laoxu.week01.model.entity;

import java.util.List;

public class ProductEntity {
    public String message;
    public String status;
    public List<Product> result;
    public static class Product{
        public String commodityId;
        public String commodityName;
        public String masterPic;
        public String price;
        public String saleNum;
    }
}
