package UI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.CreateSun;
import Controller.CreateZombie;
import GamePackage.FrozenPea;
import GamePackage.GImage;
import GamePackage.Jalpeno;
import GamePackage.LawnMower;
import GamePackage.Peashooter;
import GamePackage.Plant;
import GamePackage.SunFlower;
import GamePackage.Wallnut;
import GamePackage.Zombie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu extends Application {
	public static Stage mainStage;

	@FXML
	private AnchorPane MainMenuStage;
	@FXML
	private ImageView MainMenuBackGroundImage;
	@FXML
	private ImageView MainMenuPlayButton;
	@FXML
	private ImageView MainMenuHelp;
	@FXML
	private ImageView MainMenuExit;
	@FXML
	private ImageView EditProfileImage;
	@FXML
	private Label UserNameLabel;

	/////

	@FXML
	private ImageView LM2;
	@FXML
	private ImageView LM3;
	@FXML
	private ImageView LM4;
	@FXML
	private ImageView LM5;
	@FXML
	private ImageView LM1;

	@FXML
	private AnchorPane root;
	@FXML
	private ImageView BackYardimg;
	@FXML
	private VBox PlantsSelectorContainer;
	@FXML
	private GridPane LawnGridPane;
	@FXML
	private ImageView Shovelimg;
	@FXML
	private ImageView SunImg;
	@FXML
	private Label CollectedSunLabel;
	@FXML
	private ImageView MenuButtonimg;
	@FXML
	private ProgressBar progressBar;

	private String LastClickEvent;
	private boolean isShovelSelected;
	private boolean toUpdate = true;

	@FXML
	private ImageView Peashooter;
	@FXML
	private ImageView Sunflower;
	@FXML
	private ImageView Wallnut;
	@FXML
	private ImageView Frozenpea;
	@FXML
	private ImageView Jalpeno;
	@FXML
	private StackPane L5;
	@FXML
	private StackPane L4;
	@FXML
	private StackPane L3;
	@FXML
	private StackPane L2;
	@FXML
	private StackPane L1;

	static boolean cooldown = false;

	// static Label sunCount;
	static Thread t1;
	static Thread t2;

	@Override
	public void start(Stage mainStage) throws IOException, ClassNotFoundException {
		MainMenu.mainStage = mainStage;
		String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\MainMenu.fxml";
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
		AnchorPane root = (AnchorPane) loader.load(fxmlStream);
		MainMenu.mainStage.setScene(new Scene(root));
		MainMenu.mainStage.setResizable(false);
		MainMenu.mainStage.show();

	}

	public static void main(String args[]) throws IOException {
		Game.initializeVariables();
		Application.launch(args);
	}

	// Event Listener on ImageView[#MainMenuPlayButton].onMouseClicked
	@FXML
	public void HandlePlayEvent(MouseEvent event) throws IOException, ClassNotFoundException {
		File fs = new File("C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\gameDatabase\\Nandesh.txt");
		if (fs.exists()) {
			this.ResumeGame(null);
		} else {
			String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Level1.fxml";
			FXMLLoader loader = new FXMLLoader();
			FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
			AnchorPane root = (AnchorPane) loader.load(fxmlStream);
			MainMenu.mainStage.setScene(new Scene(root));

			CreateSun s = new CreateSun(root);
			Thread t1 = new Thread(s);
			t1.setDaemon(true);
			Thread t2 = new Thread(new CreateZombie(root, this.progressBar));
			t2.setDaemon(true);
			MainMenu.t1 = t1;
			MainMenu.t2 = t2;
			t1.start();
			t2.start();
		}

	}

	// Event Listener on ImageView[#MainMenuHelp].onMouseClicked
	@FXML
	public void HandleHelpEvent(MouseEvent event) throws IOException {
		String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\HelpUI.fxml";
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
		AnchorPane root = (AnchorPane) loader.load(fxmlStream);
		MainMenu.mainStage.setScene(new Scene(root));
	}

	// Event Listener on ImageView[#MainMenuExit].onMouseClicked
	@FXML
	public void HandleExitEvent(MouseEvent event) {
		System.out.println("Exiting ...");
		System.exit(0);
	}

	// Event Listener on ImageView[#EditProfileImage].onMouseClicked
	@FXML
	public void HandleProfileEvent(MouseEvent event) throws IOException {
		String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\ProfileWindow.fxml";
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
		AnchorPane root = (AnchorPane) loader.load(fxmlStream);
		MainMenu.mainStage.setScene(new Scene(root));
	}

	@FXML
	public void BackGroundClickHandler(MouseEvent event) {
		this.LastClickEvent = null;
		System.out.println("BackGround Clicked!!!");
		isShovelSelected = false;
	}

	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void SunflowerEvent(MouseEvent event) {
		this.LastClickEvent = "SunFlower";
		System.out.println(this.LastClickEvent);
		isShovelSelected = false;

	}

	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void PeashooterEvent(MouseEvent event) {
		this.LastClickEvent = "Peashooter";
		System.out.println(this.LastClickEvent);
		isShovelSelected = false;
		CollectedSunLabel.setText(Game.currUser.getCollectedSuns() + "");
	}

	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void WallnutEvent(MouseEvent event) {
		this.LastClickEvent = "Wallnut";
		System.out.println(this.LastClickEvent);
		isShovelSelected = false;
	}

	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void JalpenoEvent(MouseEvent event) {
		this.LastClickEvent = "Jalpeno";
		System.out.println(this.LastClickEvent);
		isShovelSelected = false;
	}

	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void FrozenPeaEvent(MouseEvent event) {
		this.LastClickEvent = "FrozenPea";
		System.out.println(this.LastClickEvent);
		isShovelSelected = false;
	}

	// Event Listener on StackPane.onMouseClicked
	@FXML
	public void ClickedOnLawnHandler(MouseEvent e) {
		System.out.println("Clicked on lawn");
		StackPane sp = (StackPane) e.getSource();
		if (!sp.getChildren().isEmpty() && this.isShovelSelected == true) {
			GImage plantimg = ((GImage) (sp.getChildren().get(0)));
			((Plant) plantimg.getObj()).hasDied();
			sp.getChildren().clear();
			Game.CurrentPlants.get(((Plant) plantimg.getObj()).getRow() - 1).remove(((Plant) plantimg.getObj()));
			isShovelSelected = false;
			return;
		}
		if (this.LastClickEvent == null) {
			System.out.println("plant Not Selected");
			return;
		}
		System.out.println("Plant Planted");
		isShovelSelected = false;
		this.sowPlant(e);
	}

	private void sowPlant(MouseEvent e) {
		StackPane sp = (StackPane) e.getSource();
		if (!sp.getChildren().isEmpty()) {
			System.out.println("Already Occupied!!!");
			this.LastClickEvent = null;
			isShovelSelected = false;
			return;
		}
		Plant plant = this.getPlant(sp);
		System.out.println("Plant Gotted");
		if (plant == null) {
			System.out.println("Not Enough Money");
			this.LastClickEvent = null;
			isShovelSelected = false;
			return;
		}
		sp.getChildren().add(plant.getImage());

		Game.CurrentPlants.get(plant.getRow() - 1).set(plant.getColumn() - 1, plant);
		this.LastClickEvent = null;
		isShovelSelected = false;
	}

	private Plant getPlant(StackPane sp) {
		Plant plant = null;
		Timeline timeline = null;
		if (this.LastClickEvent.equals("Peashooter") && Game.currUser.getCollectedSuns() >= 100) {
			plant = new Peashooter();
			((Peashooter) plant).shoot(root, sp, plant);
			this.Peashooter.setVisible(false);
			timeline = new Timeline(new KeyFrame(Duration.millis(5000), e -> {
				this.Peashooter.setVisible(true);

			}));
			timeline.setCycleCount(1);
			this.cooldown = true;
			timeline.play();
		} else if (this.LastClickEvent.equals("SunFlower") && Game.currUser.getCollectedSuns() >= 50) {
			plant = new SunFlower();
			((SunFlower) plant).generateSun();
			this.Sunflower.setVisible(false);
			timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> {
				this.Sunflower.setVisible(true);
			}));
			timeline.setCycleCount(1);
			this.cooldown = true;
			timeline.play();
		} else if (this.LastClickEvent.equals("Jalpeno") && Game.currUser.getCollectedSuns() >= 175) {
			plant = new Jalpeno();
			this.Jalpeno.setVisible(false);
			timeline = new Timeline(new KeyFrame(Duration.millis(8000), e -> {
				this.Jalpeno.setVisible(true);
			}));
			timeline.setCycleCount(1);
			this.cooldown = true;
			timeline.play();
		} else if (this.LastClickEvent.equals("Wallnut") && Game.currUser.getCollectedSuns() >= 50) {
			plant = new Wallnut();
			this.Wallnut.setVisible(false);
			timeline = new Timeline(new KeyFrame(Duration.millis(9000), e -> {
				this.Wallnut.setVisible(true);
			}));
			timeline.setCycleCount(1);
			this.cooldown = true;
			timeline.play();
		} else if (this.LastClickEvent.equals("FrozenPea") && Game.currUser.getCollectedSuns() >= 175) {
			plant = new FrozenPea();
			((FrozenPea) plant).shoot(root, sp, plant);
			this.Frozenpea.setVisible(false);
			timeline = new Timeline(new KeyFrame(Duration.millis(7000), e -> {
				this.Frozenpea.setVisible(true);
			}));
			timeline.setCycleCount(1);
			this.cooldown = true;
			timeline.play();
		}
		if (timeline != null) {
			timeline.setOnFinished(e -> {
				this.cooldown = false;
			});
		}
		if (plant == null) {
			return null;
		}
		plant.setRow(Integer.parseInt(sp.getId().substring(1, 2)));
		plant.setColumn(Integer.parseInt(sp.getId().substring(3)));
		Game.currUser.setCollectedSuns(Game.currUser.getCollectedSuns() - plant.getCost());
		return plant;
	}

	public Label getCollectedSunLabel() {
		return CollectedSunLabel;
	}

	public void setCollectedSunLabel(Label collectedSunLabel) {
		CollectedSunLabel = collectedSunLabel;
	}

	// Event Listener on ImageView[#Shovelimg].onMouseClicked
	@FXML
	public void ShovelClicked(MouseEvent event) {
		this.isShovelSelected = true;
		System.out.println("ShovelSelected");
	}

	// Event Listener on ImageView[#MenuButtonimg].onMouseClicked
	@FXML
	public void inGameMenuHandler(MouseEvent event) throws IOException, InterruptedException {
		String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\InGameMenuUI.fxml";
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
		AnchorPane root = (AnchorPane) loader.load(fxmlStream);
		MainMenu.mainStage.setScene(new Scene(root));
		try {
			CreateSun.running.set(false);
			CreateZombie.running.set(false);
		} catch (NullPointerException d) {
			System.out.println("nullpointer");
		} catch (ThreadDeath f) {
			System.out.println("Thread Death");
		}
		MainMenu.Serialize();
		Game.CurrentZombies.forEach(arr -> {
			arr.forEach(n -> {
				n.getTrans().stop();
				n.getTimeline().stop();
				n.getImg().setX(1000);
				n.getImg().setVisible(false);
			});
			arr.clear();
		});
		Game.CurrentPlants.forEach(arr -> {
			arr.forEach(p -> {
				if (p != null) {
					p.hasDied();
				}
			});
		});
	}

	@FXML
	public void ResumeGame(MouseEvent e) throws IOException, ClassNotFoundException {
		MainMenu.deSerialize();

		String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Level1.fxml";
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
		AnchorPane root = (AnchorPane) loader.load(fxmlStream);
		MainMenu.mainStage.setScene(new Scene(root));
		CreateSun s = new CreateSun(root);
		MainMenu.t1 = new Thread(s);
		t1.setDaemon(true);
		MainMenu.t1.start();
		MainMenu.t2 = new Thread(new CreateZombie(root, this.progressBar));
		MainMenu.t2.setDaemon(true);
		MainMenu.t2.start();

		for (int i = 0; i < root.getChildren().size(); i++) {
			if ("LawnGridPane".equals(root.getChildren().get(i).getId())) {
				GridPane gp = (GridPane) root.getChildren().get(i);
				for (int j = 0; j < Game.CurrentPlants.size(); j++) {
					for (int k = 0; k < Game.CurrentPlants.get(j).size(); k++) {
						Plant p = Game.CurrentPlants.get(j).get(k);
						if (p != null) {
							p.intializeImage();
							for (int l = 0; l < gp.getChildren().size(); l++) {
								String plantLocation = "R" + p.getRow() + "C" + p.getColumn();
								if (gp.getChildren().get(l).getId().equals(plantLocation)) {
									StackPane sp = (StackPane) gp.getChildren().get(l);
									sp.getChildren().add(p.getImage());
									p.hasRevive(root, sp);
									break;
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < Game.CurrentZombies.size(); i++) {
			for (int j = 0; j < Game.CurrentZombies.get(i).size(); j++) {
				Zombie z = Game.CurrentZombies.get(i).get(j);
				z.initializeImage();
				z.getImg().setLayoutX(800);
				z.getImg().setLayoutY((i * 100) + 20);
				root.getChildren().add(z.getImg());
			}
		}

	}

	@FXML
	public void SaveAndExit(MouseEvent event) throws IOException {
		System.out.println("Saving & Exiting...");
		MainMenu.Serialize();
		System.exit(0);
	}

	@SuppressWarnings("deprecation")
	public static void Serialize() throws IOException {
		try {
			CreateSun.running.set(false);
			CreateZombie.running.set(false);
		} catch (NullPointerException d) {
			System.out.println("nullpointer");
		} catch (ThreadDeath f) {
			System.out.println("Thread Death");
		}
		String path = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\gameDatabase\\" + Game.currUser.getName()
				+ ".txt";
		ObjectOutputStream o = null;
		System.out.println(Game.currUser.getName());
		try {
			o = new ObjectOutputStream(new FileOutputStream(path));
			User u = new User();
			u.setCollectedSuns(Game.currUser.getCollectedSuns());
			u.setCurrentPlants(Game.CurrentPlants);
			u.setLawnMowers(Game.lawnMowers);
			u.setLevel(Game.currUser.getLevel());
			u.setName(Game.currUser.getName());
			u.setNumberofZombieToWin(CreateZombie.zombieDeathCount);
			o.writeObject(u);
			Game.CurrentPlants.forEach(arr -> {
				arr.forEach(p -> {
					if (p != null) {
						p.hasDied();
					}
				});
			});
		} finally {
			if (o != null) {
				o.close();
			}
		}
	}

	public static void deSerialize() throws IOException, ClassNotFoundException {
		String path = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\gameDatabase\\Nandesh.txt";
		ObjectInputStream i = null;
		try {
			i = new ObjectInputStream(new FileInputStream(path));
			User u = (User) i.readObject();

			Game.currUser = u;
			Game.CurrentPlants = u.getCurrentPlants();
			// Game.CurrentZombies = u.getCurrentZombies();
			Game.lawnMowers = u.getLawnMowers();
			CreateZombie.zombieDeathCount = u.getZombiesKilled();

		} finally {
			if (i != null) {
				i.close();
			}
		}
	}

	@FXML
	public void UpdateSun(MouseEvent event) throws ClassNotFoundException, IOException, InterruptedException {
		CollectedSunLabel.setText(Game.currUser.getCollectedSuns() + "");
		if (CreateZombie.zombieDeathCount >= 1) {
			if (Game.currUser.getLevel() == 1) {
				Game.currUser.setLevel(2);
				this.Sunflower.setVisible(true);
				Game.currUser.setCollectedSuns(100);
				CreateZombie.zombieDeathCount = 0;
				this.progressBar.setProgress(0);
				
			} else if (Game.currUser.getLevel() == 2) {
				Game.currUser.setLevel(3);
				this.Wallnut.setVisible(true);
				Game.currUser.setCollectedSuns(100);
				CreateZombie.zombieDeathCount = 0;
				this.progressBar.setProgress(0);
			} else if (Game.currUser.getLevel() == 3) {
				Game.currUser.setLevel(4);
				Game.currUser.setCollectedSuns(100);
				this.Jalpeno.setVisible(true);
				CreateZombie.zombieDeathCount = 0;
				this.progressBar.setProgress(0);
			} else if (Game.currUser.getLevel() == 4) {
				Game.currUser.setLevel(5);
				this.Frozenpea.setVisible(true);
				CreateZombie.zombieDeathCount = 0;
				Game.currUser.setCollectedSuns(100);
				this.progressBar.setProgress(0);
			} else if (Game.currUser.getLevel() == 5) {
				String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\WinnerWindow.fxml";
				FXMLLoader loader = new FXMLLoader();
				FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
				AnchorPane root = (AnchorPane) loader.load(fxmlStream);
				MainMenu.mainStage.setScene(new Scene(root));
				Thread.sleep(2000);
				System.exit(0);
			}
			Game.CurrentZombies.forEach(arr -> {
				arr.forEach(n -> {
					n.getTrans().stop();
					n.getTimeline().stop();
					n.getImg().setX(1000);
					n.getImg().setVisible(false);
				});
				
				arr.clear();
			});
			Game.CurrentPlants.forEach(arr->{
				for(int i=0;i<arr.size();i++) {
					if(arr.get(i)!=null) {
						arr.get(i).hasDied();
						arr.set(i, null);
					}
				}
			});
			CreateZombie.zombieDeathCount=0;
			Game.currUser.setCollectedSuns(100);
			this.Serialize();
			this.HandlePlayEvent(null);
		}

		if (Game.currUser.getLevel() == 1 && !this.cooldown) {
			this.progressBar.setProgress(CreateZombie.zombieDeathCount);
		} else if (Game.currUser.getLevel() == 2 && !this.cooldown) {
			this.Sunflower.setVisible(true);
			this.progressBar.setProgress(CreateZombie.zombieDeathCount);
		} else if (Game.currUser.getLevel() == 3 && !this.cooldown) {
			this.Wallnut.setVisible(true);
			this.Sunflower.setVisible(true);
			this.progressBar.setProgress(CreateZombie.zombieDeathCount);
		} else if (Game.currUser.getLevel() == 4 && !this.cooldown) {
			this.Wallnut.setVisible(true);
			this.Sunflower.setVisible(true);
			this.Jalpeno.setVisible(true);
			this.progressBar.setProgress(CreateZombie.zombieDeathCount);
		} else if (Game.currUser.getLevel() == 5 && !this.cooldown) {
			this.Wallnut.setVisible(true);
			this.Sunflower.setVisible(true);
			this.Jalpeno.setVisible(true);
			this.Frozenpea.setVisible(true);
			this.progressBar.setProgress(CreateZombie.zombieDeathCount);
		}

		if (this.toUpdate) {
			for (int i = 0; i < Game.lawnMowers.length; i++) {
				Game.lawnMowers[i] = new LawnMower(i + 1);
			}
			CreateZombie.pb = this.progressBar;
			Game.lawnMowers[0].setImage(LM1);
			Game.lawnMowers[1].setImage(LM2);
			Game.lawnMowers[2].setImage(LM3);
			Game.lawnMowers[3].setImage(LM4);
			Game.lawnMowers[4].setImage(LM5);
			toUpdate = false;
		}
	}

	@FXML
	public void selectLevelEvent(MouseEvent event) throws FileNotFoundException, IOException {
		System.out.println("Choose Level");
		String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\selectLevel.fxml";
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
		AnchorPane root = (AnchorPane) loader.load(fxmlStream);
		MainMenu.mainStage.setScene(new Scene(root));
	}

	@FXML
	public void onLevelSelection(MouseEvent event) throws IOException, ClassNotFoundException {
		String s;
		if (event.getSource().getClass() == Button.class) {
			s = ((Button) event.getSource()).getId();
		} else {
			s = ((ImageView) event.getSource()).getId();
		}
		Game.initializeVariables();
		if (s.equals("level1")) {
			Game.currUser.setLevel(1);
		} else if (s.equals("level2")) {
			Game.currUser.setLevel(2);
		} else if (s.equals("level3")) {
			Game.currUser.setLevel(3);
		} else if (s.equals("level4")) {
			Game.currUser.setLevel(4);
		} else if (s.equals("level5")) {
			Game.currUser.setLevel(5);
		}
		CreateZombie.zombieDeathCount=0;
		Game.currUser.setCollectedSuns(100);
		this.Serialize();
		this.HandlePlayEvent(null);
	}
	
	
	public void retryLevel(MouseEvent e) throws ClassNotFoundException, IOException {
		ImageView img = (ImageView) e.getSource();

		String level = "level" + Game.currUser.getLevel();
		img.setId(level);
		onLevelSelection(e);
	}

	public void restartLevel(MouseEvent e) throws ClassNotFoundException, IOException {
		Button btn = (Button) e.getSource();

		String level = "level" + Game.currUser.getLevel();
		btn.setId(level);
		onLevelSelection(e);
	}
	
	public void ReturnToMainMenu(MouseEvent e) throws ClassNotFoundException, IOException {
		this.start(mainStage);
	}
}
