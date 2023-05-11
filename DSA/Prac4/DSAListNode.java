/*
 * Title:     DSAListNode
 * Author:    Jacob Jonas, 18439731
 * Created:   30/08/2022
 * Modified:  30/08/2022
 * Assertion: Create a node for use in DSALinkedList
 */
import java.util.*;
import java.io.*;

    public class DSAListNode implements Serializable
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

        public interface Serializable { }

    } // End DSAListNode class







    