import java.util.*;
import java.io.*;

public class StudentApp
{
    public static void main(String[] args)
	{
		Student[] studentArray = new Student[20]
        /*Student studentOne = new Student("John Smith", 21374652, 82.5);
        System.out.println("Student 1 is " + studentOne.toString());

        Student studentTwo = new Student("Bill Best", 14375832, 51.2);
        System.out.println("Student 2 is " + studentTwo.toString());

        Student studentThree = new Student(studentOne);
        System.out.println("Student 3 is " + studentThree.toString());
    
        Student studentFour = new Student();
        System.out.println("Student 4 is " + studentFour.toString());*/

		readFile("data01.csv", studentArray);

		/*Student studentFive = new Student(Student.toFileString("data01.csv"));
		System.out.println("Student 5 is " + studentFive.toString());*/
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
