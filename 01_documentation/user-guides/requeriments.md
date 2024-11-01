# Requirements

Here will see how to setup previos things to be able to run the project.

## Database

The project requires a Postgres instance that we can setup with the following
docker command.

```shell
docker run --name jab_db --rm \
  -p 5001:5432 \
  -e POSTGRES_DB=jab_db_test \
  -e POSTGRES_USER=jab_db_user \
  -e POSTGRES_PASSWORD=jab_db_pass \
  -d postgres:14-alpine3.16
```

To check if the instance was created we can connect to it using.

```shell
psql -h 127.0.0.1 -p 5001 -U jab_db_user -d jab_db_test
```

## Start project

Inside of adapter folder run.

```shell
mvn spring-boot:run
```
