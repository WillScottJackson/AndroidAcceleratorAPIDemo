package com.willsj.android.thirdyeardemo;

import java.util.Calendar;
import java.lang.Math;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PrototypeTwo extends Activity {

	/*Declare two text views here, this makes them visible to other functions
	 * outside of the onCreate method*/
	TextView notifyUser;
	TextView Description;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*Set the layout as according to the layout defined in the XML layout file*/
		setContentView(R.layout.prototypetwo);

		/*Bind the text views to their IDs which are in the XML layout file*/
		notifyUser = (TextView) findViewById(R.id.textView1);
		Description = (TextView) findViewById(R.id.textView2);

		/*Bind the button to the ID found in the XML layout file*/
		Button startButton = (Button) findViewById(R.id.startButton);

		/*Create a click listener for the button defined above*/
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				/*Initialise a floating point arrays of 3 million elements*/
				float[] A = new float[3000000];                  
				float[] B = new float[3000000];
				float[] result = new float[3000000];

				/*Initialise an index*/
				int counter = 0;

				/*Fill the arrays with random numbers*/
				for (int i = 0; i < 3000000; i++) {
					A[i] = (float) Math.random();
					B[i] = (float) Math.random();
				}

				/*Get the current time and store it a variable*/
				Calendar Start = Calendar.getInstance();

				/*Get the current time and store it in a variables 
				 * of minutes, seconds and milliseconds*/
				int startMinute = Start.get(Calendar.MINUTE);
				int startSecond = Start.get(Calendar.SECOND);
				int startMillisecond = Start.get(Calendar.MILLISECOND);

				/*Run this block 3 million times, adding two elements together and 
				 * incrementing the counter on each iteration*/
				for (int i =0; i < 3000000; i++) {
					result[i] = A[i] + B[i];
					counter++;
				}

				/*Get the current time after the test has finished*/
				Calendar now = Calendar.getInstance();

				/*Store the components of time into other variables
				 * that represent the time after the test has finished*/
				int nextMinute = now.get(Calendar.MINUTE);
				int nextSecond = now.get(Calendar.SECOND);
				int nextMillisecond = now.get(Calendar.MILLISECOND);

				/*Get the time taken by finding the difference between the 
				 * start and the end time. */
				int minutesTaken = nextMinute - startMinute;
				int secondsTaken = nextSecond - startSecond;
				int millisecondsTaken = nextMillisecond - startMillisecond;

				/*Display the result to the user*/
				Description.setText("");
				notifyUser.append("\n Test Complete");
				notifyUser.append("\n The test took " + Math.abs(minutesTaken) + " minutes, " + Math.abs(secondsTaken) + " seconds and " + Math.abs(millisecondsTaken) + " milliseconds.");
				notifyUser.append("\n The test did " + counter + " loops");
				notifyUser.append("\n ");
				notifyUser.append("\n The disadvantages of this test are:");
				notifyUser.append("\n 1. The resolution is in milliseconds");
				notifyUser.append("\n 2. The user thinks the program has crashed, so some sort of visual feedback is required");
				notifyUser.append("\n 3. The source code needs to be tidied up, so some of the code will be put into classes");
			}
		}
				);

	}
}