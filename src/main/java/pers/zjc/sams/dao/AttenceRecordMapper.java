package pers.zjc.sams.dao;

import pers.zjc.sams.po.AttenceRecord;
import pers.zjc.sams.vo.AttenceRecordVo;

import java.util.List;

public interface AttenceRecordMapper {
    int deleteByPrimaryKey(Integer attenceId);

    int insert(AttenceRecord record);

    int insertSelective(AttenceRecord record);

    AttenceRecord selectByPrimaryKey(Integer attenceId);

    int updateByPrimaryKeySelective(AttenceRecord record);

    int updateByPrimaryKey(AttenceRecord record);

    List<AttenceRecordVo> selecBySingleCondition(AttenceRecord record);

    List<AttenceRecordVo> commonSelectCustom(AttenceRecord record);
}