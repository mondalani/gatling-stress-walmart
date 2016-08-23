package sims.orderstore

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DeleteViaSolrSimulation extends Simulation {

  val httpProtocol = http.baseURL("http://10.10.1.10:8993/solr/")

  val scn = scenario("Delete")
      .exec(
        http("Home")
            .post("{keyspace}.{core}/update")
            .header("Content-Type", "text/xml")
            .body(StringBody("<delete><query>order_no:${order_no}</query></delete>"))
      )

  setUp(scn.inject(
    atOnceUsers(1))
  ).protocols(httpProtocol)

}