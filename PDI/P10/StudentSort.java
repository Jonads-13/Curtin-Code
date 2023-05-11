import java.util.*;
import java.io.*;

public class StudentSort
{
    public static void main(String[] args)
    {
		Scanner sc = new Scanner(System.in);

		int choice = 0;
		boolean close = false;
		Student[] studentArray = new Student[8];

		/*for(int i = 0; i < studentArray.length; i++)
		{
			studentArray [i] = new Student();
			System.out.println("Enter Name");
			studentArray[i].setName(sc.nextLine());
			System.out.println("Enter ID");
			studentArray[i].setStudentID(sc.nextInt());
			System.out.println("Enter Mark");
			studentArray[i].setMark(sc.nextDouble());
		}*/

		studentArray [0] = new Student("Jake Jonas", 18439731, 75.5);
		studentArray [1] = new Student("John Smith", 18564829, 80.1);
		studentArray [2] = new Student("Bill Best", 28456482, 64.7);
		studentArray [3] = new Student("Emily Watson", 20198745, 50.8);
		studentArray [4] = new Student("Liam Shaw", 12345678, 90.9);
		studentArray [5] = new Student("Guijong Cabagyo", 87654321,100); 
		studentArray [6] = new Student("Nic Osborne", 65748321, 85.8);
		studentArray [7] = new Student("Jonathan Drummer", 23418754, 69.0);

		System.out.println("Here is your lsit of students:\n");
		for(int i = 0; i < studentArray.length; i++)
		{
			System.out.println("Student " + (i+1) + " " + studentArray[i].toString());
		}

		do
		{
			System.out.println("\nHow would you like to sort?\n>1 By Student ID\n>2 By Student Mark\n>3 Exit\n");
			choice = sc.nextInt();

			if(choice == 1)
			{
				IDSort(studentArray);
			}
		
			else if(choice == 2)
			{
				MarkSort(studentArray);
			}
		
			else if(choice == 3)
			{
				System.out.println("\nGoodbye\n");
				close = true;
			}
			
			else
			{
				System.out.println("Error. Invalid input");
			}

		}while(!close);
	}

	public static void IDSort(Student[] pArray)
	{
		for(int p = 0; p < pArray.length-1 ; p++)
		{
			for(int i = 0; i <pArray.length -1; i++)
			{
				if(pArray[i].getStudentID() > pArray[i+1].getStudentID())
				{ 
					Student temp = pArray[i];
					pArray[i] = pArray[i+1];
					pArray[i+1] = temp;
				}
			}
		}

		System.out.println("\nHere are the students sorted by student ID:\n");

		for(int i = 0; i < pArray.length; i++)
		{
			System.out.println(pArray[i]);
		}
	
	}

	public static void MarkSort(Student[] pArray)
	{
		for(int p = 0; p < pArray.length-1 ; p++)
		{
			for(int i = 0; i <pArray.length -1; i++)
			{
				if(pArray[i].getMark() > pArray[i+1].getMark())
				{ 
					Student temp = pArray[i];
					pArray[i] = pArray[i+1];
					pArray[i+1] = temp;
				}
			}
		}

		System.out.println("\nHere are the marks sorted lowest to highest:\n");

		for(int i = 0; i < pArray.length; i++)
		{
			System.out.println(pArray[i]);
		}

	}
}
