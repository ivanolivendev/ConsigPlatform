# Architecture decision records

ADRs capture decisions that are costly to rediscover. They complement the architecture library and are updated only when a decision changes—not when an implementation detail merely evolves.

| ADR | Decision | Status |
| --- | --- | --- |
| [ADR-001](ADR-001-modular-monolith.md) | Start with a modular monolith | Accepted |
| [ADR-002](ADR-002-postgresql-and-flyway.md) | Use PostgreSQL and Flyway for transactional state | Accepted |
| [ADR-003](ADR-003-enforced-module-boundaries.md) | Enforce module boundaries with ArchUnit | Accepted |

New ADRs use `ADR-<sequence>-<short-kebab-case-title>.md` and include context, decision, consequences, and status.
