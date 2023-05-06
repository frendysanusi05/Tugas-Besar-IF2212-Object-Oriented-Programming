package GUI.gamestates;

import java.awt.event.MouseEvent;

import GUI.main.Game;
import GUI.ui.MenuButton;

public class State {

	protected Game game;

	public State(Game game) {
		this.game = game;
	}
	
	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}
	

	public Game getGame() {
		return game;
	}
}
