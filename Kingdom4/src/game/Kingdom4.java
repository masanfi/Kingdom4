package game;

import data.World;
import graphics.Hud;
import graphics.Scenery;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * This makes the magic happen.
 * Copyright (c) 2018-2019 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class Kingdom4 extends Application {

    // Define variables that will be initialized later on

	private World world;
	private Hero hero;
	private GameEngine gameEngine;
	private Scenery scenery;
	private Trophy trophy;
	private Hud hud;

    /**
     * method start
     *
     * Initializes variables, starts detection routines
     *
     * @param primaryStage
     */

	@Override
	public void start(Stage primaryStage) {

		gameEngine = new GameEngine();
		gameEngine.setStartTime();
		gameEngine.setPrimaryStage(primaryStage);

		// Initialize Collision Detection
		new CollisionDetection(gameEngine);
		// Initialize Trigger Detection
		new TriggerDetection(gameEngine);
		
		
		// Initialize Trophy Manager und Hud Manager
		hud = new Hud();
		trophy = new Trophy(gameEngine,hud);
		
		gameEngine.setHud(hud);
		gameEngine.setTrophy(trophy);
		
		world = new World(gameEngine);
		scenery = new Scenery(gameEngine,hud);
		gameEngine.setScenery(scenery);
		hero = new Hero(gameEngine);
		world.buildWorld();
		scenery.setPlayerOnField();
		
		world.setCamera();
		gameEngine.setLastUpdate(-1);

		// Initialize animation thread
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				hero.move(now);
			}
		};

		hero.getControls(gameEngine.getScene());
		primaryStage.setTitle("Kingdom 4");
		scenery.renderIntro();
		primaryStage.setScene(gameEngine.getIntro());

		primaryStage.setResizable(false);
		primaryStage.show();

		timer.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
