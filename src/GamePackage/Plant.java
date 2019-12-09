package GamePackage;

import java.io.Serializable;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public abstract class Plant extends GameObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient GImage image;
	protected int health;
	protected int cost;
	protected String attackType;
	protected String name;
	protected int Row;
	protected int Column;
	
	@Override
	public String toString() {
		return this.name+" "+this.health+" "+this.cost+" "+this.attackType+" "+this.Row+" "+this.Column;
	}
	
	abstract public void hasDied();
	
	public int getRow() {
		return Row;
	}

	public void setRow(int row) {
		Row = row;
	}

	public int getColumn() {
		return Column;
	}

	public void setColumn(int column) {
		Column = column;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public GImage getImage() {
		return image;
	}

	public int getHealth() {
		return health;
	}

	public int getCost() {
		return cost;
	}

	public String getAttackType() {
		return attackType;
	}

	public String getName() {
		return name;
	}

	abstract public void intializeImage();
	abstract public void hasRevive(AnchorPane root, StackPane sp);

	
	
}
