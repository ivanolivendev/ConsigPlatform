# Development guide and delivery phases

This guide explains **what to build next**, the dependencies between phases, and the evidence required to close each phase. It does not replace the [architecture library](../architecture/README.md).

## Delivery rule

Build from foundation to domain and from domain to advanced capabilities:

```text
Documentation → executable infrastructure → modular skeleton → Access → Partners
→ Credit domain → persistence and API → transactional resilience → events and operations → controlled AI
```

Do not begin business models, public endpoints, or AI integrations before the foundation runs and module boundaries are enforced.

| Phase | Depends on | Primary result |
| --- | --- | --- |
| 0. Documentation | Nothing | shared vision, decisions, and work order |
| 1. Executable foundation | Phase 0 | reproducible application and database |
| 2. Modular skeleton | Phase 1 | empty modules and ArchUnit rules |
| 3. Access | Phase 2 | trusted identity and authorized context |
| 4. Partners | Access | partner, agent, and tenant context |
| 5. Credit domain | module/context | simulations, eligibility, proposals, states |
| 6. Persistence and API | Credit domain | PostgreSQL adapters and OpenAPI flow |
| 7. Resilience | complete flow | idempotency, concurrency, expiration |
| 8. Events and operations | resilience | outbox, audit, observability |
| 9. Controlled AI | stable domain | bounded Risk, RAG, MCP assistance |

## Phase 0 — documentation

Record product vision, modular-monolith decision, responsibilities, allowed dependencies, roadmap, risks, and open decisions. Done means a new developer can understand the project and work order without guessing.

## Phase 1 — executable foundation

Implement Java 25, Spring Boot, Maven, Dockerfile, Docker Compose, PostgreSQL, Flyway, environment configuration, Actuator, `/actuator/health`, and basic build tests. Done means Docker alone can bring up the application, database, migrations, and an UP health check.

## Phase 2 — modular skeleton

Create `domain`, `application`, `infrastructure`, and `interfaces` packages for Access, Partners, and Credit. Add ArchUnit checks for cycle-free modules, domain independence from Spring, and forbidden infrastructure access. Done means boundary violations fail the build.

## Phase 3 — Access

Implement token-based authentication, active application validation, scopes, partner association, `AuthorizedPartnerContext`, and authorization failures. Do not add credit rules here. Done means a valid call yields trusted context and an invalid identity is rejected.

## Phase 4 — Partners

Model partners, applications, agents, co-partners, relationships, and tenant isolation. Publish only public contracts. Done means the platform identifies the authenticated partner and prevents cross-tenant access.

## Phase 5 — Credit domain

Implement in order: simulation, eligibility, proposal, state machine. Add calculations, limits, snapshots, state transitions, domain events, and margin invariants. Start with pure domain tests—no Spring, database, or HTTP. Done means valid and invalid business behavior is exhaustively tested.

## Phase 6 — persistence and API

Add migrations, persistence adapters, transactions, DTOs, controllers, HTTP validation, OpenAPI, integration tests, and contract tests. The first vertical flow is authenticated partner → authorized context → simulation → eligibility → proposal → proposal query. Done means Docker Compose can execute the documented flow end to end.

## Phase 7 — transactional resilience

Add `Idempotency-Key`, conflicting-payload protection, locking, transactional margin reservation, expiration, persistent jobs, bounded retries, and restart recovery. Done means retries do not duplicate effects and concurrent requests cannot overspend margin.

## Phase 8 — events and operations

Add domain events, outbox records, post-commit publishing, audit, notifications, metrics, traces, dashboards, then a broker only when justified. Done means one operation is traceable by tenant, actor, resource, and `trace_id`, including failures and retries.

## Phase 9 — controlled AI

Add a `RiskEngine` port, deterministic local adapter, explanations, policy/documentation RAG, authorized MCP tools, and analysis assistance. Propagate timeouts, use bounded idempotent retries, audit relevant input/output, enforce cost limits, and require human approval for sensitive decisions. Done means AI failure cannot corrupt domain state or create authorization.

## Commit sequence examples

```text
docs: add project development guide
build: initialize runnable project foundation
infra: configure PostgreSQL and Flyway
test: enforce initial module boundaries
feat: add access context
feat: add partner context
feat: add credit simulation
feat: add proposal idempotency
fix: prevent cross-tenant access
```

Follow [CONTRIBUTING.md](../../CONTRIBUTING.md) before opening a Pull Request.
