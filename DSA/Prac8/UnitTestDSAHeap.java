/*
 * Title:     UnitTestDSAHeap
 * Author:    Jacob Jonas, 18439731
 * Created:   27/09/2022
 * Modified:  27/09/2022
 * Assertion: Test the implementation of DSAHeap
 */

public class UnitTestDSAHeap 
{
    public static void main(String[] args)
    {
        DSAHeap test = new DSAHeap(20);

        System.out.println("\nTesting heap was created empty:");
        assert 0 == test.getCount();
        System.out.println("PASSED");

        // Notice that a test is being performed
        System.out.println("\nTesting add():");
        test.add(10, "Hello");
        assert 1 == test.getCount();
        System.out.println("PASSED"); // Notify of success

        System.out.println("\nTesting multiple adds:");
        test.add(12, "There");
        assert 2 == test.getCount();
        test.add(13, "Jack");
        test.add(90, "Castoria");
        test.add(100, "Spishtar");
        assert 5 == test.getCount();
        System.out.println("PASSED");

        // Notice that a test is being performed
        System.out.println("\nTesting remove():");
        DSAHeapEntry removed = test.remove();

        assert "Spishtar".equals(removed.getValue() + "");
        assert 4 == test.getCount();
        System.out.println("PASSED"); // Notify of success

        System.out.println("\nTesting adding and removing the same entry again:");
        test.add(100, "Spishtar");

        removed = test.remove();

        assert "Spishtar".equals(removed.getValue() + "");

        System.out.println("PASSED");

        System.out.println("\nTesting multiple removes");
        int count = test.getCount();
        int[] expC = {3, 2, 1, 0};
        String[] exp = {"Castoria", "Jack", "There", "Hello"};

        for(int i = 0; i < count; i++)
        {
            removed = test.remove();
            assert exp[i].equals(removed.getValue() + ""): exp[i];
            assert expC[i] == test.getCount();
        }

        System.out.println("PASSED");

    }
}
