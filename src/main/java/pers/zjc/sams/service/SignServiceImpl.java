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
            if (i != 0) {
                Logger.getLogger(SignServiceImpl.class).info(i);
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
    public List<SignRecord> getStuSignRecords(int id) {
        return null;
    }
}
