package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.HibernateConfiguration;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@PropertySource("classpath:application.properties")
public class HibernateConfigurationTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private HibernateTransactionManager transactionManager;

    @Value("${db.driver}")
    private String DRIVER;

    @Value("${db.password}")
    private String PASSWORD;

    @Value("${db.url}")
    private String URL;

    @Value("${db.username}")
    private String USERNAME;

    @Value("${hibernate.dialect}")
    private String DIALECT;

    @Value("${hibernate.show_sql}")
    private String SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String PACKAGES_TO_SCAN;

    @Test
    public void testDataSource() {
        assertNotNull(dataSource);
        assertEquals(DriverManagerDataSource.class, dataSource.getClass());
        assertEquals(URL, ((DriverManagerDataSource) dataSource).getUrl());
        assertEquals(USERNAME, ((DriverManagerDataSource) dataSource).getUsername());
        assertEquals(PASSWORD, ((DriverManagerDataSource) dataSource).getPassword());
    }

    @Test
    public void testSessionFactory() {
        assertNotNull(sessionFactory);
        Properties hibernateProperties = sessionFactory.getHibernateProperties();
        assertNotNull(hibernateProperties);
        assertEquals(DIALECT, hibernateProperties.getProperty("hibernate.dialect"));
        assertEquals(SHOW_SQL, hibernateProperties.getProperty("hibernate.show_sql"));
        assertEquals(HBM2DDL_AUTO, hibernateProperties.getProperty("hibernate.hbm2ddl.auto"));
    }

    @Test
    public void testTransactionManager() {
        assertNotNull(transactionManager);
        assertEquals(sessionFactory.getObject(), transactionManager.getSessionFactory());
    }
}
