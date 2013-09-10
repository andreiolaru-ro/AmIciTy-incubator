nullnullpackage net.amicity.pc;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	 * The default serial uid.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The window height.
	 */
	int h;
	/**
	 * The window width.
	 */
	int w;
	/**
	 * The text which displays notifications.
	 */
	static JTextArea jtf;
	/**
	 * The textbox where the username is written.
	 */
	JTextField usrname;
	/**
	 * The JPanel that contains the notification textArea.
	 */
	JPanel jp;
	/**
	 * The JPanel that contains the user textbox and the login button.
	 */
	JPanel jpuser;
	/**
	 * A JScrollPane for scrolling through the notifications.
	 */
	JScrollPane jsp;
	/**
	 * The login button.
	 */
	JButton b;
	/**
	 * The username, as extracted from the textbox.
	 */
	String user;

	/**
	 * The Interface constructor, which makes the frame and sets its parameters.
	 * It also adds all the components and sets the listener for the button.
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
		jtf = new JTextArea(20, 20);
		setLayout(new GridLayout(2, 1));
		jsp = new JScrollPane(jtf);

		b = new JButton("Login");

		final MouseListener ml = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getSource() == usrname) {
					usrname.setText("");
				}

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO;
			}
		};

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (usrname.getText().length() != 0
						&& usrname.getText().contains("-")) {
					user = usrname.getText();
					usrname.setText("Logged in as: " + user);
					b.setEnabled(false);
					usrname.setEditable(false);
					usrname.setEnabled(false);
					usrname.removeMouseListener(ml);
				}
			}

		});

		usrname.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (e.getSource() == usrname) {
					if (key == KeyEvent.VK_ENTER) {
						if (usrname.getText().length() != 0
								&& usrname.getText().contains("-")) {
							user = usrname.getText();
							usrname.setText("Logged in as: " + user);
							b.setEnabled(false);
							usrname.setEditable(false);
							usrname.setEnabled(false);
							usrname.removeMouseListener(ml);
						}
						else {
							usrname.setText("Usage: name-device");

						}
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		usrname.addMouseListener(ml);
		jpuser.add(usrname);
		jpuser.add(b);

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
	 * @param notif
	 *            the notification to be added
	 */
	public static void addNotification(String notif) {
		System.out.println(notif);
		jtf.setText(jtf.getText() + "\n" + notif);
		// jtf.append(notif + "\n");
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
