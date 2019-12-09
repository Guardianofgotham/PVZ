package GamePackage;

import java.io.File;

import Controller.CreateZombie;
import UI.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Jalpeno extends Plant {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean flag = true;

	public Jalpeno() {
		this.name = "Jalpeno";
		this.attackType = "Hellfire";
		this.health = 1000;
		this.cost = 175;
		this.image = new GImage();
		this.image.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\Jalpenocropped.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);
		explode();
	}

	public void explode() {

		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
			if (flag == false) {
				this.image.setVisible(false);
				Game.CurrentZombies.get(this.Row - 1).forEach(n -> {
					n.getImg().setX(1000);
					n.getImg().setVisible(false);
					CreateZombie.zombieDeathCount += (0.1 / Game.currUser.getLevel());
					CreateZombie.pb.setProgress(CreateZombie.zombieDeathCount);
				});
				Game.CurrentZombies.get(this.Row - 1).clear();
			}
			flag = false;
		}));
		timeline.setCycleCount(2);
		timeline.play();
	}

	@Override
	public void hasDied() {
		this.image.setVisible(false);
		Game.CurrentPlants.get(this.Row - 1).remove(this);
	}

	@Override
	public void intializeImage() {
		this.image = new GImage();
		this.image.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\Jalpenocropped.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);
	}

	@Override
	public void hasRevive(AnchorPane root, StackPane sp) {
		// TODO Auto-generated method stub
		
	}

}
