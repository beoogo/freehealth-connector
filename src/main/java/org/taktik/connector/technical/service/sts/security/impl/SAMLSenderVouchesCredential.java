package org.taktik.connector.technical.service.sts.security.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.w3c.dom.Element;

public class SAMLSenderVouchesCredential extends AbstractSAMLToken {
   public SAMLSenderVouchesCredential(Element assertion, Credential credential) throws TechnicalConnectorException {
      super(assertion, credential);
   }
}
