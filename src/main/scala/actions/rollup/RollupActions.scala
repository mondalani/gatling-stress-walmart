package actions.rollup

import com.datastax.driver.core.Session
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import io.gatling.core.Predef._
import io.github.gatling.dse.CqlPredef._
import libs.SimConfig

class RollupActions(var session: Session, var conf: SimConfig, var simGroup: String, var simName: String) {

  val keyspace = conf.getSimulationConfStr("keyspace")
  val table = conf.getSimulationConfStr("table")

  val readQuery = QueryBuilder
      .select("storefront_id", "id", "id_type", "modified_dtm", "preview_rollup_data", "preview_shell_rollup_data",
        "rollup_data", "shell_rollup_data")
      .from(keyspace, table)
      .where(QueryBuilder.eq("storefront_id", raw("?")))
      .and(QueryBuilder.eq("id", raw("?")))

  val writeQuery = QueryBuilder.insertInto(keyspace, table)
      .value("storefront_id", raw("?"))
      .value("id", raw("?"))
      .value("id_type", raw("?"))
      .value("modified_dtm", raw("?"))
      .value("preview_rollup_data", raw("?"))
      .value("preview_shell_rollup_data", raw("?"))
      .value("rollup_data", raw("?"))
      .value("shell_rollup_data", raw("?"))


  def insertRollupData = {

    val preparedStatement = session.prepare(writeQuery)

    group("Write") {
      exec(cql("Rollup")
          .execute(preparedStatement)
          .withParams(
            "${storefront_id}",
            "${id}",
            "${id_type}",
            "${modified_dtm}",
            "${preview_rollup_data}",
            "${preview_shell_rollup_data}",
            "${rollup_data}",
            "${shell_rollup_data}"
          )
          .check(rowCount greaterThan 0)
      )
    }
  }


  def getByKey = {

    val preparedStatement = session.prepare(readQuery)

    group("Read") {
      exec(cql("Rollup")
          .execute(preparedStatement)
          .withParams(
            "${storefront_id}",
            "${id}"
          )
          .check(rowCount greaterThan 0)
      )
    }
  }

}
