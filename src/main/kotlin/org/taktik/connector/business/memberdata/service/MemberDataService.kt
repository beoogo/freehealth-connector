package org.taktik.connector.business.memberdata.service

import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import org.taktik.freehealth.middleware.domain.common.CarenetPlatform

interface MemberDataService {
    @Throws(TechnicalConnectorException::class)
    fun consultMemberData(platform: CarenetPlatform, token: SAMLToken, request: MemberDataConsultationRequest): MemberDataConsultationResponse
}
