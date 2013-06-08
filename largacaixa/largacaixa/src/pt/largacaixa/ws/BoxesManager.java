package pt.largacaixa.ws;

import java.util.TreeMap;

public class BoxesManager {

	private TreeMap<String, Boxes> boxes;
	private static BoxesManager manager;
	
	private BoxesManager() {
		boxes = new TreeMap<String, Boxes>();		
	}

	
	public static BoxesManager getManager() {
		if (manager == null) {
			manager = new BoxesManager();
		}
		return manager;
	}
	
	public Boxes getBox(String caixa) {
		return boxes.get(caixa);		
	}
	
	public void addBox(Boxes box) {
		boxes.put(box.getName(), box);
	}	
}