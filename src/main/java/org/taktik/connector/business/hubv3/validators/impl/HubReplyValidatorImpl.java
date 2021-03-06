package org.taktik.connector.business.hubv3.validators.impl;

import org.taktik.connector.business.intrahubcommons.exception.KmehrBusinessConnectorException;
import org.taktik.connector.business.hubv3.validators.HubReplyValidator;
import be.fgov.ehealth.hubservices.core.v3.AcknowledgeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HubReplyValidatorImpl implements HubReplyValidator {
   private static final Logger LOG = LoggerFactory.getLogger(HubReplyValidatorImpl.class);
   private static final String LF = System.getProperty("line.separator");

   public void validate(AcknowledgeType acknowledge) throws KmehrBusinessConnectorException {
   }
}
