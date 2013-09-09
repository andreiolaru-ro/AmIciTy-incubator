package net.amicity.pc.interfaces;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Anunt extends Thread
{
	/**
	 * the frame's width
	 */
	int width = 300;
	/**
	 * the frame's height
	 */
	int height = 100;
	
	
	JButton buton;
	
	public Anunt(){

		
		
	}
	public void run(){
		JFrame frame = new JFrame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();
		frame.setTitle("Need Help?");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JLabel eticheta = new JLabel("Someone will come to help you any minute now");
		Dimension dim = eticheta.getPreferredSize();
		width = dim.width + 20;
		eticheta.setBounds((width - dim.width) / 2, 30, dim.width, dim.height);
		frame.add(eticheta);
		
		
		frame.setLocation(dimension.width - width, winSize.height - height);
		frame.setVisible(true);
		frame.toFront();
		frame.setLayout(null);
		
		
		
		frame.setSize(width - 1, height - 1);
		frame.setSize(width, height);
		
		
		try
		{
			this.sleep(5000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.dispose();
	}
	
	

}
