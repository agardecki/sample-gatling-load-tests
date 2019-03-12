## Perfomance Testing with Gatling

Detailed description can be found here: [Perfomance Testing with Gatling](https://piotrminkowski.wordpress.com/2018/01/18/perfomance-testing-with-gatling/)

### Building app
`gradlew bootJar`

### Running app
#### With H2
`gradlew bootRunH2`
#### With Postgres
`gradlew bootRunPostgres`

Before this please run Postgres database

`docker run -d --name postgres -e POSTGRES_DB=gatling -e POSTGRES_USER=gatling -e POSTGRES_PASSWORD=gatling123 -p 5432:5432 postgres`


### Running tests
`gradlew loadTest`

`gradlew loadTest`

![alt text](https://github.com/agardecki/sample-gatling-load-tests/tree/master/img/gradle-tasks.png "Gradle tasks")
