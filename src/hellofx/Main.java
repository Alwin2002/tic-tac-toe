package hellofx;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class Main extends Application {

    private Scene scene1;
    Text text2=new Text();
    public int[] arr = new int[9];
    int x[]= new int[9];
    winner w = new winner();

    public static void main(String[] args)  {
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{

        ServerSocket ss = new ServerSocket(3000);
        Socket s = ss.accept();
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());

        String c="X";
        VBox vbox = new VBox();
        Text text1= new Text("Match 2");
        Text text3 = new Text();
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

        
        button[0].setOnAction(e -> {
            
            button[0].setText(c);
            text2.setText("O's turn");
            dis(button);
            arr[0]=1;
            x[0]=1;
            int[] b = w.check(x);
            if(b!=null){
                text3.setText("X wins");
                button[b[0]].setStyle("-fx-background-color: blue; -fx-text-fill: white;");
                button[b[1]].setStyle("-fx-background-color: blue; -fx-text-fill: white;");
                button[b[2]].setStyle("-fx-background-color: blue; -fx-text-fill: white;");
            }
            
            try {
                dout.writeInt(0);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });
        button[1].setOnAction(e -> {
            
            button[1].setText(c);
            text2.setText("O's turn");
            dis(button);
            arr[1]=1;
            x[1]=1;
            int[] b = w.check(x);
            if(b!=null){
                text3.setText("X wins");
                
            }
            
            try {
                dout.writeInt(1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });
        button[2].setOnAction(e -> {
            
            button[2].setText(c);
            text2.setText("O's turn");
            dis(button);
            arr[2]=1;
            x[2]=1;
            int[] b = w.check(x);
            if(b!=null){
                text3.setText("X wins");
            }
            try {
                dout.writeInt(2);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });
        button[3].setOnAction(e -> {
            
            button[3].setText(c);
            text2.setText("O's turn");
            dis(button);
            arr[3]=1;
            x[3]=1;
            int[] b = w.check(x);
            if(b!=null){
                text3.setText("X wins");
            }
            try {
                dout.writeInt(3);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });
        button[4].setOnAction(e -> {
            
            button[4].setText(c);
            text2.setText("O's turn");
            dis(button);
            arr[4]=1;
            x[4]=1;
            int[] b = w.check(x);
            if(b!=null){
                text3.setText("X wins");
            }
            
            try {
                dout.writeInt(4);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });button[5].setOnAction(e -> {
            
            button[5].setText(c);
            text2.setText("O's turn");
            dis(button);
            arr[5]=1;
            x[5]=1;
            int[] b = w.check(x);
            if(b!=null){
                text3.setText("X wins");
            }
            try {
                dout.writeInt(5);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });button[6].setOnAction(e -> {
            
            button[6].setText(c);
            text2.setText("O's turn");
            dis(button);
            arr[6]=1;
            x[6]=1;
            int[] b = w.check(x);
            if(b!=null){
                text3.setText("X wins");
            }
            try {
                dout.writeInt(6);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });button[7].setOnAction(e -> {
            
            button[7].setText(c);
            text2.setText("O's turn");
            dis(button);
            arr[7]=1;
            x[7]=1;
            int[] b = w.check(x);
            if(b!=null){
                text3.setText("X wins");
            }
            try {
                dout.writeInt(7);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });button[8].setOnAction(e -> {
            
            button[8].setText(c);
            text2.setText("O's turn");
            dis(button);
            arr[8]=1;
            x[0]=1;
            int[] b = w.check(x);
            if(b!=null){
                text3.setText("X wins");
            }
            try {
                dout.writeInt(8);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });




        text2.setText("X's turn");
       
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setGridLinesVisible(true);

        gridPane.setPrefSize(150, 150);

        vbox.getChildren().addAll(text1,text2,gridPane,text3); 

        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);

        scene1 = new Scene(vbox,300,350);






        VBox vBox2 = new VBox();
        Text text4= new Text("No of matches");
        
        String no[] = {"1","3","5","7"};

        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(no));
        
        Button button10 = new Button("Submit");
        button10.setOnAction(e -> {
            String a = (String) combo_box.getValue();
            try {
                dout.writeInt(89);  
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            primaryStage.setScene(scene1);
        });
        vBox2.getChildren().addAll(text4,combo_box,button10); 

        vBox2.setSpacing(15);
        vBox2.setAlignment(Pos.CENTER);
        
        Scene scene2= new Scene(vBox2,300,350);

        primaryStage.setScene(scene2);
        primaryStage.setTitle("TIC-TAC-TOE");
        primaryStage.show();

        new ServerListener(dis,dout,button).start();
    
    }
    public void dis(Button[] b){
        for(int i=0;i<9;i++){
            b[i].setDisable(true);
            b[i].setStyle("-fx-text-fill: black;");
        }
    }




    private class ServerListener extends Thread {

        DataInputStream dis;
        DataOutputStream dout;
        Button button[];

        ServerListener(DataInputStream dis,DataOutputStream dout,Button button[]){
            this.dis=dis;
            this.dout=dout;
            this.button=button;
        }

        @Override
        public void run() {
            try{


                int str1;
                while ((str1 =  dis.readInt()) != 90) {
                    final int str = str1;

                    Platform.runLater(() -> {
                        button[str].setText("O");
                        arr[str]=1;
                        for(int i=0;i<9;i++){
                            if(arr[i]==0){
                                button[i].setDisable(false);
                                button[i].setStyle("-fx-text-fill: black;");
                            }
                            else{
                                button[i].setDisable(true);
                                button[i].setStyle("-fx-text-fill: black;");
                            }
                        }
                        text2.setText("X's turn");
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