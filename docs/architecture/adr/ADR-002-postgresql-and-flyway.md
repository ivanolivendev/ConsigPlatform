# ADR-002: Use PostgreSQL and Flyway for transactional state

**Status:** Accepted

## Context

Proposal lifecycle, margin reservation, idempotency, and audit require durable, transactional state and reproducible schema changes.

## Decision

Use PostgreSQL as the transactional source of truth and Flyway as the only schema migration mechanism.

## Consequences

Business effects that require atomicity occur in a single PostgreSQL transaction. Migrations are versioned, reproducible, and validated in the containerized environment. A transactional outbox extends confirmed facts beyond the database boundary later.
