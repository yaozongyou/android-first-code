package com.yaozongyou.helloandroid;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.yaozongyou.helloandroid.MainAcitiviyTest \
 * com.yaozongyou.helloandroid.tests/android.test.InstrumentationTestRunner
 */
public class MainAcitiviyTest extends ActivityInstrumentationTestCase2<MainAcitiviy> {

    public MainAcitiviyTest() {
        super("com.yaozongyou.helloandroid", MainAcitiviy.class);
    }

}
