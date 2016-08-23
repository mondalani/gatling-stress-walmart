package sims.rollups

import actions.rollup.RollupActions
import core.BaseSimulation
import feeds.rollup.RollupFeed
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.SimConfig

class WriteSimulation extends BaseSimulation {

  val simGroup = "rollups"
  val simName = "write"

  val appConf = new SimConfig(conf, simGroup, simName)

  val rollupActions = new RollupActions(cass.getSession, appConf, simGroup, simName)

  val writeFeed = new RollupFeed().writeMinRollups
  val writeScenario = scenario("RollupWrite")
      .feed(writeFeed)
      .exec(rollupActions.insertRollupData)

  setUp(

    buildRampConstScenario(writeScenario, appConf)

  ).assertions(
    //        global.responseTime.percentile4.lessThan()
    //        global.responseTime.max.lessThan(10),
    //        global.successfulRequests.percent.greaterThan(95)
  ).protocols(cqlConfig)

}