package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.AttenceRecordMapper;
import pers.zjc.sams.po.AttenceRecord;
import pers.zjc.sams.utils.Logger;
import pers.zjc.sams.vo.AttenceRecordVo;

import java.util.List;

@Service
@Transactional
public class AttenceServiceImpl implements AttenceService{

    @Autowired
    private AttenceRecordMapper attenceMapper;

    @Override
    public List<AttenceRecord> getByStuId(int stuId) {
        return null;
    }

    @Override
    public List<AttenceRecordVo> getBySingleCondition(AttenceRecord record) {
        try {
            return attenceMapper.selecBySingleCondition(record);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean changeStatus(AttenceRecord record) {
        return false;
    }

    @Override
    public List<AttenceRecordVo> getByMultiContidion(AttenceRecord record) {
        return  attenceMapper.commonSelectCustom(record);
    }

    @Override
    public boolean update(AttenceRecord record) {
     return attenceMapper.updateByPrimaryKey(record) > 0;
    }

    @Override
    public void addRecord(AttenceRecord record) {
        try {
            attenceMapper.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(this.getClass().getName()).error(e.getMessage());
        }
    }
}
