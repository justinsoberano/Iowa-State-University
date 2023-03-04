package edu.iastate.cs228.hw2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class test {
	
	public static Point[] points;
	
	public static void main(String args[]) {
		
//		Random rand = new Random();
//		Random random = new Random(rand.nextInt());
//		int num = 32;
//		Point[] pts = new Point[num];
//		int x = 0;
//		int y = 0;
//		
//		for(int i = 0; i < num; i++) {
//			x = random.nextInt(101) - 50;
//			y = random.nextInt(101) - 50;
//			pts[i] = new Point(x, y);
//			System.out.println(pts[i].toString());
//		}
		
		Boolean sorted;
		
		Random rand = new Random();
		Point[] points = new Point[20];
		sorted = true;
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		Point.xORy = true;
		SelectionSorter selectionSorter = new SelectionSorter(points);
		selectionSorter.sort();
		for (int i = 1; i < points.length; i++) {
			if (selectionSorter.points[i - 1].compareTo(selectionSorter.points[i]) == 1) {
				sorted = false;
			}
		}
		assertEquals(true, sorted);
		
//		
//		int count = 0;
//		
//		ArrayList<Integer> x = new ArrayList<Integer>();
//		ArrayList<Integer> y = new ArrayList<Integer>();
//		
//		try {
//			
//			File file = new File("points.txt");
//			Scanner scanner = new Scanner(file);
//			
//			while(scanner.hasNextInt()) {
//				if(count % 2 == 0) {
//					x.add(scanner.nextInt());
//				} else {
//					y.add(scanner.nextInt());
//				}
//				count++;
//			}
//		
//			scanner.close();
//			
//		} catch (FileNotFoundException e){
//			System.out.println("File not found");
//		}
//		
//		try {
//			if(count % 2 != 0) {
//				throw new InputMismatchException();
//			}
//		} catch (InputMismatchException e){
//			System.out.println("The file contains an odd number of integers");
//		}
//		
//		points = new Point[count/2];
//		
//		for(int i = 0; i < count/2; i++) {
//			points[i] = new Point(x.get(i), y.get(i));
//			System.out.println(points[i].toString());
//		}
//		
//		System.out.println();
//		
//		Point.setXorY(false);
		
		
		// Selection Sort
//	    for(int i = 0; i < points.length; i++) {
//	        int minIndex = i;
//	        for(int j = i + 1; j < points.length; j++) {
//	            if(points[j].compareTo(points[minIndex]) < 0) {
//	                minIndex = j;
//	            }
//	        }
//			Point temp = points[i];
//			points[i] = points[minIndex];
//			points[minIndex] = temp;
//	    }
		
		// Insertion Sort
		
//		long begin;
//		long end;
//		long time = 0;
//		
//		begin = System.nanoTime();
//		
//		int j;
//		
//        for(int i = 1; i < points.length; i++) {
//            Point target = points[i];
//            j = i - 1;
//            while(j > -1 && target.compareTo(points[j]) < 0) {
//                points[j+1] = points[j];
//                j--;
//            }
//            points[j+1] = target;
//        }
//        
//        end = System.nanoTime() - begin;
//        
//        time += end;
//        
//        System.out.println(time);
		
		//Quick Sort	
//        quickSortRec(0, points.length - 1);
		
		// Merge Sort
//		mergeSortRec(points);
		
		
//		for(int i = 0; i < count/2; i++) {
//			System.out.println(points[i].toString());
//		}
        
		
//		Point[] pts = new Point[3];
//		pts[0] = new Point(1, 2);
//		pts[1] = new Point(4, 2);
//		pts[2] = new Point(4, 1);
//		
//		Point[] points = new Point[pts.length];
//		
//		try {
//			if(pts == null || pts.length == 0) {
//				throw new IllegalArgumentException();
//			}
//			for(int i = 0; i < pts.length; i++) {
//				points[i] = new Point(pts[i].getX(), pts[i].getY());
//			}
//		} catch (IllegalArgumentException e) {
//			System.out.println("The point array is null or size 0.");
//		}
		
		
		
//	}
//	
//	public static void swap(int i, int j) {
//		Point temp = points[i];
//		points[i] = points[j];
//		points[j] = temp;
//	}
//	
	
	
//	public static void quickSortRec(int first, int last) {
//		
////		if(first >= last) {
////			return;
////		}
////		int p = partition(first, last);
////		quickSortRec(first, p - 1);
////		quickSortRec(p + 1, last);
////		
//		if (first >= last) { return; }
//		int p = partition(first, last);
//		quickSortRec(first, p - 1);
//		quickSortRec(p + 1, last);
//		
//	}
//	
//	public static int partition(int first, int last) {
//		
////		Point pivot = points[last];
////		int i = first - 1;
////		
////		for(int j = first; j < last; j++) {
////			if(points[j].compareTo(pivot) < 0) {
////				i++;
////				swap(i, j);
////			}
////		}
////		swap(i + 1, last);
////		return i + 1;
//		
//		Point pivot = points[last];
//		int i = first - 1;
//		for (int j = first; j < last; j++) {
//			if (points[j].compareTo(pivot) < 0) {
//				i++;
//				swap(i, j);
//			}
//		}
//		swap(i + 1, last);
//		return i + 1;
//		
//	}
	
	
	
//	private static void mergeSortRec(Point[] pts)
//	{
//		int k = points.length;
//		MergeSort(0, k - 1);
//	}
//
//	private static void Merge(int i, int j, int k) {
//		int mergedSize = k - i + 1;
//		int mergePos = 0;
//		int leftPos = 0;
//		int rightPos = 0;
//		Point[] mergedNumbers = new Point[mergedSize];
//		
//		leftPos = i;
//		rightPos = j + 1;
//		
//		while(leftPos <= j && rightPos <= k) {
//			if(points[leftPos].compareTo(points[rightPos]) < 0) {
//				mergedNumbers[mergePos] = points[leftPos];
//				leftPos++;
//			} else {
//				mergedNumbers[mergePos] = points[rightPos];
//				rightPos++;
//			}
//			mergePos++;
//		}
//		while(leftPos <= j) {
//			mergedNumbers[mergePos] = points[leftPos];
//			leftPos++;
//			mergePos++;
//		}
//		while(rightPos <= k) {
//			mergedNumbers[mergePos] = points[rightPos];
//			rightPos++;
//			mergePos++;
//		}
//		
//		for(mergePos = 0; mergePos < mergedSize; mergePos++) {
//			points[i + mergePos] = mergedNumbers[mergePos];
//		}
//	}
//	
//	private static void MergeSort(int i, int k) {
//		int j = 0;
//		if(i < k) {
//			j = (i + k) / 2;
//			MergeSort(i, j);
//			MergeSort(j + 1, k);
//			
//			Merge(i, j, k);
//		}
	}
}
