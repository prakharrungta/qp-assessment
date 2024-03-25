# qp-assessment
A simple Grocery app.

## Features
- Allows admin to add, view, update, and remove grocery items.
- Enables users to view available grocery items and place orders.

## Technologies Used
- Java 17
- Spring Boot 3.2
- Spring Security 6.1
- Spring Data JPA
- MySQL Database

## Setup Instructions
1. Clone the repository from master branch.
2. Before running the app, make sure you have Java 17 installed.
3. Run the app from IDE, make sure Build path is set to JDK 17.
4. Access the application at http://localhost:8080 through the endpoints mentioned below. You can use the postman collection in project directory - it has all the required sample requests.

## Security
Two users are already setup: 
1. username: user0 <br>
   password: pass <br>
   role: user
2. username: admin0 <br>
   password: pass <br>
   role: admin <br>

## API Endpoints
- Admin Endpoints:
  - `GET /api/users`: Create new user.
  - `POST /api/inventory`: Add new grocery items.
  - `GET /api/inventory`: View all existing grocery items.
  - `DELETE /api/inventory/{itemId}`: Remove grocery items.
  - `PUT /api/inventory/`: Update details of existing grocery items and manage inventory levels by providing the updated value in request body.
  - `GET /api/orders`: View all orders.

- User Endpoints:
  - `GET /api/inventory/available`: View available grocery items.
  - `POST /api/orders`: Book multiple grocery items in a single order.
