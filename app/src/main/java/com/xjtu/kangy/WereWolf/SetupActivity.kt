package com.xjtu.kangy.WereWolf

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu
import com.xjtu.kangy.WereWolf.utils.Data
import kotlinx.android.synthetic.main.activity_setup.*
import kotlinx.android.synthetic.main.basic_leftslimenu_setupactivtiy.*
import kotlinx.android.synthetic.main.list_leftslimenu_setupactivtiy.*
import kotlinx.android.synthetic.main.recomplayers_leftslimenu_setupactivtiy.*
import kotlinx.android.synthetic.main.rightres_leftslimenu_setupactivtiy.*
import kotlinx.android.synthetic.main.roles_leftslimenu_setupactivtiy.*
import java.util.*

//TODO:加入盗贼职业，卡牌介绍，APP声明
class SetupActivity : Activity() {
    internal val TAG = "SetupActivity"
    internal var gameNum: Int = 0
    internal var wolvesNum: Int = 0
    internal var onItemClickListener: AdapterView.OnItemClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
        apptitle.requestFocus()
        //        basicRules = this.getString(R.string.text_basic_rule);
        //        rolesRules = this.getString(R.string.text_roles_rule);
        //        recomPlayerRules = this.getString(R.string.text_recommendplayers_rule);
        //        rightResRules = this.getString(R.string.text_rightreserved_rule);
        setSlidingMenu()
        //        setLinkedText();

        //        setDrawerLayout();
        //        etGameNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        //            @Override
        //            public void onFocusChange(View v, boolean hasFocus) {
        //                if (!hasFocus) {
        //                    gameNum = Integer.valueOf(etGameNumber.getText().toString());
        //                }
        //            }
        //        });
        //        etWolvesNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        //            @Override
        //            public void onFocusChange(View v, boolean hasFocus) {
        //                if (!hasFocus) {
        //                    wolvesNum = Integer.valueOf(etWolvesNumber.getText().toString());
        //                }
        //            }
        //        });

