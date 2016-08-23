package actions.orderstore

import com.datastax.driver.core.Session
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.SimConfig

class OrderStoreActions(var session: Session, var appConf: SimConfig) {

  val keyspace = appConf.getSimulationConfStr("keyspace")
  val table = appConf.getSimulationConfStr("table")

  val writeOrderDataQuery = QueryBuilder.insertInto(keyspace, table)
      .value("order_no", raw("?"))
      .value("alt_fname", raw("?"))
      .value("alt_lname", raw("?"))
      .value("city", raw("?"))
      .value("cust_id", raw("?"))
      .value("data", raw("?"))
      .value("email", raw("?"))
      .value("esd", raw("?"))
      .value("fname", raw("?"))
      .value("fulfill_type", raw("?"))
      .value("group_no", raw("?"))
      .value("hold_status", raw("?"))
      .value("hold_type", raw("?"))
      .value("item_id", raw("?"))
      .value("line_code", raw("?"))
      .value("line_status", raw("?"))
      .value("lname", raw("?"))
      .value("modified_dt", raw("?"))
      .value("offer_id", raw("?"))
      .value("opd", raw("?"))
      .value("order_date", raw("?"))
      .value("order_type", raw("?"))
      .value("pallet_asn", raw("?"))
      .value("partner_item_id", raw("?"))
      .value("phone", raw("?"))
      .value("pi_hash", raw("?"))
      .value("pkg_asn", raw("?"))
      .value("po_line_code", raw("?"))
      .value("po_line_status", raw("?"))
      .value("po_no", raw("?"))
      .value("rma", raw("?"))
      .value("seller_id", raw("?"))
      .value("shard_id", raw("?"))
      .value("ship_method", raw("?"))
      .value("ship_node", raw("?"))
      .value("source", raw("?"))
      .value("state", raw("?"))
      .value("store_id", raw("?"))
      .value("store_tc_no", raw("?"))
      .value("tc_no", raw("?"))
      .value("tracking_no", raw("?"))
      .value("upc", raw("?"))


  def writeOrderData = {

    val preparedStatement = session.prepare(writeOrderDataQuery)

    group("Write") {
      exec(cql("WriteOrderData")
          .execute(preparedStatement)
          .withParams(
            "${order_no}",
            "${alt_fname}",
            "${alt_lname}",
            "${city}",
            "${cust_id}",
            "${data}",
            "${email}",
            "${esd}",
            "${fname}",
            "${fulfill_type}",
            "${group_no}",
            "${hold_status}",
            "${hold_type}",
            "${item_id}",
            "${line_code}",
            "${line_status}",
            "${lname}",
            "${modified_dt}",
            "${offer_id}",
            "${opd}",
            "${order_date}",
            "${order_type}",
            "${pallet_asn}",
            "${partner_item_id}",
            "${phone}",
            "${pi_hash}",
            "${pkg_asn}",
            "${po_line_code}",
            "${po_line_status}",
            "${po_no}",
            "${rma}",
            "${seller_id}",
            "${shard_id}",
            "${ship_method}",
            "${ship_node}",
            "${source}",
            "${state}",
            "${store_id}",
            "${store_tc_no}",
            "${tc_no}",
            "${tracking_no}",
            "${upc}"
          )
          .check(rowCount is 0)
      )
    }
  }


  def initialize = {
    val create_keyspace = ""
    val create_table = ""
    session.execute(create_keyspace)
  }

}
