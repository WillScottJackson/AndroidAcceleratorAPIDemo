/*Define the version of RenderScript to work with*/
#pragma version(1)

/*Set the java package that the script belongs to*/
#pragma rs java_package_name(com.willsj.android.thirdyeardemo)

/*Create a struct with the values in. The attribute alignment means
that in memory, each member of the struct is placed at an offset of
4 bytes to help with performance. The packed attribute decreases
the memory footprint compared to that if it wasn't packed.*/
typedef struct __attribute__((packed, aligned(4))) Values {
	int A;
	int B;
	int C;
} Values_t;

/*Create a pointer to the struct*/
Values_t *values;

/*Create a struct with the values in.*/
typedef struct __attribute__((packed, aligned(4))) floatValues {
	float A;
	float B;
	float C;
} floatValues_t;

/*Create a pointer to the struct*/
floatValues_t *floatvalues;

/*This function adds two integer arrays together*/
void intAdd() {

	/*Set up a pointer to the structure, pValues*/
	Values_t *pValues = values;

	/*Calculate the index by getting the X dimension of the allocation
	to memory*/
	int index = rsAllocationGetDimX(rsGetAllocation(values));
	
	/*Run this block of code for the amount of times equal to the index*/
	for (int i = 0; i < index; i++) {
		/*Calculate C by accessing the members of the structs and adding them
		together*/
		pValues->C = pValues->A + pValues->B;
		
		/*Send the values back to the Java layer, the middle value is just rubbish,
		but the first and third argument equate to the data and the index.
		This will block until the message is queued, this increases the transfer success
		from GPU memory to Java layer*/
		rsSendToClientBlocking(pValues->C, (const void *) &pValues->C, i);
		
		/*Move onto the next struct*/
		pValues++;
	}
}

/*This function subtracts two integer arrays*/
void intSubtract() {
	Values_t *pValues = values;
	int index = rsAllocationGetDimX(rsGetAllocation(values));
	for (int i = 0; i < index; i++) {
		pValues->C = pValues->A - pValues->B;
		rsSendToClientBlocking(pValues->C, (const void *) &pValues->C, i);
		pValues++;
	}
}

/*This function multiplies two integer arrays together*/
void intMultiply() {
	Values_t *pValues = values;
	int index = rsAllocationGetDimX(rsGetAllocation(values));
	for (int i = 0; i < index; i++) {
		pValues->C = pValues->A * pValues->B;
		rsSendToClientBlocking(pValues->C, (const void *) &pValues->C, i);
		pValues++;
	}
}

/*This function adds two floating point arrays together*/
void floatAdd() {
	floatValues_t *pfloatValues = floatvalues;
	int index = rsAllocationGetDimX(rsGetAllocation(floatvalues));
	for (int i = 0; i < index; i++) {
		pfloatValues->C = pfloatValues->A + pfloatValues->B;
		rsSendToClientBlocking(pfloatValues->C, (const void *) &pfloatValues->C, i);
		pfloatValues++;
	}
}

/*This function subtracts two floating point arrays*/
void floatSubtract() {
	floatValues_t *pfloatValues = floatvalues;
	int index = rsAllocationGetDimX(rsGetAllocation(floatvalues));
	for (int i = 0; i < index; i++) {
		pfloatValues->C = pfloatValues->A - pfloatValues->B;
		rsSendToClientBlocking(pfloatValues->C, (const void *) &pfloatValues->C, i);
		pfloatValues++;
	}
}

/*This function multiplies two floating point arrays together*/
void floatMultiply() {
	floatValues_t *pfloatValues = floatvalues;
	int index = rsAllocationGetDimX(rsGetAllocation(floatvalues));
	for (int i = 0; i < index; i++) {
		pfloatValues->C = pfloatValues->A * pfloatValues->B;
		rsSendToClientBlocking(pfloatValues->C, (const void *) &pfloatValues->C, i);
		pfloatValues++;
	}
}

/*This function divides two floating point arrays*/
void floatDivide() {
	floatValues_t *pfloatValues = floatvalues;
	int index = rsAllocationGetDimX(rsGetAllocation(floatvalues));
	for (int i = 0; i < index; i++) {
		pfloatValues->C = pfloatValues->A / pfloatValues->B;
		rsSendToClientBlocking(pfloatValues->C, (const void *) &pfloatValues->C, i);
		pfloatValues++;
	}
}

