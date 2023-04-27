package hellofx;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;

public class client extends Application{
    private Scene scene1;
    Text text2=new Text();
    public int arr[] = new int[9];
    int o[]= new int[9];
    winner w = new winner();
    int x[]= new int[9];
    Text text3 = new Text();
    Text text1= new Text("Match 1");
    int match=1;
    PauseTransition delay = new PauseTransition(Duration.seconds(3));



    public static void main(String[] args)  {
        
        launch(args);
    }

   @Override
    public void start(Stage primaryStage)  throws IOException {


        Socket s = new Socket(InetAddress.getLocalHost(), 3000);
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        
        int a = dis.readInt();

        String c = "O";
        VBox vbox = new VBox();
       
        Button button[]= new Button[9];
        
        
        GridPane gridPane = new GridPane(); 
        int count=0;
        
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                button[count]=new Button();
                button[count].setPrefSize(50, 50);
                button[count].setStyle("-fx-font-size: 22px;");
                gridPane.add(button[count],i,j);
                count++;
            }
        }

        text2.setText("X's turn");
       
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setGridLinesVisible(true);

        gridPane.setPrefSize(150, 150);

        vbox.getChildren().addAll(text1,text2,gridPane,text3); 

        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);

        scene1 = new Scene(vbox,300,350);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("TIC-TAC-TOE");
        primaryStage.show();

        button[0].setOnAction(e -> extracted(0,dout, c, button));
        button[1].setOnAction(e -> extracted(1,dout, c, button));
        button[2].setOnAction(e -> extracted(2,dout, c, button));
        button[3].setOnAction(e -> extracted(3,dout, c, button));
        button[4].setOnAction(e -> extracted(4,dout, c, button));
        button[5].setOnAction(e -> extracted(5,dout, c, button));
        button[6].setOnAction(e -> extracted(6,dout, c, button));
        button[7].setOnAction(e -> extracted(7,dout, c, button));
        button[8].setOnAction(e -> extracted(8,dout, c, button));

        new ServerListener(dis,dout,button).start();

    }

public void extracted(int count,DataOutputStream dout, String c, Button[] button) {
    button[count].setText(c);
    button[count].setStyle("-fx-font-size: 22px;");
    text2.setText("X's turn");
    dis(button);
    arr[count]=1;
    o[count]=1;
    try {
        dout.writeInt(count);
    } catch (IOException e1) {
        
    }
    int[] b = w.check(o);
    if (b != null) {
        text3.setText("O wins");
        text2.setText("");
        dis(button);
        button[b[0]].setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 22px;");
        button[b[1]].setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 22px;");
        button[b[2]].setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 22px;");
                    delay.play();
            delay.setOnFinished(event -> { 
                enl(button);
                match++;
                text1.setText("Match " + match);
                text3.setText("");
                text2.setText("X's turn");
            });
    }
}

    public void dis(Button[] b){
        for(int i=0;i<9;i++){
            b[i].setDisable(true);
        }
    }
    public void enl(Button[] b) {
        for (int i = 0; i < 9; i++) {
            b[i].setDisable(false);
            b[i].setText("");
            b[i].setStyle("");
            arr[i]=0;
            x[i]=0;
            o[i]=0;
        }
    }

    private class ServerListener extends Thread {

        DataInputStream dis;
        DataOutputStream dout;
        Button button[];


        ServerListener(DataInputStream dis,DataOutputStream dout,Button button[]){
            this.dis=dis;
            this.dout=dout;
            this.button = button;
        }

        @Override
        public void run() {
            try{
                int str1;
                while ((str1 =  dis.readInt()) != 90) {
                    final int str = str1;
                    
                    Platform.runLater(() -> {
                        text2.setText("O's turn");
                        button[str].setStyle("-fx-font-size: 22px;");
                        button[str].setText("X");
                        arr[str]=1;
                        x[str]=1;
                        int b[] = w.check(x);
                        if(b!=null){
                                text3.setText("X wins");
                                text2.setText("");
                                dis(button); 
                                button[b[0]].setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 22px;");
                                button[b[1]].setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 22px;");
                                button[b[2]].setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 22px;"); 
                                delay.play();
                                delay.setOnFinished(event -> { 
                                    enl(button);
                                    match++;
                                    text1.setText("Match " + match);
                                    text3.setText("");
                                    text2.setText("X's turn");
                                });
                            }
                        for(int i=0;i<9;i++){
                            if(arr[i]==0){
                                button[i].setDisable(false);
                            }
                            else{
                                button[i].setDisable(true);
                            }
                        }
                    });
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}