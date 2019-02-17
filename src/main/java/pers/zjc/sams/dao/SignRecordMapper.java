package pers.zjc.sams.dao;

import pers.zjc.sams.po.SignRecord;

public interface SignRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SignRecord record);

    int insertSelective(SignRecord record);

    SignRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SignRecord record);

    int updateByPrimaryKey(SignRecord record);

    int addSignRecord(SignRecord signRecord);
}