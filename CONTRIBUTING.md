# Contributing to ConsigPlatform

Thank you for contributing. This project uses Git Flow, English Conventional Commits, and architecture tests to keep its modular-monolith boundaries real.

## Before starting

1. Read the [architecture library](docs/architecture/README.md).
2. Check the [development guide](docs/development/README.md) and identify the active phase.
3. Confirm whether the change affects APIs, domain rules, authorization, tenancy, or a documented decision.
4. Create a focused branch from `develop`.

## Git Flow

```text
main
  └── develop
       ├── feature/<short-name>
       ├── release/<version>
       └── hotfix/<short-name>
```

- `main`: stable, releasable code only.
- `develop`: integration branch.
- `feature/*`: planned functionality or improvement.
- `release/*`: release preparation.
- `hotfix/*`: urgent correction for stable code.

Use lowercase kebab-case names, for example `feature/credit-simulation` or `fix/cross-tenant-access`. Do not commit feature work directly to `main` or `develop`.

## Commits

Write small, focused, English Conventional Commits in the imperative mood:

```text
<type>: <short description>
```

Allowed types are `feat`, `fix`, `docs`, `build`, `infra`, `test`, `refactor`, and `chore`.

```text
docs: add testing strategy
infra: configure PostgreSQL and Flyway
feat: add credit simulation
test: add proposal transition tests
fix: prevent cross-tenant access
```

## Architecture and tests

- Keep business rules in the domain; keep controllers thin.
- Do not make the domain depend on Spring, persistence, messaging, or HTTP.
- Never import another module's internal implementation.
- Cross modules through small public contracts or confirmed domain events.
- Update ArchUnit rules when a boundary changes.
- Update the relevant documentation when behavior, API, security, or architecture changes.

Before opening a Pull Request, run the relevant tests and validate the containerized foundation:

```bash
docker compose up --build
```

Verify `GET /actuator/health` and include domain, integration, contract, architecture, and concurrency/idempotency tests whenever the change warrants them.

## Pull Requests

Feature Pull Requests target `develop`. Describe what changed, why, the project phase, architectural/API impact, validation performed, documentation changes, and known follow-ups. Do not merge while builds, architecture tests, reproducible migrations, or required API documentation are failing.
