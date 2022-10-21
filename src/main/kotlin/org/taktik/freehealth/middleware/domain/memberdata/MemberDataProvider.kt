package org.taktik.freehealth.middleware.domain.memberdata

data class MemberDataProvider(
    val identifierType: String,
    val identifierValue: Long,
    val application: String
) {
    companion object {
        fun build(quality: String) = when(quality) {
            "retirementhome" -> MemberDataProvider("CBE", 787213495L, "IRISCARENET")
            else -> MemberDataProvider("CBE", 820563481L, "MYCARENET")
        }
    }
}
