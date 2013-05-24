package com.willsj.android.thirdyeardemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThirdYearProjectDemonstrationTwo extends Activity {
	@Override

	/*On create method is the first called when the app loads up*/
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*Set the layout to that defined in the XML file named main.xml*/
		setContentView(R.layout.main);

		/*Bind the buttons that link to the prototype programs with their IDs found 
		 * in the XML layout file*/
		Button testOne = (Button) findViewById(R.id.prototypeOne);
		Button testTwo = (Button) findViewById(R.id.prototypeTwo);
		Button testThree = (Button) findViewById(R.id.prototypeThree);
		Button rsPrototype = (Button) findViewById(R.id.renderscriptPrototype);
		Button rsPrototypeTwo = (Button) findViewById(R.id.renderscriptPrototypeTwo);

		/*Bind the buttons that link to the completed programs with their IDs found 
		 * in the XML layout file*/
		Button javaTest = (Button) findViewById(R.id.javaBuiltTest);
		Button rsTest = (Button) findViewById(R.id.renderscriptBuiltTest);

		/*Create an onClickListener that will listen for when the button */
		testOne.setOnClickListener(new OnClickListener() {

			/*This is called when the button is pressed*/
			public void onClick(View v) {

				/*Create a new object from the intent class*/
				Intent intent = new Intent();

				/*Create a string with the package name in which all of the source files 
				 * are included in*/
				String packageName = "com.willsj.android.thirdyeardemo";

				/*Create a string with the name of the source file you wish to switch to*/
				String className = "com.willsj.android.thirdyeardemo.PrototypeOne";

				/*Set the class name to the source file that is within the package described*/
				intent.setClassName(packageName, className);

				/*Switch to the new activity defined within the intent object*/
				startActivity(intent);
			}
		});

		/*Click listen function for the second prototype test*/
		testTwo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				String packageName ="com.willsj.android.thirdyeardemo";
				String className = "com.willsj.android.thirdyeardemo.PrototypeTwo";
				intent.setClassName(packageName, className);
				startActivity(intent);
			}
		});

		/*Click listen function for the third prototype test*/
		testThree.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				String packageName ="com.willsj.android.thirdyeardemo";
				String className = "com.willsj.android.thirdyeardemo.PrototypeThree";
				intent.setClassName(packageName, className);
				startActivity(intent);
			}
		});

		/*Click listen function for the Renderscript prototype test*/
		rsPrototype.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				String packageName ="com.willsj.android.thirdyeardemo";
				String className = "com.willsj.android.thirdyeardemo.RenderscriptPrototype";
				intent.setClassName(packageName, className);
				startActivity(intent);
			}
		});

		/*Click listen function for the Renderscript prototype test*/
		rsPrototypeTwo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				String packageName ="com.willsj.android.thirdyeardemo";
				String className = "com.willsj.android.thirdyeardemo.RenderscriptPrototypeTwo";
				intent.setClassName(packageName, className);
				startActivity(intent);
			}
		});


		/*Click listen function for the Java built benchmark test*/
		javaTest.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				String packageName ="com.willsj.android.thirdyeardemo";
				String className = "com.willsj.android.thirdyeardemo.JavaBuiltBenchmark";
				intent.setClassName(packageName, className);
				startActivity(intent);
			}
		});

		/*Click listen function for the Renderscript built test*/
		rsTest.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				String packageName ="com.willsj.android.thirdyeardemo";
				String className = "com.willsj.android.thirdyeardemo.RenderScriptBuiltBenchmark";
				intent.setClassName(packageName, className);
				startActivity(intent);
			}
		});
	} 
}