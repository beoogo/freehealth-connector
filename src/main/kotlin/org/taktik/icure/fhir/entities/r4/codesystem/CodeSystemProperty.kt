//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.codesystem

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import kotlin.String
import kotlin.collections.List
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension

/**
 * Additional information supplied about each concept
 *
 * A property defines an additional slot through which additional information can be provided about
 * a concept.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class CodeSystemProperty(
  /**
   * Identifies the property on the concepts, and when referred to in operations
   */
  var code: String? = null,
  /**
   * Why the property is defined, and/or what it conveys
   */
  var description: String? = null,
  override var extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override var id: String? = null,
  override var modifierExtension: List<Extension> = listOf(),
  /**
   * code | Coding | string | integer | boolean | dateTime | decimal
   */
  var type: String? = null,
  /**
   * Formal identifier for the property
   */
  var uri: String? = null
) : BackboneElement
