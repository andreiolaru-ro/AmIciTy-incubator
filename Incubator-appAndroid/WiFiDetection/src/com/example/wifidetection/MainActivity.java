package com.example.wifidetection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import android.util.Log;
import android.view.View.OnClickListener;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * activity which initialize the components of the window and the WifiManager
 * used for Wifi,
 * starts scanning for acces points,
 * compares the networks detected with the ones from its own database
 * to establish the location
 * 
 * 
 * @author vlad
 * 
 */
public class MainActivity extends Activity
{

	/**
	 * where the networks detected are shown
	 */
	TextView mainText;
	/**
	 * used for detecting the Wifi's acces points
	 */
	WifiManager mainWifi;
	/**
	 * used for receving the networks detected by the WifiManager
	 */
	WifiReceiver receiverWifi;
	/**
	 * cointains the networks detected
	 */
	List<ScanResult> wifiList;
	/**
	 * collects the information from Scan in order to put it in the TextView
	 */
	StringBuilder sb;
	/**
	 * used for showing the Chart made from the Wifi's signals
	 */
	Button showChartButton;
	/**
	 * contains the signals used for creating the Chart
	 */
	ArrayList<Integer> signalValueList;
	/**
	 * contains the networks names used for creating the Chart
	 */
	ArrayList<String> netNameList;
	/**
	 * creating from the values saved in the activity's history networks
	 */
	TreeMap<String, ArrayList<String>> locationNetPair;
	
	
	/**
	 *  using a timer to restart the scan by registering and unregistering the
	 *  BroadcastReceiver
	 */
	Timer timeToScan;

	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		locationNetPair = new TreeMap<String, ArrayList<String>>();
		timeToScan = new Timer();

		this.mainText = (TextView) findViewById(R.id.mainText);
		showChartButton = (Button) findViewById(R.id.buttonChart);
		receiverWifi = new WifiReceiver(this);
		signalValueList = new ArrayList<Integer>();
		netNameList = new ArrayList<String>();

