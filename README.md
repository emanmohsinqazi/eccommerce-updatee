## Old Version
- https://github.com/jaygajera17/E-commerce-project-springBoot/tree/main
- issue: everytime connection open close for access database 

## what new
For connecting database:

Make a database.
Add a user to that database with all permissions possible.

Go back to the project and open the applications.properties file. Change the username and password to the database's login credentials.

To fix errors:
In the applications.properties file, there is a typo. At one point 'econjava' is written instead of 'ecommjava'.
To check if the database has been connected:
You will find some new tables in the database after you run the code. At this point, the database has been connected but there is still a bug in the program. Somewhere a wrong function has been used. 'Execute' instead of 'executeQuery'

  
## Quickstart

1. Clone the repository

2. Make sure you are in the `JtProject` directory
3. Configure the database connection in `application.properties` file (check the [Database](#database) section below for more info)
4. Run the project (by running the `main` method in `JtSpringProjectApplication.java`)
5. Open http://localhost:8080/ in your browser!
   * If you ran the [`basedata.sql`](https://github.com/jaygajera17/E-commerce-project-springBoot/blob/master2/JtProject/basedata.sql)script on the database, you can log in with the following credentials as admin; otherwise you'll have to manually create an admin user in the database:
     * Username: `admin`
     * Password: `123`
   * Log in as a normal user:
     * Username: `lisa`
     * Password: `765`

### Database

MySQL or MariaDB can be used as the database for this project. The database connection can be configured in the `application.properties` file, with the appropriate values for the following properties:

```properties
    db.url=jdbc:mysql://[ip address of db]:[port of db]/ecommjava?createDatabaseIfNotExist=true
    db.username=[username]
    db.password=[password, if any]
```

Having done that, you must create some base data in the database. You can do that by running the `basedata.sql` script on the database. Check out Google for how to do that, because it depends on what tool you are using to access said database. 

### Web Directories

The views are located in `src/main/webapp/views`, but for some reason, Spring Boot doesn't recognize that directory. To remedy this, you must change the working directory of the project in your IDE. If you're using IntelliJ IDEA, follow these steps:

1. Click on the "Edit Configurations..." button in the top right corner of the IDE
2. Click on the `JtSpringProjectApplication` configuration
3. Change the "Working directory" option (if not present, click on "Modify Options" and select from the list) to the `$MODULE_WORKING_DIR$` macro
4. Click "Apply" and "OK"

When you run the project, the views should be found by Spring Boot and you should see a login page in http://localhost:8080/ (if not logged in previously)!

# Workflow
- ![image](https://github.com/jaygajera17/E-commerce-project-springBoot/assets/81226571/69951cb7-65e2-4225-8681-2542859aaec6)
### Controller
- control the endpoint and also send data to view( we use ModelAndView method)
- ``` @GetMapping("login")
	public String adminlogin() {
		
		return "adminlogin";
	}```
- whenever /login url is hit , src->main->webapp-> adminlogin.jsp file execute
### Models
- represent data as entity and relationship among them.

### View
- receive data from controller and show with frontend.

## Endpoints
- http://localhost:8080/
- http://localhost:8080/register
- http://localhost:8080/admin/products
- http://localhost:8080/admin/customers
- http://localhost:8080/admin/categories
- http://localhost:8080/admin/Dashboard


## Spring Boot

For any information about Spring Boot, here are some useful links!

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

## Preview

![image](https://github.com/jaygajera17/E-commerce-project-springBoot/assets/81226571/02a04d3c-1fc9-418c-b231-639f6525d07e)
![image](https://github.com/jaygajera17/E-commerce-project-springBoot/assets/81226571/24c4451b-43a6-4c23-a78a-786eab4303b0)
![image](https://github.com/jaygajera17/E-commerce-project-springBoot/assets/81226571/93c1baeb-326c-450f-867e-a883900a6644)
