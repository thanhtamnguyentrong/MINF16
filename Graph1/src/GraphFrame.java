import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

public class GraphFrame extends JFrame {
	
	GraphComponent mGComponent;
	
	public GraphFrame(){
		mGComponent = new GraphComponent();
		//setPreferredSize(new Dimension(500, 500));

		add(mGComponent);
		mGComponent.repaint();
		pack();
		setVisible(true);
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GraphFrame();
	}}
