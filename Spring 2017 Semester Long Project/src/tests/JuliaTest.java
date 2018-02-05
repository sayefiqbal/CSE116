package tests;
/**
 * Tests the Julia class 
 * 
 */

import static org.junit.Assert.*;
import org.junit.Test;

import code.fractals.*;

public class JuliaTest {
	
	
	/**
	 * Generates a Julia object 
	 * Uses multiple assert statements to calculate the coordinate 
	 * We used Wolfram Alpha to calculate the expected result 
	 * 
	 */
	
	@Test
	public void CoordinateTranslatorTest() {
		Julia jul = new Julia();
		assertEquals(-1.7, jul.pixelToXCoordinate(0), 0.005);
		assertEquals(-1.4890581568, jul.pixelToXCoordinate(127), 0.005);
		assertEquals(1.7, jul.pixelToXCoordinate(2047), 0.005);
		
		assertEquals(-1.0, jul.pixelToYCoordinate(0), 0.005);
		assertEquals(-0.8759159745, jul.pixelToYCoordinate(127), 0.005);
		assertEquals(1.0, jul.pixelToYCoordinate(2047), 0.005);
	}
	/**
	 * Generates a Juila object 
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin never exceeds the escape distance
	 * 
	 */

	@Test
	public void MaxPassesTest() {
		Julia jul = new Julia();
		assertEquals(255, jul.escapeTime(1.0492187499999897, -0.234375, 2.0));
	}
	
	/**
	 * Generates a Julia object
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin exceeds the escape distance after a single loop pass
	 * 
	 */
	
	@Test
	public void SingelLoopTest() {
		Julia jul = new Julia();
		assertEquals(1, jul.escapeTime(1.6933593749999853, 0.9765625, 2.0));
	}
	
	/**
	 * Generates a Julia object
	 * Uses an assert statement to check that the fractal returns a 2-d array with 512 rows and 512 columns
	 * 
	 */
	
	@Test
	public void FractalMatrixTest() {
		Julia jul = new Julia();
		int[][] mat = jul.getPasses(2.00);
		assertEquals(2048, mat.length);
		assertEquals(2048, mat[0].length);
	}
	
	/**
	 * Generates a Julia object 
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin exceeds the escape distance after a 10 passes of the loop 
	 * 
	 */
	
	@Test
	public void TenLimitTest(){
		Julia jul = new Julia();
		assertEquals(10, jul.escapeTime(1.4538160469667272, -0.13502935420743645, 3));
	}
	
	/**
	 * Generates a Julia obj
	 * Set the maximum escape time to 135
	 * Uses an assert statement to check escape time for a coordinate never exceeds the escape distance
	 */
	
	@Test
	public void phase3Test(){
		Julia jul = new Julia();
		jul.setMaxPasses(135);
		assertEquals(135, jul.escapeTime(1.0492187499999897, -0.234375, 2));
	}
	
}
