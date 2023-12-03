package com.jtspringproject.JtSpringProject.controllerTests;
import com.jtspringproject.JtSpringProject.controller.AdminController;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.categoryService;
import com.jtspringproject.JtSpringProject.services.productService;
import com.jtspringproject.JtSpringProject.services.userService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
    @Mock
    private userService userService;
    @Mock
    private categoryService categoryService;
    @Mock
    private productService productService;
    @InjectMocks
    private AdminController adminController;
    private List<Category> mockCategories;
    private List<Product> mockProducts;
    @BeforeEach
    public void setup() {
        mockCategories = new ArrayList<>();
        mockCategories.add(new Category(1, "Cat1"));
        mockCategories.add(new Category(2, "Cat2"));
        mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1, "Prod1", mockCategories.get(0), 10, 100, 5, "Desc1", "Img1"));
        mockProducts.add(new Product(2, "Prod2", mockCategories.get(1), 20, 150, 8, "Descr2", "Img2"));
    }
    @Test
    public void testIndexWhenUsernameIsEmpty() {
        Model model = mock(Model.class);
        when(model.addAttribute("username", "")).thenReturn(model);
        String result = adminController.index(model);
        assertEquals("userLogin", result);
        verify(model, times(1)).addAttribute("username", "");
    }
    @Test
    public void testIndexWhenUsernameIsNotEmpty() {
        Model model = mock(Model.class);
        adminController.setUsernameforclass("admin"); 
        when(model.addAttribute("username", "admin")).thenReturn(model);
        String result = adminController.index(model);
        assertEquals("index", result);
        verify(model, times(1)).addAttribute("username", "admin");
    }
    @Test
    public void testAdminLoginWithValidUser() {
        when(userService.checkLogin("admin", "password")).thenReturn(new User("admin", "ADMIN'sHERE"));
        ModelAndView modelAndView = adminController.adminlogin("admin", "password");
        assertEquals("adminHome", modelAndView.getViewName());
        assertEquals(1, modelAndView.getModel().size());
        assertTrue(modelAndView.getModel().containsKey("admin"));
        assertEquals("admin", ((User) modelAndView.getModel().get("admin")).getUsername());
    }




}
