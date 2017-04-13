
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

public class GraphFrame extends JFrame implements WindowListener {
	
	private GraphComponent mGComponent;
	private Graph controller;

	public GraphFrame(Graph graph){
		mGComponent = new GraphComponent();
		//setPreferredSize(new Dimension(500, 500));
		JScrollPane scrollPane = new JScrollPane(mGComponent);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		add(scrollPane,BorderLayout.EAST);
		
		//Menu
		this.controller = graph;
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);	
		createMenuBar(menuBar);
		
		JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
		createToolBar(toolBar);
		add(toolBar);
		addWindowListener(this);
		mGComponent.repaint();
		pack();
		setVisible(true);
		this.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e){
				mGComponent.setFocusable(true);
				mGComponent.requestFocusInWindow();
				System.out.println(mGComponent.requestFocusInWindow());
			}
		});
		
	
		
	}

	private void createMenuItem(JMenu menu, String name, ActionListener action) {
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.addActionListener(action);
		menu.add(menuItem);
	}

	private void createMenuSeparator(JMenu menu) {
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.lightGray);
		menu.add(separator);
	}

	private void createMenuBar(JMenuBar menuBar){
		JMenu menu = new JMenu(Graph.MENU_FILE);
		menuBar.add(menu);

		createMenuItem(menu, Graph.MENU_ITEM_NEW, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				GraphFrame.this.controller.createFrame();
			}
		});
		
		createMenuSeparator(menu);

		createMenuItem(menu, Graph.MENU_ITEM_QUIT, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				GraphFrame.this.controller.quit();
			}
		});
	}
	
	private void createToolBar(JToolBar toolBar){
		toolBar.setLayout(new GridLayout(8,0,10,10));
		toolBar.add(new JLabel());
		JButton sCircleBtn = new JButton("Small Circle");
		JButton sSquareBtn = new JButton("Small Square");
		JButton bCircleBtn = new JButton("Big Circle");
		JButton bSquareBtn = new JButton("Big Square");
		sCircleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mGComponent.setCurrenShape(1);
			}
		});
		
		bCircleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mGComponent.setCurrenShape(2);
			}
		});
		
		sSquareBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mGComponent.setCurrenShape(3);
			}
		});
		
		bSquareBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mGComponent.setCurrenShape(4);
			}
		});
		toolBar.add(sCircleBtn);
		toolBar.add(bCircleBtn);
		toolBar.add(sSquareBtn);
		toolBar.add(bSquareBtn);
		
		
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.controller.deleteFrame(this);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


}