/*This adds to integer array together and calculates the 
time it has taken to do so*/
void intAddBenchmark() {
	/*Set up a pointer to the structure, pValues*/
	Values_t *pValues = values;

	/*Calculate the index by getting the X dimension of the allocation
	to memory*/
	int index = rsAllocationGetDimX(rsGetAllocation(values));
	
	/*Get the current time in nanoseconds*/
	int64_t startTime = rsUptimeNanos();
	
	/*Run this block of code for the amount of times equal to the index*/
	for (int i = 0; i < index; i++) {
		/*Calculate C by accessing the members of the structs and adding them
		together*/
		pValues->C = pValues->A + pValues->B;
		/*Move onto the next struct*/
		pValues++;
	}
	
	/*Get the time after the test has ended in nanoseconds*/
	int64_t endTime = rsUptimeNanos();
	
	/*Calculate the time taken by getting the difference between the start an the 
	end time*/
	int timeTaken = endTime - startTime;
	
	/*Send to the logcat*/
	rsDebug("Time taken = " ,timeTaken);
	rsSendToClientBlocking(timeTaken, (const void *) 0, 0);
}

/*This function subtracts two integer arrays and calculates the 
time it has taken to so*/
void intSubtractBenchmark() {
	Values_t *pValues = values;
	int index = rsAllocationGetDimX(rsGetAllocation(values));
	int64_t startTime = rsUptimeNanos();
	for (int i = 0; i < index; i++) {
		pValues->C = pValues->A - pValues->B;
		pValues++;
	}
	int64_t endTime = rsUptimeNanos();
	int timeTaken = endTime - startTime;
	rsDebug("Time taken = " ,timeTaken);
	rsSendToClientBlocking(timeTaken, (const void *) 0, 0);
}

/*This function multiplies two integer arrays together and 
calculates the time has taken to so*/
void intMultiplyBenchmark() {
	Values_t *pValues = values;
	int index = rsAllocationGetDimX(rsGetAllocation(values));
	int64_t startTime = rsUptimeNanos();
	for (int i = 0; i < index; i++) {
		pValues->C = pValues->A * pValues->B;
		pValues++;
	}
	int64_t endTime = rsUptimeNanos();
	int timeTaken = endTime - startTime;
	rsDebug("Time taken = " ,timeTaken);
	rsSendToClientBlocking(timeTaken, (const void *) 0, 0);
}

/*This function adds two floating point arrays together and calculates
the time it has taken to do so*/
void floatAddBenchmark() {
	floatValues_t *pfloatValues = floatvalues;
	int index = rsAllocationGetDimX(rsGetAllocation(floatvalues));
	int64_t startTime = rsUptimeNanos();
	for (int i = 0; i < index; i++) {
		pfloatValues->C = pfloatValues->A + pfloatValues->B;
		pfloatValues++;
	}
	int64_t endTime = rsUptimeNanos();
	int timeTaken = endTime - startTime;
	rsDebug("Time taken = " ,timeTaken);
	rsSendToClientBlocking(timeTaken, (const void *) 0, 0);
}

/*This function subtracts two integer arrays and calculates
the time it has taken to do so*/
void floatSubtractBenchmark() {
	floatValues_t *pfloatValues = floatvalues;
	int index = rsAllocationGetDimX(rsGetAllocation(floatvalues));
	int64_t startTime = rsUptimeNanos();
	for (int i = 0; i < index; i++) {
		pfloatValues->C = pfloatValues->A - pfloatValues->B;
		pfloatValues++;
	}
	int64_t endTime = rsUptimeNanos();
	int timeTaken = endTime - startTime;
	rsDebug("Time taken = " ,timeTaken);
	rsSendToClientBlocking(timeTaken, (const void *) 0, 0);
}

/*This function multiplies two integer array together and 
calculates the time it has taken to do so.*/
void floatMultiplyBenchmark() {
	floatValues_t *pfloatValues = floatvalues;
	int index = rsAllocationGetDimX(rsGetAllocation(floatvalues));
	int64_t startTime = rsUptimeNanos();
	for (int i = 0; i < index; i++) {
		pfloatValues->C = pfloatValues->A * pfloatValues->B;
		pfloatValues++;
	}
	int64_t endTime = rsUptimeNanos();
	int timeTaken = endTime - startTime;
	rsDebug("Time taken = " ,timeTaken);
	rsSendToClientBlocking(timeTaken, (const void *) 0, 0);
}

/*This function divides two integer arrays and calulates
the time it has taken to do so.*/
void floatDivideBenchmark() {
	floatValues_t *pfloatValues = floatvalues;
	int index = rsAllocationGetDimX(rsGetAllocation(floatvalues));
	int64_t startTime = rsUptimeNanos();
	for (int i = 0; i < index; i++) {
		pfloatValues->C = pfloatValues->A / pfloatValues->B;
		pfloatValues++;
	}
	int64_t endTime = rsUptimeNanos();
	int timeTaken = endTime - startTime;
	rsDebug("Time taken = " ,timeTaken);
	rsSendToClientBlocking(timeTaken, (const void *) 0, 0);
}