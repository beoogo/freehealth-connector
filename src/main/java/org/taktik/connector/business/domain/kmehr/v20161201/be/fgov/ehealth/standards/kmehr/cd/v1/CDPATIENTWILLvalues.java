//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2017.05.11 à 02:53:46 PM CEST 
//


package org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-PATIENTWILLvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-PATIENTWILLvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ntbr"/>
 *     &lt;enumeration value="bloodtransfusionrefusal"/>
 *     &lt;enumeration value="intubationrefusal"/>
 *     &lt;enumeration value="euthanasiarequest"/>
 *     &lt;enumeration value="vaccinationrefusal"/>
 *     &lt;enumeration value="organdonationconsent"/>
 *     &lt;enumeration value="datareuseforclinicalresearchconsent"/>
 *     &lt;enumeration value="datareuseforclinicaltrialsconsent"/>
 *     &lt;enumeration value="clinicaltrialparticipationconsent"/>
 *     &lt;enumeration value="omissionofmedicaldata"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-PATIENTWILLvalues")
@XmlEnum
public enum CDPATIENTWILLvalues {

    @XmlEnumValue("ntbr")
    NTBR("ntbr"),
    @XmlEnumValue("bloodtransfusionrefusal")
    BLOODTRANSFUSIONREFUSAL("bloodtransfusionrefusal"),
    @XmlEnumValue("intubationrefusal")
    INTUBATIONREFUSAL("intubationrefusal"),
    @XmlEnumValue("euthanasiarequest")
    EUTHANASIAREQUEST("euthanasiarequest"),
    @XmlEnumValue("vaccinationrefusal")
    VACCINATIONREFUSAL("vaccinationrefusal"),
    @XmlEnumValue("organdonationconsent")
    ORGANDONATIONCONSENT("organdonationconsent"),
    @XmlEnumValue("datareuseforclinicalresearchconsent")
    DATAREUSEFORCLINICALRESEARCHCONSENT("datareuseforclinicalresearchconsent"),
    @XmlEnumValue("datareuseforclinicaltrialsconsent")
    DATAREUSEFORCLINICALTRIALSCONSENT("datareuseforclinicaltrialsconsent"),
    @XmlEnumValue("clinicaltrialparticipationconsent")
    CLINICALTRIALPARTICIPATIONCONSENT("clinicaltrialparticipationconsent"),
    @XmlEnumValue("omissionofmedicaldata")
    OMISSIONOFMEDICALDATA("omissionofmedicaldata");
    private final String value;

    CDPATIENTWILLvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDPATIENTWILLvalues fromValue(String v) {
        for (CDPATIENTWILLvalues c: CDPATIENTWILLvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
