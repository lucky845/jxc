package com.lucky845.jxc.service.impl;

import com.google.gson.Gson;
import com.lucky845.jxc.dao.GoodsDao;
import com.lucky845.jxc.dao.GoodsTypeDao;
import com.lucky845.jxc.entity.GoodsType;
import com.lucky845.jxc.entity.Log;
import com.lucky845.jxc.service.GoodsTypeService;
import com.lucky845.jxc.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private GoodsTypeDao goodsTypeDao;

    @Resource
    private LogService logService;

    @Resource
    private GoodsDao goodsDao;

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
                HashMap<Object, Object> map = new HashMap<>();
                map.put("state", goodsType.getGoodsTypeState());
                parentMap.put("attributes", map);
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
                if (goodsType.getGoodsTypeState() == 1) {
                    parentMap.put("state", "closed");
                } else {
                    // 叶子节点
                    parentMap.put("state", "open");
                }
                parentMap.put("iconCls", "goods-type");
                HashMap<Object, Object> map = new HashMap<>();
                map.put("state", goodsType.getGoodsTypeState());
                parentMap.put("attributes", map);
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


    /**
     * 根据父id查询所有的子id
     *
     * @param parentId 父id
     */
    public List<Integer> getChildrenIdList(Integer parentId, List<GoodsType> allGoodsTypeList) {
        List<Integer> childrenIdList = new ArrayList<>();

        getChilren(parentId, allGoodsTypeList, childrenIdList);

        // 递归获取子节点
        for (Integer childrenId : childrenIdList) {
            getChilren(childrenId, allGoodsTypeList, childrenIdList);
        }

        // 递归退出
        if (childrenIdList.size() == 0) {
            // 没有子节点,直接返回当前分类下的商品列表(在Goods里面判断)
            return new ArrayList<>();
        }
        return childrenIdList;
    }

    private void getChilren(Integer parentId, List<GoodsType> allGoodsTypeList, List<Integer> childrenIdList) {
        for (GoodsType goodsType : allGoodsTypeList) {
            if (parentId.equals(goodsType.getPId())) {
                childrenIdList.add(goodsType.getGoodsTypeId());
            }
        }
    }

    /**
     * 新增分类
     *
     * @param goodsTypeName 商品类型名称
     * @param pId           父id
     */
    @Override
    public void save(String goodsTypeName, Integer pId) {
        // 如果父节点下原来没有子节点，现在增加了一个子节点，就需要改变父节点的状态
        int count = 0;
        GoodsType goodsType = null;
        List<GoodsType> goodsTypeList = goodsTypeDao.getGoodsTypeList();
        for (GoodsType type : goodsTypeList) {
            if (type.getPId().equals(pId)) {
                count++;
            }
            if (type.getGoodsTypeId().equals(pId)) {
                goodsType = type;
            }
        }
        if (goodsType != null && count == 0) {
            goodsType.setGoodsTypeState(1);
            goodsTypeDao.updateGoodsTypeState(goodsType);
            logService.save(new Log(Log.UPDATE_ACTION, "修改商品分类信息"));
        }
        // 因为新添加,所以一定是叶子节点
        GoodsType goodsType1 = new GoodsType(goodsTypeName, 0, pId);
        goodsTypeDao.addGoodsType(goodsType1);
        logService.save(new Log(Log.INSERT_ACTION, "新增商品分类信息"));
    }

    /**
     * 删除分类
     *
     * @param goodsTypeId 商品分类id
     */
    @Override
    public void delete(Integer goodsTypeId) {
        int count = 0;
        GoodsType goodsType1 = null;
        GoodsType goodsType2 = null;
        // 先判断当前分类id下面是否有其他节点
        List<GoodsType> goodsTypeList = goodsTypeDao.getGoodsTypeList();
        for (GoodsType goodsType : goodsTypeList) {
            // 先查询到当前的goodsType
            if (goodsType.getGoodsTypeId().equals(goodsTypeId)) {
                goodsType1 = goodsType;
            }
        }
        for (GoodsType goodsType : goodsTypeList) {
            if (goodsType1 != null && goodsType1.getPId().equals(goodsType.getPId())) {
                // 下面至少有一个子节点，禁止删除（已经在前端实现
                count++;
            }
        }
        for (GoodsType goodsType : goodsTypeList) {
            // 再查询父节点对象
            if (goodsType1 != null && goodsType1.getPId().equals(goodsType.getGoodsTypeId())) {
                goodsType2 = goodsType;
            }
        }

        // 再判断父节点下面是不是只有当前节点，如果只有当前节点，那么就需要把父节点改为叶子节点
        if (goodsType2 != null && count == 1) {
            goodsType2.setGoodsTypeState(0);
            goodsTypeDao.updateGoodsTypeState(goodsType2);
            logService.save(new Log(Log.UPDATE_ACTION, "修改商品分类信息"));
        }
        goodsTypeDao.delete(goodsTypeId);

        logService.save(new Log(Log.DELETE_ACTION, "删除商品分类信息"));

    }
}
