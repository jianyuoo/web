package com.xiaogoubaoxigua.controller;


import com.xiaogoubaoxigua.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaogoubaoxigua.po.Goods;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/selectByGid")
    public Goods selectByGid(@RequestBody int gid) {
        return goodsService.selectByGid(gid);
    }


    @RequestMapping("/selectByClass")
    public Goods selectByClass(@RequestBody String gclass) {
        return goodsService.selectByClass(gclass);
    }

    @RequestMapping("/saveGoods")
    public int saveGoods(@RequestBody Goods goods) {
        return goodsService.saveGoods(goods);
    }
}
