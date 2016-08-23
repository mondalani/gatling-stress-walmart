package sims.bolt.whoami

import actions.bolt.WhoAmIActions
import core.BaseSimulation
import feeds.bolt.BoltFeed
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.SimConfig

class UpdateSimulation extends BaseSimulation {

  val simGroup = "bolt"
  val simName = "whoAmI"

  val appConf = new SimConfig(conf, simGroup, simName)

  val whoAmIActions = new WhoAmIActions(cass, appConf)
  whoAmIActions.fetchKeys

  val writeFeed = new BoltFeed().getWhoAmIData


  val writeScenario = scenario("WhoAmIWrite")
      .feed(writeFeed)
      .exec(whoAmIActions.writeWhoAmI(appConf))

  setUp(
    buildRampConstScenario(writeScenario, appConf)
  ).assertions(
    //        global.responseTime.percentile4.lessThan()
    //        global.responseTime.max.lessThan(10),
    //        global.successfulRequests.percent.greaterThan(95)
  ).protocols(cqlConfig)
}