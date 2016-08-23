package actions.bolt

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import com.datastax.driver.core.{ConsistencyLevel, Session}
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.SimConfig

class BoltDataActions(var session: Session) {

  def writeBoltData(appConf: SimConfig) = {

    val keyspace = appConf.getSimulationConfStr("keyspace")
    val table = appConf.getSimulationConfStr("table")

    val writeBoltQuery = QueryBuilder.insertInto(keyspace, table)
        .value("storefront_id", raw("?"))
        .value("id", raw("?"))
        .value("id_type", raw("?"))
        .value("abstract_product_buying_options", raw("?"))
        .value("abstract_product_primary_offer", raw("?"))
        .value("buying_options", raw("?"))
        .value("entity_asset_groups", raw("?"))
        .value("entity_errors", raw("?"))
        .value("flat_product", raw("?"))
        .value("modified_dtm", raw("?"))
        .value("offer_wrappers", raw("?"))
        .value("primary_offer", raw("?"))
        .value("product_id", raw("?"))
        .value("product_publish_status", raw("?"))
        .value("variant_product_summaries", raw("?"))

    val preparedStatement = session.prepare(writeBoltQuery)

    group("Write") {
      exec(cql("BoltData")
          .execute(preparedStatement)
          .withParams(
            "${storefront_id}",
            "${id}",
            "${id_type}",
            "${abstract_product_buying_options}",
            "${abstract_product_primary_offer}",
            "${buying_options}",
            "${entity_asset_groups}",
            "${entity_errors}",
            "${flat_product}",
            "${modified_dtm}",
            "${offer_wrappers}",
            "${primary_offer}",
            "${product_id}",
            "${product_publish_status}",
            "${variant_product_summaries}"
          )
          .check(rowCount is 0)
      )
    }
  }

  def readBoltData(appConf: SimConfig) = {

    val keyspace = appConf.getSimulationConfStr("keyspace")
    val table = appConf.getSimulationConfStr("table")

    // select product_id,product_publish_status,abstract_product_buying_options,buying_options,entity_asset_groups,
    // flat_product,entity_errors  from bolt_data.bolt_data WHERE storefront_id='F2AC45A479F04796A584DD9FCE751842'
    // AND id_type='ITEM_ID' AND id='899573782022799841';
    val readBoltQuery2 = QueryBuilder
        .select(
          "product_id",
          "product_publish_status",
          "abstract_product_buying_options",
          "buying_options",
          "entity_asset_groups"
        )
        .from(keyspace, table)
        .where(QueryBuilder.eq("storefront_id", raw("?")))
        .and(QueryBuilder.eq("id", raw("?")))
        .and(QueryBuilder.eq("id_type", raw("?")))



    val preparedStatement = session.prepare(readBoltQuery2)

    group("Read") {
      exec(cql("BoltData")
          .execute(preparedStatement)
          .withParams(
            "${storefront_id}",
            "${id}",
            "${id_type}"
          )
          .consistencyLevel(ConsistencyLevel.LOCAL_QUORUM)
          .check(rowCount greaterThan 0)
      )
    }
  }


  def readWhoAmI(appConf: SimConfig) = {

    val keyspace = appConf.getSimulationConfStr("keyspace")
    val table = appConf.getSimulationConfStr("table")

    // select * from bolt_data.who_am_i  WHERE storefront_id='F2AC45A479F04796A584DD9FCE751842'
    // AND id_type='PRODUCT_ID' AND id='899573782022799841';
    val readWhoAmIQuery = QueryBuilder
        .select()
        .from(keyspace, table)
        .where(QueryBuilder.eq("storefront_id", raw("?")))
        .and(QueryBuilder.eq("id", raw("?")))
        .and(QueryBuilder.eq("id_type", raw("?")))

    val preparedStatement = session.prepare(readWhoAmIQuery)

    group("Read") {
      exec(cql("WhoAmI")
          .execute(preparedStatement)
          .withParams(
            "${storefront_id}",
            "${id}",
            "${id_type}"
          )
          .consistencyLevel(ConsistencyLevel.LOCAL_ONE)
          .check(rowCount greaterThan 0)
      )
    }
  }


  def writeWhoAmI(appConf: SimConfig) = {

    val keyspace = appConf.getSimulationConfStr("keyspace")
    val table = appConf.getSimulationConfStr("table")

    val writeWhoAmIQuery = QueryBuilder.insertInto(keyspace, table)
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

    val preparedStatement = session.prepare(writeWhoAmIQuery)

    group("Write") {
      exec(cql("WhoAmI")
          .execute(preparedStatement)
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
          .check(rowCount is 0)
      )
    }
  }

  //    def updateWhoAmI(appConf: SimConfig) = {
  //
  //        val keyspace = appConf.getSimulationConfStr("keyspace")
  //        val table = appConf.getSimulationConfStr("table")
  //
  //        val writeWhoAmIQuery = QueryBuilder.update(keyspace, table)
  //                        .`with`(set('set','swee'))
  ////                .where("publish_status", raw("?"))
  //
  //        val preparedStatement = session.prepare(writeWhoAmIQuery)
  //
  //        group("Write") {
  //            exec(cql("WhoAmI")
  //                    .execute(preparedStatement)
  //                    .withParams(
  //                        "${storefront_id}",
  //                        "${id}",
  //                        "${id_type}"
  //                    )
  //                    .check(rowCount is 0)
  //            )
  //        }
  //    }

  def initialize = {
    val create_keyspace = ""
    val create_table = ""
    session.execute(create_keyspace)
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
//
// PRIMARY KEY ((storefront_id, id), id_type));


//    // select product_id,product_publish_status,abstract_product_buying_options,buying_options from
//    // bolt_data.bolt_data WHERE storefront_id=? AND id=? AND id_type=?
//    val readBoltQuery = QueryBuilder
//            .select(
//                "product_id",
//                "product_publish_status",
//                "abstract_product_buying_options",
//                "buying_options"
//            )
//            .from(keyspace, table)
//            .where(QueryBuilder.eq("storefront_id", raw("?")))
//            .and(QueryBuilder.eq("id_type", raw("?")))