package feeds.bolt.data

import java.util.concurrent.TimeUnit

import core.BaseFeed

/**
  * Created by bradvernon on 7/22/16.
  */
class BoltFakedData extends BaseFeed {

  val businessUnit = Array("WALMART_US")
  val offerType = Array("WALMART_US", "ONLINE_ONLY", "BOTH")

  def abstractProductBuyingOptions = {

    val jsonMap = Map(
      "entityErrors" -> Array,
      "productPublishStatus" -> getRandom(publishStatus),
      "productLifecycleStatus" -> getRandom(status),
      "productClassTypeEnum" -> getRandom(productClassTypes),
      "abstractProductId" -> faker.bothify("####?##??#?#"), // 5090L5OZA7X0
      "offerCount" -> faker.numerify("#"),
      "primarySeller" -> Map(
        "entityErrors" -> Array,
        "sellerId" -> getLongStrId,
        "partnerDisplayName" -> faker.internet.domainName,
        "sellerType" -> getRandom(sellerType)
      ),
      "isPrimaryOfferPUTEligible" -> getRandom(bool),
      "storeFrontBuyingOptions" -> Map(
        "storeFrontType" -> getRandom(location),
        "storeFrontId" -> Map(
          "storeUUID" -> stripChar(java.util.UUID.randomUUID().toString, "-", "")),
        "storeId" -> faker.numerify("#"),
        "onlineStoreFront" -> getRandom(bool),
        "USStoreId" -> faker.numerify("#")
      ),
      "canAddToCart" -> getRandom(bool),
      "availabilityStatus" -> getRandom(availStatus),
      "quantity" -> faker.bothify("#.#"),
      "subChannels" -> Array,
      "productType" -> getRandom(productClassTypes),
      "minPrice" -> Map(
        "currencyAmount" -> getPrice,
        "currencyUnit" -> "USD"
      ),
      "maxPrice" -> Map(
        "currencyAmount" -> getPrice,
        "currencyUnit" -> "USD"
      ),
      "offerFulfillmentOptionWrappers" -> Array(
        Map(
          "fulfillmentOption" -> faker.bothify("?#?"),
          "offerShippingMethodWrappers" -> Array(
            Map(
              "shipMethod" -> getRandom(shipMethod),
              "shippingProgramFlags" -> Array
            ),
            Map(
              "shipMethod" -> getRandom(shipMethod),
              "shippingProgramFlags" -> Array
            )
          )
        )
      ),
      "shippingProgramFlags" -> Array,
      "fulfillmentOptions" -> Array(
        faker.bothify("?#?")
      ),
      "storePUTEligible" -> getRandom(bool),
      "lastUpdatedOn" -> getRandomEpoch,
      "isShippingPassEligible" -> getRandom(bool),
      "isInternationalShippingEligible" -> getRandom(bool)
    )

    getJsonString(jsonMap)
  }


  def productPrimaryOffer = {

    val something = Map(
      "tenantId" -> faker.numerify("#"),
      "businessUnit" -> getRandom(businessUnit),
      "modifiedDtm" -> getRandomEpoch,
      "offerId" -> Map(
        "offerId" -> getIdString,
        "upc" -> getUpc,
        "wupc" -> getUpc,
        "gtin" -> getUpc,
        "USItemId" -> getUpc,
        "USSellerId" -> faker.numerify("#"),
        "legacyItemId" -> faker.numerify("########"),
        "legacySellerId" -> faker.numerify("#")
      ),
      "productId" -> Map(
        "productId" -> getIdString,
        "USItemId" -> getUpc,
        "legacyItemId" -> getUpc,
        "upc" -> getUpc,
        "wpc" -> getUpc,
        "gtin" -> getUpc
      ),
      "offerType" -> getRandom(offerType),
      "sellerId" -> getLongStrId,
      "sellerOfferId" -> faker.bothify("?#??####_#######"),
      "offerIdentifiers" -> Map(
        "GTIN" -> getUpc,
        "UPC" -> getUpc,
        "WUPC" -> getUpc
      ),
      "startDate" -> getRandomEpoch,
      "endDate" -> getRandomEpoch,
      "originOfComponents" -> Array(Map(
        "locationName" -> "Imported"
      )),
      "offerPublishStatus" -> getRandom(publishStatus),
      "offerLifecycleStatus" -> getRandom(status),
      "globalTaxCode" -> faker.numerify("########"),
      "offerAttributes" -> Map(
        "is_pay_in_person_eligible" -> Map(
          "value" -> getRandom(bool),
          "valueRank" -> faker.numerify("#"),
          "name" -> faker.bothify("????????_????_????"),
          "type" -> "STRING",
          "displayName" -> faker.bothify("????????_????_????"),
          "isMultiValued" -> getRandom(bool)
        ),
        "is_preorder" -> Map(
          "value" -> getRandom(bool),
          "valueRank" -> faker.numerify("#"),
          "name" -> faker.bothify("????????_????_????"),
          "type" -> "STRING",
          "displayName" -> faker.bothify("????????_????_????"),
          "isMultiValued" -> getRandom(bool)
        ),
        "data_source" -> Map(
          "value" -> getRandom(bool),
          "valueRank" -> faker.numerify("#"),
          "name" -> faker.bothify("????????_????_????"),
          "type" -> "STRING",
          "displayName" -> faker.bothify("????????_????_????"),
          "isMultiValued" -> getRandom(bool)
        )
      ),
      "marketOfferCrossReferences" -> Array(Map(
        "identifierName" -> getRandom(idTypes),
        "US_WMT_DOTCOM_SELLER_ID" -> faker.numerify("#"),
        "subsystemName" -> getRandom(subsystemName),
        "US_WMT_DOTCOM_ITEM_ID" -> faker.numerify("########")
      ))


    )

    getJsonString(something)

    //
    //            "marketAttributes": {
    //                "new": {
    //                "value": "false",
    //                "valueRank": 0,
    //                "name": "new",
    //                "type": "STRING",
    //                "displayName": "new",
    //                "isMultiValued": false
    //            },
    //                "lastUpdatedBy": {
    //                "value": "us-catalog",
    //                "valueRank": 0,
    //                "name": "lastUpdatedBy",
    //                "type": "STRING",
    //                "isMultiValued": false
    //            },
    //                "rh_path": {
    //                "value": "7043:7047:9790:10589:9794",
    //                "valueRank": 0,
    //                "name": "rh_path",
    //                "type": "STRING",
    //                "displayName": "rh_path",
    //                "isMultiValued": false
    //            },
    //                "pcp_seller_id": {
    //                "value": "3",
    //                "valueRank": 0,
    //                "name": "pcp_seller_id",
    //                "type": "STRING",
    //                "displayName": "pcp_seller_id",
    //                "isMultiValued": false
    //            },
    //                "item_master_created_dtm": {
    //                "value": "2013-03-29T10:53:41+0000",
    //                "valueRank": 0,
    //                "name": "item_master_created_dtm",
    //                "type": "STRING",
    //                "displayName": "item_master_created_dtm",
    //                "isMultiValued": false
    //            },
    //                "alternate_shelves": {
    //                "value": "1043980",
    //                "valueRank": 0,
    //                "name": "alternate_shelves",
    //                "type": "STRING",
    //                "displayName": "alternate_shelves",
    //                "isMultiValued": true,
    //                "values": [
    //            {
    //                "value": "1043980",
    //                "valueRank": 0
    //            },
    //            {
    //                "value": "1067820",
    //                "valueRank": 0
    //            }
    //                ]
    //            },
    //                "Legacy_Item_ID": {
    //                "value": "23901549",
    //                "valueRank": 0,
    //                "name": "Legacy_Item_ID",
    //                "type": "STRING",
    //                "displayName": "Legacy_Item_ID",
    //                "isMultiValued": false
    //            },
    //                "item_type": {
    //                "value": "0",
    //                "valueRank": 0,
    //                "name": "item_type",
    //                "type": "STRING",
    //                "displayName": "item_type",
    //                "isMultiValued": false
    //            },
    //                "url_text_key": {
    //                "value": "/ip/Colour-Shaper-Silicone-Wide-Firm-Flat-Brush/23901549",
    //                "valueRank": 0,
    //                "name": "url_text_key",
    //                "type": "STRING",
    //                "displayName": "url_text_key",
    //                "isMultiValued": false
    //            },
    //                "catalog_migration_readiness": {
    //                "value": "COMPLETE",
    //                "valueRank": 0,
    //                "name": "catalog_migration_readiness",
    //                "type": "STRING",
    //                "displayName": "catalog_migration_readiness",
    //                "isMultiValued": false
    //            },
    //                "pch_id": {
    //                "value": "4011013",
    //                "valueRank": 0,
    //                "name": "pch_id",
    //                "type": "STRING",
    //                "displayName": "pch_id",
    //                "isMultiValued": false
    //            },
    //                "item_class_id": {
    //                "value": "1",
    //                "valueRank": 0,
    //                "name": "item_class_id",
    //                "type": "STRING",
    //                "displayName": "item_class_id",
    //                "isMultiValued": false
    //            },
    //                "primary_category_path": {
    //                "value": "0:1072864:1067617:1067621:1043980",
    //                "valueRank": 0,
    //                "name": "primary_category_path",
    //                "type": "STRING",
    //                "displayName": "primary_category_path",
    //                "isMultiValued": false
    //            },
    //                "data_source": {
    //                "value": "Catalog",
    //                "valueRank": 0,
    //                "name": "data_source",
    //                "type": "STRING",
    //                "displayName": "data_source",
    //                "isMultiValued": false
    //            },
    //                "show_button_in": {
    //                "value": "3",
    //                "valueRank": 0,
    //                "name": "show_button_in",
    //                "type": "STRING",
    //                "displayName": "show_button_in",
    //                "isMultiValued": false
    //            },
    //                "partner_id": {
    //                "value": "255045",
    //                "valueRank": 0,
    //                "name": "partner_id",
    //                "type": "STRING",
    //                "displayName": "partner_id",
    //                "isMultiValued": false
    //            },
    //                "pg_offer_id": {
    //                "value": "9039805A8F244BDC87DCC1289BF41A47",
    //                "valueRank": 0,
    //                "name": "pg_offer_id",
    //                "type": "STRING",
    //                "displayName": "pg_offer_id",
    //                "isMultiValued": false
    //            },
    //                "primary_shelf_id": {
    //                "value": "1043980",
    //                "valueRank": 0,
    //                "name": "primary_shelf_id",
    //                "type": "STRING",
    //                "displayName": "primary_shelf_id",
    //                "isMultiValued": false
    //            },
    //                "char_primary_category_path": {
    //                "value": "Home Improvement/Paint & Home Decor/Paint Prep & Application/Paint Applicators",
    //                "valueRank": 0,
    //                "name": "char_primary_category_path",
    //                "type": "STRING",
    //                "displayName": "char_primary_category_path",
    //                "isMultiValued": false
    //            },
    //                "is_always_show": {
    //                "value": "N",
    //                "valueRank": 0,
    //                "name": "is_always_show",
    //                "type": "STRING",
    //                "displayName": "is_always_show",
    //                "isMultiValued": false
    //            },
    //                "display_status": {
    //                "value": "PUBLISHED",
    //                "valueRank": 0,
    //                "name": "display_status",
    //                "type": "STRING",
    //                "displayName": "display_status",
    //                "isMultiValued": false
    //            },
    //                "seller_id": {
    //                "value": "3",
    //                "valueRank": 0,
    //                "name": "seller_id",
    //                "type": "STRING",
    //                "displayName": "seller_id",
    //                "isMultiValued": false
    //            }
    //            },
    //            "offerRank": 0,
    //            "partnerId": "255045",
    //            "offerLogistics": {
    //                "productShipPackageMeasuresList": [
    //            {
    //                "productWeight": {
    //                "unitOfMeasure": "LB",
    //                "measurementValue": 0.07
    //            },
    //                "packageNumber": 0
    //            }
    //                ],
    //                "mustShipAlone": false,
    //                "shipAsIs": false,
    //                "numberOfBoxes": 1,
    //                "giftOpts": {
    //                "allowGiftWrap": false,
    //                "allowGiftMessage": false,
    //                "allowGiftReceipt": false
    //            }
    //            },
    //            "crossBorderTradeAttributes": {
    //                "isEligible": false
    //            },
    //            "streetDateDisplayable": true,
    //            "externalPartnerId": "255045"
    //        }
  }

