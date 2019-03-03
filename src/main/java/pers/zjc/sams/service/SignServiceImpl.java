package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.SignRecordMapper;
import pers.zjc.sams.po.SignRecord;
import pers.zjc.sams.utils.Logger;

import java.util.List;

@Service
@Transactional
public class SignServiceImpl implements SignService {

    @Autowired
    private SignRecordMapper signMapper;

    @Override
    public boolean sign(SignRecord signRecord) {
        try {
            int i = signMapper.addSignRecord(signRecord);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteSignRecordById(int id) {
        return false;
    }

    @Override
    public List<SignRecord> getStuSignRecords(int stuId) {
        List<SignRecord> records = signMapper.selectBystuId(stuId);
        return null;
    }

    @Override
    public boolean isSigned(SignRecord signRecord) {
        try {
            List<SignRecord> records = signMapper.commonSelectToday(signRecord);
            if (records != null && records.size() > 0) {
                Logger.getLogger("123").info(records);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    //当天
    @Override
    public List<SignRecord> getCourseSignRecords(SignRecord record) {
        try {
            List<SignRecord> records = signMapper.commonSelectToday(record);
            if (records != null && records.size() > 0) {
                return records;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    //七天
    @Override
    public List<SignRecord> getCourseSignRecordsWeek(SignRecord record) {
        try {
            List<SignRecord> records = signMapper.commonSelectWeek(record);
            if (records != null && records.size() > 0) {
                return records;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    //一个月
    @Override
    public List<SignRecord> getCourseSignRecordsMonth(SignRecord record) {
        try {
            List<SignRecord> records = signMapper.commonSelectMonth(record);
            if (records != null && records.size() > 0) {
                return records;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<SignRecord> getSignRecordsToday(SignRecord record) {
        return signMapper.commonSelectToday(record);
    }
}
