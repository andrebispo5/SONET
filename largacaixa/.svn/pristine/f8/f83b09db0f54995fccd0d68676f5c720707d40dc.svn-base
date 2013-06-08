package pt.largacaixa.ws;

import java.util.TreeMap;

public class Boxes {

	private String caixa;
	private TreeMap<String, Content> content = new TreeMap<String, Content>();
	

	public Boxes(String caixa) {
		this.caixa = caixa;
	}
	
	public Content getContent(String cid) {
		return content.get(cid);
	}

	public void put(Content newContent) {
		content.put(newContent.getCid(), newContent);
		
	}

	public void remove(String cid) {
		content.remove(cid);
	}
	
	public TreeMap<String, Content> getContent() {
		return content;
	}
	
	public String getName() {
		return this.caixa;
	}
}