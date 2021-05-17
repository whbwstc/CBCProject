package com.whb.cloud.service.score.impl;

import com.whb.cloud.dao.score.CbcScoreDao;
import com.whb.cloud.entity.score.CbcScoreEntity;
import com.whb.cloud.service.score.CbcScoreService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("cbcScoreService")
public class CbcScoreServiceImpl extends ServiceImpl<CbcScoreDao, CbcScoreEntity> implements CbcScoreService {

    @Autowired
    CbcScoreDao cbcScoreDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CbcScoreEntity> page = this.page(
                new Query<CbcScoreEntity>().getPage(params),
                new QueryWrapper<CbcScoreEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, String>> getUserScoreByIdNoPage(Integer user_id) {
        try {
            return cbcScoreDao.getUserScoreByIdNoPage(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countUserScore(Integer user_id) {
        try {
            return cbcScoreDao.countUserScore(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}