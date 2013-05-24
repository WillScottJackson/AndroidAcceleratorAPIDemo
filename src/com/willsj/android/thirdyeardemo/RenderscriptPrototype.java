package com.willsj.android.thirdyeardemo;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.renderscript.Script;
import android.widget.TextView;

public class RenderscriptPrototype extends Activity {

	/*Create a new object from the generated class, and make it global*/
	private ScriptC_rsprototypeone mScript;

	/*Define a textview that will be used to notify the user and make it global*/
	TextView notifyUser;

	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*Set the layout to that defined in the XML layout file*/
		setContentView(R.layout.renderscriptprototype);

		/*Bind the textview to the ID found in the XML layout file*/
		notifyUser = (TextView) findViewById(R.id.messageReturned);

		/*Define two integer arrays*/
		int[] A = new int[10];
		int[] B = new int[10];
		
		/*Create a new random object*/
		Random rand = new Random();
		
		/*Fill the arrays up with integer values between 1 and 10*/
		for (int i = 0; i < A.length; i++) {
			A[i] = (int) (int) Math.round(rand.nextInt(10));
			B[i] = (int) (int) Math.round(rand.nextInt(10));
		}


		notifyUser.append("Values returned from RenderScript");
		/*Call the following three functions*/
		intAdd(A, B);
		intSubtract(A, B);
		intMultiply(A, B);

		/*Provide some feedback to the user*/
		notifyUser.append("\n \n This problems with this program are:");
		notifyUser.append("\n 1. RenderScript is asynchronous, so when the Java layer requests" +
				"the value back, the program won't have excecuted by then, so it would be " +
				"useful to add a communications system between the two layers");
		notifyUser.append("\n 2. The program calls the RenderSript just to add two numbers together" +
				"which doesn't make it very efficient, maybe it would be possible to send all of the" +
				"information over at once the RenderScript and then send it all back. ");
	}

	/*Function that adds two integer arrays using a RenderScript program*/
	private void intAdd(int[] A, int[] B) {

		/*Create a new RenderScript*/
		RenderScript rs = RenderScript.create(this);

		/*Create a new object from the generated class*/
		ScriptC_rsprototypeone intaddscript = new ScriptC_rsprototypeone(rs, getResources(), R.raw.rsprototypeone);

		/*Set the object so that can be used in other functions*/
		mScript = intaddscript;

		/*Run this block of code for as many times as there are elements in the array*/
		for(int i = 0; i < A.length; i++) {

			/*Call the set new values function, sending the individual elements of the arrays
			 * as arguments*/	
			setNewValues(mScript, A[i], B[i]);

			/*Call the function intAdd contained within the RenderScript*/
			intaddscript.invoke_intAdd();

			/*Retrieve the result from the script, although this method doesn't work because 
			 * RenderScript is asynchronous, which means the RenderScript calls are queued and 
			 * executed in their own time.*/
			int C = getResult(mScript);

			/*Display the result to the user*/
			notifyUser.append(" " + C);
		}
	}

	private void intSubtract(int[] A, int[] B) {

		/*Create a new RenderScript*/
		RenderScript rs = RenderScript.create(this);

		/*Create a new object from the generated class*/
		ScriptC_rsprototypeone intsubtractscript = new ScriptC_rsprototypeone(rs, getResources(), R.raw.rsprototypeone);

		/*Set the object so that it can be seen by other functions*/
		mScript = intsubtractscript;

		/*Run this block of code for as many times as there are elements in the array*/
		for (int i = 0; i < A.length; i++) {

			/*Call the set new values function, sending the individual elements of the arrays
			 * as arguments*/
			setNewValues(mScript, A[i], B[i]);

			/*Call the function intSubtract contained within the RenderScript*/
			intsubtractscript.invoke_intSubtract();

			/*Retrieve the result*/
			int C = getResult(mScript);

			/*Notify the user*/
			notifyUser.append(" " + C);
		}
	}

	/*Function that multiplies two integer arrays together using a RenderScript program*/
	private void intMultiply(int[] A, int[] B) {

		/*Create a new RenderScript*/
		RenderScript rs = RenderScript.create(this);

		/*Create a new object from the generated class*/
		ScriptC_rsprototypeone intmultscript = new ScriptC_rsprototypeone(rs, getResources(), R.raw.rsprototypeone);

		/*Set the object so that it can be seen by other functions*/
		mScript = intmultscript;

		/*Run this block of code for as many times as there are elements in the array*/
		for (int i = 0; i < A.length; i++) {

			/*Call the set new values function, sending the individual elements of the arrays
			 * as arguments*/
			setNewValues(mScript, A[i], B[i]);

			/*Call the function intSubtract contained within the RenderScript*/
			intmultscript.invoke_intMultiply();

			/*Retrieve the result*/
			int C = getResult(mScript);

			/*Notify the user*/
			notifyUser.append(" " + C);
		}
	}

	/*This function sets the value of particular variables in RenderScript*/
	public void setNewValues(Script script, int A, int B) {

		/*Set the variable numberA contained in the script to the value stored
		 * in the argument A*/
		mScript.set_numberA(A);

		/*Set the variables numberB contained in the script to the value stored
		 * in the argument B*/
		mScript.set_numberB(B);
	}

	/*This function retrieves the value of a variable in RenderScript, although 
	 * when this is called, it returns 0 due to the Asynchronous nature of 
	 * RenderScript, for all intents and purposes this is effectively redundant 
	 * code*/
	public int getResult(Script script) {
		int C = mScript.get_numberC();
		return C;
	}
}