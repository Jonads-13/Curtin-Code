import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MyUtilsTest 
{
    @Test
    public void testMax() // Note: Not static!
    {
        MyUtils utils =  mock(MyUtils.class);

        utils.max(5,1);
        verify(utils).max(5,1);
    }

    @Test
    public void testPrintMax()
    {
        MyUtils utils = mock(MyUtils.class);
        MyUtils.printMax(utils, 5,1); // Call our production code
        verify(utils).max(5,1); // Verify the function was, indeed, called
    }

    @Test
    public void testWhenThen()
    {
        int x = 5;
        int y = 10;

        MyUtils utils = mock(MyUtils.class);
        when(utils.max(x,y)).thenReturn(y);

        int result = utils.max(x,y);
        System.out.println(result);
    }
}
