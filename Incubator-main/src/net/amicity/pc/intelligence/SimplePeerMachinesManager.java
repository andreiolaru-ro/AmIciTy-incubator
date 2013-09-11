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
package net.amicity.pc.intelligence;

import java.net.InetAddress;

import java.net.UnknownHostException;
import java.util.ArrayList;

import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.MessageItem;
import net.amicity.common.context_types.OtherDevicesItem;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;



import net.amicity.common.core.ContextStorage;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.communications.DefaultNetLink;
import net.amicity.pc.interfaces.Anunt;
import net.amicity.pc.interfaces.HelpMessage;

/**
 * operates the data from the stations in order to initiate communication
 * 
 * @author vlad
 * 
 */
public class SimplePeerMachinesManager implements PeerMachinesManager, IntelligenceModule{

	/**
	 * list cotinaing "Station" instances with its data
	 */
	ArrayList<Station> stationsReceived;

	/**
	 * a map Location-IP
	 */
	TreeMap<String, String> serversIP;

	/**
	 * initiating the members of the class
	 */
	
	
	DefaultNetLink myDefaultNetLink;
	
	
	/**
	 * obtaining the queues of the core
	 */
	ContextCore myCore;
	
	/**
	 * instance in order to gain acces to its timer and to the name of the 
	 * files changed
	 */
	FileAnalizerModule myFam; 
	
	/**
	 * @param cc : receving all the queues
	 * @param fam : to obtain the files changed
	 */
	public SimplePeerMachinesManager(ContextCore cc, FileAnalizerModule fam){
		stationsReceived = new ArrayList<Station>();
		serversIP = new TreeMap<String, String>();
		addServersIP();
		myDefaultNetLink = new DefaultNetLink();
		myCore = cc;
		myFam = fam;

	}

	/**
	 * adding pairs of data Location-IP
	 */
	public void addServersIP() {
		serversIP.put("CANTI", "192.168.0.193");
		serversIP.put("acasa", "192.168.0.197");
	}

	@Override
	public void addLocationStations(String Ip, boolean server, String location) {

		stationsReceived.add(new Station(Ip, 4501, "1", true, location));

		/*
		 * stationsReceived.add(new Station("172.128.12.6", 5000,"1","CANTI"));
		 * stationsReceived.add(new Station("172.128.12.7", 2333,"2","CANTI"));
		 * stationsReceived.add(new Station("172.128.12.8", 1555,"3","CANTI"));
		 * stationsReceived.add(new Station("172.128.12.9", 7000,"4","CANTI"));
		 * stationsReceived.add(new Station("172.128.12.10", 4000,"5","CANTI"));
		 * stationsReceived.add(new Station("192.168.0.195",
		 * 2323,"6",true,"CANTI")); stationsReceived.add(new
		 * Station("192.168.0.128", 4444,"7",true,"acasa"));
		 */
	}

	@Override
	public String getServerForLocation(String location) {

		for (Map.Entry<String, String> serverIp : serversIP.entrySet()) {
			if (serverIp.getKey().equals(location) == true) {
				addLocationStations("192.168.0.195", true, location);
				return serverIp.getValue();
			}
		}

		return null;
	}

	@Override
	public String getStationIp(Object Id) {

		for (Iterator<Station> it = stationsReceived.listIterator(); it
				.hasNext();) {
			Station st = it.next();
			if (st.Id == Id)
				return st.Ip;

		}
		return null;

	}

	@Override
	public void invoke()
	{	
		OtherDevicesItem devices;
		MessageItem message1, message2;
		ContextStorage dataKept =  myCore.getContextStorage();
		devices = (OtherDevicesItem) dataKept.remove(ContextTypes.OTHER_DEVICES_CONTEXT);
		message1 = (MessageItem) dataKept.remove(ContextTypes.SEND_ITEM_CONTEXT);
		message2 = (MessageItem) dataKept.remove(ContextTypes.RECEIVED_ITEM_CONTEXT);
		
		
		if(devices != null){
			
			ArrayList<Connection> connections = devices.getTheDevices();
			System.out.println("AM FACUT ROST DE DISPOZITIVELE ALTUIA");
			for(Connection cn : connections){
				
				String username = ContextCore.getUsername();
				String filename = myFam.fileChanged.getName();
				Connection cc;
				try
				{ 
					
					System.out.println(" trimit lui cristi datele mele");
					cc = new Connection( InetAddress.getLocalHost(), username, 4501);
					MessageItem mesaj = new MessageItem(username,filename,cc, ContextTypes.SEND_ITEM_CONTEXT  );
					myDefaultNetLink.send(cn, mesaj );
				}
				catch (UnknownHostException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			} 
		}
		else{
			System.out.println("devices ESTE NULL");
		}
		
		
		if(message1  != null){
			HelpMessage  help = new HelpMessage(message1);
			help.createWindow();
			help.addWrite();
		}
		else{
			System.out.println("message1 ESTE NULL");
		}
			
		if(message2  != null){
			Anunt help =	new Anunt(myFam, message2);
			help.start();
		}
		else{
			System.out.println("message2 ESTE NULL");
		}
			

		// TODO Auto-generated method stub
		
	}
	
}
