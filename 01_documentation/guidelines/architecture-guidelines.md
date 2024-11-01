# Architecture Guidelines

## Resources

For this code I'm using as base the following resources:

1, The book **Get Your Hands Dirty on Clean Architecture**, here I took mainly
the hexagonal architecture guides and other topics like mapping.

2, The book **Spring in Action, Sixth Edition**, here I took the bases of spring
like the management of beans, profiles, use of libraries as lombok, mapstruct.
And the TDD practices.

## Rules

1, The adapter must only access to the domainX.visible.port interfaces.

2, The libraries must be divided in two packages, one called visible and other
call internal, to identify visually what can be acceded by other and what not.

3, The inter module dependencies, should keep the less posible and/or avoided,
but if is required so, for example, dependencies between users and products must
be happen throw the module commons and only throw interfaces.

## Definitions

watching the consumed resources it really doesn't explain the role of each type of
class so I will define here some of them, to be clear what do each module, package
and type of class do.

Modules (project-jar)

- **Adapter**: Its responsibility of this module is to deal with the outside world,
  that includes everything that comes form the user as requests, format responses,
  json, etc.. Also persistence including database, cache, etc. Also for convenience
  I deal with authentication in this module.

- **DomainX**: For example, user, product, shopping, etc, these are a representation
  of a business domain, and we can access to this logic throw interfaces in their
  visible package.

- **common**: for inter-module-dependency, all modules depend on this module,
  so for the sake of the simplicity, this module should have only simple; dtos,
  IMD interfaces and utils.

Adapter packages

- **common**: Implementations that are includes or use all domain,
  for example, authentication is used by all domain like users or products, the
  same for error handling.

- **domainX**: Implementations that domainX (user, product, etc)
  requieres to deal with the outside world.

- **domainX.inWeb**: The "inWeb" is a combination of; "in" that refers to the
  driving adapters, and "web" that refers that the communication is via web,
  for example, for a CLI adapter could be "inCLI".

- **domainX.outDB**: The "outDB" is a combination of; "out" that refers to the
  driven adapters, and "db" that refers that the communication is via a traditional
  database, in my case a relational sql database using jpa.

- **domainX.utils**: are common tasks between both adapters, in my case mappers.

DomainX Packages

- **Internal and Visible**: These packages are inside of the domain modules,
  they only are a visual mark of which part is accessible by other modules,
  for example, adapter module and other domain modules.

- **visible.port**: These packages define the Dtos and interfaces so the domain can
  speak with the outside world, and would be the responsibility of the module
  adapter to implement and use them.

- **visible.port.in**: It's all the input that other system triggers, for example, a web
  request and response, in my case is just an api web restful json, but it could
  be an CLI, UI, etc.

- **visible.port.out**: It's all the output that our system triggers, for example, is my
  case is the SQL sended to postgres, but it could be an Email service, message
  broker, etc.

- **internal.service**: It's where I execute my logic business logic, which can
  be helped by helpers, but id is simple also the service could implement it.

- **internal.helper**: Here I have the helpers, that helps with the business logic.

Clases

- **DbAdapter**: These classes implements the interfaces in port.out, the generated
  data or events the app makes, in my case the database, but it could be an Email
  service, Message broker, etc.

- **Service**: This type of classes execute business logic, and dependending if the
  business is not complex also it implements it, but if the business turns complex
  with the evolutions so this can make use of Helpers.

- **Helper**: These classes implement business logic, to relive load to services,
  and they are considered par of the internal of domains.

- **Util**: Utils are classes without a state, all their methods are static, and of
  preference are idempotence.

- **Dto**: (Data Transfer Object) these classes hold just information to be passed
  from one module-jar to another, and they should be a few ones to keep simple
  the communication between modules, so when we try to undestand the whole less
  elements are involved.

- **IMD**: (Inter Module Dependency) these are always interfaces that use dtos in
  the common package, this because keep the most simple way the communication
  between modules, that it should be avoided.
