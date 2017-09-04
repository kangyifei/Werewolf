package com.xjtu.kangy.WereWolf.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.xjtu.kangy.WereWolf.R
import kotlinx.android.synthetic.main.gods_custom_checkedbox.view.*

/**
 * Created by kangy on 2017/1/31.
 */

class GodsCustomCheckedBox : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.gods_custom_checkedbox, this, true)
    }

    val isChecked: Boolean
        get() = cb2.isChecked
    val text: String
        get() = et.text.toString()
}
