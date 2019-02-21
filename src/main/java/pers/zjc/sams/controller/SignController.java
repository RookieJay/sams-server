package pers.zjc.sams.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.SignRecord;
import pers.zjc.sams.service.SignService;
import pers.zjc.sams.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "api/mobile/sign", method = RequestMethod.POST)
public class SignController {

    @Autowired
    private SignService signService;

    /**
     * 签到
     */
    @ResponseBody
    @RequestMapping(value = "")
    public Result sign(@RequestBody SignRecord signRecord, HttpServletRequest request) {
        String signId = UUIDUtils.getUUID();
        signRecord.setId(signId);
        signRecord.setSignTime(new Date());
        Logger.getLogger(SignController.class).info(signRecord.toString());
        if (StringUtils.isEmpty(signRecord.getSignIp())) {
            signRecord.setSignIp(IPUtils.getRealIp(request));
        }
        if (signRecord.getStuId() != null && !StringUtils.isEmpty(String.valueOf(signRecord.getStuId()))) {
            signService.getStuSignRecords(signRecord.getStuId());
        } else {
            return Result.build(Const.HttpStatusCode.HttpStatus_401, "学号不能为空");
        }
        //去重，当天本课程只能签到一次
        if (signService.isSigned(signRecord)) {
            return Result.build(Const.HttpStatusCode.HttpStatus_401, "您已经签到");
        }
        if (signService.sign(signRecord)) {
            Map<String, String> map = new HashMap<>();
            map.put("signId", signId);
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "签到成功", map);
        } else {
            return Result.build(Const.HttpStatusCode.HttpStatus_500, "签到失败");
        }
    }

    /**
     * @param record 根据要查询的条件输入SignRecord对应参数
     * @param interval 根据输入参数可查询1：当天；2：七天；3：一个月的记录
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public Result getCourseSignRecord(@RequestBody SignRecord record, int interval) {
        if (StringUtils.isEmpty(String.valueOf(record.getCourseId()))) {
            return Result.build(Const.HttpStatusCode.HttpStatus_401, "课程编号不能为空");
        }
        Map map = new LinkedHashMap();
        List<SignRecord> recordList = signService.getCourseSignRecords(record);
        if (interval == Const.INTERVAL.TODAY) {
        } else if (interval == Const.INTERVAL.WEEK) {
            recordList = signService.getCourseSignRecordsWeek(record);
        } else if (interval == Const.INTERVAL.MONTH) {
            recordList = signService.getCourseSignRecordsMonth(record);
        }
        if (recordList != null && recordList.size() > 0) {
            map.put("signRecords", recordList);
        }
        return Result.build(Const.HttpStatusCode.HttpStatus_200, "查询列表成功", map);
    }

}
