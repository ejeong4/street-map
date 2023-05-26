import java.io.*;
import java.lang.*;
import java.util.*;

public class Node {
  String name;
  double lat; //latitude of the node
  double lon; //longitude of the node
  boolean visited; //represents whether this node has been visited, used in Djikstra's alg
  double d = Double.MAX_VALUE; //sets d = to infinity; idea from Dr. Zhupa's pseudocode for Initialize-Single-Souce(G,s)
  Node par; //serves as the pi variable (parent) from Dr. Zhupa's pseudocode for Initialize-Single-Souce(G,s)
  
  public Node(String name, double lat, double lon) {
    this.name = name;
    this.lat = lat;
    this.lon = lon;
  }
  
}

class compNode implements Comparator<Node> { 
    @Override
    public int compare(Node a, Node b){ //compares the distances of each node, returning 1 if the first one is larger
      if (a.d>b.d){ return 1; }
      else if (a.d>b.d){ return -1; }
      else { return 0; }
    }
  }



