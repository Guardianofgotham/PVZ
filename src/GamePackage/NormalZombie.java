package GamePackage;

import java.io.File;

import javafx.scene.image.Image;

public class NormalZombie extends Zombie {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NormalZombie() {
		this.Health = 100;
		this.BiteDamage = 5;
		this.moveSpeed = 1;
		this.img = new GImage();
		this.img.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\normalZombie.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.img.setImage(img);
	}

	@Override
	public void initializeImage() {
		this.img = new GImage();
		this.img.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\normalZombie.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.img.setImage(img);
		
	}
}
