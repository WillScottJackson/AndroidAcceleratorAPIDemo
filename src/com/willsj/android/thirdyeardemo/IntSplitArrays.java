package com.willsj.android.thirdyeardemo;

import android.renderscript.RenderScript;
import android.util.Log;

/*This extends intOperationsRS so that it can use the already existing 
 *saveToMemory function and message handler function */
public class IntSplitArrays extends intOperationsRS{

	/*This function will split any array that has a size greater than 1000*/
	public int[] addSplitArrays(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {

		/*Find the number of times to divide the array into*/
		final int timesSplit = (int) Math.floor((double) (A.length/1000));

		/*Find the remaining elements left*/
		int remainder = A.length - (timesSplit * 1000);

		/*Create a new arrays to store values of the A and B arrays in*/
		final int[] E = new int[1000];
		final int[] F = new int[1000];

		/*This will store the remainder of the elements and the result of 
		 * the remainders*/
		final int[] J = new int[remainder];
		final int[] K = new int[remainder];

		/*This will contain the sections of the result*/
		int[] G = new int[1000];

		/*This will store the final result in*/
		final int[] H = new int[A.length];

		/*Fill the two arrays, E and F with values and have them calculated 
		 * 1000 elements at a time*/
		for (int i = 0; i < timesSplit; i++) {
			for (int j = 0; j < 1000; j++) {
				E[j] = A[(i * 1000) + j];
				F[j] = B[(i * 1000) + j];
			}

			/*Call the integer addition function*/
			G = intAdd(E, F, mRs, mScript);

			/*Store the newly processed elements into H*/
			for(int k = 0; k < G.length; k++) {
				H[(i * 1000) + k] = G[k];
			}
		}

		/*Check to see if there are any remaining elements left to process*/
		if (remainder == 0) {

			/*If not then return the value back to where it was called from*/
			return H;
		} else {
			/*Process the remainder of the elements left*/

			/*Message handler for the values being sent back from the RenderScript
			 * using the rsSendToClientBlocking() function*/
			mRs.setMessageHandler(new RenderScript.RSMessageHandler(){

				@Override
				public void run() {

					/*Get the message from the rsSendToClientBlocking function.
					 * Store these values in the variables, mID, mData and mLength*/
					Log.d(" ", String.valueOf(this.mID)+ " " + mData + " " + mLength);

					/*Store the data in an array, where mID is the data being returned and 
					 * mLength is the index of the array. In this case, the elements need
					 * to be inserted at an offset position*/
					H[(timesSplit * 1000) + mLength] = mID;
				}	
			});

			/*Fill these two arrays with the remaining values to be processed*/
			for (int l = 0; l < remainder; l++) {
				J[l] = A[(timesSplit * 1000) + l];
				K[l] = B[(timesSplit * 1000) + l];
			}

			/*Save the current values to GPU memory*/
			saveToMemory(J, K, mRs, mScript);

			/*Call the function contained in the RenderScript source*/
			mScript.invoke_intAdd();

			/*Return H so it can be returned back to where it was called*/
			return H;
		}
	}

	public int[] subtractSplitArrays(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		final int timesSplit = (int) Math.floor((double) (A.length/1000));
		int remainder = A.length - (timesSplit * 1000);
		final int[] E = new int[1000];
		final int[] F = new int[1000];
		final int[] J = new int[remainder];
		final int[] K = new int[remainder];
		int[] G = new int[1000];
		final int[] H = new int[A.length];

		for (int i = 0; i < timesSplit; i++) {
			for (int j = 0; j < 1000; j++) {
				E[j] = A[(i * 1000) + j];
				F[j] = B[(i * 1000) + j];
			}

			G = intSubtract(E, F, mRs, mScript);

			for(int k = 0; k < G.length; k++) {
				H[(i * 1000) + k] = G[k];
			}
		}
		if (remainder == 0) {
			return H;
		} else {
			mRs.setMessageHandler(new RenderScript.RSMessageHandler(){
				@Override
				public void run() {
					Log.d(" ", String.valueOf(this.mID)+ " " + mData + " " + mLength);
					H[(timesSplit * 1000) + mLength] = mID;
				}	
			});

			for (int l = 0; l < remainder; l++) {
				J[l] = A[(timesSplit * 1000) + l];
				K[l] = B[(timesSplit * 1000) + l];
			}

			saveToMemory(J, K, mRs, mScript);
			mScript.invoke_intSubtract();

			return H;
		}
	}

	public int[] multiplySplitArrays(int[] A, int[] B, RenderScript mRs, ScriptC_rsprototypetwo mScript) {
		final int timesSplit = (int) Math.floor((double) (A.length/1000));
		int remainder = A.length - (timesSplit * 1000);
		final int[] E = new int[1000];
		final int[] F = new int[1000];
		final int[] J = new int[remainder];
		final int[] K = new int[remainder];
		int[] G = new int[1000];
		final int[] H = new int[A.length];

		for (int i = 0; i < timesSplit; i++) {
			for (int j = 0; j < 1000; j++) {
				E[j] = A[(i * 1000) + j];
				F[j] = B[(i * 1000) + j];
			}

			G = intMultiply(E, F, mRs, mScript);

			for(int k = 0; k < G.length; k++) {
				H[(i * 1000) + k] = G[k];
			}
		}

		if (remainder == 0) {
			return H;
		} else {
			mRs.setMessageHandler(new RenderScript.RSMessageHandler(){
				@Override
				public void run() {
					Log.d(" ", String.valueOf(this.mID)+ " " + mData + " " + mLength);
					H[(timesSplit * 1000) + mLength] = mID;
				}	
			});

			for (int l = 0; l < remainder; l++) {
				J[l] = A[(timesSplit * 1000) + l];
				K[l] = B[(timesSplit * 1000) + l];
			}

			saveToMemory(J, K, mRs, mScript);
			mScript.invoke_intMultiply();

			return H;
		}
	}
}