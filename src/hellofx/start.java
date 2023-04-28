package hellofx;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class start extends Application{
    VBox vbox = new VBox();

    
    
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException{


        database db = new database();
        TextField text1 = new TextField();
        text1.setPromptText("Enter Username");
        TextField text2 = new TextField();
        text2.setPromptText("Enter password");
        Button b1 = new Button("Login In");
        Button b2 = new Button("Sign Up");
        Text t = new Text("OR");
        Text t2 = new Text();
        vbox.getChildren().addAll(text1,text2,t2,b1,t,b2);
        vbox.setAlignment(Pos.CENTER);


        
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(30));
        Scene scene1 = new Scene(vbox,300,350);


        TextField tx1 = new TextField();
        TextField tx2 = new TextField();
        VBox vbox2 = new VBox();
        Button b3 = new Button("Sign Up");
        tx1.setPromptText("Enter Username");
        tx2.setPromptText("Enter Password");

        vbox2.getChildren().addAll(tx1,tx2,b3);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(20);
        vbox2.setPadding(new Insets(30));
        Scene scene2 = new Scene(vbox2,300,300);


        ListView<HBox> list = new ListView<HBox>();
        ObservableList<HBox> items =FXCollections.observableArrayList ();
        
           
        Statement stmt = db.get();
        String s4 = "select Username from tic_tac_toe;";
        ResultSet rst =  stmt.executeQuery(s4);
        rst.last();
        int size = rst.getRow();
        rst.first();


        HBox[] hbox = new HBox[size];
        HBox[] hbox2 = new HBox[size];
        Button[] butt = new Button[size];
        Text[] tex = new Text[size];

        for(int i=0;i<size;i++){
            hbox[i]= new HBox();
            hbox2[i]= new HBox();
            butt[i] = new Button("Play");
            tex[i]= new Text(rst.getString(1));
            hbox2[i].getChildren().add(tex[i]);
            hbox2[i].setPrefWidth(200);
            hbox[i].getChildren().addAll(hbox2[i],butt[i]);
            items.add(hbox[i]);
            rst.next();
        }
        list.setItems(items);
        Scene scene3 = new Scene(list,300,300);





        primaryStage.setScene(scene1);
        primaryStage.setTitle("TIC-TAC-TOE");
        primaryStage.show();

        b2.setOnAction(e -> primaryStage.setScene(scene2));

        b1.setOnAction(e -> {
           
                try {

                    String s1 = text1.getText();
                    String s2 = text2.getText();
                    String s3 = "select IP from tic_tac_toe where Username='"+s1+"' and Password='"+s2+"';";
                    ResultSet rs = stmt.executeQuery(s3);
                    System.out.println(s3);
                    try {
                        rs.next();
                    System.out.println(rs.getString(1));
                    primaryStage.setScene(scene3);
                    }
                    catch(Exception e2){
                        t2.setText("Login Failed");
                    }

                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            
        });

        b3.setOnAction(e -> {
            try {

            String s1 = tx1.getText();
            String s2 = tx2.getText();
            String s3 = InetAddress.getLocalHost().getHostAddress().toString();
            String query = "INSERT INTO tic_tac_toe (Username, Password, IP)VALUES ('"+s1+"','"+s2+"','"+s3+"');";
            stmt.executeUpdate(query);
        } catch (SQLException | UnknownHostException e1) {
            e1.printStackTrace();
        } 
    } 
        );  



    }
    public static void main(String[] args) {
        launch(args);
    }
}