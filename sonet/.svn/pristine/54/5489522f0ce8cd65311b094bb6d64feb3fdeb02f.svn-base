package pt.ist.sonet.factory;

import pt.ist.largacaixa.LargaCaixa;
import pt.ist.largacaixa.LargaCaixaLocal;
import pt.ist.pagamigo.PagAmigo;
import pt.ist.pagamigo.PagAmigoLocal;

public class LocalExternalServiceFactory implements ExternalServiceFactory {
	
	private PagAmigo pagamigo;
	private LargaCaixa largacaixa;
	
	public LocalExternalServiceFactory(){
		this.pagamigo = new PagAmigoLocal();
		this.largacaixa = new LargaCaixaLocal();
	}

	public PagAmigo makePagAmigo(){
		return pagamigo;
	}

	
	public LargaCaixa makeLargaCaixa() {
		return largacaixa;
	}
}