  def getBuyingOptions: String = {
    """{"entityErrors":[],"productPublishStatus":"PUBLISHED","productLifecycleStatus":"ACTIVE",
      |"productClassTypeEnum":"REGULAR","offerCount":1,"primarySeller":{"entityErrors":[],"sellerId":
      |"BAD7B3C3F8E54B0BB65A5397F24671D2","partnerDisplayName":"Tech For Less Inc","sellerType":"EXTERNAL"},
      |"isPrimaryOfferPUTEligible":false,"storeFrontBuyingOptions":[{"storeFrontType":"ONLINE","storeFrontId":
      |{"storeUUID":"F2AC45A479F04796A584DD9FCE751842","storeId":"0","onlineStoreFront":true,"USStoreId":0},
      |"canAddToCart":true,"availabilityStatus":"AVAILABLE","quantity":2.0,"subChannels":[],"productType":
      |"REGULAR","primaryOfferPrice":{"currencyAmount":1210.97,"currencyUnit":"USD"},"offerFulfillmentOptionWrappers"
      |:[{"fulfillmentOption":"S2H","offerShippingMethodWrappers":[{"shipMethod":"STANDARD","shippingProgramFlags"
      |:[{"label":"FREE_SHIPPING"}]}]}],"shippingProgramFlags":[{"label":"FREE_SHIPPING"}],"fulfillmentOptions":
      |["S2H"],"storePUTEligible":false}],"lastUpdatedOn":1469065707495,"isShippingPassEligible":false,
      |"isInternationalShippingEligible":false}""".stripMargin
  }


  def getEntityAsset: String = {
    """[{"entityErrors":[],"entityId":"6SMUSOBHNM1X","entityType":"PRODUCT","assetGroupId":
      |"EB910E8C69EB44FDAE79890B009AA74E","assetGroupPublishStatus":"CDN","entityAssetGroupVersion":1445415597046,
      |"entityAssetList":[{"entityErrors":[],"assetId":"D6509D8056FC4C409A9142B31E0C740D","assetUrl":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg"
      |,"assetPublishStatus":"PUBLISHED","assetDimensionMap":{"IMAGE_SIZE_450":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=450&odnWidth=450&odnBg=FFFFFF","IMAGE_SIZE_100":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=100&odnWidth=100&odnBg=FFFFFF","IMAGE_SIZE_180":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=180&odnWidth=180&odnBg=FFFFFF","IMAGE_SIZE_60":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=60&odnWidth=60&odnBg=FFFFFF"},"assetType":"PRIMARY","assetRank":1,"width":500,"height":500,
      |"assetAttributeData":{},"assetSizeMap":{"50X50Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=50&odnWidth=50","100X100Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=100&odnWidth=100","55X55Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=55&odnWidth=55","420X420Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=420&odnWidth=420","80X80Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=80&odnWidth=80","2000X2000Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=2000&odnWidth=2000","125X125Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=125&odnWidth=125","215X215Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=215&odnWidth=215","60X60Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=60&odnWidth=60","300X300Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=300&odnWidth=300","75X75Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=75&odnWidth=75","150X150Xfalse":
      |"https://i5.walmartimages.com/asr/6cde5bb0-eaa9-4668-8dea-0468e0ac96ed_1.d801f1f13ad56a96d9a0f09841903ef9.jpeg?
      |odnHeight=150&odnWidth=150"},"providerName":"i-origin.walmartimages.com"}]}]""".stripMargin
  }


