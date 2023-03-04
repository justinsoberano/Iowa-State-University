package edu.iastate.cs228.hw2;

/**
 *  
 * @author justin soberaon
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{	
		
		int trial = 1;
		int input;
		Point[] points;
		PointScanner[] scanners = new PointScanner[4]; 
		
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println();
		System.out.println("Keys: 1 - Random Integers || 2 - File Input || 3 - Exit");
		
		Scanner scnr = new Scanner(System.in);
		System.out.print("Trial " + trial + ": ");
		input = scnr.nextInt();
		String fileName = null;
		
		while (input == 1 || input == 2) {
			
			if(input == 1) {
				int numPts;
				System.out.print("Enter the number of random points: ");
				numPts = scnr.nextInt();
				System.out.println();
				System.out.println("Algorithm           Size     Time(ns)");
				System.out.println("---------------------------------------");
				
				points = generateRandomPoints(numPts, new Random());
				
				scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(points, Algorithm.MergeSort);
				scanners[3] = new PointScanner(points, Algorithm.QuickSort);
				
				for(int i = 0; i < 4; i++) {
					scanners[i].scan();
					System.out.println(scanners[i].stats());
				}
				
				System.out.println("---------------------------------------");
				System.out.println();
				trial++;
				System.out.print("Trail " + trial + ": ");
				input = scnr.nextInt();
				
			} else if (input == 2) {
				
				System.out.println("Points from a file");
				System.out.print("File Name: ");
				fileName = scnr.next();
				
				System.out.println();
				System.out.println("Algorithm           Size     Time(ns)");
				System.out.println("---------------------------------------");
				
				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
				
				for(int i = 0; i < 4; i++) {
					scanners[i].scan();
					System.out.println(scanners[i].stats());
				}
				
				System.out.println("---------------------------------------");
				System.out.println();
				trial++;
				System.out.print("Trail " + trial + ": ");
				input = scnr.nextInt();
			}
		}
		scnr.close();
		System.out.println();
		System.out.println("Terminated");
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 	
		Random random = new Random(rand.nextInt());
		Point[] points = new Point[numPts];
		int x, y;
		for(int i = 0; i < numPts; i++) {
			x = random.nextInt(101) - 50;
			y = random.nextInt(101) - 50;
			points[i] = new Point(x, y);
		}
		return points;
	}
}
