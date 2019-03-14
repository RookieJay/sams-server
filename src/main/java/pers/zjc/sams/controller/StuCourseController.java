package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.Course;
import pers.zjc.sams.service.CourseService;
import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.Result;
import pers.zjc.sams.vo.StuCourseVo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/mobile/stuCourse", method = RequestMethod.POST)
public class StuCourseController {

    @Autowired
    private CourseService courseService;

    @ResponseBody
    @RequestMapping(value = "/common")
    public Result getStuCourses(Integer stuId) {
        if (stuId == null) {
            return Result.build(Const.HttpStatusCode.HttpStatus_403, "学号不能为空");
        }
        try {
            List<StuCourseVo> courses = courseService.getStuCourses(stuId);
            Map<String, List<StuCourseVo>> map = new LinkedHashMap<>();
            map.put("courses", courses);
            return Result.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_obj_500("courses");
        }
    }

}
