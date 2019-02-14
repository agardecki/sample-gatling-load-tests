## Perfomance Testing with Gatling

Detailed description can be found here: [Perfomance Testing with Gatling](https://piotrminkowski.wordpress.com/2018/01/18/perfomance-testing-with-gatling/)

### Running database
docker run -d --name postgres -e POSTGRES_DB=gatling -e POSTGRES_USER=gatling -e POSTGRES_PASSWORD=gatling123 -p 5432:5432 postgres

### Building app
gadlew bootJar

### Running app
java -jar build/libs/sample-load-test-gatling.jar

### Running tests
gradlew loadTest


gradlew loadTest2
