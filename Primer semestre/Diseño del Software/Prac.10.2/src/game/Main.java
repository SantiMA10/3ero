package game;

import game.version.*;

public class Main {
	public static void main(String[] args) {
		//Template Method
		
		//BallGame game = new BallGame();
		//BallGame game = new BallGameVersionPlayStation();
		//BallGame game = new BallGameVersionAndroid();
		BallGame game = new BallGameVersionWindows();
		game.play();
	}
}
