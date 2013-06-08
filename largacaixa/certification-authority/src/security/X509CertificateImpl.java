
package security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import javax.security.auth.x500.X500Principal;


public class X509CertificateImpl extends X509Certificate {

	int version;
	Date from;
	Date to;
	BigInteger serialNumber;
	String algorithm;
	X500Principal issuerName;
	X500Principal subjectName;
	String publicKeyInfo;
	PublicKey publicKey;
	String publicKeyAlg;
	
	byte[] signature;

	public X509CertificateImpl(PublicKey key) {
		
		int _version = 2;
		Date _from = new Date();
		/* One year expiration date*/ 
		Date _to = new Date(from.getTime() + 365*24*60*60*1000L);
		
		BigInteger _serialNumber = new BigInteger(64, new SecureRandom());
		
		String _algorithm = "md5WithRSAEncryption";
		
		String CN = "CN= CA ";
		String OU = "OU=sd";
		String O = "O=Ist";
		String C = "C=Portugal";
		String DN = CN + ", " + OU + ", " + O + ", " + C;
		X500Principal _issuerName = new X500Principal(DN);
		
		CN = "CN= PagAmigo Bank ";
		OU = "OU=Sd";
		O = "O=Ist";
		C = "C=Portugal";
		DN = CN + ", " + OU + ", " + O + ", " + C;
		X500Principal _subjectName = new X500Principal(DN);

		String _publicKeyInfo = key.getAlgorithm() + key;
	
		
		version = _version;
		from = _from;
		to = _to;
		issuerName = _issuerName;
		subjectName = _subjectName;
		serialNumber = _serialNumber;
		algorithm = _algorithm;
		
		publicKey = key;
		publicKeyAlg = key.getAlgorithm();
		publicKeyInfo = _publicKeyInfo;
		
		
	}

	/* Prints the certificate to a file on the project workspace */
	public void printX509Certificate() throws IOException{
		
		File newTextFile = new File("certificate.txt");
		
		FileWriter fw = new FileWriter(newTextFile);
		fw.write("-----BEGIN CERTIFICATE-----\n");
		fw.write("Version: " + this.version + "\n");
		fw.write("Serial Number: " + this.serialNumber+ "\n");
		fw.write("Algorithm ID: " + this.algorithm+ "\n");
		fw.write("Issuer: " + this.issuerName+ "\n");
		fw.write("Validity: \n");
		fw.write(" Not Before: " + this.from+ "\n");
		fw.write(" Not After: " + this.to+ "\n");
		fw.write("Subject: " + this.subjectName+ "\n");
		fw.write("Subject Public Key Info: \n");
		fw.write("Public Key Algorithm: " + this.publicKeyAlg+ "\n");
		fw.write("Subject Public Key: " + this.publicKey+ "\n");
		
		if(this.signature != null){
			fw.write("Signature: " + "\n");
			
			for (int i = 0; i < this.signature.length ; i+=20){
				byte[] dados = Arrays.copyOfRange(this.signature, i, i+20);
				fw.write(new BigInteger(1, dados).toString(16) +"\n");
			}
		}
		fw.write("-----END CERTIFICATE-----");
		fw.close();
	}

	
	@Override
	public boolean hasUnsupportedCriticalExtension() {return false;}


	@Override
	public Set<String> getCriticalExtensionOIDs() {return null;}


	@Override
	public Set<String> getNonCriticalExtensionOIDs() {return null;}


	@Override
	public byte[] getExtensionValue(String oid) {return null;}


	@Override
	public void checkValidity() throws CertificateExpiredException,
			CertificateNotYetValidException {
		Date now = new Date();
		if (now.after(getNotAfter())) System.out.println("Expired Certificate!");
	}


	@Override
	public void checkValidity(Date date) throws CertificateExpiredException,
			CertificateNotYetValidException {
		
	}


	@Override
	public int getVersion() {
		return this.version;
	}


	@Override
	public BigInteger getSerialNumber() {
		return this.serialNumber;
	}


	@Override
	public Principal getIssuerDN() {
		return this.issuerName;
	}


	@Override
	public Principal getSubjectDN() {
		return this.subjectName;
	}


	@Override
	public Date getNotBefore() {return this.from;}


	@Override
	public Date getNotAfter() { return this.to; }


	@Override
	public byte[] getSignature() {return this.signature;}


	@Override
	public String getSigAlgName() {
		return this.algorithm.toString();
	}


	@Override
	public String getSigAlgOID() {return null;}


	@Override
	public byte[] getSigAlgParams() {return null;}


	@Override
	public boolean[] getIssuerUniqueID() { return null; }


	@Override
	public boolean[] getSubjectUniqueID() { return null; }


	@Override
	public boolean[] getKeyUsage() {
		return null;
	}


	@Override
	public int getBasicConstraints() {
		return 0;
	}


	@Override
	public byte[] getEncoded() throws CertificateEncodingException {
		return null;
	}


	@Override
	public void verify(PublicKey key) throws CertificateException,
			NoSuchAlgorithmException, InvalidKeyException,
			NoSuchProviderException, SignatureException {	
	}


	@Override
	public void verify(PublicKey key, String sigProvider)
			throws CertificateException, NoSuchAlgorithmException,
			InvalidKeyException, NoSuchProviderException, SignatureException {		
	}


	@Override
	public PublicKey getPublicKey() {
		return this.publicKey;
	}


	@Override
	public byte[] getTBSCertificate() throws CertificateEncodingException {
		return null;
	}

	public void setSignature(byte[] bytes) {
		this.signature = bytes;
	}
	
	public void print() {
		try {
			printX509Certificate();
			FileReader fr = new FileReader("certificate.txt"); 
			BufferedReader br = new BufferedReader(fr);
			String s; 
			while((s = br.readLine()) != null) { 
				System.out.println(s); 
			} 
			fr.close();

		} 
		catch (IOException e) { e.printStackTrace(); }

	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Not Implemented";
	}



}
