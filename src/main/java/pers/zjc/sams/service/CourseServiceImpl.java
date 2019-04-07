package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.CourseMapper;
import pers.zjc.sams.dao.StuCourseMapper;
import pers.zjc.sams.po.Course;
import pers.zjc.sams.po.StuCourse;
import pers.zjc.sams.utils.UUIDUtils;
import pers.zjc.sams.vo.StuCourseVo;

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
        StuCourse stuCourse = new StuCourse();
        stuCourse.setId(id);
        stuCourse.setCourseId(courseId);
        stuCourse.setStuId(stuId);
        return stuCourseMapper.insert(stuCourse) > 0;
    }

    @Override
    public boolean addCourse(Course course) {
        return courseMapper.insert(course) > 0;
    }

    @Override
    public List<Course> getAll() {
        return courseMapper.selectAll();
    }

    @Override
    public boolean delete(Course course) {
        return courseMapper.deleteByPrimaryKey(course.getId()) > 0;
    }

    @Override
    public List<StuCourseVo> getStuCourses(Integer stuId) {
        return stuCourseMapper.selectByStuId(stuId);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseMapper.updateByPrimaryKey(course) > 0;
    }
}
