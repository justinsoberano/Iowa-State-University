
// This is for Homework 1 of COM S 228
// This is the code that I used to test the ArrayOutOfBounds exception
// This code prevents the exception from happening by checking the bounds of the
// array and only changing the values of the array if the bounds are not exceeded.

public class ArrayOutOfBounds {
    public static void main(String[] args) throws Exception {
        
        int r = 0;
        int c = 0;

        System.out.println();
        System.out.println("Old Array:");
        int[][] array = { {0, 0, 0, 0, 0, 0}, 
                          {0, 0, 0, 0, 0, 0}, 
                          {0, 0, 0, 0, 0, 0}, 
                          {0, 0, 0, 0, 0, 0}, 
                          {0, 0, 0, 0, 0, 0},
                          {0, 0, 0, 0, 0, 0} };

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println("New Array:");

        int sizeOfArray = array.length - 1;

        for(int i = - 1; i < 2; i++) {
            for(int j = - 1; j < 2; j++) {
                if(!((r + j) > sizeOfArray) && !((r + j) < 0) 
                && !((c + i) > sizeOfArray) && !((c + i) < 0)) {
                    array[r + j][c + i] = 1;
                }
            }
        }

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

    }
}

// Notes on what to do for the assignment
// 1. Input the nested for loop inside of the living 
// 2. Create a new array that stores all of the animals that are neighboring it.
// 3. Call the census method and have it return the number of different living animals
// 4. Compare the numbers with the conditions of that animal.
// 5. If the conditions are met, then the animal lives, if not, then it dies.