package pers.zjc.sams.service;

import pers.zjc.sams.po.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCoursesToday();

    boolean addStuCourse(Integer stuId, Integer courseId);

    boolean addCourse(Course course);
}
