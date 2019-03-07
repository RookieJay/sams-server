package pers.zjc.sams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pers.zjc.sams.utils.ConvertUtils;
import pers.zjc.sams.utils.Logger;
import pers.zjc.sams.utils.Result;

@Controller
@ResponseBody
@RequestMapping(value = "api/mobile/face", method = RequestMethod.POST)
public class FaceController {

    public static final String TAG = FaceController.class.getSimpleName();

    @ResponseBody
    @RequestMapping(value = "/upload")
    public Result uploadFace(@RequestParam("faceFile") MultipartFile faceFile) {
        //getName : 获取表单中文件组件的名字
        //getOriginalFilename : 获取上传文件的原名
        if (faceFile != null) {
            String type = faceFile.getContentType();
            String name = faceFile.getOriginalFilename();
            long size = faceFile.getSize();
            Logger.getLogger(TAG).info("type:" + type + "\n name:" + name + "\n size:" + ConvertUtils.byte2FitMemorySize(size));
            return Result.fail_array_500(name);
        }
        return Result.fail_500();

    }
}
