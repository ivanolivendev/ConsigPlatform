# Phase 1 scope and deferred improvements

This document turns the architecture review into an implementation boundary. It prevents resilience, integration, and AI concerns from being pulled into the executable core prematurely while ensuring their prerequisites are consciously documented.

## Implement now — Phase 1 executable core

| Improvement | Deliverable | Why it belongs now |
| --- | --- | --- |
| API foundation | API conventions for versioning, RFC 7807-compatible errors, `X-Correlation-Id`, pagination, filtering, and OpenAPI ownership | Controllers and contract tests need stable conventions before the first public endpoint. |
| Access contract | Immutable `AuthorizedPartnerContext` specification: tenant, partner, application, scopes, optional agent/co-partner, producer, and consumers | Partners and Credit must receive explicit authorization context rather than reading framework state throughout the domain. |
| Credit state machine | Table of states, valid and invalid transitions, preconditions, terminal states, and events | The proposal lifecycle must be testable before business persistence and HTTP adapters are added. |
| Duplicate-proposal rule | Distinguish business duplication from transport retry | The domain must decide whether simultaneous active proposals are allowed; HTTP idempotency later prevents replayed commands. |
| Risk boundary | `RiskEngine` is a Credit-owned port; deterministic, external, or AI implementations are adapters | Credit retains ownership of its decision flow without creating a separate Risk bounded context prematurely. |
| Architecture enforcement | Public-contract rule and ArchUnit checks for forbidden cross-module access | Module boundaries must fail the build, not rely only on convention. |
| Internal events | Explicit in-process-only behavior before the outbox | No external reliable publication is assumed before Phase 3. |
| Delivery visibility | Lightweight status document and phase-to-Git-Flow link | Contributors need to distinguish implemented work from roadmap work. |

## Decide now, implement in Phase 2

The margin concurrency strategy is an architectural decision that affects the future schema and test design. It must be recorded before real margin reservation is built, but the reservation, locking, retry, and expiration mechanisms do not belong in the executable core.

The decision must compare optimistic locking, short pessimistic locking, and transactional SQL conditions against the actual margin model. A database `CHECK` constraint is a safety invariant, not a complete solution to concurrent lost updates.

## Deliberately deferred — Phase 2 product resilience

- OAuth2/OIDC completion, RBAC, and production-grade scopes;
- multi-tenancy enforcement beyond the initial explicit context contract;
- HTTP idempotency storage and replay behavior;
- real margin reservation, concurrency control, bounded retry, and expiration;
- persistent jobs, audit trail, and rate limiting.

## Deliberately deferred — Phase 3 integration and operations

- transactional outbox and reliable external publisher;
- notifications and external integration adapters;
- broker adoption only when justified by a real consumer, scale, or reliability need;
- timeout propagation across remote calls, circuit breakers, bulkheads, and DLQ policies;
- production observability, CI/CD hardening, and restart recovery.

## Deliberately deferred — Phase 4 controlled AI

- AI-backed Risk Engine adapter;
- RAG over curated policy and documentation sources;
- authorized MCP tools;
- timeout propagation, cancellation, budget limits, and idempotent retry controls for LLM calls;
- human approval and audit for sensitive analysis.

AI remains an assistant. Deterministic domain rules retain authority over eligibility and financial state transitions.

## Delivery model

Each item is developed in a focused `feature/*` branch and merged through a Pull Request into `develop`. A phase is complete when its documented quality gates pass; a phase is not represented by a permanent Git branch.
