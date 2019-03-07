package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.service.CourseService;
import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.Result;

@Controller
@RequestMapping(value = "api/mpbile/stuCourse", method = RequestMethod.POST)
public class StuCourseController {

    @Autowired
    private CourseService courseService;

    @ResponseBody
    @RequestMapping(value = "/add")
    public Result addStuCourse(Integer stuId, Integer courseId) {
        try {
            if (courseService.addStuCourse(stuId, courseId)) {
                return Result.ok("学生排课成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_500();
        }
        return Result.build(Const.HttpStatusCode.HttpStatus_500, "学生排课失败");
    }

}
