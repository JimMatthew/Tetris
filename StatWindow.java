import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;

public class StatWindow extends Application{

	public StatWindow()
	{
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Rectangle bg = new Rectangle(0,0,300,600);
 		bg.setFill(Color.DARKGRAY);
 		Group root = new Group();
        root.getChildren().add(bg);
        Scene scene = new Scene(root, 600,600);
        primaryStage.setScene(scene);
      //  primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
	}
	
}