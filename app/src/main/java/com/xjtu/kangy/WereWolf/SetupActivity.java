package com.xjtu.kangy.WereWolf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xjtu.kangy.WereWolf.widget.GodsCheckedBox;

import java.util.ArrayList;

//TODO:加入盗贼职业，卡牌介绍，APP声明
public class SetupActivity extends BasicActivity {
    final String TAG="SetupActivity";
    GodsCheckedBox god1, god2, god3, god4, god5, god6, god7, god8, god9, wolfGod1, wolfGod2, wolfGod3;
    Button btnStart;
    EditText etGameNumber, etWolvesNumber;
    int gameNum, wolvesNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initViews();
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
                Log.w(TAG,"godsNUM"+godsNum);
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
        btnStart = (Button) findViewById(R.id.startGame);
        etGameNumber = (EditText) findViewById(R.id.ETGameNumber);
        etWolvesNumber = (EditText) findViewById(R.id.ETWolvesNumber);
    }

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
        Log.w(TAG,"wolvesNUM"+num);
    }

    private void addVillager(ArrayList arrayList, int gameNum, int godsNum, int wolvesNum) {
        for (int i = 0; i < (gameNum - godsNum - wolvesNum); i++) {
            arrayList.add("villager");
        }
    }
}
