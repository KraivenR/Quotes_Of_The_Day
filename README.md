# Quote of the Day

## Description

A Spring Boot REST API for managing and retrieving quotes. The application allows users to create, view, update, and delete quotes through REST API endpoints.

## Technology Stack

* Java 17/21
* Spring Boot 3.2.x
* Maven 3.10
* H2 Database (development)
* PostgreSQL/MySQL (production)

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Run the main application class.
4. The application starts at `http://localhost:8080`.

## API Endpoints

### Quote Management

* `GET /api/quotes` - Get all quotes
* `GET /api/quotes/{id}` - Get a single quote
* `POST /api/quotes` - Create a new quote
* `PUT /api/quotes/{id}` - Update a quote
* `DELETE /api/quotes/{id}` - Delete a quote

## Development Status

* [x] Lab 1: Project setup and basic REST endpoints
* [x] Quote management REST API

## Author

Kraiven Ribeiro
