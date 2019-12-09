package Controller;

import java.io.FileInputStream;
import java.io.IOException;

import UI.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HelpUIController {
	@FXML
	private ImageView HelpBackImg;
	@FXML
	private ImageView HowToPlayimg;
	@FXML
	private TextArea InfoTextArea;
	@FXML
	private ImageView BackToMainMenu;

	// Event Listener on ImageView[#BackToMainMenu].onMouseClicked
	@FXML
	public void HelpMenuBackEvent(MouseEvent event) throws IOException {
		
		String pathtoFXML = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PVZ\\src\\MainMenu.fxml";
		FXMLLoader loader = new FXMLLoader();
        FileInputStream fxmlStream = new FileInputStream(pathtoFXML);
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
        MainMenu.mainStage.setScene(new Scene(root));
	}
}
