package code.model;

import java.util.Random;

/**
 / Model class
 * All the fractals are instantiated and methods are writted to suppor the EventHandler to keep updating the GUI
 * based on user input. 
 * 
 */
import code.fractals.*;
import code.gui.*;

public class Model {
	
	private Julia _julia; 
	private BurningShip _bship; 
	private Mandelbrot _mandel; 
	private Multibrot _multi; 
	private double _escapeDist; 
	private int _maxPasses;
	private GUI _gui; 
	private Fractal _currentFractal; 
	private int _numThreads;
	
	public Model(){
		_bship = new BurningShip(); 
		_julia = new Julia();
		_mandel = new Mandelbrot(); 
		_multi = new Multibrot(); 
		_gui = null;
		_escapeDist = 2; 
		_maxPasses = 255;
		_currentFractal = null; 
		_numThreads = 4;
	}
	/**
	 * EventHandler calls this method that closes the GUI application
	 */
	public void exit(){
		System.exit(0);
	}
	/**
	 * This method is used to create an associative relationship between Model and GUI
	 * @param x - takes in a GUI parameter (the current one)
	 */
	public void addObserver(GUI x){
		_gui = x; 
	}
	
	/**
	 * Method updates the escape distance 
	 * @param x - new escape distance
	 */
	public void updateEscapeDistance(double x){
		if (x > 0) {
		_escapeDist = x; 
		_gui.updateFractal(_currentFractal.getPasses(_escapeDist));
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * Method updates the maximum escape time
	 * @param m - new escape time
	 * @throws IllegalArgumentException - if user enters a number not between 1-255, inclusive
	 */
	public void updateMaxPasses(int m) throws IllegalArgumentException {
		_maxPasses = m;
		_currentFractal.setMaxPasses(_maxPasses);
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	
	/**
	 * 
	 * @param m - new number of threads to use
	 * @throws IllegalArgumentException - if the number is not between [1,128]
	 */
	public void updateNumThreads(int m) throws IllegalArgumentException {
		if (m < 1 || m > 128) {
			throw new IllegalArgumentException();
		}
		else {
			_numThreads = m;
			_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
		}
	}
	
	/**
	 * Updates the new bounds when the user zooms in on the fractal
	 * @param xP - x-coordinate pixel when pressed
	 * @param xR - x-coordinate pixel when released
	 * @param yP - y-coordinate pixel when pressed
	 * @param yR - y-coordinate pixel when released
	 */
	public void updateBounds(int xP, int xR, int yP, int yR) {
		double pX = _currentFractal.pixelToXCoordinate(xP);
		double pY = _currentFractal.pixelToYCoordinate(yP);
		double rX = _currentFractal.pixelToXCoordinate(xR);
		double rY = _currentFractal.pixelToYCoordinate(yR);

		_currentFractal.setGrid(pX, rX, pY, rY);
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	/**
	 * Resets the bounds to the original coordinates  
	 */
	public void resetBounds() {
		_currentFractal.resetGrid();
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	/**
	 * Updates the color scheme to rainbow
	 */
	public void updateColor0(){  
		_gui.updateColorScheme(ColorModelFactory.createRainbowColorModel(155));
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	/**
	 * Updates the color scheme to gray
	 */
	public void updateColor1(){  
		_gui.updateColorScheme(ColorModelFactory.createGrayColorModel(255));
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	/**
	 * Updates the color scheme to blue
	 */
	public void updateColor2(){  
		_gui.updateColorScheme(ColorModelFactory.createBluesColorModel(255));
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	/**
	 * Updates the color scheme to a random color 
	 */
	public void updateColor3(){  
		Random rand = new Random();
		_gui.updateColorScheme(ColorModelFactory.createRainbowColorModel(rand.nextInt(255)));
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	/**
	 * Updates the Julia Fractal using the new escape distance and/or color
	 */
	public void updateJulia(){
		_currentFractal = _julia;
		_currentFractal.setMaxPasses(_maxPasses);
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	/**
	 * Updates the Burning Ship Fractal using the new escape distance and/or color
	 */
	public void updateBurningShip(){
		_currentFractal = _bship;
		_currentFractal.setMaxPasses(_maxPasses);
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	/**
	 * Updates the Multibrot Fractal using the new escape distance and/or color
	 */
	public void updateMultiBrot(){
		_currentFractal = _multi;
		_currentFractal.setMaxPasses(_maxPasses);
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
	/**
	 * Updates the Mandelbrot Fractal using the new escape distance and/or color
	 */
	public void updateMandelBrot(){
		_currentFractal = _mandel; 
		_currentFractal.setMaxPasses(_maxPasses);
		_currentFractal.compute(_escapeDist, _numThreads, _gui.getFractalPanel());
	}
}