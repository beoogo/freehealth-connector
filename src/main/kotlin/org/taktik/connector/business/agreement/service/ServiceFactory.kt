package org.taktik.connector.business.agreement.service

import org.apache.commons.lang.Validate
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType
import org.taktik.freehealth.middleware.domain.common.CarenetPlatform

object ServiceFactory {
    private const val PROP_ENDPOINT_AGREEMENT = "endpoint.agreement"
    private val expectedProps: List<String?> = listOf();
    private var config: Configuration

    @Throws(TechnicalConnectorException::class)
    fun getAgreementPort(token: SAMLToken?, platform: CarenetPlatform): GenericRequest {
        Validate.notNull(token, "Required parameter SAMLToken is null.")

        val baseUrlKey = "$PROP_ENDPOINT_AGREEMENT.${platform.name.toLowerCase()}"
        val baseUrl =
            if (config.hasProperty(baseUrlKey))
                config.getProperty(baseUrlKey, "\$uddi{uddi:ehealth-fgov-be:business:mycareneteagreement:v1}")
            else
                config.getProperty(PROP_ENDPOINT_AGREEMENT, "\$uddi{uddi:ehealth-fgov-be:business:mycareneteagreement:v1}")

        return GenericRequest().setEndpoint(baseUrl).setCredential(token, TokenType.SAML).addDefaulHandlerChain()
    }

    init {
        config = ConfigFactory.getConfigValidator(expectedProps)
    }
}
