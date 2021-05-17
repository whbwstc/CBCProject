package com.whb.cloud.controller.advice;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.whb.cloud.controller.store.CbcStoreController;
import com.whb.cloud.entity.advice.CbcAdviceEntity;
import com.whb.cloud.service.advice.CbcAdviceService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: wanghanbin
 * @Date: 22:39 2021/3/13
 **/
@CrossOrigin
@RestController
@RequestMapping("/cbcadvice")
@Api(tags = "意见信息相关API")
public class CbcAdviceController {

    private static Logger logger = Logger.getLogger(CbcStoreController.class);

    @Autowired
    private CbcAdviceService cbcAdviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = cbcAdviceService.queryPage(params);

        return Result.success().put("page", page);
    }

    /**
     * @Author: wanghanbin
     * @Description:
     * @Date: 22:39 2021/3/13
     * @Param: [adviceId]
     * @return: com.whb.cloud.utils.Result
     **/
    @RequestMapping("/info/{adviceId}")
    public Result info(@PathVariable("adviceId") Integer adviceId) {
        CbcAdviceEntity cbcAdvice = cbcAdviceService.getById(adviceId);

        return Result.success(cbcAdvice);
    }


    /**
     * @Author: wanghanbin
     * @Description: 保存意见反馈
     * @Date: 0:48 2021/4/19
     * @Param: [cbcAdvice]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "更新评价", notes = "更新评价", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "cbcAdvice", dataType = "body", required = true, value = "意见反馈信息")
    })
    @RequestMapping("/save")
    public Result save(@RequestBody CbcAdviceEntity cbcAdvice) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            cbcAdvice.setAdviceTime(sdf.format(date));
            cbcAdviceService.save(cbcAdvice);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.fail();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody CbcAdviceEntity cbcAdvice) {
        cbcAdviceService.updateById(cbcAdvice);

        return Result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] adviceIds) {
        cbcAdviceService.removeByIds(Arrays.asList(adviceIds));

        return Result.success();
    }

}
