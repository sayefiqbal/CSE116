package code.fractals;

import edu.buffalo.fractal.FractalPanel;
import edu.buffalo.fractal.ComputePool;
import code.model.FractalSwingWorker;

	/**
	 * 
	 * Defines the parent class of all fractals used in this project. 
	 *
	 */
public class Fractal {
	
	protected double _xMin;
	protected double _xMax;
	protected double _yMin;
	protected double _yMax;
	protected int _pixelMax;
	protected int _passesMax;
	protected double _xIncrement;
	protected double _yIncrement;
	
	/**
	 * Constructs a Fractal object with default x- and y-coordinate ranges of (-1.0,1.0) and an output grid of 2048 x 2048 pixels.
	 * 
	 */
	
	public Fractal() {
		this(-1.0,1.0,-1.0,1.0);
	}
	
	/**
	 * Constructs a Fractal object with user-specified x- and y-coordinate ranges and an output grid of 2048 x 2048 pixels.
	 *
	 *@param xmin - minimum x value
	 *@param xmax - maximum x value
	 *@param ymin - minimum y value
	 *@param ymax - maximum y value
	 */
	
	public Fractal(double xmin, double xmax, double ymin, double ymax) {
		_xMin = xmin;
		_xMax = xmax;
		_yMin = ymin;
		_yMax = ymax;
		_pixelMax = 2048;
		_passesMax = 255;
		_xIncrement = (_xMax - _xMin) / (_pixelMax - 1);
		_yIncrement = (_yMax - _yMin) / (_pixelMax - 1);
	}
	
	/**
	 * Generates a 2048 x 2048 square array of values according to the class's escape time algorithm.
	 * 
	 * @param escDist - escape distance for the escape time algorithm
	 * 
	 * @return A 2D integer array filled with escape times for points mapped from the array indices onto appropriate (x,y) coordinate ranges.
	 *
	 */
	
	public int[][] getPasses(double escDist) {
		int[][] retVal = new int[_pixelMax][_pixelMax];
		for (int x = 0; x < _pixelMax; x++) {
			for (int y = 0; y < _pixelMax; y++) {
				retVal[x][y] = escapeTime(pixelToXCoordinate(x), pixelToYCoordinate(y), escDist);
			}
		}
		return retVal;
	}
	
	/**
	 * Creates int[][] with all x values, y values from start (inclusive) to end (exclusive)
	 * @param escDist - escape distance for the escape time algorithm
	 * @param start - starting index
	 * @param end - ending index depending on range
	 * @return - matrix holding escape times between start and end 
	 */
	public int[][] getPasses(double escDist, int start, int end) {
		int[][] retVal = new int[end - start + 1][_pixelMax];
		System.out.println(retVal.length + " " + retVal[0].length);
		for (int y = 0; y < _pixelMax; y++) {
			for (int x = start; x <= end; x++) {
				retVal[x - start][y] = escapeTime(pixelToXCoordinate(x), pixelToYCoordinate(y), escDist);
			}
		}
		
		return retVal;
	}
	
	/**
	 * Creates an array of FractalSwingWorkers which generate sub-arrays using this fractal's 
	 * getPasses(double, int, int) method, then updates the fractal image with pool.generateFractal()
	 * @param escDist - escape distance for the escape time algorithm
	 * @param numThreads - user's desired # of threads, or the original 4
	 * @param panel - current Fractal Panel in use
	 */
	//
	public void compute(double escDist, int numThreads, FractalPanel panel) {
		ComputePool pool = new ComputePool();
		pool.changePanel(panel);
		
		FractalSwingWorker[] instances = new FractalSwingWorker[numThreads];
		
		int i = 0;
		int end = -1;
		int start;
		
		while (i < numThreads) {
			start = end + 1;
			end = start - 1 + (_pixelMax / numThreads);
			if (i == (numThreads - 1)) {
				end = _pixelMax - 1;
			}
			instances[i] = new FractalSwingWorker(this, escDist, start, end, _pixelMax - 1);
			i++;
		}
		
		pool.generateFractal(_pixelMax, instances);
	}
	
	/**
	 * Calculates the number of passes through a set of functions needed for a given coordinate pair to stray 2 units or further from the origin. Because no functions are defined for the Fractal base class, always returns 1.
	 * 
	 * @param x - x value for the coordinate pair
	 * @param y - y value for the coordinate pair
	 * @param escDist - escape distance for the algorithm
	 * 
	 * @return The number of passes needed to move outside a 2 unit circle around the origin. Returns 255 if the coordinate pair does not escape within the maximum number of passes.
	 */
	
	public int escapeTime(double x, double y, double escDist) {
		return 1;	//placeholder
	}
	
	/**
	 * Maps an integer between 0 and (_pixelMax - 1) onto the y-coordinate range specified at construction.
	 * 
	 * @param pixel - the integer to be mapped
	 * 
	 * @return The y value corresponding to the input integer based on a division of the y-coordinate range into (_pixelMax) evenly-spaced numbers.
	 * 
	 */
	
	public double pixelToYCoordinate(int pixel) {
		double retVal;
		retVal = _yMin + _yIncrement * pixel;
		return retVal;
	}
	
	/**
	 * Maps an integer between 0 and (_pixelMax - 1) onto the x-coordinate range specified at construction.
	 * @param pixel - the integer to be mapped
	 * @return The x value corresponding to the input integer based on a division of the x-coordinate range into (_pixelMax) evenly-spaced numbers.
	 */
	
	public double pixelToXCoordinate(int pixel) {
		 return _xMin + _xIncrement * pixel;
	}
	
	/**
	 * Updates the new bounds when the user zooms in on the fractal
	 * @param xP - x-coordinate pixel when pressed
	 * @param xR - x-coordinate pixel when released
	 * @param yP - y-coordinate pixel when pressed
	 * @param yR - y-coordinate pixel when released
	 */
	
	public void setGrid(double xmin, double xmax, double ymin, double ymax) {
		_xMin = xmin;
		_xMax = xmax;
		_yMin = ymin;
		_yMax = ymax;
		_xIncrement = (_xMax - _xMin) / (_pixelMax - 1);
		_yIncrement = (_yMax - _yMin) / (_pixelMax - 1);
	}
	
	/**
	 * Resets the grid to original coordinates 
	 */
	
	public void resetGrid() {
		//Implemented in subclasses
	}
	
	/**
	 * Sets a new escape time
	 * @param p - new escape time
	 */
	public void setMaxPasses(int p) {
		if (p < 1 || p > 255) {
			throw new IllegalArgumentException();
		}
		else {
			_passesMax = p;
		}
	}

}
