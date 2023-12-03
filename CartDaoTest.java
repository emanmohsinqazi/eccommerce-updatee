package com.jtspringproject.JtSpringProject.daoTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import com.jtspringproject.JtSpringProject.dao.cartDao;
import com.jtspringproject.JtSpringProject.models.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
@SpringBootTest
@ContextConfiguration(classes = {cartDao.class})
public class CartDaoTest {

    @Autowired
    private cartDao cartDao;

    @MockBean
    private SessionFactory sessionFactory;

    @MockBean
    private Session session;

    @BeforeEach
    public void setUp() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void testAddCart() {
        Cart cart = new Cart();
        when(session.save(cart)).thenReturn(1);
        Cart result = cartDao.addCart(cart);
        assertNotNull(result);
        assertEquals(cart, result);
        verify(session).save(cart);
    }
     
      @Test
    public void testGetCartByUserId() {
        int userid = 1;
        Query<Cart> query = mock(Query.class);
        when(session.createQuery("from cart where customer-id = userid", Cart.class)).thenReturn(query);
        when(query.setParameter("userid", userid)).thenReturn(query);
        Cart expectedCart = new Cart();
        when(query.uniqueResult()).thenReturn(expectedCart);
        Cart result = cartDao.getCartByUserId(userid);
        assertNotNull(result);
        assertEquals(expectedCart, result);
        verify(query).setParameter("userid", userid);
        verify(query).uniqueResult();
    }

    @Test
    public void testUpdateCart() {
        Cart cart = new Cart();
        cartDao.updateCart(cart);
        verify(session).update(cart);
    }

    
    @Test
    public void testGetCarts() {
        Query<Cart> query = mock(Query.class);
        when(session.createQuery("from cart", Cart.class)).thenReturn(query);
        List<Cart> expectedCarts = new ArrayList<>();
        when(query.list()).thenReturn(expectedCarts);
        List<Cart> result = cartDao.getCarts();
        assertNotNull(result);
        assertEquals(expectedCarts, result);
        verify(session).createQuery("from cart", Cart.class);
    }

    @Test
    public void testDeleteCart() {
        Cart cart = new Cart();
        cartDao.deleteCart(cart);
        verify(session).delete(cart);
    }

}
