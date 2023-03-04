package edu.iastate.cs228.hw2;


/**
 *  
 * @author Justin Soberano
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		algorithm = "quicksort";
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		quickSortRec(0, points.length - 1);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if (first >= last) { return; }
		int p = partition(first, last);
		quickSortRec(first, p - 1);
		quickSortRec(p + 1, last);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		Point pivot = points[last];
		int i = first - 1;
		for (int j = first; j < last; j++) {
			if(pointComparator != null) {
				if (pointComparator.compare(points[j], pivot) < 0) {
					i++;
					swap(i, j);
				}
				
				 /*
	              * Fall back else statement if pointComparator is not initialized. Uses the value in [xORy].
	              */
			} else { 
				if(points[j].compareTo(pivot) < 0) { 
					i++;
					swap(i, j);
				}
			}
		}
		swap(i + 1, last);
		return i + 1;
	}	
}
