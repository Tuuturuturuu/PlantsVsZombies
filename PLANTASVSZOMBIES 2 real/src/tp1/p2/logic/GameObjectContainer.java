package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			if(g.isAlive() && g.getCol() == col && g.getRow() == row) {
				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public boolean removeDead() { 
		for(GameObject item: gameObjects) {
			if(!item.isAlive()) {
				item.onExit();
				gameObjects.remove(item);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean matchItem(GameItem item ,int col , int row) {
		if(item.isInPosition(col, row)) {
			return true;
		}
		return false;
	}

	public GameItem getItem(int col, int row) {
		for(GameItem item: gameObjects) {
			if(matchItem(item, col ,row) && item.fillPosition()) {
				return item;
			}
		}
		return null;
	}
	
	public GameItem getSun(int col, int row) {
		for(GameItem item: gameObjects) {
			if(matchItem(item, col ,row) && !item.fillPosition() && item.isAlive()) {
				return item;                //compruebo si esta vivo para si hay mas de un sol coger todos
			}
		}
		return null;
	}
	
	public boolean isPositionFullOcuped(int col, int row) {	
		for(GameItem item: gameObjects) {
			if(matchItem(item, col ,row) && item.fillPosition()) {
				return true;
			}
		}
		return false;
	}
	
	public void addObject(GameObject object) {
		object.onEnter();
		gameObjects.add(object);
		
	}

	public void update() {
		for(GameObject object: gameObjects) {
			object.update();
		}
		
	}
	
	
	

	

	// TODO add your code here

}
