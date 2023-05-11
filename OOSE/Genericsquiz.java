import java.util.*;

public class Genericsquiz
{
    public static void main(String[] args)
    {
        List<Car> list = new ArrayList<Car>();

        ArrayList<? extends Vehicle> list2 = new ArrayList<Car>();
        
        ArrayList<? extends Car> list3 = new ArrayList<Car>();

        //ArrayList<Sedan> list4 = new ArrayList<Car>();

        ArrayList<?> list5 = new ArrayList<Car>();

        ArrayList<Car> list6 = new ArrayList<Car>();
        
        //ArrayList<? extends Sedan> list7 = new ArrayList<Car>();
        
        //ArrayList<Vehicle> list8 = new ArrayList<Car>();
    }
}