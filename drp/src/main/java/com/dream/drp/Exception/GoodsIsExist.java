package com.dream.drp.Exception;

public class GoodsIsExist extends Exception {

    public GoodsIsExist(String msg) {
        super(msg);
    }
    public GoodsIsExist() {
        super("该仓库已存在该商品");
    }
}
