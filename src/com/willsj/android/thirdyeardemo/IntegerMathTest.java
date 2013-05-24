/*Class that contains integer math benchmark tests that will return a string containing the 
 * time in which it took the test to complete in milliseconds*/
/*Written by William Scott-Jackson February 2012*/

package com.willsj.android.thirdyeardemo;
/*Import libraries for Java commands, Calendar class is for creating a timer.
 * The random class is for generating the random numbers.*/
import java.util.Calendar;
import java.util.Random;
/*Use the activity class for the basic android functionality*/
import android.app.Activity;

public class IntegerMathTest extends Activity {

	/*Function for doing an integer addition test*/
	public String IntegerAdditonTest(int length) {
		/*Define integer arrays of 3 million elements for the inputs and outputs*/
		int[] A = new int[length];
		int[] B = new int[length];
		int[] result = new int[length];

		/*Create a new random object*/
		Random rand = new Random();

		/*Fill the input arrays with random numbers between 1 and 10*/
		for (int i = 0; i < length; i++) {
			A[i] = (int) Math.round(rand.nextInt(10)); 
			B[i] = (int) Math.round(rand.nextInt(10));
		}

		/*Get the current time and store it in start. Then store the components, minutes, 
		 * seconds and milliseconds*/
		Calendar Start = Calendar.getInstance(); 										
		int startMinute = Start.get(Calendar.MINUTE);									
		int startSecond = Start.get(Calendar.SECOND);									
		int startMillisecond = Start.get(Calendar.MILLISECOND);
		long startNanosecond = System.nanoTime();

		/*Do the 3 million operations*/
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
		
		/*This will do some correction on the microseconds variable if the test
		 * happens to take more than one second*/
		if (secondsTaken >= 1) {
			microsecondsTaken = microsecondsTaken - (1000 * secondsTaken);
		}

		/*Set the value of the milliseconds taken to the more accurate value from the nanotime stamp*/
		millisecondsTaken = (int) Math.floor(nanosecondsTaken / 1000000);

		/*Create a string and fill it with data and text to notify the user*/
		String output = "\n The integer addition test is finished. The time taken is " + minutesTaken +
				" minutes, " + secondsTaken + " seconds, " + millisecondsTaken + 
				" milliseconds and " + microsecondsTaken + " microseconds \n";
		
		/*Request garbage collection*/
		clearMemory();
		/*Return the result to the program from which this class was called*/
		return output;
	}

	public String IntegerSubtractionTest(int length) {
		int[] A = new int[length];
		int[] B = new int[length];
		int[] result = new int[length];

		Random rand = new Random();

		for (int i = 0; i < length; i++) {
			A[i] = (int) Math.round(rand.nextInt(10)); 
			B[i] = (int) Math.round(rand.nextInt(10));
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

		String output = "The integer subtraction test is finished. The time taken is " + minutesTaken +
				" minutes, " + secondsTaken + " seconds, " + millisecondsTaken + 
				" milliseconds and " + microsecondsTaken + " microseconds \n";
		clearMemory();
		return output;
	}

	/*Function for doing an integer multiplication test*/
	public String IntegerMultiplicationTest(int length) {
		int[] A = new int[length];
		int[] B = new int[length];
		int[] result = new int[length];

		Random rand = new Random();

		for (int i = 0; i < length; i++) {
			A[i] = (int) Math.round(rand.nextInt(10)); 
			B[i] = (int) Math.round(rand.nextInt(10));
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

		String output = "The integer multiplication test is finished. The time taken is " + minutesTaken +
				" minutes, " + secondsTaken + " seconds, " + millisecondsTaken + 
				" milliseconds and " + microsecondsTaken + " microseconds \n";
		clearMemory();
		return output;	
	}
	
	/*Function to request garbage collection prior to the next test.*/
	private void clearMemory() {
		System.gc();
	}
}