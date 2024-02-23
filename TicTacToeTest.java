package Praktikum;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class TicTacToeTest {

	TicTacToeUIMock  Mock = new TicTacToeUIMock();
	TicTacToe ttt = new TicTacToe(Mock);
	
	char spieler1 = 'X';
	char spieler2 = 'O';
	char [][] arrayX1 = {{spieler1,spieler1,spieler1},
			{spieler2,' ',' '},
			{spieler1,' ',spieler2}};
	
	char [][] arrayX2 = {{spieler1,' ',spieler1},
			{spieler1,' ',' '},
			{spieler1,' ',spieler2}};
	
	char [][] arrayX3 = {{spieler1,spieler1,spieler1},
			{spieler1,spieler2,spieler2},
			{spieler2,spieler1,spieler1}};
	
	char [][] arrayX4 = {{' ',spieler1,spieler1},
			{spieler2,' ',' '},
			{spieler1,' ',' '}};
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGewinnBedingungen() {
		
		ttt.setSpielbrett(arrayX3);
		//assertEquals(spieler1,ttt.getGewinner());
		assertTrue(ttt.isUnentschieden());
	}
	
	@Test
	public void testgetSpielzustand() {
		ttt.setSpielbrett(arrayX4);
		assertEquals(TicTacToe.LAEUFT, ttt.getSpielzustand());
	}
	
	@Test
	public void testIsGueltig() {
		ttt.setSpielbrett(arrayX4);
		//assertFalse(ttt.isGueltig(3,2));
		assertEquals(false, ttt.isGueltig(3,2));
		assertTrue(ttt.isGueltig(2, 2));
		assertFalse(ttt.isGueltig(2, 0));
	}
	
	@Test
	public void testGetNaechstenSpieler() {
		assertEquals(0, ttt.getNaechstenSpieler(13));
	}
	
	@Test
	public void testIsSpielzug() {
		ttt.setSpielbrett(arrayX4);
        String[] zug = new String [] {"grd","11","a"}; 
		Mock.setZugfolge(zug);
		assertEquals(TicTacToe.LAEUFT,ttt.spielzug(1));
		//assertEquals(TicTacToe.LAEUFT,ttt.spielzug(1));
	}
	@Test
	public void testGetgewinnerZeichnen()
	{
		char [][] spielbrett = {{spieler2,' ',spieler1},{' ',spieler2,spieler1},{spieler1,' ',spieler2}};
		ttt.setSpielbrett(spielbrett);
		assertEquals("getGewinner ",spieler2, ttt.getGewinner());
		
		char brett2[][] = { {spieler1,spieler2,spieler2},{spieler1,spieler1,spieler2},{spieler2,spieler1,' '}};
        ttt.setSpielbrett(brett2);
        assertEquals("getGewinner ",' ', ttt.getGewinner());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSpielzugException() {
		ttt.spielzug(5);
	} 
	
	
	@Test
	public void testSpiel() {
		String zugfolgeUnentschieden[] = {"11","00","02","20","10","12","21","01","22"};
		char brettUnentschieden[][] = { {spieler2,spieler2,spieler1},{spieler1,spieler1,spieler2},{spieler2,spieler1,spieler1}};
										 
		String zugfolgeSieg[] = 		 {"11","00","02","20","10","12","21","22","01"};
		char brettSieg[][] = { {spieler2,spieler1,spieler1},{spieler1,spieler1,spieler2},{spieler2,spieler1,spieler2}};
		
		
		TicTacToe ttt2 = new TicTacToe(Mock);
		Mock.setZugfolge(zugfolgeUnentschieden);
		assertEquals("ganzes Spiel", TicTacToe.UNENTSCHIEDEN, ttt2.spiel(0));
		assertArrayEquals("felder gleichheit ",brettUnentschieden,ttt2.getSpielbrett());
	
		TicTacToe ttt3 = new TicTacToe(Mock);
		Mock.setZugfolge(zugfolgeSieg);
		assertEquals("Spiel entschieden", TicTacToe.ENTSCHIEDEN, ttt3.spiel(0));
		assertEquals("X gewinnt", 'X', ttt3.getGewinner());
		assertArrayEquals("felder gleichheit ",brettSieg,ttt3.getSpielbrett());
	}
	
	@Test 
	public void testAnzahlZuege(){
		String zugfolgeSieg[] = {"11","asd","11","00","02","20","10","12","21","22","01"};
		//String zugfolgeSieg[] = {"12","21","22","01"};
		
		//TicTacToe ttt2 = new TicTacToe(tttm);
		Mock .setZugfolge(zugfolgeSieg);
		ttt.spiel(0);
		assertEquals("Züge: ", 0,ttt.getZugAnzahl());
	
	
	}
	
	
	
	
	
	

	
	
	
	
}
