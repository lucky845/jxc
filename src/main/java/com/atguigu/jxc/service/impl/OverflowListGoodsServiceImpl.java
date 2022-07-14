package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.OverflowListDao;
import com.atguigu.jxc.dao.OverflowListGoodsDao;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.OverflowListGoodsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
public class OverflowListGoodsServiceImpl implements OverflowListGoodsService {

    @Resource
    private LogService logService;

    @Resource
    private OverflowListGoodsDao overflowListGoodsDao;

    @Resource
    private OverflowListDao overflowListDao;

    @Resource
    private GoodsDao goodsDao;

    /**
     * 保存商品报溢单
     *
     * @param overflowList         报溢清单
     * @param overflowListGoodsStr 报溢商品集合
     */
    @Override
    public void save(OverflowList overflowList, String overflowListGoodsStr) {

        Gson gson = new Gson();
        List<OverflowListGoods> overflowListGoodsList = gson.fromJson(overflowListGoodsStr, new TypeToken<List<OverflowListGoods>>() {
        }.getType());

        // 需要获取主键,所以需要先保存
        overflowListDao.save(overflowList);

        for (OverflowListGoods overflowListGoods : overflowListGoodsList) {
            overflowListGoods.setOverflowListId(overflowList.getOverflowListId());
            // 修改商品数量
            Goods goods = goodsDao.getGoods(overflowListGoods.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity() - overflowListGoods.getGoodsNum());
            goodsDao.updateGoods(goods);
        }

        overflowListGoodsDao.save(overflowListGoodsList);
        logService.save(new Log(Log.INSERT_ACTION, "保存报溢单信息"));
    }

    /**
     * 报溢单查询
     *
     * @param sTime 开始时间
     * @param eTime 结束时间
     */
    @Override
    public Map<String, Object> list(String sTime, String eTime) {
        Map<String, Object> retMap = new HashMap<>();
        List<OverflowList> overflowListList = overflowListDao.list(sTime, eTime);
        retMap.put("rows", overflowListList);
        return retMap;
    }

    /**
     * 查询报溢单商品信息
     *
     * @param overflowListId 报溢单id
     */
    @Override
    public Map<String, Object> goodsList(Integer overflowListId) {
        Map<String, Object> retMap = new HashMap<>();
        List<OverflowListGoods> overflowListGoodsList = overflowListGoodsDao.goodsList(overflowListId);
        retMap.put("rows", overflowListGoodsList);
        return retMap;
    }
}
