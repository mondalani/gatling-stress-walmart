package sims.qarth

import actions.qarth.QarthActions
import core.BaseSimulation
import feeds.qarth.QarthFeed
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.SimConfig

class CreateVariantSimulation extends BaseSimulation {

  val simGroup = "qarth"
  val simName = "createVariant"

  // load conf based on the simGroup and simName from application.conf
  val appConf = new SimConfig(conf, simGroup, simName)

  // init orderActions aka queries
  val qarthActions = new QarthActions(cass, appConf)

  // create keyspace/table if they do not exist
  qarthActions.initCassandraSchema

  // Load feed for generating data
  val writeFeed = new QarthFeed()


  // build scenario to run with feed and write action
  val writeScenario = scenario("CreateVariant")
      .feed(writeFeed.createFeed)
      .exec(qarthActions.CreateEvent.Step_1.action).exitHereIfFailed
      .exec(qarthActions.CreateEvent.Step_2.action)
      .exec(qarthActions.CreateEvent.Step_3.action)
      .exec(qarthActions.CreateEvent.Step_4.action)
      .exec(qarthActions.CreateEvent.Step_5.action)
      .exec(qarthActions.CreateEvent.Step_6.action)
      .exec(qarthActions.CreateEvent.Step_7.action)
      .exec(qarthActions.CreateEvent.Step_8.action)
      .exec(qarthActions.CreateEvent.Step_9.action)
      .exec(qarthActions.CreateEvent.Step_10.action)

  // setup the traffic to run w/ the scenario
  setUp(

    buildRampConstScenario(writeScenario, appConf)

  ).protocols(cqlConfig)

}