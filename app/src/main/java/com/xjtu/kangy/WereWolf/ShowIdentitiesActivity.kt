package com.xjtu.kangy.WereWolf

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.xjtu.kangy.WereWolf.utils.Data
import kotlinx.android.synthetic.main.activity_showidentities.*
import java.util.*

/**
 * Created by kangy on 2016/10/20.
 */
class ShowIdentitiesActivity : Activity() {
    internal val TAG = "ShowIdentitiesActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showidentities)
        val intent = intent
        val identities = intent.getStringArrayListExtra("identitiesToShow")
        val tvIdentities = ArrayList<TextView>()
        val isClicked = ArrayList<Boolean>()
        val tableLayout = findViewById(R.id.tableIdentities) as TableLayout
        val params = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        for (i in 0..identities.size / 3 + 1 - 1) {
            val tableRow = TableRow(this)
            for (n in 0..2) {
                val textView = TextView(this)
                //                textView.setText("test");
                textView.gravity = View.TEXT_ALIGNMENT_CENTER
                textView.textSize = 20f
                textView.setPadding(5, 10, 5, 5)
                tvIdentities.add(textView)
                isClicked.add(false)
                tableRow.addView(textView, params)
            }
            tableLayout.addView(tableRow, i)
            Log.w(TAG, "tvIdentities.size" + tvIdentities.size)
        }
        for (i in identities.indices) {
            val textView = tvIdentities[i]
            textView.text = (i + 1).toString() + "号选手"
            Log.w(TAG, "tvIdentities" + (i + 1) + "号选手")
            val finalI = i
            textView.setOnClickListener {
                Log.w(TAG, "clicked")
                if (!isClicked[finalI]) {
                    textView.text = Data.identitiesConvertToText[identities[finalI]]
                    isClicked[finalI] = true
                } else {
                    textView.text = (finalI + 1).toString() + "号选手"
                    isClicked[finalI] = false
                }
            }
        }
        btnRestart.setOnClickListener {
            val intent1 = Intent(this@ShowIdentitiesActivity, SetupActivity::class.java)
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent1)
        }
    }
}
