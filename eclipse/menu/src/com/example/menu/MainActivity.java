package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu; 
import android.view.View;
import android.widget.ImageView;

 
public class MainActivity extends Activity {

	ImageView image;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
  
  	  
 	  public void another(View view) 
  	  {
  		  Intent intent = new Intent(this, mainpage.class);
		  startActivity(intent);
		  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		  finish();
  	  }
 	  public void test(View view) 
  	  {
  		  Intent intent = new Intent(this, Test.class);
		  startActivity(intent);
		  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		  finish();
  	  }
 	  
	  public void steps(View view) 
  	  {
  		  Intent intent = new Intent(this, Help.class);
		  startActivity(intent);
		  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		  finish();
  	  }
	  public void results(View view) 
  	  {
  		  Intent intent = new Intent(this, Results.class);
		  startActivity(intent);
		  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		  finish();
  	  }
 	  

	}


