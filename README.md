# README

- [README](#readme)
  - [ToDo](#todo)
  - [Object Naming guiding](#object-naming-guiding)
  - [Database](#database)
  - [Errors](#errors)
    - [Don't use Interfaces in Controllers or repositories](#dont-use-interfaces-in-controllers-or-repositories)
  - [API](#api)
    - [Auth](#auth)
      - [Login](#login)
      - [Register](#register)
      - [is-logged](#is-logged)
      - [check-user-role](#check-user-role)
      - [check-product-role](#check-product-role)
    - [Products](#products)
      - [Save](#save)
      - [Find by id](#find-by-id)
      - [Delete by id](#delete-by-id)
      - [Update by id](#update-by-id)
      - [Find by page](#find-by-page)
      - [Upload image](#upload-image)
      - [See image](#see-image)
  - [Quick notes](#quick-notes)

## ToDo

Users

- [X] Add roles
  - [X] Adding test to check import.sql and Entity models are working as expected

- [X] Add register
  - [X] Search roles in JWTFilter
  - [X] Check protection by different roles, for example /add-product, /auth/users/all

- [X] use docker Postgres containers for tests

- [X] Pass Hashing to user module so the check happen inside this module

- [ ] Fixes
  - [ ] Show error to user when hit with two slashes ex. 'http://localhost:8080//products'

- [current] Add module products
  - [X] Add product
  - [X] Get by ID
  - [X] Delete
  - [X] Update
  - [X] Get products by page
  - [Current] Product images
    - [X] Upload
    - [X] Show image by id image
    - [Current] Get images by product id
    - [ ] delete image by id
    - [ ] check delete images when product is deleted

- [ ] Understand mapping and find out if there is a simpler way.

- [ ] Documentation
  - [ ] Include Architecture diagram to repository

- [ ] Create template from this project
  - [ ] Create essay
    - [ ] Explain Architecture
    - [ ] Explain how it works
    - [ ] Explain what is required
    - [ ] Explain how to use
    - [ ] Start a new project using this template following the steps of 'how to use'

- [ ] Frontend
  - [ ] Create angular project

<!--

########################################################################
########################################################################
########################################################################

-->
____

<!--

########################################################################
########################################################################
########################################################################

-->

## Object Naming guiding

For Input ports, In this case objects which come or go in the *web* layer

- RDto (Request Data Transfer Object)
- RrODto (Request Response Data Transfer object)

For Output ports, In this case objects which come or go in the *database* layer

- QDto (Query Data Transfer Object)
- QrDto (Query Response Data Transfer Object)

____

<!--

########################################################################
########################################################################
########################################################################

-->

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

____

<!--

########################################################################
########################################################################
########################################################################

-->

## Errors

### Don't use Interfaces in Controllers or repositories

Spring use default classes (created with no-args-constructor) for its internals,
so if we use interfaces and can'r create the Request/Response/Entity will throw
this error.

```r
Could not resolve parameter [1] in public daj.product.port.in.dto.ProductModel daj.adapter.product.inWeb.ProductWriterController.update(java.lang.Integer,daj.product.port.in.dto.ProductModel): Type definition error: [simple type, class daj.product.port.in.dto.ProductModel]
```

<!--

########################################################################
########################################################################
########################################################################

-->

____

## API

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

____

### Products

#### Save

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"name":"trompo1","price":10.10, "amount": 10, "description": "Trompo numero 1" }' \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3Mjk5NzI2MDEsImV4cCI6MTcyOTk3MzgwMX0.rROTaMKMlKp-tJdQD8hSE9VzXpeJ7hDDiTlgeZrKh5E" \
  http://localhost:8080/products
```

#### Find by id

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  http://localhost:8080/products/1
```

#### Delete by id

```bash
curl -X DELETE -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3Mjk2NDE0MDQsImV4cCI6MTcyOTY0MjYwNH0.9hdlYD1QlqzI8ZOspkz3ZpVSo80CeexJ6SU1-KPMu_8" \
  http://localhost:8080/products/1
```

#### Update by id

```bash
curl -X PUT -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3Mjk2NDE0MDQsImV4cCI6MTcyOTY0MjYwNH0.9hdlYD1QlqzI8ZOspkz3ZpVSo80CeexJ6SU1-KPMu_8" \
  --data '{ "description": "Trompo numero 1111" }' \
  http://localhost:8080/products/1
```

#### Find by page

```shell
curl -X GET -i \
  --header "Content-Type: application/json" \
  "http://localhost:8080/products/page?page=1&size=5"
```

#### Upload image

```shell
curl -v \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3Mjk4MTA3MDUsImV4cCI6MTcyOTgxMTkwNX0.ZP591UGBK3mCg5wC8qG_5tYyL52SXiHMN6RJWtoQ9yE" \
  -F image=@./temporal/small_blue.png  \
  http://localhost:8080/products/1/image
```

#### See image

```shell
curl -X -i GET http://localhost:8080/products/image/1
```

____

<!--

########################################################################
########################################################################
########################################################################

-->

## Quick notes
