package sims.bolt.alltables

import actions.bolt.BoltDataActions
import core.BaseSimulation
import feeds.bolt.BoltFeed
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.SimConfig

class WriteUpdateSimulation extends BaseSimulation {

  val simGroup = "bolt"

  val boltAppConf = new SimConfig(conf, simGroup, "boltData")
  val boltUpdateAppConf = new SimConfig(conf, simGroup, "boltDataUpdate")
  val whoAmIAppConf = new SimConfig(conf, simGroup, "whoAmI")
  val whoAmIUpdateAppConf = new SimConfig(conf, simGroup, "whoAmIUpdate")

  val boltActions = new BoltDataActions(cass.getSession)

  val boltWriteFeed = new BoltFeed().write
  val boltWriteScenario = scenario("BoltData")
      .feed(boltWriteFeed)
      .exec(boltActions.writeBoltData(boltAppConf))

  // start WhoAmI Reads
  val whoAmIWriteFeed = new BoltFeed().getWhoAmIData
  val whoAmIWriteScenario = scenario("WhoAmI")
      .feed(whoAmIWriteFeed)
      .exec(boltActions.writeWhoAmI(whoAmIAppConf))


  setUp(
    buildRampConstScenario(boltWriteScenario, boltAppConf),
    buildRampConstScenario(whoAmIWriteScenario, whoAmIAppConf)
  ).assertions(
    //global.responseTime.max.lessThan(10),
    //global.successfulRequests.percent.greaterThan(95)
  ).protocols(cqlConfig)
}