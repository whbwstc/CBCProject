package com.whb.cloud.service.info.impl;

import com.whb.cloud.dao.info.CbcUserDao;
import com.whb.cloud.entity.info.CbcUserEntity;
import com.whb.cloud.service.info.CbcUserService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("cbcUserService")
public class CbcUserServiceImpl extends ServiceImpl<CbcUserDao, CbcUserEntity> implements CbcUserService {

    @Autowired
    CbcUserDao cbcUserDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CbcUserEntity> page = this.page(
                new Query<CbcUserEntity>().getPage(params),
                new QueryWrapper<CbcUserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, String>> findUserAll(Integer startRow, Integer pageSize) {
        try {
            return cbcUserDao.findUserAll(startRow,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateUserMoney(double user_money, int user_id) {
        try {
            return cbcUserDao.updateUserMoney(user_money, user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateUserScore(int user_score, int user_id) {
        try {
            return cbcUserDao.updateUserScore(user_score, user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}