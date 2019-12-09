package GamePackage;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class Wallnut extends Plant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Wallnut() {
		this.attackType = null;
		this.cost = 50;
		this.health = 1000;
		this.image = new GImage();
		this.image.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\Wallnut.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);
		this.name = "Wallnut";

	}

	@Override
	public void hasDied() {
		this.image.setVisible(false);

	}

	@Override
	public void intializeImage() {
		this.image = new GImage();
		this.image.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\Wallnut.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.image.setImage(img);

	}

	@Override
	public void hasRevive(AnchorPane root, StackPane sp) {
		// TODO Auto-generated method stub
		
	}
}
