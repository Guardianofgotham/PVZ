package UI;

import java.io.Serializable;
import java.util.ArrayList;

import GamePackage.LawnMower;
import GamePackage.Plant;
import GamePackage.Zombie;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int level;
	private int collectedSuns;
	private double zombiesKilled;
	private ArrayList<ArrayList<Zombie>> currentZombies;
	private ArrayList<ArrayList<Plant>> CurrentPlants;
	private LawnMower[] lawnMowers = new LawnMower[5];

	public double getZombiesKilled() {
		return zombiesKilled;
	}

	public void setZombiesKilled(double zombiesKilled) {
		this.zombiesKilled = zombiesKilled;
	}



	

	User() {
		this.level = 1;
		this.collectedSuns = 100;
		this.zombiesKilled = level * 10;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCollectedSuns() {
		return collectedSuns;
	}

	public void setCollectedSuns(int collectedSuns) {
		this.collectedSuns = collectedSuns;
	}

	public double getNumberofZombieToWin() {
		return zombiesKilled;
	}

	public void setNumberofZombieToWin(double zombiesKilled) {
		this.zombiesKilled = zombiesKilled;
	}

	public ArrayList<ArrayList<Zombie>> getCurrentZombies() {
		return currentZombies;
	}

	public void setCurrentZombies(ArrayList<ArrayList<Zombie>> currentZombies) {
		this.currentZombies = currentZombies;
	}

	public ArrayList<ArrayList<Plant>> getCurrentPlants() {
		return CurrentPlants;
	}

	public void setCurrentPlants(ArrayList<ArrayList<Plant>> currentPlants) {
		CurrentPlants = currentPlants;
	}

	public LawnMower[] getLawnMowers() {
		return lawnMowers;
	}

	public void setLawnMowers(LawnMower[] lawnMowers) {
		this.lawnMowers = lawnMowers;
	}

}
