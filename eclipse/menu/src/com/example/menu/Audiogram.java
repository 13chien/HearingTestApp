package com.example.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
public class Audiogram extends View 
{
	public static boolean LINE = false; //Declare 
	private Paint paint; //Declare function paint to control line features such as color
	private float[] valuesY = new float[] {100}; //Declare array to store Y values
	private float[] valuesX = new float[] {0, 268, 350, 433, 514, 556, 597, 637, 680, 268, 350, 433, 514, 556, 597, 637, 680}; //Declare constant array for X-values 
	float ratioW, ratioH; //Declare variables to store height and width ratio 
	
	public Audiogram(Context context, float[] values, float height, float width,boolean type) 
	{
		super(context);
		ratioW = width; //Store height ratio and width ratio of screen obtained from HearingTestAssessmentActivity to be use as a scaling factor
		ratioH = height;
		if (values == null) //Check if the values from HearingTestAssessmentActivity is transfered correctly
			valuesY = new float[0];
		else
			this.valuesY = values;
		paint = new Paint();
	} 

	@Override
	protected void onDraw(Canvas canvas)
	{ 
		int i;
		int scaledW = (int) (55*ratioW), scaledH = (int) (35*ratioH); //Declare integer variable for scaling factor of cross and circle
		float valuesX1 = 0,valuesX2 = 0,valuesY1 =0,valuesY2 =0; //Declare float variable for storing a scaled value
		Resources res =  this.getResources(); //Declare function to get resource for drawable
		Bitmap bitmapL = BitmapFactory.decodeResource(res, R.drawable.cross_1); //Decode image of cross to a bitmap 
		Bitmap scaledL = Bitmap.createScaledBitmap(  bitmapL, scaledW,  scaledH,  true ); //Scale the bitmap with the scaling factor
		Bitmap bitmapR = BitmapFactory.decodeResource(res, R.drawable.circle_1); //Decode image of circle to a bitmap 
		Bitmap scaledR = Bitmap.createScaledBitmap(  bitmapR,  scaledW,  scaledH,  true ); //Scale the bitmap with the scaling factor

		for ( i= 1; (i+1) < 9; i++) //Use for-loop to draw 5 line and 5 cross for left ear
		{	
			paint.setColor(Color.parseColor("#800000")); //Set the color of the line to Maroon 
			paint.setStrokeWidth(3); //Set thickness of line
			/*multiple the values with the scaling factor*/
			valuesX1 = valuesX[i]*ratioW; 
			valuesX2 = valuesX[i+1]*ratioW;
			valuesY1 = (float)(((valuesY[i]*4.9)+155)*ratioH);
			valuesY2 = (float)(((valuesY[i+1]*4.9)+155)*ratioH);
			
			canvas.drawLine(valuesX1, valuesY1, valuesX2, valuesY2, paint); //Draw line from coordinates (valuesX1,valuesY1) to (valuesX2, valuesY2)
			canvas.drawBitmap(scaledL,valuesX1-(27*ratioW) ,valuesY1-(17*ratioH) , paint); //Draw cross at the start of each line
		}
		canvas.drawBitmap(scaledL,valuesX2-(27*ratioW) ,valuesY2-(17*ratioH) , paint); //Draw the last cross at the end of the last line
			
		for ( i= 9; (i+1) < 17; i++) //Use for-loop to draw 5 line and 5 circle for right ear
		{	
			paint.setColor(Color.BLUE); //Set the color of the line to blue
			paint.setStrokeWidth(3); //Set thickness of line
			/*multiple the values with the scaling factor*/
			valuesX1 = valuesX[i]*ratioW;
			valuesX2 = valuesX[i+1]*ratioW;
			valuesY1 =(float)(((valuesY[i]*4.9)+155)*ratioH);//((valuesY[i]*3)+80)*ratioH;
			valuesY2 = (float)(((valuesY[i+1]*4.9)+155)*ratioH);//((valuesY[i+1]*3)+80)*ratioH;
			
			canvas.drawLine(valuesX1, valuesY1, valuesX2, valuesY2, paint); //Draw line from coordinates (valuesX1,valuesY1) to (valuesX2, valuesY2)
			canvas.drawBitmap(scaledR,valuesX1-(27*ratioW) ,valuesY1-(17*ratioH) , paint); //Draw circle at the start of each line
		}
		canvas.drawBitmap(scaledR,valuesX2-(27*ratioW) ,valuesY2-(17*ratioH) , paint); //Draw the last circle at the end of the last line
	}
}

