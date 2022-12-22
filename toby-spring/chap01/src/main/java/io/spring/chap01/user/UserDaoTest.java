package io.spring.chap01.user;

import io.spring.chap01.user.dao.NConnectionMaker;
import io.spring.chap01.user.dao.UserDao;
import io.spring.chap01.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new UserDao(new NConnectionMaker());

        User user = new User();
        user.setId("felix");
        user.setName("용복");
        user.setPassword("felixlee");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
