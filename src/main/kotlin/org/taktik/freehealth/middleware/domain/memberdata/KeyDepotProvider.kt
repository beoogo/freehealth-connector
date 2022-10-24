package org.taktik.freehealth.middleware.domain.memberdata

data class KeyDepotProvider(
    val identifierType: String,
    val identifierValue: Long,
    val application: String
) {
    companion object {
        fun build(quality: String) = when(quality) {
            "retirementhome" -> KeyDepotProvider("CBE", 787213495L, "IRISCARENET")
            else -> KeyDepotProvider("CBE", 820563481L, "MYCARENET")
        }
    }
}
