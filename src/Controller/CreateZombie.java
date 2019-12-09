package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import GamePackage.FootBallerZombie;
import GamePackage.GImage;
import GamePackage.LawnMower;
import GamePackage.NormalZombie;
import GamePackage.Peashooter;
import GamePackage.Plant;
import GamePackage.Zombie;
import UI.Game;
import UI.MainMenu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class CreateZombie implements Runnable {
	static AnchorPane root;
	static int basePosition = 20;
	static int RowGaps = 100;
	static Random r = new Random();
	public static AtomicBoolean running=new AtomicBoolean(false);
	//private TranslateTransition trans = null;
	public static ProgressBar pb;
	public static double zombieDeathCount=0;
	boolean flag=true;

	public CreateZombie(AnchorPane root,ProgressBar pb) {
		CreateZombie.root = root;
		CreateZombie.pb=pb;
		System.out.println(pb);
	}

	@Override
	public void run() {
		running.set(true);
		while (running.get()) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					Random r=new Random();
					int s=r.nextInt(50)+1;
					System.out.println("XXXXXXXYYYYYYYYYYYYYY");
					if(s*Game.currUser.getLevel()<=48) {
					try {
						CreateZombie.root.getChildren().add(CreateZombie.generateZombie(new NormalZombie()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					else {
						try {
							CreateZombie.root.getChildren().add(CreateZombie.generateZombie(new FootBallerZombie()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			});
			try {
				Thread.sleep(12000);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}

	}

	static Plant getRequiredPlant(Zombie z) {
		Plant plant = null;
		try {
			int RowNumber = (int) (z.getImg().getLayoutY() - 20) / 100;
			for (int i = Game.CurrentPlants.get(RowNumber).size() - 1; i > -1; i--) {
				if (Game.CurrentPlants.get(RowNumber).get(i) == null) {
					continue;
				}
				if ( ((StackPane) ( Game.CurrentPlants.get(RowNumber).get(i).getImage().getParent())).getLayoutX() < z.getImg().getLayoutX()) {
					return Game.CurrentPlants.get(RowNumber).get(i);
				}
			}
		}
		catch(Throwable e) {
			
		}
		return plant;
	}

	static GImage generateZombie(Zombie z) throws IOException {
		z.getImg().setLayoutX(900);
		int rowNumberofZombie = CreateZombie.r.nextInt(5);
		int SpawnedZombieLocation = CreateZombie.basePosition + rowNumberofZombie * CreateZombie.RowGaps;
		Game.CurrentZombies.get(rowNumberofZombie).add(z);
		z.getImg().setLayoutY(SpawnedZombieLocation);
		TranslateTransition trans = new TranslateTransition();
		trans.setToX(-800);
		trans.setToY(0);
		trans.setCycleCount(1);
		trans.setDuration(Duration.millis(30000/z.getMoveSpeed()));
		trans.setNode(z.getImg());
		// System.out.println(root);
		z.setTrans(trans);
		GridPane g = null;
		for (int i = 0; i < root.getChildren().size(); i++) {
			if (root.getChildren().get(i).getId().equals("LawnGridPane")) {
				g = (GridPane) root.getChildren().get(i);
				break;
			}
		}
		StackPane sp10 = null, sp20 = null, sp30 = null, sp40 = null, sp50 = null;
		for (int i = 0; i < g.getChildren().size(); i++) {

			if (g.getChildren().get(i).getId().equals("R1C1")) {
				sp10 = (StackPane) g.getChildren().get(i);
			} else if (g.getChildren().get(i).getId().equals("R2C1")) {
				sp20 = (StackPane) g.getChildren().get(i);
			} else if (g.getChildren().get(i).getId().equals("R3C1")) {
				sp30 = (StackPane) g.getChildren().get(i);
			} else if (g.getChildren().get(i).getId().equals("R4C1")) {
				sp40 = (StackPane) g.getChildren().get(i);
			} else if (g.getChildren().get(i).getId().equals("R5C1")) {
				sp50 = (StackPane) g.getChildren().get(i);
			}
			if (sp10 != null && sp20 != null && sp30 != null && sp40 != null && sp50 != null) {
				break;
			}
		}
		final StackPane sp1 = sp10, sp2 = sp20, sp3 = sp30, sp4 = sp40, sp5 = sp50;
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			if (z.getHealth() <= 0) {
				z.getImg().setVisible(false);
				trans.stop();
				z.getImg().setX(1000);
				
				
			}
			Plant p = CreateZombie.getRequiredPlant(z);

			StackPane sp = null;

			if (p != null) {
				sp = (StackPane) p.getImage().getParent();
				GridPane gp = (GridPane) sp.getParent();
				String sop = sp.getId().substring(0, 3) + (Integer.parseInt(sp.getId().substring(3)) + 1);
				for (int i = 0; i < gp.getChildren().size(); i++) {
					if (sop.equals(gp.getChildren().get(i).getId())) {
						sp = (StackPane) gp.getChildren().get(i);
						break;
					}
				}
			}
			int RowNumber = (int) (z.getImg().getLayoutY() - 20) / 100;
			if (p != null && sp != null && z.getImg().getBoundsInParent().intersects(sp.getBoundsInParent())) {
				p.setHealth(p.getHealth() - z.getBiteDamage());
				// System.out.println("apple");
				trans.pause();
				if (p.getHealth() <= 0) {
					System.out.println("Plant Died!!!");
					p.hasDied();
					Game.CurrentPlants.get(RowNumber).remove(p);
					((StackPane) p.getImage().getParent()).getChildren().clear();

					// System.out.println(sp);
					trans.play();
				}
			}
			if (p == null && sp == null) {
				trans.play();
			}
			try {
				if (!Game.lawnMowers[RowNumber].isGone()
						&& (z.getImg().getBoundsInParent().intersects(sp1.getBoundsInParent())
								|| z.getImg().getBoundsInParent().intersects(sp2.getBoundsInParent())
						|| z.getImg().getBoundsInParent().intersects(sp3.getBoundsInParent())
						|| z.getImg().getBoundsInParent().intersects(sp4.getBoundsInParent())
						|| z.getImg().getBoundsInParent().intersects(sp5.getBoundsInParent()))) {
					CreateZombie.runLawnmower(RowNumber);
					Game.CurrentZombies.get(RowNumber).forEach(n -> {
						n.getImg().setX(1000);
						n.getImg().setVisible(false);
						CreateZombie.zombieDeathCount+=(0.1/Game.currUser.getLevel());
						CreateZombie.pb.setProgress(CreateZombie.zombieDeathCount);
					});
					Game.CurrentZombies.get(RowNumber).clear();
					Game.lawnMowers[RowNumber].setGone(true);
				}
				if (Game.lawnMowers[RowNumber].isGone()
						&& (z.getImg().getBoundsInParent().intersects(sp1.getBoundsInParent())
								|| z.getImg().getBoundsInParent().intersects(sp2.getBoundsInParent())
						|| z.getImg().getBoundsInParent().intersects(sp3.getBoundsInParent())
						|| z.getImg().getBoundsInParent().intersects(sp4.getBoundsInParent())
						|| z.getImg().getBoundsInParent().intersects(sp5.getBoundsInParent()))) {
					try {
						 String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\LoseWindow.fxml";
						FXMLLoader loader = new FXMLLoader();
						FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
						AnchorPane root = (AnchorPane) loader.load(fxmlStream);
						MainMenu.mainStage.setScene(new Scene(root));
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
						Game.currUser.setCollectedSuns(100);
						CreateZombie.zombieDeathCount=0;
						MainMenu.Serialize();
						
						z.getTimeline().stop();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
			catch(Exception ex) {
				
			}
		
			
			

		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		trans.play();
		timeline.play();
		z.setTimeline(timeline);
		return z.getImg();
	}

	static void runLawnmower(int row) {
		TranslateTransition trans = new TranslateTransition();
		trans.setCycleCount(1);
		trans.setToX(800);
		trans.setToY(0);
		trans.setDuration(Duration.millis(2000));
		trans.setNode(Game.lawnMowers[row].getImage());
		trans.play();
		trans.setOnFinished(e -> {
			trans.stop();
			Game.lawnMowers[row].getImage().setVisible(false);
		});
	}
	

}
