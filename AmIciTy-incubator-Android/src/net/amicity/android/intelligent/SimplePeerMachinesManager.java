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
package net.amicity.android.intelligent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import net.amicity.android.communications.DefaultNetLink;
import net.amicity.common.context_types.MessageItem;
import net.amicity.common.core.ContextStorage;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/**
 * operates the data from the stations in order to initiate communication
 * 
 * @author vlad
 * 
 */
public class SimplePeerMachinesManager implements PeerMachinesManager,
		IntelligenceModule {

	Context c;

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
	 * @param cc
	 *            : receving all the queues
	 */
	public SimplePeerMachinesManager(ContextCore cc, Context context) {
		stationsReceived = new ArrayList<Station>();
		serversIP = new TreeMap<String, String>();
		addServersIP();
		myDefaultNetLink = new DefaultNetLink();
		myCore = cc;
		c = context;
	}

	/**
	 * adding pairs of data Location-IP
	 */
	public void addServersIP() {
		serversIP.put("CANTI", "192.168.0.198");
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
	public void invoke() {
		MessageItem message1;
		ContextStorage dataKept = myCore.getContextStorage();
		message1 = (MessageItem) dataKept
				.remove(ContextTypes.SEND_ITEM_CONTEXT);

		AlertDialog.Builder alertDialogB = new AlertDialog.Builder(c);
		alertDialogB.setTitle("HELP, please");
		alertDialogB.setMessage("Someone needs your help");
		alertDialogB.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.e("help send", "A MERS MAHHH");
						// TODO Auto-generated method stub

					}
				});
		AlertDialog alertDialog = alertDialogB.show();

		// show it
		alertDialog.show();

	}

}
