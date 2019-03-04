package pers.zjc.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.zjc.sams.po.Course;
import pers.zjc.sams.service.CourseService;
import pers.zjc.sams.utils.Result;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/mobile/courses", method = RequestMethod.POST)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ResponseBody
    @RequestMapping(value = "/all")
    public Result getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCoursesToday();
            Map map = new LinkedHashMap();
            map.put("courses", courses);
            return Result.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail_500();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/common")
    public Result commonSelect(@RequestBody Course course) {

        return Result.ok();
    }
}
