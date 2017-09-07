package com.xjtu.kangy.WereWolf

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.uuzuche.lib_zxing.activity.CodeUtils
import com.xjtu.kangy.WereWolf.utils.ClientHandler
import com.xjtu.kangy.WereWolf.utils.Data
import com.xjtu.kangy.WereWolf.utils.TcpServer
import kotlinx.android.synthetic.main.activity_net.*
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.filter.codec.textline.LineDelimiter
import org.apache.mina.filter.codec.textline.TextLineCodecFactory
import org.apache.mina.transport.socket.nio.NioSocketConnector
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket
import java.nio.charset.Charset

/**
 * Created by kangy on 2017/9/4.
 */
class NetActivity : Activity() {
    var creatRoom = false
    var allconnected = false
    var ip = 0
    var text: TextView? = null
    var identities = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)
        var bundle = intent.extras
        identities == intent.getStringArrayListExtra("identities")
        if (bundle.getBoolean("netmode_creatroom")) {
            creatRoom = true
            getWiFiState()
            showQRCodeDialog()
            btn_QRcode.setOnClickListener {
                showQRCodeDialog()
            }
            Data.running = true
            startService(Intent(this@NetActivity, TcpServer::class.java))
//            ParseCommandService.chating = true
//            startService(Intent(this, ParseCommandService::class.java))
//            var ioAcceptor = NioSocketAcceptor()
//            var linecode = TextLineCodecFactory(Charset.forName("UTF-8"),
//                    LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())
//            linecode.decoderMaxLineLength = 1024
//            linecode.encoderMaxLineLength = 1024
//            ioAcceptor.filterChain.addLast("codec", ProtocolCodecFilter(linecode))
//            ioAcceptor.sessionConfig.readBufferSize = 2048
//            ioAcceptor.sessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, 10)
//            ioAcceptor.handler = ServerHandler()
//            ioAcceptor.bind(InetSocketAddress(3057))
        } else {
            creatRoom = false
            ip = bundle.getInt("ip")
            btn_QRcode.clearAnimation()
            btn_QRcode.clearFocus()
            btn_QRcode.isClickable = false
            btn_QRcode.visibility = View.GONE
            var ioConnector = NioSocketConnector()
            ioConnector.connectTimeoutMillis = 30 * 1000
            var linecode = TextLineCodecFactory(Charset.forName("UTF-8"),
                    LineDelimiter.WINDOWS.value, LineDelimiter.WINDOWS.value)
            linecode.decoderMaxLineLength = 1024
            linecode.encoderMaxLineLength = 1024
            ioConnector.filterChain.addLast("codec", ProtocolCodecFilter(linecode))
            ioConnector.handler = ClientHandler()
            var ipbyte: ByteArray = kotlin.ByteArray(4)
            ipbyte[0] = (ip.shr(24) and 0xFF).toByte()
            ipbyte[1] = (ip.shr(16) and 0xFF).toByte()
            ipbyte[2] = (ip.shr(8) and 0xFF).toByte()
            ipbyte[3] = (ip.shr(0) and 0xFF).toByte()
            var connectFuture = ioConnector.connect(InetSocketAddress(InetAddress.getByAddress(ipbyte), 3057))
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
        Log.e("wifi_ip", ip.toString())
    }

    override fun onDestroy() {
        Data.running = false
        stopService(Intent(this@NetActivity, TcpServer::class.java))
//        ParseCommandService.chating = false
//        stopService(Intent(this, ParseCommandService::class.java))
        super.onDestroy()
    }

    class receiver constructor(handler: Handler) : BroadcastReceiver() {
        var handler: Handler = handler

        init {
            this.handler = handler
        }

        override fun onReceive(context: Context?, intent: Intent?) {
            var msg = Message()
            msg.arg1 = intent?.getStringExtra("msg")!!.toInt()
            msg.obj = intent.extras.get("ip")
            handler.sendMessage(msg)
        }
    }

    fun sendString(socket: Socket?, msg: String?) {
    }

    fun randomIdentity(): String? {
        var random = (Math.random() * identities.size).toInt()
        val res = identities[random]
        identities.remove(res)
        return Data.identitiesConvertToText[res]
    }

}