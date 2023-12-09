package com.jtspringproject.JtSpringProject.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.Cart;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartDaoTest {

    @Autowired
    private cartDao cartDao;

    @Before
    public void setUp() {
        // Setup any prerequisite data or configurations here
    }

    @After
    public void tearDown() {
        // Clean up any data after each test
    }

    @Test
    public void testAddCart() {
        Cart cart = new Cart();
        // Set necessary properties for the cart

        Cart addedCart = cartDao.addCart(cart);

        assertNotNull(addedCart);
        assertNotNull(addedCart.getId());
        // Add more assertions based on your requirements
    }

    @Test
    public void testGetCarts() {
        List<Cart> cartList = cartDao.getCarts();
        assertNotNull(cartList);
        // Add more assertions based on your requirements
    }

    @Test
    public void testUpdateCart() {
        Cart cart = new Cart();
        // Set necessary properties for the cart

        cartDao.addCart(cart);

        // Modify the cart
        // Update necessary properties for the cart

        cartDao.updateCart(cart);

        // Fetch the cart again and assert the changes
        Cart updatedCart = cartDao.getCart(cart.getId());
        assertNotNull(updatedCart);
        // Add more assertions based on your requirements
    }

    @Test
    public void testDeleteCart() {
        Cart cart = new Cart();
        // Set necessary properties for the cart

        cartDao.addCart(cart);

        cartDao.deleteCart(cart);

        // Fetch the cart again and assert it does not exist
        Cart deletedCart = cartDao.getCart(cart.getId());
        assertNull(deletedCart);
    }

    @Test
    public void testGetCart() {
        Cart cart = new Cart();
        // Set necessary properties for the cart

        cartDao.addCart(cart);

        Cart retrievedCart = cartDao.getCart(cart.getId());
        assertNotNull(retrievedCart);
        // Add more assertions based on your requirements
    }
}
