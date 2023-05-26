import java.io.*;
import java.util.ArrayList;

class StreetMap {

  public static void main(String[] args) throws IOException {

    // Create list of commands
    ArrayList<String> commands = new ArrayList<>();
    
    boolean toShow = false; // will determine whether the map is shown
    boolean toDirect = false; // will determine whether directions are shown
    
    Graph g = new Graph();
    Djikstra djik = new Djikstra(); //makes new djikstra element

    // Add arguments to commands array
    for (int i = 0; i < args.length; i++) {
      commands.add(args[i]);
    }

    // Create Buffered Reader
    BufferedReader br = new BufferedReader(new FileReader(args[0]));
    String line = null;
    while ((line = br.readLine()) != null) {
      // Split line on whitespaces
      String[] lineArr = line.split("\\s+");

      if (lineArr[0].equals("r")) { // road (edge)
        g.addEdge(lineArr[1], lineArr[2], lineArr[3]);// add edge to graph
        
      } else if (lineArr[0].equals("i")) { // intersection (vertex)
        g.addVertex(lineArr[1], Double.parseDouble(lineArr[2]), Double.parseDouble(lineArr[3])); // add vertex to graph

      }
    } // end of while loop

    if (commands.contains("--show")) {
      toShow = true;
      commands.remove("--show");
    }
    if (commands.contains("--directions")) {
      toDirect = true;
      commands.remove("--directions");
    }

    // This loop carries out the process by which directions are printed out (if
    // asked for)
    if (toDirect == false) {
      SMapGUI gui = new SMapGUI(g); // if directions are not requested, simply print out the map graph
    } else {

      // Outputs error if there is no intersection name inputted to the command line
      if (commands.get(1) == null) {
        System.out.println("Error: No Intersections Provided");
      }

      // Stores the two intersections we are searching for directions between into two
      // String variables
      String int1 = commands.get(1); // first intersection
      String int2 = commands.get(2); // second intersection

      if (g.vertices.containsKey(int1) == false || g.vertices.containsKey(int2) == false) { // catches the error when
                                                                                            // one or both of the
                                                                                            // inputted intersections
                                                                                            // are not present in the
                                                                                            // inputted map
        System.out.println("One or more of the inputted intersections is nonexistent.");
      } else {
        br = new BufferedReader(new FileReader(args[0]));
        line = null; //start br and line over again
        while ((line = br.readLine()) != null) {
          // Split line on whitespaces again
          String[] lineArr = line.split("\\s+");
          if (lineArr[0].equals("i")) { // intersection
            djik.addVertex(lineArr[1], Double.parseDouble(lineArr[2]), Double.parseDouble(lineArr[3])); // parameters
                                                                                                        // are
                                                                                                        // intersection
                                                                                                        // name,
                                                                                                        // latitude,
                                                                                                        // longitude
          } else { // lineArr[0] would be r, signifying a road
            djik.updateAdjList(lineArr[2], lineArr[3]); // takes just latitude and longitude
          }
        } // end of while
      }

      // FIND THE SHORTEST PATH USING DJIKSTRA THEN OUTPUT IT
      djik.findPath(int1, int2);
      SMapGUI gui = new SMapGUI(g, djik, int1, int2);

    }

  } // end of main

} // end of streetmap class
