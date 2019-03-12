package pl.piomin.services.gatling.simulation.allInOneFile

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import pl.piomin.services.gatling.config.{Headers, Http}

import scala.concurrent.duration._


class RecordedSimulation extends Simulation {
  val scn = scenario("RecordedSimulation")
    .exec(http("POST /persons")
      .post("/persons")
      .headers(Headers.applicationJson)
      .body(RawFileBody("gatling/bodies/RecordedSimulation_0001_request.txt"))
      .check(jsonPath("$.id").saveAs("personId")))
    .pause(5)
    .exec(http("GET /persons/{personId}")
      .get("/persons/${personId}")
      .headers(Headers.applicationJson))
    .pause(5)
    .exec(http("DELETE /persons/{personId}")
      .delete("/persons/${personId}")
      .headers(Headers.applicationJson))


  val scn_r = scenario("RecordedSimulation")
    .repeat(1000) {
      exec(http("POST /persons")
        .post("/persons")
        .headers(Headers.applicationJson)
        .body(RawFileBody("gatling/bodies/RecordedSimulation_0001_request.txt"))
        .check(jsonPath("$.id").saveAs("personId")))
        .pause(5)
        .exec(http("GET /persons/{personId}")
          .get("/persons/${personId}")
          .headers(Headers.applicationJson))
        .pause(5)
        .exec(http("DELETE /persons/{personId}")
          .delete("/persons/${personId}")
          .headers(Headers.applicationJson))
    }

  //	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //	setUp(scn.inject(rampUsers(10) during (10 seconds))).maxDuration(1 minutes).protocols(httpProtocol)
  //	setUp(scn_r.inject(atOnceUsers(30))).maxDuration(1 minutes).protocols(httpProtocol)

  setUp(scn_r.inject(rampUsers(10) during (10 seconds))).maxDuration(1 minutes).protocols(Http.httpProtocol)
}