package GamePackage;

import java.io.File;
import java.io.Serializable;

import Controller.CreateZombie;
import UI.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Peashooter extends Plant {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int shootRate;
	private pea newPea;
	private transient Timeline timeline = null;
	private transient TranslateTransition trans = null;

	class pea extends GameObject implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		transient GImage img;
		int damageDealt;

		pea() {
			damageDealt = 20;
			img = new GImage();
		}
	}

	public Peashooter() {
		this.name = "Peashooter";
		this.attackType = "Pea";
		this.health = 100;
		this.cost = 100;
		this.image = new GImage();
		this.image.obj = this;
		this.shootRate = 1;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\Peashooter.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);
		this.newPea = null;
	}

	public int getShootRate() {
		return shootRate;
	}

	@Override
	public void hasDied() {

		this.timeline.stop();
		this.trans.stop();
		this.newPea.img.setVisible(false);
		this.newPea.damageDealt = 0;
	}

	public void hasRevive(AnchorPane root, StackPane sp) {
		this.shoot(root,sp,this);
	}
	
	public GImage shoot(AnchorPane root, StackPane sp, Plant myPlant) {
		pea newPea = this.new pea();
		this.newPea = newPea;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\Pea.png";
		Image img = new Image(new File(pathToImage).toURI().toString());
		newPea.img.setImage(img);
		newPea.img.setLayoutX(sp.getLayoutX() + 225);
		newPea.img.setLayoutY(sp.getLayoutY() + 100);
		System.out.println(sp.getLayoutX() + 225);
		// Shooting Pea
		newPea.img.setVisible(false);
		int value = (int) newPea.img.getBoundsInParent().getMaxX();
		TranslateTransition trans = new TranslateTransition();
		trans.setToX(800);
		trans.setToY(0);
		trans.setCycleCount(Animation.INDEFINITE);
		trans.setDuration(Duration.millis(2500));
		trans.setNode(newPea.img);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
			if (!Game.CurrentZombies.get(myPlant.getRow() - 1).isEmpty()
					&& trans.getStatus() != Animation.Status.RUNNING) {
				newPea.img.setVisible(true);
				newPea.damageDealt = 20;
				trans.play();
			}
			if (Game.CurrentZombies.get(myPlant.getRow() - 1).isEmpty()) {
				trans.stop();
				// newPea.img.setLayoutX(value);
			}
			if ((int) newPea.img.getBoundsInParent().getMaxX() == value) {
				newPea.img.setVisible(false);
			}
			if ((int) newPea.img.getBoundsInParent().getMaxX() != value) {
				newPea.img.setVisible(true);
			}
			if ((int) newPea.img.getBoundsInParent().getMaxX() != value
					&& !Game.CurrentZombies.get(myPlant.getRow() - 1).isEmpty()) {
				Zombie z = null;
				for (int i = 0; i < Game.CurrentZombies.get(myPlant.getRow() - 1).size(); i++) {
					if (newPea.img.getBoundsInParent()
							.intersects(Game.CurrentZombies.get(myPlant.getRow() - 1).get(i).img.getBoundsInParent())) {
						z = Game.CurrentZombies.get(myPlant.getRow() - 1).get(i);
						break;
					}
				}
				if (z != null) {
					z.setHealth(z.getHealth() - newPea.damageDealt);
					System.out.println(z.getHealth());
					if (z.getHealth() <= 0) {
						Game.CurrentZombies.get(myPlant.getRow() - 1).remove(0);
						CreateZombie.zombieDeathCount += (0.1 / Game.currUser.getLevel());
						CreateZombie.pb.setProgress(CreateZombie.zombieDeathCount);
					}
					trans.stop();
					trans.setDelay(Duration.seconds(1));
				}

			}
			if (trans.getStatus() == Animation.Status.STOPPED) {
				newPea.img.setVisible(false);
				newPea.damageDealt = 0;
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		this.timeline = timeline;
		this.trans = trans;
		root.getChildren().add(newPea.img);
		return newPea.img;

	}

	@Override
	public void intializeImage() {
		this.image = new GImage();
		this.image.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\Peashooter.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);
	}

}
