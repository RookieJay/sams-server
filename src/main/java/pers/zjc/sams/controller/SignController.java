package pers.zjc.sams.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.Approval;
import pers.zjc.sams.po.AttenceRecord;
import pers.zjc.sams.po.SignRecord;
import pers.zjc.sams.po.Student;
import pers.zjc.sams.service.ApprovalService;
import pers.zjc.sams.service.AttenceService;
import pers.zjc.sams.service.SignService;
import pers.zjc.sams.service.StudentService;
import pers.zjc.sams.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "api/mobile/sign", method = RequestMethod.POST)
public class SignController {

    @Autowired
    private SignService signService;
    @Autowired
    private AttenceService attenceService;
    @Autowired
    private StudentService studentService;

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
        try {
            if (StringUtils.isEmpty(signRecord.getSignIp())) {
                signRecord.setSignIp(IPUtils.getRealIp(request));
            }
            if (signRecord.getStuId() == null || StringUtils.isEmpty(String.valueOf(signRecord.getStuId()))) {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "学号不能为空");
            }
            //去重，当天本课程只能签到一次
            if (signService.isSigned(signRecord)) {
                return Result.build(Const.HttpStatusCode.HttpStatus_403, "您已经签到");
            }
            if (signService.sign(signRecord)) {
                AttenceRecord record = new AttenceRecord();
                record.setAttenceId(UUIDUtils.getUUID());
                record.setStuId(signRecord.getStuId());
                record.setCourseId(signRecord.getCourseId());
                Student student = studentService.getStudent(signRecord.getStuId());
                if (student != null) {
                    record.setOperator(student.getsName());
                } else {
                    record.setOperator("神秘人");
                }
                attenceService.addRecord(record);
                return Result.build(Const.HttpStatusCode.HttpStatus_200, "签到成功");
            } else {
                return Result.build(Const.HttpStatusCode.HttpStatus_500, "签到失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_500();
        }
    }

    /**
     * @param record 根据要查询的条件输入SignRecord对应参数
     * @param interval 根据1，2，3分别可查询当天、最近一周、最近一个月的签到记录
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public Result getCourseSignRecord(@RequestBody SignRecord record, String interval) {
        Logger.getLogger("interval").info(interval);
        Logger.getLogger("record is null").info(String.valueOf(record == null));
        try {
            List<SignRecord> recordList;
            Map map = new LinkedHashMap();

//            if (record != null && record.getCourseId() == null && record.getStuId() == null) {
//                return Result.build(Const.HttpStatusCode.HttpStatus_401, "至少需要一个参数", map);
//            }
            if (record == null) {
                recordList = signService.getAllSinRecords();
                map.put("records", recordList);
                return Result.build(Const.HttpStatusCode.HttpStatus_200, "查询列表成功", map);
            }
            if (StringUtils.isEmpty(interval)) {
                recordList = signService.getCourseSignRecordsMonth(record);
            } else {
                int i = Integer.valueOf(interval);
                switch (i) {
                    case 0:
                        recordList = signService.getCourseSignRecordsMonth(record);
                        break;
                    case 1:
                        recordList = signService.getSignRecordsToday(record);
                        break;
                    case 2:
                        recordList = signService.getCourseSignRecordsWeek(record);
                        break;
                    case 3:
                        recordList = signService.getCourseSignRecordsMonth(record);
                        break;
                    default:
                        recordList = signService.getCourseSignRecordsMonth(record);
                        break;
            }
        }
        map.clear();
        map.put("records", recordList);
        if (recordList == null) {
            map.clear();
            map.put("records", new ArrayList<>());
          return Result.build(Const.HttpStatusCode.HttpStatus_200, "查询列表成功", map);
        } else if (recordList.size() == 0) {
            map.clear();
            map.put("records", new ArrayList());
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "查询列表成功", map);
        }
        return Result.build(Const.HttpStatusCode.HttpStatus_200, "查询列表成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_array_500("records");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/all")
    public Result getAllRecords() {
        try {
            List<SignRecord> records = signService.getAllSinRecords();
            Map map = new LinkedHashMap();
            map.put("records", records);
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "获勤记录成功" , map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_500("records");
        }
    }

}
