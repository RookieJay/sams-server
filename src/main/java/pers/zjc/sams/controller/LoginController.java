package pers.zjc.sams.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.*;
import pers.zjc.sams.service.*;
import pers.zjc.sams.utils.*;
import pers.zjc.sams.vo.AttenceRecordVo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/mobile/login")
public class LoginController {

    Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AttenceService attenceService;
    @Autowired
    private DeviceService deviceService;
    /**
     *
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        try {
            String token = JWTUtils.createJWT(user.getAccount(), user.getPassword(), 2 * 60 * 60);
//            Claims claims = JWTUtils.parseJWT(token);
            if (StringUtils.isEmpty(user.getAccount())) {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "账号不能为空", new Object());
            }
            if (StringUtils.isEmpty(user.getPassword())) {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "密码不能为空", new Object());
            }
            if (!userService.isAccountExisted(user)) {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "账号不存在", new Object());
            }
            User localUser = userService.validate(user.getAccount(), user.getPassword());
            Map map = new LinkedHashMap<>();
            if (localUser != null) {
                switch (localUser.getRole()) {
                    //管理员
                    case 0:
                    Admin admin = adminService.getAdmin(localUser.getId());
                    if (admin != null) {
                        map.put("admin", admin);
                        map.put("role", "0");
                        map.put("userId", String.valueOf(localUser.getId()));
                        map.put("token", token);
                        map.put("userName", admin.getName());
                        return new Result(Const.HttpStatusCode.HttpStatus_200, "管理员"+admin.getName()+"登录成功", map);
                    }
                        break;
                    //学生
                    case 1:
                        Student student = studentService.getStudent(localUser.getId());
                        if (student != null) {
                            logger.info(user.getDeviceId());
                            if (!deviceService.getDeviceId(student.getStuId()).equals(user.getDeviceId())) {
                                return Result.build(Const.HttpStatusCode.HttpStatus_401, "此设备非登录账号绑定设备", map);
                            }
                            if (deviceService.isDeviceCanceled(user.getDeviceId())) {
                                return Result.build(Const.HttpStatusCode.HttpStatus_401, "设备未开通，请联系管理员", map);
                            }
                            if (userService.isStuCanceled(student.getStuId())) {
                                return Result.build(Const.HttpStatusCode.HttpStatus_401, "账号未开通，请联系管理员", map);
                            }
                            map.put("role", "1");
                            map.put("userId", String.valueOf(localUser.getId()));
                            map.put("token", token);
                            map.put("student", student);
                            map.put("userName", student.getsName());
                            map.put("major", student.getMajor());
//                            AttenceRecord record = new AttenceRecord();
//                            record.setStuId(student.getStuId());
//                            record.setCreateTime(TimeUtils.getTodayStart());
//                            record.setUpdateTime(TimeUtils.getTodayEnd());
//                            List<AttenceRecordVo> records = attenceService.getByMultiContidion(record);
//                            map.put("records", records);
                            return new Result(Const.HttpStatusCode.HttpStatus_200, "学生"+student.getsName()+"登录成功", map);
                        }
                        break;
                    //教师
                    case 2:
                        Teacher teacher = teacherService.getTeacher(localUser.getId());
                        if (teacher != null) {
                            map.put("teacher", teacher);
                            map.put("role", "2");
                            map.put("userId", String.valueOf(localUser.getId()));
                            map.put("token", token);
                            map.put("major", teacher.getMajor());
                            map.put("userName", teacher.gettName());
                            return new Result(Const.HttpStatusCode.HttpStatus_200, "教师"+teacher.gettName()+"登录成功", map);
                        }
                        break;
                    default:
                        break;
                }
            } else {
                return new Result(Const.HttpStatusCode.HttpStatus_401, "密码错误", new Object());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                return new Result(Const.HttpStatusCode.HttpStatus_500, e.getMessage(), new Object());
            }
        }
        return new Result(Const.HttpStatusCode.HttpStatus_401, "未知错误，请联系管理员！", new Object());
    }



}
