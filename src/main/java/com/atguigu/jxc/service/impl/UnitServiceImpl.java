package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.UnitDao;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.UnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucky845
 * @since 2022年05月08日
 */
@Service
public class UnitServiceImpl implements UnitService {

    @Resource
    private UnitDao unitDao;

    @Resource
    private LogService logService;

    /**
     * 查询所有商品单位
     */
    @Override
    public Map<String, Object> list() {
        Map<String, Object> retMap = new HashMap<>();
        List<Unit> rows = unitDao.list();
        retMap.put("rows", rows);
        logService.save(new Log(Log.SELECT_ACTION, "查询所有的商品单位"));
        return retMap;
    }
}
