package com.willsj.android.thirdyeardemo;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.widget.TextView;

public class RenderScriptBuiltBenchmark extends Activity {
	
	/*Declare a text view here so that it is visbile to the
	 * rest of the program*/
	TextView testRunning;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*Set the layout to that defined in the XML file*/
		setContentView(R.layout.renderscriptbuiltbenchmark);
		
		/*Bind the text view to that defined in the XML file*/
		testRunning = (TextView) findViewById(R.id.testRunning);
		
		/*Create a new RenderScript and a new object from the generated class*/
		RenderScript mRs = RenderScript.create(this);
		ScriptC_rsprototypetwo mScript = new ScriptC_rsprototypetwo(mRs, getResources(), R.raw.rsprototypetwo);
		
		/*Create two new objects from the operations classes*/
		intOperationsRS intBench = new intOperationsRS();
		floatOperationsRS floatBench = new floatOperationsRS();
		
		/*Create two integer arrays*/
		int[] A = new int[5000];
		int[] B = new int[5000];
		
		/*Create an object from the Random class*/
		Random rand = new Random();
		
		/*Fill the two arrays with random integers between 1 and 10*/
		for (int i = 0; i < A.length; i++) {
			A[i] = (int) Math.round(rand.nextInt(10)); 
			B[i] = (int) Math.round(rand.nextInt(10));
		}
		
		/*Run the integer addition benchmark test and display the result*/
		int t = intBench.intAddBenchmark(A, B, mRs, mScript);
		testRunning.append("\n Integer Addition test complete!");
		testRunning.append(" The test took " + (t/1000 ) + " microseconds");
		
		/*Run the integer subtraction benchmark test and display the result*/
		t = intBench.intSubtractBenchmark(A, B, mRs, mScript);
		testRunning.append("\n Integer Subtraction test complete!");
		testRunning.append(" The test took " + (t/1000 ) + " microseconds");
		
		/*Run the integer multiplication benchmark test and display the result*/
		t = intBench.intMultiplyBenchmark(A, B, mRs, mScript);
		testRunning.append("\n Integer Multiplication test complete!");
		testRunning.append(" The test took " + (t/1000 ) + " microseconds");	
		
		/*Create two new floating point arrays*/
		float[] C = new float[5000];
		float[] D = new float[5000];
		
		/*Fill the arrays with random floating point numbers between 1 and 10*/
		for (int i = 0; i < C.length; i++) {
			C[i] = (float) ((float) 10 * Math.random());
			D[i] = (float) ((float) 10 * Math.random());
		}
		
		/*Run the floating point addition benchmark and display the result*/
		t = floatBench.floatAddBenchmark(C, D, mRs, mScript);
		testRunning.append("\n Floating Point Addition Test Complete!");
		testRunning.append(" The test took " + (t/1000 ) + " microseconds");
		
		/*Run the floating point subtraction benchmark and display the result*/
		t = floatBench.floatSubtractBenchmark(C, D, mRs, mScript);
		testRunning.append("\n Floating Point Subtraction Test Complete!");
		testRunning.append(" The test took " + (t/1000 ) + " microseconds");
		
		/*Run the floating point multiplication and display the result*/
		t = floatBench.floatMultiplyBenchmark(C, D, mRs, mScript);
		testRunning.append("\n Floating Point Multiplication Test Complete!");
		testRunning.append(" The test took " + (t/1000 ) + " microseconds");
		
		/*Finally, run the floating point division benchmark and display the result*/
		t = floatBench.floatDivideBenchmark(C, D, mRs, mScript);
		testRunning.append("\n Floating Point Division Test Complete!");
		testRunning.append(" The test took " + (t/1000 ) + " microseconds");
	}
}
