package com.whb.cloud.service.store.impl;

import com.whb.cloud.dao.store.CbcStoreDao;
import com.whb.cloud.entity.store.CbcStoreEntity;
import com.whb.cloud.service.store.CbcStoreService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Query;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("cbcStoreService")
public class CbcStoreServiceImpl extends ServiceImpl<CbcStoreDao, CbcStoreEntity> implements CbcStoreService {

    @Autowired
    private CbcStoreDao cbcStoreDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CbcStoreEntity> page = this.page(
                new Query<CbcStoreEntity>().getPage(params),
                new QueryWrapper<CbcStoreEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public  List<Map<String,String>> findStoreAll(Integer startRow, Integer pageSize) {
        try {
            return cbcStoreDao.findStoreAll(startRow,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CbcStoreEntity> getStoreByCity(String store_city) {
        try {
            return cbcStoreDao.getStoreByCity(store_city);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: cshnefu
     * @Description: 修改寄存点状态
     * @Date: 14:49 2021/3/28
     * @Param: [state, store_id]
     * @return: int
     **/
    @Override
    public int updateStateById(String state, Integer store_id) {
        try {
            return cbcStoreDao.updateStateById(state,store_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateSumById(Integer luggage_num, Integer bag_num, Integer store_id) {
        try {
            return cbcStoreDao.updateSumById(luggage_num, bag_num, store_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}