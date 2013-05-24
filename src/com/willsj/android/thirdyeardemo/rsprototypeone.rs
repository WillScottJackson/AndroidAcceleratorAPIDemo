/*Define the version of RenderScript to work with*/
#pragma version(1)

/*Set the java package that the script belongs to*/
#pragma rs java_package_name(com.willsj.android.thirdyeardemo)

/*Declare three integer variables, their values
will be initialised in the Java layer*/
int numberA;
int numberB;
int numberC;

/*This function adds two integer numbers together*/
void intAdd() {

	/*Add the two numbers together*/
	numberC = numberA + numberB;
	
	/*Send the value of the result to the logcat*/
	rsDebug("Current value", numberC);
}

/*This function subtracts two integer numbers*/
void intSubtract() {
	/*Subtract the two numbers*/
	numberC = numberA - numberB;
	
	/*Send the value of the result to the logcat*/
	rsDebug("Current value", numberC);
}

/*This function multiplies two integer numbers together*/
void intMultiply() {
	/*Multiply the two numbers together*/
	numberC = numberA * numberB;
	
	/*Send the value of the result to the logcat*/
	rsDebug("Current value", numberC);
}