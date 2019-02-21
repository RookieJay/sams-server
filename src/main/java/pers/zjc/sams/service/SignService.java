package pers.zjc.sams.service;

import pers.zjc.sams.po.SignRecord;

import java.util.List;

public interface SignService {

    boolean sign(SignRecord signRecord);

    boolean deleteSignRecordById(int id);

    List<SignRecord> getStuSignRecords(int id);

    boolean isSigned(SignRecord signRecord);

    List<SignRecord> getCourseSignRecords(SignRecord record);

    List<SignRecord> getCourseSignRecordsWeek(SignRecord record);

    List<SignRecord> getCourseSignRecordsMonth(SignRecord record);
}
