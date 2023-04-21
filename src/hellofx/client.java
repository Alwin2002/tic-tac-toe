package hellofx;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class client extends Application{
    private Scene scene1;
    Text text2=new Text();
    public int arr[] = new int[9];
    int o[]= new int[9];
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
        Text text1= new Text("Match 2");
        Button button[]= new Button[9];
        
        
        GridPane gridPane = new GridPane(); 
        int count=0;
        
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                button[count]=new Button();
                button[count].setPrefSize(50, 50);
                gridPane.add(button[count],i,j);
                count++;
            }
        }

        text2.setText("X's turn");
       
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setGridLinesVisible(true);

        gridPane.setPrefSize(150, 150);

        vbox.getChildren().addAll(text1,text2,gridPane); 

        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);

        scene1 = new Scene(vbox,300,350);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("TIC-TAC-TOE");
        primaryStage.show();

        button[0].setOnAction(e -> {
            
            button[0].setText(c);
            text2.setText("X's turn");
            dis(button);
            arr[0]=1;
            o[0]=1;
            try {
                dout.writeInt(0);
            } catch (IOException e1) {
                
            }
        
        });
        button[1].setOnAction(e -> {
            
            button[1].setText(c);
            text2.setText("X's turn");
            dis(button);
            arr[1]=1;
            o[1]=1;
            try {
                dout.writeInt(1);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        
        });
        button[2].setOnAction(e -> {
            
            button[2].setText(c);
            text2.setText("X's turn");
            arr[2]=1;
            dis(button);
            o[2]=1;
            try {
                dout.writeInt(2);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });
        button[3].setOnAction(e -> {
            
            button[3].setText(c);
            text2.setText("X's turn");
            arr[3]=1;
            dis(button);
            o[3]=1;
            try {
                dout.writeInt(3);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });
        button[4].setOnAction(e -> {
            
            button[4].setText(c);
            text2.setText("X's turn");
            arr[4]=1;
            dis(button);
            o[4]=1;
            try {
                dout.writeInt(4);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });button[5].setOnAction(e -> {
            
            button[5].setText(c);
            text2.setText("X's turn");
            arr[5]=1;
            dis(button);
            o[5]=1;
            try {
                dout.writeInt(5);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });button[6].setOnAction(e -> {
            
            button[6].setText(c);
            text2.setText("X's turn");
            arr[6]=1;
            o[6]=1;
            dis(button);
            try {
                dout.writeInt(6);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });button[7].setOnAction(e -> {
            
            button[7].setText(c);
            text2.setText("X's turn");
            arr[7]=1;
            dis(button);
            o[7]=1;
            try {
                dout.writeInt(7);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });button[8].setOnAction(e -> {
            
            button[8].setText(c);
            text2.setText("X's turn");
            arr[8]=1;
            dis(button);
            o[8]=1;
            try {
                dout.writeInt(8);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
        });

        new ServerListener(dis,dout,button).start();

    }

    public void dis(Button[] b){
        for(int i=0;i<9;i++){
            b[i].setDisable(true);
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
                        button[str].setText("X");
                        arr[str]=1;
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
