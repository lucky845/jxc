package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.ReturnList;
import com.atguigu.jxc.entity.ReturnListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 退货商品
 */
public interface ReturnListGoodsDao {


    Integer saveReturnList(ReturnList returnList);

    Integer saveReturnListGoods(ReturnListGoods returnListGoods);

    List<ReturnList> getReturnlist(@Param("returnNumber") String returnNumber,
                                   @Param("supplierId") Integer supplierId,
                                   @Param("state") Integer state,
                                   @Param("sTime") String sTime,
                                   @Param("eTime") String eTime);

    List<ReturnListGoods> getReturnListGoodsByReturnListId(Integer returnListId);

    ReturnList getReturnList(Integer returnListId);

    Integer deleteReturnListById(Integer returnListId);

    Integer deleteReturnListGoodsByReturnListId(Integer returnListId);

    Integer updateState(Integer returnListId);

    List<ReturnListGoods> getReturnListGoods(@Param("returnListId") Integer returnListId,
                                             @Param("goodsTypeId") Integer goodsTypeId,
                                             @Param("codeOrName") String codeOrName);
}
