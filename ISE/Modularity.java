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
public class Modularity
{
    static double varianceResult;
    static int sumLength;
    static double[] sumList;

    public static void main(String[] args)
    {
        String operation;
        int listLength;
        double[] dataList;
        double result;
        
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
        
        System.out.print("Select a calculation to perform: ");
        operation = input.nextLine();
        
        // Determine which operation was chosen, and perform it.
        if(operation.equals("min"))
        {
            result = minmax(dataList, false);
        }
        else if(operation.equals("max"))
        {
            result = minmax(dataList, true);
        }
        else if(operation.equals("sum"))
        {   
            sumLength = listLength;
            sumList = dataList;
            result = sum();
        }
        else if(operation.equals("mean"))
        {
            sumLength = listLength;
            sumList = dataList;
            result = mean();
        }
        else if(operation.equals("variance"))
        {
            variance(dataList);
            result = varianceResult;
        }
        else if(operation.equals("stddev"))
        {
            result = stddev(dataList);
        }
        else if(operation.equals("product"))
        {
            result = product(0, dataList);
        }
        else if(operation.equals("geommean"))
        {
            result = product(1, dataList);
        }
        else
        {
            System.out.println("Unrecognised operation \"" + operation + "\".");
            result = 0.0;
        }
        System.out.println("Result = " + result);
    }
    
    /**
     * Calculates the sum of the numbers in the sumList variable.
     */
    public static double sum()
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
    public static double mean()
    {
        double sum = 0.0;
        for(int i = 0; i < sumLength; i++)
        {
            sum += sumList[i];
        }
        return sum / (double)sumLength;
    }
    
    /**
     * Calculates the variance of a list of numbers. Stores the result in the 
     * varianceResult variable.
     */
    public static void variance(double[] dataList)
    {
        double average;
        double difference;
        double sumSquares = 0.0;
        
        sumLength = dataList.length;
        sumList = dataList;
        average = mean();
        
        for(int i = 0; i < dataList.length; i++)
        {
            difference = dataList[i] - average;
            sumSquares += difference * difference;
        }
        
        varianceResult = sumSquares / ((int)dataList.length - 1);
    }
    
    /**
     * Calculates the standard deviation of a list of numbers. 
     */
    public static double stddev(double[] dataList)
    {
        variance(dataList);        
        return Math.sqrt(varianceResult);
    }
    
    /** 
     * Determines either the lowest or highest of a list of numbers. The 
     * calcMax parameter says whether to calculate the maximum or minimum. If 
     * calcMax is true, the maximum is found; otherwise, the minimum is found.
     */
    public static double minmax(double[] dataList, boolean calcMax)
    {
        double element;
        double result = dataList[0];
        
        if(calcMax)
        {
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
        }
        else
        {
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
        }
        
        return result;
    }
    
    /**
     * Calculates the product of a list of numbers, and optionally performs an
     * additional operation. If op is 1, we calculate the "geometric mean". If 
     * op is 2, we find the log of the product. Otherwise, we just return the 
     * raw product.
     */
    public static double product(int op, double[] dataList)
    {
        double result;
        double product = 1.0;
        for(int i = 0; i < dataList.length; i++)
        {
            product *= dataList[i];
        }
        
        switch(op)
        {
            case 1:
                result = Math.pow(product, 1.0 / (double)dataList.length);
                break;
                
            case 2:
                result = Math.log(product);
                break;
                
            default:
                result = product;            
        }
        return result;
    }
}
