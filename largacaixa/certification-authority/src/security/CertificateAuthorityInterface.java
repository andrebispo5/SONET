package security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface CertificateAuthorityInterface {

	@WebMethod
	public void certificateGenerate(byte[] dados);

	
	/* Use this to save PA public key */
	@WebMethod
	public void setPAPublicKey(Object oKey)throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	//public KeyPair keyGenerate() throws NoSuchAlgorithmException,InvalidKeyException, CertificateException, NoSuchProviderException,SignatureException, IOException;

	/* Returns the input key as a string */
	//public String keyToString(Key key);
	
	//public void saveServerPublicKey(int serverId, byte[] publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException;

	@WebMethod
	public Object makeSignature(Object oBytes) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException;
	
	public boolean verifyDigitalSignature(byte[] cipherDigest, byte[] bytes) throws Exception;
	
	@WebMethod
	public Object getPAPublicKey();
	
	@WebMethod
	public Object getCAPublicKey();
	
	@WebMethod
	public Object getCAPrivateKey();

}