package org.taktik.freehealth.middleware.domain.memberdata

import org.taktik.freehealth.middleware.domain.common.CarenetPlatform

data class KeyDepotProvider(
    val identifierType: String,
    val identifierValue: Long,
    val application: String
) {
    companion object {
        fun build(platform: CarenetPlatform) = when(platform) {
            CarenetPlatform.WALCARENET -> KeyDepotProvider("CBE", 675597179L, "WALCARENET")
            CarenetPlatform.IRISCARENET -> KeyDepotProvider("CBE", 787213495L, "IRISCARENET")
            CarenetPlatform.MYCARENET -> KeyDepotProvider("CBE", 820563481L, "MYCARENET")
        }
    }
}
