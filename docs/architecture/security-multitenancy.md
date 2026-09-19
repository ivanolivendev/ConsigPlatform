# Security and multi-tenancy

## Identity and authorization

The API uses OAuth2/OIDC with JWT. The token identifies the calling application and scopes; the partner context is validated against local registration. The input layer maps external claims into an explicit `AuthorizedPartnerContext`, so the domain does not depend on JWT claims.

Authorization is layered: valid token, active application, authorized partner, compatible scope, permitted agent/co-partner, and access to a resource inside the correct tenant. Authentication alone never grants cross-tenant access.

## Tenant isolation

Every operation carries or derives a `tenant_id`; the tenant is the authenticated partner and may include an agent or co-partner context. Queries, commands, indexes, and constraints must prevent cross-tenant access. A client-supplied tenant field is never trusted when identity can derive it.

## Audit and sensitive data

Authentication, authorization, state changes, risk decisions, and financial effects produce correlated audit records containing `trace_id`, `tenant_id`, actor, and resource. The audit trail is append-only; corrections are new facts, not silent deletion.

The project stores no real customer data. Seeds and examples are synthetic, and secrets, tokens, and local credentials are never committed.