		showChartButton.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v)
			{

				if (receiverWifi.readyScan == true)
				{
					makeChart();
				}
			}
		});

		mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		mainWifi.setWifiEnabled(true);

		// set as receiver for detected wireless WifiReceiver
		registerReceiver(receiverWifi, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		
		// start scanning
		Log.w("onCreate", "se intra iar in onCreate??");
		
	//	timeToScan.schedule(new TimerTask(){
	//		public void run(){
				mainWifi.startScan();
	//		}
			
	//	},0,6000);
		
	}
	public void onDestroy(){
		super.onDestroy();
		
		mainWifi.setWifiEnabled(false);
	}
	public void onResume(){
		super.onResume();
		registerReceiver(receiverWifi, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	}
	public void onPause(){
		super.onPause();
		this.unregisterReceiver(receiverWifi);
	}

	/**
	 * method called from clicking the button for showing the chart
	 * establishes the objects used for showing the chart
	 */
	public void makeChart()
	{

		// Creating an XYSeries for Income
		XYSeries netWorkSignalsSeries = new XYSeries("Wifi signal-level");
		// Creating an XYSeries for Expense
		// Adding data to Income and Expense Series
		int index = 0;
		for (Integer value : signalValueList)
			netWorkSignalsSeries.add(index++, 0 - value);

		// Creating a dataset to hold each series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// Adding Income Series to the dataset
		dataset.addSeries(netWorkSignalsSeries);

		// Creating XYSeriesRenderer to customize incomeSeries
		XYSeriesRenderer netWorkSignalsRenderer = new XYSeriesRenderer();
		netWorkSignalsRenderer.setColor(Color.rgb(130, 130, 230));
		// incomeRenderer.setFillPoints(true);
		netWorkSignalsRenderer.setLineWidth(5);
		netWorkSignalsRenderer.setDisplayChartValues(true);

		// Creating a XYMultipleSeriesRenderer to customize the whole chart
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
		multiRenderer.setXLabels(0);
		multiRenderer.setChartTitle("WiFi signals/names");
		multiRenderer.setXTitle("WiFi names");
		multiRenderer.setZoomButtonsVisible(true);
		for (int i = 0; i < netNameList.size(); i++)
			multiRenderer.addXTextLabel(i, netNameList.get(i));

		// Adding incomeRenderer and expenseRenderer to multipleRenderer
		// Note: The order of adding dataseries to dataset and renderers to
		// multipleRenderer
		// should be same
		multiRenderer.addSeriesRenderer(netWorkSignalsRenderer);
		multiRenderer.setBarSpacing(3);

		// Creating an intent to plot bar chart using dataset and
		// multipleRenderer
		Intent intent = ChartFactory.getBarChartIntent(getBaseContext(),
				dataset, multiRenderer, Type.DEFAULT);

		// Start Activity
		
		startActivity(intent);
	}

	/**
	 * from comparing the networks detected with the ones saved in history
	 * in case the location is new, we create a new entry in history with
	 * a new location name and the networks detected in this location
	 */
	public void writeNewWifiLocation()
	{

		String wireless = new String("WirelessNetworks");
		try
		{
			File file = new File(getFilesDir(), wireless);

			BufferedWriter bw = new BufferedWriter(
					new FileWriter(file, true));
			bw.append("NewWifiLocation: Location1" + "\n");

			bw.append(mainText.getText());
			bw.close();

			mainText.setText("");

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null)
			{
				mainText.append(line + "\n");
			}
			br.close();
		//	this.deleteFile(wireless);

		}
		catch (IOException e)
		{
			mainText.setText("error");
		}
	}

	/**
	 * comparing the networks detected with the ones from history to know
	 * if it's about a new location ( which will be inserted in the history
	 * file)
	 * or an old location from which we can find the server's ip
	 */
	public void compareLocation()
	{

		String wireless = new String("WirelessNetworks");
		try
		{
			File file = new File(getFilesDir(), wireless);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String linie;
			ArrayList<String> al = new ArrayList<String>();

			while ((linie = br.readLine()) != null)
			{

				StringTokenizer st = new StringTokenizer(linie, " ");
				String s = st.nextToken();
				if (s.compareTo("NewWifiLocation:") == 0)
				{

					al = locationNetPair.get(st.nextToken());
					
				}

				if (s.compareTo("Netname:") == 0)
					al.add(st.nextToken());

				br.close();

			}

		}
		catch (IOException e)
		{

			mainText.setText("error");
		}
	}

}

/**
 * class with the purpose of receiving the networks detected and filling the
 * mainActivity's members with the values detected
 * 
 * 
 * @author vlad
 * 
 */
class WifiReceiver extends BroadcastReceiver
{

	/**
	 * instance of MainActivity in order to populate its members
	 */
	MainActivity main;
	/**
	 * in case the user's pushes the button to show the graph without the
	 * scanning to be complet
	 */
	boolean readyScan;

	/**
	 * 
	 * @param mainReceived
	 *             : instance of the MainActivity in order to populate
	 *             its members
	 */
	WifiReceiver(MainActivity mainReceived)
	{

		main = mainReceived;
		readyScan = false;
	}

	public void onReceive(Context c, Intent intent)
	{
		Log.w("new Scan", "S-a scanat iar, s-a intrat iar in onReceive");
		main.signalValueList.clear();
		main.netNameList.clear();

		main.sb = new StringBuilder();
		main.wifiList = main.mainWifi.getScanResults();

		for (int i = 0; i < main.wifiList.size(); i++)
		{
		//	main.sb.append(Integer.valueOf(i + 1).toString() + ".");
			
			main.sb.append("Netname: " + main.wifiList.get(i).SSID + "\n");
		//	main.sb.append("Adress: " + main.wifiList.get(i).BSSID + "\n");
			main.sb.append("Frecventa: " + main.wifiList.get(i).frequency
					+ "\n");
			main.sb.append("Semnal(dB): " + main.wifiList.get(i).level);
			main.sb.append("\n\n");

			int nr = main.wifiList.get(i).level;
			String nameNet = main.wifiList.get(i).SSID;
			main.signalValueList.add(nr);
			main.netNameList.add(nameNet);
		}
		main.mainText.setText(main.sb);
		readyScan = true;
		// main.compareLocation();
		main.writeNewWifiLocation();
		main.mainWifi.setWifiEnabled(false);
	}

}
