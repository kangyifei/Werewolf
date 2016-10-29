package com.xjtu.kangy.WereWolf.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xjtu.kangy.WereWolf.R;

/**
 * Created by kangy on 2016/10/20.
 */
public class GodsCheckedBox extends LinearLayout {
    CheckBox cb;
    TextView tv;
    public GodsCheckedBox(Context context) {
        super(context);
    }

    public GodsCheckedBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.gods_checkedbox,this,true);
        cb= (CheckBox) findViewById(R.id.cb);
        tv= (TextView) findViewById(R.id.tv);
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.GodsCheckedBox);
        CharSequence charSequence=a.getText(R.styleable.GodsCheckedBox_android_text);
        if(null!=tv) {tv.setText(charSequence);}
        a.recycle();
    }
    public boolean isChecked(){
        return cb.isChecked();
    }
}
