import java.util.*;

public class Graph {
  Map<String, Node> vertices;
  PriorityQueue<Edge> edges;

  public Graph() {
    vertices = new HashMap<>();
    edges = new PriorityQueue<>();
  }

  public void addVertex(String name, double lat, double lon) {
    vertices.put(name, new Node(name, lat, lon));
  }

  public void addEdge(String name, String start, String end) {
    Node Start = vertices.get(start);
    Node End = vertices.get(end);

    double width = haversine(Start.lat, Start.lon, End.lat, End.lon);

    edges.add(new Edge(name, Start, End, width));
  }

  public void addEdge(Node start, Node end) {
    edges.add(new Edge(start, end));
  }

  // SOURCE: https://rosettacode.org/wiki/Haversine_formula#Java
  public static double haversine(double lat1, double lon1, double lat2, double lon2) {
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);
    double dLat = lat2 - lat1;
    double dLon = Math.toRadians(lon2 - lon1);

    double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
    double c = 2 * Math.asin(Math.sqrt(a));
    return 6372.8 * c;
  }

}
