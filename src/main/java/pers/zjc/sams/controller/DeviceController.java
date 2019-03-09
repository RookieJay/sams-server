package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.Device;
import pers.zjc.sams.service.DeviceService;
import pers.zjc.sams.utils.Result;

@Controller
@RequestMapping(value = "api/mobile/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @ResponseBody
    @RequestMapping(value = "api/mobile/update")
    public Result updateDevice(@RequestBody Device device) {
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
}
