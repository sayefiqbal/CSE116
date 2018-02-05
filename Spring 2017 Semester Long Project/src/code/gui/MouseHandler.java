package code.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import code.model.Model;

@SuppressWarnings("serial")
public class MouseHandler extends JPanel implements MouseListener, MouseMotionListener{
	
	private Model _model; 
	private GUI _gui; 
	private int _xPressed; 
	private int _yPressed; 
	private int _xReleased; 
	private int _yReleased; 
	private Point _startPoint;
	private int _acomp = AlphaComposite.SRC_OVER;
    private float _alpha = 0.2F;
	
	public MouseHandler(Model model, GUI gui){
		_model = model; 
		_gui = gui;
	}

         
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		_xPressed = arg0.getX();
		_yPressed = arg0.getY();
		
		 _startPoint=arg0.getPoint();
		 setOpaque(true);

         Graphics2D g2 = (Graphics2D) _gui.getFractalPanel().getGraphics();

         Rectangle2D rect = new Rectangle2D.Double();
         rect.setFrameFromDiagonal(arg0.getPoint().x, arg0.getPoint().y,_startPoint.x, _startPoint.y);
         g2.setComposite(AlphaComposite.getInstance(_acomp, _alpha));
         g2.draw(rect);
         g2.setColor(Color.GREEN);
         g2.fill(rect);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		_xReleased = arg0.getX();
		_yReleased = arg0.getY();
		
		int _xmin = Math.min(_xPressed, _xReleased);
		int _xmax = Math.max(_xPressed, _xReleased);
		int _ymin = Math.min(_yPressed, _yReleased);
		int _ymax = Math.max(_yPressed, _yReleased);
		_model.updateBounds(_xmin, _xmax, _ymin, _ymax);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		setOpaque(true);
	    
        Graphics2D g2 = (Graphics2D) _gui.getFractalPanel().getGraphics();

        Rectangle2D rect = new Rectangle2D.Double();
        rect.setFrameFromDiagonal(arg0.getPoint().x, arg0.getPoint().y,_startPoint.x, _startPoint.y);
        g2.setComposite(AlphaComposite.getInstance(_acomp, _alpha));
        g2.draw(rect);
        g2.setColor(Color.GREEN);
        g2.fill(rect);	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	static class Rectangle {
		 
        int _x1,_y1,_x2,_y2;

        Rectangle(int x1, int y1,int x2, int y2){
            _x1=x1;
            _y1=y1;
            _x2=x2;
            _y2=y2;
        }
        void paint(Graphics g){
        	g.drawRect(_x1, _y1, _x2, _y2);
        }
	}

}
