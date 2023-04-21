package hellofx;

import javafx.scene.shape.Line;

public class winner {
    int[] check(int[] a){
       
        if(a[0]==1 && a[1]==1 && a[2]==1){
            int[] b = {0,1,2};
            return b;
        }
        else if(a[3]==1 && a[4]==1 && a[5]==1){
                        int[] b = {0,1,2};
            return b;
        }
        else if(a[6]==1 && a[7]==1 && a[8]==1){
                        int[] b = {0,1,2};
            return b;
        }
        else if(a[0]==1 && a[3]==1 && a[6]==1){
                        int[] b = {0,1,2};
            return b;
        }
        else if(a[1]==1 && a[4]==1 && a[7]==1){
                        int[] b = {0,1,2};
            return b;
        }
        else if(a[2]==1 && a[5]==1 && a[8]==1){
                        int[] b = {0,1,2};
            return b;
        }
        else if(a[0]==1 && a[4]==1 && a[8]==1){
                        int[] b = {0,1,2};
            return b;
        }
        else if(a[2]==1 && a[4]==1 && a[6]==1){
                        int[] b = {0,1,2};
            return b;
        }
        else{
            return null;
        }
    }
}
