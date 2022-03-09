package com.example.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class Results extends Activity {
	float values0 ,values1 , values2, values3, values4, values5, values6, values7, values8, values9, values10, values11, values12, values13, values14, values15;
	ImageView image;
	 int mode = Activity.MODE_PRIVATE;
	   SharedPreferences mySharedPreferences ;
	   final float values_default = 0;
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results); 

	 	    mySharedPreferences=getSharedPreferences("SavedHL0",mode);
	        values0 = mySharedPreferences.getFloat("HL",values_default);//1l
		    mySharedPreferences=getSharedPreferences("SavedHL1",mode);
	        values1 =mySharedPreferences.getFloat("HL",values_default);//2l
	        mySharedPreferences=getSharedPreferences("SavedHL2",mode); //3l
	        values2 = mySharedPreferences.getFloat("HL",values_default);
	        mySharedPreferences=getSharedPreferences("SavedHL3",mode); //4l
	        values3 = mySharedPreferences.getFloat("HL",values_default);
	        mySharedPreferences=getSharedPreferences("SavedHL4",mode);
	        values4 = mySharedPreferences.getFloat("HL",values_default); //6
	        mySharedPreferences=getSharedPreferences("SavedHL5",mode);   //8
	        values5 = mySharedPreferences.getFloat("HL",values_default);
	        mySharedPreferences=getSharedPreferences("SavedHL6",mode);//250left
	        values6 = mySharedPreferences.getFloat("HL",values_default);
	        mySharedPreferences=getSharedPreferences("SavedHL7",mode);//500left
	        values7 = mySharedPreferences.getFloat("HL",values_default);
	        mySharedPreferences=getSharedPreferences("SavedHL8",mode);
	        values8 = mySharedPreferences.getFloat("HL",values_default); //1k 204;
	        mySharedPreferences=getSharedPreferences("SavedHL9",mode);
	        values9 = mySharedPreferences.getFloat("HL",values_default); //2 257;
	        mySharedPreferences=getSharedPreferences("SavedHL10",mode);
	        values10 =mySharedPreferences.getFloat("HL",values_default); //3 307;
	        mySharedPreferences=getSharedPreferences("SavedHL11",mode); 
	        values11 =mySharedPreferences.getFloat("HL",values_default); //4 357;
	        mySharedPreferences=getSharedPreferences("SavedHL12",mode);
	        values12 =mySharedPreferences.getFloat("HL",values_default); //6 407;
	        mySharedPreferences=getSharedPreferences("SavedHL13",mode);
	        values13 =mySharedPreferences.getFloat("HL",values_default); //8 457;
	        mySharedPreferences=getSharedPreferences("SavedHL14",mode);
	        values14 =mySharedPreferences.getFloat("HL",values_default); //250 103;
	        mySharedPreferences=getSharedPreferences("SavedHL15",mode);
	        values15 =mySharedPreferences.getFloat("HL",values_default); //500 153;
		
		
     DisplayMetrics metrics = new DisplayMetrics(); //Declare DiHLayMetrics function
        getWindowManager().getDefaultDisplay().getMetrics(metrics); //Get the screen size of the device and store in metrics
        float heightR =(float) (metrics.heightPixels/1232.0); //Store the height ratio of the screen with reference of the original (1232)
    	float widthR = (float) (metrics.widthPixels/800.0); //Store the width ratio of the screen with reference of the original (800)

    	float[] values = new float[]{ 0,  values6, values7, values0 ,values1 , values2, values3, values4, values5, values14, values15, values8 ,values9 , values10, values11, values12, values13}; //Store all dB HL values in array
 		Audiogram audiogram = new Audiogram(this, values, heightR, widthR, Audiogram.LINE); //Call Audiogram class to plot audiogram
 		RelativeLayout main = (RelativeLayout) findViewById(R.id.main_view); //Declare RelativeLayout as a variable by its id in the main.xml
 		main.addView(audiogram); //Add the audiogram plotted in Audiogram.class to the diHLay*/
	
	}

	String baseDir =Environment.getExternalStorageDirectory().getAbsolutePath();
	String fileName = "myFile.txt";
	 
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
  
}

