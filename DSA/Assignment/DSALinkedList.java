/* 
 * Title:     DSALinkedList class        
 * Author:    Jacob Jonas, 18439731      
 * Created:   18/08/2022                 
 * Modified:  30/08/2022 
 * Assertion: Personal Implementaion of a linked list object
 * Previously submitted for Practical 04 in COMP1002, Sem 2, 2022                
 */

import java.io.Serializable;
import java.util.Iterator;


public class DSALinkedList implements Iterable, Serializable
{

    // Class fields
    private DSAListNode head;
    private DSAListNode tail;
    private int count;


   /*
    * Title:     DSAListNode
    * Author:    Jacob Jonas, 18439731
    * Created:   30/08/2022
    * Modified:  30/08/2022
    * Assertion: Create a node for use in DSALinkedList
    */

    private class DSAListNode implements Serializable
    {
        // Class fieids
        private Object value;
        private DSAListNode next;
        private DSAListNode prev;




        // Constructor with parameter
        public DSAListNode(Object pValue)
        {
            
            value = pValue; // Set the value as the imported parameter
            // These are set by DSALinkedList's methods
            next = null;
            prev = null;
        }

        
        
        
        
        
        /*
            * Title:     getValue
            * Author:    Jacob Jonas, 18439731
            * Created:   22/08/2022
            * Modified:  30/08/2022
            * Import:    none
            * Export:    value (Object)
            * Assertion: Return the value value
            */

        public Object getValue()
        {
            return value;
        } // End getValue()

        
        
        
        
        
        /*
            * Title:     setValue
            * Author:    Jacob Jonas, 18439731
            * Created:   22/08/2022
            * Modified:  30/08/2022
            * Import:    pValue (Object)
            * Export:    none
            * Assertion: value is update with the imported pValue
            */

        public void setValue(Object pValue)
        {
            value = pValue;
        } // End setValue()

        
        
        
        
        
        /*
        * Title:     getNext
        * Author:    Jacob Jonas, 18439731
        * Created:   30/08/2022
        * Modified:  30/08/2022
        * Import:    none
        * Export:    next (DSAListNode)
        * Assertion: Return the node the the current node is pointing "forward" to
        */

        public DSAListNode getNext()
        {
            return next;
        } // End getNext()

        
        
        
        
        
        /*
        * Title:     setNext
        * Author:    Jacob Jonas, 18439731
        * Created:   30/08/2022
        * Modified:  30/08/2022
        * Import:    pNext (DSAListNode)
        * Export:    none
        * Assertion: next is updated to pNext
        */

        public void setNext(DSAListNode pNext)
        {
            next = pNext;
        } // End setNext()

        
        
        
        
        
        /*
            * Title:     getPrev
            * Author:    Jacob Jonas, 18439731
            * Created:   30/08/2022
            * Modified:  30/08/2022
            * Import:    none
            * Export:    prev (DSAListNode)
            * Assertion: Return the node the current node is pointing "back" to
            */

        public DSAListNode getPrev()
        {
            return prev;
        } // End getPrev()

        
        
        
        
        
        /*
            * Title:     setPrev
            * Author:    Jacob Jonas, 18439731
            * Created:   30/08/2022
            * Modified:  30/08/2022
            * Import:    pPrev (DSAListNode)
            * Export:    none
            * Assertion: prev is updated to pPrev
            */

        public void setPrev(DSAListNode pPrev)
        {
            prev = pPrev;
        } // End setPrev

    } // End DSAListNode class





    /*
     * Title:     DSALinkedListIterator
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Assertion: Create a private inner class iterator to use for DSALinkedList
     */

    private class DSALinkedListIterator implements Iterator
    {
        //Class field
        private DSAListNode iterNext;


        // Default Constructor
        public DSALinkedListIterator(DSALinkedList theList)
        {
            iterNext = theList.head; // Initialise the first item as the head
        }

        
        
        
        
        
        /*
         * Title:     hasNext
         * Author:    Jacob Jonas, 18439731
         * Created:   30/08/2022
         * Modified:  30/08/2022
         * Import:    none
         * Export:    (Boolean)
         * Assertion: Determine if the next node in the iteration is not null
         */

        public boolean hasNext()
        {
            return (iterNext != null); 
        } // End hasNext()

        
        
        
        
        
        /*
         * Title:     next
         * Author:    Jacob Jonas, 18439731
         * Created:   30/08/2022
         * Modified:  30/08/2022
         * Import:    none
         * Export:    value (Object)
         * Assertion: Return the next value in the iteration
         */

        public Object next()
        {
            Object value;

            if(iterNext == null) 
            {
                value = null;
            }
            else
            {
                
                value = iterNext.getValue(); // Get the current value
                iterNext = iterNext.getNext(); // Get the iterator ready for the next value
            }

            return value;
        } // End next()

        

        
        
        /*
         * Title:     remove
         * Author:    Jacob Jonas, 18439731
         * Created:   30/08/2022
         * Modified:  30/08/2022
         * Import:    none
         * Export:    none
         * Assertion: Optional operation not supported
         */

        public void remove()
        {
            throw new UnsupportedOperationException("Function does not exist");
        } // End remove()

    } // End Iterator class


    // Default constructor
    public DSALinkedList()
    {
        // Default values
        head = null;
        tail = null;
        count = 0;
    }

    
    
    
    
    
    /*
     * Title:     getCount
     * Author:    Jacob Jonas, 18439731
     * Created:   09/09/2022
     * Modified:  09/09/2022
     * Import:    none
     * Export:    count (int)
     * Assertion: return the count value
     */

