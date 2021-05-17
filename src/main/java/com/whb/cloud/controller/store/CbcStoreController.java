package com.whb.cloud.controller.store;

import java.util.List;
import java.util.Map;
import com.whb.cloud.entity.store.CbcStoreEntity;
import com.whb.cloud.service.store.CbcStoreService;
import com.whb.cloud.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-03-18 15:02:29
 */
@CrossOrigin
@RestController
@RequestMapping("/cbcstore")
@Api(tags = "寄存点相关API")
public class CbcStoreController {

    private static Logger logger = Logger.getLogger(CbcStoreController.class);

    @Autowired
    private CbcStoreService cbcStoreService;

    /**
     * @Author: wanghanbin
     * @Description:
     * @Date: 13:55 寄存点分页查询
     * @Param: [params]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "寄存点分页查询", notes = "寄存点分页查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "Integer", required = true, value = "页码"),
            @ApiImplicitParam(paramType = "query", name = "limit", dataType = "Integer", required = true, value = "每页数量")
    })
    @RequestMapping("/list")
    public Result list(@RequestParam Integer page, @RequestParam Integer limit) {
        //起始行
        Integer startRow = null;
        //查询获取寄存点信息
        List<Map<String, String>> list = null;
        Integer count = null;
        try {
            startRow = (page - 1) * limit;
            list = cbcStoreService.findStoreAll(startRow, limit);
            //获取数据库寄存点信息总数
            count = cbcStoreService.count();
            return Result.success(list, "分页查询成功", count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description: 寄存点查询
     * @Date: 9:38 2021/5/5
     * @Param: []
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "寄存点查询", notes = "寄存点查询", httpMethod = "GET")
    @RequestMapping("/listAll")
    public Result listAll() {
        //查询获取寄存点信息
        List<CbcStoreEntity> list = null;
        Integer count = null;
        try {
            list = cbcStoreService.list();
            //获取数据库寄存点信息总数
            count = cbcStoreService.count();
            return Result.success(list, "查询成功", count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description: 按所属城市查询寄存点
     * @Date: 9:41 2021/5/5
     * @Param: [store_city]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "按所属城市查询寄存点", notes = "按所属城市查询寄存点", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "store_city", dataType = "String", required = true, value = "寄存点所属城市")
    })
    @RequestMapping("/getStoreByCity")
    public Result getStoreByCity(@RequestParam String store_city){
        //查询获取寄存点信息
        List<CbcStoreEntity> list = null;
        try {
            list = cbcStoreService.getStoreByCity(store_city);
            if (list != null){
                return Result.success(list);
            }else {
                return Result.fail("该城市暂无寄存点");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description:
     * @Date: 10:35 获取单条记录
     * @Param: [storeId]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "获取单条记录", notes = "获取单条记录", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "storeId", dataType = "Int", required = true, value = "寄存点ID")
    })
    @RequestMapping("/info/{storeId}")
    public Result info(@PathVariable("storeId") Integer storeId) {
        try {
            CbcStoreEntity cbcStore = cbcStoreService.getById(storeId);
            return Result.success(cbcStore);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * @Author: wanghanbin
     * @Description: 新增寄存点
     * @Date: 10:34 2021/3/26
     * @Param: [storeId]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "新增寄存点", notes = "新增寄存点", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "cbcStore", dataType = "body", required = true, value = "寄存点信息")
    })
    @RequestMapping("/save")
    public Result save(@RequestBody CbcStoreEntity cbcStore) {
        try {
            boolean flag = cbcStoreService.save(cbcStore);
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
     * @Author: wanghanbin
     * @Description: 修改寄存点状态
     * @Date: 14:46 2021/3/28
     * @Param: [state, store_id]
     * @return: int
     **/
    @ApiOperation(value = "修改寄存点状态", notes = "修改寄存点状态", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "state", dataType = "String", required = true, value = "状态"),
            @ApiImplicitParam(paramType = "path", name = "store_id", dataType = "Integer", required = true, value = "寄存点ID")
    })
    @RequestMapping("/updateState/{state}/{store_id}")
    public Result update(@PathVariable("state") String state, @PathVariable("store_id") Integer store_id) {
        try {
            cbcStoreService.updateStateById(state, store_id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody CbcStoreEntity cbcStore) {
        cbcStoreService.updateById(cbcStore);

        return Result.success();
    }

    /**
     * @Author: wanghanbin
     * @Description: 删除寄存点
     * @Date: 15:22 2021/3/28
     * @Param: [store_id]
     * @return: com.whb.cloud.utils.Result
     **/
    @ApiOperation(value = "删除寄存点", notes = "删除寄存点", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "store_id", dataType = "String", required = true, value = "寄存点ID")
    })
    @RequestMapping("/delete/{store_id}")
    public Result delete(@PathVariable("store_id") String store_id) {
        try {
            cbcStoreService.removeById(store_id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }
}
