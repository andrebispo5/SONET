package pt.ist.sonet.factory;

import pt.ist.largacaixa.LargaCaixa;
import pt.ist.largacaixa.LargaCaixaRemote;
import pt.ist.pagamigo.PagAmigo;
import pt.ist.pagamigo.PagAmigoRemote;

public class RemoteExternalServiceFactory implements ExternalServiceFactory {
	
	private PagAmigo pagamigo;
	private LargaCaixa largacaixa;
	
	public RemoteExternalServiceFactory(){
		this.pagamigo = new PagAmigoRemote();
		this.largacaixa = new LargaCaixaRemote();
	}

	@Override
	public PagAmigo makePagAmigo() {
		return pagamigo;
	}

	@Override
	public LargaCaixa makeLargaCaixa() {
		return largacaixa;
	}

}
