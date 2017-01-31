package com.xjtu.kangy.WereWolf.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.xjtu.kangy.WereWolf.R;

/**
 * Created by kangy on 2017/1/31.
 */

public class GodsCustomCheckedBox extends LinearLayout {
    CheckBox cb;
    EditText et;
    public GodsCustomCheckedBox(Context context) {
        super(context);
    }

    public GodsCustomCheckedBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.gods_custom_checkedbox,this,true);
        cb= (CheckBox) findViewById(R.id.cb2);
        et= (EditText) findViewById(R.id.et);
//        et.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(cb.isChecked()){
//                    cb.setChecked(false);
//                }else {
//                    cb.setChecked(true);
//                }
//            }
//        });
    }
    public boolean isChecked(){
        return cb.isChecked();
    }
    public String getText(){
        return String.valueOf(et.getText());
    }
}
