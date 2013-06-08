package pt.largacaixa.ws.client;

import pt.largacaixa.ws.LargaCaixaPortType;

public class RemoveContent {

	public void execute(LargaCaixaPortType port, String fileName, String caixa) throws Exception {
			
		try{
			System.out.println("-------------------------------------------------------------------");
			System.out.println("--------------------Apagar Conteudo - TESTE------------------------");
			System.out.println("-------------------------------------------------------------------");
						
			System.out.println("-------------------------------------------------------------------");
			System.out.println("A apagar "+ fileName + " da caixa " + caixa);		
			System.out.println("-------------------------------------------------------------------");
			port.apagarConteudo(fileName, caixa);
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}