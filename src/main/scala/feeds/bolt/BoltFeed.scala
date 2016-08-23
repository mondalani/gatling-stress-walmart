package feeds.bolt

import com.github.javafaker.Faker
import feeds.bolt.data.BoltFakedData
import feeds.bolt.data.WhoAmIFakedData
import org.slf4j.LoggerFactory

import scala.util.Random


class BoltFeed {

  val logger = LoggerFactory.getLogger(this.getClass)

  val faker = new Faker()

  val BoltFakedData = new BoltFakedData
  val WhoAmiIFakedData = new WhoAmIFakedData

  def writeHeader(): Unit = {
    logger.debug("storefront_id,id,id_type")
  }

  def write = {
    Iterator.continually(getJsonMap)
  }


  def getJsonMap = {
    Map(
      "storefront_id" -> faker.bothify("?#??##?###?#####?###??#???######").toUpperCase, //F2AC45A479F04796A584DD9FCE751842
      "id" -> BoltFakedData.getRandomIdStr,
      "id_type" -> BoltFakedData.getIdType,
      "abstract_product_buying_options" -> getAbstractBuyingOptions, // abstract_product_buying_options text,
      "abstract_product_primary_offer" -> getAbstractProductPrimaryOffer,
      "buying_options" -> BoltFakedData.getBuyingOptions,
      "entity_asset_groups" -> BoltFakedData.getEntityAsset,
      "entity_errors" -> "[]",
      "flat_product" -> BoltFakedData.getFlatProduct,
      "modified_dtm" -> BoltFakedData.getTimestamp,
      "offer_wrappers" -> BoltFakedData.getPrimaryOffer,
      "primary_offer" -> BoltFakedData.getPrimaryOffer,
      "product_id" -> BoltFakedData.getProductId,
      "product_publish_status" -> "PUBLISHED",
      "variant_product_summaries" -> BoltFakedData.getVarientProduct
    )
  }


  def getAbstractBuyingOptions = {
    getRandom(
      Array(
        BoltFakedData.abstractProductBuyingOptions,
        "" // we can't write nulls to the db in gatling so just using empty string
      )
    )
  }

  def getAbstractProductPrimaryOffer = {
    getRandom(
      Array(
        BoltFakedData.productPrimaryOffer,
        ""
      )
    )
  }


  def getRandom(array: Array[String]) = {
    Random.shuffle(array.toList).head
  }


  def getWhoAmIData = {
    Iterator.continually(WhoAmiIFakedData.getRowData)
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

//    CREATE TABLE load_test.who_am_i (
//        storefront_id text,
//        id text,
//        id_type text,
//        abstract_product_id text,
//        lifecycle_status text,
//        modified_dtm timestamp,
//        offer_publish_status_map map<text>,
//        primary_product_id text,
//        product_class_type text,
//        product_id text,
//        publish_status text,
//        PRIMARY KEY ((storefront_id, id), id_type));