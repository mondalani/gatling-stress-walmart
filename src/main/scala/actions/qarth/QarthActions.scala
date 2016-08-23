package actions.qarth

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import core.BaseAction
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.{Cassandra, SimConfig}

class QarthActions(cassandra: Cassandra, appConf: SimConfig) extends BaseAction(cassandra, appConf) {

  object Tables {
    val tenantVariantProduct = "tenant_variant_product"
    val swpidToSapid = "swpid_to_sapid"
    val sourceGroupToSapid = "source_group_to_sapid"
    val sourceVariantProduct = "source_variant_product"
    val wpidToApid = "wpid_to_apid"
    val sapidToApid = "sapid_to_apid"
  }

  object CreateEvent {

    object Step_1 {

      /*
      SELECT swpid, sapid FROM swpid_to_sapid WHERE swpid = ?
       */

      val table = Tables.swpidToSapid

      val query = QueryBuilder.select("swpid", "sapid")
          .from(keyspace, table)
          .where(QueryBuilder.eq("swpid", raw("?")))

      def action = {

        val preparedStatement = session.prepare(query)

        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${swpid}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }


    object Step_2 {

      /*
      SELECT svgid,org_id,sapid FROM source_group_to_sapid WHERE svgid = ? AND org_id = ?
      */

      val table = Tables.sourceGroupToSapid

      val query = QueryBuilder.select("svgid", "org_id", "sapid")
          .from(keyspace, table)
          .where(QueryBuilder.eq("svgid", raw("?")))
          .and(QueryBuilder.eq("org_id", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${svgid}",
                "${org_id}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }


    object Step_3 {

      /*
      INSERT INTO source_group_to_sapid(svgid,org_id,sapid) VALUES (?,?,?) IF NOT EXISTS
      */

      val table = Tables.sourceGroupToSapid

      val query = QueryBuilder.insertInto(keyspace, table)
          .value("svgid", raw("?"))
          .value("org_id", raw("?"))
          .value("sapid", raw("?"))
          .ifNotExists()

      val preparedStatement = session.prepare(query)

      def action = {

        group(Groups.INSERT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${svgid}",
                "${org_id}",
                "${sapid}"
              )
              .check(columnValue("[applied]") is true)
          )
        }
      }

    }


    object Step_4 {

      /*
      INSERT INTO swpid_to_sapid(swpid,sapid) VALUES(?,?)
      */

      val table = Tables.swpidToSapid

      val query = QueryBuilder.insertInto(keyspace, table)
          .value("swpid", raw("?"))
          .value("sapid", raw("?"))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.INSERT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${swpid}",
                "${sapid}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }

    object Step_5 {

      /*
      INSERT INTO source_variant_product(sapid,swpid,variant_criteria,variant_metadata) VALUES(?,?,?,?)
      */

      val table = Tables.sourceVariantProduct

      val query = QueryBuilder.insertInto(keyspace, table)
          .value("sapid", raw("?"))
          .value("swpid", raw("?"))
          .value("variant_criteria", raw("?"))
          .value("variant_metadata", raw("?"))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.INSERT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${sapid}",
                "${swpid}",
                "${variant_criteria}",
                "${variant_metadata}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }

    object Step_6 {

      /*
      SELECT wpid,apid FROM wpid_to_apid WHERE wpid = ?
      */

      val table = Tables.wpidToApid

      val query = QueryBuilder.select("wpid", "apid")
          .from(keyspace, table)
          .where(QueryBuilder.eq("wpid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${swpid}"
              )
              .check(rowCount is 0)
          )
        }
      }
    }


    object Step_7 {

      /*
      SELECT sapid,apid FROM sapid_to_apid WHERE sapid = ?
      */

      val table = Tables.sapidToApid

      val query = QueryBuilder.select("sapid", "apid")
          .from(keyspace, table)
          .where(QueryBuilder.eq("sapid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${sapid}"
              )
              .check(rowCount is 0)
          )
        }
      }
    }

    object Step_8 {

      /*
      INSERT INTO wpid_to_apid(wpid,apid) VALUES (?,?)
      */

