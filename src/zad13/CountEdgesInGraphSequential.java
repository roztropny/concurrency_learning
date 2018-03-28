package zad13;

public class CountEdgesInGraphSequential {

    int countEdges(int n, boolean[][] a) {
        int i,j,k=0;
        for (i=0;i<n-1; i++)
            for (j=i+1;j<n;j++)
                if (a[i][j]) k++;
        return k;
    }

    public static void main(String[] args){

    }
}
