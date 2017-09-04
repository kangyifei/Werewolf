package com.xjtu.kangy.WereWolf

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.xjtu.kangy.WereWolf.utils.Data
import kotlinx.android.synthetic.main.activity_identities.*
import java.util.*

/**
 * Created by kangy on 2016/10/20.
 */
class IdentitiesActivity : Activity() {

    private val TAG = "IdentitiesActivity"


    var playerNum = 1
    var random = 0
    var ivClicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identities)
        val intent = intent
        var identities = intent.getStringArrayListExtra("identities")
        var identitiesToshow = ArrayList<String>()
        tvPlayerNumber.text = "" + playerNum
        random = (Math.random() * identities.size).toInt()
        var identity = identities[random]
        ividentity.setOnClickListener {
            if (!ivClicked) {
                showIdentity(identity)
                ivClicked = true
            }
        }
        btnNext.setOnClickListener {
            if (!ivClicked) {
                val alertDialog = AlertDialog.Builder(this@IdentitiesActivity).setTitle("警告").setMessage("未查看身份").setPositiveButton("确定") { dialog, which -> }.create()
                alertDialog.show()
            } else {
                identities.removeAt(random)
                identitiesToshow.add(identity)
                if (identities.size != 0) {
                    playerNum++
                    tvPlayerNumber.text = "" + playerNum
                    random = (Math.random() * identities.size).toInt()
                    identity = identities[random]
                    ividentity.setImageResource(R.drawable.idetity_cover)
                    tvidentity.text = "你猜"
                    ivClicked = false
                } else {
                    val intent1 = Intent(this@IdentitiesActivity, ShowIdentitiesActivity::class.java)
                    intent1.putExtra("identitiesToShow", identitiesToshow)
                    startActivity(intent1)
                }
            }
        }

    }


    private fun showIdentity(identity: String) {
        tvidentity.text = Data.identitiesConvertToText[identity]
        ividentity.setImageResource(Data.identitiesConvertToDrawable[identity]!!)
    }
}
