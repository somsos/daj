# README

- [README](#readme)
  - [ToDo](#todo)
  - [Object Naming guiding](#object-naming-guiding)
  - [Database](#database)
  - [API](#api)
    - [Auth](#auth)
      - [Login](#login)
      - [Register](#register)
      - [is-logged](#is-logged)
      - [check-user-role](#check-user-role)
      - [check-product-role](#check-product-role)
    - [Products](#products)
      - [Save](#save)

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

<!--

########################################################################
########################################################################
########################################################################

-->

## API

- [README](#readme)
  - [ToDo](#todo)
  - [Object Naming guiding](#object-naming-guiding)
  - [Database](#database)
  - [API](#api)
    - [Auth](#auth)
      - [Login](#login)
      - [Register](#register)
      - [is-logged](#is-logged)
      - [check-user-role](#check-user-role)
      - [check-product-role](#check-product-role)
    - [Products](#products)
      - [Save](#save)

### Auth

#### Login

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"username":"mario3","password":"mario3p"}' \
  http://localhost:8080/auth/create-token
```

#### Register

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"username":"mario3","password":"mario3p", "email":"mario3@email.com"}' \
  http://localhost:8080/auth/register
```

#### is-logged

With this route we can check if the user is logged, any registered and with
valid token user should get an 200 response, and with no token or invalid
token should get a 403

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzI5Mjk5MzgwLCJleHAiOjE3MjkyOTk2ODB9.38rtGlTEiNgx2omZvAdPQLmb-wy90JM3I7NVpzzsWIk" \
  http://localhost:8080/auth/is-logged
```

#### check-user-role

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzI5Mjk5MzgwLCJleHAiOjE3MjkyOTk2ODB9.38rtGlTEiNgx2omZvAdPQLmb-wy90JM3I7NVpzzsWIk" \
  http://localhost:8080/auth/check-user-role
```

#### check-product-role

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzI5Mjk5MzgwLCJleHAiOjE3MjkyOTk2ODB9.38rtGlTEiNgx2omZvAdPQLmb-wy90JM3I7NVpzzsWIk" \
  http://localhost:8080/auth/check-product-role
```

### Products

#### Save

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"name":"trompo1","price":111.11, "amount": 10, "description": "Trompo numero 1" }' \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItMTAwIiwiaWF0IjoxNzI5NTU4OTUwLCJleHAiOjE3Mjk1NjAxNTB9.nRj-vwifZA9JuB19o1Pt1KMl3YbfXgSoG0_ve1EOwtY" \
  http://localhost:8080/products
```
