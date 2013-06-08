package pt.pagamigo.ws;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.datatype.XMLGregorianCalendar;

public class Comprovativo{
	

//	private static final long serialVersionUID = 1L;
	
	private XMLGregorianCalendar datahora;
	private String ordenante;
	private String beneficiario;
	private int montante;
	private String descritivo;
	
	public Comprovativo(XMLGregorianCalendar datahora, String ordenante,
			String beneficiario, int montante, String descritivo) {
		this.datahora = datahora;
		this.ordenante = ordenante;
		this.beneficiario = beneficiario;
		this.montante = montante;
		this.descritivo = descritivo;
	}

	public XMLGregorianCalendar getDatahora() {
		return datahora;
	}

	public void setDatahora(XMLGregorianCalendar datahora) {
		this.datahora = datahora;
	}

	public String getOrdenante() {
		return ordenante;
	}

	public void setOrdenante(String ordenante) {
		this.ordenante = ordenante;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public int getMontante() {
		return montante;
	}

	public void setMontante(int montante) {
		this.montante = montante;
	}

	public String getDescritivo() {
		return descritivo;
	}

	public void setDescritivo(String descritivo) {
		this.descritivo = descritivo;
	}
	
	public byte[] encrypt(PrivateKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherBytes = cipher.doFinal(this.toString().getBytes());
        return cipherBytes;
	}
	
	@Override
	public String toString() {
		return "Comprovativo [datahora=" + datahora + ", ordenante="
				+ ordenante + ", beneficiario=" + beneficiario + ", montante="
				+ montante + ", descritivo=" + descritivo + "]";
	}
	

}
