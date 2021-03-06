package be.ehealth.businessconnector.mycarenet.memberdatav2.session;

import be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl.MemberDataServiceImpl;
import be.ehealth.businessconnector.mycarenet.memberdatav2.session.impl.MemberDataServiceImplementationFactory;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.session.AbstractSessionServiceFactory;

public final class MemberDataSessionServiceFactory extends AbstractSessionServiceFactory {
   private MemberDataSessionServiceFactory() {
   }

   public static MemberDataService getMemberDataSyncService() throws ConnectorException {
      return (MemberDataService)getService(MemberDataServiceImpl.class, new MemberDataServiceImplementationFactory(), new String[0]);
   }
}
