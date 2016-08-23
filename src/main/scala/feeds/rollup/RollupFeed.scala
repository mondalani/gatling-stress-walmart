package feeds.rollup

import java.util.concurrent.TimeUnit

import core.BaseFeed
import org.json4s.native.Serialization._

import scala.util.Random

/**
  * Created by bradvernon on 3/23/16.
  */
class RollupFeed extends BaseFeed {

  val id_types = Array("PRODUCT_ID", "UPC", "ITEM_ID")

  def writeMinRollups = {

    val feeder = Iterator.continually(Map(
      "storefront_id" -> faker.bothify("?#??##?###?#####?###??#???######").toUpperCase, //F2AC45A479F04796A584DD9FCE751842
      "id" -> faker.bothify("#???#??#???#").toUpperCase,
      "id_type" -> getRandom(id_types),
      "modified_dtm" -> faker.date().past(15, TimeUnit.DAYS),
      "preview_rollup_data" -> getPreviewData,
      "preview_shell_rollup_data" -> getPreviewShellRollupData,
      "rollup_data" -> getPreviewData,
      "shell_rollup_data" -> getPreviewShellRollupData
    ))
    feeder
  }


  def getPreviewShellRollupData = {
    val output = Array(getPreviewData, "")
    getRandom(output)
  }


  def getPreviewData: String = {

    val product_id = Map[String, Object](
      "productId" -> faker.bothify("#?##?????#?"), // "5G3OXAPVRL2C"
      "USItemId" -> faker.bothify("########"), // 15049418
      "legacyItemId" -> faker.bothify("########"), // "15049418",
      "upc" -> faker.bothify("############"), //"883316227305",
      "wupc" -> faker.bothify("#############"), //"0088331622730",
      "gtin" -> faker.bothify("############") //"00883316227305"
    )

    val rankedOfferIds = List[String](
      faker.bothify("?##########??#?#??#??##??###?#?##"),
      faker.bothify("?##########??#?#??#??##??###?#?##")
    )

    val productRollupPricing = Map[String, Object](
      "primaryOfferPriceValue" -> Map(
        "currencyAmount" -> faker.bothify("##.##"),
        "currencyUnit" -> "USD"
      ),
      "compPriceValue" -> Map(
        "currencyAmount" -> faker.bothify("##.##"),
        "currencyUnit" -> "USD"
      ),
      "savingsAmount" -> faker.numerify("#"),
      "savingsPercent" -> faker.bothify("##.##"),
      "priceDisplayCodes" -> Map(
        "isStrikethrough" -> Random.shuffle(bool.toList).head,
        "isEligibleForAssociateDiscount" -> Random.shuffle(bool.toList).head
      )
    )

    val output = Map[String, Object](
      "productId" -> product_id,
      "productClassTypeEnum" -> getRandom(productClassTypes),
      "publishStatusEnum" -> getRandom(publishStatus),
      "lifecycleStatusEnum" -> getRandom(status),
      "rankedOfferIds" -> rankedOfferIds,
      "productRollupPricing" -> productRollupPricing
    )

    //        println(scala.util.parsing.json.JSONObject(output).toString())
    //        println(write(output))
    write(output)
  }

}

