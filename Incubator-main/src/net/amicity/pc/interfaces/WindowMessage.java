/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Vlad Herescu, Cristian Neagoe, Cristian Grigoras
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.pc.interfaces;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.amicity.common.communications.MessageItem;
import net.amicity.common.core.context.ContextCore;
import net.amicity.common.intelligence.FileAnalizerModule;

/**
 * shows a frma to ask a pesone wheter he wants to contact someone
 * 
 * @author vlad
 * 
 */
public class WindowMessage extends JFrame implements ActionListener {

	/**
	 * don;t know why it is necessary
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the properties of the unmodifief file
	 */
	File myUnmodifiedFile;

	/**
	 * the frame's width
	 */
	final int width;
	/**
	 * the frame's height
	 */
	final int height;
	/**
	 * if the user asks for help
	 */
	JButton butonSave;
	/**
	 * if the user doesn't need help
	 */
	JButton butonAlone;

	/**
	 * to gain acces to shown parameter;
	 */
	FileAnalizerModule myAnalizer;

	/**
	 * initialising the window
	 * 
	 * @param analizerRecv
	 *            : creating a connection between this class and
	 *            FileAnalizerModule
	 * @param unmodifiedFile
	 *            : the properties of the file unmodified
	 */
	public WindowMessage(FileAnalizerModule analizerRecv, File unmodifiedFile) {
		myUnmodifiedFile = unmodifiedFile;
		myAnalizer = analizerRecv;
		width = 300;
		height = 200;
		createWindow();
		addWrite();

	}

	/**
	 * setting the properties
	 */
	public void createWindow() {

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds();
		this.setTitle("Need Help?");
		this.setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(dimension.width - width, winSize.height - height);
		this.setVisible(true);
		this.toFront();
		setLayout(null);
	}

	/**
	 * adding the components
	 */
	public void addWrite() {

		JLabel eticheta = new JLabel("Do you need help for cucu.java?");
		Dimension dim = eticheta.getPreferredSize();
		eticheta.setBounds((290 - dim.width) / 2, 30, dim.width, dim.height);
		add(eticheta);

		butonSave = new JButton("Contact a friend");
		dim = butonSave.getPreferredSize();
		butonSave.setBounds(10, 100, dim.width, dim.height);
		add(butonSave);
		butonSave.addActionListener(this);

		butonAlone = new JButton("No thanks");
		Dimension dim2 = butonSave.getPreferredSize();
		butonAlone.setBounds(20 + dim.width, 100, dim2.width, dim2.height);
		add(butonAlone);
		butonAlone.addActionListener(this);

		this.setSize(width - 1, height - 1);
		this.setSize(width, height);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if (command.equals("Contact a friend") == true) {
			Socket s = ContextCore.getServerSocket();
			ObjectOutputStream out;
			try {
				out = new ObjectOutputStream(s.getOutputStream());
				out.writeObject(new MessageItem(ContextCore.getUsername(),
						myUnmodifiedFile.getName()));
				System.out.println("am trimis un mesaj aluia de langa mine");
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			myAnalizer.getMyTimer().startTimer();
			myAnalizer.setShown(false);
			dispose();
		}
		if (command.equals("No thanks") == true) {
			myAnalizer.getMyTimer().startTimer();
			myAnalizer.setShown(false);
			dispose();
		}

	}

}
