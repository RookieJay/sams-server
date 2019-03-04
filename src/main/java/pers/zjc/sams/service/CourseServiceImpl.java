package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.css.CSS2Properties;
import pers.zjc.sams.dao.CourseMapper;
import pers.zjc.sams.po.Course;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getAllCoursesToday() {
        return courseMapper.selectAllToday();
    }
}
