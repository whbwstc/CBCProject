package com.whb.cloud.service.info.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whb.cloud.dao.info.CbcAdminDao;
import com.whb.cloud.entity.info.CbcAdminEntity;
import com.whb.cloud.service.info.CbcAdminService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("cbcAdminService")
public class CbcAdminServiceImpl extends ServiceImpl<CbcAdminDao, CbcAdminEntity> implements CbcAdminService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CbcAdminEntity> page = this.page(
                new Query<CbcAdminEntity>().getPage(params),
                new QueryWrapper<CbcAdminEntity>()
        );

        return new PageUtils(page);
    }

}