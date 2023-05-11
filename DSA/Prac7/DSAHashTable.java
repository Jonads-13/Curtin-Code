/*
 * Title:     DSAHashTable
 * Author:    Jacob Jonas, 18439731
 * Created:   13/09/2022
 * Modified:  22/09/2022
 * Assertion: Implement a hash table
 */

import java.util.NoSuchElementException;

public class DSAHashTable 
{
    private DSAHashEntry[] hashArray;
    private int count;
    private DSALinkedList dupeList;


    public DSAHashTable(int tableSize)
    {
        int actualSize = nextPrime(tableSize);
        hashArray = new DSAHashEntry[actualSize];
        count = 0;
        dupeList = new DSALinkedList();

        initialise(hashArray);
    }

    /*
     * Title:     DSAHashEntry
     * Author:    Jacob Jonas, 18439731
     * Created:   16/09/2022
     * Modified:  16/09/2022
     * Assertion: DSAHashEntry class implementation
     */

    private class DSAHashEntry
    {
        private String key;
        private Object value;
        private int state;

        public DSAHashEntry()
        {
            key = "";
            value = null;
            state = 0;
        }

        public DSAHashEntry(String pKey, Object pValue)
        {
            key = pKey;
            value = pValue;
            state = 1;
        }

        
        
        
        
        
        /*
         * Title:     toString
         * Author:    Jacob Jonas, 18439731
         * Created:   26/09/2022
         * Modified:  26/09/2022
         * Import:    none
         * Export:    (String)
         * Assertion: return a string representaion of thge object
         */
        
        public String toString() 
        {
            return key + "," + value;
        }


    }
    
    
    
    
    
    /*
     * Title:     nextPrime
     * Author:    Jacob Jonas, 18439731
     * Created:   16/09/2022
     * Modified:  16/09/2022
     * Import:    startVal (int)
     * Export:    primeVal(int)
     * Assertion: find the next prime number following an imported integer
     */
    
    

    public int nextPrime(int startVal)
    {
        int primeVal;

        if(startVal % 2 == 0)
        {
            primeVal = startVal -1; 
        }
        else
        {
            primeVal = startVal;
        }

        boolean isPrime = false;
        int ii;
        double rootVal;

        do
        {
            primeVal = primeVal + 2;
            ii = 3;
            isPrime = true;
            rootVal = Math.sqrt(primeVal);

            do
            {
                if(primeVal % ii == 0)
                {
                    isPrime = false;
                }
                else
                {
                    ii += 2;
                }
            }while((ii <= rootVal) && (isPrime));

        }while(!isPrime);

        if(primeVal == 5)
        {
            primeVal = 7;
        }

        return primeVal;
    }

    
    
    
    
    
    /*
     * Title:     hash
     * Author:    Jacob Jonas, 18439731
     * Created:   16/09/2022
     * Modified:  22/09/2022
     * Import:    key (byte[])
     * Export:    (int)
     * Assertion: hash function
     */
    
    private int hash(String key)
    {
        int hashIdx = 0;

        for(int i = 0; i < key.length(); i++)
        {
            hashIdx = (33 * hashIdx) + key.charAt(i);
        }

        return Math.abs(hashIdx % hashArray.length);
    }

    
    
    
    
    
    /*
     * Title:     stepHash
     * Author:    Jacob Jonas, 18439731
     * Created:   21/09/2022
     * Modified:  22/09/2022
     * Import:    key (int)
     * Export:    hashStep (int)
     * Assertion: used for double hashing
     */
    
    private int stepHash(int key) 
    {
        int hashStep = 13 - (key % 13);

        return hashStep;
    }

    
    
    
    
    
    /*
     * Title:     put
     * Author:    Jacob Jonas, 18439731
     * Created:   22/09/2022
     * Modified:  22/09/2022
     * Import:    inKey (String), inValue (Object)
     * Export:    none
     * Assertion: place a value in the hash array
     */
    
