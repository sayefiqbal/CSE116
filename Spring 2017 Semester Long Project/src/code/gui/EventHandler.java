package code.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import code.model.Model;

/**
 * Event Handler class used to perform various actions in GUI
 *
 */
public class EventHandler implements ActionListener {

	private Model _model;
	
	/**
	 * Constructor that uses an Association relationship between Event Handler and the GUI
	 * @param m - Model class passed in by the GUI
	 */
	public EventHandler(Model m) {
		_model = m; 
	}
	
	/**
	 * Each if statement checks for the source of the event and performs the corresponding event 
	 */
	@Override 
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Julia")){
			_model.updateJulia();
		}
		if(e.getActionCommand().equals("Burning Ship")){
			_model.updateBurningShip();
		}
		if(e.getActionCommand().equals("Mandelbrot")){
			_model.updateMandelBrot();
		}
		if(e.getActionCommand().equals("Multibrot")){
			_model.updateMultiBrot();
		}
		if(e.getActionCommand().equals("Color Scheme: Rainbow")){
			_model.updateColor0();
		}
		if(e.getActionCommand().equals("Color Scheme: Gray")){
			_model.updateColor1();
		}
		if(e.getActionCommand().equals("Color Scheme: Blues")){
			_model.updateColor2();
		}
		if(e.getActionCommand().equals("Color Scheme: Random Rainbow")){
			_model.updateColor3();
		}
		if(e.getActionCommand().equals("Exit")){
			_model.exit();
		}
		if(e.getActionCommand().equals("Set Escape Distance")) {
			try {
				Double d = Double.parseDouble(JOptionPane.showInputDialog("Enter escape distance:"));
				_model.updateEscapeDistance(d);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: please enter a number greater than 0");
				}
		}
		if(e.getActionCommand().equals("Set Escape Time")) {
			try {
				Integer i = Integer.parseInt(JOptionPane.showInputDialog("Enter max passes (between 1 and 255):"));
				_model.updateMaxPasses(i);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: please enter an integer between 1 and 255.");
				}
		}
		if(e.getActionCommand().equals("Reset Zoom")) {
			_model.resetBounds();
		}
		if(e.getActionCommand().equals("Set Number of Threads")) {
			try {
				Integer i = Integer.parseInt(JOptionPane.showInputDialog("Enter number of threads (between 1 and 128):"));
				_model.updateNumThreads(i);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: please enter an integer between 1 and 128.");
				}
		}
	}
}
