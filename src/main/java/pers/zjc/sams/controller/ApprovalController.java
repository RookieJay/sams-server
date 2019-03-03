package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.Approval;
import pers.zjc.sams.service.ApprovalService;
import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.Result;
import pers.zjc.sams.utils.UUIDUtils;

@Controller
@RequestMapping(value = "api/mobile/approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    /***
     * 审批
     */
    @RequestMapping(value = "check")
    @ResponseBody
    public Result check(@RequestBody Approval approval) {
        String id = UUIDUtils.getUUID();
        approval.setId(id);
        approval.settId(20010001);
        approval.setStatus(0);
        if (approvalService.approval(approval)) {
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "审批成功", approval);
        }
        return Result.build(Const.HttpStatusCode.HttpStatus_500, "服务端未知错误", new Object());
    }
}
