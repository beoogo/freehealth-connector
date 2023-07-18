package org.taktik.connector.business.agreement.service

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementRequest
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.freehealth.middleware.domain.common.CarenetPlatform

interface AgreementService {
    @Throws(TechnicalConnectorException::class)
    fun askAgreement(samlToken: SAMLToken, platform: CarenetPlatform, askAgreementRequest: AskAgreementRequest?): AskAgreementResponse?

    @Throws(TechnicalConnectorException::class)
    fun consultAgreement(samlToken: SAMLToken, platform: CarenetPlatform, consultAgreementRequest: ConsultAgreementRequest?): ConsultAgreementResponse?
}
