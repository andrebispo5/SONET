package pt.ist.sonet.domain;

import pt.ist.largacaixa.LargaCaixa;
import pt.ist.largacaixa.LargaCaixaLocal;
import pt.ist.pagamigo.PagAmigo;
import pt.ist.pagamigo.PagAmigoLocal;

public class ExternalServices {
	
	private static ExternalServices externalServices = null;
	private static PagAmigo pagamigo=null; 
	private static LargaCaixa largacaixa=null;
	
	private ExternalServices(){} 
	
	public static synchronized ExternalServices getServices(){
		if(externalServices == null){
			externalServices = new ExternalServices();
			largacaixa = new LargaCaixaLocal();
			pagamigo = new PagAmigoLocal();
		}
		return externalServices;
	}
	
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
	
	public final PagAmigo getPagamigo(){
		return pagamigo;
	}
	
	public final LargaCaixa getLargacaixa(){
		return largacaixa;
	}

}
