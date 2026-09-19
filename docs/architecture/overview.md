# Architectural overview

ConsigPlatform is a fictional payroll-deducted loan platform designed to demonstrate reliable Java backend engineering, transactional consistency, security, automated tests, and a controlled path to events and AI. It uses synthetic data only.

## Primary decision

The system begins as a **modular monolith**: one deployable application with explicit internal boundaries. This keeps local transactions simple and verifiable, reduces operating cost, enables direct end-to-end testing, and preserves a future extraction path without simulating distributed systems inside one process.

```text
ConsigPlatform
├── Access      authentication, authorization, and partner context
├── Partners    partners, applications, agents, and tenant relationships
└── Credit      simulations, eligibility, proposals, contracts, and disbursement
```

Audit, Notifications, and Risk evolve as supporting capabilities. Audit and Notifications consume public events; Risk is a port owned by Credit and can initially be implemented locally.

The executable foundation deliberately contains no business entities. It must first prove that it can build, start, connect to PostgreSQL, run Flyway migrations, expose a health check, and enforce module boundaries.

## Internal style

```text
module/
├── domain/          rules, entities, events, and invariants
├── application/     use cases and ports
├── infrastructure/  persistence, messaging, and adapters
└── interfaces/      REST, inbound events, and external DTOs
```

The domain does not depend on Spring, persistence, messaging, or HTTP. Application services coordinate use cases through ports; infrastructure implements them.

## Principles

1. The domain defines business rules; controllers do not.
2. Modules expose only small, stable public contracts.
3. A module never imports another module's internal class.
4. Transactions protect business invariants, not merely technical writes.
5. Events communicate confirmed facts; they do not conceal mandatory synchronous dependencies.
6. Important architectural decisions must be testable.
7. Complexity is added only in response to a demonstrated need.
