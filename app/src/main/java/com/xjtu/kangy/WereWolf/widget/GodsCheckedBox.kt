package com.xjtu.kangy.WereWolf.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

import com.xjtu.kangy.WereWolf.R
import kotlinx.android.synthetic.main.gods_checkedbox.view.*

/**
 * Created by kangy on 2016/10/20.
 */
class GodsCheckedBox : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.gods_checkedbox, this, true)
        val a = context.obtainStyledAttributes(attrs, R.styleable.GodsCheckedBox)
        val charSequence = a.getText(R.styleable.GodsCheckedBox_android_text)
        if (null != tv) {
            tv!!.text = charSequence
        }
        a.recycle()
        tv!!.setOnClickListener {
            cb.isChecked = !cb.isChecked
        }
    }

    val isChecked: Boolean
        get() = cb.isChecked
}
