package pt.largacaixa.ws;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.jws.WebService;
import javax.annotation.PostConstruct;
import javax.jws.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import java.security.Provider.Service;
import java.sql.*;
import javax.sql.*;
import javax.transaction.xa.XAResource;

import java.io.InputStream;
import java.net.URL;
import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

@HandlerChain(file="/handler-chain.xml")
@WebService(
    endpointInterface="pt.largacaixa.ws.LargaCaixaPortType",
    wsdlLocation="LargaCaixa.1_0.wsdl",
    name="LargaCaixa",
    portName="LargaCaixaPort",
    targetNamespace="http://ws.largacaixa.pt/",
    serviceName="LargaCaixaService"
)

public class LargaCaixaImpl implements LargaCaixaPortType{		
	
	public static String whoAmI;
	public static String dbUrl_1;
	public static Map<String, Object> map = new HashMap<String, Object>();
	private static SayAlive sa;
	public static String lastId;
	
	
	public static void setWhoAmI(String name, String adr){
		whoAmI = name;				
		dbUrl_1 = adr;
		sa = new SayAlive(name);
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + name);
		
		Thread t = new Thread(sa);
        t.start();
		
	}
	
	@PostConstruct
	private void init() throws IOException {
	
		InputStream _tolkien = this.getClass().getResourceAsStream("/Tolkien.txt");
		InputStream _pessoa = this.getClass().getResourceAsStream("/Pessoa.txt");
		InputStream _camoes = this.getClass().getResourceAsStream("/Camoes.txt");
		InputStream _tecnico = this.getClass().getResourceAsStream("/Tecnico.png");
		InputStream _istory = this.getClass().getResourceAsStream("/ISTory.png");		
		
		try{
			new InsertContent("bn", "Camoes.txt", readFile(_camoes)).execute(this);
			new InsertContent("bn", "Pessoa.txt", readFile(_pessoa)).execute(this);
			new InsertContent("bn", "Tolkien.txt", 10, readFile(_tolkien)).execute(this);
			new InsertContent("ist", "Tecnico.png", readFile(_tecnico)).execute(this);
			new InsertContent("ist", "ISTory.png", 5, readFile(_istory)).execute(this);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}	
	}
	
		
	public static byte[] readFile (InputStream is) throws IOException {
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];

		while ((nRead = is.read(data, 0, data.length)) != -1) {
		  buffer.write(data, 0, nRead);
		}

		buffer.flush();
		return buffer.toByteArray();
    }
	
	
	@Override
	@WebMethod
	@RequestWrapper(localName = "criarConteudo", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.CriarConteudoType")
	@ResponseWrapper(localName = "criarConteudoResponse", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.CriarConteudoResponseType")
	public void criarConteudo(
			@WebParam(name = "cid", targetNamespace = "") String cid,
			@WebParam(name = "caixa", targetNamespace = "") String caixa,
			@WebParam(name = "preco", targetNamespace = "") int preco,
			@WebParam(name = "dados", targetNamespace = "") byte[] dados)
			throws CaixaInexistente, ConteudoInvalido {
		
		String a = "####################### EU SOU ####################" + whoAmI;
		System.out.println(a);
		
		if(preco < 0) throw new ConteudoInvalido(cid, new ConteudoInvalidoType());		
		new InsertContent(caixa, cid,preco, dados).execute(this);
	}
	

	@Override
	@WebMethod
	@RequestWrapper(localName = "apagarConteudo", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.ApagarConteudoType")
	@ResponseWrapper(localName = "apagarConteudoResponse", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.ApagarConteudoResponseType")
	public void apagarConteudo(
			@WebParam(name = "cid", targetNamespace = "") String cid,
			@WebParam(name = "caixa", targetNamespace = "") String caixa)
			throws CaixaInexistente, ConteudoInexistenteNaCaixa {

		new DeleteContent(caixa, cid).execute(this);
	
	}

	
	@Override
	@WebMethod
	@WebResult(name = "conteudo", targetNamespace = "")
	@RequestWrapper(localName = "listarConteudos", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.ListarConteudosType")
	@ResponseWrapper(localName = "listarConteudosResponse", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.ListarConteudosResponseType")
	public List<String> listarConteudos(
			@WebParam(name = "caixa", targetNamespace = "") String caixa)
			throws CaixaInexistente {
			
		return new ListContent(caixa).execute(this);
	}
	

	@Override
	@WebMethod
	@WebResult(name = "dados", targetNamespace = "")
	@RequestWrapper(localName = "obterConteudo", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.ObterConteudoType")
	@ResponseWrapper(localName = "obterConteudoResponse", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.ObterConteudoResponseType")
	public byte[] obterConteudo(
			@WebParam(name = "cid", targetNamespace = "") String cid,
			@WebParam(name = "caixa", targetNamespace = "") String caixa)
			throws CaixaInexistente, ConteudoInexistenteNaCaixa {

		return new GetContent(caixa, cid).execute(this);
	}
	

	@Override
	@WebMethod
	@RequestWrapper(localName = "partilharConteudo", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.PartilharConteudoType")
	@ResponseWrapper(localName = "partilharConteudoResponse", targetNamespace = "http://ws.largacaixa.pt/", className = "pt.largacaixa.ws.PartilharConteudoResponseType")
	public void partilharConteudo(
			@WebParam(name = "cid", targetNamespace = "") String cid,
			@WebParam(name = "caixaOrigem", targetNamespace = "") String caixaOrigem,
			@WebParam(name = "caixaDestino", targetNamespace = "") String caixaDestino,
			@WebParam(name = "comprovativoPagamento", targetNamespace = "") Object comprovativoPagamento)
			throws CaixaInexistente, ComprovativoRejeitado,
			ConteudoInexistenteNaCaixa {
		
		//TODO: comprovativo		
		
		new ShareContent(caixaOrigem, cid, caixaDestino).execute(this);	


	}


	public static void go() {

		System.out.println("IM ON GO +++++++++++++++++++++++++++++++++ " + whoAmI);
		
		
		Timer t = new Timer();
		TimerTask task = new SayAlive(whoAmI);
		//t.schedule(task, 5000, 2000);
		t.schedule(task, 20000);
		
		if(!sa.setAlive()){
			new UDDIRegistry();
		}
	}	
}