import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingExample implements ActionListener
{
    private int clicks;
    private JLabel labelWorld;
    private JFrame frame;
    private JButton button;

	public SwingExample()
	{
        clicks = 0;
        labelWorld = new JLabel("Hello World!");
		frame = new JFrame("Swing Example");

        button = new JButton("Click Me");
        button.addActionListener(this);

        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.add(labelWorld);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(330,330);
		frame.setVisible(true);

        Dimension size = labelWorld.getPreferredSize();
        labelWorld.setBounds(50,50, size.width, size.height);
        //frame.add(labelWorld);
        //Dimension size = button.getPreferredSize();
        button.setBounds(50, 75, size.width, size.height); 

    }

    public void actionPerformed(ActionEvent e)
    {
        clicks++;
        if(clicks %2 == 0)
        {
            labelWorld.setText("Hello World!");
        }
        else
        {
            labelWorld.setText("Goodbye World!");
        }
    }
        
	public static void main(String[] args)
    {
        SwingExample worldMessage = new SwingExample();
	}
}
