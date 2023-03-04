package edu.iastate.cs228.hw2;

/**
 *  
 * @author justin soberano
 *
 */

import java.util.Comparator;
import java.lang.IllegalArgumentException; 

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{
	
	protected Point[] points;    
	
	protected String algorithm = null; 
		 
	protected Comparator<Point> pointComparator = null;  
	
	protected AbstractSorter()
	{

	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		try {
			if(pts == null || pts.length == 0) {
				throw new IllegalArgumentException();
			}
			
			getPoints(pts);
			
		} catch (IllegalArgumentException e) {
			System.out.println("The array is either null or has length of 0.");
			
		}
	}

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order == 0, by y-coordinate
	 * if order == 1. Assign the 
     * comparator to the variable pointComparator. 
     *  
	 * 
	 * @param order  0   by x-coordinate 
	 * 				 1   by y-coordinate
	 * 			    
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 *        
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
		// TODO
		
		try {
			if(order != 0 && order!= 1) {
				throw new IllegalArgumentException();
			}
			if(order == 0) {
				pointComparator = new Comparator<Point>() {
					@Override
					public int compare(Point one, Point two) {
						Point.setXorY(true);
						return one.compareTo(two); 
					}
				};
			} else if(order == 1) {
				pointComparator = new Comparator<Point>() {
					@Override
					public int compare(Point one, Point two) {
						Point.setXorY(false);
						return one.compareTo(two);
					}
				};
			}
		} catch(IllegalArgumentException e) {
			System.out.println("The value is not 1 or 0.");
		}
	}

	/**
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Should be protected. Made public for testing. 
	 */
	protected abstract void sort(); 
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Copys the array points[] onto the array pts[]. 
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		points = new Point[pts.length];
		for(int i = 0; i < pts.length; i++) {
			points[i] = new Point(pts[i].getX(), pts[i].getY());
		}
	}

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}	
}
