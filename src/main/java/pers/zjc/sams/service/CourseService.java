package pers.zjc.sams.service;

import pers.zjc.sams.po.Course;
import pers.zjc.sams.vo.StuCourseVo;

import java.util.List;

public interface CourseService {

    List<Course> getAllCoursesToday();

    boolean addStuCourse(Integer stuId, Integer courseId);

    boolean addCourse(Course course);

    List<Course> getAll();

    boolean delete(Course course);

    List<StuCourseVo> getStuCourses(Integer stuId);
}
