# Module boundaries

## Access

Owns authentication, authorization, scopes and the authorized partner context consumed by other modules.

## Partners

Owns partners, authorized applications, agents, co-partners and tenant relationships.

## Credit

Owns simulation, eligibility, proposals and their business state transitions.

## Dependency rules

```text
interfaces → application → domain
infrastructure → application + domain
```

Modules must not import internal classes from another module. Cross-module communication uses small public contracts or domain events.

## Enforcement

ArchUnit will verify these rules before the first business model is added. A change that breaks a boundary must fail the build.
