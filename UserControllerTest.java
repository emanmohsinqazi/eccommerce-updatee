package com.jtspringproject.JtSpringProject.controllerTests;
import com.jtspringproject.JtSpringProject.controller.UserController;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;
import com.jtspringproject.JtSpringProject.services.cartService;
import com.jtspringproject.JtSpringProject.utils.SessionData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
class UserControllerTest {
    @Mock
    private userService userService;
    @Mock
    private productService productService;
    @Mock
    private cartService cartService;
    @Mock
    private SessionData sessionData;
    @InjectMocks
    private UserController userController;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private Model model;
    @Mock
    private ModelAndView modelAndView;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testUserLoginSuccess() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        when(userService.checkLogin(anyString(), anyString())).thenReturn(user);
        ModelAndView result = userController.userlogin("testUser", "testPassword", model, response, session);
        assertEquals("uproduct", result.getViewName());
        verify(session, times(1)).setAttribute(eq("user"), any(User.class));
        verify(sessionData, times(1)).setCurrentUser(any(User.class));
        verify(model, times(1)).addAttribute(eq("user"), any(User.class));
    }
    @Test
    void testUserLoginFailure() {
        when(userService.checkLogin(anyString(), anyString())).thenReturn(new User());
        ModelAndView result = userController.userlogin("invalidUser", "invalidPassword", model, response, session);
        assertEquals("userLogin", result.getViewName());
        verify(model, times(1)).addAttribute(eq("hellojeeeeeeeee"), anyString());
    }
    @Test
    void testGetUserProfile() {
        User user = new User();
        user.setUsername("testUser");
        when(sessionData.getCurrentUser()).thenReturn(user);
        ModelAndView result = userController.getUserProfile();
        assertEquals("userProfile", result.getViewName());
        assertEquals(user, result.getModel().get("user"));
    }
}
