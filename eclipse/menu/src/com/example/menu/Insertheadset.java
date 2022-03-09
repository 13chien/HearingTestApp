package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class Insertheadset extends Activity 
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insertheadset);  
	}
	
	 public void any(View view) 
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
	
	public void headset() //A method to link to HeadsetConnectionReceiver.class to check if the headset is detected 
	{  
		Headsetconnection headsetReceiver = new Headsetconnection(); 
		registerReceiver(headsetReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
	 	if(Headsetconnection.state == 1)  //If there is no headset connected, go back headsetconnection
		{
	 		
       		Intent intent = new Intent(this, Insertheadset.class); //Go to Guide activity and prompt user to put on headset 
        	startActivity(intent);
	    	finish();
		}
	 	
	}
	  public void onBackPressed()
	  {
	  	Intent intent = new Intent(this, Test.class);
	  	startActivity(intent);
	  	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	  	finish();
	  }
	
}


