package sims.bolt.alltables

import actions.bolt.BoltDataActions
import core.BaseSimulation
import feeds.bolt.BoltFeed
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.SimConfig

class WriteSimulation extends BaseSimulation {

  val simGroup = "bolt"
  val boltSimName = "boltData"
  val whoAmISimName = "whoAmI"

  val boltAppConf = new SimConfig(conf, simGroup, boltSimName)
  val whoAmIappConf = new SimConfig(conf, simGroup, whoAmISimName)

  val boltActions = new BoltDataActions(cass.getSession)


  val boltWriteFeed = new BoltFeed().write
  val boltWriteScenario = scenario("BoltData")
      .feed(boltWriteFeed)
      .exec(boltActions.writeBoltData(boltAppConf))

  // start WhoAmI Reads
  val whoAmIWriteFeed = new BoltFeed().getWhoAmIData
  val whoAmIWriteScenario = scenario("WhoAmI")
      .feed(whoAmIWriteFeed)
      .exec(boltActions.writeWhoAmI(whoAmIappConf))


  setUp(
    buildRampConstScenario(boltWriteScenario, boltAppConf),
    buildRampConstScenario(whoAmIWriteScenario, whoAmIappConf)
  ).assertions(
    //global.responseTime.max.lessThan(10),
    //global.successfulRequests.percent.greaterThan(95)
  ).protocols(cqlConfig)
}