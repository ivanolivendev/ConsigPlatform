# Module boundaries

## Business modules

### Access

Owns authentication, authorization, scopes, technical application identity, and the authorized partner context consumed by other modules. It does not decide credit eligibility or access proposal storage directly.

### Partners

Owns partners, authorized applications, agents, co-partners, credentials, rate limits, and tenant relationships. It publishes authorized context without exposing internal entities.

### Credit

Owns simulations, eligibility, proposals, contracts, disbursement, and their business state transitions. It is the business core and owns financial invariants.

## Supporting capabilities

- **Audit:** append-only security and business facts. It consumes public events and never changes the source state.
- **Notifications:** reacts to public business events; it never approves, rejects, or alters a proposal.
- **Risk:** evaluates deterministic risk and eligibility through a port defined by Credit. It starts as a local adapter and may evolve later.

## Allowed dependencies

```text
interfaces → application → domain
infrastructure → application + domain

Access ───────► public authorization context
Partners ─────► public partner context
Credit ───────► public Access/Partners contracts
Audit ────────► public events
Notifications ► public events
Risk ─────────► Credit-owned analysis port
```

Modules must not import another module's `infrastructure` or `interfaces` package. Sharing ORM entities, repositories, or internal DTOs is prohibited. Cross-module communication uses small public contracts or confirmed domain events.

## Contract examples

- `AuthorizedPartnerContext`
- `CustomerEligibilityInput`
- `RiskAnalysisRequest`
- `RiskAnalysisResult`
- `ProposalSubmitted`
- `ProposalApproved`
- `ProposalRejected`

Events contain confirmed facts and correlation identifiers, never mutable ORM entities.

## Enforcement

ArchUnit verifies domain independence from Spring and infrastructure, module separation, public-only cross-module access, and absence of cycles. A change that breaks a boundary must fail the build.
