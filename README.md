# Quarkus Hibernate ORM PanacheRepository(interface) Implementation

This guide will help you set up a Quarkus application with Hibernate ORM, creating REST endpoints and using a MYSQL database.

## Prerequisites 
1. **Docker Desktop**: Install Docker Desktop to run the PostgreSQL database.
2. **IDE**: Use an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.
3. **Java Version**: jdk 21

## Step-by-Step Guide

### Step 1: Create a New Quarkus Project
1. Go to [Quarkus Code Generator](https://code.quarkus.io/).
2. Choose the necessary dependencies for your project:
   - **RESTEasy Classic**
   - **RESTEasy Classic Jackson**
   - **Quarkus Hibernate ORM Panache**
   - **Quarkus JDBC MySql**
   - **Quarkus Agroal**

### Step 2: Extract and Open the Project in Your IDE
- Extract the generated project files.
- Open the project in your preferred IDE.

### Step 3: Create REST Endpoints for CRUD Operations
Create REST endpoints for basic CRUD operations (Create, Read, Update, Delete).

### Step 4: Configure `application.properties`
Add the following properties to `src/main/resources/application.properties`:

```properties
quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3306/my_db_movie
quarkus.datasource.username=username
quarkus.datasource.password=password
quarkus.datasource.db-kind=mysql
quarkus.hibernate-orm.database.generation=drop-and-create.

```
### Step 5: Build a Docker Image for POSTGRES sql.
- Open a private command prompt for your project and run the following command
- docker run --name my_db_movie -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=my_db_movie -e MYSQL_USER=username -e MYSQL_PASSWORD=password -p 3306:3306 mysql:8.0 

### Step 6: Run the Application
- Open a private command prompt for your project and run the following command
- mvn quarkus:dev

### Step 7: Test the Endpoints on POSTMAN
- Use Postman or any other HTTP client tool to test your REST endpoints.

### Step 8: (Optional) Visualize Your Database Using DBeaver
- If you want to visualize your database schema and data, you can use DBeaver:
    Download and install DBeaver.
    Connect to your MySql database by providing the host (localhost), port (5432), username (username), password (password), and database name (my_db_movie).
  
### Tip - Make sure you have these installed on your PC and set the Path in Environment Variables
![image](https://github.com/user-attachments/assets/3f863f9a-4431-4c94-8dde-dacd99009d12)
