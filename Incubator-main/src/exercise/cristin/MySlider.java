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
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * @author ''Azgabast''
 *
 */
public class MySlider extends JPanel implements GeoManInt{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * js - sliderul care controleaza grosimea formelor geometrice
	 */
	JSlider js;
	/**
	 * 
	 */
	GeoMan gm;

	/**
	 * @param g the GeoMan instance
	 */
	public MySlider(final GeoMan g){
		this.gm = g;
		init();
		addListeners();
		addComponents();
	}

	@Override
	public void init() {
		js = new JSlider(0,10,0);
		js.setMajorTickSpacing(10);
		js.setMinorTickSpacing(1);
		js.setPaintLabels(true);
		js.setPaintTicks(true);		
	}

	@Override
	public void addComponents() {

		add(js);
	}

	@Override
	public void addListeners() {
		js.addChangeListener(new ChangeListener(){


			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (!source.getValueIsAdjusting()) {
					int val = source.getValue();
					gm.stroke = 1 + val;
					gm.jp1.repaint();
				}

			}});

	}



}
