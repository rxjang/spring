package io.spring.chap01.user.dao;


import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

public class DaoFactory {

    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost/springbook?useSSL=false&allowPublicKeyRetrieval=true");
        dataSource.setUsername("spring");
        dataSource.setPassword("Springbook@123");
        return dataSource;
    }

}
