import javax.swing.*;

public class SwingExample
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Swing Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(330,330);
		frame.setLayout(null);

		JLabel labelWorld = new JLabel();
		labelWorld.setText("Hello World!");

		//labelWorld.setLocation(50,50);

		labelWorld.setHorizontalTextPosition(JLabel.LEFT);
		labelWorld.setVerticalTextPosition(JLabel.TOP);

		frame.add(labelWorld);
		frame.setVisible(true);
	}
}
