package com.willsj.android.thirdyeardemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class JavaBuiltBenchmark extends Activity {

	/*Make these variables visible to the entire program by initialising them here*/
	/*Initialise the user interface elements*/
	ProgressBar testProgress;
	TextView testRunning;
	TextView notifyUser;

	/*Initialise some booleans to represent the state of the test, these effectively
	 * have global scope so that they are visible to both the thread and the handler*/
	boolean testStarted;
	boolean testFinished;
	boolean testCountdown;

	/*Create some integer math test objects from the integer math test class*/
	IntegerMathTest intTest = new IntegerMathTest();

	/*Create some floating point test objects from the floating point math test class*/
	FloatingPointMathTest floatTest = new FloatingPointMathTest();

	/*Set the maximum and minimum thread priorities*/
	int MAX_PRIORITY = 1;
	int MIN_PRIORITY = 2;

	/*Create a handler to communicate with the thread*/
	Handler handler = new Handler() {
		@Override
		public void handleMessage (Message msg) {

			/*Retrieve the message from the background thread, in this case the result of each test*/
			String valueReturned = (String) msg.obj;

			if (testCountdown == true) {
				testProgress.setVisibility(View.INVISIBLE);
				/*Display the result from the thread to the user*/
				testRunning.setText("" + valueReturned);
			}

			if (testStarted == true) {
				testProgress.setVisibility(View.VISIBLE);
				testRunning.setText("" + valueReturned);
			}

			if (testFinished == true) {
				/*When the test has finished, remove the progress
				 * spinner from screen by making it invisible
				 * and then display the final value.*/
				testProgress.setVisibility(View.INVISIBLE);
				notifyUser.append(valueReturned);
			}
		}
	};

	@Override 
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		/*Set the layout as defined in the XML file*/
		setContentView(R.layout.javabuiltbenchmark);

		/*Bind the progress spinner with the id defined in the XML layout file*/
		testProgress = (ProgressBar) findViewById(R.id.progressBar1);
		testProgress.setVisibility(View.INVISIBLE);

		/*Bind the text view with the id defined in the XML layout file*/
		notifyUser = (TextView) findViewById(R.id.notifyUser);
		testRunning = (TextView) findViewById(R.id.Description);

	}

	/*Method for when the application starts*/
	public void onStart() {
		super.onStart();
		
		/*Initialise a new thread that will run the repetitive benchmark
		 * test in. */
		Thread background = new Thread(new Runnable() {
			public void run() {
				
				/*Create an integer tests to do, use this as the input into
				 * the benchmark test functions*/
				int testsToDo = 5000;
				
				/*Put it in a try-catch block to catch an errors and notify the user
				 * if there is a problem.*/
				try {
					
					/*Call the count down function*/
					countdown();
					/*Perform the first test and return the result in a string*/
					String output = intTest.IntegerAdditonTest(testsToDo);
					/*Call the send message function*/
					sendMessage(output);
					
					/*Run the integer subtraction test*/
					countdown();
					output = intTest.IntegerSubtractionTest(testsToDo);
					sendMessage(output);
					
					/*Run the integer multiplication test*/
					countdown();
					output = intTest.IntegerMultiplicationTest(testsToDo);
					sendMessage(output);
					
					/*Run the floating point addition test*/
					countdown();
					output = floatTest.FloatingPointAdditionTest(testsToDo);
					sendMessage(output);
					
					/*Run the floating point subtraction test*/
					countdown();
					output = floatTest.FloatingPointSubtractionTest(testsToDo);
					sendMessage(output);
					
					/*Run the floating point multiplication test*/
					countdown();
					output = floatTest.FloatingPointMultiplicationTest(testsToDo);
					sendMessage(output);
					
					/*Run the floating point division test*/
					countdown();
					output = floatTest.FloatingPointDivisionTest(testsToDo);
					sendMessage(output);
					
				} catch (Throwable t) {
					/*End the thread here if the following block can't be performed*/
					String output = "Error Detected, ending the thread";
					Message msg = handler.obtainMessage(1, output);
					handler.sendMessage(msg);
				}
			}
		});
		/*Give the thread maximum priority so that executes the program as fast as it can*/
		background.setPriority(MAX_PRIORITY);
		background.start();
	}

	public void countdown() {
		
		/*Set the state of the booleans so that the UI elements are changed appropriately in
		 * the handler*/
		testCountdown = true;
		testStarted = false;
		testFinished = false;
		
		/*Create a new object from the timer class (my own class)*/
		TimerFunctions sleep = new TimerFunctions();

		/*Create a for loop that counts down to 0 from 5 to alert the user that the test 
		 * is about to start*/
		for (int i = 5; i>-1; i--) {
			String output = "The test will start in " + i + " seconds";
			sleep.timerInSeconds(1);
			Message msg = handler.obtainMessage(1, output);
			handler.sendMessage(msg);
		}
		
		/*Let the handler know that the countdown has finished and the test has started*/
		testCountdown = false;
		testStarted = true;
		
		/*Notify the user that the test has started*/
		String output = "Test running... Please wait";
		Message msg = handler.obtainMessage(1, output);
		handler.sendMessage(msg);
	}
	
	public void sendMessage(String output) {
		
		/*Let the handler know the test has finished*/
		testStarted = false;
		testFinished = true;

		/*Send the result to the handler*/
		Message msg = handler.obtainMessage(1, output);
		handler.sendMessage(msg);
		
		/*Wait for two seconds so that the user can read the result from the 
		 * previous test without UI elements moving on the screen.*/
		TimerFunctions Sleep = new TimerFunctions();
		Sleep.timerInSeconds(2);
	}
}