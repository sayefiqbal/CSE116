package code.gui;

import edu.buffalo.fractal.FractalPanel;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import code.model.ColorModelFactory;
import code.model.Model;

import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import java.awt.image.IndexColorModel;
import java.awt.Dimension;

/**
 * Graphical User Interface Class
 * The view part of the project
 * Instantiates the Jframe, creates the menu, and sets up methods to update the fractal
 *
 */
public class GUI {
	
	private JMenuBar _menu;
	private JMenu _fileMenu, _fractalMenu, _colorMenu;
	private JMenuItem _exit, _escapeDistMenu, _setMaxPassesMenu, _resetZoomMenu, _setNumThreadsMenu;
	private JRadioButtonMenuItem _burningShipMenu, _juliaMenu, _mandelbrotMenu, _multibrotMenu, _color0Menu, _color1Menu, _color2Menu, _color3Menu;
	
	private JFrame _topFrame;
	private FractalPanel _fractalPanel;
	
	private Model _model; 
	private EventHandler _event;
	private MouseHandler _mevent;
	
	/**
	 * GUI constructor 
	 * Creates the JFrame, builds the menu, sets up the relationship between the GUI & Model & ActionListener class
	 */
	
	public GUI() {
		_topFrame = new JFrame("Fractal Generator");
		_topFrame.setLayout(new GridLayout());
		
		buildMenu();
		_topFrame.setJMenuBar(_menu);
		
		Dimension d = new Dimension(2048, 2048);
		_fractalPanel = new FractalPanel(d, ColorModelFactory.createRainbowColorModel(255));
		_topFrame.add(_fractalPanel);
		
		_model = new Model();
		_model.addObserver(this);
		_model.updateMandelBrot();
		
		actionListeners();
		
		_topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_topFrame.setVisible(true);
		_topFrame.pack();
	}
	
	/** NOT USED ANYMORE
	 * Updates the fractal image 
	 * @param x - takes in the matrix containing the escape distances 
	 */
	public void updateFractal(int[][] x){
		_fractalPanel.updateImage(x);
	}
	
	/**
	 * Updates the color or fractal 
	 * @param color - takes an in Index Color Model object 
	 * that is used in the update 
	 */
	
	public void updateColorScheme(IndexColorModel color){
		_fractalPanel.setIndexColorModel(color);
	}
	
	/**
	 * Adds an action listener to each menu item 
	 */
	
	private void actionListeners(){
		_event = new EventHandler(_model);
		_burningShipMenu.addActionListener(_event);
		_juliaMenu.addActionListener(_event);
		_multibrotMenu.addActionListener(_event);
		_mandelbrotMenu.addActionListener(_event);
		
		_color0Menu.addActionListener(_event);
		_color1Menu.addActionListener(_event);
		_color2Menu.addActionListener(_event);
		_color3Menu.addActionListener(_event);
		_escapeDistMenu.addActionListener(_event);
		_setMaxPassesMenu.addActionListener(_event);
		_resetZoomMenu.addActionListener(_event);
		_setNumThreadsMenu.addActionListener(_event);
		_exit.addActionListener(_event);
		_mevent = new MouseHandler(_model, this);
		_fractalPanel.addMouseListener(_mevent);
		_fractalPanel.addMouseMotionListener(_mevent);
	}
	
	/**
	 * Creates the JMenu and sets up the menu items
	 */
	
	private void buildMenu() {
		_menu = new JMenuBar();
		_fileMenu = new JMenu("File");
		_fractalMenu = new JMenu("Fractal");
		_colorMenu = new JMenu("Color");
		_menu.add(_fileMenu);
		_menu.add(_fractalMenu);
		_menu.add(_colorMenu);
		
		_escapeDistMenu = new JMenuItem("Set Escape Distance");
		_setMaxPassesMenu = new JMenuItem("Set Escape Time");
		_resetZoomMenu = new JMenuItem("Reset Zoom");
		_setNumThreadsMenu = new JMenuItem("Set Number of Threads");
		_exit = new JMenuItem("Exit");
		_fileMenu.add(_escapeDistMenu);
		_fileMenu.add(_setMaxPassesMenu);
		_fileMenu.add(_resetZoomMenu);
		_fileMenu.add(_setNumThreadsMenu);
		_fileMenu.add(_exit);
		
		ButtonGroup fractalBG = new ButtonGroup();
		_burningShipMenu = new JRadioButtonMenuItem("Burning Ship");
		_juliaMenu = new JRadioButtonMenuItem("Julia");
		_mandelbrotMenu = new JRadioButtonMenuItem("Mandelbrot");
		_multibrotMenu = new JRadioButtonMenuItem("Multibrot");
		_mandelbrotMenu.setSelected(true);
		fractalBG.add(_burningShipMenu);
		fractalBG.add(_juliaMenu);
		fractalBG.add(_mandelbrotMenu);
		fractalBG.add(_multibrotMenu);
		_fractalMenu.add(_burningShipMenu);
		_fractalMenu.add(_juliaMenu);
		_fractalMenu.add(_mandelbrotMenu);
		_fractalMenu.add(_multibrotMenu);
		
		ButtonGroup colorBG = new ButtonGroup();
		_color0Menu = new JRadioButtonMenuItem("Color Scheme: Rainbow");
		_color1Menu = new JRadioButtonMenuItem("Color Scheme: Gray");
		_color2Menu = new JRadioButtonMenuItem("Color Scheme: Blues");
		_color3Menu = new JRadioButtonMenuItem("Color Scheme: Random Rainbow");
		_color0Menu.setSelected(true);
		colorBG.add(_color0Menu);
		colorBG.add(_color1Menu);
		colorBG.add(_color2Menu);
		colorBG.add(_color3Menu);
		_colorMenu.add(_color0Menu);
		_colorMenu.add(_color1Menu);
		_colorMenu.add(_color2Menu);
		_colorMenu.add(_color3Menu);
	}
	
	/**
	 * Access method for the Fractal Panel object
	 * @return - fractal panel object
	 */
	
	public FractalPanel getFractalPanel(){
		return _fractalPanel; 
	}
}
