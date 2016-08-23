package sims.rollups

import actions.rollup.RollupActions
import core.BaseSimulation
import feeds.rollup.RollupFeed
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.{FetchBaseData, SimConfig}

class ReadWriteSimulation extends BaseSimulation {

  val writeConfigSection = "rollup_write"
  val readConfigSection = "rollup_read"

  val simGroup = "rollup"
  val readSimName = "read"
  val writeSimName = "read"

  val readAppConf = new SimConfig(conf, simGroup, readSimName)
  val writeAppConf = new SimConfig(conf, simGroup, writeSimName)

  // start write...
  val rollupActions = new RollupActions(cass.getSession, readAppConf, simGroup, writeSimName)

  val writeFeed = new RollupFeed().writeMinRollups
  val writeScenario = scenario("RollupWrite")
      .feed(writeFeed)
      .exec(rollupActions.insertRollupData)
  // end write...


  // start read
  new FetchBaseData(readAppConf, cass).createBaseDataCsv()

  val readRollupActions = new RollupActions(cass.getSession, readAppConf, simGroup, readSimName)
  val feederFile = getDataPath(readAppConf)
  val csvFeeder = csv(feederFile).shuffle

  val readScenario = scenario("RollupRead")
      .feed(csvFeeder)
      .exec(readRollupActions.getByKey)

  // end read

  setUp(
    buildRampConstScenario(writeScenario, writeAppConf),
    buildRampConstScenario(readScenario, readAppConf)

  ).protocols(cqlConfig)

}