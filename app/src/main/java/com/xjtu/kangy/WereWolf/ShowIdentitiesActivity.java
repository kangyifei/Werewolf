package com.xjtu.kangy.WereWolf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.xjtu.kangy.WereWolf.utils.Data;

import java.util.ArrayList;

/**
 * Created by kangy on 2016/10/20.
 */
public class ShowIdentitiesActivity extends BasicActivity {
    final String TAG="ShowIdentitiesActivity";
    Button btnRestart;
    @Override
    protected void initViews() {
        btnRestart= (Button) findViewById(R.id.btnRestart);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showidentities);
        initViews();
        Intent intent = getIntent();
        final ArrayList<String> identities = intent.getStringArrayListExtra("identitiesToShow");
        ArrayList<TextView> tvIdentities = new ArrayList<>();
        final ArrayList<Boolean> isClicked = new ArrayList<>();
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.tableIdentities);
        TableRow.LayoutParams params = new TableRow.
                LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        for (int i = 0; i < (identities.size() / 3 + 1); i++) {
            TableRow tableRow = new TableRow(this);
            for (int n = 0; n < 3; n++) {
                TextView textView = new TextView(this);
//                textView.setText("test");
                textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextSize(20);
                textView.setPadding(5,10,5,5);
                tvIdentities.add(textView);
                isClicked.add(false);
                tableRow.addView(textView,params);
            }
            tableLayout.addView(tableRow,i);
            Log.w(TAG,"tvIdentities.size"+tvIdentities.size());
        }
        for (int i = 0; i < identities.size(); i++) {
            final TextView textView = tvIdentities.get(i);
            textView.setText((i + 1) + "号选手");
            Log.w(TAG,"tvIdentities"+(i + 1) + "号选手");
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w(TAG,"clicked");
                    if (!isClicked.get(finalI)) {
                        textView.setText(Data.getIdentitiesConvertToText().get(identities.get(finalI)));
                        isClicked.set(finalI, true);
                    } else {
                        textView.setText((finalI + 1) + "号选手");
                        isClicked.set(finalI, false);
                    }
                }
            });
        }
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ShowIdentitiesActivity.this,SetupActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
            }
        });
    }
}
