package GamePackage;

import java.io.File;
import java.util.Random;

import Controller.CreateSun;
import UI.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SunFlower extends Plant {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient ImageView imgview = null;
	transient Timeline timeline;

	public SunFlower() {
		this.name = "Peashooter";
		this.attackType = "Pea";
		this.health = 100;
		this.cost = 50;
		this.image = new GImage();
		this.image.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\SunFlower.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);
	}

	public ImageView generateSun() {

		timeline = new Timeline(new KeyFrame(Duration.millis(10000), e -> {
			double initx = this.getImage().getParent().getLayoutX() + 225;
			double inity = this.getImage().getParent().getLayoutY() + 100;
			String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PlantVsZombies\\src\\Images\\sun.gif";
			Image img = new Image(new File(pathToImage).toURI().toString());
			imgview = new ImageView(img);
			imgview.setLayoutX(initx);
			imgview.setLayoutY(inity);
			imgview.setOnMouseClicked(p -> {
				imgview.setVisible(false);
				Game.currUser.setCollectedSuns(Game.currUser.getCollectedSuns() + 25);
			});
			CreateSun.p.getChildren().add(imgview);
		}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		return imgview;
	}

	@Override
	public void hasDied() {
		if(timeline!=null) {
			this.timeline.stop();
		}
		this.image.setVisible(false);

	}

	@Override
	public void intializeImage() {
		this.image = new GImage();
		this.image.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\SunFlower.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);
	}

	@Override
	public void hasRevive(AnchorPane root, StackPane sp) {
		this.generateSun();
		
	}

}
