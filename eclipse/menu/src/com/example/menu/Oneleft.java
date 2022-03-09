package com.example.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class Oneleft extends Activity 
{
	public static float value1l ;//Declare the HL value as float value1l	
    private final double duration = 1.5; // seconds
    private final int sampleRate = 44100;
    private final double numSamples = duration * sampleRate;
    private final double sample[] = new double[(int) numSamples];
    private final byte generatedSnd[] = new byte[(int) (2 * numSamples)];
	boolean isPaused = false; //Declare variable to check if the program is paused
	int maxTop, maxBottom = 0,height, width,textHeight,imageHeight,buttonHeight;// Declare a point for the scroll bar to start and stop
    float mY,mX,totalY; //Declare variables to get the touch values of the scroll bar 
	TextView display; //Declare TextView to display HL values
	int output[]=new int[14];//Declare array to store output HL values
	float heightY;//Declare a variable to store the length between 2 dots on the scroll bar
	float range[]=new float[14];//Declare array to store an acceptable range for scrolling distance of each HL 
	double fix;//Declare a constant to calculate HL
	int mode = Activity.MODE_PRIVATE;// Declare variable mode for saving data mode to be private
	SharedPreferences mySharedPreferences ; // Declare variable mySharedPreferences for saving data function
	int SPL, i, valueHL;//Declare the initial HL value as 35 and the rest as integer
	double amplitude[]=new double[14];//Declare an array for calculating HL
    int[] volume = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    private final double freq = 1000;
	AudioManager am;//Declare AudioManager as am
	AudioTrack audioTrack;
 
	
	

	/** Called when the activity is first created. */
	@Override    
	public void onCreate(Bundle savedInstanceState) 
	{        
		audioTrack =null;
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.oneleft); //Set the layout of the screen using tone1l.xml
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON); //Prevent the screen from dimming
		do{
		loadSound(); //Call the loadSound method
		}while(audioTrack == null);
		loadScroll(); //Call the loadScroll method
 

	}
	
		private void loadScroll() 
		{

			
			display=(TextView)findViewById(R.id.SPL);  //Assign display with a created TextView by finding its id in the tone1l.xml 
			SPL = 25;
			fix =20*(Math.log10(20*Math.pow(10, -6))); //A constant to calculate HL
			for(i=0;i<12;i++) //For-loop to calculate the 12 HL values
	 		{
	 			amplitude[i]=Math.pow(10, (fix+SPL)/20) ;
	 			output[i]=(int)Math.round( (20*Math.log10(amplitude[i])-fix)-7.5+2);
	 			SPL+=5;
	 		}
			
		//	b=(Button)findViewById(R.id.buttonback); //Assign b with a created button by finding its id in the tone6r.xml 
			final ImageView button = (ImageView) findViewById(R.id.scrollbutton); //Declare a created ImageView by finding its id in the tone1l.xml 
		
			/*Function to get the height of the image */
			ViewTreeObserver vto = button.getViewTreeObserver(); 
			vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() 
			{ 
				public boolean onPreDraw() 
				{ 	
					width = button.getMeasuredWidth();
					height =button.getMeasuredHeight();  
				
			
					imageHeight=height;
					maxTop=imageHeight;
					heightY=maxTop/14;
					
					if(totalY>=maxBottom&&totalY<range[0])
					{
						//display.setText(""+output[0]); //Display HL value  
						value1l = output[0]; //Store the HL value
						am.setStreamVolume(AudioManager.STREAM_MUSIC, volume[0], 0); //Control the volume with integer
						button.scrollTo((int) mX,(int) range[0]); //Control the position of scroll bar within the range 
						valueHL = 0; //Store the current value for saving
						//loadback(range[0],valueHL);
					} 
					for(i=0;i<12;i++)
					{
						range[i]=heightY*(i+1);
					}
					return true;  
				} 
			});  
				
		    button.setOnTouchListener(new View.OnTouchListener() //Function to detect touch
		    { 
	            public boolean onTouch(View v, MotionEvent event) 
	            { 
	            	float curY; //Declare a variable to detect touch position
	              	float scrollByY; //Declare a variable to control the scroll bar position
	            
	              	check(null); //Call check method to see if headset is connected
	          
	            	switch (event.getAction()) //Event function to detect finger movement on screen
	            	{ 
	            		case MotionEvent.ACTION_DOWN: //First touch of screen
	            				mY= event.getY(); //Vertical position of finger on first touch
	            				
	            				break; 
	            				
	            		case MotionEvent.ACTION_MOVE: //Movement on screen after touch  
	            				curY = event.getY(); //Vertical position of finger after move
	            				scrollByY=mY-curY; //Distance of movement
	                          
	            				if (curY != mY) //Continue if there is finger movement
	            				{
	            					if ((mY-60)<=curY&&curY<=(mY+60)) //Control the scrolling speed
	            					{
	            						/* Function for scrolling to top of image (image moving to the bottom)*/ 
	            						if (curY > mY) 
	            						{ 
	            							if (totalY == maxBottom) 
	            							{ 
	            								button.scrollTo((int) mX,maxBottom);
	            								scrollByY=0;
	            								display.setText(""+output[0]);           							       
	            								am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[0], 0);
	            							} 
	            							if (totalY > maxBottom) 
	            							{ 
	            								totalY = totalY + scrollByY;
	            							} 
	            							if (totalY < maxBottom) 
	            							{ 
	            								button.scrollTo((int) mX,maxBottom);
	            								scrollByY=0;
	            								totalY = maxBottom; 
	            							} 
	            						} 
       
	            						/* Function for scrolling to bottom of image (picture moving to the top) */
	            						if (curY < mY) 
	            						{
	            							if (totalY == maxTop) 
	            							{ 
	            								button.scrollTo((int) mX, maxTop);
	            								scrollByY=0;
	            								display.setText(""+output[13]);
	            								valueHL = 13;
	            								
	                                  		} 
	            							if (totalY < maxTop) 
	            							{ 
	            								totalY = totalY + scrollByY; 
	            							} 
	            							if (totalY > maxTop) 
	            							{ 
	            								button.scrollTo((int) mX, maxTop);
	            								scrollByY=0; 
	            								totalY = maxTop;
	            								valueHL = 13;
	            								
	            							} 
	            						}
	                         
	            						mY=curY;
	
	            						/* Change the respective items according to the position of scroll bar*/
	            						if(totalY>=maxBottom&&totalY<range[0])
	            						{
	            							//display.setText(""+output[0]); //Display HL value  
	            							value1l = output[0]; //Store the HL value
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC, volume[0], 0); //Control the volume with integer
	            							button.scrollTo((int) mX,(int) range[0]); //Control the position of scroll bar within the range 
	            							valueHL = 0; //Store the current value for saving
	            							//loadback(range[0],valueHL);
	            						} 
	            						else if(totalY>=range[0]&&totalY<range[1])
	            						{ 
	            							//display.setText(""+output[1]);                             
	            							value1l = output[1];
	            			                
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC, volume[1], 0);
	            							button.scrollTo((int) mX,(int) range[1]);
	            							valueHL = 1;
	            							//loadback(range[1],valueHL);
	            						} 
	            						else if(totalY>=range[1]&&totalY<range[2])
	            						{ 
	            							//display.setText(""+output[2]);                        	
	            							value1l = output[2]; 

	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[2], 0);
	            							button.scrollTo((int) mX,(int) range[2]);
	            							valueHL = 2;
	            							//loadback(range[2],valueHL);
	            						} 
	            						else if(totalY>=range[2]&&totalY<range[3])
	            						{ 
	            							//display.setText(""+output[3]);                        	
	            							value1l = output[3];
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[3], 0);
	            							button.scrollTo((int) mX,(int) range[3]);
	            							valueHL = 3;
	            							//loadback(range[3],valueHL);
	            						} 
	            						else if(totalY>=range[3]&&totalY<range[4])
	            						{ 
	            							//display.setText(""+output[4]);                        		
	            							value1l = output[4];
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[4], 0);
	            							button.scrollTo((int) mX,(int) range[4]);
	            							valueHL = 4;
	            							//loadback(range[4],valueHL);
	            						} 
	            						else if(totalY>=range[4]&&totalY<range[5])
	            						{
	            							//display.setText(""+output[5]);                        		
	            							value1l = output[5];

	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[5], 0);
	            							button.scrollTo((int) mX,(int) range[5]);
	            							valueHL = 5;
	            							//loadback(range[5],valueHL);
	            						} 
	            						else if(totalY>=range[5]&&totalY<range[6])
	            						{ 
	            							//display.setText(""+output[6]);                        	
	            							value1l = output[6];
         						
	            						
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[6], 0);
	            							button.scrollTo((int) mX,(int) range[6]);
	            							valueHL = 6;
	            							//loadback(range[6],valueHL);
	            						} 
	            						else if(totalY>=range[6]&&totalY<range[7])
	            						{	 
	            							//display.setText(""+output[7]);                        		
	            							value1l = output[7];
	            					
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[7], 0);
	            							button.scrollTo((int) mX,(int) range[7]);
	            							valueHL = 7;
	            							//loadback(range[7],valueHL);
	            						} 
	            						else if(totalY>=range[7]&&totalY<range[8])
	            						{ 
	            							display.setText(""+output[8]);                        		
	            							value1l = output[8];
         						
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[8], 0);
	            							button.scrollTo((int) mX,(int) range[8]);
	            							valueHL = 8;
	            							//loadback(range[8],valueHL);
	            						} 
	            						else if(totalY>=range[8]&&totalY<range[9])
	            						{
	            							//display.setText(""+output[9]);                        	
	            							value1l = output[9];
         							 am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[9], 0);
	            							button.scrollTo((int) mX,(int) range[9]);
	            							valueHL = 9;
	            							//loadback(range[9],valueHL);
	            						} 
	            						else if(totalY>=range[9]&&totalY<range[10])
	            						{ 
	            							//display.setText(""+output[10]);                        	
	            							value1l = output[10];
	            							
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[10], 0);
	            							button.scrollTo((int) mX,(int) range[10]);
	            							valueHL = 10;
	            							//loadback(range[10],valueHL);
	            					 	} 
	            						else if(totalY>=range[10])
	            						{
	            							//display.setText(""+output[11]);                        	
	            						value1l = output[11];
         	
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[11], 0);
	            							button.scrollTo((int) mX,(int) range[11]); 
	            						valueHL = 11;
	            							//loadback(range[11],valueHL);
	            						} 
	            						/*else if(totalY>=range[11]&&totalY<=range[12])
	            						
	            							//display.setText(""+output[12]);                        	
	            							//value1l = output[12];
         							    
	            							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[12], 0);
	            							button.scrollTo((int) mX,(int) range[12]); 
	            							//valueHL = 12;
	            							//loadback(range[12],valueHL);
	            						}
	            						else if(totalY>=range[12]&&totalY<=range[13])
	            						{
	            							//display.setText(""+output[13]);                        	
	             							//value1l = output[13];
         					
	             							am.setStreamVolume(AudioManager.STREAM_MUSIC,volume[13], 0);
	            							button.scrollTo((int) mX,(int) range[13]); 
	            							//valueHL = 13;
	            							//loadback(range[13],valueHL);
	            						}*/
	            					}
	            				}
	                            break; 
	                         
	            		case MotionEvent.ACTION_UP: //Finger leaving the screen
	            				curY= event.getY();
	            				break;  
	            	} 
	            	return true; 
	            }
	            
		    });

		    	
		}
		
		private void loadSound() 
		{
			     // fill out the array
		            for (int i = 0; i < numSamples; ++i) 
		            {
		                sample[i] = 0.005 *(Math.sin(2 * Math.PI * i / (sampleRate/freq)));
		            }
		            // convert to 16 bit pcm sound array
		            // assumes the sample buffer is normalized.
		            int idx = 0; 
		            for (final double dVal : sample) 
		            {
		                // scale to maximum amplitude
		                final short val = (short) ((dVal * 32767));
		                // in 16 bit wav PCM, first byte is the low order byte
		                generatedSnd[idx++] = (byte) (val & 0x00ff);
		                generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
		            }
	 
		            //track to produce the sound
		        	 audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
		             sampleRate, AudioFormat.CHANNEL_OUT_STEREO,
		             AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
		             AudioTrack.MODE_STATIC); 
		             audioTrack.write(generatedSnd,0, generatedSnd.length);
		             //starting sound level 
		              am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
				      am.setStreamVolume(AudioManager.STREAM_MUSIC,1,0);
				  	 //loop to keep the sound playing continuously  
			          audioTrack.setStereoVolume(1.0f, 0.0f);//, 1.0f); //0.0f, 1.0f); 
			          audioTrack.setLoopPoints(0,generatedSnd.length/4, -1);
			          audioTrack.play();

		    
			
		} 
	
	
	@Override 
	protected void onPause() //Called when the app is not shown on screen
 	{ 
	    super.onPause(); 
	    if (audioTrack != null) //Check if the sound is still playing
	    {
	    	audioTrack.stop();// Stop when it is
   		}
		mySharedPreferences=getSharedPreferences("SavedY1L",mode);// Save new values of the respective item
		mySharedPreferences.edit().putFloat("Y",totalY).commit();
		mySharedPreferences=getSharedPreferences("SavedHL1L",mode);
		mySharedPreferences.edit().putInt("HL",valueHL).commit();  
		mySharedPreferences=getSharedPreferences("Saved1L",mode);
		mySharedPreferences.edit().putFloat("1L",value1l).commit();
		isPaused = true; //To indicate that the program has been paused
	} 
	 
	protected void onResume() //Called when the app is reappear on screen. Usually used after onPause
	{ 
	    super.onResume();
	    if(isPaused == true) //Check if the program has been paused
        {    
            isPaused = false; //Reset variable
	    	finish();
            Intent intent = new Intent(this, Oneleft.class);//Restart this activity
	    	startActivity(intent);
        } 
	}
	public void check(View view) 
	{  
		Headsetconnection headsetReceiver = new Headsetconnection(); 
		registerReceiver(headsetReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
	 	if(Headsetconnection.state == 0)
		{
	 		 if (audioTrack != null) //Check if the sound is still playing
	 	    {
	 	    	audioTrack.stop();// Stop when it is
	    		} 

    		mySharedPreferences=getSharedPreferences("SavedY1L",mode); //Save new values of the respective item
    		mySharedPreferences.edit().putFloat("Y",totalY).commit();
    		mySharedPreferences=getSharedPreferences("SavedHL1L",mode);
    		mySharedPreferences.edit().putInt("HL",valueHL).commit();  
    		mySharedPreferences=getSharedPreferences("SavedHL1",mode);
    		mySharedPreferences.edit().putFloat("HL",value1l).commit();

       		Intent intent = new Intent(this, Insertheadset.class); //Go to Guide activity and prompt user to put on headset
    		intent.putExtra("ClassNum", 1);
    		intent.putExtra("Next", false);
        	startActivity(intent);
        	finish();
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) //A method to control the hardware function of the button
	{
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) //Disable the decreasing volume button
	    {
	    	
	    	if ((valueHL-1)>= 0)
	    	{
	    		valueHL = valueHL-1;
	    		totalY = range[valueHL];
	    		mySharedPreferences=getSharedPreferences("SavedY1L",mode); //Save new values of the respective item
	    		mySharedPreferences.edit().putFloat("Y",totalY).commit();
	    		mySharedPreferences=getSharedPreferences("SavedHL1L",mode);
	    		mySharedPreferences.edit().putInt("HL",valueHL).commit();  
		
	    		loadScroll();
	    	}
	    }
	    if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) //Disable the increasing volume button
	    {
	    	
	    	if ((valueHL+1)<= 13)
	    	{
	    		valueHL = valueHL+1;
	    		totalY = range[valueHL];
	    		mySharedPreferences=getSharedPreferences("SavedY1L",mode); //Save new values of the respective item
	    		mySharedPreferences.edit().putFloat("Y",totalY).commit();
	    		mySharedPreferences=getSharedPreferences("SavedHL1L",mode);
	    		mySharedPreferences.edit().putInt("HL",valueHL).commit();  
		
	    		loadScroll();
			}
	    }
	    if((keyCode == KeyEvent.KEYCODE_BACK)) //Allow the back button to still function
	    {
	    	mySharedPreferences=getSharedPreferences("SavedY1L",mode); //Save new values of the respective item
			mySharedPreferences.edit().putFloat("Y",totalY).commit();
			mySharedPreferences=getSharedPreferences("SavedHL1L",mode);
			mySharedPreferences.edit().putInt("HL",valueHL).commit();  
			mySharedPreferences=getSharedPreferences("Saved1L",mode);
			mySharedPreferences.edit().putFloat("1L",value1l).commit();

			Intent intent = new Intent(this, Insertheadset.class); //Go to Guide activity 
			intent.putExtra("ClassNum", 1);
			intent.putExtra("Next", true);
	    	startActivity(intent);
	    	finish();
	    }
	    return true;
	} 
 
	
	 public void next (View view)
 	  {
		  
		
 		  Intent intent = new Intent(this, Twoleft.class);
		  startActivity(intent);
		  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		  finish();

		  mySharedPreferences=getSharedPreferences("SavedHL0",mode);
  		  mySharedPreferences.edit().putFloat("HL",value1l).commit();

			
 	  }

}

