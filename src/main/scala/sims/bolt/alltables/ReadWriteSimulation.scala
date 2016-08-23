package sims.bolt.alltables

import actions.bolt.BoltDataActions
import core.BaseSimulation
import feeds.bolt.BoltFeed
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.{FetchBaseData, SimConfig}

class ReadWriteSimulation extends BaseSimulation {

  val simGroup = "bolt"
  val boltDataReadSimName = "boltData"
  val whoAmIWriteSimName = "whoAmI"

  val boltReadAppConf = new SimConfig(conf, simGroup, boltDataReadSimName)
  val whoAmIappConf = new SimConfig(conf, simGroup, whoAmIWriteSimName)

  val boltActions = new BoltDataActions(cass.getSession)
  val boltFeed = new BoltFeed


  // bolt write

  val boltWriteFeed = boltFeed.write
  val boltWriteScenario = scenario("BoltData")
      .feed(boltWriteFeed)
      .exec(boltActions.writeBoltData(boltReadAppConf))


  // bolt read
  new FetchBaseData(boltReadAppConf, cass).createBaseDataCsv()

  val boltFeederFile = getDataPath(boltReadAppConf)
  val boltCsvFeeder = csv(boltFeederFile).random

  val boltReadScenario = scenario("ReadBoltData")
      .feed(boltCsvFeeder)
      .exec(boltActions.readBoltData(boltReadAppConf))


  // start WhoAmI Reads
  val whoAmIWriteFeed = boltFeed.getWhoAmIData
  val whoAmIWriteScenario = scenario("WhoAmI")
      .feed(whoAmIWriteFeed)
      .exec(boltActions.writeWhoAmI(whoAmIappConf))


  setUp(

    buildRampConstScenario(boltWriteScenario, boltReadAppConf),
    buildRampConstScenario(boltReadScenario, boltReadAppConf),

    buildRampConstScenario(whoAmIWriteScenario, whoAmIappConf)

  ).assertions(
    //global.responseTime.max.lessThan(10),
    //global.successfulRequests.percent.greaterThan(95)
  ).protocols(cqlConfig)
}