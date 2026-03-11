package org.taktik.freehealth.middleware.web.controllers

import ma.glasnost.orika.MapperFacade
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.taktik.connector.business.domain.agreement.EAgreementResponse
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EagreementService
import org.taktik.freehealth.middleware.service.impl.EagreementServiceImpl
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/eagreement")
class EagreementController(val eagreementService: EagreementService, val mapper: MapperFacade) {
    @Value("\${mycarenet.timezone}")
    internal val mcnTimezone: String = "Europe/Brussels"

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingTokenException::class)
    @ResponseBody
    fun handleUnauthorizedRequest(req: HttpServletRequest, ex: Exception): String =
        ex.message ?: "unknown reason"

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: Exception): String =
        ex.message ?: "unknown reason"

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(javax.xml.ws.soap.SOAPFaultException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: javax.xml.ws.soap.SOAPFaultException): String =
        ex.message ?: "unknown reason"

    data class Attachment(
        val type: String,
        val data: String
    )

    @PostMapping("/askAgreement", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun askAgreement(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam sctCode: String,
        @RequestParam(required = false) pathologyStartDate: Int?,
        @RequestParam(required = false) pathologyCode: String?,
        @RequestParam(required = false) prescriptionDate: Int?,
        @RequestParam(required = false) prescriberNihii: String?,
        @RequestParam(required = false) prescriberFirstName: String?,
        @RequestParam(required = false) prescriberLastName: String?,
        @RequestParam(required = false) sctDisplay: String?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementStartDate: Int?,
        @RequestParam(required = false) agreementEndDate: Int?,
        @RequestParam(required = false) agreementType: String?,
        @RequestParam(required = false) numberOfSessionForPrescription1: Float?,
        @RequestParam(required = false) numberOfSessionForPrescription2: Float?,
        @RequestBody(required = false) attachments: List<Attachment>?
    ): EAgreementResponse? {
        val formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")
        return eagreementService.askAgreement(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            requestType = EagreementServiceImpl.RequestTypeEnum.ASK,
            hcpQuality = hcpQuality,
            messageEventSystem = EagreementServiceImpl.MessageEventSystemEnum.MESSAGE_EVENTS,
            messageEventCode = "claim-ask",
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            patientSsin = patientSsin,
            patientIo = patientIo,
            patientIoMembership = patientIoMembership,
            pathologyStartDate = pathologyStartDate?.let { formatter.parseDateTime(it.toString()) },
            pathologyCode = pathologyCode,
            insuranceRef = null,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            prescriberNihii = prescriberNihii,
            prescriberFirstName = prescriberFirstName,
            prescriberLastName = prescriberLastName,
            orgNihii = orgNihii,
            organizationType = organizationType,
            prescription1 = attachments?.find { it.type == "prescription1" }?.data,
            prescription2 = attachments?.find { it.type == "prescription2" }?.data,
            agreementStartDate = if (agreementStartDate != null ) formatter.parseDateTime(agreementStartDate.toString()) else null,
            agreementEndDate = if (agreementEndDate != null) formatter.parseDateTime(agreementEndDate.toString()) else null,
            agreementType = agreementType,
            numberOfSessionForPrescription1 = numberOfSessionForPrescription1,
            numberOfSessionForPrescription2 = numberOfSessionForPrescription2,
            sctCode = sctCode,
            prescriptionDate = prescriptionDate?.let { formatter.parseDateTime(it.toString()) },
            sctDisplay = sctDisplay,
            attachments = attachments?.filter { it.type != "prescription1" && it.type != "prescription2" }
        )
    }

    @PostMapping("/consultList", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun consultList(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpQuality: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam subTypeCode: String,
        @RequestParam(required = false) insuranceRef: String?,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) patientIo: String?,
        @RequestParam(required = false) patientIoMembership: String?,
        @RequestParam(required = false) orgNihii: String?,
        @RequestParam(required = false) organizationType: String?,
        @RequestParam(required = false) agreementStartDate: Int?,
        @RequestParam(required = false) agreementEndDate: Int?,
        @RequestParam(required = false) agreementType: String?
    ): EAgreementResponse? {
        val formatter = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMdd")
        return eagreementService.consultAgreementList(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            requestType = EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST,
            hcpQuality = hcpQuality,
            messageEventSystem = EagreementServiceImpl.MessageEventSystemEnum.INTERACTION,
            messageEventCode = EagreementServiceImpl.RequestTypeEnum.CONSULT_LIST.requestType,
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            patientSsin = patientSsin,
            patientIo = patientIo,
            patientIoMembership = patientIoMembership,
            insuranceRef = insuranceRef,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpFirstName = hcpFirstName,
            hcpLastName = hcpLastName,
            subTypeCode = subTypeCode,
            orgNihii = orgNihii,
            organizationType = organizationType,
            agreementStartDate = if (agreementStartDate != null) formatter.parseDateTime(agreementStartDate.toString()) else null,
            agreementEndDate = if (agreementEndDate != null) formatter.parseDateTime(agreementEndDate.toString()) else null,
            agreementType = agreementType
        )
    }

}
