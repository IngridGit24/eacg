# EEACG School Management Platform

## Overview

The EEACG (Ecoles de l'Eglise de l'Alliance Chretienne de Gabon) platform is a comprehensive school management system designed to centralize and manage multiple schools including Pré-primaire, Primaire, Collège, and Lycée levels.

## Features

- **Student Enrollment**: Complete enrollment process with integration tests
- **Payment Management**: School fees and payment tracking
- **Academic Management**: Grades, subjects, and class management
- **Assessment System**: Tests, exams, and evaluation management
- **Communication**: Notifications and parent communication
- **Reporting**: Analytics and comprehensive reports
- **Multi-School Support**: Manage multiple schools from a single platform

## Architecture

The platform is built using a microservices architecture with the following components:

### Backend Services
- **School Service** (Port 8081): School information management
- **Student Service** (Port 8082): Student data management
- **Enrollment Service** (Port 8083): Student enrollment and admission process
- **Payment Service** (Port 8084): Fee management and payment processing
- **Academic Service** (Port 8085): Academic management (grades, subjects, classes)
- **Assessment Service** (Port 8086): Tests, exams, and evaluation management
- **Notification Service** (Port 8087): Communication and notifications
- **User Management Service** (Port 8088): Authentication and authorization
- **Reporting Service** (Port 8089): Analytics and reporting
- **File Management Service** (Port 8090): Document and file storage

### Infrastructure Services
- **API Gateway** (Port 8080): Centralized entry point
- **Eureka Server** (Port 8761): Service discovery
- **Config Server** (Port 8888): Centralized configuration

### Frontend
- **React Application** (Port 3000): Modern web interface

## Technology Stack

### Backend
- **Framework**: Spring Boot 3.x
- **Language**: Java 17
- **Database**: PostgreSQL, MongoDB, Redis
- **Security**: Spring Security + JWT
- **Communication**: REST APIs, Spring Cloud OpenFeign
- **Documentation**: OpenAPI 3 (Swagger)

### Frontend
- **Framework**: React 18 with TypeScript
- **State Management**: Redux Toolkit
- **UI Library**: Material-UI (MUI)
- **Routing**: React Router
- **HTTP Client**: Axios

### DevOps
- **Containerization**: Docker
- **Orchestration**: Kubernetes
- **CI/CD**: GitHub Actions
- **Monitoring**: Prometheus + Grafana
- **Logging**: ELK Stack

## Quick Start

### Prerequisites
- Java 17
- Node.js 18+
- Docker and Docker Compose
- PostgreSQL (for local development)

### Development Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd eacg
   ```

2. **Start infrastructure services**
   ```bash
   docker-compose up -d postgres-school postgres-student postgres-enrollment postgres-payment postgres-academic postgres-assessment postgres-user postgres-reporting postgres-file mongodb redis minio
   ```

3. **Start backend services**
   ```bash
   # Start Eureka Server
   cd eureka-server
   ./gradlew bootRun

   # Start API Gateway
   cd ../api-gateway
   ./gradlew bootRun

   # Start individual services
   cd ../school-service
   ./gradlew bootRun

   cd ../student-service
   ./gradlew bootRun

   cd ../enrollment-service
   ./gradlew bootRun
   ```

4. **Start frontend**
   ```bash
   cd frontend
   npm install
   npm start
   ```

### Docker Setup

1. **Build and start all services**
   ```bash
   docker-compose up --build
   ```

2. **Access the application**
   - Frontend: http://localhost:3000
   - API Gateway: http://localhost:8080
   - Eureka Dashboard: http://localhost:8761

### Kubernetes Deployment

1. **Apply Kubernetes configurations**
   ```bash
   kubectl apply -f k8s/
   ```

2. **Check deployment status**
   ```bash
   kubectl get pods -n eacg-platform
   kubectl get services -n eacg-platform
   ```

## Project Structure

```
eacg/
├── school-service/           # School management service
├── student-service/          # Student management service
├── enrollment-service/       # Enrollment and admission service
├── payment-service/          # Payment management service
├── academic-service/         # Academic management service
├── assessment-service/       # Assessment and testing service
├── notification-service/     # Communication service
├── user-management-service/  # Authentication service
├── reporting-service/        # Analytics and reporting service
├── file-management-service/  # File storage service
├── api-gateway/             # API Gateway
├── eureka-server/           # Service discovery
├── config-server/           # Configuration server
├── frontend/                # React frontend application
├── k8s/                     # Kubernetes configurations
├── docker-compose.yml       # Docker Compose configuration
└── ARCHITECTURE.md          # Detailed architecture documentation
```

## API Documentation

Once the services are running, you can access the API documentation at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI Spec: http://localhost:8080/v3/api-docs

## Database Schema

Each microservice has its own database:

- **school_db**: School information and configurations
- **student_db**: Student data and profiles
- **enrollment_db**: Application and enrollment data
- **payment_db**: Payment and fee information
- **academic_db**: Academic data (subjects, classes, grades)
- **assessment_db**: Test and evaluation data
- **user_db**: User authentication and authorization
- **reporting_db**: Analytics and reporting data
- **file_db**: File metadata
- **notification_db**: Communication and notification data (MongoDB)

## Security

- JWT-based authentication
- Role-based access control (RBAC)
- API rate limiting
- Data encryption at rest and in transit
- GDPR compliance for student data

## Monitoring and Observability

- Health checks for all services
- Distributed tracing with Zipkin
- Centralized logging with ELK Stack
- Performance metrics with Prometheus
- Alert systems for critical issues

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please contact the development team or create an issue in the repository.

## Roadmap

- [ ] Complete payment service implementation
- [ ] Implement academic service
- [ ] Add assessment service
- [ ] Implement notification service
- [ ] Add user management service
- [ ] Complete reporting service
- [ ] Add file management service
- [ ] Implement mobile application
- [ ] Add advanced analytics
- [ ] Implement multi-language support
