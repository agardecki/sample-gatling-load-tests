package pl.piomin.services.gatling.scenario

import io.gatling.core.Predef.{exec, _}
import io.gatling.http.Predef._

object PersonScenario {

  val postPerson = exec(
    http("POST /persons")
      .post("/persons")
      .body(StringBody("""{"firstName":"${firstName}","lastName":"{lastName}","birthDate":"1980-01-01", "address": {"country":"pl","city":"Warsaw","street":"${street}","postalCode":"02-200","houseNo":${houseNo}}}"""))
      .check(status.is(200))
  ).pause(5)

  val getPerson = exec(
    http("GET /persons/{personId}")
      .get("/persons/${personId}")
      .check(status.is(200)))
    .pause(5)

  val getPersons = exec(
    http("GET /persons")
      .get("/persons")
      .check(status.is(200)))
    .pause(5)

  val deletePerson = exec(
    http("DELETE /persons/{personId}")
      .delete("/persons/${personId}")
      .check(status.is(200)))
    .pause(5)

  val personFeeder = csv("person.csv").circular

  val scnPostPersonLoop = scenario("Post person in forever loop")
    .feed(personFeeder)
    .forever(
      exec(postPerson, getPerson, deletePerson, getPersons)
//        exec(postPerson).exec( getPerson).exec(deletePerson).exec(getPersons)
    )

  val scnPostPersonRepeat1000 = scenario("Post person in loop")
    .feed(personFeeder)
    .repeat(1000) {
      exec(postPerson, getPerson, deletePerson, getPersons)
    }
}