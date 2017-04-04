import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

public class GraphComponent extends JComponent implements MouseInputListener {
	
	private static final int baseRadius = 5;
	private ArrayList<Point> listCircle ;
	private Point currentPoint;
	public GraphComponent(){
		super();
		listCircle = new ArrayList<>();
		listCircle.add(new Point(5, 5));
		setPreferredSize(new Dimension(500, 500));
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point point : listCircle) {
			g.drawOval(point.x- baseRadius, point.y - baseRadius, baseRadius*2, baseRadius*2);
		}
        //paint code goes here of whatever
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		boolean inCircle = false;
		int distX;
		int distY;
		for (Point point : listCircle) {
			distX = arg0.getX() - point.x;
			distY = arg0.getY() - point.y;
			if(Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)) <= baseRadius ){
				System.out.println("win");
				inCircle = true;
				currentPoint = point;
				break;
			}
		}
		if(!inCircle){
			listCircle.add(arg0.getPoint());
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		currentPoint = null;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(currentPoint != null){
			currentPoint.setLocation(arg0.getPoint());
		}
		
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
}
