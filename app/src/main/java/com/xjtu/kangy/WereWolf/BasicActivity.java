package com.xjtu.kangy.WereWolf;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by kangy on 2016/10/20.
 */
public abstract class BasicActivity extends Activity{
    protected abstract void initViews();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }
}
