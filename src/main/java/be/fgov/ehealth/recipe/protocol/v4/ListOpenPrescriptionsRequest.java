package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListOpenPrescriptionsRequestType",
   propOrder = {"securedListOpenPrescriptionsRequest"}
)
@XmlRootElement(
   name = "ListOpenPrescriptionsRequest"
)
public class ListOpenPrescriptionsRequest extends RequestType {
   @XmlElement(
      name = "SecuredListOpenPrescriptionsRequest",
      required = true
   )
   protected SecuredContentType securedListOpenPrescriptionsRequest;

   public SecuredContentType getSecuredListOpenPrescriptionsRequest() {
      return this.securedListOpenPrescriptionsRequest;
   }

   public void setSecuredListOpenPrescriptionsRequest(SecuredContentType value) {
      this.securedListOpenPrescriptionsRequest = value;
   }
}
