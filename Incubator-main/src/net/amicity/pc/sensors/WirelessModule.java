/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru , Vlad Herescu, Cristian Radu Neagoe, Cristian Grigoras.
 * 
 * This file is part of AmIciTy-incubator.
 * 
 * AmIciTy-incubator is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.pc.sensors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import net.amicity.common.core.ContextItem;
import net.amicity.common.core.SensorModule;
import net.amicity.common.context_types.*;
import net.amicity.common.core.context.ContextCore;


/**
 * 
 * detects wireless networks
 * 
 * @author vlad
 *
 */
public class WirelessModule implements SensorModule{
	
	/**
	 *  a ContextItem which will be added to core's queue
	 */
	ContextItem wirelessItem;

	/**
	 * the location detected used further for connection
	 */
	String location;
	
	/**
	 *  instance of ContextCore to acces its queues
	 */
	ContextCore myCore;
	/**
	 * constructor of the class
	 * initialize its members
	 */
	public WirelessModule(){
		
		
		wirelessItem = new WirelessItem();
	
	}
	
	@Override
	public void connect(ContextCore coreReceived){
		myCore = coreReceived;
		obtainData();
	}

	
	@Override
	public void obtainData(){
			
		
		ProcessBuilder builder = new ProcessBuilder("netsh", "wlan", "show", "networks");
		
		Process process;
		try
		{
			process = builder.start();				// pornesc programul
			InputStream is = process.getInputStream();   // obtin outuputul shellului ca input in program
												// clasa este abstracta
			InputStreamReader isr = new InputStreamReader(is);  // creez o instanta ISR pe baza lui InputStream
			BufferedReader br = new BufferedReader(isr); // transform streamul de biti in caractere
			String line;
			while ((line = br.readLine()) != null) {
			     StringTokenizer st = new StringTokenizer(line,": ");
			     while(st.hasMoreTokens()){
			     	String nameLine= st.nextToken();
			     	if(nameLine.compareTo("SSID") == 0){
			     		st.nextToken();
			     		((WirelessItem) wirelessItem).wifiDetected.add(st.nextToken());
			     		
			     	}
			     	else
			     		break;
			     }
			}
			br.close();
			
		}
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		
	      addDataDetected();

	}
	@Override
	public void addDataDetected(){

		
		
		myCore.postContextUpdate(wirelessItem);
		System.out.println(((WirelessItem) wirelessItem).wifiDetected.size());
		
		
	}
		
}





