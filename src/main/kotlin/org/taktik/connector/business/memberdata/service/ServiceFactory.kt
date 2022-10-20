package org.taktik.connector.business.memberdata.service

import org.apache.commons.lang.Validate
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType

object ServiceFactory {
    private const val PROP_ENDPOINT_MEMBERDATASYNC = "endpoint.memberdata"
    private val expectedProps = ArrayList<String>()
    private val config: Configuration

    @Throws(TechnicalConnectorException::class)
    fun getMemberDataSyncPort(token: SAMLToken): GenericRequest {
        Validate.notNull(token, "Required parameter SAMLToken is null.")

        val baseUrlKey = "$PROP_ENDPOINT_MEMBERDATASYNC.${token.quality}"
        val baseUrl =
            if (config.hasProperty(baseUrlKey))
                config.getProperty(baseUrlKey, "\$uddi{uddi:ehealth-fgov-be:business:mycarenetmemberdata:v1}")
            else
                config.getProperty(PROP_ENDPOINT_MEMBERDATASYNC, "\$uddi{uddi:ehealth-fgov-be:business:iriscarenetmemberdata:v1}")

        return GenericRequest().setEndpoint(baseUrl).setCredential(token, TokenType.SAML).addDefaulHandlerChain()
    }

    init {
        config = ConfigFactory.getConfigValidator(expectedProps)
    }
}
