package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pers.zjc.sams.po.Face;
import pers.zjc.sams.service.FaceService;
import pers.zjc.sams.utils.ConvertUtils;
import pers.zjc.sams.utils.Logger;
import pers.zjc.sams.utils.Result;

import java.io.File;

@Controller
@ResponseBody
@RequestMapping(value = "api/mobile/face", method = RequestMethod.POST)
public class FaceController {

    public static final String TAG = FaceController.class.getSimpleName();
    Logger logger = Logger.getLogger(FaceController.class);

    @Autowired
    private FaceService faceService;

    @ResponseBody
    @RequestMapping(value = "/upload")
    public Result uploadFace(@RequestParam("faceFile") MultipartFile faceFile, Integer stuId) {
        //getName : 获取表单中文件组件的名字
        //getOriginalFilename : 获取上传文件的原名
        Face face = new Face();
        if (stuId == null) {
            return Result.fail_500("学号不能为空");
        }
        if (faceFile == null) {
            return Result.fail_500("文件不能为空");
        }
        try {
            logger.info(stuId);
            face.setStuId(stuId);
            String type = faceFile.getContentType();
            String name = faceFile.getOriginalFilename();
            long size = faceFile.getSize();
            Logger.getLogger(TAG).info("type:" + type + "\n name:" + name + "\n size:" + ConvertUtils.byte2FitMemorySize(size));
            //图片上传成功后，将图片的地址写到数据库
            String filePath = "E:\\face-upload";//保存图片的路径
            //获取原始图片的拓展名
            String originalFilename = faceFile.getOriginalFilename();
    //            //新的文件名字
    //            String newFileName = UUID.randomUUID()+originalFilename;
    //            //封装上传文件位置的全路径
            File targetFile = new File(filePath, originalFilename);
            if (targetFile.exists()) {
                targetFile.delete();
            }
            //把本地文件上传到封装上传文件位置的全路径
            faceFile.transferTo(targetFile);
            face.setFileName(originalFilename);
            face.setPath(filePath+File.separator+originalFilename);
            if (faceService.isExists(face)) {
                if (faceService.update(face)) {
                    return Result.ok(name+"人脸图片修改成功");
                }
            }
            if (faceService.addFace(face)) {
                return Result.ok(name+"上传成功");
            } else {
                return Result.fail_500("上传失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Result.fail_500("上传失败"+e.getMessage());
        }
    }
}
