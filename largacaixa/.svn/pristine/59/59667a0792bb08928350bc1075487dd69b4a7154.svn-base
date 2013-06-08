package pt.largacaixa.ws.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import pt.largacaixa.ws.*;
import java.io.InputStream;

import javax.jws.HandlerChain;


@HandlerChain(file="/handler-chain.xml")
public class AddContent {

	public void execute(LargaCaixaPortType port, String fileName, String caixa, int preco, String filePath) throws Exception {
				
		try{
			System.out.println("-------------------------------------------------------------------");
			System.out.println("------------------Adicionar Conteudo - TESTE-----------------------");
			System.out.println("-------------------------------------------------------------------");		
			byte[] _contentMusic;	
			InputStream _content = this.getClass().getResourceAsStream(filePath);
			_contentMusic = readFile(_content);	
			port.criarConteudo(fileName, caixa, preco, _contentMusic);			
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Conteudo adicionado.");
			System.out.println("-------------------------------------------------------------------");
		}
		catch(Exception e) {
			System.out.println(e.toString());
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
}