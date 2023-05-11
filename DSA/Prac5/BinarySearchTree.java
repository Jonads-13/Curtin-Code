/*
 * Title:     BinarySearchTree
 * Author:    Jacob Jonas, 18439731
 * Created:   30/08/2022
 * Modified:  30/08/2022
 * Assertion: Implement a binary search tree
 */

import java.io.*;
import java.util.*;

public class BinarySearchTree implements Serializable
{
    private TreeNode root;

    public BinarySearchTree()
    {
        root = null;
    }

    private class TreeNode implements Serializable
    {
        // Class fields
        private String key;
        private Object value;
        private TreeNode leftChild;
        private TreeNode rightChild;

        // Constructor
        public TreeNode(String inKey, Object inVal) 
        {
            if(inKey.equals(""))
            {
                throw new IllegalArgumentException("Key cannot be null");
            }
            else
            {
                // Create object with imported parameters
                key = inKey;
                value = inVal;
                rightChild = null;
                leftChild = null;
            }   
        }

        

        
        
        
        
        
        /*
         * Title:     getKey
         * Author:    Jacob Jonas, 18439731
         * Created:   30/08/2022
         * Import:    none
         * Export:    key (String)
         * Assertion: Return the key value
         */

        public String getKey() 
        { 
            return key; 
        } // End getKey()

        
        
        
        
        
        /*
         * Title:     getValue
         * Author:    Jacob Jonas, 18439731
         * Created:   30/08/2022
         * Import:    none
         * Export:    value (Object)
         * Assertion: Return the value value
         */

        public Object getValue() 
        { 
            return value; 
        } // End getValue()

        
        
        
        
        
        /*
         * Title:     getLeft
         * Author:    Jacob Jonas, 18439731
         * Created:   30/08/2022
         * Import:    none
         * Export:    leftChild (TreeNode)
         * Assertion: Return the left child of a tree node
         */

        public TreeNode getLeft() 
        { 
            return leftChild; 
        } // End getLeft()

        
        
        
        
        
        /*
         * Title:     setLeft
         * Author:    Jacob Jonas, 18439731
         * Created:   30/08/2022
         * Import:    newLeft (TreeNode)
         * Export:    none
         * Assertion: Insert a new TreeNode as a left child
         */

        public void setLeft(TreeNode newLeft) 
        { 
            leftChild = newLeft; 
        } // End setLeft()

        
        
        
        
        
        /*
         * Title:     getRight
         * Author:    Jacob Jonas, 18439731
         * Created:   30/08/2022
         * Import:    none
         * Export:    rightChild
         * Assertion: Return the rightChild if a TreeNode
         */

        public TreeNode getRight() 
        { 
            return rightChild; 
        } // End getRight()

        
        
        
        
        
        /*
         * Title:     setRight
         * Author:    Jacob Jonas, 18439731
         * Created:   30/08/2022
         * Modified:  30/08/2022
         * Import:    newRight (TreeNode)
         * Export:    none
         * Assertion: Insert a new TreeNode as a right child
         */

        public void setRight(TreeNode newRight) 
        { 
            rightChild = newRight; 
        } 

        
        
        
        
        
        /*
         * Title:     isLeaf
         * Author:    Jacob Jonas, 18439731
         * Created:   07/09/2022
         * Modified:  07/09/2022
         * Import:    none
         * Export:    isLeaf (boolean)
         * Assertion: Determine if a node is a leaf or not
         */

        public boolean isLeaf()
        {
            boolean isLeaf = false;
    
            // Node has no children
            if((this.getLeft() == null) && (this.getRight() == null))
            {
                isLeaf = true;
            }
    
            return isLeaf;
        }

        
        
        
        
        
        /*
         * Title:     toString
         * Author:    Jacob Jonas, 18439731
         * Created:   07/09/2022
         * Modified:  07/09/2022
         * Import:    none
         * Export:    nodeString
         * Assertion: Return a string representation of the object
         */

         public String toString()
         {
            String nodeString = key + "," + value;

            return nodeString;
         }
    } // End TreeNode class




    
    
    
    
    
    
    
   /*
    * Title:     getRoot
    * Author:    Jacob Jonas, 18439731
    * Created:   31/08/2022
    * Modified:  31/08/2022
    * Import:    none
    * Export:    rootKey (String)
    * Assertion: Return the key of the root
    */

