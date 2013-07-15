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


/**
 * @author ''Azgabast''
 *
 */
public class MyRadioButtons extends JPanel implements GeoManInt{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JRadioButton c1;
	/**
	 * 
	 */
	private JRadioButton c2;
	/**
	 * 
	 */
	private JRadioButton c3;
	/**
	 * 
	 */
	private ButtonGroup bg;
	/**
	 * 
	 */
	private GeoMan gm;
	
	/**
	 * @param g
	 */
	public MyRadioButtons(final GeoMan g){
		this.setGm(g);
	
		init();
		addComponents();
		addListeners();
	
	}

	@Override
	public void init() {
		setC1(new JRadioButton("1x", null, true));
		setC2(new JRadioButton("2x"));
		setC3(new JRadioButton("3x"));		
		setBg(new ButtonGroup());
	}

	@Override
	public void addComponents() {
		getBg().add(getC1());
		getBg().add(getC2());
		getBg().add(getC3());
		add(getC1());
		add(getC2());
		add(getC3());
	}

	@Override
	public void addListeners() {
		getC1().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getGm().x = 23;
				getGm().y = 34;
				getGm().d = 40; 
				getGm().jp1.repaint();

			}});

		getC2().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getGm().x = 2 * getGm().x;
				getGm().y = 2 * getGm().y;
				getGm().d = 2 * getGm().d;
				getGm().jp1.repaint();

			}});

		getC3().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getGm().x = 3 * getGm().x;
				getGm().y = 3 * getGm().y;
				getGm().d = 3 * getGm().d;
				getGm().jp1.repaint();

			}});
	}

	/**
	 * @return the second button
	 */
	public JRadioButton getC2()
	{
		return c2;
	}

	/**
	 * @param c2 the second button
	 */
	public void setC2(final JRadioButton c2)
	{
		this.c2 = c2;
	}

	/**
	 * @return the first button
	 */
	public JRadioButton getC1()
	{
		return c1;
	}

	/**
	 * @param c1 the first button
	 */
	public void setC1(final JRadioButton c1)
	{
		this.c1 = c1;
	}

	/**
	 * @return the third button
	 */
	public JRadioButton getC3()
	{
		return c3;
	}

	/**
	 * @param c3 the third button
	 */
	public void setC3(final JRadioButton c3)
	{
		this.c3 = c3;
	}

	/**
	 * @return the background color
	 */
	public ButtonGroup getBg()
	{
		return bg;
	}

	/**
	 * @param bg the background color
	 */
	public void setBg(final ButtonGroup bg)
	{
		this.bg = bg;
	}

	/**
	 * @return the GeoMan instance
	 */
	public GeoMan getGm()
	{
		return gm;
	}

	/**
	 * @param gm the GeoMan instance
	 */
	public void setGm(final GeoMan gm)
	{
		this.gm = gm;
	}
	

}
