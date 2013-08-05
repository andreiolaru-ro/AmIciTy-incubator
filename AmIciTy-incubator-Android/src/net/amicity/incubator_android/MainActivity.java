package net.amicity.incubator_android;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

/**
 * 
 * class which will start the application thus the connection between the devices
 * @author vlad
 *
 */
public class MainActivity extends Activity {
	
	
    /**
     *  the code used when starting the new activity in order to recognize this
     *  one to send the result Back.
     */
	int requestCode = 1; 
    /**
     *  used just to show that the location determined was received and to show it
     */
	TextView locationReceivedText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	    
	    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        locationReceivedText = (TextView) findViewById(R.id.mainText);
        
        Intent intent = new Intent(this, WifiLocationDetection.class);
        startActivityForResult(intent, requestCode); 
    }
    
  
    public void onActivityResult(int codeReceived, int returnStatus, Intent data){
	    locationReceivedText.setText("se intra aici");
	    
	    if(codeReceived == requestCode){
		    
		    if(returnStatus ==  RESULT_OK){ 
			    
			    String location = data.getStringExtra("result");
			    locationReceivedText.setText(location);
			    
			    if(location.compareTo("No Location detected") != 0){
			    
				    SimplePeerMachinesManager  managerP2P= new SimplePeerMachinesManager();
				    Station d =  managerP2P.getServerForLocation(location);
				    
				    if(d == null)
					    locationReceivedText.setText("No server detected");
				    else{
				    
				   locationReceivedText.setText(d.Ip );
				  
				    
				    // cristi Grig
				    
			/*	   DefaultNetLink test = new DefaultNetLink();
				    
				    try
				    {
					Connection c = new Connection(InetAddress.getByName(d.Ip),
								"gica", 4500);
				//	test.send(c, "hello");
				    }
				    catch (UnknownHostException e)
				    {
					// TODO Auto-generated catch block
					e.printStackTrace();
				    } */ 
			    }
			    }
			    
			    
   
		    } 
	    }
    }

    
}
