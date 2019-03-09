package pers.zjc.sams.service;

import pers.zjc.sams.po.Device;

public interface DeviceService {

    boolean addStuDevice(Device device);

    boolean updateDevice(Device device);

    boolean isDeviceExisted(String deviceId);

    String getDeviceId(Integer stuId);
}
