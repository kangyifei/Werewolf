package com.xjtu.kangy.WereWolf

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.uuzuche.lib_zxing.activity.CodeUtils
import kotlinx.android.synthetic.main.activity_net.*

/**
 * Created by kangy on 2017/9/4.
 */
class NetActivity : Activity() {
    var creatRoom = false
    var allconnected = false
    var ip = 0
    var text: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)
        var bundle = intent.extras
        if (bundle.getBoolean("netmode_creatroom")) {
            creatRoom = true
            getWiFiState()
            showQRCodeDialog()
            btn_QRcode.setOnClickListener {
                showQRCodeDialog()
            }
        } else {
            creatRoom = false
            ip = bundle.getInt("ip")
            btn_QRcode.visibility = View.GONE
            btn_QRcode.isClickable = false
        }
    }

    fun showQRCodeDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_showqrcode, null)
        var image = view.findViewById(R.id.imageview_QRcode) as ImageView
        text = view.findViewById(R.id.textview_numconnected) as TextView
        var dialog = AlertDialog.Builder(this@NetActivity).setTitle("扫描二维码以加入游戏房间").setView(view)
                .setPositiveButton("确定", null).create()
        image.setImageBitmap(CodeUtils.createImage(ip.toString(), 400, 400, null))
        dialog.show()
        val d = windowManager.defaultDisplay // 获取屏幕宽、高度
        var p = dialog.window.attributes // 获取对话框当前的参数值
        p.height = (d.height * 0.5).toInt()
        p.width = (d.width * 0.8).toInt()
        dialog.window.attributes = p
    }

    fun getWiFiState() {
        // 取得WifiManager对象
        val mWifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        // 取得WifiInfo对象
        ip = mWifiManager.connectionInfo.ipAddress
    }
}