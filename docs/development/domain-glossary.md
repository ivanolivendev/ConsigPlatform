# Domain glossary

| Term | Meaning in ConsigPlatform |
| --- | --- |
| Payroll-deducted loan | Loan repaid through deductions associated with an eligible income source. This project models it only with synthetic data. |
| Partner | Organization authorized to use the platform within its own tenant. |
| Tenant | Data-isolation boundary represented by the authenticated partner context. |
| Agent | Authorized actor operating for a partner. |
| Co-partner | Partner-associated entity whose permissions are scoped by a relationship. |
| Authorized partner context | Trusted application-level representation of identity, partner, scopes, and permitted actor. |
| Simulation | Calculated loan conditions before a proposal exists. |
| Eligibility | Deterministic assessment of whether the customer and conditions satisfy defined rules. |
| Proposal | Stateful request that moves from draft through analysis, approval/rejection, contracting, and possible disbursement. |
| Margin | Available amount that may be reserved for payroll deductions; a concurrency-sensitive invariant. |
| Reservation | Transactional hold against available margin created for a qualifying proposal. |
| Idempotency key | Client-supplied key allowing safe replay of a command without duplicating its effects. |
| Outbox event | Durable event record written with a business transaction and published after commit. |
| Risk Engine | Credit-owned port that returns a risk/eligibility analysis. It begins deterministically and may later receive controlled adapters. |
