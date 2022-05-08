package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.GoodsTypeService;
import com.atguigu.jxc.service.LogService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucky845
 * @since 2022年05月08日
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Autowired
    private LogService logService;

    /**
     * 查询商品所有分类
     */
    @Override
    public String loadGoodsType() {
        // 查询出所有的菜单
        List<GoodsType> allGoodsTypeList = goodsTypeDao.getGoodsTypeList();

        List<Map<String, Object>> retList = new ArrayList<>();

        // 根节点
        Map<String, Object> parentMap = new HashMap<>();
        for (GoodsType goodsType : allGoodsTypeList) {
            if (goodsType.getPId().equals(-1)) {
                parentMap.put("id", goodsType.getGoodsTypeId());
                parentMap.put("text", goodsType.getGoodsTypeName());
                if (goodsType.getPId() == -1 || goodsType.getPId() == 1) {
                    parentMap.put("state", "closed");
                } else {
                    // 叶子节点
                    parentMap.put("state", "open");
                }
                parentMap.put("iconCls", "goods-type");
                parentMap.put("attributes", new HashMap<>().put("state", goodsType.getGoodsTypeState()));
                // 获取根节点下的所有子节点
                List<Map<String, Object>> childrenList = getChilren(goodsType.getGoodsTypeId(), allGoodsTypeList);
                // 给根节点设置子节点
                parentMap.put("children", childrenList);
            }
        }
        retList.add(parentMap);

        logService.save(new Log(Log.SELECT_ACTION, "查询所有分类树"));

        return new Gson().toJson(retList);
    }

    /**
     * 获取子节点列表
     *
     * @param goodsTypeId      子节点id
     * @param allGoodsTypeList 全部分类集合
     */
    private List<Map<String, Object>> getChilren(Integer goodsTypeId, List<GoodsType> allGoodsTypeList) {
        List<Map<String, Object>> childrenList = new ArrayList<>();
        for (GoodsType goodsType : allGoodsTypeList) {
            if (goodsType.getPId().equals(goodsTypeId)) {
                Map<String, Object> parentMap = new HashMap<>();
                parentMap.put("id", goodsType.getGoodsTypeId());
                parentMap.put("text", goodsType.getGoodsTypeName());
                if (goodsType.getPId() == -1 || goodsType.getPId() == 1) {
                    parentMap.put("state", "closed");
                } else {
                    // 叶子节点
                    parentMap.put("state", "open");
                }
                parentMap.put("iconCls", "goods-type");
                parentMap.put("attributes", new HashMap<>().put("state", goodsType.getGoodsTypeState()));
                childrenList.add(parentMap);
            }
        }
        // 递归设置子节点
        for (Map<String, Object> goodsType : childrenList) {
            goodsType.put("children", getChilren((Integer) goodsType.get("id"), allGoodsTypeList));
        }

        // 如果没有下一个子节点,返回一个空list(递归退出)
        if (childrenList.size() == 0) {
            return new ArrayList<>();
        }
        return childrenList;
    }
}
