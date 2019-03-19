import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import pl.piomin.services.gatling.simulation.PersonSimulation

object GatlingTestRunner {
  def main(args: Array[String]) {
    //    val config = PuppetGatlingConfig.initialize()
    // This sets the class for the simulation we want to run.
    val simClass = classOf[PersonSimulation].getName

    val props = new GatlingPropertiesBuilder
//    props.binariesDirectory("target/test-classes")
    props.simulationClass(simClass)
    //    props.runDescription(config.runDescription)
    //    props.outputDirectoryBaseName(config.simulationId)


    // This checks the values set in gatling_kickoff.rb
    //    if (sys.env("PUPPET_GATLING_REPORTS_ONLY") == "true") {
    //      props.reportsOnly(sys.env("PUPPET_GATLING_REPORTS_TARGET"))
    //    }


    Gatling.fromMap(props.build)
  }
}