package GamePackage;

import java.io.File;
import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LawnMower extends GameObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isGone;
	private final int rowNumber;
	private transient ImageView image;
	public LawnMower(int rowNumber){
		this.rowNumber=rowNumber;
		this.isGone=false;
		
	}
	public boolean isGone() {
		return isGone;
	}
	public void setGone(boolean isGone) {
		this.isGone = isGone;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public ImageView getImage() {
		return image;
	}
	public void setImage(ImageView image) {
		this.image = image;
	}
	
	
}
