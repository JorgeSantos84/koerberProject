# TestProject

## Description

This is a simple Spring Boot application for Koerber using JAVA17, JPA, HIBERNATE, PostgreSQL, Lombok, Flyway for database migration, 
JUnit and mockito for testing, and Swagger for API documentation. 
The project is containerized with Docker to ensure easy setup and deployment.

## Prerequisites

Before running the project, ensure you have the following installed:

- [Docker](https://www.docker.com/)
- [Git](https://git-scm.com/)

## Getting Started

### 1. Clone the repository

```sh
git clone https://github.com/JorgeSantos84/koerberProject.git
cd KoerberProject
```

### 2. Build and Run the Project with Docker

Run the following command to build and start the application:

```sh
docker-compose up --build
```

This will start both the PostgreSQL database and the Spring Boot application.

### 3. Access the Application

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- PostgreSQL DB: `localhost:5432` (Username: `koerber`, Password: `testpassword`)

## Database Management

### Checking the Running Containers

```sh
docker ps
```

### Access the PostgreSQL Database with DBeaver (example)

- Host: `localhost`
- Port: `5432`
- Database: `koerberDatabase`
- Username: `koerber`
- Password: `testpassword`
- 

## Stopping the Application

To stop and remove the containers:

```sh
docker-compose down -v
```

