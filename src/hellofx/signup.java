package hellofx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Statement;

public class signup {
    TextField t1 = new TextField();
    TextField t2 = new TextField();
    TextField t3 = new TextField();
    VBox vbox = new VBox();
    Button b1 = new Button("Sign Up");
    database db = new database();

    public Scene get() throws ClassNotFoundException, SQLException, UnknownHostException{
        t1.setPromptText("Enter Username");
        t2.setPromptText("Enter Password");
        t3.setPromptText("Confirm Password");
        vbox.getChildren().addAll(t1,t2,t3,b1);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(30));
        Scene s = new Scene(vbox,300,300);
        
        Statement stmt = db.get();


        b1.setOnAction(e -> {try {
            String s1 = t1.getText();
            String s2 = t2.getText();
            String s3 = InetAddress.getLocalHost().getHostAddress().toString();
            String query = "INSERT INTO tic_tac_toe (Username, Password, IP)VALUES ('"+s1+"','"+s2+"','"+s3+"');";
            System.out.print(query);
            stmt.executeUpdate(query);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }});
        return s;
    }
}