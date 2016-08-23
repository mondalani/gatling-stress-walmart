package sims.bolt.alltables

import actions.bolt.BoltDataActions
import core.BaseSimulation
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.{FetchBaseData, SimConfig}


class ReadOnlySimulation extends BaseSimulation {

  val simGroup = "bolt"
  val boltSimName = "boltData"
  val whoAmISimName = "whoAmI"

  val boltAppConf = new SimConfig(conf, simGroup, boltSimName)
  val whoAmIappConf = new SimConfig(conf, simGroup, whoAmISimName)

  val boltActions = new BoltDataActions(cass.getSession)

  // start Bolt Reads
  new FetchBaseData(boltAppConf, cass).createBaseDataCsv()


  val boltFeederFile = getDataPath(boltAppConf)
  val boltCsvFeeder = csv(boltFeederFile).random

  val boltReadScenario = scenario("ReadBoltData")
      .feed(boltCsvFeeder)
      .exec(boltActions.readBoltData(boltAppConf))


  // start WhoAmI Reads
  new FetchBaseData(whoAmIappConf, cass).createBaseDataCsv()

  val whoAmIfeederFile = getDataPath(whoAmIappConf)
  val whoAmIcsvFeeder = csv(whoAmIfeederFile).random

  val whoAmIReadScenario = scenario("WhoAmIRead")
      .feed(whoAmIcsvFeeder)
      .exec(boltActions.readWhoAmI(whoAmIappConf))

  setUp(

    //        buildRampConstScenario(boltReadScenario, boltAppConf),
    buildRampConstScenario(whoAmIReadScenario, whoAmIappConf)

  ).protocols(cqlConfig)
}