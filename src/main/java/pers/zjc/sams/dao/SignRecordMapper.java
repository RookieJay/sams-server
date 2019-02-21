package pers.zjc.sams.dao;

import pers.zjc.sams.po.SignRecord;

import java.util.List;

public interface SignRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SignRecord record);

    int insertSelective(SignRecord record);

    SignRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SignRecord record);

    int updateByPrimaryKey(SignRecord record);

    int addSignRecord(SignRecord signRecord);

    List<SignRecord> selectByCidAndStuIid(SignRecord signRecord);

    List<SignRecord> selectBystuId(int stuId);

    List<SignRecord> commonSelectToday(SignRecord record);

    List<SignRecord> commonSelectWeek(SignRecord record);

    List<SignRecord> commonSelectMonth(SignRecord record);
}