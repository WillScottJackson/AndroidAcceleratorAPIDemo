package com.willsj.android.thirdyeardemo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimerFunctions {

	/*Function to wait for a given number for seconds*/
	public void timerInSeconds(int seconds) {

		/*Get the current time and store it in a variable*/
		Calendar Start = Calendar.getInstance();

		/*Store the current second and millisecond in integer variables*/
		int startSecond = Start.get(Calendar.SECOND);
		int startMillisecond = Start.get(Calendar.MILLISECOND);

		/*Using the input argument seconds, roll the clock ahead that number of seconds*/
		for (int i = 0; i < seconds; i++) {
			Start.roll(Calendar.SECOND, true);
		}

		/*Store the rolled ahead time seconds and milliseconds into a variable*/
		int endSecond = Start.get(Calendar.SECOND);
		int endMillisecond = Start.get(Calendar.MILLISECOND);

		/*Run the loop until the appropriate amount of time has passed*/
		while (true) {
			GregorianCalendar now = new GregorianCalendar();
			if (now.get(Calendar.SECOND)>= endSecond) {
				if (now.get(Calendar.MILLISECOND) >= endMillisecond) {
					break;
				}
			}
		}
	}

	/*Function to wait for a given number of milliseconds*/
	public void timerInMilliseconds(int milliseconds) {

		/*Find the number of seconds if the number of milliseconds is more than 1000
		 * Then send the seconds to the other timer*/
		if (milliseconds >=1000) {
			int seconds = Math.round(milliseconds/1000);

			/*Go wait for the seconds, and then come back*/
			timerInSeconds(seconds);

			/*Work out the milliseconds left*/
			milliseconds -= 1000*seconds;
		}

		/*Get the current time and store it in a variable*/
		Calendar Start = Calendar.getInstance();

		/*Store the current millisecond in integer variables*/
		int startSecond = Start.get(Calendar.SECOND);
		int startMillisecond = Start.get(Calendar.MILLISECOND);

		/*Using the input argument milliseconds, roll the clock ahead that number of milliseconds*/
		for (int i = 0; i < milliseconds; i++) {
			Start.roll(Calendar.MILLISECOND, true);
		}

		/*Store the rolled ahead time in milliseconds into a variable*/
		int endSecond = Start.get(Calendar.SECOND);
		int endMillisecond = Start.get(Calendar.MILLISECOND);

		/*Run the loop until the appropriate amount of time has passed*/
		while (true) {
			GregorianCalendar now = new GregorianCalendar();
			if (now.get(Calendar.SECOND) >= endSecond) {
				if (now.get(Calendar.MILLISECOND) >= endMillisecond) {
					break;
				}
			}
		}
	}

	/*Function that will wait for a specified number of nanoseconds*/
	public void timerInNanoseconds(int nanoseconds) {

		/*Test to see if there is more than one second in the input argument...*/
		if (nanoseconds >= 1000000000) {

			/*...If so get the number of seconds in the input argument*/
			int seconds = Math.round(nanoseconds/1000000000);

			/*Send the number of seconds to the seconds timer function*/
			timerInSeconds(seconds);

			/*Calculate the number of nanoseconds left*/
			nanoseconds -= seconds * 1000000000;

			/*...Next see if there are any milliseconds in the input argument...*/
		} else if (nanoseconds >= 1000000) {

			/*Calculate the number of milliseconds in the input argument*/
			int milliseconds = Math.round(nanoseconds / 1000000);

			/*Send the number of milliseconds to the milliseconds timer*/
			timerInMilliseconds(milliseconds);

			/*Calculate the number of nanoseconds left*/
			nanoseconds -= milliseconds * 1000000;

		} 
		/*Get the time stamp of the current time in nanoseconds*/
		int start = (int) System.nanoTime();

		/*Get the end time in nanoseconds*/
		int finish = start + nanoseconds;

		/*Run this loop until the time stamp exceeds the end time in nanoseconds*/
		while (true) {
			if (System.nanoTime() >= finish) {
				break;
			}
		}
	}
}

/*Note: Unfortunately, Java doesn't have microsecond resolution so will have to make do with just
 * nanosecond resolution.*/