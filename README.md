# Updated Movie-Management-App

Movie Management Application This is a full-stack web application built with Angular 16+ for the frontend and Spring Boot for the backend. The application allows admins to manage movies fetched from the OMDB API and regular users to view and interact with the movie list.

# Features
# Admin Dashboard
Login Functionality: Secure login for admin users.

Movie Search: Search for movies using the OMDB API.

Add/Remove Movies: Admins can add or remove movies from the application database.

Pagination: Movies are displayed with pagination for better usability (Bonus Feature).

Regular User Dashboard
Login Functionality: Secure login for regular users.

View Movie List: Users can view the full list of movies added by the admin.

Movie Details: Users can view detailed information about each movie.

# Technologies Used
# Frontend

Angular 16+: A powerful frontend framework for building dynamic web applications.

Angular Material: For a clean and responsive UI design.

RxJS: For handling asynchronous operations.

HTML/CSS/TypeScript: Core technologies for building the frontend.

# Backend
Spring Boot: A robust backend framework for building RESTful APIs.

Java 8+: The primary programming language for the backend.

Spring Security: For user authentication and authorization.

Spring Data JPA: For database operations.

OMDB API: For fetching movie data.

# Database
MySQL: A relational database for storing user and movie data.

# Other Tools
Git: For version control.

Postman: For API testing.

Maven: For dependency management in the backend.

# Setup and Installation
Prerequisites
Node.js: Install Node.js (v16+ recommended) for Angular development.

Java 8+: Install JDK 8 or higher for Spring Boot.

MySQL/PostgreSQL: Install and set up a database.

Angular CLI: Install Angular CLI globally using npm install -g @angular/cli.

# Backend Setup
Clone the repository:

git clone https://github.com/eslamAborya1/Movie-Management-Application.git
Navigate to the backend directory:

cd movie-management-app/backend
Update the application.properties file with your database credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/movie_db
spring.datasource.username=root
spring.datasource.password=yourpassword

Build and run the Spring Boot application:
mvn clean install
mvn spring-boot:run
Frontend Setup

Navigate to the frontend directory:
cd movie-management-app/frontend

Install dependencies:
npm install
Run the Angular application:
ng serve
Open your browser and navigate to http://localhost:4200.
