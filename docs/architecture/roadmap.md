# Implementation roadmap

## Phase 1 — executable core

- modular monolith;
- Access, Partners, and Credit modules;
- PostgreSQL and migrations;
- simulations, eligibility, and proposals;
- proposal state machine;
- domain, integration, and contract tests;
- ArchUnit;
- OpenAPI documentation.

Phase 1 also establishes the contracts needed to implement this flow safely: API conventions, `AuthorizedPartnerContext`, an explicit proposal state-machine specification, public module contracts, and ArchUnit enforcement. It does **not** implement production-grade idempotency, real margin reservation, or the transactional outbox; those capabilities remain intentionally deferred as described below.

## Phase 2 — product resilience

- complete OAuth2/OIDC, RBAC, and scopes;
- multi-tenancy;
- idempotency;
- concurrency control;
- persistent jobs and proposal expiration;
- append-only audit trail;
- rate limiting.

## Phase 3 — integration and operations

- transactional outbox and reliable publisher;
- external broker only when justified;
- OpenTelemetry traces, metrics, and dashboards;
- CI/CD and contract validation;
- Docker restart and recovery scenarios.

## Phase 4 — prepared and controlled AI

- Risk Engine adapter;
- RAG for policy and documentation consultation;
- authorized MCP tools;
- assisted analysis with explanation and input/output records;
- cost limits, propagated timeouts, bounded idempotent retries;
- human approval for sensitive decisions.

AI never replaces deterministic rules or receives implicit authority to approve credit.

## Extracting a module

Extract a module into a service only for a concrete reason: proven independent scale, independent deployment, security or availability isolation, a process-owning external integration, or a data boundary that cannot remain local. Preserve its public contract and replace local calls through interfaces; do not rewrite the entire domain.
