package tests;

/**
 * Tests the Mandelbrot class 
 * 
 */

import static org.junit.Assert.*;

import org.junit.Test;

import code.fractals.Mandelbrot;

public class MandelbrotTest {
	/**
	 * Generates a Mandelbrot object 
	 * Uses multiple assert statements to calculate the coordinate 
	 * We used Wolfram Alpha to calculate the expected result 
	 * 
	 */

	@Test
	public void CoordinateTranslatorTest() {
		Mandelbrot mandel = new Mandelbrot();
		assertEquals(-2.15, mandel.pixelToXCoordinate(0), 0.001);
   		assertEquals(-1.97938446507, mandel.pixelToXCoordinate(127), 0.001);
		assertEquals(0.6, mandel.pixelToXCoordinate(2047), 0.001);
		
		assertEquals(-1.3, mandel.pixelToYCoordinate(0), 0.001);
		assertEquals(-1.13869076697, mandel.pixelToYCoordinate(127), 0.001);
		assertEquals(1.3, mandel.pixelToYCoordinate(2047), 0.001);
	}
	
	/**
	 * Generates a Mandelbrot object 
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin never exceeds the escape distance
	 * 
	 */
	
	@Test
	public void MandelEsacapeTest() {
		
		Mandelbrot mandel = new Mandelbrot(); 
		int x = mandel.escapeTime(0.3207031250000001, -0.07109374999999386, 2.0);
		assertEquals(255, x, 0.001);
		
	}
	
	@Test
	public void MandelSingleLoopTest(){ 
		Mandelbrot mandel = new Mandelbrot(); 
		int y = mandel.escapeTime(0.5946289062500001, 1.2949218750000122, 2.0);
		assertEquals(1, y, 0.001);
	}
	/**
	 * Generates a Mandelbrot object
	 * Uses an assert statement to check that the fractal returns a 2-d array with 512 rows and 512 columns
	 * 
	 */
	
	@Test
	public void FractalMatrixTest(){ 	
		Mandelbrot mandel = new Mandelbrot(); 
		int [][] testMandel = mandel.getPasses(2.0);
		assertEquals(2048, testMandel.length);
		assertEquals(2048, testMandel[0].length);
	}

	/**
	 * Generates a Mandelbrot object 
	 * Uses an assert statement to check escape time for a coordinate whose distance from the origin exceeds the escape distance after a 10 passes of the loop 
	 * 
	 */
	
	@Test
	public void TenLimitTest(){
		Mandelbrot bs = new Mandelbrot();
		assertEquals(10, bs.escapeTime(0.46007827788650374, -0.3383561643835661, 3));
	}
	
	/**
	 * Generates a Mandelbrot obj
	 * Set the maximum escape time to 135
	 * Uses an assert statement to check escape time for a coordinate never exceeds the escape distance
	 */
	
	@Test
	public void phase3Test(){
		Mandelbrot mb = new Mandelbrot();
		mb.setMaxPasses(135);
		assertEquals(135, mb.escapeTime(0.3207031250000001, -0.07109374999999386, 2));
	}
	
}
