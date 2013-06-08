package pt.largacaixa.ws.client;

import java.util.List;
import pt.largacaixa.ws.LargaCaixaPortType;

public class ListContent {

	public void execute(LargaCaixaPortType port, String caixa) throws Exception {
	
		try {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("----------------------Listar Conteudo - TESTE----------------------");
			System.out.println("-------------------------------------------------------------------");
	
			List<String> lista;
		
			lista = port.listarConteudos(caixa);
			
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Listar Conteudo do/a " + caixa);
			System.out.println("-------------------------------------------------------------------");
			
			if(lista.size() == 0) {
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Caixa Vazia: " + caixa);
				System.out.println("-------------------------------------------------------------------");
			}
			else {
				for(String list : lista) {
					System.out.println(list);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}