package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.DeviceMapper;
import pers.zjc.sams.po.Device;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public boolean addStuDevice(Device device) {
        return deviceMapper.insert(device) > 0;
    }

    @Override
    public boolean updateDevice(Device device) {
        return  deviceMapper.updateByPrimaryKey(device) > 0;
    }

    @Override
    public boolean isDeviceExisted(String deviceId) {
        return deviceMapper.selectByPrimaryKey(deviceId) != null;
    }

    @Override
    public Integer getDeviceId(Integer stuId) {
        Device device = deviceMapper.selectId(stuId);
        if (device != null) {
            return Integer.valueOf(device.getDeviceId());
        }
        return 0;
    }
}
