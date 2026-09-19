# ConsigPlatform architecture

This directory is the architectural source of truth. When it conflicts with older material, the newest decision documented here prevails. Derived implementation documents must be updated after relevant architecture changes.

## Core decisions

- One deployable **modular monolith**, not premature microservices.
- Java 25 and Spring Boot are the runtime baseline.
- PostgreSQL is the transactional source of truth; Flyway owns schema evolution.
- Every business module follows hexagonal architecture.
- Initial business modules are `access`, `partners`, and `credit`.
- Business state remains locally transactional; the outbox is introduced before an external broker.
- AI is an observable, bounded assistant and never an implicit credit decision-maker.

Kafka, Redis, and an external risk engine are not Phase 1 requirements. They are introduced only when the roadmap demonstrates their need.

## Reading order

1. [Architectural overview](overview.md)
2. [Module boundaries](module-boundaries.md)
3. [Business flows](business-flows.md)
4. [Consistency, events, and concurrency](consistency-events-concurrency.md)
5. [Security and multi-tenancy](security-multitenancy.md)
6. [Architecture decision records](adr/README.md)
7. [Implementation roadmap](roadmap.md)
