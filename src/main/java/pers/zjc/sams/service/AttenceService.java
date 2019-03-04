package pers.zjc.sams.service;

import pers.zjc.sams.po.AttenceRecord;
import pers.zjc.sams.vo.AttenceRecordVo;

import java.util.List;

public interface AttenceService {

    List<AttenceRecord> getByStuId(int stuId);

    List<AttenceRecordVo> getBySingleCondition(AttenceRecord record);

    //更改考勤状态
    boolean changeStatus(AttenceRecord record);

    List<AttenceRecordVo> getByMultiContidion(AttenceRecord record);

    boolean update(AttenceRecord record);

    void addRecord(AttenceRecord record);
}
