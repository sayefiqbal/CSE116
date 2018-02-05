package code.fractals;
	/**
	 * 
	 * Defines the parent class of all fractals used in this project. 
	 *
	 */
public class Julia extends Fractal {
		/**
	 * Constructs a Julia object with default x- and y-coordinate ranges of (-1.0,1.0) and an output grid of 512 x 512 pixels.
	 * 
	 */
	
	public Julia() {
		super(-1.7, 1.7, -1.0, 1.0);
		//super(-0.30939334637964766, 0.3737769080234834, -0.30939334637964766, 0.3737769080234834);
		
	}
		/**
	 * Constructs a Juila object with user-specified x- and y-coordinate ranges and an output grid of 512 x 512 pixels.
	 *
	 *@param xmin - minimum x value
	 *@param xmax - maximum x value
	 *@param ymin - minimum y value
	 *@param ymax - maximum y value
	 */
	public Julia(double xmin, double xmax, double ymin, double ymax) {
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
			xholder = xcalc*xcalc - ycalc*ycalc - 0.72689;
			yholder = 2*xcalc*ycalc + 0.188887;
			xcalc = xholder;
			ycalc = yholder;
			dist = Math.sqrt(xcalc*xcalc + ycalc*ycalc);
			passes++;
		}
		
		return passes;
	}
	/**
	 * Resets the grid to original coordinates 
	 */
	@Override
	public void resetGrid() {
		this.setGrid(-1.7, 1.7, -1.0, 1.0);
	}

}
