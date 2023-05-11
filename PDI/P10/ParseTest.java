import java.util.*;
import java.io.*;

public class ParseTest
{
	public static void main(String[] args)
	{
		/*String toInt = "24";
		String toDouble = "25.765";
		String toGetValue = "65.234";

		String[] array = {"23.65", "76.45", "89"};

		System.out.println(toInt + " " + toDouble + " " + toGetValue);

		int num1 = Integer.parseInt(toInt);
		double num2 = Double.parseDouble(toDouble);
		double num3 = Double.valueOf(toGetValue);

		double num4 = Double.parseDouble(array[0]);
		double num5 = Double.valueOf(array[1]);
		int num6 = Integer.parseInt(array[2]);


		double ans1 = num4/num5;
		double ans2 = ans1 + num6;

		System.out.println(ans1 + " " + ans2);*/

		readFile("CovidData.csv");
	}



	public static void readFile(String pData01)
	{
		FileInputStream fileStream = null;
		InputStreamReader isr;
		BufferedReader bufRdr;
		int lineNum;
		String line;
		try
		{
			fileStream = new FileInputStream(pData01);
			isr = new InputStreamReader(fileStream);
			bufRdr = new BufferedReader(isr);
			lineNum = 0;
			line = bufRdr.readLine();
			while(line != null)
			{
				lineNum++;
				readLine(line);
				line = bufRdr.readLine();
			}
				fileStream.close();
		}
		catch(IOException errorDetails)
		{
			if(fileStream != null)
			{
				try
				{
					fileStream.close();
				}
				catch(IOException ex2)
				{ }
			}
			System.out.println("Error in file processing");
		}
	}
	
	public static void readLine(String csvRow)
	{
		String[] splitLine;
		splitLine = csvRow.split(",");
		int lineLength = splitLine.length;

		for(int i = 0; i < lineLength; i++)
		{
			System.out.print(splitLine[i] + " ");
		}
		System.out.println("");
	}
}
