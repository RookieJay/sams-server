package pers.zjc.sams.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.zjc.sams.po.Student;
import pers.zjc.sams.po.User;
import pers.zjc.sams.po.UserExample;

public interface UserMapper {

    List<User> getAllUsers();

    User validate(@Param("account") String account, @Param("pwd") String pwd);

    int add(User user);

    User selectStuByAccount(User user);

    User selectTeacByAccount(User user);

    User selectByAccount(User user);
}