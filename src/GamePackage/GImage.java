package GamePackage;

import java.io.Serializable;

import javafx.scene.image.ImageView;

public class GImage extends ImageView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected GameObject obj;

	public GameObject getObj() {
		return obj;
	}
	
}