package com.xjtu.kangy.WereWolf.Fragment

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xjtu.kangy.WereWolf.R
import com.xjtu.kangy.WereWolf.SetupActivity
import com.xjtu.kangy.WereWolf.utils.Data

/**
 * Created by kangy on 2017/9/4.
 */
class WelcomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater?.inflate(R.layout.fragment_welcome, container, false)
        val btn_localmode = v?.findViewById(R.id.btn_localmode)
        val btn_netmode = v?.findViewById(R.id.btn_netmode)
        btn_localmode?.setOnClickListener {
            Data.mode = Data.MODE_LOCALMODE
            startSetupActivity()
        }
        btn_netmode?.setOnClickListener {
            Data.mode = Data.MODE_NETMODE
            activity.fragmentManager.beginTransaction().replace(R.id.FragmentContainer_ActicityWelcome, NetSetupFragment()).commit()
        }
        return v!!
    }

    fun startSetupActivity() {
        val intent = Intent(activity, SetupActivity::class.java)
        startActivity(intent)
    }
}