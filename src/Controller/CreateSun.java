package Controller;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import UI.Game;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class CreateSun implements Runnable {
	
	static Random r = new Random();
	public static AnchorPane p;
	public static AtomicBoolean running=new AtomicBoolean(false);
	public CreateSun(AnchorPane p){
		this.p=p;
	}
	
	public static ImageView generateSun() {
		int initx=r.nextInt(525)+275;
		int inity = -30;
		String pathToImage = "C:\\Users\\Hardik\\Desktop\\Docs\\Eclipse\\PlantVsZombies\\src\\Images\\sun.gif";
		Image img=new Image(new File(pathToImage).toURI().toString());
		ImageView imgview =new ImageView(img);
		imgview.setLayoutX(initx);
		imgview.setLayoutY(inity);
		
		TranslateTransition trans = new TranslateTransition();
		trans.setDuration(Duration.seconds(5));
		int finy=r.nextInt(550)+25;
		trans.setToX(0);
		trans.setToY(finy+30);
		trans.setNode(imgview);
		trans.play();
		imgview.setOnMouseClicked(e->{
			imgview.setVisible(false);
			Game.currUser.setCollectedSuns(Game.currUser.getCollectedSuns()+25);
		});
		return imgview;
	}

	@Override
	public void run() {
		running.set(true);
		while(running.get()) {
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					CreateSun.p.getChildren().add(CreateSun.generateSun());
				}
			});
			try {
				Thread.sleep(5000);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
		
	}
}


