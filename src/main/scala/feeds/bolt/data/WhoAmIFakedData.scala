package feeds.bolt.data

import core.BaseFeed

import scala.collection.JavaConversions._

/**
  * Created by bradvernon on 7/22/16.
  */
class WhoAmIFakedData extends BaseFeed {

  def getRowData = {
    Map(
      "storefront_id" -> getIdString.toUpperCase,
      "id" -> getShortIdString.toUpperCase,
      "id_type" -> getRandom(idTypes),
      "abstract_product_id" -> getShortIdString.toUpperCase,
      "lifecycle_status" -> "ACTIVE",
      "modified_dtm" -> getRandomEpoch,
      "offer_publish_status_map" -> mapAsJavaMap(
        Map[String, String](getIdString.toUpperCase -> getRandom(publishStatus))
      ),
      "primary_product_id" -> getJsonString(Map("productId" -> getShortIdString.toUpperCase)),
      "product_class_type" -> getRandom(productClassTypes),
      "product_id" -> getProductIdJson,
      "publish_status" -> getRandom(publishStatus)
    )
  }

  def getProductIdJson = {
    getJsonString(
      Map(
        "productId" -> getShortIdString,
        "USItemId" -> faker.numerify("########"),
        "legacyItemId" -> faker.numerify("########"),
        "upc" -> getUpc,
        "wupc" -> getUpc,
        "gtin" -> getUpc
      )
    )
  }

}
