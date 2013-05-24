/*This class contains floating point math benchmark tests that returns the result as 
 * a string containing the time in which it took the test to complete*/
/*Written by William Scott-Jackson February 2012*/

package com.willsj.android.thirdyeardemo;
/*Import libraries for Java commands, Calendar class is for creating a timer.*/
import java.util.Calendar;
/*Use the activity class for the basic android functionality*/
import android.app.Activity;

public class FloatingPointMathTest extends Activity {

	/*Function for doing a floating point addition test*/
	public String FloatingPointAdditionTest(int length) {

		/*Define floating point arrays for 3 million elements for the inputs and outputs*/
		float[] A = new float[length];
		float[] B = new float[length];
		float[] result = new float[length];

		/*Fill the input arrays with random floating point numbers between 1 and 10*/
		for (int i = 0; i < length; i++) {
			A[i] = (float) ((float) 10 * Math.random());
			B[i] = (float) ((float) 10 * Math.random());
		}

		/*Get the current time and store it in start. Then store the components, minutes, 
		 * seconds and milliseconds*/
		Calendar Start = Calendar.getInstance(); 										
		int startMinute = Start.get(Calendar.MINUTE);									
		int startSecond = Start.get(Calendar.SECOND);									
		int startMillisecond = Start.get(Calendar.MILLISECOND);
		long startNanosecond = System.nanoTime();

		/*Do 3 million addition operations*/
		for (int i = 0; i < length; i++) {
			result[i] = A[i] + B[i];
		}

		/*Get the current time, which will be some time later after the addition 
		 *operations have been done, and then store it in the variable Finish*/
		Calendar Finish = Calendar.getInstance(); 										
		int finishMinute = Finish.get(Calendar.MINUTE);													
		int finishSecond = Finish.get(Calendar.SECOND);													
		int finishMillisecond = Finish.get(Calendar.MILLISECOND);
		long finishNanosecond = System.nanoTime();

		/*To calculate the time taken for the test, subtract the time at the end of the test
		 * * from the time at the start of the test. To make sure that the number returned is positive
		 * * use the absolute value function*/
		int minutesTaken = Math.abs(finishMinute - startMinute);
		int secondsTaken = Math.abs(finishSecond - startSecond);
		int millisecondsTaken = Math.abs(finishMillisecond - startMillisecond);
		long nanosecondsTaken =  Math.abs(finishNanosecond - startNanosecond);
		int microsecondsTaken = (int) Math.abs((nanosecondsTaken - (millisecondsTaken * 1000000))/1000);

		/*Set the value of the milliseconds taken to the more accurate value from the nanotime stamp*/
		millisecondsTaken = (int) (nanosecondsTaken / 1000000);
		
		/*This will do some correction on the microseconds variable if the test happens to 
		 * take longer than one second*/
		if (secondsTaken >= 1) {
			microsecondsTaken = microsecondsTaken - (1000 * secondsTaken);
		}

		/*Create a string and fill it with data and text to notify the user*/
		String output = "The floating point addition test is finished. The time taken is " + minutesTaken +
				" minutes, " + secondsTaken + " seconds, " + millisecondsTaken + 
				" milliseconds and " + microsecondsTaken + " microseconds \n";
		
		/*Request garbage collection*/
		clearMemory();
		
		/*Return the result to the program from which this class was called*/
		return output;
	}

	/*Function for doing a floating point subtraction test*/
	public String FloatingPointSubtractionTest(int length) {
		float[] A = new float[length];
		float[] B = new float[length];
		float[] result = new float[length];

		for (int i = 0; i < length; i++) {
			A[i] = (float) ((float) 10 * Math.random());
			B[i] = (float) ((float) 10 * Math.random());
		}

		Calendar Start = Calendar.getInstance(); 										
		int startMinute = Start.get(Calendar.MINUTE);									
		int startSecond = Start.get(Calendar.SECOND);									
		int startMillisecond = Start.get(Calendar.MILLISECOND);
		long startNanosecond = System.nanoTime();

		for (int i = 0; i < length; i++) {
			result[i] = A[i] - B[i];
		}

		Calendar Finish = Calendar.getInstance(); 										
		int finishMinute = Finish.get(Calendar.MINUTE);													
		int finishSecond = Finish.get(Calendar.SECOND);													
		int finishMillisecond = Finish.get(Calendar.MILLISECOND);
		long finishNanosecond = System.nanoTime();

		int minutesTaken = Math.abs(finishMinute - startMinute);
		int secondsTaken = Math.abs(finishSecond - startSecond);
		int millisecondsTaken = Math.abs(finishMillisecond - startMillisecond);
		long nanosecondsTaken =  Math.abs(finishNanosecond - startNanosecond);
		int microsecondsTaken = (int) Math.abs((nanosecondsTaken - (millisecondsTaken * 1000000))/1000);
		millisecondsTaken = (int) (nanosecondsTaken / 1000000);
		
		if (secondsTaken >= 1) {
			microsecondsTaken = microsecondsTaken - (1000 * secondsTaken);
		}

		String output = "The floating point subtraction test is finished. The time taken is " + minutesTaken +
				" minutes, " + secondsTaken + " seconds, " + millisecondsTaken + 
				" milliseconds and " + microsecondsTaken + " microseconds \n";
		clearMemory();
		return output;	
	}

