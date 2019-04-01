package pers.zjc.sams.service;

import pers.zjc.sams.po.Leave;
import pers.zjc.sams.vo.LeaveVo;

import java.util.List;

public interface LeaveService {

    boolean askForLeave(Leave record);

    List<LeaveVo> getLeavesByStuId(Leave stuId);

    List<LeaveVo> getAllLeaves();

    boolean revoke(String id);

    boolean pass(String id);

    boolean refuse(String id);
}
