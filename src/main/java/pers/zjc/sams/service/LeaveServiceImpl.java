package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.LeaveMapper;
import pers.zjc.sams.po.Leave;
import pers.zjc.sams.vo.LeaveVo;

import java.util.List;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    LeaveMapper leaveMapper;

    @Override
    public boolean askForLeave(Leave record) {
        try {
            if (leaveMapper.insert(record) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<LeaveVo> getLeavesByStuId(Leave leave) {
        try {
            List<LeaveVo> leaves = leaveMapper.commonSelect(leave);
            if (leaves != null && leaves.size() >0) {
                return leaves;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LeaveVo> getAllLeaves() {
        return leaveMapper.selectAll();
    }

    @Override
    public boolean revoke(String id) {
        try {
            if (leaveMapper.revoke(id) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean pass(String id) {
        return leaveMapper.pass(id) > 0;
    }

    @Override
    public boolean refuse(String id) {
        return leaveMapper.refuse(id) > 0;
    }
}
