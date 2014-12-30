package com.example.dsis;

import android.widget.TextView;

public class CustomViewHolder {
    public TextView []textView = new TextView[6];

    public int getLength()
    {
        return textView.length;
    }
}
