import java.util.Scanner;

/**
 * This is a Java program for performing simple statistical operations on an 
 * array of numbers.
 *
 * This class is part of ISE (ISAD1000/5004) Modularity worksheet.
 *
 * The code here uses arrays and looping, so make sure you're up-to-date with
 * your programming unit!
 */
public class Statistics
{
    public static void main(String[] args)
    {
        String operation;
        int listLength;
        double[] dataList;
        double result = 0.0;
	boolean close = false;
        
        Scanner input = new Scanner(System.in);
        
        // Input array length
        System.out.print("Enter length of list: ");
        listLength = input.nextInt();
        
        // Create new array and input each element
        dataList = new double[listLength]; 
        for(int i = 0; i < listLength; i++)
        {
            System.out.print("Enter real number: ");
            dataList[i] = input.nextDouble();
        }
        input.nextLine(); // Skip the newline after the last value read.
        while(!close)
	{

        System.out.print("Select a calculation to perform: \n>min \n>max \n>sum \n>mean \n>variance \n>stddev \n>product \n>geomean \n>log \n>exit \n \n");
        operation = input.nextLine();
        
        // Determine which operation was chosen, and perform it.
        if(operation.equals("min"))
        {
            result = min(dataList);
	    printResult(result);
        }
        else if(operation.equals("max"))
        {
            result = max(dataList);
	    printResult(result);
        }
        else if(operation.equals("sum"))
	{
	    result = sum(listLength, dataList);
	    printResult(result);
        }
        else if(operation.equals("mean"))
        {
            result = mean(listLength, dataList);
	    printResult(result);
        }
        else if(operation.equals("variance"))
        {
            result = variance(dataList);
	    printResult(result);
        }
        else if(operation.equals("stddev"))
        {
            result = stddev(dataList);
	    printResult(result);
        }
        else if(operation.equals("product"))
        {
            result = product(dataList);
	    printResult(result);
        }
        else if(operation.equals("geommean"))
        {
            result = geommean(dataList);
	    printResult(result);
        }
        else if(operation.equals("log"))
	{
	    result = log(dataList);
	    printResult(result);
	}
	else if(operation.equals("exit"))
	{
	    System.out.println("Goodbye");
	    close = true;
	}
	else
        {
            System.out.println("Unrecognised operation \"" + operation + "\".");
            result = 0.0;
        }
	}
    }
    
    /**
     * Calculates the sum of the numbers in the sumList variable.
     */
    public static double sum(int sumLength, double[] sumList)
    {
        double result = 0.0;
        for(int i = 0; i < sumLength; i++)
        {
            result += sumList[i];
        }
        return result;
    }
    
    
    /**
     * Calculates the mean (average) of the numbers in the sumList variable.
     */
    public static double mean(int sumLength, double[] sumList)
    {
        double sum = sum(sumLength, sumList);
        return sum / (double)sumLength;
    }
    
    /**
     * Calculates the variance of a list of numbers. Stores the result in the 
     * varianceResult variable.
     */
    public static double variance(double[] dataList)
    {
        double average;
        double difference;
        double sumSquares = 0.0;
        
        average = mean(dataList.length, dataList);
        
        for(int i = 0; i < dataList.length; i++)
        {
            difference = dataList[i] - average;
            sumSquares += difference * difference;
        }
        
        double result = sumSquares / ((int)dataList.length - 1);

	return result;
    }
    
    /**
     * Calculates the standard deviation of a list of numbers. 
     */
    public static double stddev(double[] dataList)
    {
        double varianceResult = variance(dataList);        
        return Math.sqrt(varianceResult);
    }
    
    /** 
     * Determines either the lowest or highest of a list of numbers. The 
     * calcMax parameter says whether to calculate the maximum or minimum. If 
     * calcMax is true, the maximum is found; otherwise, the minimum is found.
     */
    public static double max(double[] dataList)
    {
        double element;
        double result = dataList[0];
        
        // Find the highest value in the list.
        for(int i = 1; i < dataList.length; i++)
        {
     	    element = dataList[i];
            if(result < element) 
            {
                // If the next element is higher than the maximum so far, 
                // update the maximum.
                result = element;
            }
        }
	return result;
    }
    public static double min(double[] dataList)
    {
	double element;
	double result = dataList[0];
        // Find the lowest value in the list.
        for(int i = 1; i < dataList.length; i++)
        {
            element = dataList[i];
            if(result > element) 
            {
                // If the next element is lower than the minimum so far, 
                // update the minimum.
                result = element;
	    }
        }  
        return result;
    }
    
    /**
     * Calculates the product of a list of numbers, and optionally performs an
     * additional operation. If op is 1, we calculate the "geometric mean". If 
     * op is 2, we find the log of the product. Otherwise, we just return the 
     * raw product.
     */
    public static double product(double[] dataList)
    {
        double result;
        double product = 1.0;
        for(int i = 0; i < dataList.length; i++)
        {
            product *= dataList[i];
        }
	return product;
    }

    public static double geommean(double[] dataList)
    {
	double product = product(dataList);
        double result = Math.pow(product, 1.0 / (double)dataList.length);
        return result;
    }
                
    public static double log(double[] dataList)
    {
	double product = product(dataList);
        double result = Math.log(product);
        return result;
    }

    public static void printResult(double result)
    {
	System.out.println("\nResult = " + result + "\n");
    }
}

