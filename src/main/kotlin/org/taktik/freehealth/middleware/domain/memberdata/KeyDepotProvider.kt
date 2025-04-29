package org.taktik.freehealth.middleware.domain.memberdata

data class KeyDepotProvider(
    val identifierType: String,
    val identifierValue: Long,
    val application: String
) {
    companion object {
        // TODO mycarenet/iriscarenet/walcarenet selection should not be linked to quality itself
        fun build(quality: String) = when(quality) {
            "reeducation" -> KeyDepotProvider("CBE", 675597179L, "WALCARENET")
            "retirementhome" -> KeyDepotProvider("CBE", 787213495L, "IRISCARENET")
            else -> KeyDepotProvider("CBE", 820563481L, "MYCARENET")
        }
    }
}
