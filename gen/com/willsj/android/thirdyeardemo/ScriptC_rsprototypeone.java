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
 * The source Renderscript file: C:\Users\Will\Documents\Dropbox\University Work\Meng Electronic and Electrical Engineering\Level 3\EG3001. Third Year Project\ThirdYearProjectDemonstrationTwo\src\com\willsj\android\thirdyeardemo\rsprototypeone.rs
 */
package com.willsj.android.thirdyeardemo;

import android.renderscript.*;
import android.content.res.Resources;

/**
 * @hide
 */
public class ScriptC_rsprototypeone extends ScriptC {
    private static final String __rs_resource_name = "rsprototypeone";
    // Constructor
    public  ScriptC_rsprototypeone(RenderScript rs) {
        this(rs,
             rs.getApplicationContext().getResources(),
             rs.getApplicationContext().getResources().getIdentifier(
                 __rs_resource_name, "raw",
                 rs.getApplicationContext().getPackageName()));
    }

    public  ScriptC_rsprototypeone(RenderScript rs, Resources resources, int id) {
        super(rs, resources, id);
    }

    private FieldPacker __rs_fp_I32;
    private final static int mExportVarIdx_numberA = 0;
    private int mExportVar_numberA;
    public synchronized void set_numberA(int v) {
        setVar(mExportVarIdx_numberA, v);
        mExportVar_numberA = v;
    }

    public int get_numberA() {
        return mExportVar_numberA;
    }

    private final static int mExportVarIdx_numberB = 1;
    private int mExportVar_numberB;
    public synchronized void set_numberB(int v) {
        setVar(mExportVarIdx_numberB, v);
        mExportVar_numberB = v;
    }

    public int get_numberB() {
        return mExportVar_numberB;
    }

    private final static int mExportVarIdx_numberC = 2;
    private int mExportVar_numberC;
    public synchronized void set_numberC(int v) {
        setVar(mExportVarIdx_numberC, v);
        mExportVar_numberC = v;
    }

    public int get_numberC() {
        return mExportVar_numberC;
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

}

