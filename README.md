
# Restful Api Project

A simple REST service that returns user information from the Github API.

## Requirements

For building and running the application you need:

- Java JDK 11 or above
- Maven 3.x 
- MySQL 8.x
- Git


## Running the application locally

 Configure MySQL database:

```bash
  Default MySQL instance credentials:
  username:root
  password:root 
 
  Create database:
  create database rest_api_project;
```
Clone the repository:

```bash
  git clone https://github.com/MatPotGh/restful-api-project.git
```

Go to the project directory:

```bash
  cd my-project
```

Build the project:

```bash
  mvn clean install
```

Run the application:
```bash
 java -jar target/restful-api-project-0.0.1-SNAPSHOT.jar
 or
 mvn spring-boot:run 
```


## API Reference

#### Get User information from Github API

```http
  GET /users/{login}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `login` | `string` | **Required**. Login of user to fetch |


#### Test the API



```http
http://localhost:8080/swagger-ui/#/

```



