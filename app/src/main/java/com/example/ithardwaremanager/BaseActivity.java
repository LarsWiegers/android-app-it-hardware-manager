package com.example.ithardwaremanager;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    /**
     * Do a color change based on the the screen orientation
     * @return void
     */
    @Override
    protected void onResume() {
        super.onResume();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setBackgroundColor(Color.parseColor("#D81B60"));
        }
    }
}
