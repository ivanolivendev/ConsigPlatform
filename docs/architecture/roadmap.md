# Implementation roadmap

## Foundation

- runnable Spring Boot application;
- Docker Compose;
- PostgreSQL;
- Flyway migration;
- health check;
- initial documentation.

## Modular skeleton

- empty `access`, `partners` and `credit` module packages;
- ArchUnit boundary tests;
- local development instructions.

## First vertical slice

- Access context;
- partner context;
- credit simulation;
- eligibility rules;
- proposal state machine;
- OpenAPI contract;
- domain, integration and contract tests.

## Later capabilities

Idempotency, concurrency control, outbox, audit, notifications, observability and controlled AI integrations are introduced only after the first vertical slice is stable.
