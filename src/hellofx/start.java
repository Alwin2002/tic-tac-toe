package hellofx;

import java.net.UnknownHostException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class start extends Application{
    VBox vbox = new VBox();

    
    
    public void start(Stage primaryStage){



        TextField text1 = new TextField();
        text1.setPromptText("Enter Username");
        TextField text2 = new TextField();
        text2.setPromptText("Enter password");
        Button b1 = new Button("Login In");
        Button b2 = new Button("Sign Up");
        Text t = new Text("OR");
        vbox.getChildren().addAll(text1,text2,b1,t,b2);
        vbox.setAlignment(Pos.CENTER);


        b2.setOnAction(e -> {try {
            primaryStage.setScene(new signup().get());
        } catch (ClassNotFoundException | UnknownHostException | SQLException e1) {
            e1.printStackTrace();
        }});


        vbox.setSpacing(20);
        vbox.setPadding(new Insets(30));
        Scene scene1 = new Scene(vbox,300,350);

        primaryStage.setScene(scene1);
        primaryStage.setTitle("TIC-TAC-TOE");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}