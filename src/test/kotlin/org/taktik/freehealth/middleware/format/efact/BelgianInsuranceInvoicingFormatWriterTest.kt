package org.taktik.freehealth.middleware.format.efact

import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.taktik.freehealth.middleware.domain.common.Insurability
import org.taktik.freehealth.middleware.domain.common.InsuranceParameter
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.efact.InvoiceItem
import org.taktik.freehealth.middleware.dto.efact.InvoiceSender
import org.taktik.freehealth.middleware.dto.efact.InvoicingTreatmentReasonCode
import org.taktik.freehealth.utils.FuzzyValues
import java.io.StringWriter
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@RunWith(Enclosed::class)
class BelgianInsuranceInvoicingFormatWriterTest {

    class Record20 {
        private val str = StringWriter()
        private val writer = BelgianInsuranceInvoicingFormatWriter(str)

        @Test
        fun record20_empty() {
            val sender = InvoiceSender().apply {
                nihii = 10000000000
            }
            val patient = Patient().apply {
                ssin = "00000000097"
                insurabilities = arrayListOf(Insurability().apply {
                    parameters = mutableMapOf(InsuranceParameter.tc1 to "100", InsuranceParameter.tc2 to "100")
                })
            }

            writer.writeRecordHeader(
                1,
                sender,
                null,
                InvoicingTreatmentReasonCode.Other,
                "3456f",
                patient,
                "300",
                ignorePrescriptionDate = true,
                hospitalisedPatient = false,
                creditNote = false,
                relatedBatchSendNumber = null,
                relatedBatchYearMonth = null,
                relatedInvoiceIoCode = null,
                relatedInvoiceNumber = null,
                magneticInvoice = false,
                startOfCoveragePeriod = 20180401,
                admissionStartDate = 20190401,
                admissionStartTime = 1110,
                admissionEndDate = 20190430,
                admissionEndTime = 1213
            )

            Assert.assertEquals(
                "200000010000111020190401201904303000000000000097230000001000000000000000000000010000300000000000000201804010121300000000000000000001001003456f                    00000000000010000000                      000000000000000000000000000000000000000000000000000000000000000000                      0000000000000000000000000000000000000000000000000000000092",
                str.toString()
            )
        }
    }

    class Record30 {
        private val str = StringWriter()
        private val writer = BelgianInsuranceInvoicingFormatWriter(str)

        @Test
        fun record30_empty() {
            val sender = InvoiceSender().apply {
                nihii = 0
            }
            val patient = Patient()
            val icd = InvoiceItem().apply {
                dateCode = FuzzyValues.getFuzzyDate(LocalDateTime.of(2022, 6, 20, 2, 2), ChronoUnit.DAYS)
            }

            writer.writeRecordMaintenanceDayContent(1, sender, patient, icd, "300")

            Assert.assertEquals(
                "300000010000000020220620202206203000000000000000200099000000000000000000000000000000000+0000000000000000000+0000000           00                                  00+00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000034",
                str.toString()
            )
        }

        @Test
        fun record30_withData() {
            val sender = InvoiceSender().apply {
                nihii = 0
            }
            val patient = Patient().apply {
                ssin = "00000000097"
            }
            val icd = InvoiceItem().apply {
                val fixedDate = LocalDateTime.of(2022, 1, 1, 11, 0)

                dateCode = FuzzyValues.getFuzzyDate(fixedDate, ChronoUnit.DAYS)
                endDateCode = FuzzyValues.getFuzzyDate(fixedDate.plusDays(30), ChronoUnit.DAYS)
                nbDays = 20
                codeNomenclature = 763033
            }

            writer.writeRecordMaintenanceDayContent(1, sender, patient, icd, "300")

            Assert.assertEquals(
                "300000010076303320220101202201313000000000000097200099000000000000000000000000000000000+0000000000000000000+0020000           00                                  00+00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000065",
                str.toString()
            )
        }

        @Test
        fun record30_withIncontinentCode() {
            val sender = InvoiceSender().apply {
                nihii = 0
            }
            val patient = Patient().apply {
                ssin = "00000000097"
            }
            val icd = InvoiceItem().apply {
                val fixedDate = LocalDateTime.of(2022, 1, 1, 11, 0)

                dateCode = FuzzyValues.getFuzzyDate(fixedDate, ChronoUnit.DAYS)
                endDateCode = FuzzyValues.getFuzzyDate(fixedDate.plusDays(30), ChronoUnit.DAYS)
                nbDays = 20
                codeNomenclature = 763593
                doctorSupplement = 15533
            }

            writer.writeRecordMaintenanceDayContent(1, sender, patient, icd, "300")

            Assert.assertEquals(
                "300000010076359320220101202201313000000000000097200099000000000000000000000000000000000+0000000000000000000+0020000           00                                  00+00001553300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000093",
                str.toString()
            )
        }
    }

    class Record50 {
        private val str = StringWriter()
        private val writer = BelgianInsuranceInvoicingFormatWriter(str)

        @Test
        fun record50_empty() {
            val sender = InvoiceSender().apply {
                nihii = 100000000111
            }
            val year = 2022
            val month = 1
            val patient = Patient().apply {
                ssin = "00000000097"
            }
            val ioCode = "300"
            val icd = InvoiceItem().apply {
                dateCode = FuzzyValues.getFuzzyDate(LocalDateTime.of(2022, 1, 1, 1, 1), ChronoUnit.DAYS)
            }

            writer.writeRecordContent(1, sender, year, month, patient, ioCode, icd)

            Assert.assertEquals(
                "500000010000000020220101202201013000000000000097200099000000000000000000000000010000000+0000000000000000000+0000000000000000000+000000000                         00+00000000000000000000000000000000000000000000000000000000000000000                              0+0000000000000000000000000000000000000000000000000000000000000000000000000000000000000021",
                str.toString()
            )
        }

        @Test
        fun record50_withMMData() {
            val sender = InvoiceSender().apply {
                nihii = 10000000111
            }
            val year = 2019
            val month = 4
            val patient = Patient().apply {
                ssin = "00000000097"
                gender = Gender.male
            }
            val ioCode = "509"
            val icd = InvoiceItem().apply {
                dateCode = 20190401
                endDateCode = 20190430
                codeNomenclature = 109616
                invoiceRef = "3698A"
                reimbursedAmount = 1597
                units = 1
            }

            writer.writeRecordContent(801, sender, year, month, patient, ioCode, icd)

            Assert.assertEquals(
                "500008010010961620190401201904300000000000000097100099000000000000000000000000010000000+0000000159700000000+0001000000000000000+0000000003698A                    00+00000000000000000000000000000000000000000000000000000000000000000                              000000000000000000000000000000000000000000000000000000000000000000000000000000000000000041",
                str.toString()
            )
        }

        @Test
        fun record50_withRHData() {
            val sender = InvoiceSender().apply {
                nihii = 73800000000
            }
            val year = 2019
            val month = 4
            val patient = Patient().apply {
                ssin = "00000000097"
                gender = Gender.male
            }
            val ioCode = "509"
            val icd = InvoiceItem().apply {
                dateCode = 20190401
                codeNomenclature = 111111
                reimbursedAmount = 1597
                units = 1
            }

            writer.writeRecordContent(801, sender, year, month, patient, ioCode, icd)

            Assert.assertEquals(
                "500008010011111120190401201904010000000000000097100099007380000000000000000000000000000+0000000159700000000+0001000000000000000+000000000                         00+00000000000000000000000000000000000000000000000000000000000000000                              000000000000000000000000000000000000000000000000000000000000000000000000000000000000000052",
                str.toString()
            )
        }
    }
}
