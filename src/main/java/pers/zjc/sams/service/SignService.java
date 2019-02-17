package pers.zjc.sams.service;

import pers.zjc.sams.po.SignRecord;

import java.util.List;

public interface SignService {

    boolean sign(SignRecord signRecord);

    boolean deleteSignRecordById(int id);

    List<SignRecord> getStuSignRecords(int id);


}
