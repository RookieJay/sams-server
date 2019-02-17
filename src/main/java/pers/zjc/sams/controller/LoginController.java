package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.Admin;
import pers.zjc.sams.po.Student;
import pers.zjc.sams.po.Teacher;
import pers.zjc.sams.po.User;
import pers.zjc.sams.service.AdminService;
import pers.zjc.sams.service.StudentService;
import pers.zjc.sams.service.TeacherService;
import pers.zjc.sams.service.UserService;
import pers.zjc.sams.utils.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "api/mobile/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        Logger.getLogger("account").info(user.getAccount());
        Logger.getLogger("pwd").info(user.getPassword());
        try {
            if (StringUtils.isEmpty(user.getAccount())) {
                return new Result(Const.HttpStatusCode.HttpStatus_401, "账号不能为空", new Object());
            }
            if (StringUtils.isEmpty(user.getPassword())) {
                return new Result(Const.HttpStatusCode.HttpStatus_401, "密码不能为空", new Object());
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
                        return new Result(Const.HttpStatusCode.HttpStatus_200, "管理员"+admin.getName()+"登录成功", map);
                    }
                        break;
                    //学生
                    case 1:
                        Student student = studentService.getStudent(localUser.getId());
                        if (student != null) {
                            map.put("student", student);
                            return new Result(Const.HttpStatusCode.HttpStatus_200, "学生"+student.getName()+"登录成功", map);
                        }
                        break;
                    //教师
                    case 2:
                        Teacher teacher = teacherService.getTeacher(localUser.getId());
                        if (teacher != null) {
                            map.put("teacher", teacher);
                            return new Result(Const.HttpStatusCode.HttpStatus_200, "教师"+teacher.getName()+"登录成功", map);
                        }
                        break;
                    default:
                        break;
                }
            } else {
                return new Result(Const.HttpStatusCode.HttpStatus_401, "账号或密码错误", new Object());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NullPointerException) {
                return new Result(Const.HttpStatusCode.HttpStatus_500, e.getMessage(), new Object());
            }
        }
        return new Result(Const.HttpStatusCode.HttpStatus_401, "不存在该账号对应的角色信息，请联系管理员！", new Object());
    }

}
