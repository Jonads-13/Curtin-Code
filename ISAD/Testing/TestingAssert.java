import java.util.*;

public class TestingAssert
{
	public static void main(String[] args)
	{
		testCalcGrade();
		testCalcGradeBnd();
		testRoomVolume();
		testUVRating();
		testCharCase();
		testSubstr();
	}

	public static void testCalcGrade()
	{

		int [] mark = {32, 55, 66, 77, 87, 93, 100, -1, 200};
		String [] exp = {"F", "5", "6", "7", "8", "9", "10", "", ""};
		
		String [] msg = {"mark is between 0 and 49,inclusive", "mark is between 50 and 59 inclusive", "mark is between 60 and 69 inclusive", "mark is between 70 and 79 inclusive", "mark is between 80 and 89 inclusive", "mark is between 90 and 99 inclusive", "mark is 100", "mark is less than 0", "mark is more than 100"};


		for(int i = 0; i < mark.length; i++)
		{ 
			assert exp[i].equals(TestingMethods.calcGrade(mark[i])) : msg[i];
		}
	
	}
	/*	assert "F".equals(TestingMethods.calcGrade(32)) : "mark is between 0 and 49 inclusive";
		assert "5".equals(TestingMethods.calcGrade(55)) : "mark is between 50 and 59 inclusive";
		assert "6".equals(TestingMethods.calcGrade(66)) : "mark is between 50 and 59 inclusive";
		assert "7".equals(TestingMethods.calcGrade(79)) : "mark is between 50 and 59 inclusive";
		assert "8".equals(TestingMethods.calcGrade(87)) : "mark is between 50 and 59 inclusive";
		assert "9".equals(TestingMethods.calcGrade(93)) : "mark is between 50 and 59 inclusive";
		assert "10".equals(TestingMethods.calcGrade(100)) : "mark is 100";
		assert "".equals(TestingMethods.calcGrade(-1)) : "mark is less than 0";
		assert "".equals(TestingMethods.calcGrade(200)) : "mark is more than 100";
	}*/

	public static void testCalcGradeBnd()
	{

		int [] mark = {-1, 0, 49, 50, 59, 60, 69, 70, 79, 80, 89, 90, 99, 100, 101};
		String [] exp = {"", "F", "5","5","6", "6", "7", "7", "8", "8", "9", "9", "10", ""};
		
		String [] msg = {"bnd low invalid", "bnd low F", "bnd high F", "bnd low 5", "bnd high 5","bnd low 6", "bnd high 6","bnd low 7", "bnd high 7","bnd low 8", "bnd high 8", "bnd low 9", "bnd high 9", "bnd 10", "bnd high invalid"};


		for(int i = 0; i < mark.length; i++)
		{ 
			assert exp[i].equals(TestingMethods.calcGrade(mark[i])) : msg[i];
		}
	
	}

	public static void testRoomVolume()
	{
		assert 0 == TestingMethods.roomVolume(1, 2, 2) : "W < 2, L < 2.5, H < 3";
		assert 15 == TestingMethods.roomVolume(2, 2.5, 3) : "W >= 2, L >= 2.5, H >= 3";
		assert 0 == TestingMethods.roomVolume(1, 25, 30) : "W < 2, L >= 2.5, H >= 3";
		assert 0 == TestingMethods.roomVolume(12, 2, 12) : "W >= 2, L < 2.5, H >= 3";
		assert 0 == TestingMethods.roomVolume(10, 12, 2) : "W >= 2, L >= 2.5, H < 3";
		assert 0 == TestingMethods.roomVolume(1, 2, 5) : "W < 2, L < 2.5, H >= 3";
		assert 0 == TestingMethods.roomVolume(3, 2, 1) : "W >= 2, L < 2.5, H < 3";
		assert 0 == TestingMethods.roomVolume(1, 4, 2) : "W < 2, L >= 2.5, H < 3";
	}

	public static void testCharCase()
	{
		boolean actual;
		actual = TestingMethods.charCase(true, 'A');
		assert actual : "char is upper";

		actual = TestingMethods.charCase(true, 'a');
		assert !actual : "char is lower";

		actual = TestingMethods.charCase(true, '/');
		assert !actual : "char is neither upper or lower";
	}

	public static void testSubstr()
	{
		assert "science".equals(TestingMethods.substr("conscience", "science")) : "str2 is inside str1";
		assert "".equals(TestingMethods.substr("hello", "goodbye")) : "str2 is not inside str 1";
		assert "the".equals(TestingMethods.substr("the", "there")) : "str1 is inside str2";
		assert "".equals(TestingMethods.substr("why", "try")) : "str1 is not inside str2";
		assert "yes".equals(TestingMethods.substr("yes", "yes")) : "str1 and str2 are the same";
		assert "".equals(TestingMethods.substr("", "no")) : "str1 is empty";
		assert "".equals(TestingMethods.substr("cloud", "")) : "str2 is empty";
		assert "".equals(TestingMethods.substr("", "")) : "both str1 and str2 are empty";

	}

	public static void testUVRating()
	{
		double [] index = {-1, 2, 4, 7.5, 10, 13};
		String [] exp = {"-", "low", "moderate", "high", "very high", "extreme"};

		String [] msg = {"index is invalid", "index is low", "index is moderate", "index is high", "index is very high", "index is extreme"};

		for(int i = 0; i < index.length; i++)
		{
			assert exp[i].equals(TestingMethods.uvRating(index[i])) : msg[i];
		}
	}
}





