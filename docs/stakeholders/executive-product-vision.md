# Executive product vision

> This is a stakeholder reading document. It does not replace the architecture library, OpenAPI, or technical specifications.

## Summary

ConsigPlatform is a fictional payroll-deducted loan platform showing how a financial-style solution can be built with clear rules, security, transactional consistency, and a gradual evolution path. It uses synthetic data only and performs no real financial operation.

The product begins as a modular monolith so the team can deliver value quickly without sacrificing business boundaries, reliable state changes, or future evolution.

## Problem explored

Payroll-deducted credit involves multiple participants, eligibility policies, available margin, authorization, proposals, and lifecycle transitions. The core challenge is not simply creating endpoints. The platform must ensure partners see only their own data, rules remain explainable, retries do not duplicate proposals or cost, concurrency cannot exceed margin, failures remain recoverable, and later integrations do not require rewriting the core.

## Primary journey

```text
Authenticated partner → credit simulation → eligibility check → proposal submission
→ analysis → approved or rejected proposal
```

Each step owns explicit rules and can produce events, audit records, and notifications.

## Capabilities by phase

| Phase | Outcome |
| --- | --- |
| 1. Executable core | Java application, PostgreSQL, simulations, eligibility, proposals, state machine, initial security, automated tests, OpenAPI |
| 2. Product resilience | idempotency, concurrency control, tenant isolation, expiration, bounded retries, recovery, audit |
| 3. Operations and integration | transactional outbox, events, notifications, metrics, tracing, dashboards, broker when justified |
| 4. Controlled AI | assisted analysis, policy RAG, authorized MCP tools, explainability, cost/timeout controls, human approval |

## Why a modular monolith

It keeps the initial operation simple without turning the codebase into an undifferentiated block. Each module owns a responsibility and its boundary. A later service extraction needs concrete evidence: independent scale, deployment, security, availability, or a data boundary that cannot remain local.

## Success indicators

The project succeeds when a new developer can run it locally; a partner can complete the first journey; eligibility rules are explainable and tested; retries cannot multiply effects or token bills; concurrency protects margin; failures are observable and recoverable; the domain can evolve without a rewrite; and AI remains bounded and auditable.

## Explicit non-goals

The initial scope excludes real financial operations, real customer data, autonomous AI credit decisions, a fleet of microservices, banking certification, and legally binding contracts.
