package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.Student;
import pers.zjc.sams.po.Teacher;
import pers.zjc.sams.po.User;
import pers.zjc.sams.service.UserService;
import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.Logger;
import pers.zjc.sams.utils.Result;
import pers.zjc.sams.utils.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "api/mobile/users")
public class UserController extends BaseController{

    private static final String TAG = "UserController";

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result getUsers() {
        List<User> userList = userService.getAllUsers();
        if (userList != null && userList.size() > 0) {
            Map map = new LinkedHashMap<>();
            map.put("users", userList);
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "查询用户列表成功",userList);
        }
        return Result.fail_500();
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {
        if (user == null || StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) {
            return Result.build(Const.HttpStatusCode.HttpStatus_401, "账号或密码不能为空");
        }
        try {
            //管理员
            if (user.getRole() == 0) {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "您没有权限注册管理员账号");
            }
            if (userService.isExisted(user)) {
                return Result.build(Const.HttpStatusCode.HttpStatus_401, "该账号已被注册");
            }
            switch (user.getRole()) {
                //学生
                case 1:
                    Student student = new Student();
                    Student maxIdStu = userService.getMaxIdStu();
                    if (maxIdStu == null) {
                        student.setStuId(Const.DEFAULT_ID.DEFAULT_STU_ID);
                        user.setId(Const.DEFAULT_ID.DEFAULT_STU_ID);
                    } else {
                        student.setStuId(maxIdStu.getStuId() + 1);
                        user.setId(maxIdStu.getStuId() + 1);
                    }
                    userService.addStudent(student);
                    break;
                //教师
                case 2:
                    Teacher teacher = new Teacher();
                    Teacher maxIdTeacher = userService.getMaxIdTeacher();
                    if (maxIdTeacher == null || maxIdTeacher.getId() == null) {
                        teacher.setId(Const.DEFAULT_ID.DEFAULT_TEAC_ID);
                        user.setId(Const.DEFAULT_ID.DEFAULT_TEAC_ID);
                    } else {
                        teacher.setId(maxIdTeacher.getId() + 1);
                        user.setId(maxIdTeacher.getId() + 1);
                    }
                    userService.addTeacher(teacher);
                    break;
                default:
                    break;
            }
            if (userService.addUser(user)) {
                return Result.build(Const.HttpStatusCode.HttpStatus_200, "注册成功");
            } else {
                return Result.fail_500();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_500();
        }
    }

    /**
     * 学生信息查询
     * */
    @ResponseBody
    @RequestMapping(value = "/students/info", method = RequestMethod.POST)
    public Result getStudent(@RequestBody Student student) {
        if (StringUtils.isEmpty(String.valueOf(student.getStuId()))) {
            return Result.build(Const.HttpStatusCode.HttpStatus_401, "学号不能为空");
        }
        Student stu = userService.getStudent(student);
        if (stu != null) {
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "学生信息查询成功", stu);
        } else {
            return Result.build(Const.HttpStatusCode.HttpStatus_500, "学生信息查询失败");
        }
    }

    /**
     * 教师信息查询
     * */
    @ResponseBody
    @RequestMapping(value = "/teachers/info", method = RequestMethod.POST)
    public Result getStudent(@RequestBody Teacher teacher) {
        if (StringUtils.isEmpty(String.valueOf(teacher.getId()))) {
            return Result.build(Const.HttpStatusCode.HttpStatus_401, "教师编号不能为空");
        }
        Teacher teac = userService.getTeacher(teacher);
        if (teac != null) {
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "教师信息查询成功", teac);
        } else {
            return Result.build(Const.HttpStatusCode.HttpStatus_500, "教师信息查询失败");
        }
    }

    /**
     * 学生信息修改
     * */
    @ResponseBody
    @RequestMapping(value = "/students/info/modify", method = RequestMethod.POST)
    public Result modifyStudent(@RequestBody Student student) {
        if (StringUtils.isEmpty(String.valueOf(student.getStuId()))) {
            return Result.build(Const.HttpStatusCode.HttpStatus_401, "学号不能为空");
        }
        if (userService.modifyStudent(student)) {
            Student stu = userService.getStudent(student);
            Logger.getLogger(this.getClass().getName()).info(stu.getName());
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "学生"+stu.getName()+"信息修改成功", stu);
        } else {
            return Result.build(Const.HttpStatusCode.HttpStatus_500, "学生信息修改失败");
        }
    }

    /**
     * 教师信息修改
     * */
    @ResponseBody
    @RequestMapping(value = "/teachers/info/modify")
    public Result modifyTeacher(@RequestBody Teacher teacher) {
        if (StringUtils.isEmpty(String.valueOf(teacher.getId()))) {
            return Result.build(Const.HttpStatusCode.HttpStatus_401, "教师编号不能为空");
        }
        if (userService.modifyTeacher(teacher)) {
            Teacher teac = userService.getTeacher(teacher);
            Logger.getLogger(this.getClass().getName()).info(teac.getName());
            return Result.build(Const.HttpStatusCode.HttpStatus_200, "教师"+teac.getName()+"信息修改成功", teac);
        } else {
            return Result.build(Const.HttpStatusCode.HttpStatus_500, "教师信息修改失败");
        }
    }


}
