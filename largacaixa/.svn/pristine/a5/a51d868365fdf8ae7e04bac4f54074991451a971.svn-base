package pt.largacaixa.ws;

import java.io.IOException;

public class ShareContent extends Operation {

	private String caixaDestino;
	
	public ShareContent(String caixa, String cid, String caixaDestino) {
		super(caixa, cid);
		this.caixaDestino = caixaDestino;
	}

	public void execute(LargaCaixaImpl lc) throws CaixaInexistente, ConteudoInexistenteNaCaixa {
		
		super.checkContentAvailability(lc, this.cid, this.caixa);
		super.checkBox(lc, this.caixa);
		//TODO: comprovativo
		
		//da hacks		
		try {
			byte[] buffer = new GetContent(caixa, cid).execute(lc);
			//InsertContent ic = new InsertContent(caixaDestino, cid, buffer);
			//ic.execute();	
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}	
}