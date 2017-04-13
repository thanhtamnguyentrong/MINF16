
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputListener;

public class GraphComponent extends JComponent implements MouseInputListener, KeyListener {
	
	private static final int baseRadius = 5;
	private boolean altPressed = false;
	private boolean spacePressed = false;
	private ArrayList<Line2D> listEdges;
	private ArrayList<RectangularShape> listShapes ;
	private ArrayList<RectangularShape> listJointPoint;
	private int currentShape = 1; 
	private RectangularShape currentEllipse;
	private Line2D currentEdge;
	private Point2D currentLocation;
	
	public GraphComponent(){
		super();
		listShapes = new ArrayList<>();
		listEdges = new ArrayList<>();
		listJointPoint = new ArrayList<>();
		ShapeCache.loadCache(baseRadius);
		setPreferredSize(new Dimension(700, 700));
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for (RectangularShape ellipse : listShapes) {
        	g2D.draw(ellipse);

		}
        for (Line2D edge : listEdges){
        	g2D.draw(edge);
        }
        
        g2D.setPaint(Color.RED);
        for (RectangularShape jPoint : listJointPoint){
        	g2D.draw(jPoint);
        	g2D.fill(jPoint);
        }
        //paint code goes here of whatever
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("clicked");
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("pressed");
		boolean inShape = false;

		for (RectangularShape shape : listShapes) {
			if(shape.contains(arg0.getPoint())){
//				System.out.println("win");
				inShape = true;
				currentEllipse = shape;
				if(altPressed){
					currentEdge = new Line2D.Double(arg0.getX(), arg0.getY(), arg0.getX(), arg0.getY());
					listEdges.add(currentEdge);
				}
				break;
			}
		}
		
		if(!inShape){
			for (RectangularShape shape : listJointPoint) {
				if(shape.contains(arg0.getPoint())){
	//				System.out.println("win");
					inShape = true;
					currentEllipse = shape;
					if(altPressed){
						currentEdge = new Line2D.Double(arg0.getX(), arg0.getY(), arg0.getX(), arg0.getY());
						listEdges.add(currentEdge);
					}
					break;
				}
			}
		}
		if(!inShape){	
			if(!altPressed){
				RectangularShape clonedShape = ShapeCache.getShape(currentShape);
				double width = clonedShape.getWidth();
				double height = clonedShape.getHeight();
				clonedShape.setFrame(arg0.getX()-(width/2), arg0.getY()-(height/2), width, height);
				listShapes.add(clonedShape);
				repaint();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		createdNewEdge();
		currentEllipse = null;
		currentEdge = null;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(currentEllipse != null){
			if(!altPressed){
				double width = currentEllipse.getWidth();
				double height = currentEllipse.getHeight();
				if(currentEdge == null ){
					currentEllipse.setFrame(arg0.getX()-(width/2), arg0.getY()- (height/2), width, height);
				}
			}
			else{
				if(currentEdge!=null){
					currentEdge.setLine(currentEdge.getX1(), currentEdge.getY1(), arg0.getX(), arg0.getY());
				}
			}
		}
		currentLocation = arg0.getPoint();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public void setCurrenShape(int currentShape){
		this.currentShape = currentShape;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
			case KeyEvent.VK_ALT:
				altPressed = true;
				break;
			case KeyEvent.VK_SPACE:
				spacePressed=true;
				break;	
			default:
				break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		altPressed=e.isAltDown();
		createdNewEdge();
		spacePressed = false;
		e.consume();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void createdNewEdge(){
		boolean inShape = false;
		//if currently draw a edge 
		if(currentEdge!=null){
			if(spacePressed){
				RectangularShape clonedShape = ShapeCache.getShape(1);
				double width = clonedShape.getWidth();
				double height = clonedShape.getHeight();
				clonedShape.setFrame(currentLocation.getX()-(width/2), currentLocation.getY()-(height/2), width, height);
				listJointPoint.add(clonedShape);
			} else{
				for (RectangularShape shape : listShapes) {
					if(shape.contains(currentLocation)){
						inShape = true;
						break;
					}
				}
				if(!inShape)
				{
					RectangularShape clonedShape = ShapeCache.getShape(currentShape);
					double width = clonedShape.getWidth();
					double height = clonedShape.getHeight();
					clonedShape.setFrame(currentLocation.getX()-(width/2), currentLocation.getY()-(height/2), width, height);
					listShapes.add(clonedShape);
				}
			}
			currentEdge = null;
			currentEllipse=null;
		}
		repaint();
	}
}
