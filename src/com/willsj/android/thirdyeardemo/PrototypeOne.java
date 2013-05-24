package com.willsj.android.thirdyeardemo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PrototypeOne extends Activity {
	/*Define two text views which will give the user feedback*/
	TextView notifyUser;
	TextView Description;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*Set the layout as according to the layout defined in the XML layout file*/
		setContentView(R.layout.prototypeone);

		/*Bind the text views to their IDs which are in the XML layout file*/
		notifyUser = (TextView) findViewById(R.id.notifyUser);
		Description = (TextView) findViewById(R.id.textView2);

		/*Bind the button to the ID found in the XML layout file*/
		Button startButton = (Button) findViewById(R.id.startButton);

		/*Create a click listener for the button defined above*/
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				/*Get the current time and store it in a variable*/
				Calendar Start = Calendar.getInstance();

				/*Store the current minute, second and millisecond into variables*/
				int startMinute = Start.get(Calendar.MINUTE);
				int startSecond = Start.get(Calendar.SECOND);
				int startMillisecond = Start.get(Calendar.MILLISECOND);

				/*Roll ahead the calendar by a minute (the end time)*/
				Start.roll(Calendar.MINUTE, true);

				/*Store the rolled ahead times into a variable*/
				int endMinute = Start.get(Calendar.MINUTE);
				int endSecond = Start.get(Calendar.SECOND);
				int endMillisecond = Start.get(Calendar.MILLISECOND);

				/*Initialise a counter to count the number of loops executed*/
				int loopCounter = 0;

				/*This loop will execute indefinitely until the minute is up until it breaks away*/
				while (true) {

					/*Initialise a variable which is the square root of a random number*/
					float A = (float) Math.sqrt(Math.random());

					/*Get the current time and compare it with the rolled ahead time (the target end time)*/
					GregorianCalendar now = new GregorianCalendar();
					if (now.get(Calendar.MINUTE) >= endMinute) {
						if (now.get(Calendar.SECOND)>= endSecond) {
							if (now.get(Calendar.MILLISECOND) >= endMillisecond) {
								break;
							}
						}
					}
					/*Increment the counter by one after every loop execution*/
					loopCounter++;
				}

				/*Display the results and some feedback to the user*/
				Description.setText("");
				notifyUser.append("\n Test Complete");
				notifyUser.append("\n The test did " + loopCounter + " loops in one minute");
				notifyUser.append("\n ");
				notifyUser.append("\n The main disadvantages of this test are: ");
				notifyUser.append("\n 1. The control statements happening sequentially with the test outweighs the " +
						"clock cycles taken for the actual test to perform, making it a poor indicator of performance ");
				notifyUser.append("\n 2. Because the test takes a minute it looks like the program has crashed, user " +
						"feedback such as a thread is required");
				notifyUser.append("\n The test doesn't actually give a figure for performance, but just a number that " +
						"can be used to test relative performance");
			}
		});
	}
}
