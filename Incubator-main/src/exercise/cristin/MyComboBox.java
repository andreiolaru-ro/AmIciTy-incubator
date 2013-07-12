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
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * @author Cristi
 * 
 */
public class MyComboBox extends JPanel implements GeoManInt
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**
	 * jcb - lista de culori pentru formele geometrice
	 */
	JComboBox					jcb;
	GeoMan						gm;
	/**
	 * h - tabela de asocieri de culori
	 */
	Hashtable<String, Color>	h;

	public MyComboBox(GeoMan g)
	{
		this.gm = g;
		init();
		addComponents();
		addListeners();

	}

	@Override
	public void init()
	{
		String[] colors = { "default", "black", "red", "blue", "green" };
		h = new Hashtable<String, Color>();
		h.put("black", Color.black);
		h.put("red", Color.red);
		h.put("blue", Color.blue);
		h.put("green", Color.green);
		h.put("default", Color.gray);
		jcb = new JComboBox(colors);
	}

	@Override
	public void addComponents()
	{
		add(jcb);
	}

	@Override
	public void addListeners()
	{
		jcb.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JComboBox cb = (JComboBox) e.getSource();
				String col = (String) cb.getSelectedItem();
				gm.color = h.get(col);
				gm.jp1.repaint();

			}
		});
	}

}
