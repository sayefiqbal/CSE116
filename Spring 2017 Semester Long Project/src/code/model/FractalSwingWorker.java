package code.model;

import code.fractals.*;
import javax.swing.SwingWorker;
import edu.buffalo.fractal.WorkerResult;

public class FractalSwingWorker extends SwingWorker<WorkerResult, Void> {

	Fractal _f;
	double _escDist;
	int _start;
	int _end;
	int _pixelMax;
	
	public FractalSwingWorker(Fractal f, double escDist, int start, int end, int pixelMax) {
		_f = f;
		_escDist = escDist;
		_start = start;
		_end = end;
		_pixelMax = pixelMax;
	}
	
	@Override
	public WorkerResult doInBackground() {
		return new WorkerResult(_start, _f.getPasses(_escDist, _start, _end));
	}
}
