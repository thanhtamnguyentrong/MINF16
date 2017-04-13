import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Hashtable;

public class ShapeCache {
	private static Hashtable<Integer, RectangularShape> shapeMap = new Hashtable<Integer, RectangularShape>();
	
	public static RectangularShape getShape(int shapeId) {
		RectangularShape cachedShape = shapeMap.get(shapeId);
		return (RectangularShape) cachedShape.clone();	
	}
	
	public static void loadCache(double baseRadius) {
	      Ellipse2D smallCircle = new Ellipse2D.Double(0,0,baseRadius*2,baseRadius*2);
	      shapeMap.put(1,smallCircle);

	      Ellipse2D bigCircle = new Ellipse2D.Double(0,0,baseRadius*2*2,baseRadius*2*2);
	      shapeMap.put(2,bigCircle);
	      
	      Rectangle2D smallSquare = new Rectangle2D.Double(0,0,baseRadius*2, baseRadius*2);
	      shapeMap.put(3,smallSquare);
	      
	      Rectangle2D bigSquare = new Rectangle2D.Double(0,0,baseRadius*2*2, baseRadius*2*2);
	      shapeMap.put(4,bigSquare);
	}
}
