package GamePackage;

import java.io.File;
import java.io.Serializable;

import Controller.CreateZombie;
import GamePackage.Peashooter.pea;
import UI.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class FrozenPea extends Plant {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int shootRate;
	private Fpea newPea;
	private transient Timeline timeline = null;
	private transient TranslateTransition trans = null;

	class Fpea extends GameObject implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		transient GImage img;
		int damageDealt;

		Fpea() {
			damageDealt = 25;
			img = new GImage();
		}
	}

	public FrozenPea() {
		this.shootRate = 2;
		this.attackType = "FrozenPea";
		this.cost = 175;
		this.health = 100;
		this.image = new GImage();
		this.image.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\FrozenPea.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);
		this.name = "Frozen Pea";
	}

	public int getShootRate() {
		return shootRate;
	}

	public void hasDied() {
		System.out.println("XXXXXXXXXXX");

		this.timeline.stop();
		this.trans.stop();
		this.newPea.img.setVisible(false);
		this.newPea.damageDealt = 0;
	}

	public GImage shoot(AnchorPane root, StackPane sp, Plant myPlant) {
		Fpea newPea = this.new Fpea();
		this.newPea = newPea;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\FrozenPea.png";
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
		trans.setDuration(Duration.millis(3000 / this.shootRate));
		trans.setNode(newPea.img);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
			if (!Game.CurrentZombies.get(myPlant.getRow() - 1).isEmpty()
					&& trans.getStatus() != Animation.Status.RUNNING) {
				newPea.img.setVisible(true);
				newPea.damageDealt = 25;
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
					&& !Game.CurrentZombies.get(myPlant.getRow() - 1).isEmpty() && newPea.img.getBoundsInParent()
							.intersects(Game.CurrentZombies.get(myPlant.getRow() - 1).get(0).img.getBoundsInParent())) {
				Zombie z = Game.CurrentZombies.get(myPlant.getRow() - 1).get(0);
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
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\FrozenPea.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);

	}

	@Override
	public void hasRevive(AnchorPane root, StackPane sp) {
		this.shoot(root, sp, this);		
	}
}
