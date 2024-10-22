# README

- [README](#readme)
  - [ToDo](#todo)
  - [Object Naming guiding](#object-naming-guiding)
  - [Database](#database)

## ToDo

Users

- [-] Add roles
  - [-] Adding test to check import.sql and Entity models are working as expected

- [-] Add register
  - [-] Search roles in JWTFilter
  - [-] Check protection by different roles, for example /add-product, /auth/users/all

- [X] use docker Postgres containers for tests

- [current] Pass Hashing to user module so the check happen inside this module

- [ ] Add module products
  - [X] Add product
  - [ ] Get by ID
  - [ ] Update
  - [ ] Delete
  - [ ] Get products by page
  - [ ] Add image

- [ ] Understand mapping and find out if there is a simpler way.

<!--
-->

## Object Naming guiding

For Input ports, In this case objects which come or go in the *web* layer

- RDto (Request Data Transfer Object)
- RrODto (Request Response Data Transfer object)

For Output ports, In this case objects which come or go in the *database* layer

- QDto (Query Data Transfer Object)
- QrDto (Query Response Data Transfer Object)

## Database

Crear contenedor

```shell
#postgres:14-alpine3.16

docker run --name jab_db --rm \
  -p 5001:5432 \
  -e POSTGRES_DB=jab_db_test \
  -e POSTGRES_USER=jab_db_user \
  -e POSTGRES_PASSWORD=jab_db_pass \
  -d postgres:14-alpine3.16
```

Conectar a db

```shell
psql -h 127.0.0.1 -p 5001 -U jab_db_user -d jab_db_test
```
