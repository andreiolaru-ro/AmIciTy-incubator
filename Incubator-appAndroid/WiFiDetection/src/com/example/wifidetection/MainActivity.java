package com.example.wifidetection;

import java.util.ArrayList;
import java.util.List;
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

public class MainActivity extends Activity
{

	TextView mainText;
	WifiManager mainWifi;
	WifiReceiver receiverWifi;
	List<ScanResult> wifiList;
	StringBuilder sb;
	Button showChartButton;
	ArrayList<Integer> signalValueList;
	ArrayList<String> netNameList;

	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
		mainWifi.startScan();
		mainText.setText("\\nStarting Scan...\\n");
	}

	public void makeChart()
	{

		// Creating an XYSeries for Income
		XYSeries netWorkSignalsSeries = new XYSeries("Wifi signal-level");
		// Creating an XYSeries for Expense
		// Adding data to Income and Expense Series
		for (int i = 0; i < signalValueList.size(); i++)
			netWorkSignalsSeries.add(i, 0 - signalValueList.get(i));

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

}

class WifiReceiver extends BroadcastReceiver
{

	MainActivity main;
	boolean readyScan;

	WifiReceiver(MainActivity mainReceived)
	{

		main = mainReceived;
		readyScan = false;
	}

	public void onReceive(Context c, Intent intent)
	{

		main.sb = new StringBuilder();
		main.wifiList = main.mainWifi.getScanResults();

		for (int i = 0; i < main.wifiList.size(); i++)
		{
			main.sb.append(new Integer(i + 1).toString() + ".");
			main.sb.append("Netname:" + main.wifiList.get(i).SSID + "\n");
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

	}
}