	/*Function for doing a floating point multiplication test*/
	public String FloatingPointMultiplicationTest(int length) {
		float[] A = new float[length];
		float[] B = new float[length];
		float[] result = new float[length];

		for (int i = 0; i < length; i++) {
			A[i] = (float) ((float) 10 * Math.random());
			B[i] = (float) ((float) 10 * Math.random());
		}

		Calendar Start = Calendar.getInstance(); 										
		int startMinute = Start.get(Calendar.MINUTE);									
		int startSecond = Start.get(Calendar.SECOND);									
		int startMillisecond = Start.get(Calendar.MILLISECOND);
		long startNanosecond = System.nanoTime();

		for (int i = 0; i < length; i++) {
			result[i] = A[i] * B[i];
		}

		Calendar Finish = Calendar.getInstance(); 										
		int finishMinute = Finish.get(Calendar.MINUTE);													
		int finishSecond = Finish.get(Calendar.SECOND);													
		int finishMillisecond = Finish.get(Calendar.MILLISECOND);
		long finishNanosecond = System.nanoTime();

		int minutesTaken = Math.abs(finishMinute - startMinute);
		int secondsTaken = Math.abs(finishSecond - startSecond);
		int millisecondsTaken = Math.abs(finishMillisecond - startMillisecond);
		long nanosecondsTaken =  Math.abs(finishNanosecond - startNanosecond);
		int microsecondsTaken = (int) Math.abs((nanosecondsTaken - (millisecondsTaken * 1000000))/1000);
		millisecondsTaken = (int) (nanosecondsTaken / 1000000);
		
		if (secondsTaken >= 1) {
			microsecondsTaken = microsecondsTaken - (1000 * secondsTaken);
		}

		String output = "The floating point multiplication test is finished. The time taken is " + minutesTaken +
				" minutes, " + secondsTaken + " seconds, " + millisecondsTaken + 
				" milliseconds and " + microsecondsTaken + " microseconds \n";
		clearMemory();
		return output;	
	}

	/*Function for a floating point division test*/
	public String FloatingPointDivisionTest(int length) {
		float[] A = new float[length];
		float[] B = new float[length];
		float[] result = new float[length];

		for (int i = 0; i < length; i++) {
			A[i] = (float) ((float) 10 * Math.random());
			B[i] = (float) ((float) 10 * Math.random());
		}

		Calendar Start = Calendar.getInstance(); 										
		int startMinute = Start.get(Calendar.MINUTE);									
		int startSecond = Start.get(Calendar.SECOND);									
		int startMillisecond = Start.get(Calendar.MILLISECOND);
		long startNanosecond = System.nanoTime();

		for (int i = 0; i < length; i++) {
			result[i] = A[i] / B[i];
		}

		Calendar Finish = Calendar.getInstance(); 										
		int finishMinute = Finish.get(Calendar.MINUTE);													
		int finishSecond = Finish.get(Calendar.SECOND);													
		int finishMillisecond = Finish.get(Calendar.MILLISECOND);
		long finishNanosecond = System.nanoTime();

		int minutesTaken = Math.abs(finishMinute - startMinute);
		int secondsTaken = Math.abs(finishSecond - startSecond);
		int millisecondsTaken = Math.abs(finishMillisecond - startMillisecond);
		long nanosecondsTaken =  Math.abs(finishNanosecond - startNanosecond);
		int microsecondsTaken = (int) Math.abs((nanosecondsTaken - (millisecondsTaken * 1000000))/1000);
		millisecondsTaken = (int) (nanosecondsTaken / 1000000);
		
		if (secondsTaken >= 1) {
			microsecondsTaken = microsecondsTaken - (1000 * secondsTaken);
		}

		String output = "The floating point division test is finished. The time taken is " + minutesTaken +
				" minutes, " + secondsTaken + " seconds, " + millisecondsTaken + 
				" milliseconds and " + microsecondsTaken + " microseconds \n";
		clearMemory();
		return output;
	}
	
	/*Function to request garbage collection prior to the next test*/
	private void clearMemory() {
		System.gc();
	}
}