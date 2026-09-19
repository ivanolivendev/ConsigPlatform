# Consistency, events, and concurrency

## Transactional source of truth

PostgreSQL is the source of truth for business state. Effects requiring atomicity happen in one local transaction.

```text
BEGIN
  lock or validate available margin
  create proposal
  reserve margin
  record domain event
  record pending work
COMMIT
```

## Transactional outbox

When a confirmed event must leave the application boundary, it is written to an `outbox_event` record in the same transaction as the business change. A reliable publisher dispatches it after commit. A broker is not a Phase 1 dependency; the outbox contract comes first.

## Idempotency

Retryable commands accept an idempotency key scoped by partner, operation, and normalized payload. The same key and payload return the original result; the same key with a different payload is a conflict. Recording the key and all command effects is atomic.

## Margin concurrency

Payroll-deducted margin is a domain invariant. Active concurrent reservations must never exceed available margin. The implementation chooses and documents optimistic or pessimistic locking, then proves it with a real concurrent PostgreSQL integration test.

## Events

Events represent facts after commit, for example `ProposalSubmitted`, `ProposalApproved`, `ProposalRejected`, `ContractRegistered`, and `DisbursementScheduled`. They do not publish before commit or hide a synchronous dependency that is required for correctness.
