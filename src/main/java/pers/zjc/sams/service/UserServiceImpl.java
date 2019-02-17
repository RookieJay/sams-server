package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.StudentMapper;
import pers.zjc.sams.dao.TeacherMapper;
import pers.zjc.sams.dao.UserMapper;
import pers.zjc.sams.po.Student;
import pers.zjc.sams.po.Teacher;
import pers.zjc.sams.po.User;

import java.util.List;

/*
 * 1.@Service标识业务层的实现类
   2.使用@Transactional注解来将标识类中的所有方法都纳入Spring的事务管理
*/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public boolean addStudent(Student student) {
        try {
            if (studentMapper.insert(student) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean updateStuById(String id) {
        return false;
    }

    @Override
    public boolean deleteStuById(String id) {
        return false;
    }

    @Override
    public User validate(String account, String pwd) {
        try {
            User user = userMapper.validate(account, pwd);
            if (null != user) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        try {
            if (userMapper.add(user) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Student getMaxIdStu() {
        try {
            Student student = studentMapper.selectMaxIdStu();
            if (student != null) {
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        try {
            if (teacherMapper.insert(teacher) > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Teacher getMaxIdTeacher() {
        try {
            Teacher teacher = teacherMapper.selectMaxIdTeac();
            if (teacher != null) {
                return teacher;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean isExistedStu(User user) {
        try {
            User existedUser = userMapper.selectStuByAccount(user);
            if (existedUser != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean isExistedTeac(User user) {
        try {
            User existedUser = userMapper.selectTeacByAccount(user);
            if (existedUser !=  null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean isExisted(User user) {
        try {
            User existedUser = userMapper.selectByAccount(user);
            if (existedUser !=  null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean modifyStudent(Student student) {
        return studentMapper.updateByPrimaryKey(student) > 0;
    }
}
