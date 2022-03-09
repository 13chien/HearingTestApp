package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu; 
import android.view.View;
import android.widget.ImageView;


public class Test extends Activity {

	ImageView image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test); 
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
  
	  public void next(View view) 
  	  {
		  headset();
		  if(Headsetconnection.state ==1)
		  {
	  		  Intent intent = new Intent(this, Oneleft.class);
			  startActivity(intent);
			  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			  finish();
		  }

  	  }

	  public void onBackPressed()
	  {
	  	Intent intent = new Intent(this, MainActivity.class);
	  	startActivity(intent);
	  	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	  	finish();
	  }

		public void headset() //A method to link to HeadsetConnectionReceiver.class to check if the headset is detected 
		{  
			Headsetconnection headsetReceiver = new Headsetconnection(); 
			registerReceiver(headsetReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
		 	if(Headsetconnection.state == 0)  //If there is no headset connected, go back headsetconnection
			{
	       		Intent intent = new Intent(this, Insertheadset.class); //Go to Guide activity and prompt user to put on headset 
	        	startActivity(intent);
		    	finish();
			}
		}	  
	  
	  
 	  

	}

