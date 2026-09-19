# ADR-003: Enforce module boundaries with ArchUnit

**Status:** Accepted

## Context

Documented boundaries are insufficient if ordinary changes can silently bypass them.

## Decision

Use ArchUnit tests to enforce layer direction, domain independence, absence of forbidden cross-module imports, and absence of cycles.

## Consequences

The build rejects architectural regressions early. The initial rules evolve with the implementation, but exceptions require an explicit architectural decision rather than an unreviewed dependency.
