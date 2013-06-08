package pt.ist.sonet.factory;

import pt.ist.largacaixa.LargaCaixa;
import pt.ist.pagamigo.PagAmigo;

public interface ExternalServiceFactory {

	public PagAmigo makePagAmigo();
	
	public LargaCaixa makeLargaCaixa();
}