    public void put(String inKey, Object inValue) 
    {
        int hashIdx = hash(inKey);
        boolean dupe = false;

        while(hashArray[hashIdx].state == 1)
        {
            if((hashArray[hashIdx].key).equals(inKey))
            {
                dupe = true;
                DSAHashEntry duplicate = new DSAHashEntry(inKey, inValue);

                // Add both entries to the dupe list as the first might not be the correct one
                dupeList.insertLast(hashArray[hashIdx]);
                dupeList.insertLast(duplicate);
            }

            hashIdx = (hashIdx + hashIdx % hashArray.length/*stepHash(hashIdx)*/) % hashArray.length;
        }

        if(!dupe)
        {
            hashArray[hashIdx].key = inKey;
            hashArray[hashIdx].value = inValue;
            hashArray[hashIdx].state = 1;
            count++;
        }

        grow();
    }
    
    
    
    
    /*
     * Title:     get
     * Author:    Jacob Jonas, 18439731
     * Created:   16/09/2022
     * Modified:  22/09/2022
     * Import:    pKey (String)
     * Export:    (Object)
     * Assertion: 
     */
    
    public Object get(String pKey)
    {
        int hashIdx = hash(pKey);
        int origIdx = hashIdx;
        boolean found = false, giveUp = false;

        while((!found) && (!giveUp))
        {
            if(hashArray[hashIdx].state == 0)
            {
                giveUp = true;
            }
            else if(hashArray[hashIdx].key.equals(pKey))
            {
                found = true;
            }
            else 
            {
                hashIdx = (hashIdx + hashIdx % hashArray.length) % hashArray.length;

                if(hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }

        if(!found)
        {
            throw new NoSuchElementException(pKey + " could not be found");
        }

        Object retValue = hashArray[hashIdx].value;

        return retValue;
    }

    
    
    
    
    
    /*
     * Title:     find
     * Author:    Jacob Jonas, 18439731
     * Created:   23/09/2022
     * Modified:  23/09/2022
     * Import:    pKey (String)
     * Export:    hashIdx (int)
     * Assertion: find the index for a given key
     */
    
    public int find(String pKey)
    {
        int hashIdx = hash(pKey);
        int origIdx = hashIdx;
        boolean found = false, giveUp = false;

        while((!found) && (!giveUp))
        {
            if(hashArray[hashIdx].state == 0)
            {
                giveUp = true;
            }
            else if(hashArray[hashIdx].key.equals(pKey))
            {
                found = true;
            }
            else 
            {
                hashIdx = (hashIdx + hashIdx % hashArray.length) % hashArray.length;

                if(hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }

        if(!found)
        {
            throw new NoSuchElementException(pKey + " could not be found");
        }

        return hashIdx;
    }

    
    
    
    
    
    /*
     * Title:     remove
     * Author:    Jacob Jonas, 18439731
     * Created:   22/09/2022
     * Modified:  22/09/2022
     * Import:    inKey (String)
     * Export:    value (Object)
     * Assertion: remove a value from the hash array
     */
    
    public Object remove(String inKey) 
    {

        int hashIdx = find(inKey);

        Object retValue = hashArray[hashIdx].value;

        hashArray[hashIdx].key = "";
        hashArray[hashIdx].value = null;
        hashArray[hashIdx].state = -1;
        count--;


        shrink();

        return retValue;
    }

    
    
    
    
    
    /*
     * Title:     hasKey
     * Author:    Jacob Jonas, 18439731
     * Created:   22/09/2022
     * Modified:  22/09/2022
     * Import:    inKey (String)
     * Export:    hasKey (boolean)
     * Assertion: Determine if an imported key is in the hash table
     */
    
    public boolean hasKey(String inKey) 
    {
        boolean hasKey = false;
        try
        {
            find(inKey);
            hasKey = true;
        }    
        catch (NoSuchElementException e)
        {
            hasKey = false;
        }

        return hasKey;
    }

    
    
    
    
    
    /*
     * Title:     
     * Author:    Jacob Jonas, 18439731
     * Created:   24/09/2022
     * Modified:  24/09/2022
     * Import:    
     * Export:    
     * Assertion: 
     */
    
    public DSALinkedList dupeList() 
    {
        return dupeList;    
    }

    
    
    
    
    
    /*
     * Title:     loadFactor
     * Author:    Jacob Jonas, 18439731
     * Created:   16/09/2022
     * Modified:  16/09/2022
     * Import:    none
     * Export:    lf (double)
     * Assertion: Determine the load factor of the hash table
     */
    
    public double loadFactor()
    {
        // Calculate load factor
        return count/((double)hashArray.length);
    }

    
    
    
    
    
    /*
     * Title:     grow
     * Author:    Jacob Jonas, 18439731
     * Created:   16/09/2022
     * Modified:  23/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Determine whether it is necessary to grow the size of the array depending on the load factor
     */
    
    private void grow()
    {
        double lf = loadFactor(); 
        int newLength = hashArray.length;

        if(lf > 0.6)
        {
            newLength = (hashArray.length) * 2;
            newLength = nextPrime(newLength);
        }

        if(newLength != hashArray.length)
        {
            resize(newLength);
        }
    }

    
    
    
    
    
    /*
     * Title:     shrink
     * Author:    Jacob Jonas, 18439731
     * Created:   23/09/2022
     * Modified:  23/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Determine whether it is necessary to shrink the size of the array depending on the load factor
     */
    
    private void shrink() 
    {
        double lf = loadFactor();
        int newLength = hashArray.length;

        if(lf < 0.4)
        {
            newLength = (hashArray.length)/2;
            newLength = nextPrime(newLength);
        }   
        
        if(newLength != hashArray.length)
        {
            resize(newLength);
        } 
    }

    
    
    
    
    
    /*
     * Title:     resize
     * Author:    Jacob Jonas, 18439731
     * Created:   23/09/2022
     * Modified:  23/09/2022
     * Import:    newLength (int)
     * Export:    none
     * Assertion: Resize the hashArray to a new length
     */
    
    private void resize(int newLength) 
    {
        DSAHashEntry[] newArray = new DSAHashEntry[newLength];
        initialise(newArray);
        DSAHashEntry[] oldArray = hashArray;
        hashArray = newArray;
        count = 0;

        for(int i = 0; i < oldArray.length; i++)
        {
            if(oldArray[i].state == 1)
            {
                put(oldArray[i].key, oldArray[i].value);
            }
        }    
    }

    
    
    
    
    
    /*
     * Title:     initialise
     * Author:    Jacob Jonas, 18439731
     * Created:   22/09/2022
     * Modified:  22/09/2022
     * Import:    hashArray (HashEntry[])
     * Export:    none
     * Assertion: 
     */
    
    private void initialise(DSAHashEntry[] hashArray) 
    {
        for(int i = 0; i < hashArray.length; i++)
        {
            hashArray[i] = new DSAHashEntry();
        }    
    }

    
    
    
    
    
    /*
     * Title:     getLength
     * Author:    Jacob Jonas, 18439731
     * Created:   22/09/2022
     * Modified:  22/09/2022
     * Import:    none
     * Export:    length (int)
     * Assertion: return the length of the hash array
     */
    
    public int getLength() 
    {
        return hashArray.length;
    }

    
    
    
    
    
    /*
     * Title:     getCount
     * Author:    Jacob Jonas, 18439731
     * Created:   22/09/2022
     * Modified:  22/09/2022
     * Import:    none
     * Export:    count (int)
     * Assertion: Return the count value
     */
    
    public int getCount() 
    {
        return count;    
    }

    
    
    
    
    
    /*
     * Title:     print
     * Author:    Jacob Jonas, 18439731
     * Created:   22/09/2022
     * Modified:  22/09/2022
     * Import:    none
     * Export:    none
     * Assertion: Print the contents of the hash table
     */
    
    public void print() 
    {
        for(int i = 0; i < hashArray.length; i++)
        {
            //if(hashArray[i].value != null)
            //{
                System.out.println(hashArray[i]);
            //}
        }    
    }

}
