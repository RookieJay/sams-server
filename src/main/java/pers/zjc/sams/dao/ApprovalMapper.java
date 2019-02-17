package pers.zjc.sams.dao;

import org.apache.ibatis.annotations.Param;
import pers.zjc.sams.po.Approval;

import java.util.List;

public interface ApprovalMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Approval record);

    int insertSelective(Approval record);

    Approval selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Approval record);

    int updateByPrimaryKey(Approval record);

    List<Approval> selectAll();

    List<Approval> selectByTId(@Param("tId") int tId);
}