package com.yaozongyou.broadcastbestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends BaseActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button forceOffline = (Button)findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.yaozongyou.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