  def getFlatProduct: String = {
    getRandom(Array(
      """{"productId":{"productId":"6SMUSOBHNM1X","USItemId":44045022,"legacyItemId":"44045022","upc":"52PDNLNW0371",
        |"wupc":"I52PDNLNW0371","gtin":"II52PDNLNW0371"},"productIdentifiers":{"GTIN":"II52PDNLNW0371","UPC":"52PDNLNW0371",
        |"WUPC":"I52PDNLNW0371"},"productVersion":1469029938035,"productName":"Servertech CS-36VDX813A4 0U
        |Smart PDU - 24 x IEC C13 and 12 x IEC (Refurbished)","shortDescription":"Servertech CS-36VDX813A4 0U Smart PDU with
        |24 x IEC C13 and 12 x IEC C19 Outlets.","longDescription":"Servertech CS-36VDX813A4 0U Smart PDU - 24 x IEC C13 and
        |12 x IEC C19 Outlets. (Refurbished)<br><br> This is a refurbished item restored to meet manufacturer quality standards.
        |They may show limited signs of use and cosmetic blemishes and carry a manufacturer warranty. If the manufacturer
        |is unable to assist with the warranty, Tech For Less will warrant the item with the same coverage as the manufacturer.&#xFFFD;
        | These items can be in the original manufacturer box or repackaged for protection during shipping.<br><ul><li>Model
        | number: CS-36VDX813A4</li><li>Product Name: Servertech CS-36VDX813A4 PDU</li><li>Product Type: PDU</li><li>Outlets:
        | 24 x IEC C13 & 12 x IEC C19</li><li>Rack Space: 0U</li><li>Power Voltage: 208 V</li><li>Input: 60 A</li><li>Cord:
        | 10 feet</li></ul>","manufacturerProductId":"CS-36VDX813A4","msrp":{"currencyAmount":1799.49},"manufacturerName":
        | "Microsoft","productSegment":"Electronics","productSegmentName":"Electronics","productType":"Uninterruptible Power
        | Supplies","productTypeName":"Uninterruptible Power Supplies","productAttributes":{"legacy_UPC":{"value":"52PDNLNW0371
        |","valueRank":0,"name":"legacy_UPC","type":"STRING","displayName":"Legacy UPC","isMultiValued":false},"primary_shelf":
        |{"value":"Surge Protectors","valueRank":0,"name":"primary_shelf","type":"STRING","displayName":"Primary Shelf",
        |"isMultiValued":false,"synonyms":[null]},"alternate_shelves":{"value":"4483","valueRank":0,"name":"alternate_shelves",
        |"type":"STRING","displayName":"Alternate Shelves","isMultiValued":false},"item_type":{"value":"0","valueRank":0,
        |"name":"item_type","type":"STRING","displayName":"Item Type","isMultiValued":false},"pch_id":{"value":"4009053",
        |"valueRank":0,"name":"pch_id","type":"STRING","displayName":"PCH ID","isMultiValued":false},"item_class_id":
        |{"value":"19","valueRank":0,"name":"item_class_id","type":"STRING","displayName":"Item Class ID","isMultiValued":false},
        |"warranty_information":{"value":"Y","valueRank":0,"name":"warranty_information","type":"STRING","displayName":
        |"Warranty Information","isMultiValued":false,"synonyms":["supplier_warranty"]},"primary_category_path":{"value":
        |"0:3944:3951:132959:4483","valueRank":0,"name":"primary_category_path","type":"STRING","displayName":"Primary
        |Category Path","isMultiValued":false},"country_of_origin_components":{"value":"USA and/or Imported",
        |"valueRank":0,"name":"country_of_origin_components","type":"STRING","displayName":"Country of Origin - Components"
        |,"isMultiValued":false,"synonyms":["componentsCountryOfOrigin"]},"product_url_text":{"value":"/ip/Servertech-CS-
        |36VDX813A4-0U-Smart-PDU-24-x-IEC-C13-and-12-x-IEC-Refurbished/44045022","valueRank":0,"name":"product_url_text",
        |"type":"STRING","displayName":"Product URL Text","isMultiValued":false,"synonyms":["url_text_key"]},"primary_shelf_id"
        |:{"value":"4483","valueRank":0,"name":"primary_shelf_id","type":"STRING","displayName":"Primary Shelf ID",
        |"isMultiValued":false},"model":{"value":"CS-36VDX813A4","valueRank":0,"name":"model","type":"STRING","displayName":
        |"Model","isMultiValued":false},"char_primary_category_path":{"value":"Home Page/Electronics/Computers/Computer
        |Accessories/Surge Protectors","valueRank":0,"name":"char_primary_category_path","type":"STRING","displayName":
        |"Character Primary Category Path","isMultiValued":false},"wmt_category":{"value":"Surge_Protectors","valueRank":0,
        |"name":"wmt_category","type":"STRING","displayName":"WMT Category","isMultiValued":false,"synonyms":[null]},
        |"is_always_show":{"value":"N","valueRank":0,"name":"is_always_show","type":"STRING","displayName":"Is Always Show",
        |"isMultiValued":false},"display_status":{"value":"PUBLISHED","valueRank":0,"name":"display_status","type":"STRING",
        |"displayName":"Display Status","isMultiValued":false},"country_of_origin_assembly":{"value":"USA and/or Imported",
        |"valueRank":0,"name":"country_of_origin_assembly","type":"STRING","displayName":"Country of Origin - Assembly",
        |"isMultiValued":false,"synonyms":["assembly_country_of_origin"]},"new":{"value":"N","valueRank":0,"name":"new",
        |"type":"STRING","displayName":"New","isMultiValued":false},"gtin":{"value":"II52PDNLNW0371","valueRank":0,"name":
        |"gtin","type":"STRING","displayName":"GTIN","isMultiValued":false},"pcp_seller_id":{"value":"132","valueRank":0,
        |"name":"pcp_seller_id","type":"STRING","displayName":"PCP Seller ID","isMultiValued":false},"rh_path":{"value":
        |"7043:7044:5202:9786:10332","valueRank":0,"name":"rh_path","type":"STRING","displayName":"RH Path","isMultiValued"
        |:false},"is_preorder":{"value":"N","valueRank":0,"name":"is_preorder","type":"STRING","displayName":"Is Pre-Order"
        |,"isMultiValued":false},"item_id":{"value":"44045022","valueRank":0,"name":"item_id","type":"STRING","displayName":
        |"Item ID","isMultiValued":false},"wupc":{"value":"I52PDNLNW0371","valueRank":0,"name":"wupc","type":"STRING",
        |"displayName":"WUPC","isMultiValued":false},"legacy_GTIN":{"value":"II52PDNLNW0371","valueRank":0,"name":
        |"legacy_GTIN","type":"STRING","displayName":"Legacy GTIN","isMultiValued":false},"upc":{"value":"52PDNLNW0371",
        |"valueRank":0,"name":"upc","type":"STRING","displayName":"UPC","isMultiValued":false},"product_pt_family":
        |{"value":"Home Audio","valueRank":0,"name":"product_pt_family","type":"STRING","displayName":"Product PT Family",
        |"isMultiValued":false,"synonyms":[null]},"item_master_created_date":{"value":"1426245237000","valueRank":0,
        |"name":"item_master_created_date","type":"STRING","displayName":"Item Master Created Date","isMultiValued":false,
        |"synonyms":["item_master_created_dtm"]},"shelf_description":{"value":"Servertech CS-36VDX813A4 0U Smart PDU with
        |24 x IEC C13 and 12 x IEC C19 Outlets.","valueRank":0,"name":"shelf_description","type":"STRING","displayName":
        |"Shelf Description","isMultiValued":false,"synonyms":["product_search_description"]},"data_source":{"value":
        |"Catalog","valueRank":0,"name":"data_source","type":"STRING","displayName":"Data Source","isMultiValued":false},
        |"product_tax_code":{"value":"2038710","valueRank":0,"name":"product_tax_code","type":"STRING","displayName":
        |"Product Tax Code","isMultiValued":false},"legacy_WUPC":{"value":"I52PDNLNW0371","valueRank":0,"name":
        |"legacy_WUPC","type":"STRING","displayName":"Legacy WUPC","isMultiValued":false},"condition":{"value":"Refurbished",
        |"valueRank":0,"name":"condition","type":"STRING","displayName":"Condition","isMultiValued":false,"synonyms":[null]},
        |"all_shelves":{"value":"Surge Protectors","valueRank":0,"name":"all_shelves","type":"STRING","displayName":
        |"All Shelves","isMultiValued":false,"synonyms":[null]},"character_primary_category_path":{"value":
        |"Electronics/Computers/Computer Accessories/Surge Protectors","valueRank":0,"name":"character_primary_category_path",
        |"type":"STRING","displayName":"Character Primary Category Path","isMultiValued":false,"synonyms":[
        |"char_primary_category_path"]},"product_type":{"value":"Uninterruptible Power Supplies","valueRank":0,
        |"name":"product_type","type":"STRING","displayName":"Product Type","isMultiValued":false,"synonyms":[null]},
        |"show_button_in":{"value":"3","valueRank":0,"name":"show_button_in","type":"STRING","displayName":
        |"Show Button In","isMultiValued":false},"reporting_hierarchy_id":{"value":"10332","valueRank":0,"name":
        |"reporting_hierarchy_id","type":"STRING","displayName":"rhid.id","isMultiValued":false},"reporting_hierarchy":
        |{"value":"NOTEBOOK BATTERIES AND POWER ADAPTERS","valueRank":0,"name":"reporting_hierarchy","type":"STRING",
        |"displayName":"Reporting Hierarchy","isMultiValued":false,"synonyms":[null]},"Warranty Length":{"value":
        |"Same as manufacturer","valueRank":0,"name":"Warranty Length","type":"STRING","displayName":"Warranty Length",
        |"isMultiValued":false},"product_segment":{"value":"Electronics","valueRank":0,"name":"product_segment","type":
        |"STRING","displayName":"Product Segment","isMultiValued":false},"category":{"value":"UPS","valueRank":0,"name":
        |"category","type":"STRING","displayName":"Category","isMultiValued":false,"synonyms":["Finer Categorizations"]},
        |"ironbank_category":{"value":"Computer Components","valueRank":0,"name":"ironbank_category","type":"STRING",
        |"displayName":"IronBank Category","isMultiValued":false,"synonyms":[null]},"product_category":{"value":"Electronics",
        |"valueRank":0,"name":"product_category","type":"STRING","displayName":"Product Category","isMultiValued":false,
        |"synonyms":[null]}},"productMarketAttributes":{"new":{"value":"N","valueRank":0,"name":"new","type":"STRING",
        |"displayName":"New","isMultiValued":false},"rh_path":{"value":"7043:7044:5202:9786:10332","valueRank":0,"name":
        |"rh_path","type":"STRING","displayName":"RH Path","isMultiValued":false},"pcp_seller_id":{"value":"132","valueRank":0,
        |"name":"pcp_seller_id","type":"STRING","displayName":"PCP Seller ID","isMultiValued":false},"Finer Categorizations"
        |:{"value":"UPS","valueRank":0,"name":"Finer Categorizations","type":"STRING","displayName":"Category","isMultiValued"
        |:false,"synonyms":["Finer Categorizations"]},"alternate_shelves":{"value":"4483","valueRank":0,"name":
        |"alternate_shelves","type":"STRING","displayName":"Alternate Shelves","isMultiValued":false},"item_type":
        |{"value":"0","valueRank":0,"name":"item_type","type":"STRING","displayName":"Item Type","isMultiValued":false},
        |"pch_id":{"value":"4009053","valueRank":0,"name":"pch_id","type":"STRING","displayName":"PCH ID","isMultiValued":false},
        |"item_class_id":{"value":"19","valueRank":0,"name":"item_class_id","type":"STRING","displayName":"Item Class ID",
        |"isMultiValued":false},"primary_category_path":{"value":"0:3944:3951:132959:4483","valueRank":0,"name":
        |"primary_category_path","type":"STRING","displayName":"Primary Category Path","isMultiValued":false},
        |"item_master_created_date":{"value":"1426245237000","valueRank":0,"name":"item_master_created_date",
        |"type":"STRING","displayName":"Item Master Created Date","isMultiValued":false,"synonyms":
        |["item_master_created_dtm"]},"data_source":{"value":"Catalog","valueRank":0,"name":"data_source",
        |"type":"STRING","displayName":"Data Source","isMultiValued":false},"character_primary_category_path":
        |{"value":"Electronics/Computers/Computer Accessories/Surge Protectors","valueRank":0,"name":
        |"character_primary_category_path","type":"STRING","displayName":"Character Primary Category Path",
        |"isMultiValued":false,"synonyms":["char_primary_category_path"]},"show_button_in":{"value":"3",
        |"valueRank":0,"name":"show_button_in","type":"STRING","displayName":"Show Button In","isMultiValued":false},
        |"product_url_text":{"value":"/ip/Servertech-CS-36VDX813A4-0U-Smart-PDU-24-x-IEC-C13-and-12-x-IEC-Refurbished/44045022",
        |"valueRank":0,"name":"product_url_text","type":"STRING","displayName":"Product URL Text","isMultiValued":false,
        |"synonyms":["url_text_key"]},"primary_shelf_id":{"value":"4483","valueRank":0,"name":"primary_shelf_id",
        |"type":"STRING","displayName":"Primary Shelf ID","isMultiValued":false},"char_primary_category_path":
        |{"value":"Home Page/Electronics/Computers/Computer Accessories/Surge Protectors","valueRank":0,"name":
        |"char_primary_category_path","type":"STRING","displayName":"Character Primary Category Path","isMultiValued":false},
        |"is_always_show":{"value":"N","valueRank":0,"name":"is_always_show","type":"STRING","displayName":"Is Always Show",
        |"isMultiValued":false},"display_status":{"value":"PUBLISHED","valueRank":0,"name":"display_status","type":"STRING",
        |"displayName":"Display Status","isMultiValued":false}},"productClassType":"REGULAR","productPublishStatus":
        |"PUBLISHED","productLifecycleStatus":"ACTIVE","marketProductCrossReferences":[{"identifierName":"ITEM_ID",
        |"subsystemName":"CATALOG","US_WMT_DOTCOM_ITEM_ID":"44045022"}],"complianceAttributes":{"en_US":{"attributes"
        |:[{"value":"USA and/or Imported","valueRank":0,"name":"country_of_origin_components","type":"STRING",
        |"displayName":"Country of Origin - Components","isMultiValued":false},{"value":"USA and/or Imported",
        |"valueRank":0,"name":"country_of_origin_assembly","type":"STRING","displayName":"Country of Origin -
        |Assembly","isMultiValued":false}],"locationName":"en_US"}},"productQuantities":[]}""".stripMargin,

      """{"productId":{"productId":"2G0HDOB02XE9","USItemId":23901549,"legacyItemId":"23901549","upc":"722480221255",
        |"wupc":"0072248022125","gtin":"00722480221255"},"productIdentifiers":{"GTIN":"00722480221255","UPC":"722480221255",
        |"WUPC":"0072248022125"},"productVersion":1465430903192,"productName":"Colour Shaper Silicone Wide Firm Flat Brush",
        |"shortDescription":"Features:  -Firm grey. Dimensions:  Overall Depth - Front to Back: -0.5 Inches.",
        |"longDescription":"COSH1017<br /><strong>Features</strong><br /><li>Firm grey</li> <br /><strong>Dimensions</strong>
        |<br /><li>Overall Depth - Front to Back: 0.5 Inches</li>","assembledProductDimensions":{"productLength":{"unitOfMeasure"
        |:"IN","measurementValue":9},"productWidth":{"unitOfMeasure":"IN","measurementValue":3},"productHeight":{
        |"unitOfMeasure":"IN","measurementValue":0.5}},"manufacturerProductId":"22125","msrp":{"currencyAmount":31.62}
        |,"manufacturerName":"Colour Shaper","brand":"Colour Shaper","productSegment":"default","productSegmentName"
        |:"default","productType":"default","productTypeName":"default","variantSummary":{"variantProductIds":
        |["69D3COW0M1ER","6555PAA9SFGJ","2G0HDOB02XE9","61U83GGCLMPM","5UCWWNAIOYQU"],"primaryProduct":{"productId"
        |:{"productId":"69D3COW0M1ER","USItemId":23901544,"legacyItemId":"23901544","upc":"722480221101","wupc":
        |"0072248022110","gtin":"00722480221101"},"productIdentifiers":{"GTIN":"00722480221101","UPC":"722480221101",
        |"WUPC":"0072248022110"},"productVersion":1465430744721,"productName":"Colour Shaper Silicone Wide Firm
        |Flat Brush","shortDescription":"Features:  -Firm grey. Dimensions:  Overall Depth - Front to Back: -0.5 Inches."
        |,"longDescription":"COSH1017<br /><strong>Features</strong><br /><li>Firm grey</li> <br /><strong>Dimensions
        |</strong><br /><li>Overall Depth - Front to Back: 0.5 Inches</li>","assembledProductDimensions":{
        |"productLength":{"unitOfMeasure":"IN","measurementValue":9},"productWidth":{"unitOfMeasure":"IN",
        |"measurementValue":3},"productHeight":{"unitOfMeasure":"IN","measurementValue":0.5}},
        |"manufacturerProductId":"22110","msrp":{"currencyAmount":25.99},"manufacturerName":"Colour Shaper",
        |"brand":"Colour Shaper","productSegment":"default","productSegmentName":"default","productType":
        |"default","productTypeName":"default","productAttributes":{"primary_shelf":{"value":"Cleaning Tools",
        |"valueRank":0,"name":"primary_shelf","type":"STRING","displayName":"Primary Shelf","isMultiValued":false,
        |"synonyms":[null]},"alternate_shelves":{"value":"1025741","valueRank":0,"name":"alternate_shelves",
        |"type":"STRING","displayName":"Alternate Shelves","isMultiValued":true,"values":[{"value":"1025741",
        |"valueRank":0},{"value":"1067820","valueRank":0},{"value":"1043980","valueRank":0}]},"item_type":{
        |"value":"0","valueRank":0,"name":"item_type","type":"STRING","displayName":"Item Type","isMultiValued":
        |false},"pch_id":{"value":"4011013","valueRank":0,"name":"pch_id","type":"STRING","displayName":"PCH ID",
        |"isMultiValued":false},"item_class_id":{"value":"1","valueRank":0,"name":"item_class_id","type":"STRING",
        |"displayName":"Item Class ID","isMultiValued":false},"warranty_information":{"value":"Warranty not
        |available for this item","valueRank":0,"name":"warranty_information","type":"STRING","displayName":
        |"Warranty Information","isMultiValued":false,"synonyms":["supplier_warranty"]},"primary_category_path":
        |{"value":"0:1115193:1071966:1025741","valueRank":0,"name":"primary_category_path","type":"STRING",
        |"displayName":"Primary Category Path","isMultiValued":false},"size_raw_data":{"value":"1","valueRank":0,
        |"name":"size_raw_data","type":"STRING","displayName":"pcs_size_raw_data","isMultiValued":false,"synonyms"
        |:[null]},"country_of_origin_components":{"value":"Imported","valueRank":0,"name":"country_of_origin_components",
        |"type":"STRING","displayName":"Country of Origin - Components","isMultiValued":false,"synonyms":
        |["componentsCountryOfOrigin"]},"product_url_text":{"value":"/ip/Colour-Shaper-Silicone-Wide-Firm-Flat-Brush/23901544",
        |"valueRank":0,"name":"product_url_text","type":"STRING","displayName":"Product URL Text","isMultiValued":false,
        |"synonyms":["url_text_key"]},"assembled_product_width":{"value":"3.0","valueRank":0,"name":
        |"assembled_product_width","type":"STRING","displayName":"Assembled Product Width","isMultiValued":false,
        |"synonyms":[null]},"primary_shelf_id":{"value":"1025741","valueRank":0,"name":"primary_shelf_id","type":
        |"STRING","displayName":"Primary Shelf ID","isMultiValued":false},"model":{"value":"COSH1017_9600586",
        |"valueRank":0,"name":"model","type":"STRING","displayName":"Model","isMultiValued":false,"synonyms":
        |["manufacturerModelNumber"]},"char_primary_category_path":{"value":"Home Page/Household Essentials/Cleaning
        |Supplies/Cleaning Tools","valueRank":0,"name":"char_primary_category_path","type":"STRING","displayName":
        |"Character Primary Category Path","isMultiValued":false},"wmt_category":{"value":"Paint_Applicators",
        |"valueRank":0,"name":"wmt_category","type":"STRING","displayName":"WMT Category","isMultiValued":false,
        |"synonyms":[null]},"is_always_show":{"value":"N","valueRank":0,"name":"is_always_show","type":"STRING",
        |"displayName":"Is Always Show","isMultiValued":false},"assembled_product_height":{"value":"0.5",
        |"valueRank":0,"name":"assembled_product_height","type":"STRING","displayName":"Assembled Product Height",
        |"isMultiValued":false,"synonyms":[null]},"assembled_product_width_raw_data":{"value":"3.0","valueRank":0,
        |"name":"assembled_product_width_raw_data","type":"STRING","isMultiValued":false,"synonyms":[null]},
        |"assembled_product_height_raw_data":{"value":"0.5","valueRank":0,"name":"assembled_product_height_raw_data",
        |"type":"STRING","displayName":"pcs_assembled_product_height_raw_data","isMultiValued":false,"synonyms":
        |[null]},"display_status":{"value":"PUBLISHED","valueRank":0,"name":"display_status","type":"STRING",
        |"displayName":"Display Status","isMultiValued":false},"country_of_origin_assembly":{"value":"Imported",
        |"valueRank":0,"name":"country_of_origin_assembly","type":"STRING","displayName":"Country of Origin -
        |Assembly","isMultiValued":false,"synonyms":["assembly_country_of_origin"]},"new":{"value":"N","valueRank"
        |:0,"name":"new","type":"STRING","displayName":"New","isMultiValued":false},"gtin":{"value":"00722480221101"
        |,"valueRank":0,"name":"gtin","type":"STRING","displayName":"GTIN","isMultiValued":false},"pcp_seller_id":
        |{"value":"3","valueRank":0,"name":"pcp_seller_id","type":"STRING","displayName":"PCP Seller ID",
        |"isMultiValued":false},"rh_path":{"value":"7043:7047:9790:10589:9794","valueRank":0,"name":"rh_path",
        |"type":"STRING","displayName":"RH Path","isMultiValued":false},"is_preorder":{"value":"N","valueRank":0,
        |"name":"is_preorder","type":"STRING","displayName":"Is Pre-Order","isMultiValued":false},"item_id":
        |{"value":"23901544","valueRank":0,"name":"item_id","type":"STRING","displayName":"Item ID","isMultiValued"
        |:false},"wupc":{"value":"0072248022110","valueRank":0,"name":"wupc","type":"STRING","displayName":"WUPC",
        |"isMultiValued":false},"upc":{"value":"722480221101","valueRank":0,"name":"upc","type":"STRING",
        |"displayName":"UPC","isMultiValued":false},"assembled_product_length":{"value":"9.0","valueRank":0,
        |"name":"assembled_product_length","type":"STRING","displayName":"Assembled Product Length","isMultiValued"
        |:false,"synonyms":[null]},"product_pt_family":{"value":"default","valueRank":0,"name":"product_pt_family",
        |"type":"STRING","displayName":"Product PT Family","isMultiValued":false,"synonyms":[null]},
        |"item_master_created_date":{"value":"1364554373000","valueRank":0,"name":"item_master_created_date",
        |"type":"STRING","displayName":"Item Master Created Date","isMultiValued":false,"synonyms":
        |["item_master_created_dtm"]},"shelf_description":{"value":"Features:  -Firm grey. Dimensions:
        |Overall Depth - Front to Back: -0.5 Inches.","valueRank":0,"name":"shelf_description","type":"STRING",
        |"displayName":"Shelf Description","isMultiValued":false,"synonyms":["product_search_description"]},
        |"data_source":{"value":"Catalog","valueRank":0,"name":"data_source","type":"STRING","displayName":
        |"Data Source","isMultiValued":false},"product_tax_code":{"value":"2038710","valueRank":0,"name":
        |"product_tax_code","type":"STRING","displayName":"Product Tax Code","isMultiValued":false},"all_shelves":
        |{"value":"Paint Applicators","valueRank":0,"name":"all_shelves","type":"STRING","displayName":
        |"All Shelves","isMultiValued":false,"synonyms":[null]},"character_primary_category_path":
        |{"value":"Home Improvement/Paint & Home Decor/Paint Prep & Application/Paint Tools & Supplies",
        |"valueRank":0,"name":"character_primary_category_path","type":"STRING","displayName":
        |"Character Primary Category Path","isMultiValued":false,"synonyms":["char_primary_category_path"]},
        |"product_type":{"value":"default","valueRank":0,"name":"product_type","type":"STRING",
        |"displayName":"Product Type","isMultiValued":false,"synonyms":[null]},"show_button_in":
        |{"value":"3","valueRank":0,"name":"show_button_in","type":"STRING","displayName":"Show Button In",
        |"isMultiValued":false},"size":{"value":"1","valueRank":0,"name":"size","type":"STRING","displayName":
        |"Size","isMultiValued":false,"synonyms":[null]},"reporting_hierarchy":{"value":"LADDERS","valueRank":0,
        |"name":"reporting_hierarchy","type":"STRING","displayName":"Reporting Hierarchy","isMultiValued":false,
        |"synonyms":[null]},"Warranty Length":{"value":"Warranty not available for this item","valueRank":0,
        |"name":"Warranty Length","type":"STRING","displayName":"Warranty Length","isMultiValued":false},
        |"product_segment":{"value":"Home & Garden","valueRank":0,"name":"product_segment","type":"STRING",
        |"displayName":"Product Segment","isMultiValued":false},"assembled_product_length_raw_data":{"value":"9.0",
        |"valueRank":0,"name":"assembled_product_length_raw_data","type":"STRING","isMultiValued":false,"synonyms":
        |[null]},"category":{"value":"Paint Rollers","valueRank":0,"name":"category","type":"STRING","displayName":
        |"Category","isMultiValued":false,"synonyms":[null]},"product_category":{"value":"default","valueRank":0,
        |"name":"product_category","type":"STRING","displayName":"Product Category","isMultiValued":false,
        |"synonyms":[null]}},"abstractProductId":"5090L5OZA7X0","variantCriteria":{"size":{"value":"1",
        |"valueRank":0,"name":"size","type":"STRING","displayName":"Size","isMultiValued":false}},
        |"productMarketAttributes":{"new":{"value":"N","valueRank":0,"name":"new","type":"STRING",
        |"displayName":"New","isMultiValued":false},"rh_path":{"value":"7043:7047:9790:10589:9794",
        |"valueRank":0,"name":"rh_path","type":"STRING","displayName":"RH Path","isMultiValued":false},
        |"pcp_seller_id":{"value":"3","valueRank":0,"name":"pcp_seller_id","type":"STRING","displayName":
        |"PCP Seller ID","isMultiValued":false},"Finer Categorizations":{"value":"Paint Rollers","valueRank":0,
        |"name":"Finer Categorizations","type":"STRING","displayName":"Category","isMultiValued":false,
        |"synonyms":[null]},"alternate_shelves":{"value":"1025741","valueRank":0,"name":"alternate_shelves",
        |"type":"STRING","displayName":"Alternate Shelves","isMultiValued":true,"values":[{"value":"1025741",
        |"valueRank":0},{"value":"1067820","valueRank":0},{"value":"1043980","valueRank":0}]},"item_type":
        |{"value":"0","valueRank":0,"name":"item_type","type":"STRING","displayName":"Item Type","isMultiValued"
        |:false},"pch_id":{"value":"4011013","valueRank":0,"name":"pch_id","type":"STRING","displayName":"PCH ID",
        |"isMultiValued":false},"item_class_id":{"value":"1","valueRank":0,"name":"item_class_id","type":"STRING",
        |"displayName":"Item Class ID","isMultiValued":false},"primary_category_path":{"value":"0:1115193:1071966:1025741",
        |"valueRank":0,"name":"primary_category_path","type":"STRING","displayName":"Primary Category Path",
        |isMultiValued":false},"item_master_created_date":{"value":"1364554373000","valueRank":0,"name":
        |"item_master_created_date","type":"STRING","displayName":"Item Master Created Date","isMultiValued":
        |false,"synonyms":["item_master_created_dtm"]},"data_source":{"value":"Catalog","valueRank":0,"name":
        |"data_source","type":"STRING","displayName":"Data Source","isMultiValued":false},
        |"character_primary_category_path":{"value":"Home Improvement/Paint & Home Decor/Paint Prep &
        | Application/Paint Tools & Supplies","valueRank":0,"name":"character_primary_category_path",
        |"type":"STRING","displayName":"Character Primary Category Path","isMultiValued":false,"synonyms":
        |["char_primary_category_path"]},"show_button_in":{"value":"3","valueRank":0,"name":"show_button_in",
        |"type":"STRING","displayName":"Show Button In","isMultiValued":false},"product_url_text":{"value":
        |"/ip/Colour-Shaper-Silicone-Wide-Firm-Flat-Brush/23901544","valueRank":0,"name":"product_url_text",
        |"type":"STRING","displayName":"Product URL Text","isMultiValued":false,"synonyms":["url_text_key"]},
        |"primary_shelf_id":{"value":"1025741","valueRank":0,"name":"primary_shelf_id","type":"STRING",
        |"displayName":"Primary Shelf ID","isMultiValued":false},"char_primary_category_path":{"value":
        |"Home Page/Household Essentials/Cleaning Supplies/Cleaning Tools","valueRank":0,"name":
        |"char_primary_category_path","type":"STRING","displayName":"Character Primary Category Path",
        |"isMultiValued":false},"is_always_show":{"value":"N","valueRank":0,"name":"is_always_show",
        |"type":"STRING","displayName":"Is Always Show","isMultiValued":false},"display_status":{"value":
        |"PUBLISHED","valueRank":0,"name":"display_status","type":"STRING","displayName":"Display Status",
        |"isMultiValued":false}},"productClassType":"VARIANT","productPublishStatus":"PUBLISHED",
        |"productLifecycleStatus":"ACTIVE","marketProductCrossReferences":[{"identifierName":"ITEM_ID",
        |"subsystemName":"CATALOG","US_WMT_DOTCOM_ITEM_ID":"23901544"}],"complianceAttributes":
        |{"en_US":{"attributes":[{"value":"Imported","valueRank":0,"name":"country_of_origin_components",
        |"type":"STRING","displayName":"Country of Origin - Components","isMultiValued":false},{"value":
        |"Imported","valueRank":0,"name":"country_of_origin_assembly","type":"STRING","displayName":"Country
        |of Origin - Assembly","isMultiValued":false}],"locationName":"en_US"}},"productQuantities":[]},
        |"primaryProductId":{"productId":"69D3COW0M1ER"},"variantCriteria":{"size":[{"value":"1","valueRank":
        |100000,"name":"size","displayName":"Size","isMultiValued":false,"variantResourceType":"DEFAULT"},
        |{"value":"2","valueRank":100002,"name":"size","displayName":"Size","isMultiValued":false,
        |"variantResourceType":"DEFAULT"},{"value":"1.5","valueRank":100001,"name":"size","displayName":
        |"Size","isMultiValued":false,"variantResourceType":"DEFAULT"},{"value":"2.5","valueRank":100003,
        |"name":"size","displayName":"Size","isMultiValued":false,"variantResourceType":"DEFAULT"},
        |{"value":"3","valueRank":100004,"name":"size","displayName":"Size","isMultiValued":false,
        |"variantResourceType":"DEFAULT"}]},"variantInfoMap":{"61U83GGCLMPM":{"size":{"value":"2","valueRank":0,
        |"name":"size","type":"STRING","displayName":"Size","isMultiValued":false}},"5UCWWNAIOYQU":{"size":
        |{"value":"1.5","valueRank":0,"name":"size","type":"STRING","displayName":"Size","isMultiValued":false}},
        |"6555PAA9SFGJ":{"size":{"value":"3","valueRank":0,"name":"size","type":"STRING","displayName":"Size",
        |"isMultiValued":false}},"69D3COW0M1ER":{"size":{"value":"1","valueRank":0,"name":"size","type":"STRING",
        |"displayName":"Size","isMultiValued":false}},"2G0HDOB02XE9":{"size":{"value":"2.5","valueRank":0,"name":
        |"size","type":"STRING","displayName":"Size","isMultiValued":false}}}},"productAttributes":{
        |"primary_shelf":{"value":"Cleaning Tools","valueRank":0,"name":"primary_shelf","type":"STRING",
        |"displayName":"Primary Shelf","isMultiValued":false,"synonyms":[null]},"alternate_shelves":{"value":
        |"1025741","valueRank":0,"name":"alternate_shelves","type":"STRING","displayName":"Alternate Shelves",
        |"isMultiValued":true,"values":[{"value":"1025741","valueRank":0},{"value":"1067820","valueRank":0},
        |{"value":"1043980","valueRank":0}]},"item_type":{"value":"0","valueRank":0,"name":"item_type","type":
        |"STRING","displayName":"Item Type","isMultiValued":false},"pch_id":{"value":"4011013","valueRank":0,
        |"name":"pch_id","type":"STRING","displayName":"PCH ID","isMultiValued":false},"item_class_id":
        |{"value":"1","valueRank":0,"name":"item_class_id","type":"STRING","displayName":"Item Class ID",
        |"isMultiValued":false},"warranty_information":{"value":"Warranty not available for this item",
        |"valueRank":0,"name":"warranty_information","type":"STRING","displayName":"Warranty Information",
        |"isMultiValued":false,"synonyms":["supplier_warranty"]},"primary_category_path":{"value":
        |"0:1115193:1071966:1025741","valueRank":0,"name":"primary_category_path","type":"STRING",
        |"displayName":"Primary Category Path","isMultiValued":false},"size_raw_data":{"value":"2.5",
        |"valueRank":0,"name":"size_raw_data","type":"STRING","displayName":"pcs_size_raw_data",
        |"isMultiValued":false,"synonyms":[null]},"country_of_origin_components":{"value":"Imported",
        |"valueRank":0,"name":"country_of_origin_components","type":"STRING","displayName":"Country of Origin -
        | Components","isMultiValued":false,"synonyms":["componentsCountryOfOrigin"]},"product_url_text":
        |{"value":"/ip/Colour-Shaper-Silicone-Wide-Firm-Flat-Brush/23901549","valueRank":0,"name":
        |"product_url_text","type":"STRING","displayName":"Product URL Text","isMultiValued":false,"synonyms":
        |["url_text_key"]},"assembled_product_width":{"value":"3.0","valueRank":0,"name":
        |"assembled_product_width","type":"STRING","displayName":"Assembled Product Width","isMultiValued"
        |:false,"synonyms":[null]},"primary_shelf_id":{"value":"1025741","valueRank":0,"name":
        |"primary_shelf_id","type":"STRING","displayName":"Primary Shelf ID","isMultiValued":false},
        |"model":{"value":"COSH1017_9600589","valueRank":0,"name":"model","type":"STRING","displayName":
        |"Model","isMultiValued":false,"synonyms":["manufacturerModelNumber"]},"char_primary_category_path":
        |{"value":"Home Page/Household Essentials/Cleaning Supplies/Cleaning Tools","valueRank":0,
        |"name":"char_primary_category_path","type":"STRING","displayName":"Character Primary Category Path",
        |"isMultiValued":false},"wmt_category":{"value":"Paint_Applicators","valueRank":0,"name":"wmt_category",
        |"type":"STRING","displayName":"WMT Category","isMultiValued":false,"synonyms":[null]},"is_always_show":
        |{"value":"N","valueRank":0,"name":"is_always_show","type":"STRING","displayName":"Is Always Show",
        |"isMultiValued":false},"assembled_product_height":{"value":"0.5","valueRank":0,"name":
        |"assembled_product_height","type":"STRING","displayName":"Assembled Product Height",
        |"isMultiValued":false,"synonyms":[null]},"assembled_product_height_raw_data":
        |{"value":"0.5","valueRank":0,"name":"assembled_product_height_raw_data","type":
        |"STRING","displayName":"pcs_assembled_product_height_raw_data","isMultiValued":false,
        |"synonyms":[null]},"assembled_product_width_raw_data":{"value":"3.0","valueRank":0,"name":
        |"assembled_product_width_raw_data","type":"STRING","isMultiValued":false,"synonyms":[null]},
        |"display_status":{"value":"PUBLISHED","valueRank":0,"name":"display_status","type":"STRING",
        |"displayName":"Display Status","isMultiValued":false},"country_of_origin_assembly":{"value":"Imported",
        |"valueRank":0,"name":"country_of_origin_assembly","type":"STRING","displayName":"Country of Origin -
        | Assembly","isMultiValued":false,"synonyms":["assembly_country_of_origin"]},"new":{"value":"N",
        |"valueRank":0,"name":"new","type":"STRING","displayName":"New","isMultiValued":false},"gtin":
        |{"value":"00722480221255","valueRank":0,"name":"gtin","type":"STRING","displayName":"GTIN",
        |"isMultiValued":false},"pcp_seller_id":{"value":"3","valueRank":0,"name":"pcp_seller_id",
        |"type":"STRING","displayName":"PCP Seller ID","isMultiValued":false},"rh_path":{"value":
        |"7043:7047:9790:10589:9794","valueRank":0,"name":"rh_path","type":"STRING","displayName":
        |"RH Path","isMultiValued":false},"item_id":{"value":"23901549","valueRank":0,"name":
        |"item_id","type":"STRING","displayName":"Item ID","isMultiValued":false},"is_preorder":
        |{"value":"N","valueRank":0,"name":"is_preorder","type":"STRING","displayName":"Is Pre-Order",
        |"isMultiValued":false},"wupc":{"value":"0072248022125","valueRank":0,"name":"wupc",
        |"type":"STRING","displayName":"WUPC","isMultiValued":false},"upc":{"value":"722480221255",
        |"valueRank":0,"name":"upc","type":"STRING","displayName":"UPC","isMultiValued":false},
        |"assembled_product_length":{"value":"9.0","valueRank":0,"name":"assembled_product_length",
        |"type":"STRING","displayName":"Assembled Product Length","isMultiValued":false,"synonyms":[null]},
        |"product_pt_family":{"value":"default","valueRank":0,"name":"product_pt_family","type":"STRING",
        |"displayName":"Product PT Family","isMultiValued":false,"synonyms":[null]},
        |"item_master_created_date":{"value":"1364554421000","valueRank":0,"name":"item_master_created_date",
        |"type":"STRING","displayName":"Item Master Created Date","isMultiValued":false,
        |"synonyms":["item_master_created_dtm"]},"shelf_description":{"value":"Features:
        |-Firm grey. Dimensions:  Overall Depth - Front to Back: -0.5 Inches.","valueRank":0,"name":"shelf_description",
        |"type":"STRING","displayName":"Shelf Description","isMultiValued":false,"synonyms":["product_search_description"]},
        |"data_source":{"value":"Catalog","valueRank":0,"name":"data_source","type":"STRING","displayName":"Data Source",
        |"isMultiValued":false},"product_tax_code":{"value":"2038710","valueRank":0,"name":"product_tax_code",
        |"type":"STRING","displayName":"Product Tax Code","isMultiValued":false},"all_shelves":{"value":
        |"Paint Applicators","valueRank":0,"name":"all_shelves","type":"STRING","displayName":"All Shelves",
        |"isMultiValued":false,"synonyms":[null]},"character_primary_category_path":{"value":"Home Improvement/Paint
        | & Home Decor/Paint Prep & Application/Paint Tools & Supplies","valueRank":0,"name":
        |"character_primary_category_path","type":"STRING","displayName":"Character Primary Category Path",
        |"isMultiValued":false,"synonyms":["char_primary_category_path"]},"product_type":{"value":"default",
        |"valueRank":0,"name":"product_type","type":"STRING","displayName":"Product Type","isMultiValued":false,
        |"synonyms":[null]},"show_button_in":{"value":"3","valueRank":0,"name":"show_button_in","type":
        |"STRING","displayName":"Show Button In","isMultiValued":false},"size":{"value":"2.5","valueRank":0,
        |"name":"size","type":"STRING","displayName":"Size","isMultiValued":false,"synonyms":[null]},
        |"reporting_hierarchy":{"value":"LADDERS","valueRank":0,"name":"reporting_hierarchy","type":"STRING",
        |"displayName":"Reporting Hierarchy","isMultiValued":false,"synonyms":[null]},"product_segment":{"value":
        |"Home & Garden","valueRank":0,"name":"product_segment","type":"STRING","displayName":"Product Segment",
        |"isMultiValued":false},"Warranty Length":{"value":"Warranty not available for this item","valueRank":0,
        |"name":"Warranty Length","type":"STRING","displayName":"Warranty Length","isMultiValued":false},
        |"assembled_product_length_raw_data":{"value":"9.0","valueRank":0,"name":"assembled_product_length_raw_data",
        |"type":"STRING","isMultiValued":false,"synonyms":[null]},"category":{"value":"Paint Rollers","valueRank":0,
        |"name":"category","type":"STRING","displayName":"Category","isMultiValued":false,"synonyms":[null]},
        |"product_category":{"value":"default","valueRank":0,"name":"product_category","type":"STRING",
        |"displayName":"Product Category","isMultiValued":false,"synonyms":[null]}},"abstractProductId":
        |"5090L5OZA7X0","variantCriteria":{"size":{"value":"2.5","valueRank":0,"name":"size","type":"STRING",
        |"displayName":"Size","isMultiValued":false}},"productMarketAttributes":{"new":{"value":"N",
        |"valueRank":0,"name":"new","type":"STRING","displayName":"New","isMultiValued":false},"pcp_seller_id":
        |{"value":"3","valueRank":0,"name":"pcp_seller_id","type":"STRING","displayName":"PCP Seller ID",
        |"isMultiValued":false},"rh_path":{"value":"7043:7047:9790:10589:9794","valueRank":0,"name":"rh_path",
        |"type":"STRING","displayName":"RH Path","isMultiValued":false},"alternate_shelves":{"value":"1025741",
        |"valueRank":0,"name":"alternate_shelves","type":"STRING","displayName":"Alternate Shelves","isMultiValued":
        |true,"values":[{"value":"1025741","valueRank":0},{"value":"1067820","valueRank":0},{"value":"1043980",
        |"valueRank":0}]},"Finer Categorizations":{"value":"Paint Rollers","valueRank":0,"name":"Finer Categorizations",
        |"type":"STRING","displayName":"Category","isMultiValued":false,"synonyms":[null]},"item_type":{"value":"0",
        |"valueRank":0,"name":"item_type","type":"STRING","displayName":"Item Type","isMultiValued":false},
        |"pch_id":{"value":"4011013","valueRank":0,"name":"pch_id","type":"STRING","displayName":"PCH ID",
        |"isMultiValued":false},"item_class_id":{"value":"1","valueRank":0,"name":"item_class_id","type":"STRING",
        |"displayName":"Item Class ID","isMultiValued":false},"primary_category_path":{"value":
        |"0:1115193:1071966:1025741","valueRank":0,"name":"primary_category_path","type":"STRING","displayName":
        |"Primary Category Path","isMultiValued":false},"item_master_created_date":{"value":"1364554421000",
        |"valueRank":0,"name":"item_master_created_date","type":"STRING","displayName":"Item Master Created Date",
        |"isMultiValued":false,"synonyms":["item_master_created_dtm"]},"data_source":{"value":"Catalog","valueRank":0,
        |"name":"data_source","type":"STRING","displayName":"Data Source","isMultiValued":false},
        |"character_primary_category_path":{"value":"Home Improvement/Paint & Home Decor/Paint Prep &
        |Application/Paint Tools & Supplies","valueRank":0,"name":"character_primary_category_path",
        |"type":"STRING","displayName":"Character Primary Category Path","isMultiValued":false,"synonyms":
        |["char_primary_category_path"]},"show_button_in":{"value":"3","valueRank":0,"name":"show_button_in",
        |"type":"STRING","displayName":"Show Button In","isMultiValued":false},"product_url_text":
        |{"value":"/ip/Colour-Shaper-Silicone-Wide-Firm-Flat-Brush/23901549","valueRank":0,"name":
        |"product_url_text","type":"STRING","displayName":"Product URL Text","isMultiValued":false,
        |"synonyms":["url_text_key"]},"primary_shelf_id":{"value":"1025741","valueRank":0,"name":
        |"primary_shelf_id","type":"STRING","displayName":"Primary Shelf ID","isMultiValued":false},
        |"char_primary_category_path":{"value":"Home Page/Household Essentials/Cleaning Supplies/Cleaning Tools",
        |"valueRank":0,"name":"char_primary_category_path","type":"STRING","displayName":"Character Primary Category Path",
        |"isMultiValued":false},"is_always_show":{"value":"N","valueRank":0,"name":"is_always_show","type":
        |"STRING","displayName":"Is Always Show","isMultiValued":false},"display_status":{"value":"PUBLISHED",
        |"valueRank":0,"name":"display_status","type":"STRING","displayName":"Display Status","isMultiValued":false}},
        |"productClassType":"VARIANT","productPublishStatus":"PUBLISHED","productLifecycleStatus":"ACTIVE",
        |"marketProductCrossReferences":[{"identifierName":"ITEM_ID","subsystemName":"CATALOG","US_WMT_DOTCOM_ITEM_ID":"23901549"}]
        |,"complianceAttributes":{"en_US":{"attributes":[{"value":"Imported","valueRank":0,"name":
        |"country_of_origin_components","type":"STRING","displayName":"Country of Origin - Components",
        |"isMultiValued":false},{"value":"Imported","valueRank":0,"name":"country_of_origin_assembly",
        |"type":"STRING","displayName":"Country of Origin - Assembly","isMultiValued":false}],"locationName"
        |:"en_US"}},"productQuantities":[]}""".stripMargin
    ))
  }

