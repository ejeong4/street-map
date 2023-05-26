import java.util.*;

public class Djikstra extends Graph{
    
  Stack<Node> answer; //will end up with the final sequence of intersections to be outputted as directions, in reverse order
  PriorityQueue<Node> temp; //will store the directions
  Map<String, ArrayList<Node>> adjList;
    
  
    public Djikstra(){
        adjList = new HashMap<>();// creates hashmap to be used to for adjacency list
        answer = new Stack<>();
        Comparator<Node> compar = new compNode();// will be used to compare nodes to make temp priority queue, see compNode in Node.java
        temp = new PriorityQueue<>(10, compar);
    }


    public void relax(Node u, Node v, double w){ //taken from Dr. Zhupa's pseudocode in class
        if(v.d > u.d + w) {
            v.d = u.d + w;
            v.par = u;
        }
    }

    public void findPath(String int1, String int2){

        int found = 1; //will determine whether the intersections are even connected
        vertices.get(int1).d = 0; //sets the distance of the starting intersection (from the starting point) to 0
        temp.add(vertices.get(int1)); //adds the starting intersection to the temp queue
        
        while(temp.isEmpty()==false) {
          
            Node n1 = temp.poll(); //takes in the starting intersection
          
            if(n1.name.equals(int2)){ //If n1 ends up with the same name as int2, our destination vertex, that means there are roads connecting the two intersections
                found = 2;
                break;
            }
          
            n1.visited = true; //set each node as visited whenever we pass it
          
            for (Node n2: adjList.get(n1.name)) {
                if(!n2.visited) {
                  
                    temp.remove(n2); //remove nodes that aren't visited from our temporary queue
                    double w = haversine(n1.lat, n1.lon, n2.lat, n2.lon); //see haversine function from rosettacode in the Graph.java
                    relax(n1, n2, w);
                    temp.add(n2);
                }
            }
        }
      
        if(found != 2){ //if found was never changed to 2, that means the second intersection was never reached
          
            System.out.println("These intersections have no roads connecting them.");
        }
        
        else {
          
            Node n2 = vertices.get(int2);
          
            System.out.println("The distance is " + n2.d);
          
            while (n2 != null) {
              
                if (n2.name.equals(int1)) {
                    break;
                }
              
                answer.push(n2);
                Edge edge = new Edge(n2, n2.par);
                edges.add(edge);
                n2 = n2.par;
              
            }

          //this is where the directions are printed out in order from our answer stack
            System.out.print("Directions: "+int1);
            while (!answer.empty()) {
                System.out.print(" " + answer.pop().name);
            }
            System.out.println();

          
        }
    }//end of findPath

  public void updateAdjList(String int1, String int2){// adds new node to the adjList

    // Initiate arrays
        ArrayList<Node> array1 = new ArrayList<>();
        ArrayList<Node> array2 = new ArrayList<>();

        if(adjList.containsKey(int1)){ // found this method on GeeksforGeeks
            ArrayList<Node> old = adjList.get(int1);
            array1.addAll(old); //found the add all method on GeeksforGeeks
            array1.add(vertices.get(int2));
            adjList.replace(int1, array1); //found the replace method on GeeksforGeeks
        }
          
        else if(!adjList.containsKey(int1)){
            array1.add(vertices.get(int2));
            adjList.put(int1, array1);
        }
    
        if(adjList.containsKey(int2)){
            ArrayList<Node> old = adjList.get(int2);
            array2.addAll(old);
            array2.add(vertices.get(int1));
            adjList.replace(int2, array2);
        }
          
        else if(!adjList.containsKey(int2)){
            array2.add(vertices.get(int1));
            adjList.put(int2, array2);
        }
    
    }//end of updateAdjList
  

}