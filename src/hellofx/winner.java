package hellofx;

public class winner {
    int[] check(int[] a){
       
        if(a[0]==1 && a[1]==1 && a[2]==1){
            int[] b = {0,1,2};
            return b;
        }
        else if(a[3]==1 && a[4]==1 && a[5]==1){
                        int[] b = {3,4,5};
            return b;
        }
        else if(a[6]==1 && a[7]==1 && a[8]==1){
                        int[] b = {6,7,8};
            return b;
        }
        else if(a[0]==1 && a[3]==1 && a[6]==1){
                        int[] b = {0,3,6};
            return b;
        }
        else if(a[1]==1 && a[4]==1 && a[7]==1){
                        int[] b = {1,4,7};
            return b;
        }
        else if(a[2]==1 && a[5]==1 && a[8]==1){
                        int[] b = {2,5,8};
            return b;
        }
        else if(a[0]==1 && a[4]==1 && a[8]==1){
                        int[] b = {0,4,8};
            return b;
        }
        else if(a[2]==1 && a[4]==1 && a[6]==1){
                        int[] b = {2,4,6};
            return b;
        }
        else{
            return null;
        }
    }
}
