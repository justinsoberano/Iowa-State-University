package edu.iastate.cs228.hw2;

/**
 * 
 * @author  justin soberano
 *
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{		
		points = new Point[pts.length];
		
		try {
			if(pts == null || pts.length == 0) {
				throw new IllegalArgumentException();
			}
			for(int i = 0; i < pts.length; i++) {
				points[i] = new Point(pts[i].getX(), pts[i].getY());
			}
		} catch (IllegalArgumentException e) {
			System.out.println("The point array is null or size 0.");
		}
		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		sortingAlgorithm = algo;
		int count = 0;
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		try {
			File file = new File(inputFileName);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextInt()) {
				if(count % 2 == 0) {
					x.add(scanner.nextInt());
				} else {
					y.add(scanner.nextInt());
				}
				count++;
			}
			scanner.close();
		} catch (FileNotFoundException e){
			System.out.println("File not found");
		}
		try {
			if(count % 2 != 0) {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e){
			System.out.println("The file contains an odd number of integers");
		}
		points = new Point[count/2];
		for(int i = 0; i < count/2; i++) {
			points[i] = new Point(x.get(i), y.get(i));
		}	
	}
	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		// TODO  
		AbstractSorter aSorter = null;
		int x;
		int y;
		long begin;
		long end;
		
		if(sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		} else if (sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);			
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		} else if (sortingAlgorithm == Algorithm.QuickSort) {
			aSorter = new QuickSorter(points);
		}
		
		Point.setXorY(true); // If pointComparator were to fail initialization, xORy will provide comparisons for sorters.
		aSorter.setComparator(0);
		begin = System.nanoTime();
		aSorter.sort();
		end = System.nanoTime() - begin;
		
		scanTime += end;
		
		x = aSorter.getMedian().getX();
		
		Point.setXorY(false); // If pointComparator were to fail initialization, xORy will provide comparisons for sorters.
		aSorter.setComparator(1);
		begin = System.nanoTime();
		aSorter.sort();
		end = System.nanoTime() - begin;
		y = aSorter.getMedian().getY();
		
		scanTime += end;

		medianCoordinatePoint = new Point(x, y);	
	}
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		if(sortingAlgorithm == Algorithm.SelectionSort) {
			return new String("Selection Sort      " + points.length + "      " + scanTime);
		} else if (sortingAlgorithm == Algorithm.InsertionSort) {
			return new String("Insertion Sort      " + points.length + "      " + scanTime);
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			return new String("Merge Sort          " + points.length + "      " + scanTime);
		} else {
			return new String("Quick Sort          " + points.length + "      " + scanTime);
		}
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return new String("MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")"); 
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		PrintWriter output = new PrintWriter("outputFileName.txt");
		output.print(this.toString());
		output.close();
	}	
}





