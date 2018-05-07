import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ForestFireModel {

	public static void main(String[] args) {
		
		int rows = 101;
		int columns = 82;
		char[][] grid = new char[rows][columns];
		
		// Input p: The rate of spontaneous growth
		System.out.print("Please enter the rate of spontaneous growth p: ");
		double p = Double.parseDouble(System.console().readLine());
		
		// Input f: The rate of spontaneous fire
		System.out.print("Please enter the rate of spontaneous fire f: ");
		double f = Double.parseDouble(System.console().readLine());
		
		// Input q: The rate of induced growth
		System.out.print("Please enter the rate of induced growth q: ");
		double q = Double.parseDouble(System.console().readLine());
		
		// Writer to write a results file with results of first 20 generations.
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("Results.txt", false));
		} catch (IOException e) {
			System.out.println("There was some error in creating/overwriting the results file.");
		}
		
		// Initialize with Ashes
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid[i].length; j++) {
				grid[i][j] = 'A';
			}
		}
		
		// Run 20 generations. You can change this to produce as many generations as you want.
		for (int g=0; g<20; g++) {
			
			// Create a new array to produce the next generation.
			char[][] nextGen = new char[rows][columns];
			
			// Iterate the current generation.
			for (int i=0; i<grid.length; i++) {
				for (int j=0; j<grid[i].length; j++) {
					// Count all the types of neighbours.
					int[] neighbourTypesCount = neighbourTypesCount(grid, i, j);
					char currentState = grid[i][j];
					// Determine next state on the basis of current state, neighbour types count, and probabilities taken as input.
					char nextState = nextState(currentState, neighbourTypesCount, p, f, q);
					nextGen[i][j] = nextState;
				}
			}
			
			grid = nextGen;
			
			// Count number of Ashes, Trees, Fire in the next generation. 
			int[] stateTypeCounts = stateTypeCounts(grid);
			
			// Write to a file.
			if (writer != null) {
				// Also print the counts for the next generation.
				String results = stateTypeCounts[0] + " " + stateTypeCounts[1] + " " + stateTypeCounts[2];
				System.out.println("Results are : " + results);
				try {
					writer.write(results);
					writer.newLine();
				} catch (IOException e) {
					System.out.println("Error writing into file.");
				}
			}
			
		}
		
		try {
			writer.close();
			System.out.println("Successfully wrote results in file \"Results.txt\". You can find the file in root folder (the folder in which this program is placed.)");
		} catch (IOException e) {
			System.out.println("Error closing results file");
		}
	}
	
	public static char nextState(char currentState, int[] neighbourTypesCount, double p, double f, double q) {
		
		char nextState = currentState;
		
		// Determine next state
		switch (nextState) {
		// On the basis of Ashes as current state. Decide next state.
		case 'A':
			// Check if next state can be converted to tree on the basis of spontaneous growth probability.
			nextState = Math.random() < p ? 'T': 'A';
			// Check if it has a neighbour tree.
			boolean hasTreeNeighbour = neighbourTypesCount[1] > 0;
			
			// Check if it has a neighbour and induced growth probability is satisfied then produce a tree.
			if (hasTreeNeighbour && Math.random() < q) {
				nextState = 'T';
			}
			
			break;
			
		// On the basis of Tree as current state. Decide next state.
		case 'T':
			// Check if it has burning neighbour.
			boolean hasBurningNeighbour = neighbourTypesCount[2] > 0;
			nextState = hasBurningNeighbour ? 'F': 'T';
			
			// Decide the next state as Fire or not on the basis of spontaneous fire probability.
			if (Math.random() < f) {
				nextState = 'F';
			}
			
			break;
		
		// On the basis of Fire as current state. Decide next state.
		case 'F':
			// if fire set next state to Ashes.
			nextState = 'A';
			break;
		}
	
		
		return nextState;
	}
	
	public static int[] neighbourTypesCount(char[][] grid, int positionX, int positionY) {
		
		// Count all types of neighbours. Ashes, Fire and Burning.
		int[] neighbourTypesCount = new int[3];
		neighbourTypesCount[0] = 0;
		neighbourTypesCount[1] = 0;
		neighbourTypesCount[2] = 0;
		
		// Four neighbours on the basis of von neumann neighbourhood.
		int left = positionY - 1;
		int right = positionY + 1;
		int up = positionX - 1;
		int down = positionX + 1;
		
		char leftNeighbour = torusNeighbour(grid, positionX, left);
		char rightNeighbour = torusNeighbour(grid, positionX, right);
		char upNeighbour = torusNeighbour(grid, up, positionY);
		char downNeighbour = torusNeighbour(grid, down, positionY);
		
		neighbourTypesCount = increaseNeighbourTypeCount(leftNeighbour, neighbourTypesCount);
		neighbourTypesCount = increaseNeighbourTypeCount(rightNeighbour, neighbourTypesCount);
		neighbourTypesCount = increaseNeighbourTypeCount(upNeighbour, neighbourTypesCount);
		neighbourTypesCount = increaseNeighbourTypeCount(downNeighbour, neighbourTypesCount);
		
		return neighbourTypesCount;
	}
	
	public static char torusNeighbour(char[][] grid, int positionX, int positionY) {
		
		// Calculate torus neighbour according to Torus topology.
		int rows = 101;
		int columns = 82;
		
		int torusPositionX = ((positionX % rows) + rows) % rows;
		int torusPositionY = ((positionY % columns) + columns) % columns;
		
		return grid[torusPositionX][torusPositionY];
	}
	
	public static int[] increaseNeighbourTypeCount(char cell, int[] counts) {
		if (cell == 'A') {
			counts[0] += 1;
		} else if (cell == 'T') {
			counts[1] += 1;
		} else if (cell == 'F') {
			counts[2] += 1;
		}
		
		return counts;
	}
 	
	public static int[] stateTypeCounts(char[][] grid) {
		
		int[] count = new int[3];
		
		// Initialize all types of states to 0s.
		for (int i=0; i<count.length; i++) {
			count[i] = 0;
		}
		
		// Count all types of states. Ashes, Fire and burning.
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid[i].length; j++) {
				char cell = grid[i][j];
				
				if (cell == 'A') {
					count[0] += 1;
				} else if (cell == 'T') {
					count[1] += 1;
				} else if (cell == 'F') {
					count[2] += 1;
				}
			}
		}
		
		return count;
	}

}
