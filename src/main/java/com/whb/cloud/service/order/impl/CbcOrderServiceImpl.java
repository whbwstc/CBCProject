package com.whb.cloud.service.order.impl;

import com.whb.cloud.dao.order.CbcOrderDao;
import com.whb.cloud.entity.order.CbcOrderEntity;
import com.whb.cloud.service.order.CbcOrderService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("cbcOrderService")
public class CbcOrderServiceImpl extends ServiceImpl<CbcOrderDao, CbcOrderEntity> implements CbcOrderService {

    @Autowired
    private CbcOrderDao cbcOrderDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CbcOrderEntity> page = this.page(
                new Query<CbcOrderEntity>().getPage(params),
                new QueryWrapper<CbcOrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public  List<Map<String,String>> findOrderAll(Integer startRow, Integer pageSize) {
        try {
            return cbcOrderDao.findOrderAll(startRow,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String,String>> getUserOrderById(Integer startRow, Integer pageSize,Integer user_id){
        try {
            return cbcOrderDao.getUserOrderById(startRow, pageSize, user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, String>> getUserOrderByIdNoPage(Integer user_id) {
        try {
            return cbcOrderDao.getUserOrderByIdNoPage(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, String>> getStoreOrderById(Integer startRow, Integer pageSize, Integer store_id) {
        try {
            return cbcOrderDao.getStoreOrderById(startRow, pageSize, store_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map<String, String>> getUserStoreOrderById(Integer store_id, String order_code) {
        try {
            return cbcOrderDao.getUserStoreOrderById(store_id, order_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String,String> getUserOrderInfoById(Integer order_id) {
        try {
            return cbcOrderDao.getUserOrderInfoById(order_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getOrderIdByCode(Integer order_code) {
        try {
            return cbcOrderDao.getOrderIdByCode(order_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Map<String,String> getOrderStateById(Integer order_id) {
        try {
            return cbcOrderDao.getOrderStateById(order_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countUserOrder(Integer userId) {
        try {
            return cbcOrderDao.countUserOrder(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
}

    @Override
    public int countStoreOrder(Integer store_id) {
        try {
            return cbcOrderDao.countStoreOrder(store_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateOrderState(Integer order_id) {
        try {
            return cbcOrderDao.updateOrderState(order_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}