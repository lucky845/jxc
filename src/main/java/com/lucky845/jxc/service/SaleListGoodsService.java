package com.lucky845.jxc.service;

import com.lucky845.jxc.domain.ServiceVO;
import com.lucky845.jxc.entity.SaleList;

import java.util.Map;

/**
 * @description
 */
public interface SaleListGoodsService {

    Integer getSaleTotalByGoodsId(Integer goodsId);

    ServiceVO save(SaleList saleList, String saleListGoodsStr);

    Map<String,Object> list(String saleNumber, Integer customerId, Integer state, String sTime,
                            String eTime);

    Map<String,Object> goodsList(Integer saleListId);

    ServiceVO delete(Integer saleListId);

    ServiceVO updateState(Integer saleListId);

    String count(String sTime, String eTime ,Integer goodsTypeId, String codeOrName);

    String getSaleDataByDay(String sTime, String eTime);

    String getSaleDataByMonth(String sTime, String eTime);
}
