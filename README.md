# README

- [README](#readme)
  - [Documentation guide](#documentation-guide)
  - [ToDo](#todo)

## Documentation guide

I'm using:

- Hexagonal architecture
- Web API Restful Json
- Multi Modules-Jars
- Spring boot
- Postgres
- Spring jpa
- Spring security
- JWT (Stateless)
- Saving boilerplate code with (lombok, mapstruct, hibernate-validator)
- TDD (Mockito, WebMvcTest, DataJpaTest)
- E2E test (SpringBootTest and TestContainers)

To know about how to setup and/or use the application, see *01_documentation/user-guides*.

To understand how the application works so you can make some modifications, see *01_documentation/guidelines*.

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
  - [ ] Show error to user when hit with two slashes ex. '<http://localhost:8080//products>'

- [current] Add module products
  - [X] Add product
  - [X] Get by ID
  - [X] Delete
  - [X] Update
  - [X] Get products by page
  - [X] Product images
    - [X] Upload
    - [X] Show image by id image
    - [X] Get images by product id
    - [X] delete image by id
    - [X] check delete images when product is deleted
  - [X] don't delete, mark as deleted

- [X] Understand mapping and find out if there is a simpler way.

- [Current] Documentation
  - [X] Include Architecture diagram to repository
  - [Current] Sincronice Diagram with project folder structure
    - [X] Separate Request/Response objects and keep just DTO in user domain module
    - [X] In domain modules make changes to see clear what part is public and what one is private
    - [X] remove dependencies of adapter.user to anything outside of user.port
    - [X] Export diagram from .drawio to .png (and that it looks fine)
    - [Current] Sync diagram architecture and code.
    - [ ] **IMPORTANT** Add ArchUnit tests, to check that the adapter just access to the public part of the domain module
    - [ ] Check what other diagrams should be useful

- [ ] Create template from this project
  - [ ] Create essay
    - [ ] Explain Architecture
    - [ ] Explain how it works
    - [ ] Explain what is required
    - [ ] Explain how to use
    - [ ] Start a new project using this template following the steps of 'how to use'

- [ ] Frontend
  - [ ] Create angular project

____

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
