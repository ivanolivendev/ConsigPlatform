# Testing strategy

Quality is layered: each test type owns a distinct risk and is run as close as possible to the change that created the risk.

| Test type | Protects | Examples |
| --- | --- | --- |
| Domain | business rules and invariants | calculation, eligibility, proposal transition |
| Architecture | modular boundaries | no Spring in domain, no forbidden imports, no cycles |
| Integration | real infrastructure behavior | Flyway, PostgreSQL transaction, locking, outbox record |
| Contract | public HTTP behavior | OpenAPI status, request validation, response shape |
| Concurrent integration | race conditions | two reservations cannot exceed margin |
| End-to-end | operable product flow | Compose startup through documented endpoint |

## Test-first order for a use case

1. Specify the domain behavior in a pure unit test.
2. Add the application use case and its port tests.
3. Add a real PostgreSQL integration test if transactions or persistence are involved.
4. Publish and verify the OpenAPI contract before or with the controller.
5. Add contract tests and an end-to-end scenario where the flow crosses boundaries.

## Mandatory quality gates

- The build and all relevant automated tests pass.
- ArchUnit passes on every change that could affect boundaries.
- Schema migrations run cleanly on an empty PostgreSQL instance.
- API changes update OpenAPI and contract tests.
- Retryable commands demonstrate idempotency.
- Margin-affecting behavior demonstrates real concurrency safety against PostgreSQL.
- Docker Compose produces an UP health check.

## Test data

Use deterministic, synthetic fixtures. Keep test data focused on the behavior under test; never use credentials or customer data from real systems. Name scenarios by business intent, such as `shouldRejectProposalWhenMarginIsInsufficient`.
