package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.SignRecord;
import pers.zjc.sams.service.SignService;
import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.Logger;
import pers.zjc.sams.utils.Result;
import pers.zjc.sams.utils.UUIDUtils;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "api/mobile/sign", method = RequestMethod.POST)
public class SignController {

    @Autowired
    private SignService signService;

    @ResponseBody
    @RequestMapping(value = "")
    public Result sign(@RequestBody SignRecord signRecord) {
        String signId = UUIDUtils.getUUID();
//        signRecord.setId(Integer.valueOf(signId));
        signRecord.setId(signId);
//        signRecord.setStuId(41504145);
//        signRecord.setLocation("西区1栋302");
//        signRecord.setCourseId(1541);
//        signRecord.setSignIp("220.166.50.62");
        signRecord.setSignTime(new Date());
        Logger.getLogger(SignController.class).info(signRecord.toString());
        if (signService.sign(signRecord)) {
            Map<String, String> map = new HashMap<>();
            map.put("signId", signId);
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "签到成功", map);
        } else {
            return Result.build(Const.HttpStatusCode.HttpStatus_500, "签到失败", new SignRecord());
        }

    }
}
