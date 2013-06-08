package pt.ist.sonet.service;

import pt.ist.largacaixa.LargaCaixa;
import pt.ist.pagamigo.PagAmigo;
import pt.ist.sonet.domain.*;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.sonet.exception.SonetException;
import jvstm.Atomic;

public abstract class SonetService {

	@Atomic
	public void execute() throws SonetException { 
		dispatch();
	}
	
	public abstract void dispatch() throws SonetException;
	
	
	
	protected Publication getPublication(int id) {
		Sonet sonet = FenixFramework.getRoot();;
		Publication publication = sonet.getPublicationById(id);
		return publication;
	}
	
	protected PagAmigo getPagAmigo() {
		Sonet sonet = FenixFramework.getRoot();;
		PagAmigo pagamigo = sonet.getPagAmigo();
		return pagamigo;
	}
	
	protected LargaCaixa getLargaCaixa() {
		Sonet sonet = FenixFramework.getRoot();;
		LargaCaixa largacaixa = sonet.getLargaCaixa();
		return largacaixa;
	}
}
