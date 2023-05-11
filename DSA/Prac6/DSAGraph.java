/*
 * Title:     DSAGraph
 * Author:    Jacob Jonas, 18439731
 * Created:   05/09/2022
 * Modified:  05/09/2022
 * Assertion: Create my own graph object
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DSAGraph
{
    private DSALinkedList vertices;
    private DSALinkedList edges;

    // Constructor
    public DSAGraph()
    {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
    }





    /*
     * Title:     DSAGraphVertx
     * Author:    Jacob Jonas, 18439731
     * Created:   05/09/2022
     * Modified:  05/09/2022
     * Assertion: Ceated a vertex object used in DSAGraph
     */
    private class DSAGraphVertex
    {
        private String label;
        private Object value;
        private DSALinkedList adjacent;
        private boolean visited;

        // Constructor
        public DSAGraphVertex(String pLabel, Object pValue)
        {
            // Class fields take on the values of the imported parameters
            label = pLabel;
            value = pValue;
            adjacent = new DSALinkedList();
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
        }

    
    
    
    
    
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
        }

        
        
        
        
        
       /*
        * Title:     getAdjacent
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    adjacent (DSALinkedList)
        * Assertion: return the list of adjacent vertices
        */

        public DSALinkedList getAdjacent()
        {
            return adjacent;
        }

        
        
        
        
        
       /*
        * Title:     edgeList
        * Author:    Jacob Jonas, 18439731
        * Created:   05/09/2022
        * Modified:  05/09/2022
        * Import:    none
        * Export:    edges (DSALinkedList)
        * Assertion: return a list containing all the edges connecting to this vertex
        */

        public DSALinkedList edgeList()
        {
            // List hold the edges containg this vertex
            DSALinkedList edgeList = new DSALinkedList();
            DSAGraphEdge edge = null;

            for( Object o : edges)
            {
                edge = (DSAGraphEdge)o; // Typecast  
                
                // If the current edge contains the vertex
                if(edge.getLabel().contains(this.getLabel()))
                {
                    edgeList.insertLast(edge); // Add edge to the edges list
                }
            }

            return edgeList;
        }

    
    
    
    
    
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
        }

        
        
        
        
        
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
        }
        
        
        
        
        
        
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
        }

    
    
    
    
    
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
            String vertexString = label +  ", " + value;

            return vertexString;
        }





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
        String[][] arr = lookUp();
        
        // Runthrough the look up table
        for(int r = 0; r < arr.length; r++)
        {
             // Check for the look up value       
            if(arr[r][1].equals(this.getLabel()))
            {
                // Typecast and return the value when found
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
        private DSAGraphVertex from;
        private DSAGraphVertex to;
        private String label;
        private int weight;

        // Constructor
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
            String reverse = to.getLabel() + " " + from.getLabel();

            boolean isDirected;

            try 
            {
                // Edge is undirected if an edge in the other direction exists
                DSAGraph.this.getEdge(reverse);
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
            String edgeString = label + "," + weight;

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
        
        // No vertices in the list means the graph has no vertices
        if(vertices.isEmpty())
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

     public DSALinkedList getVertices()
     {
        return vertices;
     }

     
     
     
     
     
     /*
      * Title:     getEdges
      * Author:    Jacob Jonas, 18439731
      * Created:   07/09/2022
      * Modified:  07/09/2022
      * Import:    none
      * Export:    edges (DSALinkedList)
      * Assertion: Return the list of edges
      */

      public DSALinkedList getEdges()
      {
        return edges;
      }
    
    
    
    
    
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
        return vertices.getCount();
     }

     
     
     
     
     
     /*
      * Title:     lookUp
      * Author:    Jacob Jonas, 18439731
      * Created:   07/09/2022
      * Modified:  07/09/2022
      * Import:    none
      * Export:    lookUp (String[][])
      * Assertion: return a String array with numbers corresponding to each vertex
      */

      public String[][] lookUp()
      {
        String[][]lookUp = new String[numVertices()][2];

        vertices.sortList();
        
        int r = 0;

        for(Object o : vertices)
        {
            lookUp[r][0] = r + ""; // Add the index
            lookUp[r][1] = ((DSAGraphVertex)o).getLabel();
            r++;
        }

        return lookUp;
      }

      
      
      




   /*
    * Title:     addVertex
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  05/09/2022
    * Import:    label (String), value (Object)
    * Export:    none
    * Assertion: add a vertex to the graph
    */

    public void addVertex(String label, Object value)
    {
        DSAGraphVertex newNd = new DSAGraphVertex(label, value);

        if(hasVertex(label))
        {}
        else
        {
            vertices.insertLast(newNd);
        }
    }






   /*
    * Title:     addEdge
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  05/09/2022
    * Import:    label1 (String), label2 (String), weight (int)
    * Export:    none
    * Assertion: add an edge between two vertices 
    */

    public void addEdge(String label1, String label2, int weight)
    {
        DSAGraphVertex from = this.getVertex(label1);
        DSAGraphVertex to = this.getVertex(label2);

        if(hasEdge(label1+label2))
        {
            System.out.println("Edge already exists in the graph");
        }
        else
        {
            // Add each node to the other's adjacency list
            from.adjacent.insertLast(to);

            DSAGraphEdge newEdge = new DSAGraphEdge(from, to, weight);

            edges.insertLast(newEdge); // Add edge to list of edges
        }
 
    }






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
    }

    
    
    
    
    
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
        DSAGraphVertex vertex = null;
        
        for(Object o : vertices)
        {
            vertex = (DSAGraphVertex)o;
            if(label.equals(vertex.getLabel()))
            {
                return vertex;
            }
        }
        
        throw new NoSuchElementException("Vertex: " + label + " does not exist in the graph");
       
    }






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
    }

    

    
    
    
    
    
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
        DSAGraphEdge edge = null;

        for(Object o : edges)
        {
            edge = (DSAGraphEdge)o;

            if(label.equals(edge.getLabel()))
            {
                return edge;
            }
        }
        throw new NoSuchElementException("Edge: " + label + " does not exist in the graph");
        
    }






   /*
    * Title:     breadthFirstSearch
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  05/09/2022
    * Import:    none
    * Export:    none
    * Assertion: perform a breadth first search on the graph
    */

    public void breadthFirstSearch()
    {
        DSALLQueue T = new DSALLQueue();
        DSALLQueue Q = new DSALLQueue();
    
        vertices.sortList(); // Sort alphabetically

        for(Object o : vertices)
        {
            DSAGraphVertex ver = (DSAGraphVertex)o;
            ver.clearVisited(); // Set all nodes to unvisited
        }

        // Choose a node to start from
        DSAGraphVertex v = (DSAGraphVertex)vertices.peekFirst();

        Q.enqueue(v);
        v.setVisited();
        while(!Q.isEmpty())
        {
            v = (DSAGraphVertex)Q.dequeue();
            DSALinkedList adjacent = v.getAdjacent();
            adjacent.sortList();
            Iterator iter = adjacent.iterator();
    
            while(iter.hasNext())
            {
                DSAGraphVertex w = (DSAGraphVertex)iter.next();

                if(!w.getVisited()) // Visited all adjacent nodes
                {
                    T.enqueue(v);
                    T.enqueue(w);
                    w.setVisited();
                    Q.enqueue(w);
                }
            }  
        }

        int count = 0;

        while(!T.isEmpty())
        {
            if(count < 2)
            {
                // Print out items in groups of two
                System.out.print(((DSAGraphVertex)(T.dequeue())).getLabel());
                count++;
            }
            else
            {
                System.out.println("");
                count = 0;
            }
        }
        System.out.println("");

        
    }






   /*
    * Title:     depthFirstSearch
    * Author:    Jacob Jonas, 18439731
    * Created:   05/09/2022
    * Modified:  05/09/2022
    * Import:    none
    * Export:    none
    * Assertion: perform a depth first search on the graph
    */

    public void depthFirstSearch()
    {
        DSALLQueue T = new DSALLQueue();
        DSALLStack S = new DSALLStack();

        vertices.sortList();

        for(Object o : vertices)
        {
            DSAGraphVertex ver = (DSAGraphVertex)o;
            ver.clearVisited();
        }

        DSAGraphVertex v = (DSAGraphVertex)vertices.peekFirst();

        S.push(v);
        v.setVisited();
        while(!S.isEmpty())
        {
            DSALinkedList adjacent = v.getAdjacent();
            adjacent.sortList();
            Iterator iter = adjacent.iterator();

            DSAGraphVertex w = (DSAGraphVertex)iter.next();

        
            while(iter.hasNext())
            {
                if(!w.getVisited())
                {
                    T.enqueue(v);
                    T.enqueue(w);
                    w.setVisited();
                    S.push(w);
                    v = w;

                    adjacent = v.getAdjacent();
                    iter = adjacent.iterator();  
                    w = (DSAGraphVertex)iter.next();
                    while((iter.hasNext()) && (w.getVisited()))
                    {
                        w = (DSAGraphVertex)iter.next();
                    }    
                }
                else
                {
                    w = (DSAGraphVertex)iter.next();
                }                      
            }

            if((w != null) && (!w.getVisited()))
            {
                T.enqueue(v);
                T.enqueue(w);
                w.setVisited();
                S.push(w);
                v = w;
            }
            else
            {
                v = (DSAGraphVertex)S.pop();
            }
            
        }

        int count = 0;
        
        while(!T.isEmpty())
        {
            if(count < 2)
            {
                System.out.print(((DSAGraphVertex)(T.dequeue())).getLabel());
                count++;
            }
            else
            {
                System.out.println("");
                count = 0;
            }            
        }
        System.out.println("");
    }

    
    
    
    
    
    /*
     * Title:     createMatrix
     * Author:    Jacob Jonas, 18439731
     * Created:   07/09/2022
     * Modified:  09/09/2022
     * Import:    none
     * Export:    matrix (int[][])
     * Assertion: Create an adjacency matrix for the graph
     */

     public int[][] createMatrix()
     {   
        int[][] matrix = new int[numVertices()+1][numVertices()+1];
        int v = 1; // Is 1 to skip the very first element

        vertices.sortList();

        for (Object o : vertices) // These are across the top row
        {
            // Place the look up value in the corresponding places in the top row and left column
            matrix[0][v] = ((DSAGraphVertex)o).getLookUp();
            matrix[v][0] = ((DSAGraphVertex)o).getLookUp();
            v++; // Increase for next Object

            DSALinkedList adjacent = ((DSAGraphVertex)o).getAdjacent();

            for (Object q : adjacent) // These are down the left column
            {
                // Place a 1 at the location in the matrix where the two nodes meet.
                matrix[((DSAGraphVertex)o).getLookUp()+1][((DSAGraphVertex)q).getLookUp()+1] = 1;
            }
        }

        return matrix;
     }

     
     
     
     
     
     /*
      * Title:     displayAsMatrix
      * Author:    Jacob Jonas, 18439731
      * Created:   07/09/2022
      * Modified:  07/09/2022
      * Import:    matrix (int[][])
      * Export:    none
      * Assertion: print out the adjacency matrix
      */

      public void displayAsMatrix()
      {
        int[][] matrix = this.createMatrix();

        // Standard nested for loop to display 2-D array
        for(int r = 0; r < matrix.length; r++)
        {
            for(int c = 0; c < matrix.length; c++)
            {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println(""); // print each row on a new line
        }
      }
}
