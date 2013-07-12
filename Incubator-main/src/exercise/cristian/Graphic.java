package exercise.cristian;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * 
 * @author cristian
 */

public class Graphic extends JFrame implements ItemListener {

	int append;
	boolean activeGo1 = false;
	boolean activeGo2 = false;
	boolean activeGo3 = false;
	boolean create;

	private Vector<String> items = new Vector();
	private Vector<String> items2 = new Vector();

	final ArrayList<DefaultFunctions> functions;

	final JButton go = new JButton("GO");

	Graphic(final ArrayList<DefaultFunctions> functions) {

		super("Quicksilver");

		this.functions = functions;
		this.setLayout(new GridLayout(1, 3));
		try {
			UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		/*---------------------------------generate files------------------------------------------------*/

		File folder = new File("./Folder");
		File[] files = folder.listFiles();

		items2.add("New file");

		for (int i = 0; i < files.length; i++) {
			items.add(files[i].getName());
			items2.add(files[i].getName());
		}
		/*-----------------------------------GO-Button---------------------------------------------------*/
		go.setPreferredSize(new Dimension(100, 20));
		go.setForeground(Color.red);
		go.setEnabled(false);
		/*------------------------------------FILE-IN----------------------------------------------------*/
		JPanel filein = new JPanel();
		filein.setBorder(new TitledBorder("File in"));
		// create button
		final JButton filin = new JButton("File in");
		filin.setPreferredSize(new Dimension(200, 50));
		// create list
		final JComboBox comboin;
		comboin = new JComboBox(items);
		comboin.setPreferredSize(new Dimension(200, 50));
		comboin.setVisible(false);
		// add actions
		filin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				filin.setVisible(false);
				comboin.setVisible(true);
				comboin.setSelectedItem(null);
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {
						comboin.setPopupVisible(true);
					}
				});
				activeGo1 = false;
				go.setEnabled(false);
			}
		});
		comboin.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					filin.setText(comboin.getSelectedItem().toString());
					filin.setVisible(true);
					comboin.setVisible(false);
					activeGo1 = true;
					;
					if (activeGo1 && activeGo2 && activeGo3)
						go.setEnabled(true);
				}
			}
		});
		// add them in JPanel
		filein.add(filin);
		filein.add(comboin);
		// add JPanel in JFrame
		this.add(filein);
		/*------------------------------------FUNCTION---------------------------------------------------*/
		final JPanel func = new JPanel();
		func.setBorder(new TitledBorder("Function"));

		for (int i = 0; i < functions.size(); i++) {
			func.add(functions.get(i).c);
			functions.get(i).c.addItemListener(this);
		}

		this.add(func);
		/*------------------------------------FILE-OUT---------------------------------------------------*/
		final JPanel fileout = new JPanel();
		fileout.setBorder(new TitledBorder("File out"));
		// create button
		final JButton filout = new JButton("File out");
		filout.setPreferredSize(new Dimension(200, 50));
		// create list
		final JComboBox comboout;
		comboout = new JComboBox(items2);
		comboout.setPreferredSize(new Dimension(200, 50));
		comboout.setVisible(false);
		// create text
		final JTextField newFile = new JTextField("file name...");
		newFile.setPreferredSize(new Dimension(200, 50));
		newFile.setVisible(false);
		// create checkbox
		final JCheckBox check = new JCheckBox("append");
		check.setPreferredSize(new Dimension(200, 50));
		// add actions
		filout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				filout.setVisible(false);
				comboout.setVisible(true);
				comboout.setSelectedItem(null);
				SwingUtilities.invokeLater(new Runnable() {

					public void run() {
						comboout.setPopupVisible(true);
					}
				});
				activeGo3 = false;
				go.setEnabled(false);
			}
		});
		comboout.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (comboout.getSelectedItem().toString()
							.equals("New file")) {
						comboout.setVisible(false);
						newFile.setVisible(true);
						newFile.setText("file name...");
					}
					else {
						filout.setText(comboout.getSelectedItem().toString());
						filout.setVisible(true);
						comboout.setVisible(false);
						activeGo3 = true;
					}
					if (activeGo1 && activeGo2 && activeGo3)
						go.setEnabled(true);
				}
			}
		});
		newFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				filout.setText(newFile.getText());
				newFile.setVisible(false);
				filout.setVisible(true);
				create = true;
				activeGo3 = true;
				if (activeGo1 && activeGo2 && activeGo3)
					go.setEnabled(true);
			}
		});
		check.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (check.isSelected())
					append = 1;
				else
					append = 0;
			}
		});
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (create == true) {
					if (!items.contains(filout.getText())) {
						items.add(filout.getText());
						items2.add(filout.getText());
					}

				}
				int k = 0;
				int last = 0;
				ArrayList<String> transformed = new ArrayList();
				for (int i = 0; i < functions.size(); i++) {
					if (functions.get(i).c.isSelected()) {
						if (k == 0) {
							ArrayList<String> text = functions.get(i).read(
									new File("./Folder/" + filin.getText()));
							transformed = functions.get(i).transform(text);
							k++;
							last = i;
						}
						else {
							transformed = functions.get(i).transform(
									transformed);
							last = i;
							k++;
						}
					}
				}
				functions.get(0).write(
						new File("./Folder/" + filout.getText()), append,
						transformed);
			}
		});
		// add them in JPanel
		fileout.add(filout);
		fileout.add(comboout);
		fileout.add(newFile);
		fileout.add(check);
		fileout.add(go);
		// add JPanel in JFrame
		this.add(fileout);

		this.setSize(900, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void itemStateChanged(ItemEvent e) {
		if (functions.get(0).c.isSelected())
			functions.get(1).c.setEnabled(false);
		else
			functions.get(1).c.setEnabled(true);
		if (functions.get(1).c.isSelected())
			functions.get(0).c.setEnabled(false);
		else
			functions.get(0).c.setEnabled(true);
		activeGo2 = false;
		for (int i = 0; i < functions.size(); i++) {
			if (functions.get(i).c.isSelected()) {
				activeGo2 = true;
				break;
			}
		}
		if (activeGo1 && activeGo2 && activeGo3)
			go.setEnabled(true);
		else
			go.setEnabled(false);
	}

}
