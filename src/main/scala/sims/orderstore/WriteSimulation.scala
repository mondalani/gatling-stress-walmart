package sims.orderstore

import actions.orderstore.OrderStoreActions
import core.BaseSimulation
import feeds.orderstore.OrderStoreFeed
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.SimConfig

class WriteSimulation extends BaseSimulation {

  val simGroup = "orderStore"
  val simName = "write"

  val appConf = new SimConfig(conf, simGroup, simName)

  val orderStoreActions = new OrderStoreActions(cass.getSession, appConf)

  val writeFeed = new OrderStoreFeed().write
  val writeScenario = scenario("OrderStoreWrite")
      .feed(writeFeed)
      .exec(orderStoreActions.writeOrderData)

  setUp(

    buildRampConstScenario(writeScenario, appConf)

  ).assertions(
    //        global.responseTime.percentile4.lessThan()
    //        global.responseTime.max.lessThan(10),
    //        global.successfulRequests.percent.greaterThan(95)
  ).protocols(cqlConfig)

}