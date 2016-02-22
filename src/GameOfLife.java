import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class GameOfLife {
	//change for arbitrary grid size
	private final int COLUMNS = 8;
	private final int ROWS = 6;
	
	//ROWS number of characters, consisting only of . or o
	private final String inputLineRegex = "^[.o]{"+COLUMNS+"}$";
	
	char[][] grid;
	
	public void play(){
		grid = getUserInputtedGrid();
		
		System.out.println("====YOUR GRID====");
		printGrid();
		System.out.println("=================");
		
		
		advance();
		
		System.out.println("====NEXT GEN=====");
		printGrid();
		System.out.println("=================");
		
		//loop advance() and printGrid() for continuous updating
	}
	
	public char[][] getUserInputtedGrid(){
		System.out.println("Please enter a "+COLUMNS+" x "+ROWS+" grid, one line at a time.");
		System.out.println("A '.' is a dead cell, a 'o' is a live cell");
		
		grid = new char[ROWS][COLUMNS];
		
		Scanner scan = null;
		try {
			scan = new Scanner(System.in);
			for (int i = 0; i < ROWS; i++) {
				String input = StringUtils.trim(scan.next());

				if (input.matches(inputLineRegex)) {
					char[] charArray = input.toCharArray();
					grid[i] = charArray;
				} else {
					System.out.println("There was something wrong with your input. Please try again");
					i--;
				}
			}
		}finally{
			scan.close();
		}
		
		return grid;
	}
	
	public void advance(){
		char[][] advancedGrid = new char[ROWS][COLUMNS];
		
		for(int rowPos = 0; rowPos < ROWS; rowPos++){
			for(int colPos = 0; colPos < COLUMNS; colPos++){
				int aliveNeighbors = numberOfLiveNeighbors(rowPos,colPos);
				
				if(aliveNeighbors == 3 || (isLiveCell(rowPos,colPos) && aliveNeighbors == 2)){
					advancedGrid[rowPos][colPos] = 'o';
				}else{
					advancedGrid[rowPos][colPos] = '.';
				}
			}
		}
		
		grid = advancedGrid;
	}
	
	public int numberOfLiveNeighbors(int rowPos, int colPos){
		int numAlive = 0;
		
		//check bottom
		if(isInBounds(rowPos +1, colPos) && isLiveCell(rowPos +1, colPos)){
			numAlive++;
		}
		//check top
		if(isInBounds(rowPos -1, colPos) && isLiveCell(rowPos -1, colPos)){
			numAlive++;
		}
		//check left
		if(isInBounds(rowPos, colPos -1) && isLiveCell(rowPos, colPos -1)){
			numAlive++;
		}
		//check right
		if(isInBounds(rowPos, colPos +1) && isLiveCell(rowPos, colPos +1)){
			numAlive++;
		}
		//check bottom left
		if(isInBounds(rowPos +1, colPos -1) && isLiveCell(rowPos +1, colPos -1)){
			numAlive++;
		}
		//check bottom right
		if(isInBounds(rowPos +1, colPos +1) && isLiveCell(rowPos +1, colPos +1)){
			numAlive++;
		}
		//check top left
		if(isInBounds(rowPos -1, colPos -1) && isLiveCell(rowPos -1, colPos -1)){
			numAlive++;
		}
		//check top right
		if(isInBounds(rowPos -1, colPos +1) && isLiveCell(rowPos -1, colPos +1)){
			numAlive++;
		}
		
		return numAlive;
	}
	
	private boolean isInBounds(int rowPos, int colPos) {
		boolean result = true;
		
		if(rowPos < 0){
			//out on top
			result = false;
		}else if(rowPos >= ROWS){
			//out on bottom
			result = false;
		}else if(colPos < 0){
			//out on left
			result = false;
		}else if(colPos >= COLUMNS){
			//out on right
			result = false;
		}
		
		return result;
	}

	public void printGrid(){
		for (char[] row : grid)
		{
			for(char c : row){
				System.out.print(c + " ");
			}
			System.out.print('\n');
		}
	}
	
	public boolean isLiveCell(int rowPos, int colPos){
		return grid[rowPos][colPos] == 'o' ? true : false;
	}
	
	public void setGrid(char[][] grid){
		this.grid = grid;
	}
	
	public char[][] getGrid(){
		return this.grid;
	}
}
