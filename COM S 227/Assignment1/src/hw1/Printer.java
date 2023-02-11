package hw1;
/**
 * Simulate a printer that allows the user to add paper, remove paper, and print.
 * @author justin
 *
 */
public class Printer {
	
	/**
	 * The amount of paper the printer can hold.
	 */
	private int trayCapacity;
	/**
	 * The amount of paper available to the printer.
	 */
	private int sheetsAvailable;
	/**
	 * The following page number.
	 */
	private int nextPage;
	/**
	 * The total amount of pages that the printer has printed.
	 */
	private int totalPages;
	/**
	 * The amount of pages that it will take to fully print a document.
	 */
	private int documentPages;
	/**
	 * The amount of pages that were removed from the printer and are in the tray.
	 */
	private int traySheets;
	
	/**
	 * Constructs a new printer with the given maximum tray 
	 * capacity of the number of paper sheets it can hold
	 * @param trayCapacity The maximum number of paper sheets that the printer can hold.
	 */
	public Printer(int trayCapacity) {
		this.trayCapacity = trayCapacity;
	}
	
	/**
	 * Starts a new print job to make copies of a document that is a specified page length
	 * @param documentPages The page length of the specified document.
	 */
	public void startPrintJob (int documentPages) {
		this.documentPages = documentPages;
		nextPage = 0;
	}
	
	/**
	 * Returns the number of sheets available for printing.
	 * @return Sheets available for printing.
	 */
	public int getSheetsAvailable() {
		return sheetsAvailable;
	}
	
	/**
	 * Returns the next page number of the document that will be printed.
	 * @return Next page number.
	 */
	public int getNextPage() {
		return nextPage;
	}
	
	/**
	 * Returns the count of all pages printed by the printer since its construction.
	 * @return Total pages printed.
	 */
	public int getTotalPages() {
		return totalPages;
	}
	
	/**
	 * Simulates the printer printing a page. The number pages printed is either
	 * one or zero depending on whether there is at least one sheet of paper available
	 * to the printer. Increments the total page count of the printer by the number 
	 * of pages printed. Advanced the next page to print by the number of pages printed
	 * The number of pages available to the printer and in the tray are also updated accordingly.
	 */
	public void printPage() {
		totalPages += (Math.min(1, sheetsAvailable)); 	
		nextPage += (Math.min(1, sheetsAvailable)); 
		nextPage %= documentPages; 
		sheetsAvailable--;
		sheetsAvailable = (Math.max(0,sheetsAvailable));
	}
	
	/**
	 * Removes the paper tray from the printer; that is, makes the sheets
	 * available to the printer 0.
	 */
	public void removeTray() {
		traySheets = Math.max(traySheets, sheetsAvailable);
		sheetsAvailable = 0;
	}
	
	/**
	 * Replaces the tray in the printer; that is, makes the sheets
	 * available to the printer the same as the number of sheets 
	 * in the tray.
	 */
	public void replaceTray() {
		sheetsAvailable = traySheets;
	}
	
	/**
	 * Simulates removing the tray, adding the given number of sheets (up to
	 * the maximum capacity of the tray), and replacing the tray in the printer
	 * @param sheets The amount of pages the user wants to add to the printer.
	 */
	public void addPaper(int sheets) {
		removeTray();
		traySheets += sheets;
		traySheets = Math.min(traySheets, trayCapacity);
		replaceTray();
	}
	
	/**
	 * Simulates removing the tray, removing the given number of sheets (but not
	 * allowing the sheets to go below zero), and replacing the tray in the printer.
	 * @param sheets The amount of pages the user wants to remove from the printer.
	 */
	public void removePaper(int sheets) {
		removeTray();
		traySheets -= sheets;
		traySheets = Math.max(0, traySheets);
		replaceTray();
	}

}
