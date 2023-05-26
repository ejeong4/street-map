import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class SMapGUI extends JFrame {
  
  public SMapGUI(Graph graph, Graph direction, String start, String end) {
    Canvas c = new Canvas(graph, direction, start, end);

    setTitle("Map");
    setSize(1000, 1000);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(c);
  }

  public SMapGUI(Graph graph) {
    Canvas c = new Canvas(graph);

    setTitle("Map");
    setSize(1000, 1000);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(c);
  }

  class Canvas extends JComponent {
    
    String start;
    String end;
    Graph graph;
    Graph direction;

    public Canvas(Graph graph) {
      
      this.graph = graph;
      
    }

    public Canvas(Graph graph, Graph direction, String start, String end) {
      this.start = start;
      this.end = end;
      this.graph = graph;
      this.direction = direction;
      
    }

    public double[] coordinates(Graph graph) {
      
      // Initialize array for values
      double[] coords = new double[4];
      // Initialize min/max values
      double y1 = Double.NEGATIVE_INFINITY;
      double x1 = Double.POSITIVE_INFINITY;
      double y2 = Double.POSITIVE_INFINITY;
      double x2 = Double.NEGATIVE_INFINITY;
      
      // Get extreme coordinates
      for (String x : graph.vertices.keySet()) {
        Node temp = graph.vertices.get(x);

        if (temp.lat > y1) {
          y1 = temp.lat;
        }
        if (temp.lat < y2) {
          y2 = temp.lat;
        }
        if (temp.lon < x1) {
          x1 = temp.lon;
        }
        if (temp.lon > x2) {
          x2 = temp.lon;
        }
      }
      // Set coordinate array values
      coords[0] = y1;
      coords[1] = y2;
      coords[2] = x1;
      coords[3] = x2;

      return coords;
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      // Get size of window
      double width = getWidth() - 25;
      double height = getHeight() - 25;
      // Get coordinates
      double[] coords = coordinates(graph);
      double y1 = coords[0];
      double x1 = coords[2];
      // Get distance
      double distY = coords[0] - coords[1];
      double distX = coords[2] - coords[3];

      // Draw map
      for (Edge e : graph.edges) {
        double Y1 = (y1 - e.start.lat) / distY * height;
        double X1 = (x1 - e.start.lon) / distX * width;
        double Y2 = (y1 - e.end.lat) / distY * height;
        double X2 = (x1 - e.end.lon) / distX * width;
        g2.draw(new Line2D.Double(X1, Y1, X2, Y2));
      }

      // For direction lines
      if (direction != null) {
        g2.setColor(Color.BLUE); //makes the edges given in the directions blue...
        g2.setStroke(new BasicStroke(6)); //...and larger in stroke size
        // Draw lines
        for (Edge e : direction.edges) {
          double Y1 = (y1 - e.start.lat) / distY * height;
          double X1 = (x1 - e.start.lon) / distX * width;
          double Y2 = (y1 - e.end.lat) / distY * height;
          double X2 = (x1 - e.end.lon) / distX * width;
          g2.draw(new Line2D.Double(X1, Y1, X2, Y2));
        }

  }
}
  } //end of Canvas

} //SMApGUI end
