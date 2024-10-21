# Microservices Project

This project consists of three simple Spring Boot microservices that communicate with each other: `OrderService`, `UserService`, and `ProductService`. Each microservice is set up to handle basic CRUD operations for their respective entities (`Order`, `User`, and `Product`) and communicates with other services using REST.

## Table of Contents
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Setup and Run](#setup-and-run)
- [Microservices Overview](#microservices-overview)
- [API Endpoints](#api-endpoints)
- [Testing Using Postman](#testing-using-postman)

## Technologies
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory)
- RestTemplate
- Maven

## Project Structure
The project is organized into the following modules:
1. `UserService` - Handles user-related operations.
2. `ProductService` - Manages product-related operations.
3. `OrderService` - Handles order-related operations and communicates with `UserService` and `ProductService` to fetch user and product details.

## Setup and Run
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/microservices-project.git
   cd microservices-project

## Building and Running the Microservices Project

### Prerequisites
- Maven installed

### Building the Project
1. Open a terminal.
2. Navigate to the project directory.
3. Run the following command:
```bash
mvn clean install
```

3.Run the services: Each service should be started separately:

Open a terminal for each service (UserService, ProductService, OrderService) and run:
```bash
mvn spring-boot:run
```

4. Make sure each service runs on a different port (configured in application.properties).
Access H2 Console:

- **URL:** `http://localhost:<port>/h2-console` (replace `<port>` with the actual port for each service).
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (leave it empty)

  ## Microservices Overview

1. **UserService**  
   Manages users with the following fields:
   - `id`: Auto-generated ID.
   - `name`: User's name.
   - `email`: User's email.

2. **ProductService**  
   Manages products with the following fields:
   - `id`: Auto-generated ID.
   - `name`: Product name.
   - `price`: Product price.

3. **OrderService**  
   Handles orders and communicates with `UserService` and `ProductService` to retrieve user and product details.
   - `id`: Auto-generated ID.
   - `userId`: The ID of the user placing the order.
   - `productId`: The ID of the product being ordered.
   - `quantity`: The quantity of the product.

## API Endpoints

### UserService
- **Create User:** `POST /users`
- **Get User by ID:** `GET /users/{id}`
- **Update User:** `PUT /users/{id}`
- **Delete User:** `DELETE /users/{id}`

### ProductService
- **Create Product:** `POST /products`
- **Get Product by ID:** `GET /products/{id}`
- **Update Product:** `PUT /products/{id}`
- **Delete Product:** `DELETE /products/{id}`

### OrderService
- **Create Order:** `POST /orders`
- **Get Order by ID:** `GET /orders/{id}`
- **Update Order:** `PUT /orders/{id}`
- **Delete Order:** `DELETE /orders/{id}`
## Testing Using Postman

You can use Postman to test the microservices. Below are the example request bodies:

1. **Create a User**  
   - **Endpoint:** `POST /users`  
   - **Body:**  
     ```json
     {
         "name": "John Doe",
         "email": "john.doe@example.com"
     }
     ```

2. **Create a Product**  
   - **Endpoint:** `POST /products`  
   - **Body:**  
     ```json
     {
         "name": "Lipstick",
         "price": 19.99
     }
     ```

3. **Create an Order**  
   - **Endpoint:** `POST /orders`  
   - **Body:**  
     ```json
     {
         "userId": 1,
         "productId": 101,
         "quantity": 2
     }
     ```

## H2 Database Configuration

Make sure the following configurations are set in the `application.properties` file for each service:

```properties
# H2 Database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Hibernate configuration
spring.jpa.hibernate.ddl-auto=update
```
To access the H2 database console:

1. Open the browser and go to `http://localhost:<port>/h2-console`.
2. Use the JDBC URL `jdbc:h2:mem:testdb`, Username `sa`, and leave the Password field empty.