  def getPrimaryOffer = {
    getRandom(Array(
      """[{"offer":{"tenantId":0,"businessUnit":"WALMART_US","modifiedDtm":1469028163000,"offerId":{"offerId":"8F4DA690C1DF43F8A8E1149E0FE4
        |9176","upc":"52PDNLNW0371","wupc":"I52PDNLNW0371","gtin":"II52PDNLNW0371","USItemId":44045022,"USSellerId":132,"legacyItemId":"440
        |45022","legacySellerId":"132"},"productId":{"productId":"6SMUSOBHNM1X","USItemId":44045022,"legacyItemId":"44045022","upc":"52PDNL
        |NW0371","wupc":"I52PDNLNW0371","gtin":"II52PDNLNW0371"},"offerType":"ONLINE_ONLY","sellerId":"BAD7B3C3F8E54B0BB65A5397F24671D2","s
        |ellerOfferId":"CS-36VDX813A4_C2","offerIdentifiers":{"GTIN":"II52PDNLNW0371","UPC":"52PDNLNW0371","WUPC":"I52PDNLNW0371"},"startDa
        |te":1426230000000,"endDate":64029052801000,"originOfComponents":[{"locationName":"USA and/or
        |Imported"}],"offerPublishStatus":"PUBLISHED","offerLifecycleStatus":"ACTIVE","globalTaxCode":"2038710","offerAttributes":{"is_pay_
        |in_person_eligible":{"value":"false","valueRank":0,"name":"is_pay_in_person_eligible","type":"STRING","displayName":"is_pay_in_per
        |son_eligible","isMultiValued":false},"legacy_UPC":{"value":"52PDNLNW0371","valueRank":0,"name":"legacy_UPC","type":"STRING","displ
        |ayName":"Legacy
        |UPC","isMultiValued":false},"is_preorder":{"value":"N","valueRank":0,"name":"is_preorder","type":"STRING","displayName":"is_preord
        |er","isMultiValued":false},"data_source":{"value":"Catalog","valueRank":0,"name":"data_source","type":"STRING","displayName":"data
        |_source","isMultiValued":false}},"marketOfferCrossReferences":[{"identifierName":"ITEM_ID","US_WMT_DOTCOM_SELLER_ID":"132","subsys
        |temName":"CATALOG","US_WMT_DOTCOM_ITEM_ID":"44045022"}],"marketAttributes":{"new":{"value":"false","valueRank":0,"name":"new","typ
        |e":"STRING","displayName":"new","isMultiValued":false},"lastUpdatedBy":{"value":"us-catalog","valueRank":0,"name":"lastUpdatedBy",
        |"type":"STRING","isMultiValued":false},"pcp_seller_id":{"value":"132","valueRank":0,"name":"pcp_seller_id","type":"STRING","displa
        |yName":"pcp_seller_id","isMultiValued":false},"rh_path":{"value":"7043:7044:5202:9786:10332","valueRank":0,"name":"rh_path","type"
        |:"STRING","displayName":"rh_path","isMultiValued":false},"alternate_shelves":{"value":"4483","valueRank":0,"name":"alternate_shelv
        |es","type":"STRING","displayName":"alternate_shelves","isMultiValued":false},"item_master_created_dtm":{"value":"2015-03-13T11:13:
        |57+0000","valueRank":0,"name":"item_master_created_dtm","type":"STRING","displayName":"item_master_created_dtm","isMultiValued":fa
        |lse},"Legacy_Item_ID":{"value":"44045022","valueRank":0,"name":"Legacy_Item_ID","type":"STRING","displayName":"Legacy_Item_ID","is
        |MultiValued":false},"item_type":{"value":"0","valueRank":0,"name":"item_type","type":"STRING","displayName":"item_type","isMultiVa
        |lued":false},"url_text_key":{"value":"/ip/Servertech-CS-36VDX813A4-0U-Smart-PDU-24-x-IEC-C13-and-12-x-IEC-Refurbished/44045022","v
        |alueRank":0,"name":"url_text_key","type":"STRING","displayName":"url_text_key","isMultiValued":false},"catalog_migration_readiness
        |":{"value":"COMPLETE","valueRank":0,"name":"catalog_migration_readiness","type":"STRING","displayName":"catalog_migration_readines
        |s","isMultiValued":false},"pch_id":{"value":"4009053","valueRank":0,"name":"pch_id","type":"STRING","displayName":"pch_id","isMult
        |iValued":false},"item_class_id":{"value":"19","valueRank":0,"name":"item_class_id","type":"STRING","displayName":"item_class_id","
        |isMultiValued":false},"primary_category_path":{"value":"0:3944:3951:132959:4483","valueRank":0,"name":"primary_category_path","typ
        |e":"STRING","displayName":"primary_category_path","isMultiValued":false},"data_source":{"value":"Catalog","valueRank":0,"name":"da
        |ta_source","type":"STRING","displayName":"data_source","isMultiValued":false},"show_button_in":{"value":"3","valueRank":0,"name":"
        |show_button_in","type":"STRING","displayName":"show_button_in","isMultiValued":false},"partner_id":{"value":"714124","valueRank":0
        |,"name":"partner_id","type":"STRING","displayName":"partner_id","isMultiValued":false},"pg_offer_id":{"value":"8F4DA690C1DF43F8A8E
        |1149E0FE49176","valueRank":0,"name":"pg_offer_id","type":"STRING","displayName":"pg_offer_id","isMultiValued":false},"primary_shel
        |f_id":{"value":"4483","valueRank":0,"name":"primary_shelf_id","type":"STRING","displayName":"primary_shelf_id","isMultiValued":fal
        |se},"char_primary_category_path":{"value":"Electronics/Computers/Computer Accessories /Surge
        |Protectors","valueRank":0,"name":"char_primary_category_path","type":"STRING","displayName":"char_primary_category_path","isMultiV
        |alued":false},"is_always_show":{"value":"N","valueRank":0,"name":"is_always_show","type":"STRING","displayName":"is_always_show","
        |isMultiValued":false},"display_status":{"value":"PUBLISHED","valueRank":0,"name":"display_status","type":"STRING","displayName":"d
        |isplay_status","isMultiValued":false},"seller_id":{"value":"132","valueRank":0,"name":"seller_id","type":"STRING","displayName":"s
        |eller_id","isMultiValued":false}},"offerRank":0,"partnerId":"714124","offerLogistics":{"productShipPackageMeasuresList":[{"product
        |Weight":{"unitOfMeasure":"LB","measurementValue":45},"packageNumber":0}],"mustShipAlone":false,"shipAsIs":false,"numberOfBoxes":1,
        |"giftOpts":{"allowGiftWrap":false,"allowGiftMessage":false,"allowGiftReceipt":false}},"crossBorderTradeAttributes":{"isEligible":f
        |alse},"streetDateDisplayable":true,"externalPartnerId":"714124"},"seller":{"entityErrors":[],"sellerId":"BAD7B3C3F8E54B0BB65A5397F
        |24671D2","catalogSellerId":132,"partnerName":"Tech For Less Inc","partnerDisplayName":"Tech For Less
        |Inc","imageId":-1,"imageUrl":"http://i5.walmartimages.com/dfw/dce07b8c-700a/k2-_f006e978-5d08-4b56-92d3-6546a4361d96.v1.gif","sell
        |erType":"EXTERNAL","sellerLogoURL":"http://i5.walmartimages.com/dfw/dce07b8c-700a/k2-_f006e978-5d08-4b56-92d3-6546a4361d96.v1.gif"
        |,"sellerTaxCodes":[{"taxId":999,"sellerId":132,"tweTaxCode":"2038711","s2sFlag":"N"},{"taxId":104,"sellerId":132,"tweTaxCode":"203
        |8346","s2sFlag":"N"},{"taxId":102,"sellerId":132,"tweTaxCode":"2038346","s2sFlag":"N"},{"taxId":101,"sellerId":132,"tweTaxCode":"2
        |038346","s2sFlag":"N"}],"model":{"modelId":2,"sellerId":132}},"storeFronts":[{"storeFrontId":{"storeUUID":"F2AC45A479F04796A584DD9
        |FCE751842","storeId":"0","onlineStoreFront":true,"USStoreId":0},"storeFrontType":"ONLINE","offerPricing":{"offerPriceId":{"offerId
        |":{"offerId":"8F4DA690C1DF43F8A8E1149E0FE49176","upc":"52PDNLNW0371","wupc":"I52PDNLNW0371","gtin":"II52PDNLNW0371","USItemId":440
        |45022,"USSellerId":132,"legacyItemId":"44045022","legacySellerId":"132"},"storeFrontId":{"storeUUID":"F2AC45A479F04796A584DD9FCE75
        |1842","storeId":"0","onlineStoreFront":true,"USStoreId":0}},"storefrontPricingList":[{"currentPrice":{"currentValue":{"currencyAmo
        |unt":1210.97,"currencyUnit":"USD"}},"currentPriceType":"BASE","effectiveDate":1468846029589}],"entityErrors":[]},"isStorePUTEligib
        |le":false,"offerFulfillmentOptionWrappers":[{"fulfillmentOption":"S2H","availabilityStatus":"AVAILABLE","availableQuantity":2.0,"s
        |ubChannels":[{"name":"SHIPPINGPASS","status":"NOT_AVAILABLE","quantity":0.0}],"offerShippingMethodWrappers":[{"shipMethod":"STANDA
        |RD","shipPrice":{"currencyAmount":0.00,"currencyUnit":"USD"},"shippingProgramFlags":[{"label":"FREE_SHIPPING"}]}]}],"canAddToCart"
        |:true}],"isOfferPUTEligible":false,"isShippingPassEligible":false,"isInternationalShippingEligible":false}] |
        |{"tenantId":0,"businessUnit":"WALMART_US","modifiedDtm":1469028163000,"offerId":{"offerId":"8F4DA690C1DF43F8A8E1149E0FE49176","upc
        |":"52PDNLNW0371","wupc":"I52PDNLNW0371","gtin":"II52PDNLNW0371","USItemId":44045022,"USSellerId":132,"legacyItemId":"44045022","le
        |gacySellerId":"132"},"productId":{"productId":"6SMUSOBHNM1X","USItemId":44045022,"legacyItemId":"44045022","upc":"52PDNLNW0371","w
        |upc":"I52PDNLNW0371","gtin":"II52PDNLNW0371"},"offerType":"ONLINE_ONLY","sellerId":"BAD7B3C3F8E54B0BB65A5397F24671D2","sellerOffer
        |Id":"CS-36VDX813A4_C2","offerIdentifiers":{"GTIN":"II52PDNLNW0371","UPC":"52PDNLNW0371","WUPC":"I52PDNLNW0371"},"startDate":142623
        |0000000,"endDate":64029052801000,"originOfComponents":[{"locationName":"USA and/or
        |Imported"}],"offerPublishStatus":"PUBLISHED","offerLifecycleStatus":"ACTIVE","globalTaxCode":"2038710","offerAttributes":{"is_pay_
        |in_person_eligible":{"value":"false","valueRank":0,"name":"is_pay_in_person_eligible","type":"STRING","displayName":"is_pay_in_per
        |son_eligible","isMultiValued":false},"legacy_UPC":{"value":"52PDNLNW0371","valueRank":0,"name":"legacy_UPC","type":"STRING","displ
        |ayName":"Legacy
        |UPC","isMultiValued":false},"is_preorder":{"value":"N","valueRank":0,"name":"is_preorder","type":"STRING","displayName":"is_preord
        |er","isMultiValued":false},"data_source":{"value":"Catalog","valueRank":0,"name":"data_source","type":"STRING","displayName":"data
        |_source","isMultiValued":false}},"marketOfferCrossReferences":[{"identifierName":"ITEM_ID","US_WMT_DOTCOM_SELLER_ID":"132","subsys
        |temName":"CATALOG","US_WMT_DOTCOM_ITEM_ID":"44045022"}],"marketAttributes":{"new":{"value":"false","valueRank":0,"name":"new","typ
        |e":"STRING","displayName":"new","isMultiValued":false},"lastUpdatedBy":{"value":"us-catalog","valueRank":0,"name":"lastUpdatedBy",
        |"type":"STRING","isMultiValued":false},"pcp_seller_id":{"value":"132","valueRank":0,"name":"pcp_seller_id","type":"STRING","displa
        |yName":"pcp_seller_id","isMultiValued":false},"rh_path":{"value":"7043:7044:5202:9786:10332","valueRank":0,"name":"rh_path","type"
        |:"STRING","displayName":"rh_path","isMultiValued":false},"alternate_shelves":{"value":"4483","valueRank":0,"name":"alternate_shelv
        |es","type":"STRING","displayName":"alternate_shelves","isMultiValued":false},"item_master_created_dtm":{"value":"2015-03-13T11:13:
        |57+0000","valueRank":0,"name":"item_master_created_dtm","type":"STRING","displayName":"item_master_created_dtm","isMultiValued":fa
        |lse},"Legacy_Item_ID":{"value":"44045022","valueRank":0,"name":"Legacy_Item_ID","type":"STRING","displayName":"Legacy_Item_ID","is
        |MultiValued":false},"item_type":{"value":"0","valueRank":0,"name":"item_type","type":"STRING","displayName":"item_type","isMultiVa
        |lued":false},"url_text_key":{"value":"/ip/Servertech-CS-36VDX813A4-0U-Smart-PDU-24-x-IEC-C13-and-12-x-IEC-Refurbished/44045022","v
        |alueRank":0,"name":"url_text_key","type":"STRING","displayName":"url_text_key","isMultiValued":false},"catalog_migration_readiness
        |":{"value":"COMPLETE","valueRank":0,"name":"catalog_migration_readiness","type":"STRING","displayName":"catalog_migration_readines
        |s","isMultiValued":false},"pch_id":{"value":"4009053","valueRank":0,"name":"pch_id","type":"STRING","displayName":"pch_id","isMult
        |iValued":false},"item_class_id":{"value":"19","valueRank":0,"name":"item_class_id","type":"STRING","displayName":"item_class_id","
        |isMultiValued":false},"primary_category_path":{"value":"0:3944:3951:132959:4483","valueRank":0,"name":"primary_category_path","typ
        |e":"STRING","displayName":"primary_category_path","isMultiValued":false},"data_source":{"value":"Catalog","valueRank":0,"name":"da
        |ta_source","type":"STRING","displayName":"data_source","isMultiValued":false},"show_button_in":{"value":"3","valueRank":0,"name":"
        |show_button_in","type":"STRING","displayName":"show_button_in","isMultiValued":false},"partner_id":{"value":"714124","valueRank":0
        |,"name":"partner_id","type":"STRING","displayName":"partner_id","isMultiValued":false},"pg_offer_id":{"value":"8F4DA690C1DF43F8A8E
        |1149E0FE49176","valueRank":0,"name":"pg_offer_id","type":"STRING","displayName":"pg_offer_id","isMultiValued":false},"primary_shel
        |f_id":{"value":"4483","valueRank":0,"name":"primary_shelf_id","type":"STRING","displayName":"primary_shelf_id","isMultiValued":fal
        |se},"char_primary_category_path":{"value":"Electronics/Computers/Computer Accessories /Surge
        |Protectors","valueRank":0,"name":"char_primary_category_path","type":"STRING","displayName":"char_primary_category_path","isMultiV
        |alued":false},"is_always_show":{"value":"N","valueRank":0,"name":"is_always_show","type":"STRING","displayName":"is_always_show","
        |isMultiValued":false},"display_status":{"value":"PUBLISHED","valueRank":0,"name":"display_status","type":"STRING","displayName":"d
        |isplay_status","isMultiValued":false},"seller_id":{"value":"132","valueRank":0,"name":"seller_id","type":"STRING","displayName":"s
        |eller_id","isMultiValued":false}},"offerRank":0,"partnerId":"714124","offerLogistics":{"productShipPackageMeasuresList":[{"product
        |Weight":{"unitOfMeasure":"LB","measurementValue":45},"packageNumber":0}],"mustShipAlone":false,"shipAsIs":false,"numberOfBoxes":1,
        |"giftOpts":{"allowGiftWrap":false,"allowGiftMessage":false,"allowGiftReceipt":false}},"crossBorderTradeAttributes":{"isEligible":f
        |alse},"streetDateDisplayable":true,"externalPartnerId":"714124"}""".stripMargin,

      """|[{"offer":{"tenantId":0,"businessUnit":"WALMART_US","modifiedDtm":1465429433000,"offerId":{"offerId":"9039805A8F244BDC87DCC1289BF4
        |1A47","upc":"722480221255","wupc":"0072248022125","gtin":"00722480221255","USItemId":23901549,"USSellerId":3,"legacyItemId":"23901
        |549","legacySellerId":"3"},"productId":{"productId":"2G0HDOB02XE9","USItemId":23901549,"legacyItemId":"23901549","upc":"7224802212
        |55","wupc":"0072248022125","gtin":"00722480221255"},"offerType":"ONLINE_ONLY","sellerId":"7FA263AB123A4BE9B3F91DD4E6131D1D","selle
        |rOfferId":"COSH1017_9600589","offerIdentifiers":{"GTIN":"00722480221255","UPC":"722480221255","WUPC":"0072248022125"},"startDate":
        |1425542400000,"endDate":1520236800000,"originOfComponents":[{"locationName":"Imported"}],"offerPublishStatus":"PUBLISHED","offerLi
        |fecycleStatus":"ACTIVE","globalTaxCode":"2038710","offerAttributes":{"is_pay_in_person_eligible":{"value":"false","valueRank":0,"n
        |ame":"is_pay_in_person_eligible","type":"STRING","displayName":"is_pay_in_person_eligible","isMultiValued":false},"is_preorder":{"
        |value":"N","valueRank":0,"name":"is_preorder","type":"STRING","displayName":"is_preorder","isMultiValued":false},"data_source":{"v
        |alue":"Catalog","valueRank":0,"name":"data_source","type":"STRING","displayName":"data_source","isMultiValued":false}},"marketOffe
        |rCrossReferences":[{"identifierName":"ITEM_ID","US_WMT_DOTCOM_SELLER_ID":"3","subsystemName":"CATALOG","US_WMT_DOTCOM_ITEM_ID":"23
        |901549"}],"marketAttributes":{"new":{"value":"false","valueRank":0,"name":"new","type":"STRING","displayName":"new","isMultiValued
        |":false},"lastUpdatedBy":{"value":"us-catalog","valueRank":0,"name":"lastUpdatedBy","type":"STRING","isMultiValued":false},"rh_pat
        |h":{"value":"7043:7047:9790:10589:9794","valueRank":0,"name":"rh_path","type":"STRING","displayName":"rh_path","isMultiValued":fal
        |se},"pcp_seller_id":{"value":"3","valueRank":0,"name":"pcp_seller_id","type":"STRING","displayName":"pcp_seller_id","isMultiValued
        |":false},"item_master_created_dtm":{"value":"2013-03-29T10:53:41+0000","valueRank":0,"name":"item_master_created_dtm","type":"STRI
        |NG","displayName":"item_master_created_dtm","isMultiValued":false},"alternate_shelves":{"value":"1043980","valueRank":0,"name":"al
        |ternate_shelves","type":"STRING","displayName":"alternate_shelves","isMultiValued":true,"values":[{"value":"1043980","valueRank":0
        |},{"value":"1067820","valueRank":0}]},"Legacy_Item_ID":{"value":"23901549","valueRank":0,"name":"Legacy_Item_ID","type":"STRING","
        |displayName":"Legacy_Item_ID","isMultiValued":false},"item_type":{"value":"0","valueRank":0,"name":"item_type","type":"STRING","di
        |splayName":"item_type","isMultiValued":false},"url_text_key":{"value":"/ip/Colour-Shaper-Silicone-Wide-Firm-Flat-Brush/23901549","
        |valueRank":0,"name":"url_text_key","type":"STRING","displayName":"url_text_key","isMultiValued":false},"catalog_migration_readines
        |s":{"value":"COMPLETE","valueRank":0,"name":"catalog_migration_readiness","type":"STRING","displayName":"catalog_migration_readine
        |ss","isMultiValued":false},"pch_id":{"value":"4011013","valueRank":0,"name":"pch_id","type":"STRING","displayName":"pch_id","isMul
        |tiValued":false},"item_class_id":{"value":"1","valueRank":0,"name":"item_class_id","type":"STRING","displayName":"item_class_id","
        |isMultiValued":false},"primary_category_path":{"value":"0:1072864:1067617:1067621:1043980","valueRank":0,"name":"primary_category_
        |path","type":"STRING","displayName":"primary_category_path","isMultiValued":false},"data_source":{"value":"Catalog","valueRank":0,
        |"name":"data_source","type":"STRING","displayName":"data_source","isMultiValued":false},"show_button_in":{"value":"3","valueRank":
        |0,"name":"show_button_in","type":"STRING","displayName":"show_button_in","isMultiValued":false},"partner_id":{"value":"255045","va
        |lueRank":0,"name":"partner_id","type":"STRING","displayName":"partner_id","isMultiValued":false},"pg_offer_id":{"value":"9039805A8
        |F244BDC87DCC1289BF41A47","valueRank":0,"name":"pg_offer_id","type":"STRING","displayName":"pg_offer_id","isMultiValued":false},"pr
        |imary_shelf_id":{"value":"1043980","valueRank":0,"name":"primary_shelf_id","type":"STRING","displayName":"primary_shelf_id","isMul
        |tiValued":false},"char_primary_category_path":{"value":"Home Improvement/Paint & Home Decor/Paint Prep & Application/Paint
        |Applicators","valueRank":0,"name":"char_primary_category_path","type":"STRING","displayName":"char_primary_category_path","isMulti
        |Valued":false},"is_always_show":{"value":"N","valueRank":0,"name":"is_always_show","type":"STRING","displayName":"is_always_show",
        |"isMultiValued":false},"display_status":{"value":"PUBLISHED","valueRank":0,"name":"display_status","type":"STRING","displayName":"
        |display_status","isMultiValued":false},"seller_id":{"value":"3","valueRank":0,"name":"seller_id","type":"STRING","displayName":"se
        |ller_id","isMultiValued":false}},"offerRank":0,"partnerId":"255045","offerLogistics":{"productShipPackageMeasuresList":[{"productW
        |eight":{"unitOfMeasure":"LB","measurementValue":0.07},"packageNumber":0}],"mustShipAlone":false,"shipAsIs":false,"numberOfBoxes":1
        |,"giftOpts":{"allowGiftWrap":false,"allowGiftMessage":false,"allowGiftReceipt":false}},"crossBorderTradeAttributes":{"isEligible":
        |false},"streetDateDisplayable":true,"externalPartnerId":"255045"},"seller":{"entityErrors":[],"sellerId":"7FA263AB123A4BE9B3F91DD4
        |E6131D1D","catalogSellerId":3,"partnerName":"Wayfair","partnerDisplayName":"Wayfair","imageId":-1,"imageUrl":"http://i5.walmartima
        |ges.com/dfw/dce07b8c-d8cc/k2-_4e29410c-0dd8-42aa-ad09-5e34c1f7a311.v1.gif","sellerType":"EXTERNAL","sellerLogoURL":"http://i5.walm
        |artimages.com/dfw/dce07b8c-d8cc/k2-_4e29410c-0dd8-42aa-ad09-5e34c1f7a311.v1.gif","sellerTaxCodes":[{"taxId":999,"taxCode":"18450",
        |"sellerId":3,"tweTaxCode":"2038857","s2sFlag":"N"},{"taxId":105,"taxCode":"40000","sellerId":3,"tweTaxCode":"2038350","s2sFlag":"N
        |"},{"taxId":104,"taxCode":"40000","sellerId":3,"tweTaxCode":"2038350","s2sFlag":"N"},{"taxId":103,"taxCode":"40000","sellerId":3,"
        |tweTaxCode":"2038347","s2sFlag":"N"},{"taxId":102,"taxCode":"40000","sellerId":3,"tweTaxCode":"2038347","s2sFlag":"N"},{"taxId":10
        |1,"taxCode":"40000","sellerId":3,"tweTaxCode":"2038347","s2sFlag":"N"}],"model":{"modelId":1,"sellerId":3}},"storeFronts":[{"store
        |FrontId":{"storeUUID":"F2AC45A479F04796A584DD9FCE751842","storeId":"0","onlineStoreFront":true,"USStoreId":0},"storeFrontType":"ON
        |LINE","offerPricing":{"offerPriceId":{"offerId":{"offerId":"9039805A8F244BDC87DCC1289BF41A47","upc":"722480221255","wupc":"0072248
        |022125","gtin":"00722480221255","USItemId":23901549,"USSellerId":3,"legacyItemId":"23901549","legacySellerId":"3"},"storeFrontId":
        |{"storeUUID":"F2AC45A479F04796A584DD9FCE751842","storeId":"0","onlineStoreFront":true,"USStoreId":0}},"storefrontPricingList":[{"c
        |urrentPrice":{"currentValue":{"currencyAmount":31.62,"currencyUnit":"USD"}},"currentPriceType":"BASE","effectiveDate":146400064000
        |0}],"entityErrors":[]},"isStorePUTEligible":false,"offerFulfillmentOptionWrappers":[{"fulfillmentOption":"S2H","availabilityStatus
        |":"NOT_AVAILABLE","availableQuantity":0.0,"subChannels":[{"name":"SHIPPINGPASS","status":"NOT_AVAILABLE","quantity":0.0}],"offerSh
        |ippingMethodWrappers":[{"shipMethod":"STANDARD","shipPrice":{"currencyAmount":4.99,"currencyUnit":"USD"},"shippingProgramFlags":[]
        |},{"shipMethod":"EXPEDITED","shipPrice":{"currencyAmount":19.99,"currencyUnit":"USD"},"shippingProgramFlags":[]}]}],"canAddToCart"
        |:false}],"isOfferPUTEligible":false,"isShippingPassEligible":false,"isInternationalShippingEligible":false}]
      """.stripMargin
    ))
  }

