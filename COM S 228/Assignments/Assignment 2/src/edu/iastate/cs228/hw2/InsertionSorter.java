package edu.iastate.cs228.hw2;

/**
 *  
 * @author justin soberano
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "insertion sort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		int j;
		
        for(int i = 1; i < points.length; i++) {
            Point target = points[i];
            j = i - 1;
            if(pointComparator != null) {
                while(j > -1 && (pointComparator.compare(points[j], target) > 0)) {
                    points[j+1] = points[j];
                    j--;
                }
                
               /*
                * Fall back else statement if pointComparator is not initialized. Uses the value in [xORy].
                */
            } else {
            	while(j > -1 && points[j].compareTo(target) > 0) {
            		points[j+1] = points[j];
            		j--;
            	}
            }
            points[j+1] = target;
        }	
	}
}
