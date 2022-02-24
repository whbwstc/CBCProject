package com.whb.cloud.controller.info;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.whb.cloud.controller.order.CbcOrderController;
import com.whb.cloud.entity.info.CbcUserEntity;
import com.whb.cloud.service.info.CbcUserService;
import com.whb.cloud.utils.PageUtils;
import com.whb.cloud.utils.Result;
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
 * @date 2021-03-11 23:01:25
 */
@CrossOrigin
@RestController
@RequestMapping("/cbcuser")
public class CbcUserController {

    private static Logger logger = Logger.getLogger(CbcOrderController.class);

    @Autowired
    private CbcUserService cbcUserService;


    /**
     * @Author: cshnefu
     * @Description:
     * @Date: 13:55 用户列表
     * @Param: [params]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "用户列表", notes = "用户列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "Integer", required = true, value = "页码"),
            @ApiImplicitParam(paramType = "query", name = "limit",dataType = "Integer", required = true, value = "每页数量")
    })
    @RequestMapping("/list")
    public Result list(@RequestParam Integer page,@RequestParam Integer limit){
        //起始行
        Integer startRow = null;
        //查询获取用户信息
        List<Map<String,String>> list = null;
        Integer count = null;
        try {
            startRow = (page-1)*limit;
            list = cbcUserService.findUserAll(startRow,limit);
            //获取数据库信息总数
            count = cbcUserService.count();
            return Result.success(list,"",count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * @Author: cshnefu
     * @Description: 单条数据查询
     * @Date: 10:41 2021/4/28
     * @Param: [userId]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "单条数据查询", notes = "通过userId来查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userId", dataType = "Int", required = true, value = "用户ID")
    })
    @RequestMapping("/info/{userId}")
    public Result info(@PathVariable("userId") Integer userId){
        CbcUserEntity cbcUser = null;
        try {
            cbcUser = cbcUserService.getById(userId);
            return Result.success(cbcUser);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody CbcUserEntity cbcUser){
		cbcUserService.save(cbcUser);

        return Result.success();
    }

    /**
     * @Author: cshnefu
     * @Description: 修改用户信息
     * @Date: 10:40 2021/4/28
     * @Param: [cbcUser]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "修改信息", notes = "修改信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "cbcUser", required = true, value = "用户信息")
    })
    @RequestMapping("/update")
    public Result update(@RequestBody CbcUserEntity cbcUser){
        try {
            boolean flag =  cbcUserService.updateById(cbcUser);
            return Result.success(flag);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    /**
     * @Author: cshnefu
     * @Description: 充值
     * @Date: 10:45 2021/4/28
     * @Param: [user_id, user_money]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "充值", notes = "充值", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "user_id", required = true, value = "用户ID"),
            @ApiImplicitParam(paramType = "query", name = "user_money", required = true, value = "用户余额")
    })
    @RequestMapping("/chargeMoney")
    public Result chargeMoney(@RequestParam Integer user_id,@RequestParam double user_money){
        try {
            return Result.success(cbcUserService.updateUserMoney(user_money,user_id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] userIds){
		cbcUserService.removeByIds(Arrays.asList(userIds));

        return Result.success();
    }

}
