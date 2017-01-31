package com.xjtu.kangy.WereWolf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.xjtu.kangy.WereWolf.utils.Data;
import com.xjtu.kangy.WereWolf.widget.GodsCheckedBox;
import com.xjtu.kangy.WereWolf.widget.GodsCustomCheckedBox;

import java.util.ArrayList;


//TODO:加入盗贼职业，卡牌介绍，APP声明
public class SetupActivity extends BasicActivity {
    final String TAG = "SetupActivity";
    GodsCheckedBox god1, god2, god3, god4, god5, god6, god7, god8, god9, wolfGod1, wolfGod2, wolfGod3;
    GodsCustomCheckedBox godCustom1,godCustom2,godCustom3;
    Button btnStart, btnRulesComeBack;
    EditText etGameNumber, etWolvesNumber;
    ListView mListView;
    DrawerLayout drawerLayout;
    TextView rulesTV;
    int gameNum, wolvesNum;
    String basicRules, rolesRules, recomPlayerRules, rightResRules;
    Link linkBasicRules;
    Link linkRolesRules;
    Link linkRecomPlayersRules;
    Link linkRightResRules;
    Button mBackBtn;
    AdapterView.OnItemClickListener onItemClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initViews();
//        basicRules = this.getString(R.string.text_basic_rule);
//        rolesRules = this.getString(R.string.text_roles_rule);
//        recomPlayerRules = this.getString(R.string.text_recommendplayers_rule);
//        rightResRules = this.getString(R.string.text_rightreserved_rule);
        setSlidingMenu();
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

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameNum = Integer.valueOf(etGameNumber.getText().toString());
                wolvesNum = Integer.valueOf(etWolvesNumber.getText().toString());
                ArrayList<String> identities = new ArrayList<String>();
                int godsNum = addGodsReturnNum(identities);
                Log.w(TAG, "godsNUM" + godsNum);
                addWolves(identities, wolvesNum);
                addVillager(identities, gameNum, godsNum, wolvesNum);
                Intent intent = new Intent(SetupActivity.this, IdentitiesActivity.class);
                intent.putExtra("identities", identities);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initViews() {
        god1 = (GodsCheckedBox) findViewById(R.id.god1);
        god2 = (GodsCheckedBox) findViewById(R.id.god2);
        god3 = (GodsCheckedBox) findViewById(R.id.god3);
        god4 = (GodsCheckedBox) findViewById(R.id.god4);
        god5 = (GodsCheckedBox) findViewById(R.id.god5);
        god6 = (GodsCheckedBox) findViewById(R.id.god6);
        god7 = (GodsCheckedBox) findViewById(R.id.god7);
        god8 = (GodsCheckedBox) findViewById(R.id.god8);
        god9 = (GodsCheckedBox) findViewById(R.id.god9);
        wolfGod1 = (GodsCheckedBox) findViewById(R.id.wolfgod1);
        wolfGod2 = (GodsCheckedBox) findViewById(R.id.wolfgod2);
        wolfGod3 = (GodsCheckedBox) findViewById(R.id.wolfgod3);
        godCustom1= (GodsCustomCheckedBox) findViewById(R.id.godcustom1);
        godCustom2= (GodsCustomCheckedBox) findViewById(R.id.godcustom2);
        godCustom3= (GodsCustomCheckedBox) findViewById(R.id.godcustom3);
        btnStart = (Button) findViewById(R.id.btnstartGame);
        etGameNumber = (EditText) findViewById(R.id.ETGameNumber);
        etWolvesNumber = (EditText) findViewById(R.id.ETWolvesNumber);


    }

    private void setSlidingMenu() {
        final SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.shadow);
        //设置后视图阴影
        menu.setOffsetFadeDegree(0.5f);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
//        menu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.list_leftslimenu_setupactivtiy);

