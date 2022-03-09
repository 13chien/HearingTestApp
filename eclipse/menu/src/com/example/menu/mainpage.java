package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mainpage extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);  
	}
		
	 	  public void signin (View view)
	  	  {
	  		  Intent intent = new Intent(this, MainActivity.class);
			  startActivity(intent);
			  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			  finish();
	  	  }
	}
 

