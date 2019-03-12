package pl.piomin.services.gatling.simulation

import io.gatling.core.Predef.{Simulation, rampUsers, atOnceUsers}
import pl.piomin.services.gatling.config.Http
import pl.piomin.services.gatling.scenario.PersonScenario

import scala.concurrent.duration._

class PersonSimulation extends Simulation {
  setUp(PersonScenario.scnPostPersonRepeat1000.inject(atOnceUsers(10))).maxDuration(1 minutes).protocols(Http.httpProtocol)
}
