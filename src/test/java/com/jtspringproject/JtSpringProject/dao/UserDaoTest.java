package com.jtspringproject.JtSpringProject.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

    @Autowired
    private userDao userDao;

    @Before
    public void setUp() {
        // Setup any prerequisite data or configurations here
    }

    @After
    public void tearDown() {
        // Clean up any data after each test
    }

    @Test
    public void testGetAllUser() {
        List<User> userList = userDao.getAllUser();
        assertNotNull(userList);
        // Add more assertions based on your requirements
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        User savedUser = userDao.saveUser(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals("testuser", savedUser.getUsername());
        // Add more assertions based on your requirements
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        userDao.saveUser(user);

        User retrievedUser = userDao.getUser("testuser", "testpassword");
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getUsername());
        // Add more assertions based on your requirements
    }

    @Test
    public void testUserExists() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        userDao.saveUser(user);

        assertTrue(userDao.userExists("testuser"));
    }
}
