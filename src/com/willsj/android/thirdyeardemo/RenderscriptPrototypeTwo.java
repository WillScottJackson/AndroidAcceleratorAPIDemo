package com.willsj.android.thirdyeardemo;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.widget.TextView;

public class RenderscriptPrototypeTwo extends Activity {

	/*Declare a text view here so that they are visible to 
	 * the rest of the program*/
	TextView notifyUser;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		/*Set the layout to that defined in the XML file*/
		setContentView(R.layout.renderscriptprototypetwo);

		/*Bind the text view to the one found in the XML file*/
		notifyUser = (TextView) findViewById(R.id.notifyUser);

		/*Create a new RenderScript and a new object from the generated class*/
		RenderScript mRs = RenderScript.create(this);
		ScriptC_rsprototypetwo mScript = new ScriptC_rsprototypetwo(mRs, getResources(), R.raw.rsprototypetwo);

		/*Initialise this variable which will serve as a count for how 
		 * successfully data has returned from the RenderScript layer*/
		int successes = 0;

		/*Define three integer arrays*/
		int[] A = new int[1000];
		int[] B = new int[1000];
		int[] C = new int[1000];
		
		/*Create a new random object*/
		Random rand = new Random();
		
		/*Fill the two input arrays with random numbers between 1 and 10*/
		for (int i = 0; i < A.length; i++) {
			A[i] = (int) (int) Math.round(rand.nextInt(10));
			B[i] = (int) (int) Math.round(rand.nextInt(10));
		}

		/*Create a new object from the intOperationsRS class*/
		intOperationsRS intOp = new intOperationsRS();

		/*Call the integer addition function*/
		C = intOp.intAdd(A, B, mRs, mScript);

		/*Notify the user that the test is complete, and display the 
		 * result for the success rate. */
		notifyUser.append("\n Integer Additon Test Complete!");
		calculateSuccessRate(successes, C);

		/*Provide the user with some feedback about this prototype*/
		notifyUser.append("\n The disadvantages of this program are:");
		notifyUser.append("\n 1. The data takes a long time to return from RenderScript," +
				"so a significant delay has been added which slows down the running a lot.");
		notifyUser.append("\n 2. Because of this delay, that determines the rate at which this " +
				"program runs, it is the rate determining step if you will. Need to find a way of " +
				"benchmarking the processing of array elements.");
	}

	/*This function calculates the data transfer success rate and notifies
	 * the user about it*/
	public void calculateSuccessRate(int successes, int[] C) {
		/*Test the success of the data transfer back to the Java layer*/
		for (int i = 0; i < C.length; i++) {
			/*If the data doesn't transfer back to the Java layer properly...*/
			if (C[i] == 0 & i != 0) {
			} else {
				/*...Otherwise increment the success index*/
				successes++;
			}
		}

		/*Calculate the success rate, failure rate and then display it. */
		double successrate = (((float) successes)/(C.length)) * 100;
		double failurerrate = 100 - successrate;
		notifyUser.append("\n Success rate = " + successrate + "%");
		notifyUser.append("\n Failre rate =" + failurerrate + "%");

	}
}
