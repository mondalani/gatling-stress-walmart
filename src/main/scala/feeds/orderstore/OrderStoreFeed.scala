package feeds.orderstore

import java.util.Date

import core.BaseFeed
import feeds.bolt.data.WhoAmIFakedData
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._


class OrderStoreFeed extends BaseFeed {

  val logger = LoggerFactory.getLogger(this.getClass)

  val WhoAmiIFakedData = new WhoAmIFakedData

  def writeHeader(): Unit = {
    logger.debug("storefront_id,id,id_type")
  }

  def write = {
    Iterator.continually(getJsonMap)
  }


  def getJsonMap = {
    Map(
      "order_no" -> faker.bothify("###########"), //F2AC45A479F04796A584DD9FCE751842
      "alt_fname" -> faker.name().firstName(),
      "alt_lname" -> faker.name().lastName(),
      "city" -> faker.address().city(), // abstract_product_buying_options text,
      "cust_id" -> java.util.UUID.randomUUID().toString,
      "data" -> getData,
      "email" -> faker.internet().emailAddress(),
      "esd" -> setAsJavaSet(
        Set[Date](getRandomEpoch, getRandomEpoch)
      ), // set timestamp
      "fname" -> faker.name().firstName(),
      "fulfill_type" -> faker.bothify("?#?"),
      "group_no" -> faker.numerify("##############"),
      "hold_status" -> "",
      "hold_type" -> "",
      "item_id" -> faker.numerify("#########"),
      "line_code" -> faker.numerify("####"),
      "line_status" -> "DELIVERED",
      "lname" -> faker.name().lastName(),
      "modified_dt" -> getRandomEpoch,
      "offer_id" -> "",
      "opd" -> setAsJavaSet(
        Set[Date](getRandomEpoch, getRandomEpoch)
      ), // set timestamp
      "order_date" -> getRandomEpoch,
      "order_type" -> "DOMESTIC",
      "pallet_asn" -> "",
      "partner_item_id" -> "",
      "phone" -> faker.phoneNumber.phoneNumber,
      "pi_hash" -> "",
      "pkg_asn" -> "",
      "po_line_code" -> "",
      "po_line_status" -> "SHIPPED",
      "po_no" -> faker.numerify("####"),
      "rma" -> setAsJavaSet(
        Set[String](faker.bothify("???????"), faker.bothify("???????"))
      ), // set text
      "seller_id" -> "0",
      "shard_id" -> faker.numerify("####"),
      "ship_method" -> "VALUE",
      "ship_node" -> faker.numerify("####"),
      "source" -> faker.bothify("???_???"),
      "state" -> faker.address().state(),
      "store_id" -> faker.bothify("?#???##"),
      "store_tc_no" -> "",
      "tc_no" -> faker.numerify("####"),
      "tracking_no" -> faker.numerify("############"),
      "upc" -> getUpc
    )
  }

