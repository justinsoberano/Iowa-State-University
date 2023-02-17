package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Justin Soberano
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{		
		for(int i = 0; i < pOld.getWidth(); i++) {
			for(int j = 0; j < pOld.getWidth(); j++) {
				pOld.grid[i][j].next(pNew);
			}
		}
	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {	
		
		Plain even;   				
		Plain odd;                  
		System.out.println("Simulation of Wildlife of the Plain");
		System.out.println("Mode: 1 (Random Plain) 2 (File Input) Any # (Exit)");
		
		int trials = 1;
		int input = 1;
		
		while(input == 1 || input == 2) {
			
			System.out.print("Trial " + trials + ": ");
			Scanner scnr = new Scanner(System.in);
			input = scnr.nextInt();
			
			if(input == 1) {
				
				System.out.println("Random Plain");
				System.out.print("Enter the grid width: ");
				scnr = new Scanner(System.in);
				int width = scnr.nextInt();

				System.out.print("Enter the number of cycles: ");
				int cycles = 0;
				
				while(!(cycles > 0)) {
					cycles = 0;
					scnr = new Scanner(System.in);
					cycles = scnr.nextInt();
				}
				
				even = new Plain(width);
				odd = new Plain(width);
				
				even.randomInit();
				odd.randomInit();
				
				System.out.println();
				System.out.println("Initial Plain:");
				System.out.println(odd.toString());
				
				for(int i = 0; i < cycles; i++) {
					if(i % 2 == 0) {
						updatePlain(odd, even);
					} else {
						updatePlain(even, odd);
					}
				}
				
				if(cycles % 2 == 0) {
					System.out.println("Final Plain:");
					System.out.println(odd.toString());
				} else {
					System.out.println("Final Plain:");
					System.out.println(even.toString());
				}
				
			} else if (input == 2) {
				
				System.out.println("File Input");
				System.out.print("File Name: ");
				scnr = new Scanner(System.in);
				String file = scnr.next();
				
				System.out.print("Enter the number of cycles: ");
				int cycles = 0;
				
				while(!(cycles > 0)) {
					cycles = 0;
					scnr = new Scanner(System.in);
					cycles = scnr.nextInt();
				}
				
				even = new Plain(file);
				odd = new Plain(file);
				
				System.out.println();
				System.out.println("Initial Plain:");
				System.out.println(odd.toString());
				
				for(int i = 0; i < cycles; i++) {
					if(i % 2 == 0) {
						updatePlain(odd, even);
					} else {
						updatePlain(even, odd);
					}
				}
				
				if(cycles % 2 == 0) {
					System.out.println("Final Plain:");
					System.out.println(odd.toString());
				} else {
					System.out.println("Final Plain:");
					System.out.println(even.toString());
				}
			}
			trials++;
		}
		System.out.println("Simulation Ended");
	}
}
