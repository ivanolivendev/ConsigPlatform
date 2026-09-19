# ConsigPlatform Architecture

This directory is the source of truth for architectural decisions. Implementation documents must be aligned with these decisions when they evolve.

## Initial decisions

- The application starts as a modular monolith.
- Java 25 and Spring Boot are the runtime baseline.
- PostgreSQL is the transactional data store.
- Each business module follows hexagonal architecture.
- Business models are introduced after the technical foundation is verified.
- The first business modules are `access`, `partners` and `credit`.

## Reading order

1. [Architectural overview](overview.md)
2. [Module boundaries](module-boundaries.md)
3. [Implementation roadmap](roadmap.md)
