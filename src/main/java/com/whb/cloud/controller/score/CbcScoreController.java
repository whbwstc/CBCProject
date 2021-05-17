package com.whb.cloud.controller.score;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.whb.cloud.controller.order.CbcOrderController;
import com.whb.cloud.entity.score.CbcScoreEntity;
import com.whb.cloud.service.score.CbcScoreService;
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
@RequestMapping("/cbcscore")
@Api(tags = "积分信息相关API")
public class CbcScoreController {

    private static Logger logger = Logger.getLogger(CbcOrderController.class);

    @Autowired
    private CbcScoreService cbcScoreService;

    /**
     * @Author: wanghanbin
     * @Description: 积分查询
     * @Date: 14:19 2021/5/1
     * @Param: [page, limit, user_id]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "积分查询",notes = "积分查询",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "Integer", required = false, value = "页码"),
            @ApiImplicitParam(paramType = "query", name = "limit",dataType = "Integer", required = false, value = "每页数量"),
            @ApiImplicitParam(paramType = "query",name = "user_id",dataType = "Integer",required = true,value = "用户ID")
    })
    @RequestMapping("/scoreInfo")
    public Result userOrder(@RequestParam Integer page,@RequestParam Integer limit,@RequestParam Integer user_id){
        //起始行
        Integer startRow = null;
        //查询获取数据
        List<Map<String,String>> list = null;
        Integer count = null;
        try {
            if (page != null && limit != null){
//                //分页查询
//                startRow = (page-1)*limit;
//                list = cbcOrderService.getUserOrderById(startRow,limit,user_id);
//                //获取数据总数
//                count = cbcOrderService.countUserOrder(user_id);
//                return Result.success(list,"分页查询成功",count);
            }else {
                //不分页查询
                list = cbcScoreService.getUserScoreByIdNoPage(user_id);
                //获取数据总数
                count = cbcScoreService.countUserScore(user_id);
                return Result.success(list,"不分页查询成功",count);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = cbcScoreService.queryPage(params);

        return Result. success().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{scoreId}")
    public Result info(@PathVariable("scoreId") Integer scoreId){
		CbcScoreEntity cbcScore = cbcScoreService.getById(scoreId);

        return Result. success().put("cbcScore", cbcScore);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody CbcScoreEntity cbcScore){
		cbcScoreService.save(cbcScore);

        return Result. success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody CbcScoreEntity cbcScore){
		cbcScoreService.updateById(cbcScore);

        return Result. success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] scoreIds){
		cbcScoreService.removeByIds(Arrays.asList(scoreIds));

        return Result. success();
    }

}
