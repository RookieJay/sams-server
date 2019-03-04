package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.Leave;
import pers.zjc.sams.service.LeaveService;
import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.Result;
import pers.zjc.sams.utils.StringUtils;
import pers.zjc.sams.utils.UUIDUtils;
import pers.zjc.sams.vo.LeaveVo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/mobile/leaves")
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    /**
     * 请假 ()
     * */
    @ResponseBody
    @RequestMapping(value = "/askForLeave", method = RequestMethod.POST)
    public Result askForLeave(@RequestBody Leave leave) {
        try {
            String id = UUIDUtils.getUUID();
            leave.setId(id);
            if (leaveService.askForLeave(leave)) {
                return Result.build(Const.HttpStatusCode.HttpStatus_200, "请假成功");
            } else {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "请假失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_500();
        }
    }

    /**
     * 撤销请假
     * */
    @ResponseBody
    @RequestMapping(value = "/revoke", method = RequestMethod.POST)
    public Result revoke(@RequestBody Leave leave) {
            if (StringUtils.isEmpty(leave.getId())) {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "请假编号不能为空", new Object());
            }
        if (leaveService.revoke(leave.getId())) {
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "撤销请假成功", new Object());
        } else {
            return Result.build(Const.HttpStatusCode.HttpStatus_500, "撤销请假失败", new Object());
        }
    }



    /**
     * 所有请假信息查询
     * */
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public Result getAllLeaves() {
        List<LeaveVo> leaveResult = leaveService.getAllLeaves();
        if (leaveResult != null) {
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "获取所有请假信息成功", leaveResult);
        } else {
            return Result.build(Const.HttpStatusCode.HttpStatus_500, "获取所有请假信息失败", new Object());
        }
    }

    /**
     * 个人请假信息查询
     * */
    @ResponseBody
    @RequestMapping(value = "/individual", method = RequestMethod.POST)
    public Result getleaves(@RequestBody Leave leave) {
        try {
            if (StringUtils.isEmpty(String.valueOf(leave.getStuId()))) {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "学号不能为空", new Object());
            }
            Map map = new LinkedHashMap<>();
            List<LeaveVo> leaveResult =  leaveService.getLeavesByStuId(leave);
            if (leaveResult == null) {
                map.put("records", new ArrayList<>());
                return Result.build(Const.HttpStatusCode.HttpStatus_200, "获取个人请假信息成功", map);
            } else {
                map.put("records", leaveResult);
                return Result.build(Const.HttpStatusCode.HttpStatus_200, "获取个人请假信息成功", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_array_500("records");
        }
    }




}
