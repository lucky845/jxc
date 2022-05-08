package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.CustomerReturnList;
import com.atguigu.jxc.entity.CustomerReturnListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 客户退货
 */
public interface CustomerReturnListGoodsDao {

    Integer getCustomerReturnTotalByGoodsId(Integer goodsId);

    Integer saveCustomerReturnList(CustomerReturnList customerReturnList);

    Integer saveCustomerReturnListGoods(CustomerReturnListGoods customerReturnListGoods);

    List<CustomerReturnList> getCustomerReturnlist(@Param("returnNumber") String returnNumber,
                                                   @Param("customerId") Integer customerId,
                                                   @Param("state") Integer state,
                                                   @Param("sTime") String sTime,
                                                   @Param("eTime") String eTime);

    List<CustomerReturnListGoods> getCustomerReturnListGoodsByCustomerReturnListId(Integer customerReturnListId);

    CustomerReturnList getCustomerReturnList(Integer customerReturnListId);

    Integer deleteCustomerReturnListById(Integer customerReturnListId);

    Integer deleteCustomerReturnListGoodsByCustomerReturnListId(Integer customerReturnListId);

    Integer updateState(Integer customerReturnListId);

    List<CustomerReturnListGoods> getCustomerReturnListGoods(@Param("customerReturnListId") Integer customerReturnListId,
                                                             @Param("goodsTypeId") Integer goodsTypeId,
                                                             @Param("codeOrName") String codeOrName);
}
