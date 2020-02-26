//Rachel Friedman | Data Structures 3130 | Assignment 2 | February 19, 2020

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	final double[] prices = { 2, 7, 8.5 };
	double[] amounts = { 0, 0, 0 };
	ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
	HashMap<String,Warehouse> map = new HashMap<>();

	public static void main(final String[] args) {
		final Main m = new Main();
		m.setupWarehouses();
		m.readFile("asst2_data.csv");
	}

	////////////////////////////// *READFILE*///////////////////////////////
	public void readFile(final String filename) {
		final NumberFormat nf = NumberFormat.getCurrencyInstance();

		int count = 0;
		try {
			final Scanner data = new Scanner(new File(filename));
			while (data.hasNextLine()) {
				count++;
				System.out.print(count + " | CARD ENTRY:");
				final String[] line = data.nextLine().split(",");
				final String city = line[1];
				// Warehouse w = warehouses.get(getWarehouse(city));
				final Warehouse w = map.get(city);
				if (line[0].equals("s")) {
					System.out.print(" SHIPMENT\n");
					System.out.print(
							"CARD ENTRY " + line[1] + ": Items=[" + line[2] + ", " + line[3] + ", " + line[4] + "]");
					System.out.print("\nCURRENT " + w);
					for (int i = 0; i < 3; i++) {
						final int amount = Integer.parseInt(line[i + 2]);
						w.setItem(i, w.getItem(i) + amount);
					}
					System.out.println("\nUPDATED " + w + "\n");
				} else if (line[0].contentEquals("o")) {
					System.out.print(" ORDER\n");
					System.out.print(
							"CARD ENTRY " + line[1] + ": Items=[" + line[2] + ", " + line[3] + ", " + line[4] + "]");
					int itemsShort;
					double surcharge = 0;
					double total = 0;
					System.out.println("\nCURRENT " + w);
					for (int i = 0; i < 3; i++) {
						itemsShort = w.getItem(i) - Integer.parseInt(line[i + 2]);
						if (itemsShort < 0) {
							amounts[i] = 0;
							if (searchWarehouse(w, i, Math.abs(itemsShort))) {
								surcharge = .1 * prices[i] * Math.abs(itemsShort);
								amounts[i] = Integer.parseInt(line[i + 2]) * prices[i] + surcharge;
							}
						} else {
							amounts[i] = Integer.parseInt(line[i + 2]) * prices[i];
							w.setItem(i, w.getItem(i) - Integer.parseInt(line[i + 2]));
						}
						total += amounts[i];
					}
					System.out.println("UPDATED " + w + "\n");
					System.out.println("*** CUSTOMER INVOICE ****** ");
					System.out.printf("%-13s%-10s%-10s%-10s", "City", "Amount1", "Amount2", "Amount3");
					System.out.println();
					System.out.format("%-13s", w.getwarehouseCity() + "   ");

					for (int i = 0; i < 3; i++) {
						System.out.printf("%-10s", nf.format(amounts[i]) + " ");
					}
					System.out.println("\nPRICE OF ORDER: " + nf.format(total) + "\n");
				}
			}
			data.close();
		} catch (final FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	////////////////// SEARCH WAREHOUSES*****************************
	public boolean searchWarehouse(final Warehouse w1, final int itemNumber, final int itemsShort) {
		// Warehouse w2;
		String searchResult = "";
		int amount = itemsShort;
		int max = w1.getItem(itemNumber);
		int pos = getWarehouse(w1.getwarehouseCity());
		// System.out.println("MAP: " + map.get(w1.getwarehouseCity()));

		for (final Warehouse w : warehouses) {
			amount = w.getItem(itemNumber);
			if (amount >= itemsShort && amount > max) {
				max = amount;
				pos = getWarehouse(w.getwarehouseCity());
				// w2 = map.get(w.getwarehouseCity());
			}
		}
		if (pos == getWarehouse(w1.getwarehouseCity())) {
			searchResult = "\t* Insufficient items of Item " + (itemNumber + 1) + " in stock. Order unfulfilled";
			System.out.println(searchResult);
			return false;
		} else {
			w1.setItem(itemNumber, 0);
			final Warehouse w = warehouses.get(pos);
			// w2.setItem(itemNumber, w2.getItem(itemNumber) - itemsShort);
			w.setItem(itemNumber, w.getItem(itemNumber) - itemsShort);
			searchResult = ("\t* Shipped " + itemsShort + " of Item" + (itemNumber + 1) + " from "
					+ w.getwarehouseCity() + " to " + w1.getwarehouseCity());
			// searchResult += (".\n\t Now remaining in " + w.getwarehouseCity() + ": " +
			// w.getItem(itemNumber));
			searchResult += "\n\t  An additional ten percent has been charged for this item.";
			System.out.println(searchResult);
			return true;
		}
	}

	///////////////////// *MAP WAREHOUSE TO NUMBER*///////////////////////////////
	public static int getWarehouse(final String city) {
		if (city.equals("New York"))
			return 0;
		if (city.equals("Miami"))
			return 1;
		if (city.equals("Los Angeles"))
			return 2;
		if (city.equals("Houston"))
			return 3;
		if (city.equals("Chicago"))
			return 4;
		else
			return -1;
	}

	///////////////////// *SET UP WAREHOUSES*///////////////////////////////
	public void setupWarehouses() {
		final String[] cities = { "New York", "Miami", "Los Angeles", "Houston", "Chicago" };
		for (final String s : cities) {
			final Warehouse w = new Warehouse(s);
			warehouses.add(w);
			map.put(s,w);
		}
	}
}
