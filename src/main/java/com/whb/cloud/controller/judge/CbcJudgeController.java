package com.whb.cloud.controller.judge;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.whb.cloud.controller.store.CbcStoreController;
import com.whb.cloud.entity.judge.CbcJudgeEntity;
import com.whb.cloud.service.judge.CbcJudgeService;
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
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
@CrossOrigin
@RestController
@RequestMapping("/cbcjudge")
@Api(tags = "评价信息相关API")
public class CbcJudgeController {

    private static Logger logger = Logger.getLogger(CbcStoreController.class);

    @Autowired
    private CbcJudgeService cbcJudgeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = cbcJudgeService.queryPage(params);

        return Result. success().put("page", page);
    }


    /**
     * @Author: wanghanbin
     * @Description:  查询评价状态
     * @Date: 17:13 2021/4/15
     * @Param: [order_id]
     * @return: com.whb.cloud.utils.Result
     **/
    @RequestMapping("/judgeState/{order_id}")
    public Result judgeState(@PathVariable("order_id") Integer order_id){

        Integer judge_state;
        try {
            judge_state = cbcJudgeService.getJudgeStateById(order_id);
            return Result.success(judge_state);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{judgeId}")
    public Result info(@PathVariable("judgeId") Integer judgeId){
        CbcJudgeEntity cbcJudge = cbcJudgeService.getById(judgeId);

        return Result. success().put("cbcJudge", cbcJudge);
    }

    /**
     * @Author: wanghanbin
     * @Description: 新增评价
     * @Date: 15:26 2021/4/14
     * @Param: [cbcJudge]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "新增评价", notes = "新增评价", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "cbcJudge", dataType = "body", required = true, value = "评价信息")
    })
    @RequestMapping("/save")
    public Result save(@RequestBody CbcJudgeEntity cbcJudge){
        boolean flag = false;
        try {
            flag = cbcJudgeService.save(cbcJudge);
            if (flag) {
                return Result.success(flag);
            } else {
                return Result.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 修改
     */
    @ApiOperation(value = "更新评价", notes = "更新评价", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "cbcJudge", dataType = "body", required = true, value = "评价信息")
    })
    @RequestMapping("/update")
    public Result update(@RequestBody CbcJudgeEntity cbcJudge){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            cbcJudgeService.updateByOrderId(cbcJudge.getJudgeGrade(),cbcJudge.getJudgeText(),sdf.format(date),cbcJudge.getOrderId());
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] judgeIds){
		cbcJudgeService.removeByIds(Arrays.asList(judgeIds));

        return Result. success();
    }

}
