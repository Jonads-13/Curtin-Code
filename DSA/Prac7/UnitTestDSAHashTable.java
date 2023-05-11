import java.util.NoSuchElementException;

/*
 * Title:     UnitTestDSAHashTable
 * Author:    Jacob Jonas, 18439731
 * Created:   22/09/2022
 * Modified:  23/09/2022
 * Assertion: Test the implementation of the DSAHashTable class
 */

public class UnitTestDSAHashTable 
{
    public static void main(String[] args)
    {
        DSAHashTable test = new DSAHashTable(20);

        System.out.println("\nTesting nextPrime():");
        assert 23 == test.getLength();
        System.out.println("PASSED");

        System.out.println("\nTesting that the table was created empty:");
        assert 0 == test.getCount();
        System.out.println("PASSED");

        System.out.println("\nTesting put() and get():");
        test.put("hello", 10);

        assert 10 == (int)test.get("hello");

        assert 23 == test.getLength();

        assert 1 == test.getCount();

        System.out.println("PASSED");

        System.out.println("\nTesting hasKey():");

        assert true == test.hasKey("hello");
        assert false == test.hasKey("fault");

        System.out.println("PASSED");

        System.out.println("\nTesting duplicates can't be added:");
        test.put("hello", 20);

        assert 1 == test.getCount();
        System.out.println("PASSED");

        System.out.println("\nTesting remove()");
        test.remove("hello");

        try 
        {
            test.get("hello");
            assert false;
        } 
        catch (NoSuchElementException e) 
        {
            assert true;
        }

        assert 0 == test.getCount();

        assert 13 == test.getLength();

        System.out.println("PASSED");

        System.out.println("\nTesting multiple puts:");
        test.put("hello", 10);
        test.put("there", 15);
        test.put("general", 4);
        test.put("kenobi",5);
        System.out.println("PASSED");

        System.out.println("\nTesting shrink():");
        System.out.println("Length before removing: " + test.getLength());
        test.remove("general");
        test.remove("hello");

        System.out.println("Length after removing: " + test.getLength());

        assert 7 == test.getLength();
        System.out.println("PASSED");

        System.out.println("\nTesting grow():");
        System.out.println("Length before putting: " + test.getLength());
        test.put("work", 100);
        test.put("hello", 50);
        test.put("castoria", 90);
        test.put("spishtar", 100);
        System.out.println("Length after putting: " + test.getLength());
        System.out.println("PASSED");


        System.out.println("\nTesting find() with mulitple values in the table:");
        test.find("work");
        test.find("hello");
        test.find("castoria");
        test.find("spishtar");
        System.out.println("PASSED");
    }
}
