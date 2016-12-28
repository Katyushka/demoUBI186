package com.demoUBI186;

import com.demoUBI186.domain.Role;
import com.demoUBI186.domain.User;
import com.demoUBI186.domain.UserCreateForm;
import com.demoUBI186.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by ekaterina on 22.12.2016.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoUBI186App.class)
public class UserServiseTest {

    @Autowired
    private UserService userService;


    @Test
    public void testGetUserById() throws Exception {
        User user = userService.getUserById(1L);
        assertThat(user.getRole(), is(Role.ROLE_ADMIN));
    }



    @Test
    public void testGetUserByName() throws Exception {
        User user = userService.getUserByLogin("user");
        assertThat(user.getId(), is(2L));
    }


    @Test
    public void testGetAllUsers() throws Exception {
        assertThat(userService.getAllUsers().size(), greaterThan(2));
    }

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setRole(Role.ROLE_USER);
        userService.save(user);
        User testUser = userService.getUserByLogin("test");
        assertThat(testUser.getRole(), is(Role.ROLE_USER));
        assertThat(testUser.getLogin(), is("test"));
    }

    @Test
    public void testSave1() throws Exception {
        User user = new User();
        user.setLogin("kate");
        user.setPassword("123qwe");
        user.setRole(Role.ROLE_ADMIN);
        userService.save(user);
        User testUser = userService.getUserByLogin("kate");
        assertThat(testUser.getRole(), is(Role.ROLE_ADMIN));
        assertThat(testUser.getLogin(), is("kate"));
    }

    @Test
    public void testDelete() throws Exception {
        User user = userService.getUserById(1L);
        assertNotNull(user);
        userService.delete(userService.getUserById(1L));
        assertNull(userService.getUserById(1L));
    }

    @Test
    public void testDelete1() throws Exception {
        User user = userService.getUserById(2L);
        assertNotNull(user);
        userService.delete(userService.getUserById(2L));
        assertNull(userService.getUserById(2L));
    }

}
