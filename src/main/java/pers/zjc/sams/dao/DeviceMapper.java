package pers.zjc.sams.dao;

import org.apache.ibatis.annotations.Param;
import pers.zjc.sams.po.Device;

public interface DeviceMapper {

    int deleteByPrimaryKey(@Param("deviceId") String deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    Device selectId(@Param("stuId") Integer stuId);
}