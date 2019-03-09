package pers.zjc.sams.dao;

import pers.zjc.sams.po.Course;

import java.util.List;

public interface CourseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> selectAllToday();

    List<Course> selectAll();
}