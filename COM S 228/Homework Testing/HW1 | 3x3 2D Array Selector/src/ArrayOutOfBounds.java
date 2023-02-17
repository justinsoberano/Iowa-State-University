import java.util.ArrayList;
// This is for Homework 1 of COM S 228
// This is the code that I used to test the ArrayOutOfBounds exception
// This code prevents the exception from happening by checking the bounds of the
// array and only changing the values of the array if the bounds are not exceeded.

public class ArrayOutOfBounds {
    
    public static int[][] globalArray;
    public static void main(String[] args) throws Exception {
        
        int row = 0;
        int column = 1;

        System.out.println();
        System.out.println("Old Array:");
        int[][] array = { {1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9} };

        globalArray = array;

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println("New Array:");

        int sizeOfArray = array.length - 1;
        int arraySize = 0;
        ArrayList<Integer> neighbors = new ArrayList<Integer>();


        // for(int i = - 1; i < 2; i++) {
        //     for(int j = - 1; j < 2; j++) {
        //         if(!((row + j) > sizeOfArray) && !((row + j) < 0) 
        //         && !((column + i) > sizeOfArray) && !((column + i) < 0)) {
        //             arraySize++;
        //             neighbors.add(array[row + j][column + i]);
        //             array[row + j][column + i] = 8;
        //         }
        //     }
        // }

        for(int i = - 1; i < 2; i++) {								
            for(int j = - 1; j < 2; j++) {	
                if(!((row + j) > sizeOfArray) && !((row + j) < 0) 		
                && !((column + i) > sizeOfArray) && !((column + i) < 0)) {	
                    array[row + j][column + i] = 8;
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

        for(int i = 0; i < neighbors.size(); i++) {
            System.out.print(neighbors.get(i) + " ");
        }
        System.out.println();
        System.out.println(arraySize);
        System.out.println();

    }

    public String toString() {
        for(int i = 0; i < globalArray[0].length; i++) {
            for(int j = 0; j < globalArray.length; j++) {
                System.out.print(globalArray[i][j] + " ");
            }
            System.out.println();
        }
        return "da";
    }
}

// Notes on what to do for the assignment
// 1. Input the nested for loop inside of the living 
// 2. Create a new array that stores all of the animals that are neighboring it.
// 3. Call the census method and have it return the number of different living animals
// 4. Compare the numbers with the conditions of that animal.
// 5. If the conditions are met, then the animal lives, if not, then it dies.