import java.util.*;
import java.io.*;

public class TestingUtils
{
	public static void main(String[] args)
	{
		testPrintCoordiantes();
		//testReadChar();
		testGuessingGame();
	}	
		public static void testPrintCoordiantes()
		{
			ByteArrayOutputStream capOut = new ByteArrayOutputStream();
			PrintStream orginalOut = System.out;
			System.setOut(new PrintStream(capOut));
			Utils.printCoordinates(12.65, 45.24, 32.78);
			String actualOutput = capOut.toString();
			assert "(12.65,45.24,32.78)\n".equals(actualOutput) : "Coordinates don't match";
			System.setOut(orginalOut);
		}

	/*public static void testReadChar()
	{
		InputStream originalIn = System.in;
		System.setIn(new ByteArrayInputStream("s\n".getBytes()));
		assert "s\n".equals(Utils.readChar("string")) : "user input does match";

		System.setIn(new ByteArrayInputStream("h\ne\nl\nl\no\n".getBytes()));
		assert "".equals(Utils.readChar("string")): "user inpit doesn't match";

		System.setIn(originalIn);
	}*/

	public static void testGuessingGame()
	{
		ByteArrayOutputStream capOut = new ByteArrayOutputStream();
		InputStream originalIn = System.in;
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(capOut));
		String actualOutput = capOut.toString();
		System.setIn(new ByteArrayInputStream("50".getBytes()));
		assert "Enter an Integer: \n50\nCorrect!\n".equals(Utils.guessingGame(50).capOut.toString());
		System.setOut(originalOut);
		System.setIn(originalIn);
	}


		
		

	
}
