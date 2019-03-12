package pl.piomin.services.gatling.simulation

import io.gatling.core.Predef._
import pl.piomin.services.gatling.config.Http
import pl.piomin.services.gatling.scenario.PersonScenario

import scala.concurrent.duration._

class PersonSimulation extends Simulation {
  setUp(PersonScenario.scnPostPersonRepeat1000.inject(rampUsers(10) during (10 seconds))).maxDuration(1 minutes).protocols(Http.httpProtocol)
}
