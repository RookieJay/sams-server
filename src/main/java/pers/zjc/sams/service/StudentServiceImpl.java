package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.AdminMapper;
import pers.zjc.sams.dao.StudentMapper;
import pers.zjc.sams.po.Admin;
import pers.zjc.sams.po.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudent(int id) {
        try {
            Student student = studentMapper.selectByPrimaryKey(id);
            if (student != null) {
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
