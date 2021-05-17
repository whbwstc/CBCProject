package com.whb.cloud.controller.info;

import java.util.Arrays;
import java.util.Map;

import com.whb.cloud.controller.account.CbcAccountAdminController;
import com.whb.cloud.entity.info.CbcAdminEntity;
import com.whb.cloud.service.info.CbcAdminService;
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
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-09 10:07:22
 */
@CrossOrigin
@RestController
@RequestMapping("/cbcadmin")
@Api(tags = "管理员信息相关API")
public class CbcAdminController {

    @Autowired
    private CbcAdminService cbcAdminService;

    private static Logger logger = Logger.getLogger(CbcAccountAdminController.class);


    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = cbcAdminService.queryPage(params);

        return Result.success().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "单条数据查询", notes = "通过admin_id来查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "admin_id", dataType = "Int", required = true, value = "管理员ID")
    })
    @RequestMapping("/info/{admin_id}")
    public Result info(@PathVariable("admin_id") Integer adminId) {
        CbcAdminEntity cbcAdmin = null;
        try {
            cbcAdmin = cbcAdminService.getById(adminId);
            return Result.success(cbcAdmin);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody CbcAdminEntity cbcAdmin) {
        cbcAdminService.save(cbcAdmin);

        return Result.success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改信息", notes = "修改信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "cbcAdmin", required = true, value = "管理员信息")
    })
    @RequestMapping("/update")
    public Result update(@RequestBody CbcAdminEntity cbcAdmin) {
        try {
            boolean flag = cbcAdminService.updateById(cbcAdmin);
            return Result.success(flag);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] adminIds) {
        cbcAdminService.removeByIds(Arrays.asList(adminIds));

        return Result.success();
    }

}
