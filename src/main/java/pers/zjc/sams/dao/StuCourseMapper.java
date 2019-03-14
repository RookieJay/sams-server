package pers.zjc.sams.dao;


import org.apache.ibatis.annotations.Param;
import pers.zjc.sams.po.Course;
import pers.zjc.sams.po.StuCourse;
import pers.zjc.sams.vo.StuCourseVo;

import java.util.List;

public interface StuCourseMapper {

    int insert(StuCourse stuCourse);

    List<StuCourseVo> selectByStuId(@Param("stuId") Integer stuId);
}