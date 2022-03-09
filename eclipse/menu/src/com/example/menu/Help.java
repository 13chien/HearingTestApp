package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu; 
import android.view.View;
import android.widget.ImageView;


public class Help extends Activity {

	ImageView image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help); 
	/*	image =(ImageView) findViewById(R.id.help);
		image =(ImageView) findViewById(R.id.test);
		image =(ImageView) findViewById(R.id.results);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
public void onBackPressed()
{
	Intent intent = new Intent(this, MainActivity.class);
	startActivity(intent);
	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	finish();
	}
  	  
	public void next(View view) 
	  {
		  Intent intent = new Intent(this, Test.class);
		  startActivity(intent);
		  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		  finish();
	  }
 	  

	}
