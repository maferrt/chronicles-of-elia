# Tech Stack

## Mobile App

### React Native

Used to build a mobile application for Android and iOS using a shared codebase.

### Expo

Used to simplify mobile development, testing and previewing the app during the MVP phase.

### TypeScript

Used to write safer and more organized frontend code with typed data models, API responses and reusable components.

## Backend

### Java

Main backend language for business logic, object-oriented programming, services and API development.

### Spring Boot

Used to build the REST API, manage controllers, services, repositories, validation, security and database integration.

### Spring Security

Used to protect private routes and manage authentication.

### JWT

Used to authenticate users and allow secure access to private endpoints.

## Database

### PostgreSQL

Used as the relational database for users, profiles, diagnostics, learning paths, lessons, exercises and progress.

## API Documentation

### Swagger / OpenAPI

Used to document and test backend endpoints.

### Postman

Used to manually test API requests during development.

## DevOps

### Docker

Planned for local development and future deployment.

### Docker Compose

Planned to run PostgreSQL and backend services in a reproducible local environment.

## AI Integration

The project will use an external AI API in future phases to support:

- Writing correction
- Exercise generation
- Personalized feedback
- Role-play scenarios
- Learning path recommendations

## Architecture

The project will start as a modular monolith.

Future versions may evolve into microservices, separating responsibilities such as:

- Auth service
- User profile service
- Learning service
- Progress service
- AI service