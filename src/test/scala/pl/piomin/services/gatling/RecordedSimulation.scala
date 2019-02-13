package pl.piomin.services.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8090")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("PostmanRuntime/7.1.1")

  val headers = Map(
    "Content-Type" -> "application/json"
  )


  val scn = scenario("RecordedSimulation")
    .exec(http("POST /persons")
      .post("/persons")
      .headers(headers)
      .body(RawFileBody("gatling/bodies/RecordedSimulation_0001_request.txt"))
      .check(jsonPath("$.id").saveAs("personId")))
    .pause(5)
    .exec(http("GET /persons/{personId}")
      .get("/persons/${personId}")
      .headers(headers))
    .pause(5)
    .exec(http("DELETE /persons/{personId}")
      .delete("/persons/${personId}")
      .headers(headers))


  val scn_r = scenario("RecordedSimulation")
    .repeat(1000) {
      exec(http("POST /persons")
        .post("/persons")
        .headers(headers)
        .body(RawFileBody("gatling/bodies/RecordedSimulation_0001_request.txt"))
        .check(jsonPath("$.id").saveAs("personId")))
      .pause(5)
      .exec(http("GET /persons/{personId}")
        .get("/persons/${personId}")
        .headers(headers))
      .pause(5)
      .exec(http("DELETE /persons/{personId}")
        .delete("/persons/${personId}")
        .headers(headers))
    }

  //	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
  //	setUp(scn.inject(rampUsers(10) during (10 seconds))).maxDuration(1 minutes).protocols(httpProtocol)
  //	setUp(scn_r.inject(atOnceUsers(30))).maxDuration(1 minutes).protocols(httpProtocol)

  setUp(scn_r.inject(rampUsers(10) during (10 seconds))).maxDuration(1 minutes).protocols(httpProtocol)
}