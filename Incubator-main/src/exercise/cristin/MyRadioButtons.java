/*******************************************************************************
 * Copyright (C) 2013 Neagoe Cristian.
 * 
 * This file is part of AmIciTy-incubator.
 * 
 * AmIciTy-incubator is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package exercise.cristin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class MyRadioButtons extends JPanel implements GeoManInt{
	JRadioButton c1,c2,c3;
	ButtonGroup bg;
	GeoMan gm;
	
	public MyRadioButtons(GeoMan g){
		this.gm = g;
	
		init();
		addComponents();
		addListeners();
	
	}

	@Override
	public void init() {
		c1 = new JRadioButton("1x", null, true);
		c2 = new JRadioButton("2x");
		c3 = new JRadioButton("3x");		
		bg = new ButtonGroup();
	}

	@Override
	public void addComponents() {
		bg.add(c1);
		bg.add(c2);
		bg.add(c3);
		add(c1);
		add(c2);
		add(c3);
	}

	@Override
	public void addListeners() {
		c1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gm.x = 23;
				gm.y = 34;
				gm.d = 40; 
				gm.jp1.repaint();

			}});

		c2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gm.x = 2 * gm.x;
				gm.y = 2 * gm.y;
				gm.d = 2 * gm.d;
				gm.jp1.repaint();

			}});

		c3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gm.x = 3 * gm.x;
				gm.y = 3 * gm.y;
				gm.d = 3 * gm.d;
				gm.jp1.repaint();

			}});
	}
	

}
