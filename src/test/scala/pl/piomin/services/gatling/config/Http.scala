package pl.piomin.services.gatling.config

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Http {
  val httpProtocol = http
    .baseUrl("http://localhost:8090")
    .acceptHeader("*/*")
    .headers(Headers.applicationJson)
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("PostmanRuntime/7.1.1")
}