        //侧滑菜单列表设置
        mListView = (ListView) findViewById(R.id.list_rule);
        String[] ruleListText = {this.getString(R.string.basic_rule),
                this.getString(R.string.roles_rule),
                this.getString(R.string.recommendplayers_rule),
                this.getString(R.string.rightreserved_rule),
        };
        final ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>
                (SetupActivity.this, android.R.layout.simple_list_item_1, ruleListText);
        mListView.setAdapter(mArrayAdapter);
//        mBackBtn = (Button) findViewById(R.id.rules_button);
//        Log.w("BackBTNid-1", "" + R.id.rules_button);
        final View.OnClickListener backBtnOnClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setMenu(R.layout.list_leftslimenu_setupactivtiy);
                mListView = (ListView) findViewById(R.id.list_rule);
                mListView.setAdapter(mArrayAdapter);
                mListView.setOnItemClickListener(onItemClickListener);
            }
        };
        onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        menu.setMenu(R.layout.basic_leftslimenu_setupactivtiy);
                        mBackBtn = (Button) findViewById(R.id.rules_button1);
                        mBackBtn.setOnClickListener(backBtnOnClickListener);
                        break;
                    case 1:
                        menu.setMenu(R.layout.roles_leftslimenu_setupactivtiy);
                        mBackBtn = (Button) findViewById(R.id.rules_button2);
                        mBackBtn.setOnClickListener(backBtnOnClickListener);
                        break;
                    case 2:
                        menu.setMenu(R.layout.recomplayers_leftslimenu_setupactivtiy);
                        mBackBtn = (Button) findViewById(R.id.rules_button3);
                        mBackBtn.setOnClickListener(backBtnOnClickListener);
                        break;
                    case 3:
                        menu.setMenu(R.layout.rightres_leftslimenu_setupactivtiy);
                        mBackBtn = (Button) findViewById(R.id.rules_button4);
                        mBackBtn.setOnClickListener(backBtnOnClickListener);
                        break;
                    default:
                        break;
                }
            }
        };
        mListView.setOnItemClickListener(onItemClickListener);

    }

    private void setLinkedText() {
        linkBasicRules = new Link(this.getString(R.string.basic_rule))
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {
                        rulesTV.setText(basicRules);
                        btnRulesComeBack.setVisibility(View.VISIBLE);

                    }
                });
        linkRolesRules = new Link(this.getString(R.string.roles_rule))
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {
                        rulesTV.setText(rolesRules);
                        btnRulesComeBack.setVisibility(View.VISIBLE);
                    }
                });
        linkRecomPlayersRules = new Link(this.getString(R.string.recommendplayers_rule))
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {
                        rulesTV.setText(recomPlayerRules);
                        btnRulesComeBack.setVisibility(View.VISIBLE);
                    }
                });
        linkRightResRules = new Link(this.getString(R.string.rightreserved_rule))
                .setOnClickListener(new Link.OnClickListener() {
                    @Override
                    public void onClick(String clickedText) {
                        rulesTV.setText(rightResRules);
                        btnRulesComeBack.setVisibility(View.VISIBLE);
                    }
                });
        Log.w("rules", this.getString(R.string.roles_rule)
                + this.getString(R.string.recommendplayers_rule)
                + this.getString(R.string.recommendplayers_rule)
                + this.getString(R.string.rightreserved_rule));
//        rulesTV= (TextView) findViewById(R.id.rlues_tv);
//        LinkBuilder.on(rulesTV)
//                .addLink(linkBasicRules).build();
////                .addLink(linkRecomPlayersRules)
////                .addLink(linkRightResRules)
////                .addLink(linkRolesRules)

        btnRulesComeBack = (Button) findViewById(R.id.rules_button);
        btnRulesComeBack.setVisibility(View.INVISIBLE);
        btnRulesComeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkBuilder.on(rulesTV)
                        .addLink(linkBasicRules)
                        .addLink(linkRecomPlayersRules)
                        .addLink(linkRightResRules)
                        .addLink(linkRolesRules)
                        .build();
                btnRulesComeBack.setVisibility(View.INVISIBLE);
            }
        });

    }
//    private void setDrawerLayout(){
//        drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
//    }

    private int addGodsReturnNum(ArrayList arrayList) {
        int num = 0;
        if (god1.isChecked()) {
            arrayList.add("god1");
            num++;
        }
        if (god2.isChecked()) {
            arrayList.add("god2");
            num++;
        }
        if (god3.isChecked()) {
            arrayList.add("god3");
            num++;
        }
        if (god4.isChecked()) {
            arrayList.add("god4");
            num++;
        }
        if (god5.isChecked()) {
            arrayList.add("god5");
            num++;
        }
        if (god6.isChecked()) {
            arrayList.add("god6");
            num++;
        }
        if (god7.isChecked()) {
            arrayList.add("god7");
            num++;
        }
        if (god8.isChecked()) {
            arrayList.add("god8");
            num++;
        }
        if (god9.isChecked()) {
            arrayList.add("god9");
            num++;
        }
        if(godCustom1.isChecked()){
            Data.getIdentitiesConvertToDrawable().put("godCustom1",R.drawable.godcustom);
            Data.getIdentitiesConvertToText().put("godCustom1",godCustom1.getText());
            arrayList.add("godCustom1");
            num++;
        }
        if(godCustom2.isChecked()){
            Data.getIdentitiesConvertToDrawable().put("godCustom2",R.drawable.godcustom);
            Data.getIdentitiesConvertToText().put("godCustom2",godCustom2.getText());
            arrayList.add("godCustom2");
            num++;
        }
        if(godCustom3.isChecked()){
            Data.getIdentitiesConvertToDrawable().put("godCustom3",R.drawable.godcustom);
            Data.getIdentitiesConvertToText().put("godCustom3",godCustom3.getText());
            arrayList.add("godCustom3");
            num++;
        }
        return num;
    }

    private void addWolves(ArrayList arrayList, int wolvesNum) {
        int num = wolvesNum;
        if (wolfGod1.isChecked()) {
            arrayList.add("wolfgod1");
            num--;
        }
        if (wolfGod2.isChecked()) {
            arrayList.add("wolfgod2");
            num--;
        }
        if (wolfGod3.isChecked()) {
            arrayList.add("wolfgod3");
            num--;
        }
        for (int i = 0; i < num; i++) {
            arrayList.add("wolf");
        }
        Log.w(TAG, "wolvesNUM" + num);
    }

    private void addVillager(ArrayList arrayList, int gameNum, int godsNum, int wolvesNum) {
        for (int i = 0; i < (gameNum - godsNum - wolvesNum); i++) {
            arrayList.add("villager");
        }
    }
}
