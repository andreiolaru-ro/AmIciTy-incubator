package net.amicity.pc.interfaces;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import net.amicity.pc.intelligence.FileAnalizerModule;



/**
 * Framw shown to tell the user that he will receive Aim
 * 
 * @author vlad
 *
 */
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
	
	
	/**
	 * to start the timer;
	 */
	FileAnalizerModule myAnalizer;
	
	/**
	 * @param recvAnalizer : received in order to restart the timer
	 */
	public Anunt(FileAnalizerModule recvAnalizer ){

		myAnalizer = recvAnalizer;
		
	}
	@Override
	public void run(){
		JFrame frame = new JFrame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();
		frame.setTitle("Need Help?");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		
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
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		myAnalizer.getMyTimer().SenderTimeSet("Yes");
		myAnalizer.getMyTimer().startTimer();
		myAnalizer.setShown(false);
		
		
		frame.dispose();
	}
	
	

}
