/*
 * Copyright (C) 2011-2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is auto-generated. DO NOT MODIFY!
 * The source Renderscript file: C:\Users\Will\Documents\Dropbox\University Work\Meng Electronic and Electrical Engineering\Level 3\EG3001. Third Year Project\ThirdYearProjectDemonstrationTwo\src\com\willsj\android\thirdyeardemo\rsprototypetwo.rs
 */
package com.willsj.android.thirdyeardemo;

import android.renderscript.*;
import android.content.res.Resources;

/**
 * @hide
 */
public class ScriptC_rsprototypetwo extends ScriptC {
    private static final String __rs_resource_name = "rsprototypetwo";
    // Constructor
    public  ScriptC_rsprototypetwo(RenderScript rs) {
        this(rs,
             rs.getApplicationContext().getResources(),
             rs.getApplicationContext().getResources().getIdentifier(
                 __rs_resource_name, "raw",
                 rs.getApplicationContext().getPackageName()));
    }

    public  ScriptC_rsprototypetwo(RenderScript rs, Resources resources, int id) {
        super(rs, resources, id);
    }

    private final static int mExportVarIdx_values = 0;
    private ScriptField_Values mExportVar_values;
    public void bind_values(ScriptField_Values v) {
        mExportVar_values = v;
        if (v == null) bindAllocation(null, mExportVarIdx_values);
        else bindAllocation(v.getAllocation(), mExportVarIdx_values);
    }

    public ScriptField_Values get_values() {
        return mExportVar_values;
    }

    private final static int mExportVarIdx_floatvalues = 1;
    private ScriptField_floatValues mExportVar_floatvalues;
    public void bind_floatvalues(ScriptField_floatValues v) {
        mExportVar_floatvalues = v;
        if (v == null) bindAllocation(null, mExportVarIdx_floatvalues);
        else bindAllocation(v.getAllocation(), mExportVarIdx_floatvalues);
    }

    public ScriptField_floatValues get_floatvalues() {
        return mExportVar_floatvalues;
    }

    private final static int mExportFuncIdx_intAdd = 0;
    public void invoke_intAdd() {
        invoke(mExportFuncIdx_intAdd);
    }

    private final static int mExportFuncIdx_intSubtract = 1;
    public void invoke_intSubtract() {
        invoke(mExportFuncIdx_intSubtract);
    }

    private final static int mExportFuncIdx_intMultiply = 2;
    public void invoke_intMultiply() {
        invoke(mExportFuncIdx_intMultiply);
    }

    private final static int mExportFuncIdx_floatAdd = 3;
    public void invoke_floatAdd() {
        invoke(mExportFuncIdx_floatAdd);
    }

    private final static int mExportFuncIdx_floatSubtract = 4;
    public void invoke_floatSubtract() {
        invoke(mExportFuncIdx_floatSubtract);
    }

    private final static int mExportFuncIdx_floatMultiply = 5;
    public void invoke_floatMultiply() {
        invoke(mExportFuncIdx_floatMultiply);
    }

    private final static int mExportFuncIdx_floatDivide = 6;
    public void invoke_floatDivide() {
        invoke(mExportFuncIdx_floatDivide);
    }

    private final static int mExportFuncIdx_intAddBenchmark = 7;
    public void invoke_intAddBenchmark() {
        invoke(mExportFuncIdx_intAddBenchmark);
    }

    private final static int mExportFuncIdx_intSubtractBenchmark = 8;
    public void invoke_intSubtractBenchmark() {
        invoke(mExportFuncIdx_intSubtractBenchmark);
    }

    private final static int mExportFuncIdx_intMultiplyBenchmark = 9;
    public void invoke_intMultiplyBenchmark() {
        invoke(mExportFuncIdx_intMultiplyBenchmark);
    }

    private final static int mExportFuncIdx_floatAddBenchmark = 10;
    public void invoke_floatAddBenchmark() {
        invoke(mExportFuncIdx_floatAddBenchmark);
    }

    private final static int mExportFuncIdx_floatSubtractBenchmark = 11;
    public void invoke_floatSubtractBenchmark() {
        invoke(mExportFuncIdx_floatSubtractBenchmark);
    }

    private final static int mExportFuncIdx_floatMultiplyBenchmark = 12;
    public void invoke_floatMultiplyBenchmark() {
        invoke(mExportFuncIdx_floatMultiplyBenchmark);
    }

    private final static int mExportFuncIdx_floatDivideBenchmark = 13;
    public void invoke_floatDivideBenchmark() {
        invoke(mExportFuncIdx_floatDivideBenchmark);
    }

}

