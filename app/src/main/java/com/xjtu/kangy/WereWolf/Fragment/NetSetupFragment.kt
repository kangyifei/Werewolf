package com.xjtu.kangy.WereWolf.Fragment

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uuzuche.lib_zxing.activity.CaptureActivity
import com.xjtu.kangy.WereWolf.R
import com.xjtu.kangy.WereWolf.SetupActivity
import com.xjtu.kangy.WereWolf.utils.Data

/**
 * Created by kangy on 2017/9/4.
 */
class NetSetupFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater?.inflate(R.layout.fragment_netsetup, container, false)
        val btn_creatroom = v?.findViewById(R.id.btn_creatroom)
        val btn_joinroom = v?.findViewById(R.id.btn_joinroom)
        btn_creatroom?.setOnClickListener {
            Data.NETMODE = Data.NETMODE_CREATROOM
            startActivity(Intent(activity, SetupActivity::class.java))
        }
        btn_joinroom?.setOnClickListener {
            Data.NETMODE = Data.NETMODE_JOINROOM
            val intent = Intent(activity, CaptureActivity::class.java)
            activity.startActivityForResult(intent, 1)
        }
        return v!!
    }


}