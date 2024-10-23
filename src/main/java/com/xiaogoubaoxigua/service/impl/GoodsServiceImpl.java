package com.xiaogoubaoxigua.service.impl;

import com.xiaogoubaoxigua.mapper.GoodsMapper;
import com.xiaogoubaoxigua.po.Goods;
import com.xiaogoubaoxigua.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper GoodsMapper;

    @Override
    public Goods selectByGid(int gid) {
        return GoodsMapper.selectByGid(gid);
    }

    @Override
    public Goods selectByClass(String gclass) {
        return GoodsMapper.selectByClass(gclass);
    }

    @Override
    public int saveGoods(Goods goods) {
        return GoodsMapper.saveGoods(goods);
    }


}
