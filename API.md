# API

- [API](#api)
  - [Auth](#auth)
    - [Login](#login)
    - [Register](#register)
    - [is-logged](#is-logged)
    - [check-user-role](#check-user-role)
    - [check-product-role](#check-product-role)
  - [Products](#products)
    - [Save](#save)

## Auth

### Login

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"username":"mario3","password":"mario3p"}' \
  http://localhost:8080/auth/create-token
```

### Register

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"username":"mario3","password":"mario3p", "email":"mario3@email.com"}' \
  http://localhost:8080/auth/register
```

### is-logged

With this route we can check if the user is logged, any registered and with
valid token user should get an 200 response, and with no token or invalid
token should get a 403

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzI5Mjk5MzgwLCJleHAiOjE3MjkyOTk2ODB9.38rtGlTEiNgx2omZvAdPQLmb-wy90JM3I7NVpzzsWIk" \
  http://localhost:8080/auth/is-logged
```

### check-user-role

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzI5Mjk5MzgwLCJleHAiOjE3MjkyOTk2ODB9.38rtGlTEiNgx2omZvAdPQLmb-wy90JM3I7NVpzzsWIk" \
  http://localhost:8080/auth/check-user-role
```

### check-product-role

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzI5Mjk5MzgwLCJleHAiOjE3MjkyOTk2ODB9.38rtGlTEiNgx2omZvAdPQLmb-wy90JM3I7NVpzzsWIk" \
  http://localhost:8080/auth/check-product-role
```

## Products

### Save

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"name":"trompo1","price":111.11, "amount": 10, "description": "Trompo numero 1" }' \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItMTAwIiwiaWF0IjoxNzI5NTU4OTUwLCJleHAiOjE3Mjk1NjAxNTB9.nRj-vwifZA9JuB19o1Pt1KMl3YbfXgSoG0_ve1EOwtY" \
  http://localhost:8080/products
```
