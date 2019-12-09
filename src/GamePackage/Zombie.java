package GamePackage;

import javafx.animation.Timeline;
import javafx.animation.Transition;

public abstract class Zombie extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient GImage img;
	protected int Health;
	protected int BiteDamage;
	protected int moveSpeed;
	protected transient Transition trans;
	protected transient Timeline timeline;
	public Timeline getTimeline() {
		return timeline;
	}
	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	public Transition getTrans() {
		return trans;
	}
	public void setTrans(Transition trans) {
		this.trans = trans;
	}
	public GImage getImg() {
		return img;
	}
	public void setImg(GImage img) {
		this.img = img;
	}
	public int getHealth() {
		return Health;
	}
	public void setHealth(int health) {
		Health = health;
	}
	public int getBiteDamage() {
		return BiteDamage;
	}
	public void setBiteDamage(int biteDamage) {
		BiteDamage = biteDamage;
	}
	public int getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public abstract void initializeImage();
	
}
