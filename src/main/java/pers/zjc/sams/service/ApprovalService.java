package pers.zjc.sams.service;

import pers.zjc.sams.po.Approval;

import java.util.List;

public interface ApprovalService {

    boolean approval(Approval approval);

    boolean update(Approval approval);

    List<Approval> getApprovals(int tId);
}
