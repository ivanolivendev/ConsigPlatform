# Architectural overview

ConsigPlatform is a fictional payroll-deducted loan platform designed to demonstrate reliable Java backend engineering.

It starts as one deployable application with explicit internal boundaries:

```text
ConsigPlatform
├── Access      authentication and authorization context
├── Partners    partner and tenant relationships
└── Credit      simulation, eligibility and proposals
```

The initial foundation deliberately contains no business entities. The application must first prove that it can build, start, connect to PostgreSQL, run Flyway migrations and expose a health check.

Each module will use this structure as it becomes functional:

```text
module/
├── domain/
├── application/
├── infrastructure/
└── interfaces/
```

The domain must not depend on Spring, persistence or transport details. Application services coordinate use cases through ports, while infrastructure implements those ports.