        btnStart.setOnClickListener {
            if (etGameNumber.text.toString().isEmpty() || etWolvesNumber.text.toString().isEmpty()) {
                val alertDialog = AlertDialog.Builder(this@SetupActivity).setTitle("警告").setMessage("人数不能为空").setPositiveButton("确定", DialogInterface.OnClickListener { dialog, which -> }).create()
                alertDialog.show()
            } else {
                gameNum = Integer.valueOf(etGameNumber.text.toString())!!
                wolvesNum = Integer.valueOf(etWolvesNumber.text.toString())!!
                Data.maxPlayerNum = gameNum
                val identities = ArrayList<String>()
                if (godcustom1.isChecked && godcustom1.text.isEmpty()
                        || godcustom2.isChecked && godcustom2.text.isEmpty()
                        || godcustom3.isChecked && godcustom3.text.isEmpty()) {
                    val alertDialog = AlertDialog.Builder(this@SetupActivity).setTitle("警告").setMessage("自定义神未取名").setPositiveButton("确定", DialogInterface.OnClickListener { dialog, which -> }).create()
                    alertDialog.show()
                } else {
                    val godsNum = addGodsReturnNum(identities)
                    Log.w(TAG, "godsNUM" + godsNum)
                    addWolves(identities, wolvesNum)
                    addVillager(identities, gameNum, godsNum, wolvesNum)
                    if (Data.mode == Data.MODE_LOCALMODE) {
                        val intent = Intent(this@SetupActivity, IdentitiesActivity::class.java)
                        intent.putExtra("identities", identities)
                        startActivity(intent)
                    } else if (Data.mode == Data.MODE_NETMODE) {
                        val intent = Intent(this@SetupActivity, NetActivity::class.java)
                        intent.putExtra("netmode_creatroom", true)
                        intent.putExtra("identities", identities)
                        startActivity(intent)
                    }
                }
            }
        }
    }


    private fun setSlidingMenu() {
        val menu = SlidingMenu(this)
        menu.mode = SlidingMenu.LEFT
        // 设置触摸屏幕的模式
        menu.touchModeAbove = SlidingMenu.TOUCHMODE_FULLSCREEN
        //        menu.setShadowWidthRes(R.dimen.shadow_width);
        //        menu.setShadowDrawable(R.drawable.shadow);
        //设置后视图阴影
        menu.setOffsetFadeDegree(0.5f)
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset)
        // 设置渐入渐出效果的值
        //        menu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT)
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.list_leftslimenu_setupactivtiy)

        //侧滑菜单列表设置
        val ruleListText = arrayOf(this.getString(R.string.basic_rule), this.getString(R.string.roles_rule), this.getString(R.string.recommendplayers_rule), this.getString(R.string.rightreserved_rule))
        val mArrayAdapter = ArrayAdapter(this@SetupActivity, android.R.layout.simple_list_item_1, ruleListText)
        list_rule.adapter = mArrayAdapter
        //        mBackBtn = (Button) findViewById(R.id.rules_button);
        //        Log.w("BackBTNid-1", "" + R.id.rules_button);
        val backBtnOnClickListener = View.OnClickListener {
            menu.setMenu(R.layout.list_leftslimenu_setupactivtiy)
            list_rule.adapter = mArrayAdapter
            list_rule.onItemClickListener = onItemClickListener
        }
        onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    menu.setMenu(R.layout.basic_leftslimenu_setupactivtiy)
                    rules_button1.setOnClickListener(backBtnOnClickListener)
                }
                1 -> {
                    menu.setMenu(R.layout.roles_leftslimenu_setupactivtiy)
                    rules_button2.setOnClickListener(backBtnOnClickListener)
                }
                2 -> {
                    menu.setMenu(R.layout.recomplayers_leftslimenu_setupactivtiy)
                    rules_button3.setOnClickListener(backBtnOnClickListener)
                }
                3 -> {
                    menu.setMenu(R.layout.rightres_leftslimenu_setupactivtiy)
                    rules_button4.setOnClickListener(backBtnOnClickListener)
                }
                else -> {
                }
            }
        }
        list_rule.onItemClickListener = onItemClickListener

    }


    private fun addGodsReturnNum(arrayList: ArrayList<String>): Int {
        var num = 0
        if (god1.isChecked) {
            arrayList.add("god1")
            num++
        }
        if (god2.isChecked) {
            arrayList.add("god2")
            num++
        }
        if (god3.isChecked) {
            arrayList.add("god3")
            num++
        }
        if (god4.isChecked) {
            arrayList.add("god4")
            num++
        }
        if (god5.isChecked) {
            arrayList.add("god5")
            num++
        }
        if (god6.isChecked) {
            arrayList.add("god6")
            num++
        }
        if (god7.isChecked) {
            arrayList.add("god7")
            num++
        }
        if (god8.isChecked) {
            arrayList.add("god8")
            num++
        }
        if (god9.isChecked) {
            arrayList.add("god9")
            num++
        }
        if (godcustom1.isChecked) {
            Data.identitiesConvertToDrawable.put("godCustom1", R.drawable.godcustom)
            Data.identitiesConvertToText.put("godCustom1", godcustom1.text)
            arrayList.add("godCustom1")
            num++
        }
        if (godcustom2.isChecked) {
            Data.identitiesConvertToDrawable.put("godCustom2", R.drawable.godcustom)
            Data.identitiesConvertToText.put("godCustom2", godcustom2.text)
            arrayList.add("godCustom2")
            num++
        }
        if (godcustom3.isChecked) {
            Data.identitiesConvertToDrawable.put("godCustom3", R.drawable.godcustom)
            Data.identitiesConvertToText.put("godCustom3", godcustom3.text)
            arrayList.add("godCustom3")
            num++
        }
        return num
    }

    private fun addWolves(arrayList: ArrayList<String>, wolvesNum: Int) {
        var num = wolvesNum
        if (wolfgod1.isChecked) {
            arrayList.add("wolfgod1")
            num--
        }
        if (wolfgod2.isChecked) {
            arrayList.add("wolfgod2")
            num--
        }
        if (wolfgod3.isChecked) {
            arrayList.add("wolfgod3")
            num--
        }
        for (i in 0..num - 1) {
            arrayList.add("wolf")
        }
        Log.w(TAG, "wolvesNUM" + num)
    }

    private fun addVillager(arrayList: ArrayList<String>, gameNum: Int, godsNum: Int, wolvesNum: Int) {
        for (i in 0..gameNum - godsNum - wolvesNum - 1) {
            arrayList.add("villager")
        }
    }
}
