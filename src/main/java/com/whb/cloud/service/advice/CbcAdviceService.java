package com.whb.cloud.service.advice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whb.cloud.entity.advice.CbcAdviceEntity;
import com.whb.cloud.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
public interface CbcAdviceService extends IService<CbcAdviceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

