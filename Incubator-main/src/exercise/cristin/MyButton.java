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
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * @author ''Azgabast''
 *
 */
public class MyButton extends JPanel implements GeoManInt{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**
	 * jb - butonul care controleaza culoarea fundalului
	 */
	JButton jb;
	/**
	 * 
	 */
	private GeoMan gm;
	/**
	 * @param g
	 */
	public MyButton(final GeoMan g){
		this.setGm(g);
		init();
		addComponents();
		addListeners();
	}
	
	@Override
	public void init() {		
		jb = new JButton("Negative");
		jb.setBackground(Color.red);
	}
	
	@Override
	public void addComponents() {
		add(jb);
	}
	
	@Override
	public void addListeners() {		
		jb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(getGm().bgcolor == Color.black){
					getGm().bgcolor = Color.white;
					jb.setBackground(Color.red);
				}
				else{
					getGm().setColor(Color.black);
					jb.setBackground(Color.green);
				}
				getGm().jp1.repaint();


			}});
	}

	/**
	 * @return the GeoMan instance
	 */
	public GeoMan getGm()
	{
		return gm;
	}

	/**
	 * @param gm
	 */
	public void setGm(final GeoMan gm)
	{
		this.gm = gm;
	}
}
