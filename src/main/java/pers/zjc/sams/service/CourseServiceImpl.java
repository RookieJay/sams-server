package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.CourseMapper;
import pers.zjc.sams.dao.StuCourseMapper;
import pers.zjc.sams.po.Course;
import pers.zjc.sams.utils.UUIDUtils;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StuCourseMapper stuCourseMapper;

    @Override
    public List<Course> getAllCoursesToday() {
        return courseMapper.selectAllToday();
    }

    @Override
    public boolean addStuCourse(Integer stuId, Integer courseId) {
        String id = UUIDUtils.getUUID();
        return stuCourseMapper.insert(id, stuId, courseId) > 0;
    }

    @Override
    public boolean addCourse(Course course) {
        return courseMapper.insert(course) > 0;
    }
}
