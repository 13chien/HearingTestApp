package com.example.menu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Headsetconnection extends BroadcastReceiver 
{
	public static int state =0; //Declare variable state to store the headset state
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		if(intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) //Function to check headset 
		{                     
			state = intent.getIntExtra("state", 0); //Get the state of headset(1 = headset detected, 0 = no headset detected)
		}  
		  
	} 
} 
