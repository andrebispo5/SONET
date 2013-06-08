package pt.largacaixa.ws.client;

import pt.largacaixa.ws.LargaCaixaPortType;


public class CheckContent {
	
	public void execute(LargaCaixaPortType port, String fileName, String caixa) throws Exception {
		
		try {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("---------------------Ver Conteudo - TESTE--------------------------");
			System.out.println("-------------------------------------------------------------------");
			
			byte[] _content;
			_content = port.obterConteudo(fileName, caixa);
			
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Conteudo " + fileName + " da Caixa " + caixa);
			System.out.println("-------------------------------------------------------------------");
			
			if (_content != null) {			
				String _tolkien = new String(_content);	
				System.out.println(_tolkien);
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}		
	}	
}