      val table = Tables.wpidToApid

      val query = QueryBuilder.insertInto(keyspace, table)
          .value("wpid", raw("?"))
          .value("apid", raw("?"))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.INSERT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${wpid}",
                "${apid}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }


    object Step_9 {

      /*
      INSERT INTO sapid_to_apid(sapid,apid) VALUES(?,?) IF NOT EXISTS
      */

      val table = Tables.sapidToApid

      val query = QueryBuilder.insertInto(keyspace, table)
          .value("sapid", raw("?"))
          .value("apid", raw("?"))
          .ifNotExists()

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.INSERT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${sapid}",
                "${apid}"
              )
              .check(columnValue("[applied]") is true)
          )
        }
      }

    }

    object Step_10 {

      /*
      INSERT INTO tenant_variant_product(apid,wpid,merge_meta) VALUES(?,?,?)
      */

      val table = Tables.tenantVariantProduct

      val query = QueryBuilder.insertInto(keyspace, table)
          .value("apid", raw("?"))
          .value("wpid", raw("?"))
          .value("merge_meta", raw("?"))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.INSERT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${apid}",
                "${wpid}",
                "${merge_meta}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }

  }


  object UpdateEvent {


    object Step_1 {

      /*
      SELECT swpid, sapid FROM swpid_to_sapid WHERE swpid = ?
       */

      val table = Tables.swpidToSapid

      val query = QueryBuilder.select("swpid", "sapid")
          .from(keyspace, table)
          .where(QueryBuilder.eq("swpid", raw("?")))

      def action = {

        val preparedStatement = session.prepare(query)

        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${swpid}"
              )
              .check(rowCount greaterThan 0)
          )
        }
      }

    }


    object Step_2 {

      /*
      SELECT svgid,org_id,sapid FROM source_group_to_sapid WHERE svgid = ? AND org_id = ?
      */

      val table = Tables.sourceGroupToSapid

      val query = QueryBuilder.select("svgid", "org_id", "sapid")
          .from(keyspace, table)
          .where(QueryBuilder.eq("svgid", raw("?")))
          .and(QueryBuilder.eq("org_id", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${svgid}",
                "${org_id}"
              )
              .check(rowCount greaterThan 0)
          )
        }
      }

    }


    object Step_3 {

      /*
      UPDATE source_variant_product SET variant_criteria=?,variant_metadata=? WHERE sapid = ? and swpid = ?
      */

      val table = Tables.sourceVariantProduct

      val query = QueryBuilder.update(keyspace, table)
          .`with`(set("variant_criteria", raw("?")))
          .and(set("variant_metadata", raw("?")))
          .where(QueryBuilder.eq("sapid", raw("?")))
          .and(QueryBuilder.eq("swpid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {

        group(Groups.UPDATE) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${variant_criteria}",
                "${variant_metadata}",
                "${sapid}",
                "${swpid}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }

    object Step_4 {

      /*
      SELECT wpid,apid FROM wpid_to_apid WHERE wpid = ?
      */

      val table = Tables.sourceGroupToSapid

      val query = QueryBuilder.select("wpid", "apid")
          .from(keyspace, table)
          .where(QueryBuilder.eq("wpid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${wpid}"
              )
              .check(rowCount greaterThan 0)
          )
        }
      }

    }


    object Step_5 {

      /*
      UPDATE tenant_variant_product SET variant_criteria = ?,variant_metadata = ? WHERE apid = ? and wpid = ?
      */

      val table = Tables.tenantVariantProduct

      val query = QueryBuilder.update(keyspace, table)
          .`with`(set("variant_criteria", raw("?")))
          .and(set("variant_metadata", raw("?")))
          .where(QueryBuilder.eq("apid", raw("?")))
          .and(QueryBuilder.eq("wpid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {

        group(Groups.UPDATE) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${variant_criteria}",
                "${variant_metadata}",
                "${apid}",
                "${wpid}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }

  }


  object DeleteEvent {


    object Step_1 {

      /*
      SELECT swpid, sapid FROM swpid_to_sapid WHERE swpid = ?
       */

      val table = Tables.swpidToSapid

      val query = QueryBuilder.select("swpid", "sapid")
          .from(keyspace, table)
          .where(QueryBuilder.eq("swpid", raw("?")))

      def action = {

        val preparedStatement = session.prepare(query)

        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${swpid}"
              )
              .check(rowCount greaterThan 0)
          )
        }
      }

    }


    object Step_2 {

      /*
      DELETE FROM swpid_to_sapid WHERE swpid = ?
      */

      val table = Tables.swpidToSapid

      val query = QueryBuilder.delete()
          .from(keyspace, table)
          .where(QueryBuilder.eq("swpid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${swpid}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }

    object Step_3 {

      /*
      DELETE FROM source_variant_product WHERE sapid = ? AND swpid = ?
      */

      val table = Tables.sourceVariantProduct

      val query = QueryBuilder.delete()
          .from(keyspace, table)
          .where(QueryBuilder.eq("sapid", raw("?")))
          .and(QueryBuilder.eq("swpid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${sapid}",
                "${swpid}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }


    object Step_4 {

      /*
      SELECT wpid,apid FROM wpid_to_apid WHERE wpid = ?
      */

      val table = Tables.sourceGroupToSapid

      val query = QueryBuilder.select("wpid", "apid")
          .from(keyspace, table)
          .where(QueryBuilder.eq("wpid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${wpid}"
              )
              .check(rowCount greaterThan 0)
          )
        }
      }

    }


    object Step_5 {

      /*
      DELETE FROM wpid_to_apid WHERE wpid = ?
      */

      val table = Tables.wpidToApid

      val query = QueryBuilder.delete()
          .from(keyspace, table)
          .where(QueryBuilder.eq("wpid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${wpid}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }


    object Step_6 {

      /*
      DELETE FROM tenant_variant_product WHERE apid = ? AND wpid = ?
      */

      val table = Tables.tenantVariantProduct

      val query = QueryBuilder.delete()
          .from(keyspace, table)
          .where(QueryBuilder.eq("apid", raw("?")))
          .and(QueryBuilder.eq("wpid", raw("?")))

      val preparedStatement = session.prepare(query)

      def action = {
        group(Groups.SELECT) {
          exec(cql(table)
              .execute(preparedStatement)
              .withParams(
                "${apid}",
                "${wpid}"
              )
              .check(rowCount is 0)
          )
        }
      }

    }


  }


  def initCassandraSchema = {

    val createKeyspace = s"CREATE KEYSPACE IF NOT EXISTS $keyspace WITH replication = {'class': 'SimpleStrategy', " +
        s"'replication_factor': '1'} AND durable_writes = true;"

    val createTable1 = s"CREATE TABLE IF NOT EXISTS $keyspace.tenant_variant_product (" +
        "apid text, wpid text, variant_criteria text, variant_metadata text, is_primary boolean, update_time bigint, " +
        "merge_meta text, primary key ((apid), wpid) );"

    val createTable2 = s"CREATE TABLE IF NOT EXISTS $keyspace.swpid_to_sapid (" +
        "swpid text, sapid text, primary key (swpid) );"

    val createTable3 = s"CREATE TABLE IF NOT EXISTS $keyspace.source_group_to_sapid (svgid text, org_id text, " +
        s"sapid text, primary key ((svgid, org_id)) );"

    val createTable4 = s"CREATE TABLE IF NOT EXISTS $keyspace.source_variant_product (" +
        "sapid text, swpid text, variant_criteria text, variant_metadata text, primary key ((sapid),swpid) );"

    val createTable5 = s"CREATE TABLE IF NOT EXISTS $keyspace.wpid_to_apid (" +
        "wpid text primary key, apid text);"

    val createTable6 = s"CREATE TABLE IF NOT EXISTS $keyspace.sapid_to_apid (" +
        "sapid text, apid text, primary key (sapid));"


    logger.debug("Creating Keyspace and tables")

    session.execute(createKeyspace)
    session.execute(createTable1)
    session.execute(createTable2)
    session.execute(createTable3)
    session.execute(createTable4)
    session.execute(createTable5)
    session.execute(createTable6)

  }

}
