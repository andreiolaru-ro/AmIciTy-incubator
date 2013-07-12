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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * @author Cristian
 * @version 1.0
 * @since 2013-07-01
 */
public class GeoMan extends JFrame implements ActionListener{
	/**
	 * lista de butoane
	 */
	ArrayList<JButton> buttonList;
	/**
	 * x, y - lungimea si latimea dreptunghiului desenat
	 */
	 int x = 23, y = 34;
	
	/**
	 * cx, cy - coordonatele centrului cercului desenat
	 */
	int cx = 90, cy = 120;

	/**
	 * pozx, pozy - coltul stanga sus al dreptunghiului
	 */
	int pozx = 15, pozy = 22;

	/** 
	 * d - diametrul cercului
	 */
	 int d = 40;

	/** 
	 * stroke - grosimea liniei de desenat
	 */
	 int stroke = 1;

	/**
	 * bgcolor - culoarea de fundal a desenului
	 */	
	 Color bgcolor = Color.white;
	
	/**
	 * color - culoarea default a formelor geometrice desenate
	 */
	 Color color = Color.gray;

	/**
	 * height - inaltimea ferestrei
	 */	
	final int height = 400;
	
	/**
	 * width - latimea ferestrei
	 */
	final int width = 1000;

	/**
	 * jp1 - panoul din stanga, unde e desenul
	 */
	 JPanel jp1;
	
	/**
	 * jp2 - panoul din dreapta, unde sunt optiunile si butoanele de activare
	 */
	JPanel jp2;
	
	/**
	 * jup - panoul cu butoanele de activare
	 */
	JPanel jup;
	
	/**
	 * jd - panoul cu optiunile
	 */
	JPanel jd;

	/**
	 * h - tabelul in care se tin asocierile de culoare
	 */
	Hashtable<String, Color> h;

	/** 
	 * sp - peretele separator intre ferestre
	 */
	JSplitPane sp;


	/**
	 * constructorul clasei
	 * apeleaza metodele ce realizeaza aplicatia
	 */
	public GeoMan(){
		init();
		addListeners();
		addComponents();
	};
	
	/**
	 * @param c
	 */
	public void setColor( final Color c){
		this.bgcolor = c;
	}
	
	/**
	 * @return
	 */
	public Color getColor (){
		return this.bgcolor;
	}

	/**
	 * init
	 * initializeaza membrii clasei, eventual completandu-i sau
	 * realizand setari de vizibilitate
	 */
	public void init(){

		this.setTitle("Geomanip");
		this.setSize(width, height);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - width/2, dim.height/2 - height/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		buttonList = new ArrayList<JButton>();

		jp1= new MyPanel();
		jp2 = new JPanel();
		sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jp1, jp2);


		jp1.setMinimumSize(new Dimension(300,100));
		jp2.setMinimumSize(new Dimension(500,100));
		jp2.setLayout(new GridLayout(2,1));

		jup = new JPanel();
		jup.setLayout(new GridLayout(1,8));

		for ( int i = 0; i < 8; i++)
			buttonList.add(new JButton(i + " - Off"));

		jd = new JPanel();
		jd.setLayout(new GridLayout(4,2));


	}

	/**
	 * addComponents - metoda de adaugare a componentelor in fereastra mare
	 * se adauga panourile, butoanele si restul componentelor la JFrame si se
	 * face fereastra vizibila 
	 */
	public void addComponents(){

		for( int i = 0; i < 8; i++)
			jup.add(buttonList.get(i));

		jp2.add(jup);


		jd.add(new MySlider(this));
		jd.add(new MyButton(this));
		jd.add(new MyRadioButtons(this));
		jd.add(new MyComboBox(this));
		jd.add(new JPanel());
		jd.add(new JPanel());
		jd.add(new JPanel());
		jd.add(new JPanel());


		jd.getComponent(0).setVisible(false);
		jd.getComponent(1).setVisible(false);
		jd.getComponent(2).setVisible(false);
		jd.getComponent(3).setVisible(false);


		jp2.add(jd); 

		add(sp);

		this.setVisible(true);
	}

	/**
	 * addListeners - metoda de adaugare a ascultatorilor
	 * se adauga ascultatori butoanelor de activare si componentelor de manipulare
	 * a formelor geometrice desenate si a fundalului acestora
	 */
	public void addListeners(){
		for( int i = 0; i <8 ;i++)
			buttonList.get(i).addActionListener(this);

	}


	/**
	 * @param args
	 */
	public static void main(String[] args){
		new GeoMan();
	}
	
	/**
	 * @author Cristian
	 * clasa imbricata ce extinde JPanel
	 * realizeaza desenarea componentelor pe acest panou in
	 * functie de valorile membrilor de dimensiune si aleculorii
	 * alese
	 */
	class MyPanel extends JPanel{
		
		
		@Override
		public void paintComponent(Graphics g){

			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D)g;

			g2.setColor(color);
			g2.setStroke(new BasicStroke(stroke));

			g2.draw(new Rectangle2D.Double(pozx,pozy,x, y));
			g2.draw(new Ellipse2D.Double(cx, cy, d, d));

			jp1.setBackground(bgcolor);

		}
	}

	/**
	 * metoda de tratare a apasarii celor 8 butoane de activare a optiunilor
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		for(int i = 0; i < 8; i++)
			if(s.equals(i + " - Off")){
				buttonList.get(i).setText(i + " - On");
				jd.getComponent(i).setVisible(true);
			}
			else
			if((JButton) e.getSource() == buttonList.get(i))
			{
				buttonList.get(i).setText(i + " - Off");
				jd.getComponent(i).setVisible(false);
			} 
		

	}
}


