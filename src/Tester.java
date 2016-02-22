import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Tester {
	
	GameOfLife game = new GameOfLife();
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outContent));
	    
	    char[][] grid = {
				{ 'o', '.', '.', '.', 'o', '.', 'o', 'o'},
				{ 'o', 'o', 'o', '.', '.', '.', 'o', '.'},
				{ '.', '.', '.', '.', '.', '.', 'o', '.'},
				{ '.', '.', '.', 'o', 'o', 'o', '.', '.'},
				{ '.', '.', '.', 'o', 'o', 'o', '.', '.'},
				{ 'o', '.', '.', 'o', 'o', 'o', '.', 'o'}};
	    
	    game.setGrid(grid);
	}

	@After
	public void cleanUp() {
	    System.setOut(null);
	    game.setGrid(null);
	}
	
	@Test
	public void testPrintGrid(){
		
		String expected = "o . . . o . o o \n"+
						  "o o o . . . o . \n"+
						  ". . . . . . o . \n"+
						  ". . . o o o . . \n"+
						  ". . . o o o . . \n"+
						  "o . . o o o . o \n";
		
		game.printGrid();
		
		assertEquals(expected,outContent.toString());
	}
	
	@Test
	public void testIsAliveWithDeadCell(){
		assertFalse( game.isLiveCell(2, 0));	
	}
	
	@Test
	public void testIsAliveWithLiveCell(){
		assertTrue( game.isLiveCell(1, 0));
	}
	
	@Test
	public void testAdvance(){
	    char[][] givenExampleGrid = {
				{ '.', '.', '.', '.', '.', '.', 'o', '.'},
				{ 'o', 'o', 'o', '.', '.', '.', 'o', '.'},
				{ '.', '.', '.', '.', '.', '.', 'o', '.'},
				{ '.', '.', '.', '.', '.', '.', '.', '.'},
				{ '.', '.', '.', 'o', 'o', '.', '.', '.'},
				{ '.', '.', '.', 'o', 'o', '.', '.', '.'}};
		
		String expected = 
				  ". o . . . . . . \n"+
				  ". o . . . o o o \n"+
				  ". o . . . . . . \n"+
				  ". . . . . . . . \n"+
				  ". . . o o . . . \n"+
				  ". . . o o . . . \n";
		
		game.setGrid(givenExampleGrid);
		game.advance();
		game.printGrid();
		
		assertEquals(expected,outContent.toString());
	}
	
	@Test
	public void testGetAndSetGrid(){
		char[][] setGrid = {
				{ '.', '.', '.', '.', '.', '.', 'o', '.'},
				{ 'o', 'o', 'o', '.', '.', '.', 'o', '.'},
				{ '.', '.', '.', '.', '.', '.', 'o', '.'},
				{ '.', '.', '.', '.', '.', '.', '.', '.'},
				{ '.', '.', '.', 'o', 'o', '.', '.', '.'},
				{ '.', '.', '.', 'o', 'o', '.', '.', '.'}};
		game.setGrid(setGrid);
		
		char[][] identicalGrid = {
				{ '.', '.', '.', '.', '.', '.', 'o', '.'},
				{ 'o', 'o', 'o', '.', '.', '.', 'o', '.'},
				{ '.', '.', '.', '.', '.', '.', 'o', '.'},
				{ '.', '.', '.', '.', '.', '.', '.', '.'},
				{ '.', '.', '.', 'o', 'o', '.', '.', '.'},
				{ '.', '.', '.', 'o', 'o', '.', '.', '.'}};
		
		assertArrayEquals(identicalGrid,game.getGrid());
	}
	
	@Test
	public void testNumberOfLiveNeighborsWithNoneLiveInBounds(){
		assertEquals(0, game.numberOfLiveNeighbors(3, 1));
	}
	
	@Test
	public void testNumberOfLiveNeighborsWithAllLiveInBounds(){
		assertEquals(8, game.numberOfLiveNeighbors(4, 4));
	}
	
	@Test
	public void testNumberOfLiveNeighborsWithPositionAtTopRightCorner(){
		assertEquals(2, game.numberOfLiveNeighbors(0, 7));
	}
	
	@Test
	public void testNumberOfLiveNeighborsWithPositionAtTop(){
		assertEquals(0, game.numberOfLiveNeighbors(0, 4));
	}
	
	@Test
	public void testNumberOfLiveNeighborsWithPositionAtBottomLeftCorner(){
		assertEquals(0, game.numberOfLiveNeighbors(5, 0));
	}
	
	@Test
	public void testNumberOfLiveNeighborsWithPositionAtBottom(){
		assertEquals(5, game.numberOfLiveNeighbors(5, 4));
	}
}
