import java.util.*;

public class concurrent {
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("ghi");    
        list.add("jkl");
        for(String s : list)
        {
            list.add(s + s);
        }
        System.out.println(list);
    }
}
