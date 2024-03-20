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
- In-mem relational H2 Database

## Setup Instructions
1. Clone the repository.
2. Before running the app, make sure you have Java 17 installed.
3. Run the app from IDE, make sure Build path is set to JDK 17.
4. Access the application through the postman collection in project directory.

## Security
Two users are already setup: 
1. username: user0
   password: pass
   role: user
2. username: admin0
   password: pass
   role: admin
For testing, you can create additional users by updating the userDetailsService bean created in SecurityConfig class in config package.

## API Endpoints
- Admin Endpoints:
  - `POST /api/inventory`: Add new grocery items.
  - `GET /api/inventory`: View all existing grocery items.
  - `DELETE /api/inventory/{itemId}`: Remove grocery items.
  - `PUT /api/inventory/`: Update details of existing grocery items and manage inventory levels by providing the updated value in request body.

- User Endpoints:
  - `GET /api/inventory/available`: View available grocery items.
  - `POST /api/order`: Book multiple grocery items in a single order.
  
## Upcoming changes
- Dockerization of app and usage of MySQL DB instead of H2.
