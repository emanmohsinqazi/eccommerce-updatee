package com.jtspringproject.JtSpringProject.daoTests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import com.jtspringproject.JtSpringProject.dao.categoryDao;
import com.jtspringproject.JtSpringProject.models.Category;
@SpringBootTest
@Transactional
@Rollback(true)
class CategoryDaoTest {

    @Autowired
    private categoryDao categoryDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testAddCategory() {
        Category category = categoryDao.addCategory("evaluationcategory");
        assertNotNull(category);
        assertEquals("evaluationcategory", category.getName());
    }
    
       @Test
    void testGetCategory() {
        Category testCategory = categoryDao.addCategory("evaluationcategory");
        Category retrievedCategory = categoryDao.getCategory(testCategory.getId());
        assertNotNull(retrievedCategory);
        assertEquals("evaluationcategory", retrievedCategory.getName());
    }

    @Test
    void testGetCategories() {
        List<Category> initialCategories = categoryDao.getCategories();
        Category testCategory = categoryDao.addCategory("evaluationcategory");
        List<Category> categoriesAfterAddition = categoryDao.getCategories();
        assertEquals(initialCategories.size() + 1, categoriesAfterAddition.size());
        assertTrue(categoriesAfterAddition.contains(testCategory));
    }

    @Test
    void testUpdateCategory() {
        Category testCategory = categoryDao.addCategory("evaluationcategory");
        Category updatedCategory = categoryDao.updateCategory(testCategory.getId(), "updated");
        assertEquals("updated", updatedCategory.getName());
    }

    @Test
    void testDeleteCategory() {
        Category testCategory = categoryDao.addCategory("evaluationcategory");
        assertTrue(categoryDao.deletCategory(testCategory.getId()));
        assertNull(sessionFactory.getCurrentSession().get(Category.class, testCategory.getId()));
    }

}
