package com.yaozongyou.servicebestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent = new Intent(this, LongRunningService.class);
        startService(intent);
    }
}
