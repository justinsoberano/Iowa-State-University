
public class MergeSort {

    Merge(B, C) { 
        p = B.length;
        q = C.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < p && j < q) {
            if(B[i] <= C[j]) {
                D[k] = B[i];
                i++;
                j++;
            } else {
                D[k] = C[j];
                j++;
                k++;
            }
        if(i >= p ) {
            while(j < q) {
                D[k] = C[j];
                j++;
            }
        else {
            while(j < p) {
                D[k] = B[i];
                i++
            }
    }
}
        }
    }
