package com.whb.cloud.controller.program;

import com.whb.cloud.controller.store.CbcStoreController;
import com.whb.cloud.utils.Result;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ProgramController
 * Description:
 * date: 2021/4/18 16:35
 *
 * @author wanghanbin
 */

@CrossOrigin
@RestController
@RequestMapping("/program")
@Api(tags = "公用API")
public class ProgramController {

    private static Logger logger = Logger.getLogger(CbcStoreController.class);

    /**
     * @Author: wanghanbin
     * @Description: 图片上传
     * @Date: 16:37 2021/4/18
     * @Param: [file, request, response]
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/upload/headImg", method = {RequestMethod.POST})
    public Result headImg(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String prefix = "";
        String dateStr = "";
        String uploadDir = "C:\\Users\\帅彬\\Desktop\\CBCProject\\src\\main\\resources\\uploadDir";//这个文件夹是创建在:helloworld/target/helloworld/statics/uploadDir,以及helloworld/statics/uploadDir处
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                dateStr = format.format(new Date());
                String filepath = uploadDir + "\\" + dateStr + "." + prefix;
//                String filepath = request.getServletContext().getRealPath("/statics/" + uploadDir + "/" + dateStr + "." + prefix);
//                filepath = filepath.replace("/", "\\");//java中路径转码
                System.out.println(filepath);
                File files = new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("src","/uploadDir/" + dateStr + "." + prefix);

        return Result.success(map);
    }
}
