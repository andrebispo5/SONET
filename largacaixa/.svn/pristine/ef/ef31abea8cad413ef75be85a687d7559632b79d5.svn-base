//package security;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.security.PublicKey;
//
//public class Main {
//	
//  public static void main(String[] argv) throws Exception {
//
//    try {
//		
//    CertificateAuthority CA = new CertificateAuthority();
//    
//	PublicKey caKey = CA.getCAPublicKey();
//	
//    X509CertificateImpl certificateImpl = new X509CertificateImpl(caKey);
//        
//    byte[] data = serialize(certificateImpl);
//    
//    byte[] signature = CA.makeSignature(data);
//    
//    certificateImpl.setSignature(signature);
//    
//    System.out.println("Certificate: ");
//    certificateImpl.print();
//    
//
//    
////	byte[] data = serialize(certificateImpl);
////
////    byte[] sign = CA.makeSignature(data);
////    System.out.println("SIGNATURE: " + sign.toString());
////    
////    System.out.println("Certificate DATA: " + data.toString());
////    
////    boolean a = CA.verifyDigitalSignature(sign, data);
////    
////    System.out.println("VERIFIED: " + a);
////    certificateImpl.printX509Certificate();
////    System.out.println(printHexBinary(data));
////    // data modification ...
////	data[3] = 12;
////    System.out.println("Tampered bytes:");
////    System.out.println(printHexBinary(data));
////
////    // verify the signature
////    System.out.println("Verifying ...");
////    boolean result = CA.verifyDigitalSignature(sign, data);
////    System.out.println("Signature is " + (result ? "right" : "wrong"));
////    
////    X509Certificate certi = certificateImpl;
////    System.out.println("-----BEGIN CERTIFICATE-----");
////    System.out.println(DatatypeConverter.printBase64Binary(certi.getEncoded()));
////    System.out.println("-----END CERTIFICATE-----");
//    
//    
//    }
//    catch(Exception e){ System.out.println("ERROR: " + e.toString());}
//
//  }
//
//  public static byte[] serialize(Object obj) throws IOException {
//	    ByteArrayOutputStream out = new ByteArrayOutputStream();
//	    ObjectOutputStream os = new ObjectOutputStream(out);
//	    os.writeObject(obj);
//	    return out.toByteArray();
//	}
//}