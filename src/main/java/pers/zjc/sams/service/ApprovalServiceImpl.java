package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.ApprovalMapper;
import pers.zjc.sams.po.Approval;

import java.util.List;

@Service
@Transactional
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private ApprovalMapper approvalMapper;

    @Override
    public boolean approval(Approval approval) {
        try {
            int resultCode = approvalMapper.insert(approval);
            if (resultCode > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean update(Approval approval) {
        try {
            int resultCode = approvalMapper.updateByPrimaryKey(approval);
            if (resultCode > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public List<Approval> getApprovals(int tId) {
        try {
            List<Approval> approvalList = approvalMapper.selectByTId(tId);
            if (null != approvalList && approvalList.size() > 0) {
                return approvalList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
