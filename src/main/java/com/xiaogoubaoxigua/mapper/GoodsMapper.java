package com.xiaogoubaoxigua.mapper;

import com.xiaogoubaoxigua.po.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GoodsMapper {

    //商品是否已经存在的验证
    @Select("select * from goods where gid=#{gid}")
    public Goods selectByGid(int gid);

    //添加商品信息
    @Insert("insert into goods value (#{gid},#{gname},#{value},#{gclass},#{remarks})")
    public int saveGoods(Goods goods);

    //查询商品并按类别输出
    @Select("select * from goods where class=#{gclass}")
    public Goods selectByClass(String gclass);
}
