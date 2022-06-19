package GameEnviroment;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList <GameObject>();
	
	public int spd =6;
	
	public void tick() {
		for (int i=0;i<object.size();i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	public void render (Graphics g) {
		for (int i =0; i < object.size();i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	public void clearEnemies() {
		int j = 0;
		while (j<object.size()) {
			GameObject tempObject = object.get(j);
			if(tempObject.getId() != ID.Player){
			    removeObject(tempObject);
			    j--;
			   }
			j++;
		}

	}
	public void clearAllEnemies() {
		int j = 0;
		while (j<object.size()) {
			GameObject tempObject = object.get(j);
			    removeObject(tempObject);
			    j--;
			j++;
		}

	}
	
	
}
