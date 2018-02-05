package tests;

/**
 * Tests the Burning Ship class 
 * 
 */

import static org.junit.Assert.*;
import org.junit.Test;

import code.fractals.*;

public class BurningShipTest {
	
	/**
	 * Generates a burning ship object 
	 * Uses multiple assert statements to calculate the coordinate 
	 * We used Wolfram Alpha to calculate the expected result 
	 * 
	 */
	
	@Test
	public void CoordinateTranslatorTest() {
		BurningShip bs = new BurningShip();
		assertEquals(-1.8, bs.pixelToXCoordinate(0), 0.005);
		assertEquals(-1.7500244259, bs.pixelToXCoordinate(1023), 0.005);
		assertEquals(-1.7, bs.pixelToXCoordinate(2047), 0.005);
		
		assertEquals(-0.08, bs.pixelToYCoordinate(0), 0.005);
		assertEquals(-0.0275256472, bs.pixelToYCoordinate(1023), 0.005);
		assertEquals(0.025, bs.pixelToYCoordinate(2047), 0.005);
	}
	
	/**
	 * Generates a burning ship object 
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin never exceeds the escape distance
	 * 
	 */
	
	@Test
	public void MaxPassesTest() {
		BurningShip bs = new BurningShip();
		assertEquals(255, bs.escapeTime(-1.7443359374999874, -0.017451171875000338, 2.0));
	}
	
	/**
	 * Generates a burning ship object
	 * Uses an assert statement to check that none of the pixels in the Burning Ship set have an escape time of 0 or 1
	 * 
	 */
	
	@Test
	public void NoFastEscapesTest() {
		BurningShip bs = new BurningShip();
		int[][] mat = bs.getPasses(2.0);
		boolean val = true;
		
		for(int x = 0; x < mat.length; x++) {
			for(int y = 0; y <mat[0].length; y++) {
				if (mat[x][y] < 2) {
					val = false;
					System.out.println("Failed at ("+x+", "+y+")");
				}
			}
		}
		assertTrue(val);
	}
	
	/**
	 * Generates a burning ship object
	 * Uses an assert statement to check that the fractal returns a 2-d array with 512 rows and 512 columns
	 * 
	 */
	
	@Test
	public void FractalMatrixTest() {
		BurningShip bs = new BurningShip();
		int[][] mat = bs.getPasses(2.0);
		assertEquals(2048, mat.length);
		assertEquals(2048, mat[0].length);
	}
	
	/**
	 * Generates a burning ship object 
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin exceeds the escape distance after a 10 passes of the loop 
	 * 
	 */
	
	@Test
	public void TenLimitTest(){
		BurningShip bs = new BurningShip();
		assertEquals(10, bs.escapeTime(-1.6999999999999802, 0.0030136986301371603, 3));
	}
	
	/**
	 * Generates a BurningShip obj
	 * Set the maximum escape time to 135
	 * Uses an assert statement to check escape time for a coordinate never exceeds the escape distance
	 */
	
	@Test
	public void phase3Test(){
		BurningShip bs = new BurningShip();
		bs.setMaxPasses(135);
		assertEquals(135, bs.escapeTime(-1.7443359374999874, -0.017451171875000338, 2));
	}

}
