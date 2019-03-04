package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.AttenceRecord;
import pers.zjc.sams.service.AttenceService;
import pers.zjc.sams.utils.*;
import pers.zjc.sams.vo.AttenceRecordVo;

import java.util.*;

@Controller
@RequestMapping(value = "api/mobile/attence", method = RequestMethod.POST)
public class AttenceController {

    @Autowired
    private AttenceService attenceService;

    /**
     *
     * @param record
     * 单条件（课程id，学生id,考勤状态）组合起始时间查询考勤记录
     */
    @ResponseBody
    @RequestMapping(value = "/list/singleCond")
    public Result getSingleCondRecord(@RequestBody AttenceRecord record) {
        if (record.getStuId() == null && record.getCourseId() == null && record.getStatus() == null ) {
            return Result.build(Const.HttpStatusCode.HttpStatus_401, "至少需要一个参数");
        }
        try {
            Map map = new LinkedHashMap();
            List<AttenceRecordVo> records = attenceService.getBySingleCondition(record);
            map.put("records", records);
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "查询列表成功", records);
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("records", new ArrayList<AttenceRecordVo>());
            return Result.build(Const.HttpStatusCode.HttpStatus_500, e.getMessage(), map);
        }
    }

    private boolean isIdEmpty(Integer id) {
        return StringUtils.isEmpty(String.valueOf(id));
    }


    /**
     *
     * @param record
     * 查询自定义时间段内的某课程或某学生考勤记录
     */
    @ResponseBody
    @RequestMapping(value = "list/multiCond")
    public Result getMultiCondRecord(@RequestBody AttenceRecord record) {
        try {
            Map map = new LinkedHashMap();
            if (record.getCreateTime() == null || record.getUpdateTime() == null) {
                record.setCreateTime(TimeUtils.string2Date("1900-01-01 00:00:00", Const.DateFormat.WITH_HMS));
                record.setCreateTime(TimeUtils.string2Date("2100-01-01 00:00:00", Const.DateFormat.WITH_HMS));
            }
            Logger.getLogger("当前时间").info(TimeUtils.date2String(new Date()));
            System.out.println(TimeUtils.date2String(record.getCreateTime()));
            List<AttenceRecordVo> records = attenceService.getByMultiContidion(record);
            map.put("records", records);
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "查询列表成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_500();
        }
    }

    /**
     * 更新考勤记录
     */
    @ResponseBody
    @RequestMapping(value = "update")
    public Result update(@RequestBody AttenceRecord record) {
        try {
            if (attenceService.update(record)) {
                return Result.ok();
            } else {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "操作失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_500();
        }
    }

}
