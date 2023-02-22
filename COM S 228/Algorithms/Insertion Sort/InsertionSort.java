
public static void InsertionSort(int[] A) {
        int n = A.length;
        for(int i = 1; i < n; i++) {
            int target = A[i];
            j = i - 1;
            while( j > -1 && target < A[j]) {
                A[j+1] = A[j];
                j--;
            }
        A[j+1] = target;
    }
}
