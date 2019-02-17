package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.StudentMapper;
import pers.zjc.sams.dao.TeacherMapper;
import pers.zjc.sams.po.Student;
import pers.zjc.sams.po.Teacher;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacher(int id) {
        try {
            Teacher teacher = teacherMapper.selectByPrimaryKey(id);
            if (teacher != null) {
                return teacher;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
