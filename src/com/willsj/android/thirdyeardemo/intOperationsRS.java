package com.willsj.android.thirdyeardemo;

import com.willsj.android.thirdyeardemo.ScriptField_Values.Item;

import android.app.Activity;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.util.Log;

/*This class sorts out the Arrays and sends them to the appropriate 
 * RenderScript operation, and waits for the data to be sent back*/
public class intOperationsRS extends Activity{

	/*This function allocates memory for the array elements, then sends them off
	 * to get added together, the retrieves the input and sends it back to the 
	 * activity which it was called from*/
	public int[] intAdd(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		
		/*Create an integer array to fill with data to send back*/
		int[] D = new int[A.length];

		/*The program struggles doing more than 1000 operations at a time, so split them
		 * into multiples of 1000 using this control statement and new function*/
		if (A.length > 1000) {
			int[] E = new int[A.length];
			IntSplitArrays intSplit = new IntSplitArrays();
			E = intSplit.addSplitArrays(A, B, mRs, mScript);
			return E;
		} else {

			/*Call the message handler function to fill up the array D with
			 * all of the elements that were processed in the RenderScript source*/
			D = messageHandler(A.length, mRs);

			/*Call the function that allocates memory and then stores values within 
			 * Arrays to GPU vertex memory*/
			saveToMemory(A, B, mRs, mScript);

			/*Call the function contained in the RenderScript source*/
			mScript.invoke_intAdd();
			
			/*Call the sleep function*/
			sleep();
			
			/*Send the array back to where it was called*/
			return D;
		}
	}

	public int[] intSubtract(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		int[] D = new int[A.length];

		if (A.length > 1000) {
			int[] E = new int[A.length];
			IntSplitArrays intSplit = new IntSplitArrays();
			E = intSplit.subtractSplitArrays(A, B, mRs, mScript);
			return E;
		} else {

			D = messageHandler(A.length, mRs);

			saveToMemory(A, B, mRs, mScript);
			mScript.invoke_intSubtract();

			sleep();

			return D;
		}
	}

	public int[] intMultiply(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		int[] D = new int[A.length];

		if (A.length > 1000) {
			int[] E = new int[A.length];
			IntSplitArrays intSplit = new IntSplitArrays();
			E = intSplit.multiplySplitArrays(A, B, mRs, mScript);
			return E;
		} else {

			D = messageHandler(A.length, mRs);

			saveToMemory(A, B, mRs, mScript);
			mScript.invoke_intMultiply();

			sleep();

			return D;
		}
	}

	public void saveToMemory(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {

		/*In the RenderScript layer, allocate memory for a number of structs 
		 * equal to the dimensions of the arrays, data is stored in the vertex memory space of 
		 * the GPU*/
		ScriptField_Values Arrays = new ScriptField_Values(mRs, A.length, Allocation.USAGE_GRAPHICS_VERTEX);

		/*Then bind the object to the RenderScript*/
		mScript.bind_values(Arrays);

		/*This creates a new item, which is a function inside the generated
		 * class. This allows the user to set values of variables in 
		 * the RenderScript struct*/
		Item i = new ScriptField_Values.Item();

		/*Runs this block of code for the amount of array elements*/
		for (int j = 0; j < A.length; j++) {

			/*Set the values inside the struct to the values in the array elements*/
			i.A = A[j];
			i.B = B[j];

			/*Send to the struct pointed to by the index and copy to memory straight away*/
			Arrays.set(i, j, true);
		}
	}

	/*Method that listens for the messages being passed back from RenderScript*/
	public int[] messageHandler(int length, RenderScript mRs) {

		/*Create a new array that will store the values being returned from RenderScript*/
		final int[] D = new int[length];

		/*This listens out for the message...*/
		mRs.setMessageHandler(new RenderScript.RSMessageHandler(){
			@Override
			public void run() {

				/*When plugged in via USB this message will be printed to the logcat. Otherwise
				 * Retrieve the vales, mID, which contains the data, mData has useless information
				 * and mLength is the index for the array*/
				Log.d(" ", String.valueOf(this.mID)+ " " + mData + " " + mLength);

				/*Store the data into the array elements every time a message is received*/
				D[mLength] = mID;
			}	
		});
		return D;
	}
	
	/*This function will create a delay so that the message handler has plenty of time 
	 * to retrieve the elements of the array*/
	private void sleep() {
		TimerFunctions Sleep = new TimerFunctions();
		Sleep.timerInSeconds(2);
	}
	
	/*This function will display execution time in nanoseconds in the logcat, note this can be 
	 * connected to PC via USB port whilst running eclipse. But the result will also display on the 
	 * application*/
	public int intAddBenchmark(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		/*Get a single integer value from the handler, this represents the 
		 * time in microseconds it took for the RenderScript process to execute*/
		int[] t = messageHandler(1, mRs);
		
		/*Save the values in the arrays to memory*/
		saveToMemory(A, B, mRs, mScript);
		
		/*Call the special integer addition benchmark function*/
		mScript.invoke_intAddBenchmark();
		
		/*Wait for just a few milliseconds so that the handler has time to 
		 * Retrieve the time variable from the RenderScript layer*/
		TimerFunctions wait = new TimerFunctions();
		wait.timerInMilliseconds(10);
		
		/*Send the value back from where it was called*/
		return t[0];
	}
	
	/*This function will display execution time in nanoseconds in the logcat, note this can be 
	 * connected to PC via USB port whilst running eclipse. But the result will also display on the 
	 * application*/
	public int intSubtractBenchmark(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		int[] t = messageHandler(1, mRs);
		saveToMemory(A, B, mRs, mScript);
		mScript.invoke_intSubtractBenchmark();
		TimerFunctions wait = new TimerFunctions();
		wait.timerInMilliseconds(10);
		return t[0];
	}
	
	/*This function will display execution time in nanoseconds in the logcat, note this can be 
	 * connected to PC via USB port whilst running eclipse. But the result will also display on the 
	 * application*/
	public int intMultiplyBenchmark(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		int[] t = messageHandler(1, mRs);
		saveToMemory(A, B, mRs, mScript);
		mScript.invoke_intMultiplyBenchmark();
		TimerFunctions wait = new TimerFunctions();
		wait.timerInMilliseconds(10);
		return t[0];
	}
}