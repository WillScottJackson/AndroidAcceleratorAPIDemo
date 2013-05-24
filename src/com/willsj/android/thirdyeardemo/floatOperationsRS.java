package com.willsj.android.thirdyeardemo;

import com.willsj.android.thirdyeardemo.ScriptField_floatValues.Item;

import android.app.Activity;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.util.Log;

public class floatOperationsRS extends Activity {

	public float[] floatAdd(float[] A, float[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {

		/*Create an array to store the output data on*/
		float[] D = new float[A.length];

		/*The program struggles doing more than 1000 operations at a time, so split them
		 * into multiples of 1000 using this control statement and new function*/
		if (A.length > 1000) {
			float[] E = new float[A.length];
			floatSplitArrays floatSplit = new floatSplitArrays();
			E = floatSplit.addSplitArrays(A, B, mRs, mScript);
			return E;
		} else {

			/*Call the message handler function*/
			D = messageHandler(A.length, mRs);

			/*Save the values to GPU memory*/
			saveToMemory(A, B, mRs, mScript);

			/*Call the addition function*/
			mScript.invoke_floatAdd();

			/*Call the sleep function*/
			sleep();

			/*Return the value back to where it was called from*/
			return D;
		}
	}

	public float[] floatSubtract(float[] A, float[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		float[] D = new float[A.length];

		if (A.length > 1000) {
			float[] E = new float[A.length];
			floatSplitArrays floatSplit = new floatSplitArrays();
			E = floatSplit.subtractSplitArrays(A, B, mRs, mScript);
			return E;
		} else {

			D= messageHandler(A.length, mRs);

			saveToMemory(A, B, mRs, mScript);

			mScript.invoke_floatSubtract();

			sleep();

			return D;
		}
	}

	public float[] floatMultiply(float[] A, float[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		float[] D = new float[A.length];

		if (A.length > 1000) {
			float[] E = new float[A.length];
			floatSplitArrays floatSplit = new floatSplitArrays();
			E = floatSplit.multiplySplitArrays(A, B, mRs, mScript);
			return E;
		} else {

			D = messageHandler(A.length, mRs);

			saveToMemory(A, B, mRs, mScript);

			mScript.invoke_floatMultiply();

			sleep();

			return D;
		}
	}

	public float[] floatDivide(float[] A, float[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		float[] D = new float[A.length];

		if (A.length > 1000) {
			float[] E = new float[A.length];
			floatSplitArrays floatSplit = new floatSplitArrays();
			E = floatSplit.divideSplitArrays(A, B, mRs, mScript);
			return E;
		} else {

			D = messageHandler(A.length, mRs);

			saveToMemory(A, B, mRs, mScript);

			mScript.invoke_floatDivide();

			sleep();

			return D;
		}
	}

	public void saveToMemory(float[] A, float[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {

		/*In the RenderScript layer, allocate memory for a number of structs 
		 * equal to the dimensions of the arrays, data is stored in the vertex memory space of 
		 * the GPU*/
		ScriptField_floatValues Arrays = new ScriptField_floatValues(mRs, A.length, Allocation.USAGE_GRAPHICS_VERTEX);

		/*Then bind the object to the RenderScript*/
		mScript.bind_floatvalues(Arrays);

		/*This creates a new item, which is a function inside the generated
		 * class. This allows the user to set values of variables in 
		 * the RenderScript struct*/
		Item i = new ScriptField_floatValues.Item();

		/*Runs this block of code for the amount of array elements*/
		for (int j = 0; j < A.length; j++) {

			/*Set the values inside the struct to the values in the array elements*/
			i.A = A[j];
			i.B = B[j];

			/*Send to the struct pointed to by the index and copy to memory straight away*/
			Arrays.set(i, j, true);
		}
	}

	public float[] messageHandler(int length, RenderScript mRs) {
		final float[] D = new float[length];

		/*Message handler for the values being sent back from the RenderScript
		 * using the rsSendToClientBlocking() function*/
		mRs.setMessageHandler(new RenderScript.RSMessageHandler(){

			@Override
			public void run() {

				/*Get the message from the rsSendToClientBlocking function.
				 * * Store these values in the variables, mID, mData and mLength*/
				Log.d(" ", String.valueOf(this.mID)+ " " + mData + " " + mLength);

				/*Store the data in an array, where mID is the data being returned and 
				 * mLength is the index of the array.*/
				D[mLength] = mID;
			}	
		});

		/*Send the array back*/
		return D;
	}

	/*Function to create  a two second delay to give the message handler enough
	 * time to retrieve the message from the RenderScript layer*/
	public void sleep() {
		TimerFunctions Sleep = new TimerFunctions();
		Sleep.timerInSeconds(2);
	}

	/*This function will display execution time in nanoseconds in the logcat, note this can be 
	 * connected to PC via USB port whilst running eclipse. But the result will also display on the 
	 * application*/
	public int floatAddBenchmark(float[] A, float[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		/*Use the message handler to retrieve a single value that represents the time
		 * take for the program to execute*/
		float[] t = messageHandler(1, mRs);
		
		/*Save the values of the elements in the arrays to memory*/
		saveToMemory(A, B, mRs, mScript);
		
		/*Call the special floating point addition benchmark function*/
		mScript.invoke_floatAddBenchmark();
		
		/*Wait for a short period of time to give the message handler time to retrieve the 
		 * value from the RenderScript layer*/
		TimerFunctions wait = new TimerFunctions();
		wait.timerInMilliseconds(10);
		return (int) t[0];
	}

	/*This function will display execution time in nanoseconds in the logcat, note this can be 
	 * connected to PC via USB port whilst running eclipse. But the result will also display on the 
	 * application*/
	public int floatSubtractBenchmark(float[] A, float[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		float[] t = messageHandler(1, mRs);
		saveToMemory(A, B, mRs, mScript);
		mScript.invoke_floatSubtractBenchmark();
		TimerFunctions wait = new TimerFunctions();
		wait.timerInMilliseconds(10);
		return (int) t[0];
	}

	/*This function will display execution time in nanoseconds in the logcat, note this can be 
	 * connected to PC via USB port whilst running eclipse. But the result will also display on the 
	 * application*/
	public int floatMultiplyBenchmark(float[] A, float[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		float[] t = messageHandler(1, mRs);
		saveToMemory(A, B, mRs, mScript);
		mScript.invoke_floatMultiplyBenchmark();
		TimerFunctions wait = new TimerFunctions();
		wait.timerInMilliseconds(10);
		return (int) t[0];
	}

	/*This function will display execution time in nanoseconds in the logcat, note this can be 
	 * connected to PC via USB port whilst running eclipse. But the result will also display on the 
	 * application*/
	public int floatDivideBenchmark(float[] A, float[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		float[] t = messageHandler(1, mRs);
		saveToMemory(A, B, mRs, mScript);
		mScript.invoke_floatAddBenchmark();
		TimerFunctions wait = new TimerFunctions();
		wait.timerInMilliseconds(10);
		return (int) t[0];
	}
}