  def getData = {
    """{"entityErrors":[],"version":1430396134182,"buyerInfo":{"id":"f069557e-f658-0303-e044-001517f43a86","isGuest":false,"associateInfo":{"shouldApplyAssociateDiscount":false},"primaryContact":{"name":{"completeName":"Eneida Evans","firstName":"Eneida","lastName":"Evans"},"email":{"emailAddress":"evansjimmie@rocketmail.com"}}},"groupOrderNo":"2677258748764","orderNo":"2677258748764","orderType":"DOMESTIC","lastModified":"2015-04-30T12:15:34.182+0000","orderDate":"2015-03-02T20:04:32.000+0000","orderLines":[{"seller":{"catalogSellerId":0,"partnerDisplayName":"Walmart.com"},"primeLineNo":1,"orderedQty":{"unitOfMeasure":"EA","measurementValue":1},"fulfillmentType":"S2H","shippingMethod":"VALUE","orderProduct":{"offerId":{"upc":"0004740612271","USItemId":29020952,"USSellerId":0},"productName":"Graco Spree Classic Connect Travel System, Signal","offerAttributes":{"isMailReturnable":true,"isPerishable":false,"winNo":"551823266"}},"unitPrice":{"currencyAmount":99,"currencyUnit":"USD"},"shipToAddress":{"address":{"addressLineOne":"5919 Timber Valley Dr","addressType":"RESIDENTIAL","city":"Lake Worth","countryCode":"USA","postalCode":"33463","stateOrProvinceName":"FL","stateOrProvinceCode":"FL","isApoFpo":false,"isPoBox":false},"name":{"completeName":"Eneida Evans","firstName":"Eneida","lastName":"Evans"},"phone":{"completeNumber":"5617194513"},"email":{"emailAddress":"evansjimmie@rocketmail.com"}},"orderedLineDates":[{"actualDate":"2015-03-06T00:52:57.000+0000","dateTypeId":"SHIPMENT","requestedDate":"2015-03-03T23:30:00.000+0000","expectedDate":"2015-03-06T08:00:00.000+0000"},{"actualDate":"2015-03-10T17:06:27.000+0000","dateTypeId":"DELIVERY","expectedDate":"2015-03-11T22:30:00.000+0000","minExpectedDate":"2015-03-07T22:36:00.000+0000","maxExpectedDate":"2015-03-07T22:36:00.000+0000"},{"dateTypeId":"OPD","requestedDate":"2015-03-06T08:00:00.000+0000"}],"charges":[{"chargeCategory":"PRODUCT","chargeName":"ItemPrice","chargeQuantity":{"unitOfMeasure":"EA","measurementValue":1},"chargePerUnit":{"currencyAmount":99,"currencyUnit":"USD"},"isDiscount":false,"tax":[{"taxName":"ItemPrice","taxPerLine":{"currencyAmount":5.94,"currencyUnit":"USD"},"taxPerUnit":{"currencyAmount":5.94,"currencyUnit":"USD"},"taxPercentage":0.06}]},{"chargeCategory":"SHIPPING","chargeName":"Shipping","chargeQuantity":{"unitOfMeasure":"EA","measurementValue":1},"chargePerUnit":{"currencyAmount":0,"currencyUnit":"USD"},"isDiscount":false,"tax":[{"taxName":"Shipping","taxPerLine":{"currencyAmount":0,"currencyUnit":"USD"},"taxPerUnit":{"currencyAmount":0,"currencyUnit":"USD"}}]},{"chargeCategory":"VAS","chargeName":"Gift Wrap Price","chargeQuantity":{"unitOfMeasure":"EA","measurementValue":1},"chargePerUnit":{"currencyAmount":0,"currencyUnit":"USD"},"isDiscount":false,"tax":[{"taxName":"Gift Wrap Price","taxPerLine":{"currencyAmount":0,"currencyUnit":"USD"},"taxPerUnit":{"currencyAmount":0,"currencyUnit":"USD"}}]}],"orderLineQuantityInfo":[{"status":"DELIVERED","statusCode":"437","statusChangeDate":"2015-03-10T17:06:27.000+0000","statusQuantity":{"unitOfMeasure":"EA","measurementValue":1},"lineDates":[{"actualDate":"2015-03-06T00:52:57.000+0000","dateTypeId":"SHIPMENT","requestedDate":"2015-03-03T23:30:00.000+0000","expectedDate":"2015-03-06T08:00:00.000+0000"},{"actualDate":"2015-03-10T17:06:27.000+0000","dateTypeId":"DELIVERY","expectedDate":"2015-03-11T22:30:00.000+0000","minExpectedDate":"2015-03-07T22:36:00.000+0000","maxExpectedDate":"2015-03-07T22:36:00.000+0000"},{"dateTypeId":"OPD","requestedDate":"2015-03-06T08:00:00.000+0000"}]}],"returnInfo":{"openRMAQuantity":{"unitOfMeasure":"EA","measurementValue":0},"returnableQty":{"unitOfMeasure":"EA","measurementValue":1},"returnByDate":"2015-06-08T07:00:00.000+0000"},"orderLineCustomAttributes":{"groupLineNo":"1"}}],"paymentMethods":[{"paymentType":"MASTER CARD","pmId":"MASTERCARD","last4DigitsOfCard":"3017","personInfoBillTo":{"address":{"addressLineOne":"101 Cabana Way","city":"Crestview","countryCode":"USA","postalCode":"32536","stateOrProvinceName":"FL","stateOrProvinceCode":"FL","isApoFpo":false,"isPoBox":false},"name":{"completeName":"Mr Jimmie D. Evans","firstName":"Jimmie","middleName":"D.","lastName":"Evans","titleOfRespect":"Mr"}},"amountpromised":{"currencyAmount":104.94,"currencyUnit":"USD"}}],"shipments":[{"status":"SHIPPED","carrierServiceCode":"(Value) FedEx Home Delivery","sCAC":"FedEx","trackingNo":"622737079788","trackingURL":"http://www.fedex.com/Tracking?action=track&language=english&cntry_code=us&initial=x&tracknumbers=622737079788","shipmentLines":[{"primeLineNo":1,"shipmentLineNo":1,"quantity":{"unitOfMeasure":"EA","measurementValue":1}}]}],"purchaseOrders":[{"purchaseOrderNo":"538192833","shipNode":"7628","status":"6","carrierServiceCode":"(Value) FedEx Home Delivery","scac":"FedEx","tcNumber":"030320376210895844157","shipToAddress":{"address":{"addressLineOne":"5919 Timber Valley Dr","addressType":"RESIDENTIAL","city":"Lake Worth","countryCode":"USA","postalCode":"33463","stateOrProvinceCode":"FL","isApoFpo":false,"isPoBox":false},"name":{"completeName":"Eneida Evans","firstName":"Eneida","lastName":"Evans"}},"purchaseOrderLines":[{"poLineId":"1","primeLineNo":1,"poLineStatusInfos":[{"poLineStatus":"SHIPPED","poLineStatusQuantity":{"unitOfMeasure":"EA","measurementValue":1}}],"orderedQty":{"unitOfMeasure":"EA","measurementValue":1},"requestOfferId":{"upc":"0004740612271","USItemId":29020952,"USSellerId":0},"updatedPrice":{"currencyAmount":99,"currencyUnit":"USD"}}],"additionalInfo":{"statusChangeTime":"2015-03-05T11:52:44.000-0800"},"purchaseOrderShipments":[{"actualShipmentDate":"2015-03-05T22:36:00.000+0000","trackingNo":"622737079788","carrierServiceCode":"217","scac":"FedEx","shipmentNo":"526034867","shipNode":"7628","packageNo":"00000557140288S","purchaseOrderShipmentLines":[{"quantity":{"unitOfMeasure":"EA","measurementValue":1},"poLineId":"1","offerId":{"upc":"0004740612271","USItemId":29020952,"USSellerId":0}}],"packageDetail":{"weight":{"measurementValue":37.0}}}]}]}"""
  }
}
