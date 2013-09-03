package net.amicity.pc;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * The PC interface. It will notify when the pc receives a sound, accelerometer
 * or file notification.
 * 
 * @author ''Azgabast''
 * 
 */
public class PCInterface extends JFrame {

	/**
	 * 
	 */
	int h;
	/**
	 * 
	 */
	int w;
	/**
	 * 
	 */
	JTextArea jtf;
	/**
	 * 
	 */
	JTextField usrname;
	/**
	 * 
	 */
	JPanel jp;
	/**
	 * 
	 */
	JPanel jpuser;
	/**
	 * 
	 */
	JScrollPane jsp;
	/**
	 * 
	 */
	JButton b;
	/**
	 * 
	 */
	String user;

	/**
	 * 
	 */
	public PCInterface() {
		user = "";
		h = 500;
		w = 400;
		this.setTitle("PC Interface");
		this.setSize(w, h);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - w / 2, dim.height / 2 - h / 2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jp = new JPanel();
		jpuser = new JPanel();
		usrname = new JTextField(15);
		b = new JButton("Login");
		// ActionListener bs;
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (usrname.getText().length() != 0) {
					user = usrname.getText();
					usrname.setText("Logged in as: " + user);
					b.setEnabled(false);
					usrname.setEditable(false);
				}
			}

		});
		jpuser.add(usrname);
		jpuser.add(b);
		jtf = new JTextArea(20, 20);
		setLayout(new GridLayout(2, 1));
		jsp = new JScrollPane(jtf);

		jp.add(jsp);
		add(jpuser);
		add(jp);
		this.setVisible(true);
	}

	/**
	 * @return The username.
	 */
	public String getUserName() {
		return user;
	}

	/**
	 * @param args
	 *            nothing.
	 */
	public static void main(String[] args) {
		PCInterface pci = new PCInterface();
		pci.repaint();

	}

}
