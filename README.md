# street-map
Partners: Emily Jeong, Harini Prabaharan
Contact Info: ejeong4@u.rochester.edu, hprabaha@u.rochester.edu

Synopsis:
  The StreetMap class contains our main method, in which the file is taken in and the commands are processed to correctly output a graph and/or directions, whichever is requested. It makes use of the Graph.java, which contains the Graph class and the Djikstra class (which extends Graph). Importantly, the Graph class has a PriorityList called edges to store the edges in order of their weight (which is distance in this scenario), which was calculated using a haversine method from RosettaCode. This is helpful to the Djikstra class. There is also a compNode method in Node that helps order the edges after new ones are added.
  The Djikstra class class has a HashMap called adjList that is of type Map<String, ArrayList<Edge>> in order to store the vertex name as a string and all its adjacent vertices in the arraylist. The findPath method in the Djikstra class is vital, as it is what actually finds the shortest path between the two desired intersections. 
  The GUI class contains the methods to display the maps, including paintComponent method to display edges and vertices of a map. The GUI class also contains methods to create a JFrame and apply basic characteristics of a window such as size, closing operation and more. We also added a method that calculates the coordinates of the map, which are then used to draw edges and vertices.

Obstacles: 
  It was slightly challenging to work with so many classes, because once we divided up the work, each of us created different variable names. We had to change many names in order to avoid overlap, and it was difficult to switch back and forth between files to find what we needed from each other's classes. 
  The findPath method in the Djikstra class was the most difficult aspect to implement for us, as it took quite a while to determine the correct order of declarations and loops. It was also exasperating because we spent copious time debugging it, and the error turned out to be a simple n2 in the place of an n1. 
  We had an error that said none of the intersections were reachable from one another for quite a while, and debugged this for upwards of 6 hours. Now, that functionality works. 
  We also had an error in the SMapGUI error for drawing edges -- we had a for loop that iterated through the graph's edges rather than the direction's edges, which was a careless mistake that took hours to debug. Until we fixed it, it highlighted the entire graph in blue lines. 

Division of Workload: 
  Harini worked on the Djikstra class, while Emily did the Graph and SMapGUI. We did the StreetMap class together, with Harini doing the latter half, while Emily began it. However, we were next to each other for most of it and therefore helped each other with much of the entire project.

Runtime: We think the runtime for showing the map in O(# of nodes), and that the runtime for the Djikstra's algorith is O(# of edges).
