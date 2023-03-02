import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class scannerTest {
    public static void main(String[] args) {
        
        int count = //0;
        File file = new File("Iowa State University/COM S 228/Homework Testing/HW2/test.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextInt()) {
            count++;
        }
        System.out.println("The number of integers in the file is: " + count);
    }
}