  def getProductId = {
    getRandom(Array(
      """{"productId":"6SMUSOBHNM1X","USItemId":44045022,"legacyItemId":"44045022","upc":"52PDNLNW0371","wupc":"I52PDNLNW0371",
        |"gtin":"II52PDNLNW0371"}""".stripMargin,
      """{"productId":"2G0HDOB02XE9","USItemId":23901549,"legacyItemId":"23901549","upc":"722480221255","wupc":"0072248022125",
        |"gtin":"00722480221255"}""".stripMargin
    ))
  }

  def getVarientProduct = {
    getRandom(Array(
      """{"61U83GGCLMPM":{"productId":{"productId":"61U83GGCLMPM","USItemId":23901548,"legacyItemId":"23901548","upc":"722480221200","wupc"
        |:"0072248022120","gtin":"00722480221200"},"availabilityStatus":"NOT_AVAILABLE","entityErrors":[],"isTrueAvailability":true,"primar
        |yOfferPrice":{"currencyAmount":28.80,"currencyUnit":"USD"},"offerSummaries":[{"offerId":{"offerId":"04C3CFE1864441C39A6D83122BA40A
        |22","upc":"722480221200","wupc":"0072248022120","gtin":"00722480221200","USItemId":23901548,"USSellerId":3,"legacyItemId":"2390154
        |8","legacySellerId":"3"},"offerType":"ONLINE_ONLY"}]},"5UCWWNAIOYQU":{"productId":{"productId":"5UCWWNAIOYQU","USItemId":23901545,
        |"legacyItemId":"23901545","upc":"722480221156","wupc":"0072248022115","gtin":"00722480221156"},"availabilityStatus":"NOT_AVAILABLE
        |","entityErrors":[],"isTrueAvailability":true,"primaryOfferPrice":{"currencyAmount":28.80,"currencyUnit":"USD"},"offerSummaries":[
        |{"offerId":{"offerId":"C9AAA0E612114AA69B4A3707C70FAE6D","upc":"722480221156","wupc":"0072248022115","gtin":"00722480221156","USIt
        |emId":23901545,"USSellerId":3,"legacyItemId":"23901545","legacySellerId":"3"},"offerType":"ONLINE_ONLY"}]},"6555PAA9SFGJ":{"produc
        |tId":{"productId":"6555PAA9SFGJ","USItemId":23901550,"legacyItemId":"23901550","upc":"722480221309","wupc":"0072248022130","gtin":
        |"00722480221309"},"availabilityStatus":"NOT_AVAILABLE","entityErrors":[],"isTrueAvailability":true,"primaryOfferPrice":{"currencyA
        |mount":35.76,"currencyUnit":"USD"},"offerSummaries":[{"offerId":{"offerId":"9C7C0F82032A4C24B018B36686ACB727","upc":"722480221309"
        |,"wupc":"0072248022130","gtin":"00722480221309","USItemId":23901550,"USSellerId":3,"legacyItemId":"23901550","legacySellerId":"3"}
        |,"offerType":"ONLINE_ONLY"}]},"5090L5OZA7X0":{"productId":{"productId":"5090L5OZA7X0"},"availabilityStatus":"NOT_AVAILABLE","entit
        |yErrors":[],"isTrueAvailability":true,"minPrice":{"currencyAmount":25.99,"currencyUnit":"USD"},"maxPrice":{"currencyAmount":35.76,
        |"currencyUnit":"USD"},"offerSummaries":[{"offerId":{"offerId":"2D0D91CE8D814DFE9FAD75C6FE72B4B3","upc":"722480221101","wupc":"0072
        |248022110","gtin":"00722480221101","USItemId":23901544,"USSellerId":3,"legacyItemId":"23901544","legacySellerId":"3"},"offerType":
        |"ONLINE_ONLY"}]},"69D3COW0M1ER":{"productId":{"productId":"69D3COW0M1ER","USItemId":23901544,"legacyItemId":"23901544","upc":"7224
        |80221101","wupc":"0072248022110","gtin":"00722480221101"},"availabilityStatus":"NOT_AVAILABLE","entityErrors":[],"isTrueAvailabili
        |ty":true,"primaryOfferPrice":{"currencyAmount":25.99,"currencyUnit":"USD"},"offerSummaries":[{"offerId":{"offerId":"2D0D91CE8D814D
        |FE9FAD75C6FE72B4B3","upc":"722480221101","wupc":"0072248022110","gtin":"00722480221101","USItemId":23901544,"USSellerId":3,"legacy
        |ItemId":"23901544","legacySellerId":"3"},"offerType":"ONLINE_ONLY"}]},"2G0HDOB02XE9":{"productId":{"productId":"2G0HDOB02XE9","USI
        |temId":23901549,"legacyItemId":"23901549","upc":"722480221255","wupc":"0072248022125","gtin":"00722480221255"},"availabilityStatus
        |":"NOT_AVAILABLE","entityErrors":[],"isTrueAvailability":true,"primaryOfferPrice":{"currencyAmount":31.62,"currencyUnit":"USD"},"o
        |fferSummaries":[{"offerId":{"offerId":"9039805A8F244BDC87DCC1289BF41A47","upc":"722480221255","wupc":"0072248022125","gtin":"00722
        |480221255","USItemId":23901549,"USSellerId":3,"legacyItemId":"23901549","legacySellerId":"3"},"offerType":"ONLINE_ONLY"}]}}""".stripMargin,
      ""
    ))
  }

  def getTimestamp = {
    faker.date.past(2, TimeUnit.DAYS)
  }


  def getIdType = {
    getRandom(idTypes)
  }

}
