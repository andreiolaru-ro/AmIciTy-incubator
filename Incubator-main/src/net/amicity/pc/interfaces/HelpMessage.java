package net.amicity.pc.interfaces;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.MessageItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.communications.DefaultNetLink;

/**
 * 
 * frame shown when somebody asks for help. The User can see the files
 * he works on and can give an answer, waether he can help or not
 * 
 * @author vlad
 *
 */
public class HelpMessage extends JFrame implements ActionListener {

	/**
	 * don;t know why it is necessary
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the frame's width
	 */
	int width;
	/**
	 * the frame's height
	 */
	int height;
	/**
	 * if the user asks for help
	 */
	JButton butonYes;
	/**
	 * if the user doesn't need help
	 */
	JButton butonNo;

	/**
	 * the filename for which help is required
	 */
	String myFilename;

	/**
	 * the user which needs help
	 */
	String myUser;
	
	/**
	 * makes the connection to the deviced which asked for help
	 */
	DefaultNetLink  myLink;
	
	/**
	 * gives the data necessary to create the connection
	 */
	Connection connectionRecv;
	
	Rectangle winSize;
	
	Dimension dimension ;


	/**
	 * initialising the window
	 * @param objectReceived : the MessageItem received
	 * 
	 */
	public HelpMessage(Object objectReceived) {
		System.out.println("FEREASTRA A FOST PRIMITA");
		MessageItem myItem = (MessageItem) objectReceived;
		myFilename = myItem.myFilename;
		myUser = myItem.myUser;
		connectionRecv = myItem.connectionSent;
		width = 300;
		height = 200;
		myLink = new DefaultNetLink();

	}

	/**
	 * setting the properties
	 */
	public void createWindow() {

		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		winSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();
		this.setTitle("Need Help?");
		this.setSize(width, height);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
		this.toFront();
		setLayout(null);
	}

	/**
	 * adding the components
	 */
	public void addWrite() {

		JLabel eticheta = new JLabel(myUser + " needs help for: " + myFilename);
		Dimension dim = eticheta.getPreferredSize();
		width = dim.width + 20;
		int mijloc = (width - dim.width) / 2 ;
		eticheta.setBounds((width - dim.width) / 2, 30, dim.width, dim.height);
		width = width + mijloc;
		add(eticheta);

		butonYes = new JButton("Yes, with pleasure");
		dim = butonYes.getPreferredSize();
		butonYes.setBounds(10, 100, dim.width, dim.height);
		add(butonYes);
		butonYes.addActionListener(this);

		butonNo = new JButton("No, too busy");
		Dimension dim2 = butonYes.getPreferredSize();
		butonNo.setBounds(20 + dim.width, 100, dim2.width, dim2.height);
		add(butonNo);
		butonNo.addActionListener(this);

		int buttons_size = dim.width + dim2.height + 150;
		if(width < buttons_size)
			width = buttons_size;
		
		
		System.out.println(width + " " + buttons_size);
		this.setLocation(dimension.width - width, winSize.height - height);
		this.setSize(width - 1, height - 1);
		this.setSize(width, height);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		Connection cc;
		String id = ContextCore.getUsername();

		try {
		if (command.equals("Yes, with pleasure") == true) {
						
				cc = new Connection(InetAddress.getLocalHost(), id, 4501);
				MessageItem mesaj = new MessageItem(id,myFilename,cc, ContextTypes.RECEIVED_ITEM_CONTEXT  );
				System.out.println("AJUTORUL A FOST CONFIRMAT");
				System.out.println(connectionRecv.getId() + " " + connectionRecv.getIp() + " ");
				myLink.send(connectionRecv, mesaj);
				System.out.println("SEND A FOST REALIZAT");
				
			}
			
		
		if (command.equals("No, too busy") == true) {
			cc = new Connection(InetAddress.getLocalHost(), id, 4501);
			MessageItem mesaj = new MessageItem(null,myFilename,cc, ContextTypes.RECEIVED_ITEM_CONTEXT  );
			System.out.println("AJUTORUL A FOST CONFIRMAT");
			System.out.println(connectionRecv.getId() + " " + connectionRecv.getIp() + " ");
			myLink.send(connectionRecv, mesaj);
			System.out.println("SEND A FOST REALIZAT");
			
		}
		dispose();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
