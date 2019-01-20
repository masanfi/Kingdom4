package game;

import data.Item;
import graphics.Hud;
import java.util.ArrayList;

/**
 *
 * This manages the trophies in Kingdom of Faboma
 * Copyright (c) 2018-2019 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.2
 *
 */

public class Trophy {

	GameEngine gameEngine;
	Hud hud;
	
	private final int trophyFlowersMaxCount = 30;
	private final int trophyTreesMaxCount = 20;
	private final int trophyStonesMaxCount = 10;
	private final int trophyBridgeMaxCount = 3;
	private final int trophyNPCsMaxCount = 15;

	private ArrayList<Integer> trophyCollisionWithTrees;
	private ArrayList<Integer> trophyCollisionWithFlowers;
	private ArrayList<Integer> trophyCollisionWithStones;
	private ArrayList<Integer> trophyCollisionWithNPCs;
	private ArrayList<Integer> trophyCollisionWithBridge;
	private long lastCollisionTime = 0;
	private Boolean trophyBridge = false;
	private Boolean trophyStones = false;
	private Boolean trophyTrees = false;
	private Boolean trophyFlowers = false;
	private Boolean trophyNPCs = false;
	private String lastCollisionName;

	public Trophy(GameEngine gameEngine, Hud hud) {
		this.gameEngine = gameEngine;
		this.hud = hud;

		trophyCollisionWithTrees = new ArrayList<>();
		trophyCollisionWithFlowers = new ArrayList<>();
		trophyCollisionWithStones = new ArrayList<>();
		trophyCollisionWithNPCs = new ArrayList<>();
		trophyCollisionWithBridge = new ArrayList<>();
	}

	public int getTrophyFlowersMaxCount() {
		return trophyFlowersMaxCount;
	}

	public int getTrophyTreesMaxCount() {
		return trophyTreesMaxCount;
	}

	public int getTrophyStonesMaxCount() {
		return trophyStonesMaxCount;
	}

	public int getTrophyBridgeMaxCount() {
		return trophyBridgeMaxCount;
	}

	public int getTrophyNPCsMaxCount() {
		return trophyNPCsMaxCount;
	}

	public void setlastCollisionName(String name) {
		this.lastCollisionName = name;
	}

	private ArrayList<Integer> getTrophyCollisionsWithBridge() {
		return trophyCollisionWithBridge;
	}

	private ArrayList<Integer> getTrophyCollisionsWithNPCs() {
		return trophyCollisionWithNPCs;
	}

	private ArrayList<Integer> getTrophyCollisionsWithTrees() {
		return trophyCollisionWithTrees;
	}

	private ArrayList<Integer> getTrophyCollisionsWithStones() {
		return trophyCollisionWithStones;
	}

	private ArrayList<Integer> getTrophyCollisionsWithFlowers() {
		return trophyCollisionWithFlowers;
	}

	public String getTrophysForHighScore() {

		return "B" + ((trophyBridge) ? "*" : "-") + "§N" + ((trophyNPCs) ? "*" : "-") + "§T"
				+ ((trophyTrees) ? "*" : "-") + "§S" + ((trophyStones) ? "*" : "-") + "§F"
				+ ((trophyFlowers) ? "*" : "-");

	}

	/**
	 * Public method to handle Trophy with Hud
	 * 
	 * @param object
	 * @param item
	 */
	public void trophyManager(int object, Item item) {

		if (this.getTrophyCollisionsWithFlowers().size() == trophyFlowersMaxCount) {

			if (!this.trophyFlowers) {
				gameEngine.collectTrophyClumsy();
			}
			this.trophyFlowers = true;
		}

		if (this.getTrophyCollisionsWithTrees().size() == trophyTreesMaxCount) {

			if (!trophyTrees) {
				gameEngine.collectTrophyTreehugger();
			}
			this.trophyTrees = true;
		}

		if (this.getTrophyCollisionsWithStones().size() == trophyStonesMaxCount) {

			if (!trophyStones) {
				gameEngine.collectTrophyStoney();
			}
			this.trophyStones = true;
		}

		if (this.getTrophyCollisionsWithNPCs().size() == trophyNPCsMaxCount) {

			if (!trophyNPCs) {
				gameEngine.collectTrophyInfluencer();
			}
			this.trophyNPCs = true;
		}

		if (this.getTrophyCollisionsWithBridge().size() == trophyBridgeMaxCount) {

			if (!trophyBridge) {
				gameEngine.collectTrophyConfused();
			}
			this.trophyBridge = true;
		}

		//Collison only count if last collision > 1sec
		if ((System.currentTimeMillis() - lastCollisionTime) > 1000) {

			// Collision with Trees
			if (item.getName().contentEquals("tree") || item.getName().contentEquals("tree2")
					|| item.getName().contentEquals("tree3")) {
				this.getTrophyCollisionsWithTrees().add(object);
			}

			// Collision with Flowers
			if (item.getName().contentEquals("flowers") || item.getName().contentEquals("flower2")) {
				if (!item.getName().equals(lastCollisionName)) {
					this.getTrophyCollisionsWithFlowers().add(object);
					setlastCollisionName(item.getName());
				}
			}
			//Collision with Stones
			if (item.getName().contentEquals("stone")) {
				this.getTrophyCollisionsWithStones().add(object);
			}
			
			//Collision with Npc
			if (item.isNpc()) {
				if (!item.getName().equals(lastCollisionName)) {
					this.getTrophyCollisionsWithNPCs().add(object);
					setlastCollisionName(item.getName());
				}
			}
			
			//Collision with Bridge
			if (item.getName().equals("river_bridge_l")) {
				if (!item.getName().equals(lastCollisionName)) {
					this.getTrophyCollisionsWithBridge().add(object);
					setlastCollisionName(item.getName());
				}
			}

			lastCollisionTime = System.currentTimeMillis();
		}

	}
}
