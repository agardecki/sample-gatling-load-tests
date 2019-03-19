package pl.piomin.services.gatling.scenario

import io.gatling.core.Predef.{exec, pace, _}
import io.gatling.http.Predef._
import scala.concurrent.duration._

object PersonScenario {

  val postPerson = exec(
    http("POST /persons")
      .post("/persons")
      .body(StringBody("""{"firstName":"${firstName}","lastName":"${lastName}","birthDate":"1980-01-01", "address": {"country":"pl","city":"Warsaw","street":"${street}","postalCode":"02-200","houseNo":${houseNo}}}"""))
      .check(status.is(200))
      .check(jsonPath("$.id").saveAs("personId"))
  )
    .pause(1 second, 4 seconds)

  val getPerson = exec(
    http("GET /persons/{personId}")
      .get("/persons/${personId}")
      .check(status.is(200)))

    .pause(1 second, 4 seconds)

  val getPersons = exec(
    http("GET /persons")
      .get("/persons")
      .check(status.is(200))
      .check(jsonPath("$.id").count.saveAs("personCounts"))
  )
    .pause(1 second, 4 seconds)

  val printPersonsCount = exec { session =>
    println("personCounts: " + session("personCounts").as[String])
    session
  }

  val deletePerson = exec(
    http("DELETE /persons/{personId}")
      .delete("/persons/${personId}")
      .check(status.is(200)))
    .pause(1 second, 4 seconds)

  val personFeeder = csv("person.csv").circular

  val scnPostPersonLoop = scenario("Post person in forever loop")
    .feed(personFeeder)
    .forever(
      exec(postPerson.exitHereIfFailed, getPerson, deletePerson, getPersons)
      //        exec(postPerson).exec(getPerson).exec(deletePerson).exec(getPersons)
    )

  val scnPostPersonLoopWithPace = scenario("Post person in forever loop")
    .feed(personFeeder)
    .forever(
      pace(5 seconds).exitBlockOnFail {
        exec(postPerson.exitHereIfFailed, getPerson, deletePerson, getPersons)
      }
    )

  val scnPostPersonRepeat1000 = scenario("Post person in loop")
    .feed(personFeeder)
    .repeat(1000) {
      exec(postPerson.exitHereIfFailed, getPerson, deletePerson, getPersons)
    }
}