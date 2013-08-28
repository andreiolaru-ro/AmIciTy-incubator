package com.example.soundrepr;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

/**
 * @author ''Azgabast'' The main activity, where I periodically listen to sounds
 *         and represent the highest level heard within the time interval
 */
public class SoundRepr extends Activity
{

	/**
	 * the current value to be displayed
	 */
	double val;

	/**
	 * the recording object
	 */
	MediaRecorder m;
	/**
	 * the textview where to display the value
	 */
	TextView et;
	/**
	 * the timer used to periodically listen to ambient sounds
	 */
	Timer t;

	/**
	 * the series of points representing the time and value
	 */
	GraphViewSeries gvs;
	/**
	 * the array of values to be shown at a time
	 */
	GraphViewData[] gvd;
	/**
	 * the graph object
	 */
	GraphView gv;

	/**
	 * the main method, where the timer is set and runs periodically, every 2s.
	 * It listens for 2 seconds then displays a value corresponding to the
	 * biggest sound heard during that interval.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gvd = new GraphViewData[10];
		for (int i = 0; i < 10; i++)
			gvd[i] = new GraphViewData(0, 0.0d);

		gvs = new GraphViewSeries(gvd);

		gv = new LineGraphView(this // context
				, "Noisiness" // heading
		);
		gv.addSeries(gvs); // data
		// gv.setScrollable(true);
		// gv.setScalable(false);
		gv.setScrollable(false);

		// gv.setViewPort(0, 10);
		LinearLayout layout = (LinearLayout) findViewById(R.id.l);
		layout.addView(gv);

		t = new Timer();
		m = new MediaRecorder();
		et = (TextView) findViewById(R.id.ed);
		m = new MediaRecorder();
		m.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
		m.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		m.setOutputFile(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/ex.3gp");
		m.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try
		{
			m.prepare();
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

		m.start();
		m.getMaxAmplitude();
		final Runnable r = new Runnable()
		{

			@Override
			public void run()
			{
				double d = m.getMaxAmplitude() / 32767.0;
				et.setText("max: " + String.format("%.3g%n", d));
				for (int i = 0; i < 9; i++)
				{
					gvd[i] = new GraphViewData(gvd[i + 1].valueX,
							gvd[i + 1].valueY);
				}
				gvd[9] = new GraphViewData(val, d);
				val = val + 2;
				// gvd[0] = new GraphViewData(0, 1);
				// gvd[1] = new GraphViewData(-1, 0);
				gv.addSeries(gvs);
				// gvs.appendData(gvd[9], true);
				gv.redrawAll();
				m.stop();
				m.reset();
				m.release();
				m = null;
				m = new MediaRecorder();
				m.setAudioSource(MediaRecorder.AudioSource.MIC);
				m.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
				m.setOutputFile(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/ex.3gp");
				m.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				try
				{
					m.prepare();
				}
				catch (Exception e)
				{

					e.printStackTrace();
				}

				m.start();
				m.getMaxAmplitude();

			}

		};

		t.scheduleAtFixedRate(new TimerTask()
		{

			@Override
			public void run()
			{

				runOnUiThread(r);

			}

		}, 500, 2000);

	}

	/**
	 * When the application finishes, the timer needs to be reset and the
	 * recording object gets released.
	 */
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		t.cancel();
		if (m != null)
			m.release();
	}

	/**
	 * The method to create an options menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
