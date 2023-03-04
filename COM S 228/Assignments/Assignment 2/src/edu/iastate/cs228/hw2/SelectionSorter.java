package edu.iastate.cs228.hw2;

/**
 *  
 * @author Justin Soberano
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "selection sort";
		
	}	

	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort() {
		for (int i = 0; i < points.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < points.length; j++) {
				/*
				 * Fall back if statement if pointComparator is not initialized. Uses xORy values.
				 */
				if (pointComparator == null) {
					if (points[j].compareTo(points[minIndex]) < 0) {
						minIndex = j;
					}
				} else {
					if (pointComparator.compare(points[j], points[minIndex]) < 0) {
						minIndex = j;
					}
				}
			}
			swap(i, minIndex);
		}
	}
}
