package net.amicity.pc.sensors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

import net.amicity.common.core.ContextItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.SensorModule;
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
		
		
		wirelessItem = new WirelessResult();
	
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
			     		((WirelessResult) wirelessItem).wifiDetected.add(st.nextToken());
			     		
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
		System.out.println(((WirelessResult) wirelessItem).wifiDetected.size());
		
		
	}
		
}


class WirelessResult implements ContextItem{
	/**
	 * enum used to know the ContextItem's type by ContextManager
	 */
	ContextTypes type;
	/**
	 * arraylist of wireless netowrks detected
	 */
	ArrayList <String> wifiDetected;
	/**
	 * initialising the members
	 */
	WirelessResult(){
		type = ContextTypes.WIRELESS_CONTEXT; 
		wifiDetected = new ArrayList<String>();
	}
	
}