    public String getRoot()
    {
        if(root == null)
        {
            throw new NoSuchElementException("No root to get");
        }
        else
        {
            return root.getKey();
        }
    } // End getRoot()

    
    /*
     * Title:     find
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    key (String)
     * Export:    (Object)
     * Assertion: Find an object in the tree
     */

    public Object find(String key)
    {
        return findRec(key, root);
    } //End find()

    
    
    
    
    
    /*
     * Title:     findRec
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  31/08/2022
     * Import:    key (String), currNode(TreeNode)
     * Export:    value (Object)
     * Assertion: Recursively look for the desired key
     */

    private Object findRec(String key, TreeNode currNode)
    {
        Object value = null;

        if (currNode == null)
        {
            throw new NoSuchElementException("Key " + key + " not found");
        }
        else if (key.equals(currNode.getKey()))
        {
            value = currNode.getValue(); 
        }
        else if(key.compareTo(currNode.getKey()) < 0) // Alphabetically before
        {
            // Go left   
            value = findRec(key, currNode.getLeft());
        }
        else // Alphabetically after
        {
            // Go right
            value = findRec(key, currNode.getRight());
        }

        return value;
    } // End findRec()

    
    
    
    
    
    /*
     * Title:     insert
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    key (String), data (Object)
     * Export:    (TreeNode)
     * Assertion: Insert a TreeNode into the tree
     */

    public TreeNode insert(String key, Object data)
    {
        return insertRec(key, data, root);
    } // End insert()

    
    
    
    
    
    /*
     * Title:     insertRec
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  31/08/2022
     * Import:    key (String), data (Object), currNode (TreeNode)
     * Export:    update (TreeNode)
     * Assertion: Recursively find where to insert a TreeNode, then insert it
     */

    private TreeNode insertRec(String key, Object data, TreeNode currNode)
    {
        TreeNode update = currNode;

        if(root == null) // Tree is empty
        {
            TreeNode newNd = new TreeNode(key, data);
            root = newNd;
        }
        else if(currNode == null)
        {
            TreeNode newNd = new TreeNode(key, data);
            update = newNd;
        }
        else if(key.equals(currNode.getKey())) // Node found in tree
        {
            throw new IllegalArgumentException("Error: Key " + key + " already in tree");
        }
        else if(key.compareTo(currNode.getKey()) < 0) // Alphabetically before
        {
            // Go left
            currNode.setLeft(insertRec(key, data, currNode.getLeft()));
        }
        else // Alphabetically after
        {
            // Go right
            currNode.setRight(insertRec(key, data, currNode.getRight()));
        }

        return update;
    } // End insertRec()

    
    
    
    
    
    /*
     * Title:     isEmpty
     * Author:    Jacob Jonas, 18439731
     * Created:   31/08/2022
     * Modified:  31/08/2022
     * Import:    none
     * Export:    isEmpty (Boolean)
     * Assertion: Check if the Tree is empty
     */

    public boolean isEmpty() 
    {
        boolean isEmpty = false;

        if(root == null)
        {
            isEmpty = true;
        }

        return isEmpty;
        
    }

    
    
    
    
    
    /*
     * Title:     delete
     * Author:    Jacob Jonas, 18439731
     * Created:   31/08/2022
     * Modified:  31/08/2022
     * Import:    key (String)
     * Export:    (TreeNode)
     * Assertion: Wrapper for recursive method
     */

    public TreeNode delete(String key)
    {
        return deleteRec(key, root);
    } // End delete()

    
    
    
    
    
    /*
     * Title:     deleteRec
     * Author:    Jacob Jonas, 18439731
     * Created:   31/08/2022
     * Modified:  31/08/2022
     * Import:    key (String), currNode (TreeNode)
     * Export:    update (TreeNode)
     * Assertion: Find the node to delete
     */

    private TreeNode deleteRec(String key, TreeNode currNode)
    {
        TreeNode update = currNode;

        if(currNode == null)
        {
            throw new NoSuchElementException("Error: Key " + key + " does not exist to delete");
        }
        else if(key.equals(currNode.getKey()))
        {
            if(currNode.getKey().equals(root.getKey())) // Node to delete is the root
            {
                root = deleteNode(key, root);
            }
            else
            {
                update = deleteNode(key,currNode);
            }
            
        }
        else if(key.compareTo(currNode.getKey()) < 0) // Alphabetically before
        {
            // Go left
            currNode.setLeft(deleteRec(key, currNode.getLeft()));
        }
        else // Alphabetically after
        {
            // Go right
            currNode.setRight(deleteRec(key, currNode.getRight()));
        }

        return update;
    } // End deleteRec()

    
    
    
    
    
    /*
     * Title:     deleteNode
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    key (String), delNode (TreeNode)
     * Export:    update (TreeNode)
     * Assertion: Performs all actions required to both delete a node and reconstruct the tree after it was deleted
     */

