package com.whb.cloud.service.advice.impl;

import com.whb.cloud.dao.advice.CbcAdviceDao;
import com.whb.cloud.entity.advice.CbcAdviceEntity;
import com.whb.cloud.service.advice.CbcAdviceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("cbcAdviceService")
public class CbcAdviceServiceImpl extends ServiceImpl<CbcAdviceDao, CbcAdviceEntity> implements CbcAdviceService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CbcAdviceEntity> page = this.page(
                new Query<CbcAdviceEntity>().getPage(params),
                new QueryWrapper<CbcAdviceEntity>()
        );

        return new PageUtils(page);
    }

}