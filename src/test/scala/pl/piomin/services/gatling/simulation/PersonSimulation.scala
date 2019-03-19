package pl.piomin.services.gatling.simulation

import io.gatling.core.Predef._
import pl.piomin.services.gatling.config.Http
import pl.piomin.services.gatling.scenario.PersonScenario

import scala.concurrent.duration._

class PersonSimulation extends Simulation {
  setUp(PersonScenario.scnPostPersonLoop.inject(
//    rampUsers(10) during (10 seconds)
    heavisideUsers(20) during(2 minutes)
  )).maxDuration(1 minutes).protocols(Http.httpProtocol)
}
