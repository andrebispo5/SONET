package security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@WebService(endpointInterface="security.CertificateAuthorityInterface")
public class CertificateAuthority implements CertificateAuthorityInterface {


	private static HashMap<Integer, X509CertificateImpl> certificates;
	
	private static KeyPair keyPairCA;
	/* Counter for servers */
	int serverId;
	
	int certificateId;
	
	private PublicKey pagAmigoPublicKey;
	
	private static HashMap<Integer, Key> serversPublicKeys = new HashMap<Integer, Key>();

	@PostConstruct
    private void init() throws Exception{
		
		serverId = 1;
		certificateId = 1;
		/* Generate CA keys , keyPairCA holds both*/
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		keyPairCA = keyGen.generateKeyPair();
	}
	
	@WebMethod
	public void certificateGenerate(byte[] dados) {
	
		try{
		X509CertificateImpl certificate = new X509CertificateImpl(pagAmigoPublicKey);
		
		Object o = makeSignature(dados);
		byte[] sign = (byte[]) o;
		certificate.setSignature(sign);
		certificates.put(certificateId, certificate);
		
		certificateId++;
		} catch(Exception e){ e.toString(); }
		
		
	}

	public void setPAPublicKey(Object oKey) throws NoSuchAlgorithmException, InvalidKeySpecException{
		byte[] PAKey = (byte[]) oKey;
		X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(PAKey);
        KeyFactory keyFacPub = KeyFactory.getInstance("RSA");
        PublicKey pub = keyFacPub.generatePublic(pubSpec);
		pagAmigoPublicKey = pub;
		
		
//		System.out.println(key.getClass().getName());

		
	}
	
//	@Override
//	public KeyPair keyGenerate() throws NoSuchAlgorithmException,
//			InvalidKeyException, CertificateException, NoSuchProviderException,
//			SignatureException, IOException {
//
//		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
//		/* Initializes key with 1024 bits size */
//		keyGen.initialize(1024);
//		
//		/* Generates a pair of keys */
//		KeyPair keys = keyGen.generateKeyPair();
//		
//		PublicKey publicKey = keys.getPublic();
//		
//		saveServerPublicKey(serverId, publicKey);
//		serverId++;
//		
//		return keys;
//	}


	public Object getPAPublicKey(){
		return pagAmigoPublicKey.getEncoded();
	}
	@WebMethod
	@Override
	public Object getCAPublicKey() {
		return keyPairCA.getPublic().getEncoded();
	}

	@WebMethod
	@Override
	public PrivateKey getCAPrivateKey() {
		return keyPairCA.getPrivate();
	}
	

	
    public Object makeSignature(Object oBytes) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException{
    	byte[] bytes = (byte[]) oBytes;
        // get a signature object using the MD5 and RSA combo and sign the plaintext with the private key
        Signature sig = Signature.getInstance("MD5WithRSA");
        sig.initSign(getCAPrivateKey());
        sig.update(bytes);
        byte[] signature = sig.sign();
        
        return signature;
    }


    public boolean verifyDigitalSignature(byte[] cipherDigest, byte[] bytes) throws Exception {

        // verify the signature with the public key
        Signature sig = Signature.getInstance("MD5WithRSA");
        
        PublicKey key = (PublicKey) getCAPublicKey();
        sig.initVerify(key);
        sig.update(bytes);
        try {
            return sig.verify(cipherDigest);
        } catch (SignatureException se) {
            return false;
        }
    }

    public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
    
    
}
