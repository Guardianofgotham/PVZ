package UI;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import GamePackage.LawnMower;
import GamePackage.Plant;
import GamePackage.Zombie;
import javafx.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Game {
	static Stage mainStage;
	public static ArrayList<ArrayList<Zombie>> CurrentZombies;
	public static ArrayList<ArrayList<Plant>> CurrentPlants;
	public static LawnMower[] lawnMowers=new LawnMower[5];
	public static User currUser;
	public static Label label;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Executing Game");
		Game.initializeVariables();
		String[] arg = new String[1];
		MainMenu.main(arg);
		
	}
	
	static boolean isCurrRowEmpty(int row) {
		row--;
		for(int i=0;i<Game.CurrentPlants.get(row).size();i++) {
			if(Game.CurrentPlants.get(row).get(i)==null) {
				continue;
			}
			return false;
		}
		return true;
	}
	
	
	
	public static void initializeVariables() {
		currUser = new User();
		currUser.setName("Nandesh");
		currUser.setCollectedSuns(100);
		Game.CurrentZombies = new ArrayList<ArrayList<Zombie>>();
		Game.CurrentPlants = new ArrayList<ArrayList<Plant>>();
		//Game.lawnMowers = new LawnMower[5];
		for(int i=0;i<5;i++) {
			Game.CurrentZombies.add(new ArrayList<Zombie>());
			Game.CurrentPlants.add(new ArrayList<Plant>());
			for(int j=0;j<10;j++) {
				Game.CurrentPlants.get(i).add(null);
			}
		}
	}
	
}
