import java.util.*;
public class markgrade
{
	public static char markGrade(int pMark)
	{
		int newMark = pMark / 10; char grade;
		switch(newMark)
		{
		case 8: case 9: case 10:
			grade = 'H';
			break;
		case 7:
			grade = 'D';
			break;
		case 6:
			grade = 'C';
			break;
		case 5:
			grade = 'P';
			break;
		default:
			grade = 'F';
		}
		return grade;
	}
}
