package com.jtspringproject.JtSpringProject.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.jtspringproject.JtSpringProject.models.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductDaoTest {

    @Autowired
    private productDao productDao;

    @Before
    public void setUp() {
        // Setup any prerequisite data or configurations here
    }

    @After
    public void tearDown() {
        // Clean up any data after each test
    }

    @Test
    public void testGetProducts() {
        List<Product> productList = productDao.getProducts();
        assertNotNull(productList);
        // Add more assertions based on your requirements
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(50);

        Product addedProduct = productDao.addProduct(product);

        assertNotNull(addedProduct);
        assertNotNull(addedProduct.getId());
        assertEquals("TestProduct", addedProduct.getName());
        // Add more assertions based on your requirements
    }

    @Test
    public void testGetProduct() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(50);

        productDao.addProduct(product);

        Product retrievedProduct = productDao.getProduct(product.getId());
        assertNotNull(retrievedProduct);
        assertEquals("TestProduct", retrievedProduct.getName());
        // Add more assertions based on your requirements
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(50);

        productDao.addProduct(product);

        // Modify the product
        product.setPrice(60);
        Product updatedProduct = productDao.updateProduct(product);

        assertNotNull(updatedProduct);
        assertEquals(60, updatedProduct.getPrice(), 0.01);
        // Add more assertions based on your requirements
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(50);

        productDao.addProduct(product);

        assertTrue(productDao.deletProduct(product.getId()));
    }
}