    private TreeNode deleteNode(String key, TreeNode delNode)
    {
        TreeNode update = null;

        // Node is a leaf
        if((delNode.getLeft() == null) && (delNode.getRight() == null))
        {
            update = null;
        }
        // Node has only one child on the left
        else if((delNode.getLeft() != null) && (delNode.getRight() == null))
        {
            update = delNode.getLeft();
        }
        // Node has only one child on the right
        else if((delNode.getLeft() == null) && (delNode.getRight() != null))
        {
            update = delNode.getRight();
        }
        else // Node has two children
        {
            // Find successor
            update = promote(delNode.getRight());

            if(update != delNode.getRight())
            {
                update.setRight(delNode.getRight());
            }

            update.setLeft(delNode.getLeft());
        }

        return update;
    } // End deleteNode()

    
    
    
    
    
    /*
     * Title:     promote
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    currNode (TreeNode)
     * Export:    successor (TreeNode)
     * Assertion: Find a node to replace a deleted node as a parent node
     */

    private TreeNode promote(TreeNode currNode)
    {
        TreeNode successor = currNode;

        if(currNode.getLeft() == null) // If no left chiild
        {
            successor = currNode;
        }
        else
        {
            // Get the right-most node of the left half of the tree
            if(currNode.getLeft() != null)
            {
                successor = promote(currNode.getLeft());
                
                if(successor == currNode.getLeft())
                {
                    currNode.setLeft(successor.getRight());
                }
            }
        }

        return successor;
    } // End promote()

    
    
    
    
    
    /*
     * Title:     min
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    none
     * Export:    (String)
     * Assertion: Wrapper for recursive function
     */

    public String min()
    {
        return minRec(root);
    } // End min()

    
    
    
    
    
    /*
     * Title:     minRec
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    currNode (TreeNode)
     * Export:    minKey (String)
     * Assertion: Find the left-most node and return it's key
     */

    public String minRec(TreeNode currNode)
    {
        String minKey;

        while (currNode.getLeft() != null) // Keep going left
        {
            currNode = currNode.getLeft();
        }

        // minKey is the furthest left node's key
        minKey = currNode.getKey();

        return minKey;
    } // End minRec()

    
    
    
    
    
    /*
     * Title:     max
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    none
     * Export:    (String)
     * Assertion: Wrapper for recursive function
     */

    public String max()
    {
        return maxRec(root);
    } // End max()

    
    
    
    
    
    /*
     * Title:     maxRec
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    currNode (TreeNode)
     * Export:    maxKey (String)
     * Assertion: Find the key of the right-most node and return it's key
     */

    public String maxRec(TreeNode currNode)
    {
        String maxKey;

        while(currNode.getRight() != null) // Keep going right
        {
            currNode = currNode.getRight();
        }
        
        // maxKey is the furthest right node's key
        maxKey = currNode.getKey(); 

        return maxKey;
    } // End maxRec()

    
    
    
    
    
    /*
     * Title:     height
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    none
     * Export:    (int)
     * Assertion: Wrapper for recursive function
     */

    public int height()
    {
        return heightRec(root);
    } // End height()

    
    
    
    
    
    /*
     * Title:     heightRec
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    currNode (TreeNode)
     * Export:    htSoFar (int)
     * Assertion: Calculate the height of the tree
     */

    public int heightRec(TreeNode curNode)
    {
        int htSoFar, iLeftHt, iRightHt;

        if (curNode == null)
        {
            htSoFar = -1; // Base case – no more along this branch
        }
        else
        {
            iLeftHt = heightRec(curNode.getLeft()); // Calc left height from here 
            iRightHt = heightRec(curNode.getRight()); // Calc right height from here

            // Get highest of left vs right branches
            if (iLeftHt > iRightHt)
            {
                htSoFar = iLeftHt + 1;
            }
            else
            {
                htSoFar = iRightHt + 1;
            }   
       }

       return htSoFar;
    } // End heightRec()






