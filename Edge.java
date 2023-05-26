public class Edge implements Comparable<Edge>{
  Node start;
  Node end;
  String name;
  double width;

  public Edge(Node start, Node end) {
    this.start = start;
    this.end = end;
  }

  public Edge(String name, Node start, Node end, double width) {
    this.start = start;
    this.end = end;
    this.name = name;
    this.width = width;
  }
  
    @Override 
    public int compareTo(Edge o) {
        return (int)(this.width - o.width);
    }
}