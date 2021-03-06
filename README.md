[![Build Status](https://travis-ci.org/zalando-incubator/api-discovery.svg?branch=master)](https://travis-ci.org/zalando-incubator/api-discovery)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/af767a162ce24df0b22d1e4cb70a6556)](https://www.codacy.com/app/felix-mueller-berlin/api-discovery?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=zalando-incubator/api-discovery&amp;utm_campaign=Badge_Grade)

# API Discovery Solution for Microservice Architectures

### Purpose

In Microservice architectures many services and APIs are built. Over time it becomes quite hard for each developer to keep track which APIs exist and what services an API offers. As a solution for this problem, this API Discovery solution offers easy to implement [auto discovery](https://github.com/zalando-incubator/api-discovery/tree/master/crawler) of available APIs and gathers all found API specifications in one central [storage](https://github.com/zalando-incubator/api-discovery/tree/master/storage) system. It's built upon OpenAPI specifications (formerly Swagger). It also comes with an adapted [Swagger UI](swagger-ui/) to display all APIs.

**IMPORTANT** Currently the crawler is still depending on an application registry, called [Kio](https://github.com/zalando-stups/kio). From this application registry all services, which potentially have an API definition, are crawled. In near future, this will change and retrieving of crawlable services will be configurable. By this you can adapt API Discovery to your needs. Please contribute if you have requirements or ideas to [this issue](https://github.com/zalando-incubator/api-discovery/issues/2).


### Architecture Overview

    +------------+     +---------+     +---------+     +------+
    | Swagger UI +---->+ Storage +<----+ Crawler +---->+ APIs |
    +------------+     +---------+     +---------+     +------+


### How does it work?

The crawler constantly fetches a list of all available services from an application registry
(currently [Kio](https://github.com/zalando-stups/kio)) and then tries to retrieve an API definition per service.
For that, it first crawls `/.well-known/schema-discovery` which should respond with payload like:

  ```json
  {
    "schema_url": "/swagger.json",
    "schema_type": "swagger-2.0",
    "ui_url": "/ui/"
  }
  ```

`schema_url` points to an API definition endpoint. The API definition can be provided either in JSON or YAML format.
After the crawler loaded the definition it tries to store it in the [storage](storage/) system. An adapted
[Swagger UI](swagger-ui/) displays all API definitions which were stored in this storage system.

Currently the `/.well-known/schema-discovery` endpoint and the endpoint provided by `schema_url` are requested
with OAuth `uid` token by the crawler. By this services can secure their API definition if necessary or just ignore
the `uid` token.

### Features

- Auto-discovery of your APIs
- Basic UI support for auto-discovered APIs
- API Lifecycle Management Support (see [storage](storage/) for more information)


### Dependencies

- Java 8
- NPM

### Installation

Please refer to individual installation instructions of sub-projects for more detailed information and configuration parameters.


### Run locally
As prerequisite the [Python SCM Source Generator](https://github.com/zalando-stups/python-scm-source) should be in place:

```pip install --upgrade scm-source```

You can start the whole stack locally (Swagger UI and Storage incl. PostgreSQL DB and test data) by running:

```bash
./start.sh localhost
```

If your docker host is not your localhost (e.g. MacOS), replace localhost in the link with the docker machine host name or IP address:

```bash
docker-machine ip ${machine_name}
```

Swagger UI is accessible on <http://localhost:8080>. You can also access Storage API <http://localhost:8010/apps> and PostgreSQL database <postgresql://localhost:54321/apis> directly.


### Contributing

We are happy to accept contributions. First, take a look at our [contributing guidelines](CONTRIBUTING.md).

You can see our current status in [this task board](https://github.com/zalando-incubator/api-discovery/projects/1).


### TODO

Please check the [Issues Page](https://github.com/zalando-incubator/api-discovery/issues)
for contribution ideas.


### Contact

Feel free to contact one of the [maintainers](MAINTAINERS).


### License

MIT
