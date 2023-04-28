package hellofx;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class start extends Application{
    VBox vbox = new VBox();

    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException, IOException{

        Random rand = new Random();
        int rand_num = rand.nextInt(4000)+1000;
        ServerSocket ss = new ServerSocket(rand_num);


        //Creating Scene 1 which is login page
        database db = new database();
        TextField text1 = new TextField();
        text1.setPromptText("Enter Username");
        PasswordField text2 = new PasswordField();
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

        //Creating Scene 2 which is a signup page 
        TextField tx1 = new TextField();
        TextField tx2 = new TextField();
        VBox vbox2 = new VBox();
        Button b3 = new Button("Sign Up");
        Button b6 = new Button("Back");
        tx1.setPromptText("Enter Username");
        tx2.setPromptText("Enter Password");

        vbox2.getChildren().addAll(tx1,tx2,b3,b6);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(20);
        vbox2.setPadding(new Insets(30));
        Scene scene2 = new Scene(vbox2,300,300);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));

        
        
        //Creating Scene 3 which is a list of users in game
        
        
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
        String[] str= new String[size];

        for(int i=0;i<size;i++){
            hbox[i]= new HBox();
            hbox2[i]= new HBox();
            butt[i] = new Button("Play");
            str[i]= new String();
            str[i]= rst.getString(1);
            tex[i]= new Text(rst.getString(1));
            hbox2[i].getChildren().add(tex[i]);
            hbox2[i].setPrefWidth(340);
            hbox[i].getChildren().addAll(hbox2[i],butt[i]);
            items.add(hbox[i]);
            rst.next();
        }
        list.setItems(items);
        list.setStyle("-fx-border-color: white;");
        Button b5 = new Button("Accept Invitation");

        VBox vbox4 = new VBox();
        vbox4.getChildren().addAll(b5,list);
        vbox4.setAlignment(Pos.CENTER);
        VBox.setMargin(b5, new Insets(10));
        Scene scene3 = new Scene(vbox4,400,400);


        //Creating Scene 4 
        Text t3 = new Text("Waiting for other player to accept...");
        StackPane stack = new StackPane(t3);
        Scene scene4 = new Scene(stack,300,300);


        //Creating Scene 5 
        Text t4 = new Text("Waiting for other to send");
        StackPane stack2 = new StackPane(t4);
        Scene scene5 = new Scene(stack2,300,300);


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
                        String s5="update tic_tac_toe set port="+rand_num+" where Username='"+s1+"';";
                        System.out.print(s5);
                        stmt.executeUpdate(s5);
                    primaryStage.setScene(scene3);
                    }
                    catch(Exception e2){
                        t2.setText("Login Failed");
                        System.out.print(e2);
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
            primaryStage.setScene(scene1);
        } catch (SQLException | UnknownHostException e1) {
            e1.printStackTrace();
        } 
    } 
        );  

        b6.setOnAction(e-> primaryStage.setScene(scene1));


        b5.setOnAction(e -> {
            primaryStage.setScene(scene5);
                delay.play();
                delay.setOnFinished(event -> { 
                    try (Socket socket2 = ss.accept()) {
                        Main main = new Main();
                        primaryStage.setScene(main.get(socket2));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } 
                });
           
        });

        
        for(int i=0;i<size;i++){
            final int ii = i;
            butt[i].setOnAction(e-> {
                primaryStage.setScene(scene4);
                delay.play();
                delay.setOnFinished(event -> { 
                    String queryString = "select port from tic_tac_toe where Username ='"+str[ii]+"'";
                    System.out.println(queryString);
                    try (ResultSet rst2 = stmt.executeQuery(queryString)) {
                        rst2.next();
                        
                        Socket socket1 = new Socket("127.0.0.1",rst2.getInt(1));
                        client cli = new client();
                        primaryStage.setScene(cli.get(socket1)); 
                    } catch (SQLException | IOException e1) {
                        e1.printStackTrace();
                    }
                });
               
                });
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
    
}