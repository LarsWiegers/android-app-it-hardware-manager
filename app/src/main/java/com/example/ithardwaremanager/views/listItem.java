package com.example.ithardwaremanager.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.ithardwaremanager.R;

public class listItem extends LinearLayout {


    public listItem(Context context) {
        super(context);
    }

    public listItem(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public listItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * Initialize view
     */
    private void init() {

        //Inflate xml resource, pass "this" as the parent, we use <merge> tag in xml to avoid
        //redundant parent, otherwise a LinearLayout will be added to this LinearLayout ending up
        //with two view groups
        inflate(getContext(), R.layout.list_item, this);
    }
}