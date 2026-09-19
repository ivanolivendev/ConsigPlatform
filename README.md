# ConsigPlatform

Fictional payroll-deducted loan platform built with Java 25 and Spring Boot.

The project demonstrates a modular monolith, hexagonal architecture, transactional consistency, eligibility rules, proposal workflows, security and automated testing.

This project uses synthetic data and does not represent a real financial institution or perform real financial operations.

## Architecture

The application starts as a modular monolith with three initial business modules:

- `access`: authentication, authorization and partner context;
- `partners`: partners, applications, agents and tenant relationships;
- `credit`: simulation, eligibility and proposals.

The architectural documentation is the source of truth for internal boundaries and evolution decisions. See [Architecture](docs/architecture/README.md).

## Technology baseline

- Java 25
- Spring Boot 3.5.16
- Maven
- PostgreSQL
- Flyway
- Docker Compose
- JUnit 5
- ArchUnit

## Local development

The first foundation milestone will provide a runnable application and PostgreSQL environment. Business models are intentionally introduced in later feature branches.

```bash
docker compose up --build
```

Health check:

```text
http://localhost:8080/actuator/health
```

## Git Flow

- `main`: stable code;
- `develop`: integration branch;
- `feature/*`: feature work;
- `release/*`: release preparation;
- `hotfix/*`: urgent production fixes.

Commit messages use English Conventional Commits.
