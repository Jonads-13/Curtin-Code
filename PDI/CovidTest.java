import java.util.*;

public class CovidTest
{
	public static void main(String[] args)
	{
		CovidRecord cr = new CovidRecord();
		System.out.println(cr);

		Country c = new Country();
		System.out.println(c);

		Country aus = new Country("AUS","OC","Australia","AU", -26, 133);
		System.out.println(aus);

		CovidRecord australia = new CovidRecord(aus, "23/12/1996", 56, 356, 35, 63, 36, 24);
		System.out.println(australia);
	}
}
