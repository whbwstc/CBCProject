package com.whb.cloud.service.info;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whb.cloud.entity.info.CbcAdminEntity;
import com.whb.cloud.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
public interface CbcAdminService extends IService<CbcAdminEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

