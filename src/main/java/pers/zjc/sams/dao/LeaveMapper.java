package pers.zjc.sams.dao;

import org.apache.ibatis.annotations.Param;
import pers.zjc.sams.po.Leave;

import java.util.List;

public interface LeaveMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);

    List<Leave> selectLeavesByStuId(@Param("stuId") int stuId);

    List<Leave> selectAll();

    int revoke(String id);
}