package sims.rollups

import actions.rollup.RollupActions
import core.BaseSimulation
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.{FetchBaseData, SimConfig}

import scala.concurrent.duration.Duration

class ReadSimulation extends BaseSimulation {

  val simGroup = "rollups"
  val simName = "read"

  val appConf = new SimConfig(conf, simGroup, simName)

  // create base data file
  new FetchBaseData(appConf, cass).createBaseDataCsv()

  val rollupActions = new RollupActions(cass.getSession, appConf, simGroup, simName)
  val feederFile = getDataPath(appConf)
  val csvFeeder = csv(feederFile).random

  val readScenario = scenario("RollupRead")
      .feed(csvFeeder)
      .exec(rollupActions.getByKey)

  val usersRampStartCnt = appConf.getSimulationConfInt("usersRampStartCnt")
  val usersRampToCnt = appConf.getSimulationConfInt("usersRampToCnt")
  val usersRampTime = Duration.apply(appConf.getSimulationConfStr("usersRampTime"))

  val usersConstantCnt = appConf.getSimulationConfInt("usersConstantCnt")
  val usersConstantTime = Duration.apply(appConf.getSimulationConfStr("usersConstantTime"))

  setUp(
    buildRampConstScenario(readScenario, appConf)
  ).protocols(cqlConfig)


}