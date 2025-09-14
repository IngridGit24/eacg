# EEACG School Management Platform - Microservices Architecture

## Overview
The EEACG (Ecoles de l'Eglise de l'Alliance Chretienne de Gabon) platform is designed as a comprehensive school management system using microservices architecture to handle multiple school types: Pré-primaire, Primaire, Collège, and Lycée.

## Architecture Principles
- **Microservices**: Each business domain is a separate service
- **Frontend/Backend Separation**: Modern React frontend with RESTful APIs
- **Containerization**: Docker for all services
- **Orchestration**: Kubernetes for deployment and scaling
- **Database per Service**: Each microservice has its own database
- **API Gateway**: Centralized entry point for all client requests

## Microservices Architecture

### Core Services

#### 1. **School Service** (Existing)
- **Purpose**: Manage school information and configurations
- **Database**: PostgreSQL
- **Port**: 8081
- **Responsibilities**:
  - School registration and management
  - School type management (Pré-primaire, Primaire, Collège, Lycée)
  - School capacity and class management
  - School location and contact information

#### 2. **Student Service** (Existing)
- **Purpose**: Student information management
- **Database**: PostgreSQL
- **Port**: 8082
- **Responsibilities**:
  - Student registration and profiles
  - Student academic history
  - Student photos and documents
  - Student status tracking

#### 3. **Enrollment Service** (New)
- **Purpose**: Student enrollment and admission process
- **Database**: PostgreSQL
- **Port**: 8083
- **Responsibilities**:
  - New student applications
  - Enrollment workflow management
  - Document verification
  - Admission decisions
  - Integration test scheduling

#### 4. **Payment Service** (New)
- **Purpose**: School fees and payment management
- **Database**: PostgreSQL
- **Port**: 8084
- **Responsibilities**:
  - Fee structure management
  - Payment processing
  - Payment history tracking
  - Late payment notifications
  - Financial reporting

#### 5. **Academic Service** (New)
- **Purpose**: Academic management (grades, subjects, classes)
- **Database**: PostgreSQL
- **Port**: 8085
- **Responsibilities**:
  - Subject and curriculum management
  - Class and section management
  - Teacher assignment
  - Academic calendar
  - Grade management

#### 6. **Assessment Service** (New)
- **Purpose**: Tests, exams, and evaluation management
- **Database**: PostgreSQL
- **Port**: 8086
- **Responsibilities**:
  - Test creation and scheduling
  - Integration tests for new students
  - Exam management
  - Grade entry and calculation
  - Result processing

#### 7. **Notification Service** (New)
- **Purpose**: Communication and notifications
- **Database**: MongoDB (for flexible notification storage)
- **Port**: 8087
- **Responsibilities**:
  - SMS notifications
  - Email notifications
  - Push notifications
  - Parent communication
  - School announcements

#### 8. **User Management Service** (New)
- **Purpose**: Authentication and authorization
- **Database**: PostgreSQL
- **Port**: 8088
- **Responsibilities**:
  - User authentication (JWT)
  - Role-based access control
  - Parent, teacher, admin accounts
  - Password management
  - Session management

#### 9. **Reporting Service** (New)
- **Purpose**: Analytics and reporting
- **Database**: PostgreSQL + Redis (caching)
- **Port**: 8089
- **Responsibilities**:
  - Academic reports
  - Financial reports
  - Student performance analytics
  - School statistics
  - Dashboard data

#### 10. **File Management Service** (New)
- **Purpose**: Document and file storage
- **Database**: PostgreSQL (metadata) + MinIO/S3 (files)
- **Port**: 8090
- **Responsibilities**:
  - Document upload and storage
  - Student photos
  - Academic certificates
  - Report generation
  - File access control

### Supporting Infrastructure

#### API Gateway
- **Technology**: Spring Cloud Gateway
- **Port**: 8080
- **Responsibilities**:
  - Request routing
  - Load balancing
  - Authentication
  - Rate limiting
  - CORS handling

#### Service Discovery
- **Technology**: Eureka Server
- **Port**: 8761
- **Responsibilities**:
  - Service registration
  - Health monitoring
  - Load balancing

#### Configuration Server
- **Technology**: Spring Cloud Config
- **Port**: 8888
- **Responsibilities**:
  - Centralized configuration
  - Environment-specific settings
  - Dynamic configuration updates

## Technology Stack

### Backend
- **Framework**: Spring Boot 3.x
- **Language**: Java 17
- **Database**: PostgreSQL (primary), MongoDB (notifications), Redis (caching)
- **Security**: Spring Security + JWT
- **Communication**: REST APIs, Spring Cloud OpenFeign
- **Documentation**: OpenAPI 3 (Swagger)

### Frontend
- **Framework**: React 18 with TypeScript
- **State Management**: Redux Toolkit
- **UI Library**: Material-UI (MUI) or Ant Design
- **Routing**: React Router
- **HTTP Client**: Axios
- **Build Tool**: Vite

### DevOps
- **Containerization**: Docker
- **Orchestration**: Kubernetes
- **CI/CD**: GitHub Actions or GitLab CI
- **Monitoring**: Prometheus + Grafana
- **Logging**: ELK Stack (Elasticsearch, Logstash, Kibana)
- **Message Broker**: RabbitMQ or Apache Kafka

## Database Design

### School Service Database
```sql
- schools (id, matricule, name, date_of_creation, type, location, number_of_classes, capacity_per_class)
- school_types (id, name, description)
```

### Student Service Database
```sql
- students (id, matricule, first_name, last_name, birth_date, level, picture_url, school_id, parent_id)
- parents (id, first_name, last_name, email, phone, address)
```

### Enrollment Service Database
```sql
- applications (id, student_id, school_id, application_date, status, documents)
- integration_tests (id, student_id, test_date, score, status)
```

### Payment Service Database
```sql
- fee_structures (id, school_id, level, academic_year, amount)
- payments (id, student_id, amount, payment_date, status, method)
- payment_schedules (id, student_id, due_date, amount, status)
```

## Deployment Strategy

### Development Environment
- Docker Compose for local development
- Individual service databases
- Mock external services

### Production Environment
- Kubernetes cluster
- Separate namespaces for each environment
- Persistent volumes for databases
- Load balancers and ingress controllers
- Auto-scaling based on metrics

## Security Considerations
- JWT-based authentication
- Role-based access control (RBAC)
- API rate limiting
- Data encryption at rest and in transit
- Regular security audits
- GDPR compliance for student data

## Monitoring and Observability
- Health checks for all services
- Distributed tracing with Zipkin
- Centralized logging
- Performance metrics
- Alert systems for critical issues

## Scalability Considerations
- Horizontal scaling of stateless services
- Database read replicas
- Caching strategies
- CDN for static assets
- Message queues for async processing
