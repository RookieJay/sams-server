package pers.zjc.sams.service;

import pers.zjc.sams.po.Device;
import pers.zjc.sams.vo.DeviceVo;

import java.util.List;

public interface DeviceService {

    boolean addStuDevice(Device device);

    boolean updateDevice(Device device);

    boolean isDeviceExisted(String deviceId);

    String getDeviceId(Integer stuId);

    List<DeviceVo> getAll();

    boolean isDeviceCanceled(String deviceId);
}
