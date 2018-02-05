package code.fractals;
/**
 * 
 * BurningShip subclass that defines a Multibrot fractal. 
 *
 */
public class BurningShip extends Fractal {
	/**
	 * Constructs a Fractal object with default x- and y-coordinate ranges of (-1.0,1.0) and an output grid of 512 x 512 pixels.
	 * 
	 */
	
	public BurningShip() {
			/**
	 * Constructs a Fractal object with user-specified x- and y-coordinate ranges and an output grid of 512 x 512 pixels.
	 *
	 *@param xmin - minimum x value
	 *@param xmax - maximum x value
	 *@param ymin - minimum y value
	 *@param ymax - maximum y value
	 */
		super(-1.8, -1.7, -0.08, 0.025);
	}
	
	public BurningShip(double xmin, double xmax, double ymin, double ymax) {
		super(xmin, xmax, ymin, ymax);
	}
	/**
	 * Generates a 512 x 512 square array of values according to the class's escape time algorithm.
	 * 
	 * @return A 2D integer array filled with escape times for points mapped from the array indices onto appropriate (x,y) coordinate ranges.
	 *
	 */

	@Override
	public int escapeTime(double x, double y, double escDist) {
		double xcalc = x;
		double ycalc = y;
		double xholder = xcalc;
		double yholder = ycalc;
		int passes = 0;
		double dist = Math.sqrt(xcalc*xcalc + ycalc*ycalc);
		
		while(dist <= escDist && passes < _passesMax) {
			xholder = (xcalc*xcalc) - (ycalc*ycalc) + x;
			yholder = Math.abs(2*xcalc*ycalc) + y;
			xcalc = xholder;
			ycalc = yholder;
			dist = Math.sqrt(xcalc*xcalc + ycalc*ycalc);
			passes++;
		}
		
		/**
	 * Maps an integer between 0 and 511 onto the y-coordinate range specified at construction.
	 * 
	 * @param pixel - the integer to be mapped
	 * 
	 * @return The y value corresponding to the input integer based on a division of the y-coordinate range into 512 evenly-spaced numbers.
	 * 
	 */
		return passes;
	}
	/**
	 * Resets the grid to original coordinates 
	 */
	@Override
	public void resetGrid() {
		this.setGrid(-1.8, -1.7, -0.08, 0.025);
	}
	
}
