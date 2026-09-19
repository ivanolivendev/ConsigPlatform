# Business flows

## Proposal state machine

```text
DRAFT
  ↓
ELIGIBILITY_CHECK
  ↓
SIMULATED
  ↓
SUBMITTED
  ↓
ANALYSIS
  ├── REJECTED
  └── APPROVED
        ↓
    CONTRACTED
        ↓
    DISBURSED
```

The eventual public API names are defined by the phase-specific OpenAPI contract; these names express the conceptual model.

## Transition rules

Every transition is an explicit domain operation or policy. State changes must not be scattered through controllers or generic services. A transition validates the allowed current state, domain preconditions, authorized identity, time validity, transactional effects, and the resulting domain event.

## Contracting workflow

```text
Submit proposal → validate customer → validate margin → run risk analysis
      → generate contract → register contract → schedule disbursement
```

The initial workflow is a persisted internal process. If a failure follows an independent partial effect, the system either performs an explicit compensation or retains a recoverable state.

## Internal saga rule

Introduce a saga only when a workflow has multiple independent effects. Each step must declare an input command, success event, recoverable failure, terminal failure, compensation, retry policy, and idempotency behavior. Being inside a monolith reduces communication cost; it does not remove the need to model failure.
