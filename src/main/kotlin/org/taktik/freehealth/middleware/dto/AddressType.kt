/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.taktik.freehealth.utils.FailSafeEnumDeserializer
import java.io.Serializable

@JsonDeserialize(using = AddressTypeDeserializer::class)
enum class AddressType : Serializable {
    home, work, vacation, hospital, clinic, hq, other, diplomatic, reference, postal, temporary
}

class AddressTypeDeserializer:FailSafeEnumDeserializer<AddressType>(AddressType::class.java)

