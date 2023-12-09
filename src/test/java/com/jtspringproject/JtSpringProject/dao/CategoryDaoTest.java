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

import com.jtspringproject.JtSpringProject.models.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryDaoTest {

    @Autowired
    private categoryDao categoryDao;

    @Before
    public void setUp() {
        // Setup any prerequisite data or configurations here
    }

    @After
    public void tearDown() {
        // Clean up any data after each test
    }

    @Test
    public void testAddCategory() {
        Category category = categoryDao.addCategory("TestCategory");
        assertNotNull(category);
        assertNotNull(category.getId());
        assertEquals("TestCategory", category.getName());
        // Add more assertions based on your requirements
    }

    @Test
    public void testGetCategories() {
        List<Category> categoryList = categoryDao.getCategories();
        assertNotNull(categoryList);
        // Add more assertions based on your requirements
    }

    @Test
    public void testDeleteCategory() {
        Category category = categoryDao.addCategory("TestCategory");

        assertTrue(categoryDao.deletCategory(category.getId()));
    }

    @Test
    public void testUpdateCategory() {
        Category category = categoryDao.addCategory("TestCategory");

        // Modify the category
        Category updatedCategory = categoryDao.updateCategory(category.getId(), "UpdatedCategory");

        assertNotNull(updatedCategory);
        assertEquals("UpdatedCategory", updatedCategory.getName());
        // Add more assertions based on your requirements
    }

    @Test
    public void testGetCategory() {
        Category category = categoryDao.addCategory("TestCategory");

        Category retrievedCategory = categoryDao.getCategory(category.getId());
        assertNotNull(retrievedCategory);
        assertEquals("TestCategory", retrievedCategory.getName());
        // Add more assertions based on your requirements
    }
}
