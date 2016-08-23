package sims.bolt.whoami

import actions.bolt.BoltDataActions
import core.BaseSimulation
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.{FetchBaseData, SimConfig}

class ReadSimulation extends BaseSimulation {

  val simGroup = "bolt"
  val simName = "whoAmI"

  val appConf = new SimConfig(conf, simGroup, simName)

  val boltActions = new BoltDataActions(cass.getSession)

  // create base data file
  new FetchBaseData(appConf, cass).createBaseDataCsv()


  val feederFile = getDataPath(appConf)
  val csvFeeder = csv(feederFile).random

  val readScenario = scenario("WhoAmIRead")
      .feed(csvFeeder)
      .exec(boltActions.readWhoAmI(appConf))

  setUp(

    buildRampConstScenario(readScenario, appConf)

  ).assertions(
    //global.responseTime.max.lessThan(10),
    //global.successfulRequests.percent.greaterThan(95)
  ).protocols(cqlConfig)
}