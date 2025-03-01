# Betting Game API

This is a Betting Game API built with **Kotlin**, **Spring Boot**, and **Maven**.  
It includes functionality for managing players, placing bets, and handling transactions.

## Getting Started

### Prerequisites
Make sure you have the following installed:

- [JDK 21+](https://adoptium.net/)
- [Maven 3+](https://maven.apache.org/)
- An IDE such as [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Clone the Repository
```sh
git clone https://github.com/elpablo79/betting-game-api.git
cd betting-game-api
```
## Running the Application

### Run with Maven
You can start the Spring Boot application using:

```sh
mvn spring-boot:run
```

The application should now be running at:  
**http://localhost:8080**

## Running Tests

The project includes **unit tests** using **JUnit 5** and **MockK**.  
To run all tests:

```sh
mvn test
```

If you want to force Maven to update dependencies, use:

```sh
mvn clean test -U
```

## API Endpoints

| Method | Endpoint               | Description               |
|--------|------------------------|---------------------------|
| GET    | `/players/{username}`  | Get player details        |
| POST   | `/bets`                | Place a new bet           |
| GET    | `/transactions/{id}`    | Get transaction details   |

You can test the API using [Postman](https://www.postman.com/) or `curl`:

```sh
curl -X GET http://localhost:8080/players/markdonovan
```

## Tech Stack

- **Kotlin**
- **Spring Boot**
- **Maven**
- **JUnit 5 + MockK**
- **H2**

