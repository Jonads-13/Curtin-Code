import org.junit.*;
import org.junit.runner.RunWith; 
import org.junit.runners.JUnit4; 
import static org.junit.Assert.*;
import java.util.*;


@RunWith(JUnit4.class)
public class MyUtils
{
	@Test
	public void testCalcGrade()
	{

		int [] mark = {32, 55, 66, 79, 87, 93, 100, -5, 200};
		String [] exp = {"F", "5", "6", "7", "8", "9", "10", "", ""};
		
		String [] msg = {"mark is between 0 and 49,inclusive", "mark is between 50 and 59 inclusive", "mark is between 60 and 69 inclusive", "mark is between 70 and 79 inclusive", "mark is between 80 and 89 inclusive", "mark is between 90 and 99 inclusive", "mark is 100", "mark is less than 0", "mark is more than 100"};


		for(int i = 0; i < mark.length; i++)
		{ 
			assertEquals(msg[i], exp[i], TestingMethods.calcGrade(mark[i]));
		}
	
	}

	@Test
	public void testCalcGradeBnd()
	{

		int [] mark = {-1, 0, 49, 50, 59, 60, 69, 70, 79, 80, 89, 90, 99, 100, 101};
		String [] exp = {"", "F", "5","5","6", "6", "7", "7", "8", "8", "9", "9", "10", ""};
		
		String [] msg = {"bnd low invalid", "bnd low F", "bnd high F", "bnd low 5", "bnd high 5","bnd low 6", "bnd high 6","bnd low 7", "bnd high 7","bnd low 8", "bnd high 8", "bnd low 9", "bnd high 9", "bnd 10", "bnd high invalid"};


		for(int i = 0; i < mark.length; i++)
		{ 
			assertEquals(msg[i], exp[i], TestingMethods.calcGrade(mark[i]));
		}
	
	}

	@Test
	public void testRoomVolume()
	{
		assertEquals("W < 2, L < 2.5, H < 3", 0, TestingMethods.roomVolume(1, 2, 2));
		assertEquals("W >= 2, L >= 2.5, H >= 3", 15, TestingMethods.roomVolume(2, 2.5, 3));
		assertEquals("W < 2, L >= 2.5, H >= 3", 0, TestingMethods.roomVolume(1, 25, 30));
		assertEquals("W >= 2, L < 2.5, H >= 3", 0, TestingMethods.roomVolume(12, 2, 12));
		assertEquals("W >= 2, L >= 2.5, H < 3", 0, TestingMethods.roomVolume(10, 12, 2));
		assertEquals("W < 2, L < 2.5, H >= 3", 0, TestingMethods.roomVolume(1, 2, 5));
		assertEquals("W >= 2, L < 2.5, H < 3", 0, TestingMethods.roomVolume(3, 2, 1));
		assertEquals("W < 2, L >= 2.5, H < 3", 0, TestingMethods.roomVolume(1, 4, 2));
	}
	
	@Test
	public void testCharCase()
	{
		boolean actual;

		actual = TestingMethods.charCase(true, 'A');
		assertTrue("char is upper", actual);

		actual = TestingMethods.charCase(true, 'a');
		assertFalse("char is lower", actual);

		actual = TestingMethods.charCase(true, '/');
		assertFalse("char is neither upper or lower", actual);
	}

	@Test
	public void testSubstr()
	{
		assertEquals("str2 is inside str1","science", TestingMethods.substr("conscience", "science"));
		assertEquals("str2 is not inside str 1", "", TestingMethods.substr("hello", "goodbye"));
		assertEquals("str1 is inside str2", "the", TestingMethods.substr("the", "there"));
		assertEquals("str1 is not inside str2", "", TestingMethods.substr("why", "try"));
		assertEquals("str1 and str2 are the same", "yes", TestingMethods.substr("yes", "yes"));
		assertEquals("str1 is empty", "", TestingMethods.substr("", "no"));
		assertEquals("str2 is empty", "", TestingMethods.substr("coud", ""));
		assertEquals("both str1 and str2 are empty", "", TestingMethods.substr("", ""));

	}

	@Test
	public void testUVRating()
	{
		double [] index = {-1, 2, 4, 7.5, 10, 13};
		String [] exp = {"-", "low", "moderate", "high", "very high", "extreme"};

		String [] msg = {"index is invalid", "index is low", "index is moderate", "index is high", "index is very high", "index is extreme"};

		for(int i = 0; i < index.length; i++)
		{
			assertEquals(msg[i], exp[i], TestingMethods.uvRating(index[i]));
		}
	}
}




