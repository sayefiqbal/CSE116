package tests;

/**
 * Tests the Multibrot class 
 * 
 */

import static org.junit.Assert.*;

import org.junit.Test;

import code.fractals.Multibrot;

public class MultibrotTest {
	/**
	 * Generates a Multibrot object 
	 * Uses multiple assert statements to calculate the coordinate 
	 * We used Wolfram Alpha to calculate the expected result 
	 * 
	 */
	@Test
	public void CoordinateTranslatorTest() {
		Multibrot multi = new Multibrot();
		assertEquals(-1.0, multi.pixelToXCoordinate(0), 0.001);
		assertEquals(-0.87591597, multi.pixelToXCoordinate(127), 0.001);
		assertEquals(1.0, multi.pixelToXCoordinate(2047), 0.001);
		
		assertEquals(-1.3, multi.pixelToYCoordinate(0), 0.001);
		assertEquals(-1.13869076697, multi.pixelToYCoordinate(127), 0.001);
		assertEquals(1.3, multi.pixelToYCoordinate(2047), 0.001);
	}
	/**
	 * Generates a Multibrot object 
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin never exceeds the escape distance
	 * 
	 */
	
	@Test
	public void MultiEscapeTest() {
		
		Multibrot multi = new Multibrot(); 
		int x = multi.escapeTime(0.5859375, 0.24375000000000108, 2.0);
		assertEquals(255, x, 0.001);
		
	}
	
	/**
	 * Generates a Multibrot object
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin exceeds the escape distance after a single loop pass
	 * 
	 */
	
	@Test
	public void MultiSingleLoopTest(){ 
		Multibrot multi = new Multibrot(); 
		int y = multi.escapeTime(0.9921875, 1.05625, 2.0);
		assertEquals(1, y, 0.001);
	}
	
	/**
	 * Generates a Multibrot object
	 * Uses an assert statement to check that the fractal returns a 2-d array with 512 rows and 512 columns
	 * 
	 */
	
	@Test
	public void FractalMatrixTest(){ 	
		Multibrot multi = new Multibrot(); 
		int [][] testMulti = multi.getPasses(2.0);
		assertEquals(2048, testMulti.length);
		assertEquals(2048, testMulti[0].length);
	}

	
	/**
	 * Generates a Multibrot object 
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin exceeds the escape distance after a 10 passes of the loop 
	 * 
	 */
	
	@Test
	public void TenLimitTest(){
		Multibrot bs = new Multibrot();
		assertEquals(10, bs.escapeTime(0.7025440313111545, -0.5520547945205528, 3));
	}
	
	/**
	 * Generates a Multibrot obj
	 * Set the maximum escape time to 135
	 * Uses an assert statement to check escape time for a coordinate never exceeds the escape distance
	 */
	
	@Test
	public void phase3Test(){
		Multibrot mub = new Multibrot();
		mub.setMaxPasses(135);
		assertEquals(135, mub.escapeTime(0.5859375, 0.24375000000000108, 2));
	}
	
}
