public class treeheight 
{
    public static void main(String[] args)
    {
        BinarySearchTree one = new BinarySearchTree();

        one.insert("10", 10);
        one.insert("05", 5);
        one.insert("50", 50);
        one.insert("35", 35);
        one.insert("40", 40);
        one.insert("15", 15);
        one.insert("95", 95);
        one.insert("65", 65);
        one.insert("20", 20);

        System.out.println("tree one height = " + one.height());

        BinarySearchTree two = new BinarySearchTree();

        two.insert("05", 5);
        two.insert("10", 10);
        two.insert("20", 20);
        two.insert("30", 30);
        two.insert("40", 40);
        two.insert("50", 50);
        two.insert("60", 60);
        two.insert("70", 70);

        System.out.println("tree two height = " + two.height());

        BinarySearchTree three = new BinarySearchTree();

        three.insert("100", 100);
        three.insert("090", 90);
        three.insert("080", 80);
        three.insert("070", 70);
        three.insert("060", 60);
        three.insert("050", 50);
        three.insert("040", 40);
        three.insert("030", 30);
        three.insert("020", 20);
        three.insert("010", 10);
        three.insert("005", 5);

        System.out.println("tree three height = " + three.height()); 
    }    
}
