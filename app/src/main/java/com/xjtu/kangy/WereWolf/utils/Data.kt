package com.xjtu.kangy.WereWolf.utils

import android.app.Application
import com.uuzuche.lib_zxing.activity.ZXingLibrary
import com.xjtu.kangy.WereWolf.R
import java.net.InetAddress
import java.net.Socket

/**
 * Created by kangy on 2016/10/20.
 */

class Data : Application() {

    override fun onCreate() {
        identitiesConvertToText.put("god1", "预言家")
        identitiesConvertToText.put("god2", "女巫")
        identitiesConvertToText.put("god3", "猎人")
        identitiesConvertToText.put("god4", "守卫")
        identitiesConvertToText.put("god5", "长老")
        identitiesConvertToText.put("god6", "丘比特")
        identitiesConvertToText.put("god7", "白痴")
        identitiesConvertToText.put("god8", "熊")
        identitiesConvertToText.put("god9", "野孩子")
        identitiesConvertToText.put("wolfgod1", "狼王")
        identitiesConvertToText.put("wolfgod2", "白狼王")
        identitiesConvertToText.put("wolfgod3", "隐狼")
        identitiesConvertToText.put("wolf", "狼人")
        identitiesConvertToText.put("villager", "普通村民")
        identitiesConvertToDrawable.put("god1", R.drawable.god1)
        identitiesConvertToDrawable.put("god2", R.drawable.god2)
        identitiesConvertToDrawable.put("god3", R.drawable.god3)
        identitiesConvertToDrawable.put("god4", R.drawable.god4)
        identitiesConvertToDrawable.put("god5", R.drawable.god5)
        identitiesConvertToDrawable.put("god6", R.drawable.god6)
        identitiesConvertToDrawable.put("god7", R.drawable.god7)
        identitiesConvertToDrawable.put("god8", R.drawable.god8)
        identitiesConvertToDrawable.put("god9", R.drawable.god9)
        identitiesConvertToDrawable.put("wolfgod1", R.drawable.wolfgod1)
        identitiesConvertToDrawable.put("wolfgod2", R.drawable.wolfgod2)
        identitiesConvertToDrawable.put("wolfgod3", R.drawable.wolfgod3)
        identitiesConvertToDrawable.put("wolf", R.drawable.wolf)
        identitiesConvertToDrawable.put("villager", R.drawable.villager)
        ZXingLibrary.initDisplayOpinion(this)
        super.onCreate()
    }

    companion object {
        val identitiesConvertToText = HashMap<String, String>()
        val identitiesConvertToDrawable = HashMap<String, Int>()
        var mode = 0
        val MODE_LOCALMODE = 1
        val MODE_NETMODE = 2
        var NETMODE = 0
        val NETMODE_CREATROOM = 1
        val NETMODE_JOINROOM = 2
        var maxPlayerNum = 0
        var CurrentPlayerNum = 0
        var ipList = ArrayList<InetAddress>()
        var ipMap = HashMap<InetAddress, Socket>()
        val SENDMSG_ACTION = "com.xjtu.kangy.WereWolf.SendAciton"
        var running = false
        var receivedMsgList = ArrayList<Event>()
        var sendMsgList = ArrayList<Event>()
    }
}
