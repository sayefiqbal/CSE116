package code.fractals;
/**
 * 
 * Multibrot subclass that defines a Multibrot fractal. 
 *
 */
 
public class Multibrot extends Fractal {
	
	/**
	 * Constructs a Multibrot object with given x-min, x-max, y-min, y-max
	 * 
	 */
	
	public Multibrot(){ 
		super(-1.0, 1.0, -1.3, 1.3);
	}
	
	/**
	 * Constructs a Multibrot object with custom parameters. 
	 *@param xmin - minimum x value
	 *@param xmax - maximum x value
	 *@param ymin - minimum y value
	 *@param ymax - maximum y value
	 *
	 */
	
	public Multibrot(double xmin, double xmax, double ymin, double ymax){
		super(xmin, xmax, ymin, ymax);
	}

	/**
	 * Method used to calculate the escape time given two coordinates 
	 * 
	 */
	
	public int escapeTime(double x, double y, double escDist){
		
		double xCalc = x; 
		double yCalc = y; 
		double xHolder = xCalc;
		double yHolder = yCalc;
		double dist = Math.sqrt(Math.pow(xCalc, 2.0) + Math.pow(yCalc, 2.0)); 
		int passes = 0; 
		
		while(dist<= escDist && passes<_passesMax){ 
			xHolder = (xCalc * xCalc * xCalc) - (3 * xCalc * yCalc * yCalc) + x;
			yHolder = (3 * (xCalc * xCalc) * yCalc) - (yCalc * yCalc * yCalc) + y; 
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
		this.setGrid(-1.0, 1.0, -1.3, 1.3);
	}
}
