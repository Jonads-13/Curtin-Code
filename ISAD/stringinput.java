import java.util.*;

public class stringinput
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String input1 = "", input2 = "";

		System.out.println("enter a string");
		input1 = sc.nextLine();

		System.out.println("enter a string");
		input2 = sc.next();

		System.out.println(input1 + " " + input2);
	}
}
