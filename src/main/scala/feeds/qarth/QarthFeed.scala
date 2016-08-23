package feeds.qarth

import java.util.UUID

import core.BaseFeed
import org.slf4j.LoggerFactory


class QarthFeed extends BaseFeed {

  val logger = LoggerFactory.getLogger(this.getClass)


  def createFeed = {
    Iterator.continually(Map(
      "apid" -> UUID.randomUUID().toString, // b2ff7596-0726-4bcf-87ad-1c79dbc63b0c,
      "wpid" -> faker.bothify("#???##??????"), // 1GEK89QUYUGI
      "is_primary" -> getRandom(bool),
      "merge_meta" -> getRandom(Array("", "{ \"old\" : { \"org_id\" : \"7fa263ab-123a-4be9-b3f9-1dd4e6131d1d\" , \"requestId\" : \"PIG-D03442C5960341328001B4826BCAE486_AQABAAA/QSUN4731_10691174/0\" , \"source\" : \"mp\" , \"qid\" : \"WALMART_DOTCOM#7fa263ab-123a-4be9-b3f9-1dd4e6131d1d#36811237\" , \"original_source\" : \"WALMART_DOTCOM\" , \"content_lifecycle_status\" : \"PUBLISHED\"}}")),
      "update_time" -> faker.numerify("1468423######").toLong,
      "variant_criteria" -> getRandom(Array("[ \"size\"]", "[ \"clothing_size\" , \"size\"]")),

      "swpid" -> UUID.randomUUID().toString,
      "sapid" -> UUID.randomUUID().toString,
      "org_id" -> UUID.randomUUID().toString,
      "svgid" -> getRandom(Array(
        UUID.randomUUID().toString.filterNot(Set("-")),
        faker.bothify("#???##??????")
      )),
      "variant_metadata" -> getRandom(Array(
        """{ \"clothing_size\" : { \"values\" : [ { \"locale\" : \"en_US\" , \"valueRank\" : \"100002\" , \"value\" : \"YTH (18-20)\" , \"originalValueRank\" : \"0\"}] , \"properties\" : { \"variantResourceType\" : \"DEFAULT\" , \"updated\" : \"Fri Aug 12 22:24:00 UTC 2016\" , \"added\" : \"Tue Aug 09 08:35:34 UTC 2016\" , \"attributeName\" : \"Clothing Size\" , \"org_id\" : \"4362915f-ea60-4030-b519-5ca8197e8bf8\" , \"fromPcp\" : \"false\" , \"source\" : \"mp\" , \"content_lifecycle_status\" : \"STAGING\" , \"attributeId\" : \"10804\" , \"requestId\" : \"PIG-de36d2c4-98e6-4045-b95c-f1fb2e7d56f2/A12-157-LG/533\" , \"qid\" : \"MARKETPLACE_PARTNER#4362915f-ea60-4030-b519-5ca8197e8bf8#A12-157-LG\" , \"taxonomy_version\" : \"urn:taxonomy:pcs2.0\" , \"multiselect\" : \"N\"}} , \"size\" : { \"values\" : [ { \"locale\" : \"en_US\" , \"valueRank\" : \"100002\" , \"value\" : \"YTH (18-20)\" , \"originalValueRank\" : \"0\"}] , \"properties\" : { \"variantResourceType\" : \"DEFAULT\" , \"updated\" : \"Fri Aug 12 22:24:00 UTC 2016\" , \"added\" : \"Tue Aug 09 08:35:34 UTC 2016\" , \"attributeName\" : \"Size\" , \"isFacet\" : \"true\" , \"org_id\" : \"4362915f-ea60-4030-b519-5ca8197e8bf8\" , \"fromPcp\" : \"false\" , \"source\" : \"mp\" , \"content_lifecycle_status\" : \"STAGING\" , \"attributeId\" : \"90126\" , \"requestId\" : \"PIG-de36d2c4-98e6-4045-b95c-f1fb2e7d56f2/A12-157-LG/533\" , \"qid\" : \"MARKETPLACE_PARTNER#4362915f-ea60-4030-b519-5ca8197e8bf8#A12-157-LG\" , \"taxonomy_version\" : \"urn:taxonomy:pcs2.0\" , \"multiselect\" : \"N\"}}}""",
        """{ \"size\" : { \"values\" : [ { \"locale\" : \"en_US\" , \"isVariant\" : \"true\" , \"source_key\" : \"Size\" , \"value\" : \"40\\" L x 30 \\ " W\" , \"valueRank\" : \"100000\" , \"display_attr_name\" : \"Size\" , \"source_value\" : \"40\\" L x 30 \\ " W\" , \"isPrimary\" : \"true\" , \"originalValueRank\" : \"1\"}] , \"properties\" : { \"attributeName\" : \"Size\" , \"variantResourceType\" : \"DEFAULT\" , \"updated\" : \"Wed Jul 13 15:18:54 UTC 2016\" , \"added\" : \"Thu Mar 26 21:30:23 UTC 2015\" , \"qid\" : \"WALMART_DOTCOM#7fa263ab-123a-4be9-b3f9-1dd4e6131d1d#36811237\" , \"isFacet\" : \"true\" , \"org_id\" : \"7fa263ab-123a-4be9-b3f9-1dd4e6131d1d\" , \"fromPcp\" : \"false\" , \"source\" : \"mp\" , \"content_lifecycle_status\" : \"PUBLISHED\" , \"multiselect\" : \"N\" , \"requestId\" : \"PIG-D03442C5960341328001B4826BCAE486_AQABAAA/QSUN4731_10691174/0\" , \"taxonomy_version\" : \"urn:taxonomy:pcs2.0\" , \"attributeId\" : \"90126\" , \"type\" : \"LOCALIZABLE_TEXT\"}}}"""
      ))
    ))
  }

}
