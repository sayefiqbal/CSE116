package code.fractals;

public class Mandelbrot extends Fractal {

	public Mandelbrot (){
		super(-2.15, 0.6, -1.3, 1.3);
		/**
	 * Constructs a Mandelbrot object with user-specified x- and y-coordinate ranges and an output grid of 512 x 512 pixels.
	 *
	 *@param xmin - minimum x value
	 *@param xmax - maximum x value
	 *@param ymin - minimum y value
	 *@param ymax - maximum y value
	 */
	}
	public Mandelbrot(double xmin, double xmax, double ymin, double ymax){
		super(xmin, xmax, ymin, ymax); 
		/**
	 * Constructs a Mandelbrot object with default x- and y-coordinate ranges of (-1.0,1.0) and an output grid of 512 x 512 pixels.
	 * 
	 */
	}
	
	@Override
	public int escapeTime(double x, double y, double escDist){
		
		double xCalc = x; 
		double yCalc = y;
		double xHolder = xCalc;
		double yHolder = yCalc;
		double dist = Math.sqrt(Math.pow(xCalc, 2.0) + Math.pow(yCalc, 2.0)); 
		int passes = 0; 
		
		while(dist<= escDist && passes<_passesMax){ 
			xHolder = Math.pow(xCalc, 2) - Math.pow(yCalc, 2) + x;
			yHolder = 2 * xCalc * yCalc + y; 
			xCalc = xHolder;
			yCalc = yHolder;
			passes++; 
			dist = Math.sqrt(Math.pow(xCalc, 2.0) + Math.pow(yCalc, 2.0));
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
		this.setGrid(-2.15, 0.6, -1.3, 1.3);
	}
}
