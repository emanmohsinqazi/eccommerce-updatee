package com.jtspringproject.JtSpringProject.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {userService.class})
@ExtendWith(SpringExtension.class)
class userServiceTest {
    @MockBean
    private userDao userDao;

    @Autowired
    private userService userService;

    /**
    {@link userService#getUsers()}
     */
    @Test
    void testGetUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(userDao.getAllUser()).thenReturn(userList);
        List<User> actualUsers = userService.getUsers();
        verify(userDao).getAllUser();
        assertTrue(actualUsers.isEmpty());
        assertSame(userList, actualUsers);
    }

    /**
    {@link userService#addUser(User)}
     */
    @Test
    void testAddUser() {
        User user = new User();
        user.setAddress("42 Main St");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userDao.saveUser(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setAddress("42 Main St");
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUsername("janedoe");
        User actualAddUserResult = userService.addUser(user2);
        verify(userDao).saveUser(Mockito.<User>any());
        assertSame(user, actualAddUserResult);
    }

    /**
    {@link userService#checkLogin(String, String)}
     */
    @Test
    void testCheckLogin() {
        User user = new User();
        user.setAddress("42 Main St");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUsername("janedoe");
        when(userDao.getUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        User actualCheckLoginResult = userService.checkLogin("janedoe", "iloveyou");
        verify(userDao).getUser(Mockito.<String>any(), Mockito.<String>any());
        assertSame(user, actualCheckLoginResult);
    }

    /**
    {@link userService#checkUserExists(String)}
     */
    @Test
    void testCheckUserExists() {
        when(userDao.userExists(Mockito.<String>any())).thenReturn(true);
        boolean actualCheckUserExistsResult = userService.checkUserExists("janedoe");
        verify(userDao).userExists(Mockito.<String>any());
        assertTrue(actualCheckUserExistsResult);
    }

    /**
    {@link userService#checkUserExists(String)}
     */
    @Test
    void testCheckUserExists2() {
        when(userDao.userExists(Mockito.<String>any())).thenReturn(false);
        boolean actualCheckUserExistsResult = userService.checkUserExists("janedoe");
        verify(userDao).userExists(Mockito.<String>any());
        assertFalse(actualCheckUserExistsResult);
    }

    /**
    {@link userService#deleteUser(int)}
     */
    @Test
    void testDeleteUser() {
        doNothing().when(userDao).deleteUser(anyInt());
        userService.deleteUser(1);
        verify(userDao).deleteUser(anyInt());
    }
}
