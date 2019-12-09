package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import GamePackage.*;
import UI.Game;
import UI.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BackyardController {
	@FXML
	private AnchorPane root;
	@FXML
	private ImageView BackYardimg;
	@FXML
	private VBox PlantsSelectorContainer;
	@FXML
	private GridPane LawnGridPane;
	@FXML
	private ImageView Shovelimg;
	@FXML
	private ImageView SunImg;
	@FXML
	private Label CollectedSunLabel;
	@FXML
	private ImageView MenuButtonimg;
	private String LastClickEvent;
	private boolean isShovelSelected;
	//static Label sunCount;
	
	
	// Event Listener on ImageView[#BackYardimg].onMouseClicked
	@FXML
	public void BackGroundClickHandler(MouseEvent event) {
		this.LastClickEvent=null;
		System.out.println("BackGround Clicked!!!");
		isShovelSelected=false;
		System.out.println(CollectedSunLabel.getText());
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void SunflowerEvent(MouseEvent event) {
		this.LastClickEvent="SunFlower";
		System.out.println(this.LastClickEvent);
		isShovelSelected=false;
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void PeashooterEvent(MouseEvent event) {
		this.LastClickEvent="Peashooter";
		System.out.println(this.LastClickEvent);
		isShovelSelected=false;
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void WallnutEvent(MouseEvent event) {
		this.LastClickEvent="Wallnut";
		System.out.println(this.LastClickEvent);
		isShovelSelected=false;
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void CherryBombEvent(MouseEvent event) {
		this.LastClickEvent="CherryBomb";
		System.out.println(this.LastClickEvent);
		isShovelSelected=false;
	}
	// Event Listener on ImageView.onMouseClicked
	@FXML
	public void FrozenPeaEvent(MouseEvent event) {
		this.LastClickEvent="FrozenPea";
		System.out.println(this.LastClickEvent);
		isShovelSelected=false;
	}
	// Event Listener on StackPane.onMouseClicked
	@FXML
	public void ClickedOnLawnHandler(MouseEvent e) {
		StackPane sp = (StackPane) e.getSource();
		if(this.isShovelSelected && !sp.getChildren().isEmpty()) {
			GImage plantimg = ((GImage)(sp.getChildren().get(0)));
			((Plant)plantimg.getObj()).hasDied();
			sp.getChildren().clear();
			Game.CurrentPlants.get(((Plant)plantimg.getObj()).getRow()).remove(((Plant)plantimg.getObj()));
			isShovelSelected=false;
			return;
		}
		if(this.LastClickEvent==null) {
			System.out.println("plant Not Selected");
			return;
		}
		System.out.println("Plant Planted");
		isShovelSelected=false;
		this.sowPlant(e);
	}
	
	private void sowPlant(MouseEvent e) {
		StackPane sp = (StackPane) e.getSource();
		if(!sp.getChildren().isEmpty()) {
			System.out.println("Already Occupied!!!");
			return;
		}
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\Images\\"+this.LastClickEvent+".gif";
		Plant plant;
		if(this.LastClickEvent.equals("Peashooter")) {
			plant=new Peashooter();
		}
		else if(this.LastClickEvent.equals("SunFlower")) {
			plant = new SunFlower();
		}
		Image img=new Image(new File(pathToImage).toURI().toString());
		ImageView imgview =new ImageView(img);
		sp.getChildren().add(imgview);
		this.LastClickEvent=null;
		isShovelSelected=false;
	}
	
	// Event Listener on ImageView[#Shovelimg].onMouseClicked
	@FXML
	public void ShovelClicked(MouseEvent event) {
		this.isShovelSelected=true;
		System.out.println("ShovelSelected");
	}
	// Event Listener on ImageView[#MenuButtonimg].onMouseClicked
	@FXML
	public void inGameMenuHandler(MouseEvent event) throws IOException {
		String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\InGameMenuUI.fxml";
		FXMLLoader loader = new FXMLLoader();
		FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
		AnchorPane root = (AnchorPane) loader.load(fxmlStream);
		MainMenu.mainStage.setScene(new Scene(root));
	}
}