    public int getCount()
    {
        return count;
    }

        
    
    
    
    
    /*
     * Title:     iterator
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    none
     * Export:    (Iterator)
     * Assertion: Return an iterator object
     */

    public Iterator iterator()
    {
        // Create and return an iterator object
        return new DSALinkedListIterator(this);
    } // End iterator()

    
    
    
    
    
    /*
     * Title:     insertFirst
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    pValue (Object)
     * Export:    none
     * Assertion: Insert a value at the beginning of the list
     */

    public void insertFirst(Object pValue)
    {
        // Create a new node to hold the imported value
        DSAListNode newNd = new DSAListNode(pValue);
        count++;

        if(isEmpty())
        {
            // The new node is both the head and tail
            head = newNd;
            tail = newNd;
        }
        else
        {
            newNd.setNext(head); // Have the new node's next be the previous head
            head.setPrev(newNd); // Have the head's prev be the new node
            head = newNd; // The new node is now the head
        }
    } // End insertFirst()

    
    
    
    
    
    /*
     * Title:     insertLast
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    pValue (Object)
     * Export:    none
     * Assertion: Insert a value at the end of the list
     */

    public void insertLast(Object pValue)
    {
        // Create a new node to hold the imported value
        DSAListNode newNd = new DSAListNode(pValue);
        count++;

        if(isEmpty())
        {
            // New node is both the head and the tail
            head = newNd;
            tail = newNd;
        }
        else
        {
            newNd.setPrev(tail); // New node's prev is the old tail
            tail.setNext(newNd); // Tail's next is the new ndoe
            tail = newNd; // New node is now the tail
        }
    } // End insertLast()

    
    
    
    
    
    /*
     * Title:     isEmpty
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    none
     * Export:    isEmpty (Boolean)
     * Assertion: Determine if the list is empty
     */

    public boolean isEmpty()
    {
        boolean isEmpty = false;

        // Ensure both ends are empty
        if((head == null) && (tail == null))
        {
            isEmpty = true;
        }

        return isEmpty;
    } // End isEmpty()

    

    
    
    
    
    
    /*
     * Title:     peekFirst
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    none
     * Export:    nodeValue (Object)
     * Assertion: Peek at the first item in the list
     */

    public Object peekFirst()
    {
        Object nodeValue;

        if(isEmpty())
        {
            // Can't access an item that isn't there
            throw new NullPointerException("List is empty. No node to access");
        }
        else
        {
            // Return the value at the head
            nodeValue = head.getValue();
        }

        return nodeValue;
    } // End peekFirst()

    
    
    
    
    
    /*
     * Title:     peekLast
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    none
     * Export:    nodeValue (Object)
     * Assertion: Peek at the last item in the list
     */

    public Object peekLast()
    {
        Object nodeValue;

        if(isEmpty())
        {
            // Can't look at something that isn;t there
            throw new NullPointerException("List is empty. No node to access");
        }
        else
        {
            // Return the value at the tail
            nodeValue = tail.getValue();
        }

        return nodeValue;
    } // End peekLast()

    
    
    
    
    
    /*
     * Title:     removeFirst
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  01/09/2022
     * Import:    none
     * Export:    nodeValue (Object)
     * Assertion: Remove the item at the front of the list
     */

    public Object removeFirst()
    {
        Object nodeValue;

        if(isEmpty())
        {
            // Can't remove something that isn't there
            throw new NullPointerException("List is empty. No node to access");
        }
        else if(head.getNext() == null) // Only one item in the list
        {
            nodeValue = head.getValue(); // Return the item and the head
            // Set the list to empty
            head = null;
            tail = null;
            count--;
        }
        else
        {
            nodeValue = head.getValue(); // Return the value at the head
            head = head.getNext(); // Set the second Nnde as the head
            head.setPrev(null); // Ensure there is nothing "before" the head
            count--;
        }

        return nodeValue;
    } // End removeFirst()

    
    
    
    
    
    /*
     * Title:     removeLast
     * Author:    Jacob Jonas, 18439731
     * Created:   30/08/2022
     * Modified:  30/08/2022
     * Import:    none
     * Export:    nodeValue (Object)
     * Assertion: Remove the last item in the list
     */

    public Object removeLast()
    {
        Object nodeValue;

        if(isEmpty())
        {
            // Can't remove something that isn't there
            throw new NullPointerException("List is empty. No node to access");
        }
        else if(head.getNext() == null) // Only one item in the list
        {
            nodeValue = tail.getValue(); // Return the value at the tail
            // Set the list to empty
            head = null;
            tail = null;
            count--;
        }
        else 
        {
            nodeValue = tail.getValue(); // Return the value at the tail
            tail = tail.getPrev(); // Set the second last node as the tail
            tail.setNext(null); // Ensure there is nothing "after" the tail
            count--;
        }

        return nodeValue;
    } // End removeLast()

    
    
    
    
    
    /*
     * Title:     sortList
     * Author:    Jacob Jonas, 18439731
     * Created:   08/09/2022
     * Modified:  08/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Sort the list 
     */

    public void sortList()
    {
  
        // Node current will point to head
        DSAListNode current = head, index = null;
  
        Object temp;
  
        if (head == null) 
        {
            return;
        }
        else 
        {
            while (current != null) {
                // Node index will point to node next to
                // current
                index = current.getNext();
  
                while (index != null) {
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them
                    if ((current.getValue().toString()).compareTo(index.getValue().toString()) > 0)
                    {
                        temp = current.getValue();
                        current.setValue(index.getValue());
                        index.setValue(temp);
                    }
  
                    index = index.getNext();
                }
                current = current.getNext();
            }
        }
    }
}
