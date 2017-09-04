package com.xjtu.kangy.WereWolf

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.uuzuche.lib_zxing.activity.CodeUtils
import com.xjtu.kangy.WereWolf.Fragment.WelcomeFragment


/**
 * Created by kangy on 2017/1/31.
 */

class WelcomeActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        var v = fragmentManager.findFragmentById(R.id.fragment_welcome)
        if (v == null) {
            fragmentManager.beginTransaction().add(R.id.FragmentContainer_ActicityWelcome, WelcomeFragment()).commit()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (data != null) {
                if (data.extras.getInt(CodeUtils.RESULT_TYPE) === CodeUtils.RESULT_SUCCESS) {
                    val result = data.extras.getString(CodeUtils.RESULT_STRING).toInt()
                    var intent = Intent(this, NetActivity::class.java)
                    intent.putExtra("ip", result)
                    intent.putExtra("netmode_creatroom", false)
                    startActivity(intent)
                } else if (data.extras.getInt(CodeUtils.RESULT_TYPE) === CodeUtils.RESULT_FAILED) {
                }
            }
        }
    }


}