    /*
     * Title:     balance
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    none
     * Export:    balance (double)
     * Assertion: calculate how balanced the tree is
     */

    public double balance()
    {
        double balance = 0, numLeaves = 0;
        int height;

        height = this.height(); // Get the tree's height

        numLeaves = treeTraversal(root); // Count the number of leaf nodes

        if(numLeaves >=0) // As long as the tree isn't empty
        {
            // Balance is the ratio of number of actual leaves to the number of possible leaves
            balance = numLeaves / (2^(height)) * 100; 
        }
        double roundOff = Math.round(balance * 100.0) / 100.0;

        return roundOff;
    } // End balance()

    
    
    
    
    
    /*
     * Title:     treeTraversal
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    currNode (TreeNode)
     * Export:    numLeaves (double)
     * Assertion: Count the number of leaf nodes in the tree
     */

    private double treeTraversal(TreeNode currNode)
    {
        double numLeaves; // Use double to allow for decimal division for balance()

        if(currNode == null)
        {
            numLeaves = 0;
        }
        else if(currNode.isLeaf())
        {
            numLeaves = 1;
        }
        else
        {
            numLeaves = treeTraversal(currNode.getRight()) + treeTraversal(currNode.getLeft());
        }
        return numLeaves;
    } // End treeTraversal()

    
    
    
    
    
    /*
     * Title:     preOrder
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    none
     * Export:    (DSALinkedList)
     * Assertion: Wrapper for recursve function
     */

    public DSALinkedList preOrder()
    {
        DSALinkedList preOrder = new DSALinkedList();
        return preOrderRec(root, preOrder);
    } // End preOrder()

    
    
    
    
    
    /*
     * Title:     preOrderRec
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    currNode (TreeNode), preOrder (DSALinkedList)
     * Export:    preOrder (DSALinkedList)
     * Assertion: Create a linked list containing the tree in pre order form
     */

    private DSALinkedList preOrderRec(TreeNode currNode, DSALinkedList preOrder) 
    { 
        if (currNode == null) 
        { 
             
        } 
        else
        {
            preOrder.insertLast(currNode);
            preOrderRec(currNode.getLeft(), preOrder);
            preOrderRec(currNode.getRight(), preOrder); 
        }

        return preOrder;
    } // End preOrderRec()

    
    
    
    
    
    /*
     * Title:     inOrder
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    none
     * Export:    (DSALinkedList)
     * Assertion: Wrapper for recursive function
     */

    public DSALinkedList inOrder()
    {
        DSALinkedList inOrder = new DSALinkedList();
        return inOrderRec(root, inOrder);
    } // End inOrder()

    
    
    
    
    
    /*
     * Title:     inOrderRec
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    currNode (TreeNode), inOrder (DSALinkedList)
     * Export:    inOrder (DSALinkedList)
     * Assertion: Create a linked list containing the tree in in order form
     */

    private DSALinkedList inOrderRec(TreeNode currNode, DSALinkedList inOrder)
    {
        if (currNode == null) 
        { 
             
        } 
        else
        {
            inOrderRec(currNode.getLeft(), inOrder);
            inOrder.insertLast(currNode);
            inOrderRec(currNode.getRight(), inOrder); 
        }

        return inOrder;
    } // End inOrderRec()

    
    
    
    
    
    /*
     * Title:     postOrder
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    none
     * Export:    (DSALinkedList)
     * Assertion: Wrapper for recursive function
     */

    public DSALinkedList postOrder()
    {
        DSALinkedList postOrder = new DSALinkedList();
        return postOrderRec(root, postOrder);
    } // End postOrder()

    
    
    
    
    
    /*
     * Title:     postOrderRec
     * Author:    Jacob Jonas, 18439731
     * Created:   06/09/2022
     * Modified:  06/09/2022
     * Import:    currNode (TreeNode), postOrder (DSAlinkedList)
     * Export:    postOrder (DSALinkedList)
     * Assertion: Create a linked list containing the tree in post order form
     */

    private DSALinkedList postOrderRec(TreeNode currNode, DSALinkedList postOrder)
    {
        if (currNode == null) 
        { 
             
        } 
        else
        {
            postOrderRec(currNode.getLeft(), postOrder);
            postOrderRec(currNode.getRight(), postOrder); 
            postOrder.insertLast(currNode);
        }

        return postOrder;
    } // End postOrderRec()

}
