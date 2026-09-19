# ADR-001: Start with a modular monolith

**Status:** Accepted

## Context

The product needs clear domain boundaries, reliable financial invariants, and a runnable first delivery without premature distributed-systems overhead.

## Decision

Deploy one application while keeping Access, Partners, and Credit as explicit modules with public contracts and enforced internal boundaries.

## Consequences

Local transactions and end-to-end tests stay straightforward. Modules can be extracted later only for proven scale, deployment, security, availability, or data-boundary reasons. The team must actively prevent internal coupling through ArchUnit and code review.
