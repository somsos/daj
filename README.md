# README

## ToDo

Users

- [-] Add roles
  - [-] Adding test to check import.sql and Entity models are working as expected
- [current] Add register
  - [ ] Search roles in JWTFilter
  - [ ] Check protection by different roles, for example /add-product, /auth/users/all
- [ ] Add Postgres for development and keep H2 for tests
- [ ] Understand mapping and find out if there is a simpler way.

<!--
-->

Products

- [ ] Add
- [ ] get by page
- [ ] get by id
- [ ] Add image

## Object Naming guiding

For Input ports, In this case objects which come or go in the *web* layer

- RDto (Request Data Transfer Object)
- RrODto (Request Response Data Transfer object)

For Output ports, In this case objects which come or go in the *database* layer

- QDto (Query Transfer Object)
- QrDto (Query Response Data Transfer Object)
