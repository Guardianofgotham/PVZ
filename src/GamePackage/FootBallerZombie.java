package GamePackage;

import java.io.File;

import javafx.scene.image.Image;

public class FootBallerZombie extends Zombie{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FootBallerZombie() {
		this.Health = 250;
		this.BiteDamage = 10;
		this.moveSpeed = 1;
		this.img = new GImage();
		this.img.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\FootBallZombie.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.img.setImage(img);
	}

	@Override
	public void initializeImage() {
		this.img = new GImage();
		this.img.obj = this;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\FootBallZombie.gif";
		Image img = new Image(new File(pathToImage).toURI().toString());
		this.img.setImage(img);
		
	}
}
