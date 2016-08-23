package actions.bolt

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import core.BaseAction
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.{Cassandra, FetchBaseData, SimConfig}

class WhoAmIActions(cassandra: Cassandra, appConf: SimConfig) extends BaseAction(cassandra, appConf) {

  val CONF_CL = "consistencyLevel"

  // select * from bolt_data.who_am_i  WHERE storefront_id='F2AC45A479F04796A584DD9FCE751842'
  // AND id_type='PRODUCT_ID' AND id='899573782022799841';
  val readWhoAmIQuery = cassandra.session.prepare(
    QueryBuilder.select()
        .from(keyspace, table)
        .where(QueryBuilder.eq("storefront_id", raw("?")))
        .and(QueryBuilder.eq("id", raw("?")))
        .and(QueryBuilder.eq("id_type", raw("?")))
  )

  val writeWhoAmIQuery = cassandra.session.prepare(
    QueryBuilder.insertInto(keyspace, table)
        .value("storefront_id", raw("?"))
        .value("id", raw("?"))
        .value("id_type", raw("?"))
        .value("abstract_product_id", raw("?"))
        .value("lifecycle_status", raw("?"))
        .value("modified_dtm", raw("?"))
        .value("offer_publish_status_map", raw("?"))
        .value("primary_product_id", raw("?"))
        .value("product_class_type", raw("?"))
        .value("product_id", raw("?"))
        .value("publish_status", raw("?"))
  )

  //    Statement statement = QueryBuilder.update("simplex", "songs")
  //            .with(set("artist", "Vasili Ostertag"))
  //            .where(eq("id", UUID.fromString("f6071e72-48ec-4fcb-bf3e-379c8a696488")));

  //    val updateWhoAmIQuery = cassandra.session.prepare(
  //        QueryBuilder.update(keyspace, table)
  //                .`with`(
  //                    set("id", "dds"),
  //                    set("id", "dds")
  //                )
  //                .and(set("id", raw("?")))
  //                .and(set("abstract_product_id", raw("?")))
  //                .and(set("lifecycle_status", raw("?")))
  //                .and(set("lifecycle_status", raw("?")))
  //                .and(set("modified_dtm", raw("?")))
  //                .and(set("offer_publish_status_map", raw("?")))
  //                .and(set("primary_product_id", raw("?")))
  //                .and(set("product_class_type", raw("?")))
  //                .and(set("product_id", raw("?")))
  //                .and(set("publish_status", raw("?")))
  //                        .where(eq("storefront_id", raw("?")))
  //                .and("id", raw("?"))


  //                        .`with`()
  //                .value("id_type", raw("?"))
  //                .value("abstract_product_id", raw("?"))
  //                .value("lifecycle_status", raw("?"))
  //                .value("modified_dtm", raw("?"))
  //                .value("offer_publish_status_map", raw("?"))
  //                .value("primary_product_id", raw("?"))
  //                .value("product_class_type", raw("?"))
  //                .value("product_id", raw("?"))
  //                .value("publish_status", raw("?"))
  //    )


  def readWhoAmI = {

    group("Read") {
      exec(cql("WhoAmI")
          .execute(readWhoAmIQuery)
          .withParams(
            "${storefront_id}",
            "${id}",
            "${id_type}"
          )
          .consistencyLevel(cassandra.getCL(appConf.getSimulationConfStr(CONF_CL)))
          .check(rowCount greaterThan 0)
      )
    }
  }

  def UpdateWhoAmI = {

    // bolt read

    group("Read") {
      exec(cql("WhoAmI")
          .execute(readWhoAmIQuery)
          .withParams(
            "${storefront_id}",
            "${id}",
            "${id_type}"
          )
          .consistencyLevel(cassandra.getCL(appConf.getSimulationConfStr(CONF_CL)))
          .check(rowCount greaterThan 0)
      )
    }
  }


  def writeWhoAmI(appConf: SimConfig) = {

    group("Write") {
      exec(cql("WhoAmI")
          .execute(writeWhoAmIQuery)
          .withParams(
            "${storefront_id}",
            "${id}",
            "${id_type}",
            "${abstract_product_id}",
            "${lifecycle_status}",
            "${modified_dtm}",
            "${offer_publish_status_map}",
            "${primary_product_id}",
            "${product_class_type}",
            "${product_id}",
            "${publish_status}"
          )
          .consistencyLevel(cassandra.getCL(appConf.getSimulationConfStr(CONF_CL)))
          .check(rowCount is 0)
      )
    }
  }

  def initialize = {
    val create_keyspace = "CREATE KEYSPACE IF NOT EXISTS gatling"
    val create_table = ""
    session.execute(create_keyspace)
  }

  def fetchKeys = {
    new FetchBaseData(appConf, cassandra).createBaseDataCsv()
  }

}


//CREATE TABLE bolt_data.bolt_data (
//storefront_id text,
//id text,
//id_type text,
//abstract_product_buying_options text,
//abstract_product_primary_offer text,
//buying_options text,
//entity_asset_groups text,
//entity_errors text,
//flat_product text,
//modified_dtm timestamp,
//offer_wrappers text,
//primary_offer text,
//product_id text,
//product_publish_status text,
//variant_product_summaries text,
//PRIMARY KEY ((storefront_id, id), id_type));


//    CREATE TABLE bolt_data.who_am_i (
//        storefront_id text,
//        id text,
//        id_type text,
//        abstract_product_id text,
//        lifecycle_status text,
//        modified_dtm timestamp,
//        offer_publish_status_map map,
//        primary_product_id text,
//        product_class_type text,
//        product_id text,
//        publish_status text,
//        PRIMARY KEY ((storefront_id, id), id_type));