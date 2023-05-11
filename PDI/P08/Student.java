import java.util.*;
import java.io.*;
public class Student
{
    private String name;
    private int studentID;
    private double mark;
	
    //Constructor with parameters
    public Student(String pName, int pStudentID, double pMark)
    {
        name = pName;
        studentID = pStudentID;
        mark = pMark;
    }

    //Copy constructor
    public Student(Student pStudent)
    {
        name = pStudent.getName();
        studentID = pStudent.getStudentID();
        mark = pStudent.getMark();
    }

    //Default constructor
    public Student()
    {
        name = "Jake Jonas";
        studentID = 18439731;
        mark = 75.0;
    }


    public String getName()
	{
    	return name;
	}


	public int getStudentID()
	{
		return studentID;
	}

	public double getMark()
	{
		return mark;
	}

    public void setName (String pName)
	{
		name = pName;
	}

	public void setStudentID (int pStudentID)
	{

        boolean validID = CheckStudentID(pStudentID);

        if(validID = true)
        {
		    studentID = pStudentID;
        }
        else
        {
            //throw exception
			//throw new IllegalArgumentException
        }
        
	}

	public void setMark (double pMark)
	{
        boolean validMark = CheckMark(pMark);

        if(validMark = true)
        {
		    mark = pMark;
        }
        else
        {
            //throw new IllegalArgumentException
        }
	}

	public String getGrade(double pMark)
	{
		String grade = "";
		
		if((pMark >= 0.0) && (pMark <= 49.9))
		{
			grade = "F";
		}

		else if((pMark >= 50.0) && (pMark <= 59.9))
		{
			grade = "P";
		}
		
		else if((pMark >= 60.0) && (pMark <= 69.9))
		{
			grade = "C";
		}

		else if((pMark >= 70.0) && (pMark <= 79.9))
		{
			grade = "D";
		}
		
		else if((pMark >= 80.0) && (pMark <= 100))
		{
			grade = "HD";
		}
		
		return grade;
	}
			
    private boolean CheckStudentID(int pStudentID)
    {
        boolean IDCheck = true;

        if((pStudentID >= 10000000) && (pStudentID <= 99999999))
        {
		    IDCheck = true;
        }
        else
        {
            IDCheck = false;
        }
        
        return IDCheck;
    }

    private boolean CheckMark(double pMark)
    {
        boolean markCheck = true;

        if((pMark >= 0.0) && (pMark <= 100.0))
        {
		    markCheck = true;
        }
        else
        {
            markCheck = false;
        }   

        return markCheck;
    }

    public String toString()
    {
	String grade = getGrade(mark);
        String studentString;
        studentString = name + ", " + studentID + " has the grade: " + grade + " with a mark of " + mark +"%";
        
        return studentString;
    }

	public boolean equals(Object inObject)
	{
		boolean isEqual = false;
		Person inPerson = null;
		if(inObject instanceof Person)
		{
			inPerson = (Person)inObject;
			if(firstName.equals(inPerson.getFirstName()))
				if(familyName.equals(inPerson.getFamilyName()))
					if(dayOfBirth == inPerson.getDayOfBirth())
						if(birthMonth == inPerson.getBirthMonth())
							if(birthYear == inPerson.getBirthYear())
								
									isEqual = true;
	return isEqual;
	}

	/*public static void toFileString(String pData01)
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
	}*/

}

    
