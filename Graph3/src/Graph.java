import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Graph{
	public final static String MENU_FILE = "File";
	public final static String MENU_ITEM_NEW = "New";
	public final static String MENU_ITEM_CLOSE = "Close";
	public final static String MENU_ITEM_QUIT = "Quit";
	public final static String DIALOG_QUIT_MSG = "Do you really want to quit ?";
	public final static String DIALOG_QUIT_TITLE = "Quit ?";
	public final static String TITLE = "Graph";

	private static final List<JFrame> frames = new ArrayList<>();

	public void quit() {
		int answer = JOptionPane.showConfirmDialog(null, DIALOG_QUIT_MSG,
				DIALOG_QUIT_TITLE, JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			System.exit(0);
		}else{
			System.out.println("not quit");
		}
	}

	public JFrame createFrame() {
		JFrame frame = new GraphFrame(this);
		frame.setTitle(TITLE + " " + frames.size() + 1);
//		int pos = 30 * (frames.size() % 5);
//		frame.setLocation(pos, pos);
//		frame.setPreferredSize(new Dimension(960, 720));
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frames.add(frame);
		return frame;
	}

	public void deleteFrame(JFrame frame) {
		if (frames.size() > 1) {
			frames.remove(frame);
			frame.dispose();
		} else {
			quit();
		}
	}

	public static void main(String[] args) {
		new Graph().createFrame();
	}
}