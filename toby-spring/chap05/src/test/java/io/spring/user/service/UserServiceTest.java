package io.spring.user.service;

import io.spring.user.dao.UserDao;
import io.spring.user.domain.Level;
import io.spring.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static io.spring.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static io.spring.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
          new User("felix", "이용복", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0),
          new User("chan", "방찬", "p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
          new User("chris", "찬방", "p3", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1),
          new User("poppy", "파피", "p4", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD),
          new User("bear", "테디", "p5", Level.GOLD, 100, Integer.MAX_VALUE)
        );
    }

    @Test
    public void upgradeLevels() {
        userDao.deleteAll();
        for (User user: users) {
            userDao.add(user);
        }

        userService.upgradeLevels();

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);
    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4); // GOLD 레벨
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
        assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());
        if (upgraded) {
            assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
        } else {
            assertThat(userUpdate.getLevel(), is(user.getLevel()));
        }
    }
}