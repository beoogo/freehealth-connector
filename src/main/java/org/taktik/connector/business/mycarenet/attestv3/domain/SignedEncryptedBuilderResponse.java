package org.taktik.connector.business.mycarenet.attestv3.domain;

import be.cin.encrypted.EncryptedKnownContent;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionResponse;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;

public class SignedEncryptedBuilderResponse {
   private EncryptedKnownContent encryptedKnownContent;
   private SignatureVerificationResult signatureVerificationResult;
   private byte[] signedData;
   private byte[] xades;
   private SendTransactionResponse sendTransactionResponse;

   public SignedEncryptedBuilderResponse(EncryptedKnownContent encryptedKnownContent, SignatureVerificationResult signatureVerificationResult, byte[] signedData, byte[] xades) {
      this.encryptedKnownContent = encryptedKnownContent;
      this.signatureVerificationResult = signatureVerificationResult;
      this.signedData = signedData;
      this.xades = xades;
      this.sendTransactionResponse = (SendTransactionResponse)ConnectorXmlUtils.toObject(encryptedKnownContent.getBusinessContent().getValue(), SendTransactionResponse.class);
   }

   public EncryptedKnownContent getRawDecryptedBlob() {
      return this.encryptedKnownContent;
   }

   public SignatureVerificationResult getSignatureVerificationResult() {
      return this.signatureVerificationResult;
   }

   public byte[] getSignedData() {
      return this.signedData;
   }

   public byte[] getXades() {
      return this.xades;
   }

   public SendTransactionResponse getSendTransactionResponse() {
      return this.sendTransactionResponse;
   }
}
