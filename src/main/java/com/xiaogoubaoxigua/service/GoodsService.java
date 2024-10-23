package com.xiaogoubaoxigua.service;

import com.xiaogoubaoxigua.po.Goods;


public interface GoodsService {

    Goods selectByGid(int gid);

    Goods selectByClass(String gclass);

    int saveGoods(Goods goods);
}
