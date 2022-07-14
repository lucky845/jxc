package com.lucky845.jxc.service;

import com.lucky845.jxc.domain.ServiceVO;
import com.lucky845.jxc.entity.PurchaseList;

import java.util.Map;

/**
 * @description
 */
public interface PurchaseListGoodsService {

   ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr);

    Map<String, Object> list(String purchaseNumber,
                            Integer supplierId,
                            Integer state,
                            String sTime,
                            String eTime);

    Map<String, Object> goodsList(Integer purchaseListId);

    ServiceVO delete(Integer purchaseListId);

    ServiceVO updateState(Integer purchaseListId);

    String count(String sTime, String eTime ,Integer goodsTypeId, String codeOrName);
}
