package pers.zjc.sams.service;

import pers.zjc.sams.po.Student;
import pers.zjc.sams.po.Teacher;
import pers.zjc.sams.po.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    boolean addStudent(Student student);

    boolean updateStuById(int id);

    boolean deleteStuById(String id);

    User validate(String account, String pwd);

    boolean addUser(User user);

    Student getMaxIdStu();

    boolean addTeacher(Teacher teacher);

    Teacher getMaxIdTeacher();

    boolean isExistedStu(User user);

    boolean isExistedTeac(User user);

    boolean isExisted(User user);

    boolean modifyStudent(Student student);

    boolean modifyTeacher(Teacher teacher);

    Student getStudent(Student student);

    Teacher getTeacher(Teacher teacher);

    boolean isAccountExisted(User user);

    boolean isPwdCorrect(User user);

    boolean modifyPwd(User user);

    boolean updateUser(User user);
}
