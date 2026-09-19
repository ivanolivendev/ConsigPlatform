# Local setup and troubleshooting

## Prerequisites

The executable foundation requires Docker Desktop with Docker Compose available. A local Java or Maven installation is optional because the Docker build contains its own toolchain.

## Start the platform

```bash
docker compose up --build
```

Expected startup sequence:

```text
PostgreSQL becomes healthy → application starts → Flyway applies migrations → health endpoint returns UP
```

Check the service:

```text
GET http://localhost:8080/actuator/health
```

## Useful commands

```bash
docker compose ps
docker compose logs app
docker compose logs postgres
docker compose down
```

Use `docker compose down -v` only when you deliberately want to remove the local database volume and all development data.

## Troubleshooting

| Symptom | Likely cause | Action |
| --- | --- | --- |
| Docker command fails | Docker Desktop is not running | Start Docker Desktop and wait for the engine |
| App cannot connect to PostgreSQL | Database has not become healthy | Inspect `docker compose logs postgres` and retry |
| Port 8080 is busy | Another local service uses the port | Stop the conflicting process or adjust the compose port mapping |
| Migration fails | Local schema does not match migration history | Inspect Flyway output; reset the local volume only if its data is disposable |
| Health is not UP | Application startup failed | Inspect `docker compose logs app` for the root exception |

## Configuration hygiene

Copy `.env.example` when environment overrides are needed. Never commit local secrets, tokens, production-like credentials, or real personal data. Seeds, requests, and screenshots must stay synthetic.
