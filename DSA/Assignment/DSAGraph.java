/*
 * Title:     DSAGraph
 * Author:    Jacob Jonas, 18439731
 * Created:   05/09/2022
 * Modified:  20/09/2022
 * Assertion: Create my own graph object
 * Previously submitted for Practical 06 in COMP1002, Sem 2, 2022
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DSAGraph
{
    private BinarySearchTree vertices;
    private BinarySearchTree edges;

    // Constructor
    public DSAGraph()
    {
        vertices = new BinarySearchTree();
        edges = new BinarySearchTree();
    }

    //Copy constructor
    public DSAGraph(DSAGraph pGraph)
    {
        vertices = new BinarySearchTree(pGraph.getVertices());
        edges = new BinarySearchTree(pGraph.getEdges());
    }





    /*
     * Title:     DSAGraphVertex
     * Author:    Jacob Jonas, 18439731
     * Created:   05/09/2022
     * Modified:  05/09/2022
     * Assertion: Ceated a vertex object used in DSAGraph
     */

    private class DSAGraphVertex
    {
        // Class fields
        private String label;
        private Object value;
        private BinarySearchTree adjacent;
        private boolean visited;

        // Construcotr with parameters
        public DSAGraphVertex(String pLabel, Object pValue)
        {
            label = pLabel;
            value = pValue;
            adjacent = new BinarySearchTree();
            visited = false;
        }

    
    
    
    
    
       /*
        * Title:     getLabel
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    label (String)
        * Assertion: return the label value
        */

        public String getLabel()
        {
            return label;
        } // End getLabel()

    
    
    
    
    
       /*
        * Title:     getValue
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    value(Object)
        * Assertion: return the value
        */

        public Object getValue()
        {
            return value;
        } // End getValue()

        
        
        
        
        
       /*
        * Title:     getAdjacent
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    adjacent (BinarySearchTree)
        * Assertion: return the list of adjacent vertices
        */

        public BinarySearchTree getAdjacent()
        {
            return adjacent;
        } // End getAdjacent()

        
        
        
        
        
       /*
        * Title:     edgeList
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    edges (DSALinkedList)
        * Assertion: return a list containing all the edges connecting to this vertex
        */

        public BinarySearchTree edgeList()
        {
            DSALinkedList list = edges.inOrder();
            BinarySearchTree edgeList = new BinarySearchTree();
            DSAGraphEdge edge = null;

            for( Object o : list)
            {
                edge = (DSAGraphEdge)o;   
                
                if(edge.getLabel().contains(this.label))
                {
                    edgeList.insert(edge.getLabel(), edge);
                }
            }

            return edgeList;
        } // End edgeList()

    
    
    
    
    
       /*
        * Title:     setVisited
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    none
        * Assertion: Update visited status to true
        */

        public void setVisited()
        {
            visited = true;
        } // End setVisited()

        
        
        
        
        
       /*
        * Title:     clearVisited
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    none
        * Assertion: Reset visited status
        */

        public void clearVisited()
        {
            visited = false;
        } // End clearVisited()
        
        
        
        
        
        
       /*
        * Title:     getVisited
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    visited (boolean)
        * Assertion: return hte visited status
        */

        public boolean getVisited()
        {
            return visited;
        } // End getVisited()

    
    
    
    
    
       /*
        * Title:     toString
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    vertexString (String)
        * Assertion: return a string representation of the object
        */

        public String toString()
        {
            String vertexString = label;

            return vertexString;
        } // End toString()





      /*
       * Title:     getLookUp
       * Author:    Jacob Jonas, 18439731
       * Created:   07/09/2022
       * Modified:  07/09/2022
       * Import:    label (String)
       * Export:    ref (int)
       * Assertion: return the number correspinding to a vertex
       */

        public int getLookUp()
        {
            int ref = 0;

            String[][] arr = lookUp(); // Get the look up table for the graph
            
            for(int r = 0; r < arr.length; r++) // Iterate through the look up table
            {
                if(arr[r][1].equals(this.getLabel()))
                {
                    // Return the look up value of the node
                    ref = Integer.parseInt(arr[r][0]);
                }
            }
            return ref;
        }
        } // End DSAGraphVertex Class






    /*
     * Title:     DSAGraphEdge
     * Author:    Jacob Jonas, 18439731
     * Created:   05/09/2022
     * Modified:  05/09/2022
     * Assertion: Create an edge object used in DSAGraph
     */

     private class DSAGraphEdge
     {
        // Class fields
        private DSAGraphVertex from;
        private DSAGraphVertex to;
        private String label;
        private int weight;

        // Constructor with parameters
        public DSAGraphEdge(DSAGraphVertex pFrom, DSAGraphVertex pTo, int pWeight)
        {
            from = pFrom;
            to = pTo;
            label = pFrom.getLabel() + pTo.getLabel();
            weight = pWeight;
        }

        
        
        
        
        
       /*
        * Title:     getLabel
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    label (String)
        * Assertion: return the label
        */

        public String getLabel()
        {
            return label;
        }

        
        
        
        
        
       /*
        * Title:     getWeight
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    weight (int)
        * Assertion: return the weight value
        */

        public int getWeight()
        {
            return weight;
        }

        
        
        
        
        
       /*
        * Title:     getFrom
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    from (DSAGraphVertex)
        * Assertion: return the orgin of the edge
        */

        public DSAGraphVertex getFrom()
        {
            return from;
        }

        
        
        
        
        
       /*
        * Title:     getTo
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    to (DSAGraphVertex)
        * Assertion: return the end of the edge
        */

        public DSAGraphVertex getTo()
        {
            return to;
        }

        
        
        
        
        
       /*
        * Title:     isDirected
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    isDirected (boolean)
        * Assertion: return whether or not the edge is directed
        */

        public boolean isDirected()
        {
            // If an edge with the reverse label of this edge exists,
            // then it isn't directed

            String reverse = to.getLabel() + from.getLabel();

            boolean isDirected;

            try 
            {
                getEdge(reverse);
                isDirected = false;
            }
            catch(NoSuchElementException e)
            {
                isDirected = true;
            }

            return isDirected;
        }

    
    
    
    
    
       /*
        * Title:     toString
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    edgeString
        * Assertion: return a string representation of the object
        */

        public String toString()
        {
            String edgeString = label;

            return edgeString;
        }
     } // End DSAGraphEdge Class







   /*
    * Title:     isEmpty
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  05/09/2022
    * Import:    none
    * Export:    isEmpty (boolean)
    * Assertion: determine if the graph is empty
    */

    public boolean isEmpty()
    {
        boolean isEmpty = false;

        if(vertices.isEmpty()) // If there are no vertices in the Graph
        {
            isEmpty = true;
        }

        return isEmpty;
    }

    
    
    
    
    
    /*
     * Title:     getVertices
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  07/09/2022
     * Import:    none
     * Export:    vertices (DSALinkedList)
     * Assertion: Return the list of vertices
     */ 

     public BinarySearchTree getVertices()
     {
        return vertices;
     } // End getVertices
     
     
     
     
     
     /*
      * Title:     getEdges
      * Author:    Jacob Jonas, 18439731
      * Created:   07/09/2022
      * Modified:  07/09/2022
      * Import:    none
      * Export:    edges (DSALinkedList)
      * Assertion: Return the list of edges
      */

      public BinarySearchTree getEdges()
      {
        return edges;
      } // End getEdges
    
    
    
    
    
    /*
     * Title:     numVertices
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  07/09/2022
     * Import:    none
     * Export:    numVertices (int)
     * Assertion: return the number of vertices in the graph
     */

     public int numVertices()
     {
        int numVertices = 0;

        DSALinkedList v = vertices.inOrder(); 

        // Increment value for each object in the list
        for(Object o : v) 
        {
            numVertices++;
        }

        return numVertices;
     } // End numVertices

     
     
     
     
     
     /*
      * Title:     lookUp
      * Author:    Jacob Jonas, 18439731
      * Created:   07/09/2022
      * Modified:  20/09/2022
      * Import:    none
      * Export:    lookUp (String[][])
      * Assertion: return an int array with numbers corresponding to each vertex
      */

    public String[][] lookUp()
    {
    
        String[][] lookUp = new String[numVertices()][2];

        DSALinkedList v = vertices.inOrder();
        
        int r = 0; // increment for look up values

        for(Object o : v)
        {
            // Place each vertex in order next to it's corresponding look up value
            lookUp[r][0] = r + "";
            lookUp[r][1] = ((DSAGraphVertex)o).getLabel();
            r++; // next value
        }

        return lookUp;
    } // End lookup()

      
      
      




   /*
    * Title:     addVertex
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  04/10/2022
    * Import:    label (String), value (Object)
    * Export:    none
    * Assertion: add a vertex to the graph
    */

    public void addVertex(String label, Object value)
    {
        DSAGraphVertex newNd = new DSAGraphVertex(label, value);

        // Only add it if the vertex doesn't already exist
        if(!hasVertex(label))
        {
            vertices.insert(label, newNd);
        }
    } // End addVertex()

    
    
    
    
    
    /*
     * Title:     deleteVertex
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  20/09/2022
     * Import:    label (String)
     * Export:    none
     * Assertion: Delete a vertex from the graph
     */

    public void deleteVertex(String label)
    {
        // Remove the vertex from the vertices Tree
        vertices.delete(label);

        // Remove all adges that conatin this vertex
        deleteVertexEdges(label);

        // Delete this vertex from any relevant adjacency lists
        deleteVertexAdjacents(label);
    } // End deleteVertex()

    
    
    
    
    
    /*
     * Title:     deleteVertexEdges
     * Author:    Jacob Jonas, 18439731
     * Created:   13/09/2022
     * Modified:  20/09/2022
     * Import:    label (String)
     * Export:    none
     * Assertion: remove all edges containing an imported vertex
     */

    private void deleteVertexEdges(String label)
    {
        DSALinkedList e = edges.inOrder();

        for (Object o : e) 
        {
            DSAGraphEdge temp = (DSAGraphEdge)o;

            // If the vertex is invloved in the current edge, then delete the edge
            if((temp.getFrom().getLabel().equals(label)) || (temp.getTo().getLabel().equals(label)))
            {
                edges.delete(temp.getLabel());
            }
        }
    } // End deleteVertexEdges()

    
    
    
    
    
    /*
     * Title:     deleteVertexAdjacents
     * Author:    Jacob Jonas, 18439731
     * Created:   13/09/2022
     * Modified:  20/09/2022
     * Import:    label (String)
     * Export:    none
     * Assertion: Delete a removed vertex from other nodes adjaceny lists
     */

    private void deleteVertexAdjacents(String label)
    {
        DSALinkedList v = vertices.inOrder();

        // go through every vertex
        for (Object object : v) 
        {
            DSALinkedList adjacent = ((DSAGraphVertex)object).getAdjacent().inOrder();

            for (Object a : adjacent)
            {
                DSAGraphVertex temp = (DSAGraphVertex)a;

                // If the vertex matches rhe imported vertex then remove it from the adjacency list
                if(temp.getLabel().equals(label))
                {
                    ((DSAGraphVertex)object).getAdjacent().delete(temp.getLabel());
                }
            }
        }
    } // End deleteVertexAdjacent()






   /*
    * Title:     addEdge
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  20/09/2022
    * Import:    label1 (String), label2 (String), weight (int)
    * Export:    none
    * Assertion: add an edge between two vertices 
    */

    public void addEdge(String label1, String label2, int weight)
    {
        DSAGraphVertex from = this.getVertex(label1);
        DSAGraphVertex to = this.getVertex(label2);

        if(hasEdge(label1 + label2))
        {
            System.out.println("Edge already exists in the graph");
        }
        else
        {
            // Add the "to" node to the "from"'s adjacency list
            from.getAdjacent().insert(label2, to);

            DSAGraphEdge newEdge = new DSAGraphEdge(from, to, weight);

            edges.insert(newEdge.getLabel(), newEdge); // Add edge to list of edges
        }
    } // End addEdge()

    
    
    
    
    
    /*
     * Title:     deleteEdge
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  20/09/2022
     * Import:    none
     * Export:    none
     * Assertion: remove an edge from the graph
     */

    public void deleteEdge(String label)
    {
        DSAGraphEdge temp = getEdge(label);

        // Delete "to" vertex in "from"'s adjaceny list
        temp.getFrom().getAdjacent().delete(temp.getTo().getLabel());

        edges.delete(label);
    } // End deleteEdge()






   /*
    * Title:     hasVertex
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  05/09/2022
    * Import:    label (String)
    * Export:    hasVertex (boolean)
    * Assertion: determine if the graph contains a vertex with the imported label
    */

    public boolean hasVertex(String label)
    {
        boolean hasVertex;

        // Checks if the node can be gotten from the vertices field
        try
        {
            getVertex(label);
            hasVertex = true;
        }
        catch(NoSuchElementException e)
        {
            hasVertex = false;
        }

        return hasVertex;
    } // End hasVertex()

    
    
    
    
    
   /*
    * Title:     getVertex
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  05/09/2022
    * Import:    label (String)
    * Export:    vertex DSAGraphVertex
    * Assertion: return the vertex with the matching imported label
    */

    public DSAGraphVertex getVertex(String label)
    {
        // Attempt to find the vertex in the vertices class field
        DSAGraphVertex vertex = (DSAGraphVertex)vertices.find(label);

        // Expection is not caught here to allow for 
        // handling in the program that is using the DSAGraph
           
        return vertex;
    } // End getVertex()






   /*
    * Title:     hasEdge
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  05/09/2022
    * Import:    label (String)
    * Export:    hasEdge (boolean)
    * Assertion: determine if the edge is in the grap
    */

    public boolean hasEdge(String label)
    {
        boolean hasEdge;

        // Checks if the edge can be gotten from the edges class field
        try
        {
            getEdge(label);
            hasEdge = true;
        }
        catch(NoSuchElementException e)
        {
            hasEdge = false;
        }

        return hasEdge;
    } // End hasEdge()

    

    
    
    
    
    
   /*
    * Title:     getEdge
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  05/09/2022
    * Import:    label (String)
    * Export:    edge (DSAGraphEdge)
    * Assertion: return the edge with the corresponding label
    */

    public DSAGraphEdge getEdge(String label)
    {
        // Attempt to find the edge in the edges class field
        DSAGraphEdge edge = (DSAGraphEdge)edges.find(label);

        // Expection is not caught here to allow for 
        // handling in the program that is using the DSAGraph
       
        return edge;       
    } // End getEdge()






   /*
    * Title:     breadthFirstSearch
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  04/10/2022
    * Import:    none
    * Export:    none
    * Assertion: perform a breadth first search on the graph
    */

    public void breadthFirstSearch()
    {
        DSALLQueue T = new DSALLQueue(); // Holds the traversed path
        DSALLQueue Q = new DSALLQueue();
    
        // Convert the BinarySearchTree into a DSALinkedList for easier iteration
        DSALinkedList verts = vertices.inOrder();

        for(Object o : verts) // Mark every vertex as unvisited
        {
            DSAGraphVertex ver = (DSAGraphVertex)o;
            ver.clearVisited();
        }

        // Start at the alphabetically first vertex
        DSAGraphVertex v = (DSAGraphVertex)verts.peekFirst();

        Q.enqueue(v); 
        v.setVisited(); // Start node 

        while(!Q.isEmpty()) // Keep goinf until no more unvisited nodes
        {
            v = (DSAGraphVertex)Q.dequeue(); // "Current" node in the search

            // Get the current nodes adjacencies
            DSALinkedList adjacent = (v.getAdjacent()).inOrder();
    
            for(Object o : adjacent) // For all adjacent nodes
            {
                DSAGraphVertex w = (DSAGraphVertex)o;

                if(!w.getVisited()) // The node hasn't been visited in the search
                {
                    // Create a new edge with the two nodes
                    DSAGraphEdge e = new DSAGraphEdge(v,w,0);
                    T.enqueue(e); // Place the edge in the traversed Queue
                    w.setVisited(); // Set the adjacent node as visited
                    Q.enqueue(w); // Enqueue it to be used as a "current" node later
                }
            }  
        }

        // Print out the contents of the traversed path
        while(!T.isEmpty())
        {
            System.out.println(T.dequeue());
        }
    } // End breadthFirstSearch()






   /*
    * Title:     depthFirstSearch
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  04/10/2022
    * Import:    none
    * Export:    none
    * Assertion: perform a depth first search on the graph
    */

    public void depthFirstSearch()
    {
        DSALLQueue T = new DSALLQueue(); // Holds the traversed path
        DSALLStack S = new DSALLStack();

        DSALinkedList verts = vertices.inOrder();

        // Mark every vertex as unvisited
        for(Object o : verts)
        {
            DSAGraphVertex ver = (DSAGraphVertex)o;
            ver.clearVisited();
        }

        // Start at the alphabetically first vertex
        DSAGraphVertex v = (DSAGraphVertex)verts.peekFirst();

        S.push(v);
        v.setVisited();
        while(!S.isEmpty())
        {
            DSALinkedList adjacent = (v.getAdjacent()).inOrder();
            Iterator iter = adjacent.iterator();

            DSAGraphVertex w = (DSAGraphVertex)iter.next();

            while(iter.hasNext())
            {
                if(!w.getVisited())
                {
                    DSAGraphEdge e = new DSAGraphEdge(v,w,0);
                    T.enqueue(e);
                    w.setVisited();
                    S.push(w);
                    v = w;

                    adjacent = (v.getAdjacent()).inOrder();
                    iter = adjacent.iterator();  
                    w = (DSAGraphVertex)iter.next();

                    // Keep updating w until the node hasn't been visited
                    while((iter.hasNext()) && (w.getVisited()))
                    {
                        w = (DSAGraphVertex)iter.next();
                    }   
                }
                else // Node was visited
                {
                    w = (DSAGraphVertex)iter.next(); // Move to next node in adjacency list
                }                      
            }

            // Reached the last node in the list and it hasn't been visited
            if((w != null) && (!w.getVisited()))
            {
                DSAGraphEdge e = new DSAGraphEdge(v,w,0);
                T.enqueue(e);
                w.setVisited();
                S.push(w);
                v = w;
            }
            else // Get the next node for the next iteration
            {
                v = (DSAGraphVertex)S.pop();
            }
            
        }
        
        // Print out the contents of the traversed path
        while(!T.isEmpty())
        {
            System.out.println(T.dequeue());
        }
    } // End depthFirstSearch()

    
    
    
    
    
    /*
     * Title:     createMatrix
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  20/09/2022
     * Import:    none
     * Export:    matrix (int[][])
     * Assertion: Display the graph as an adjacency matrix
     */

     public int[][] createMatrix()
     {   
        // Initailise matrix with size of the number of vertices plus 1 to account for headers
        int[][] matrix = new int[numVertices()+1][numVertices()+1];
        int v = 1; // Used to index matrix

        DSALinkedList verts = vertices.inOrder();

        for(Object o : verts) 
        {
            DSAGraphVertex node = (DSAGraphVertex)o;

            // Place the headers
            matrix[0][v] = node.getLookUp();
            matrix[v][0] = node.getLookUp();
            v++; // Ready for next vertex

            DSALinkedList adjacent = (node.getAdjacent()).inOrder();

            // For each adjacent node, place a 1 at the intersect location
            // Indicating the nodes are adjacent
            for (Object a : adjacent) 
            {
                // Row of the parent vertex and the column of the adjacent vertex
                // Plus 1 to account fo headers
                matrix[node.getLookUp() + 1][((DSAGraphVertex)a).getLookUp() + 1] = 1;
            }
        }

        return matrix;
     } // End createMatrix()

     
     
     
     
     
     /*
      * Title:     adjacencyList
      * Author:    Jacob Jonas, 18439731
      * Created:   07/10/2022
      * Modified:  07/10/2022
      * Import:    none
      * Export:    none
      * Assertion: Display the graph as an adjacency list
      */
     
     public void adjacencyList()
     {
        DSALinkedList verts = vertices.inOrder();

        for (Object node : verts) 
        {
            DSAGraphVertex vertex = (DSAGraphVertex)node;
            DSALinkedList adjacencies = vertex.getAdjacent().inOrder();

            System.out.print(vertex + " |\t");
            
            for (Object adj : adjacencies) 
            {
                System.out.print(adj + " ");
            }
            System.out.println();
        }
     }

     
     
     
     
     
     /*
      * Title:     displayAsMatrix
      * Author:    Jacob Jonas, 18439731
      * Created:   07/09/2022
      * Modified:  20/09/2022
      * Import:    none
      * Export:    none
      * Assertion: print out the adjacency matrix
      */

    public void displayAsMatrix()
    {
        int[][] matrix = this.createMatrix(); // Create the matrix

        // Standrad nested for loop to print out 2-D array
        for(int r = 0; r < matrix.length; r++)
        {
            for(int c = 0; c < matrix.length; c++)
            {
                // Format so that numbers line up properly when double and triple digits get invloved
                System.out.format("%3d", matrix[r][c]);
            }
            System.out.println(""); // Print each row on a new line
        }
    } // End displayAsMatrix()

      
      
      
      
      
   /*
    * Title:     findPaths
    * Author:    Jacob Jonas, 18439731
    * Created:   09/09/2022
    * Modified:  05/10/2022
    * Import:    from (String), to (String), max (int)
    * Export:    allPaths (DSALinkedList)
    * Assertion: find the imported number paths between two nodes using a BFS algorithm
    */

    public DSALinkedList findPaths(String from, String to, int max)
    {
        DSALLQueue Q = new DSALLQueue();
        DSALinkedList allPaths = new DSALinkedList();
        DSALinkedList path = new DSALinkedList();
        DSALinkedList verts = vertices.inOrder();

        // Mark every vertex as unvisited ensure base states
        for(Object o : verts)
        {
            DSAGraphVertex ver = (DSAGraphVertex)o;
            ver.clearVisited();
        }
    
        DSAGraphVertex v = getVertex(from); // Create the start vertex
        vertices.find(to); // Ensure the end node exists in the graph
        
        if(from.equals(to)) // Start and end location are the same
        {
            // Only one path that only contains the start node
            path.insertLast(v);
            allPaths.insertLast(path);
        }
        else // Search for paths
        {
            path.insertLast(v); // Path begins at the start node

            Q.enqueue(path);

            while((!Q.isEmpty()) && (allPaths.getCount() < max))
            {
                // Mark every vertex as unvisited each iteration
                for(Object o : verts)
                {
                    DSAGraphVertex ver = (DSAGraphVertex)o;
                    ver.clearVisited();
                }

                // Get the next path to check if it's at the destination or needs to keep searching
                path = (DSALinkedList)Q.dequeue();

                // Mark every vertex in the current path as visited
                for(Object n : path)
                {
                    DSAGraphVertex node = (DSAGraphVertex)n;
                    node.setVisited(); 
                }

                // Start the iteration at the end of the current path
                DSAGraphVertex last = (DSAGraphVertex)path.peekLast();
                
                if(last.getLabel().equals(to)) // Reached the destination
                {
                    allPaths.insertLast(path); // Insert the current path into the overall list of paths
                }
                else // Keep searching
                {
                    // Get the adjacent nodes for the current node
                    DSALinkedList adjacent = last.getAdjacent().inOrder();

                    for(Object o : adjacent)
                    {
                        DSAGraphVertex w = (DSAGraphVertex)o;

                        if(!w.getVisited()) // If the adjacent vertex isn't in the current path
                        {
                            DSALinkedList newPath = new DSALinkedList();

                            // Essentially copying path to newPath
                            for(Object u : path)
                            {
                                newPath.insertLast(u);
                            } // I tried using a copy constructor but was getting stackoverflow errors

                            // Add the adjacent vertex to newPath
                            newPath.insertLast(w); 
                            Q.enqueue(newPath); // Add path to the queue
                        }
                    }
                }
            }
        }

        return allPaths;
    } // End findPaths()
}
