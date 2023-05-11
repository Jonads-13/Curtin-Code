import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;

public class DemoJLabel
{
    public static void main(String [] args)
    {
        JFrame frame = new JFrame ("Programming Design and Implementation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(330,330);

        JLabel labelWorld = new JLabel();

        labelWorld.setText("The legend Virgil van Djik!");

        frame.add(labelWorld);
        frame.setVisible(true);
    }
}
