## About
WIP - This project was developed as a practice, to explore SpringBoot and React libraries.

## Architecture

- **Frontend**: React with Vite
- **Backend**: Spring Boot with Java
- **Containerization**: Docker with docker-compose for easy deployment

## Quick Start

### Prerequisites
- Docker and Docker Compose
- Java 17+ (for local development)
- Node.js 18+ (for local development)

### Using Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd BotWarehouse
   ```

2. **Start the application**
   ```bash
   docker-compose up --build
   ```

3. **Access the application**
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080

### Local Development

#### Backend Setup
```bash
cd Backend
./mvnw spring-boot:run
```

#### Frontend Setup
```bash
cd frontend
npm install
npm run dev
```
