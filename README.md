Ancient History Portal Apllication - Semi RESTful/Thymeleaf WEB Application.
Getting Started
Please make sure you follow the steps, step by step!

Prerequisites

JDK 21 or 17
Apache Maven 4.0.0+
Depending on the features you want to use, you may need some third-party software, such as 
or MySQL Workbench (free) for data modeling, SQL development, and comprehensive administration for the system data.

Installation
In order to run Ancient History App you need to:

DOWNLOAD the repo.

Set up environment variables ${MYYSQL_PORT}, ${MYSQL_USER}.. etc...
spring.datasource.url: jdbc:mysql://localhost:${MYSQL_PORT}/
spring.datasource.username: ${MYSQL_USER}
spring.datasource.password: ${MYSQL_PASSWORD}
admin.username: ${ADMIN_USERNAME}
admin.password: ${ADMIN_PASSWORD}
Enjoy the application!)

About The Project

The Ancient History App  project is a Spring Boot-based application designed to streamline history article and education services. 
It offers users the convenience of registering, managing their articles, and scheduling appointments for various education and article author services.

Built With
Java
JavaScript
Bootstrap
HTML
CSS
MySQL
SpringSecurity
SpringBoot
SpringDataJPA

Front End
Thymeleaf View Engine Thymeleaf is utilized in this project for rendering dynamic HTML content by binding data from the backend to the frontend templates.

 Exception handling
 Custom pages for the different types of exceptions
 Data validity checks
 Custom messages if some of the data is not valid
Back End
The project incorporates a bit of Aspect-Oriented Programming (AOP) to modularize cross-cutting concerns.

Scheduled Tasks :
Removing Deprecate and old articles: Articles not edited or deleted from admin considered inactive and are automatically removed from the system.

Handling Old Log in system: Scheduled task todelete old logs within the system.

Send shceduled emails upon registration and on every 12 hours.


Internalization/i18n
Bulgarian
English

Events Handling
User Registration Activation Email: An event triggers the sending of an activation email upon user registration.
Password Reset Email: Another event allows users to reset their passwords if they forget them.
User Email Notification: Users will receive email notifications indicating whether the appointment has been accepted or rejected.

Mapping
ModelMaper - In this project, I employ ModelMaper to simplify the mapping between different types of objects, particularly between entity models and DTOs (Data Transfer Objects). MapStruct is a convenient and efficient code generation library that automates the process of mapping by generating mapping code during the compilation phase.

JWT Authentication
Upon successful authentication, a JWT is generated and stored as an HTTP-only secure cookie in the browser.

 Expiration: The token expires after 24 hours.

 Verify the origin with reCAPTCHA Google solutions

Custom Exceptions
Custom exceptions are used to handle specific errors or exceptional cases in the application, improving error handling and providing meaningful feedback to users or clients.

Interceptor for Banned Users
An interceptor is implemented to handle access control for banned users within the application. When a registerDTO attempts to access restricted resources or endpoints, this interceptor checks whether the registerDTO has been banned. If the registerDTO is identified as banned, access to the requested resource is denied.

Exception Handling
Custom exception handling is implemented within the application to manage and respond to exceptional situations or errors that occur during the runtime of the system. These exceptions are designed to capture specific scenarios and provide appropriate responses to users or client applications.

Testing
 Junit
 Mockito
 
 Integration tests
The project includes JUnit and Mockito for unit testing and integration tests with inmemory database, ensuring the reliability and correctness of various components within the application. This structure provides a clear and organized overview of both the frontend and backend aspects of the project, detailing the technologies, functionalities, and methodologies utilized. Adjust the descriptions as needed to accurately represent the specifics of your project.

 Usage
Here you can find all the information about the roles in the applicaтion and what they can do.

Admin
Only the users with role Admin can delete others users articles !
Moderator
This role has the authority to approve/decline the appointments requested by users.

User
The role of the User is basically a permission to see the madet articles by others, wich are not deleted!






