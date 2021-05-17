package com.whb.cloud.service.judge.impl;

import com.whb.cloud.dao.judge.CbcJudgeDao;
import com.whb.cloud.entity.judge.CbcJudgeEntity;
import com.whb.cloud.service.judge.CbcJudgeService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("cbcJudgeService")
public class CbcJudgeServiceImpl extends ServiceImpl<CbcJudgeDao, CbcJudgeEntity> implements CbcJudgeService {

    @Autowired
    CbcJudgeDao cbcJudgeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CbcJudgeEntity> page = this.page(
                new Query<CbcJudgeEntity>().getPage(params),
                new QueryWrapper<CbcJudgeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int getJudgeStateById(Integer order_id) {
        try {
            return cbcJudgeDao.getJudgeStateById(order_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByOrderId(Integer judgeGrade,String judgeText, String judgeTime,Integer order_id) {
        try {
            return cbcJudgeDao.updateByOrderId(judgeGrade,judgeText, judgeTime, order_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}