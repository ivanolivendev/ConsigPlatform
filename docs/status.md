# Project status

| Area | Status | Evidence |
| --- | --- | --- |
| Executable foundation | Complete | Spring Boot, Docker Compose, PostgreSQL, Flyway, and Actuator health check are available. |
| Modular skeleton | Complete | `access`, `partners`, and `credit` package boundaries plus initial ArchUnit rules exist. |
| Phase 1 architecture contracts | Planned next | API conventions, `AuthorizedPartnerContext`, proposal state-machine specification, and public module contracts. |
| Credit business flow | Not started | Simulation, eligibility, proposal, and HTTP contracts are Phase 1 delivery work. |
| Product resilience | Deferred | Phase 2: idempotency, margin concurrency, expiration, audit, and rate limiting. |
| Integration and operations | Deferred | Phase 3: outbox, integrations, observability, and recovery. |
| Controlled AI | Deferred | Phase 4: bounded Risk adapter, RAG, MCP, and human approval. |

Status is updated through focused feature Pull Requests. See [development phases](development/README.md) and [Phase 1 scope](development/phase-1-scope.md).
