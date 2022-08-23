package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TELECOMvalues"
)
@XmlEnum
public enum CDTELECOMvalues {
   @XmlEnumValue("phone")
   PHONE("phone"),
   @XmlEnumValue("mobile")
   MOBILE("mobile"),
   @XmlEnumValue("fax")
   FAX("fax"),
   @XmlEnumValue("email")
   EMAIL("email"),
   @XmlEnumValue("carenet")
   CARENET("carenet");

   private final String value;

   private CDTELECOMvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTELECOMvalues fromValue(String v) {
      CDTELECOMvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDTELECOMvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}