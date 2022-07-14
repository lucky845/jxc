package com.lucky845.jxc.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lucky845.jxc.dao.DamageListDao;
import com.lucky845.jxc.dao.DamageListGoodsDao;
import com.lucky845.jxc.dao.GoodsDao;
import com.lucky845.jxc.entity.DamageList;
import com.lucky845.jxc.entity.DamageListGoods;
import com.lucky845.jxc.entity.Goods;
import com.lucky845.jxc.entity.Log;
import com.lucky845.jxc.service.DamageListGoodsService;
import com.lucky845.jxc.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucky845
 * @since 2022年05月10日
 */
@Service
public class DamageListGoodsServiceImpl implements DamageListGoodsService {

    @Resource
    private DamageListGoodsDao damageListGoodsDao;

    @Resource
    private DamageListDao damageListDao;

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private LogService logService;

    /**
     * 保存商品报损单
     *
     * @param damageList         损坏清单
     * @param damageListGoodsStr 损坏商品集合
     */
    @Override
    public void save(DamageList damageList, String damageListGoodsStr) {

        Gson gson = new Gson();

        List<DamageListGoods> damageListGoodsList = gson.fromJson(damageListGoodsStr, new TypeToken<List<DamageListGoods>>() {
        }.getType());

        // 需要获取主键,所以需要先保存
        damageListDao.save(damageList);

        for (DamageListGoods damageListGoods : damageListGoodsList) {
            damageListGoods.setDamageListId(damageList.getDamageListId());
            // 修改商品数量
            Goods goods = goodsDao.getGoods(damageListGoods.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity() - damageListGoods.getGoodsNum());
            goodsDao.updateGoods(goods);
        }

        damageListGoodsDao.save(damageListGoodsList);
        logService.save(new Log(Log.INSERT_ACTION, "保存报损单信息"));
    }

    /**
     * 报损单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    @Override
    public Map<String, Object> list(String sTime, String eTime) {
        Map<String, Object> retMap = new HashMap<>();
        List<DamageList> damageListList = damageListDao.list(sTime, eTime);
        retMap.put("rows", damageListList);
        return retMap;
    }

    /**
     * 查询报损单商品信息
     *
     * @param damageListId 报损单id
     */
    @Override
    public Map<String, Object> goodsList(Integer damageListId) {
        Map<String, Object> retMap = new HashMap<>();
        List<DamageListGoods> damageListGoodsList = damageListGoodsDao.goodsList(damageListId);
        retMap.put("rows", damageListGoodsList);
        return retMap;
    }
}
