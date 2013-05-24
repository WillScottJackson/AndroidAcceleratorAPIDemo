package com.willsj.android.thirdyeardemo;

/*Import the required Java classes, the Calendar class is for using the timing of the test
 * Random is required for generating a random number to use in the test*/
import java.util.Calendar;
import java.util.Random;

/*Import the required Java classes for the Android user interface elements*/
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PrototypeThree extends Activity {

	/*Make these variables visible to the entire program by initialising them here*/
	/*Initialise the user interface elements*/
	ProgressBar testProgress;
	TextView testRunning;
	TextView notifyUser;
	
	/*Initialise three booleans, these will be helpful in communicating
	 * with the thread handler and displaying the right text to the user*/
	boolean testStarted;
	boolean testFinished;
	boolean testCountdown;

	/*Set the maximum and minimum thread priorities*/
	int MAX_PRIORITY = 1;
	int MIN_PRIORITY = 2;

	/*Create handler to communicate with thread*/
	Handler handler = new Handler() {
		@Override
		public void handleMessage (Message msg) {
			/*Retrieve message from background thread, in this case containing the result*/
			String valueReturned = (String)msg.obj;
			
			/*This will run whilst the count down to the test is running*/
			if (testCountdown == true) {
				/*Make the progress spinner invisible to the user*/
				testProgress.setVisibility(View.INVISIBLE);
				testRunning.setText("" + valueReturned);
			}

			/*This will run whilst the actual test is running*/
			if (testStarted == true) {
				/*Make the progress visible to the user*/
				testProgress.setVisibility(View.VISIBLE);
				testRunning.setText("" + valueReturned);
			}

			/*This will run once the test has finished*/
			if (testFinished == true) {
				/*Make the progress bar invisible.*/
				testProgress.setVisibility(View.INVISIBLE);

				/*Then give the user some feedback about the test*/
				notifyUser.append("This test performed as per the previous test but with the assistance of a thread so that a " +
						"progress spinner can run without detracting from the performance of the test");
				notifyUser.append("\n The Main disadvantages of this test are: ");
				notifyUser.append("\n 1. The source code starts to get messy, so putting sections of it into classes will help tidy it up");
				notifyUser.append("\n 2. More tests need to be done really to properly put it through its paces, these will be included in the classes for the later programs. ");			
			}
		}
	};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		//Set the layout as defined in the xml file
		setContentView(R.layout.prototypethree);

		/*Bind the progress spinner with the id defined in the xml layout file*/
		testProgress = (ProgressBar) findViewById(R.id.progressBar1);
		testProgress.setVisibility(View.INVISIBLE);

		/*Bind the text view with the id defined in the XML layout file*/
		testRunning = (TextView) findViewById(R.id.testRunning);
		notifyUser = (TextView) findViewById(R.id.notifyUser);
	}

	/*Method for when the application starts. Basically the test will start running
	 * as soon as this Activity is initialised. */
	public void onStart() {
		super.onStart();

		/*Create a new thread*/
		Thread background = new Thread(new Runnable() {
			public void run() {
				/*Set these booleans to communicate with the handlers*/
				testCountdown = true;
				testStarted = false;
				testFinished = false;

				/*Create a new object from the TimerFunctions class*/
				TimerFunctions sleep = new TimerFunctions();

				/*Count down from 5 to 0, to let the user know the test is about
				 * to start*/
				for (int j = 5; j>-1; j--) {
					String output = "The test will start in " + j + " seconds";
					sleep.timerInSeconds(1);
					Message msg = handler.obtainMessage(1, output);
					handler.sendMessage(msg);
				}
				testCountdown = false;
				testStarted = true;

				/*Notify the user that the test has started*/
				String output = "Test running... Please wait";
				Message msg = handler.obtainMessage(1, output);
				handler.sendMessage(msg);


				/*Put it in a try catch block*/
				try {

					/*Define integer arrays of 3 million elements for the inputs and outputs*/
					int[] A = new int[3000000];
					int[] B = new int[3000000];
					int[] result = new int[3000000];

					/*Initialise an integer for the number of tests that need to be done*/
					int testNumber = 3000000;
					int i;

					/*Create a new random object from the random class*/
					Random rand = new Random();

					/*Fill the input arrays with random numbers between 1 and 10*/
					for (i = 0; i < testNumber; i++) {
						A[i] = (int) Math.round(rand.nextInt(10)); 
						B[i] = (int) Math.round(rand.nextInt(10));
					}

					/*Get the current time and store it in start. Then store the components, minutes, 
					 * seconds and milliseconds*/
					Calendar Start = Calendar.getInstance(); 										
					int startMinute = Start.get(Calendar.MINUTE);									
					int startSecond = Start.get(Calendar.SECOND);									
					int startMillisecond = Start.get(Calendar.MILLISECOND);

					/*Do the 3 million operations*/
					for (i = 0; i < testNumber; i++) {
						result[i] = A[i] + B[i];
					}

					/*Get the current time, which will be some time later after the addition 
					 * operations have been done, and then store it in the variable Finish*/
					Calendar Finish = Calendar.getInstance(); 										
					int finishMinute = Finish.get(Calendar.MINUTE);													
					int finishSecond = Finish.get(Calendar.SECOND);													
					int finishMillisecond = Finish.get(Calendar.MILLISECOND);

					/*To calculate the time taken for the test, subtract the time at the end of the test
					 * from the time at the start of the test. To make sure that the number returned is positive
					 * use the absolute value function*/
					int minutesTaken = Math.abs(finishMinute - startMinute);
					int secondsTaken = Math.abs(finishSecond - startSecond);
					int millisecondsTaken = Math.abs(finishMillisecond - startMillisecond);

					/*Create a string and fill it with data and text to notify the user*/
					output = "The test is finished. The time taken is " + minutesTaken +
							" minutes, " + secondsTaken + " seconds and " + millisecondsTaken + 
							" milliseconds.";

					/*Put the data in a message to send to the thread handler*/
					msg = handler.obtainMessage(1, output);
					testFinished = true;

					/*Send the message to the handler*/
					handler.sendMessage(msg);
				} catch (Throwable t) {
					/*End the thread and notify the user there was an error*/
					output = "Error detected, ending the thread";
					msg = handler.obtainMessage(1, output);
					handler.sendMessage(msg);
				}
			}
		});

		/*Give the thread maximum priority so that executes the program as fast as it can*/
		background.setPriority(MAX_PRIORITY);
		background.start();
	}
}