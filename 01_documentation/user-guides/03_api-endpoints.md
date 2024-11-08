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
    - [Find by id](#find-by-id)
    - [Delete by id](#delete-by-id)
    - [Update by id](#update-by-id)
    - [Product image](#product-image)
    - [Find by page](#find-by-page)
    - [Upload image](#upload-image)
    - [See image](#see-image)
    - [delete image](#delete-image)
  - [Quick notes](#quick-notes)

## Auth

### Login

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"username":"mario2","password":"mario2p"}' \
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
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3MzA4NTM2NTEsImV4cCI6MTczMDg1NDg1MX0.WB-C4OCFNAKKzT8MO6Yi1glm2iBR_vZ_p_aQoP57o-k" \
  http://localhost:8080/auth/is-logged
```

### check-user-role

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3MzA4NTM2NTEsImV4cCI6MTczMDg1NDg1MX0.WB-C4OCFNAKKzT8MO6Yi1glm2iBR_vZ_p_aQoP57o-k" \
  http://localhost:8080/auth/check-user-role
```

### check-product-role

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3MzA4NTM2NTEsImV4cCI6MTczMDg1NDg1MX0.WB-C4OCFNAKKzT8MO6Yi1glm2iBR_vZ_p_aQoP57o-k" \
  http://localhost:8080/auth/check-product-role
```

____

## Products

### Save

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"name":"trompo1","price":10.10, "amount": 10, "description": "Trompo numero 1", "idOwner": -99}' \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3MzA4NTM2NTEsImV4cCI6MTczMDg1NDg1MX0.WB-C4OCFNAKKzT8MO6Yi1glm2iBR_vZ_p_aQoP57o-k" \
  http://localhost:8080/products
```

### Find by id

```bash
curl -i -X GET \
  --header "Content-Type: application/json" \
  http://localhost:8080/products/1
```

### Delete by id

```bash
curl -X DELETE -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3MzA4NTM2NTEsImV4cCI6MTczMDg1NDg1MX0.WB-C4OCFNAKKzT8MO6Yi1glm2iBR_vZ_p_aQoP57o-k" \
  http://localhost:8080/products/1
```

### Update by id

```bash
curl -X PUT -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3MzA4NTM2NTEsImV4cCI6MTczMDg1NDg1MX0.WB-C4OCFNAKKzT8MO6Yi1glm2iBR_vZ_p_aQoP57o-k" \
  --data '{ "description": "Trompo numero 1111" }' \
  http://localhost:8080/products/1
```

### Product image

### Find by page

```shell
curl -X GET -i \
  --header "Content-Type: application/json" \
  "http://localhost:8080/products/page?page=1&size=5"
```

### Upload image

```shell
curl -v \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3MzA4NTM2NTEsImV4cCI6MTczMDg1NDg1MX0.WB-C4OCFNAKKzT8MO6Yi1glm2iBR_vZ_p_aQoP57o-k" \
  -F image=@./temporal/small_blue.png  \
  http://localhost:8080/products/1/image
```

### See image

```shell
curl -i -X GET http://localhost:8080/products/image/1
```

### delete image

```shell
curl -i -X DELETE \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiItOTkiLCJpYXQiOjE3MzA4NTM2NTEsImV4cCI6MTczMDg1NDg1MX0.WB-C4OCFNAKKzT8MO6Yi1glm2iBR_vZ_p_aQoP57o-k" \
  http://localhost:8080/products/image/1
```

____

<!--

######################################################
######################################################
######################################################

-->

## Quick notes

Change from Model to DTO, because since the point of view of Domain the object that
comes and goes to the adapter is just to transport the info, what it goes more
with the definition od DTO, and it keeps things more simple to understand
(check the `Figure 9.3` of `Get Your Hands Dirty on Clean Architecture`)

- Add definitions of Service, Helper, Util, Adapter, API, Dto, etc.
