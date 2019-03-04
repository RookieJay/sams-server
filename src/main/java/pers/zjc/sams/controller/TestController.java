package pers.zjc.sams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.IPUtils;
import pers.zjc.sams.utils.Result;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "test", method = RequestMethod.POST)
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/1")
    public Result test(HttpServletRequest request) {
        String ipAddr = IPUtils.getRealIp(request);
        return Result.build(Const.HttpStatusCode.HttpStatus_200, "GET测试成功", ipAddr);
    }
}
