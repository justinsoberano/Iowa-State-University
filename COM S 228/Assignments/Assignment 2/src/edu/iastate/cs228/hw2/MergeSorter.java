package edu.iastate.cs228.hw2;

/**
 *  
 * @author justin soberano
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts) // Initializes the MergeSort method.
	{
		int k = points.length;
		MergeSort(0, k - 1);
	}
	
	/**
	 * Merge Sort method from ZyBooks
	 * @param i
	 * @param j
	 * @param k
	 */
	private void Merge(int i, int j, int k) {
		int mergedSize = k - i + 1;
		int mergePos = 0;
		int leftPos = 0;
		int rightPos = 0;
		Point[] mergedNumbers = new Point[mergedSize];
		
		leftPos = i;
		rightPos = j + 1;
		
		while(leftPos <= j && rightPos <= k) {
			if(pointComparator != null) {
				if(pointComparator.compare(points[leftPos], points[rightPos]) < 0) {
					mergedNumbers[mergePos] = points[leftPos];
					leftPos++;
				} else {
					mergedNumbers[mergePos] = points[rightPos];
					rightPos++;
				}
				
				/*
				 * Fall back else statement if pointComparator is not initialized. Uses xORy value.
				 */
			} else {
				if(points[leftPos].compareTo(points[rightPos]) < 0) {
					mergedNumbers[mergePos] = points[leftPos];
					leftPos++;
				} else {
					mergedNumbers[mergePos] = points[rightPos];
					rightPos++;
				}
			}
			mergePos++;
		}
		while(leftPos <= j) {
			mergedNumbers[mergePos] = points[leftPos];
			leftPos++;
			mergePos++;
		}
		while(rightPos <= k) {
			mergedNumbers[mergePos] = points[rightPos];
			rightPos++;
			mergePos++;
		}
		
		for(mergePos = 0; mergePos < mergedSize; mergePos++) {
			points[i + mergePos] = mergedNumbers[mergePos];
		}
	}
	
	/**
	 * Merge sort method from ZyBooks
	 * @param i
	 * @param k
	 */
	private void MergeSort(int i, int k) {
		int j = 0;
		if(i < k) {
			j = (i + k) / 2;
			MergeSort(i, j);
			MergeSort(j + 1, k);
			
			Merge(i, j, k);
		}
	}
}
