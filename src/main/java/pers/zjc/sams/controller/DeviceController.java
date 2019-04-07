package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.Device;
import pers.zjc.sams.service.DeviceService;
import pers.zjc.sams.utils.Logger;
import pers.zjc.sams.utils.Result;
import pers.zjc.sams.vo.DeviceVo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/mobile/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @ResponseBody
    @RequestMapping(value = "/update")
    public Result updateDevice(@RequestBody Device device) {
        Logger.getLogger("updateDevice").info(device.getDeviceId() + " "+ device.getDeviceStatus());
        try {
            if (deviceService.updateDevice(device)) {
                return Result.ok("设备修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_500();
        }
        return Result.fail_500("设备修改失败");
    }

    @ResponseBody
    @RequestMapping(value = "/all")
    public Result getAllDevices() {
        try {
            List<DeviceVo> devices = deviceService.getAll();
            Map map = new LinkedHashMap();
            map.put("devices", devices);
            return Result.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_array_500("devices");
        }
    }

}
