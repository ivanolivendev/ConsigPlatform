# ConsigPlatform

ConsigPlatform is a fictional payroll-deducted loan platform built to demonstrate reliable Java backend engineering. It uses synthetic data only and does not represent a financial institution or execute real financial operations.

The project deliberately starts as a **modular monolith**: one deployable application, explicit business boundaries, local transactional consistency, and an evolution path to events and controlled AI when those capabilities are justified.

## Current foundation

- Java 25 and Spring Boot 3.5;
- PostgreSQL and Flyway migrations;
- Docker Compose local environment;
- health endpoint through Spring Boot Actuator;
- empty `access`, `partners`, and `credit` module boundaries;
- ArchUnit architecture tests.

Business models are intentionally introduced after the executable foundation and its boundaries are proven.

## Run locally

Docker is the only local prerequisite for the current foundation.

```bash
docker compose up --build
```

Then check:

```text
http://localhost:8080/actuator/health
```

The expected response includes `"status":"UP"`.

## Documentation

Start with the [documentation index](docs/README.md). The architectural library is the source of truth when a document conflicts with an older implementation note.

| Audience | Start here |
| --- | --- |
| New developer | [Development guide](docs/development/README.md) |
| Architecture and domain design | [Architecture library](docs/architecture/README.md) |
| Local environment and validation | [Local setup and troubleshooting](docs/development/local-setup-and-troubleshooting.md) |
| Tests and quality gates | [Testing strategy](docs/development/testing-strategy.md) |
| Product and delivery stakeholders | [Executive product vision](docs/stakeholders/executive-product-vision.md) |
| Portuguese PDF reference library | [pt-BR PDFs](docs/pt-BR/README.md) |

## Delivery roadmap

1. **Executable core:** modular monolith, initial modules, PostgreSQL, simulations, eligibility, proposals, state machine, tests, and OpenAPI.
2. **Product resilience:** OAuth2/OIDC, tenant isolation, idempotency, concurrency control, audit trail, and persistent jobs.
3. **Integration and operations:** transactional outbox, events, observability, CI/CD, and recovery validation.
4. **Controlled AI:** a risk adapter, RAG for policies, authorized MCP tools, explainability, and human approval for sensitive decisions.

## Contribution model

The repository follows Git Flow:

```text
main -> stable releases
develop -> integration
feature/* -> planned work
release/* -> release preparation
hotfix/* -> urgent stable-code fixes
```

Use English Conventional Commits and open feature Pull Requests into `develop`. See [CONTRIBUTING.md](CONTRIBUTING.md) for the complete workflow.
