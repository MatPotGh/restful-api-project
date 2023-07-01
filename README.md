
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
Default MySQL instance credentials to be configured for application:
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

### Get User information

```http
GET /users/{login}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `login` | `string` | **Required**. Login of user to fetch |


### Test API




http://localhost:8080/swagger-ui/#/



### Rest API Success Responses

 ```bash
GET /users/{login}

HTTP/1.1 200
Content-Type: application/json

{
  "id": 32551,
  "login": "davglass",
  "name": "Dav Glass",
  "type": "User",
  "calculations": 1.36125,
  "avatar_url": "https://avatars.githubusercontent.com/u/32551?v=4",
  "created_at": "2008-11-04T04:22:29Z"
}
```
### Rest API Error Responses

 ```bash
GET /users/{login}

#login:'testUser'

HTTP/1.1 404
Content-Type: application/json

{
  "message": "Github user with given login do not exist.",
  "httpStatus": 404
}
```

 ```bash
GET /users/{login}

#login:'itson'

HTTP/1.1 406
Content-Type: application/json

{
  "message": "Followers number for the user is equal to 0. Calculations can't be proceeded.",
  "httpStatus": 406
}
```
