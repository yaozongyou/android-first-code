package com.yaozongyou.fragmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button:
            /*
            AnotherRightFragment fragment = new AnotherRightFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.right_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            */
            break;
        default:
            break;
        }
    }
}
