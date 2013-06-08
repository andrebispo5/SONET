package pt.largacaixa.ws.client;

import pt.largacaixa.ws.LargaCaixaPortType;

public class ShareContent{

	public void execute(LargaCaixaPortType port, String origem, String cid, String destino) throws Exception {
	
		try{
			System.out.println("-------------------------------------------------------------------");
			System.out.println("--------------------Partilhar Conteudo - TESTE---------------------");
			System.out.println("-------------------------------------------------------------------");
		
			System.out.println("-------------------------------------------------------------------");
			System.out.println("A partilhar " + cid + " da caixa " + origem + " para " + destino);
			System.out.println("-------------------------------------------------------------------");
			port.partilharConteudo(cid, origem, destino, "FREE");
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}