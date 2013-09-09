package net.amicity.pc.interfaces;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Anunt extends JFrame
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

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();
		this.setTitle("Need Help?");
		this.setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JLabel eticheta = new JLabel("Someone will come to help you any minute now");
		Dimension dim = eticheta.getPreferredSize();
		width = dim.width + 20;
		eticheta.setBounds((width - dim.width) / 2, 30, dim.width, dim.height);
		add(eticheta);
		
		
		this.setLocation(dimension.width - width, winSize.height - height);
		this.setVisible(true);
		this.toFront();
		setLayout(null);
		
		
		
		this.setSize(width - 1, height - 1);
		this.setSize(width, height);
		
	}
	
	

}
