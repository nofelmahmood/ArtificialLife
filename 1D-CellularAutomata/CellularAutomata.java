import java.io.Console;
import java.util.Arrays;
import java.util.Random;

public class CellularAutomata {

	public static void main(String[] args) {
		
		// Initialize cells 
		int[] cells = new int[84];
		cells[0] = 0;
		cells[1] = 0;
		cells[82] = 0;
		cells[83] = 0;
		
		// Enter radius
		System.out.println("Please enter the neighborhood radius r. (1 or 2)");
		int radius = Integer.parseInt(System.console().readLine());
		
		// Enter Rule in Binary for example: 01011010
		System.out.println("Please enter the rule for the CA (Wolfram Notation).");
		char[] rule = System.console().readLine().toCharArray();
		int[] ruleArray = new int[rule.length];
		
		// Convert rule character array to int array
		for (int i=0; i<rule.length; i++) {
			String charString = Character.toString(rule[i]);
			ruleArray[i] = Integer.parseInt(charString);
		}
		
		// Enter starting condition. S for seed and R for random with 0.5 probability.
		System.out.println("Please enter the starting condition.");
		System.out.println("There are two conditions. Enter S for a seed (all cells are empty but cell no 42 is set to 1. Enter R for a random starting condition, each cell is set with a probability of p = 0.5.");
		String condition = System.console().readLine();
		
		if (condition.equalsIgnoreCase("S")) {
			
			// Set cells according to seed condition
			for (int i=2; i<cells.length-2; i++) {
				cells[i] = 0;
				if (i == 42) {
					cells[i] = 1;
				}
			}
			
		} else if (condition.equalsIgnoreCase("R")) {
			
			// Set cells according to random condition with 0.5 probability, using RandomCollection class included with the program.
			RandomCollection<String> items = new RandomCollection();
			items.add(0.5, "0");
			items.add(0.5, "1");
			
			for (int i=2; i<cells.length-2; i++) {
				cells[i] = Integer.parseInt(items.next());
			}
		}
		
		// Run 20 generations for cells. You can change this to how many generations you want to be printed.
		for (int j=0; j<20; j++) {
			System.out.print("Step ");
			System.out.print(j);
			System.out.println();
			
			for(int i=0; i<cells.length; i++) {
				System.out.print(cells[i]);
				System.out.print(" ");
			}
			System.out.println("");
			int[] nextGenCells = new int[84];
			nextGenCells[0] = 0;
			nextGenCells[1] = 0;
			nextGenCells[82] = 0;
			nextGenCells[83] = 0;
			
			// Iterate every cell and get next gen.
			for (int i=2; i<cells.length - 2; i++) {
				int next = nextGen(cells, i, ruleArray, radius);
				nextGenCells[i] = next;
			}
			
			cells = nextGenCells;
		}
	}
	
	public static int nextGen(int[] cells, int position, int[] rule, int radius) {
		
		// Get neighbours for respective position in cell according to radius.
		int totalNeighbours = (radius + radius) + 1;
		int[] neighboursCells = new int[totalNeighbours];
		
		int j = 0;
		int startingNeighbourPosition = position - radius;
		int endingNeighbourPosition = position + radius;
		
		for (int i = startingNeighbourPosition; i<=endingNeighbourPosition; i++) {
			neighboursCells[j] = cells[i];
			j++;
		}
		
		// Match and get index of the truth table value so that it can be mapped to rule.
		int matchedTruthTableValue = matchTruthTableValue(radius, neighboursCells);
		int nextGenValue = rule[rule.length - matchedTruthTableValue - 1];
		
		return nextGenValue;
	}
	
	public static int matchTruthTableValue(int radius, int[] neighboursCells) {
		
		int[][] tt = truthTable(radius);
		int value = 0;
		
		// Check which index of truth table array is equal to neighbours and get its index, so that it can be mapped with the rule and next 
		// generation can be produced.
		for (int i=0; i<tt.length; i++) {
			int[] fullRow = tt[i];
			if (Arrays.equals(fullRow, neighboursCells)) {
				value = i;
			}
			
		}
		
		return value;
	}
	
	public static int[][] truthTable(int radius) {

		// Generate truth table according to radius.
		int totalNeighbours = (radius + radius) + 1;
		int rows = (int) Math.pow(2, totalNeighbours);
		int columns = totalNeighbours;
		
		int[][] truthTable = new int[rows][columns];
		
		int startingFrequency = rows/2;
		int bit = 0;
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				truthTable[i][j] = j;
			}
		}
		
		for (int i=0; i<columns; i++) {
			for (int j=0; j<rows; j++) {

				truthTable[j][i] = bit;
				if ((j + 1) % startingFrequency == 0) {
					if (bit == 0) {
						bit = 1;
					} else {
						bit = 0;
					}
				}
			}
			startingFrequency = startingFrequency/2;
			bit = 0;
		}
		
		return truthTable;
	}
}
