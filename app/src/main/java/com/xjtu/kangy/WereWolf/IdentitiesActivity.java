package com.xjtu.kangy.WereWolf;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xjtu.kangy.WereWolf.utils.Data;

import java.util.ArrayList;

/**
 * Created by kangy on 2016/10/20.
 */
public class IdentitiesActivity extends BasicActivity {

    private final String TAG = "IdentitiesActivity";

    ArrayList<String> identities, identitiesToshow;
    TextView tvPlayerNum, tvIdentity;
    ImageView ivIdentity;
    Button btnNext;
    int playerNum = 1;
    int random = 0;
    boolean ivClicked = false;
    String identity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identities);
        initViews();
        final Intent intent = getIntent();
        identities = intent.getStringArrayListExtra("identities");
        identitiesToshow = new ArrayList<>();
        tvPlayerNum.setText("" + playerNum);
        random = (int) (Math.random() * identities.size());
        identity = identities.get(random);
        ivIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ivClicked) {
                    showIdentity(identity);
                    ivClicked = true;
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ivClicked) {
                    Dialog alertDialog = new AlertDialog.Builder(IdentitiesActivity.this).
                            setTitle("警告").
                            setMessage("未查看身份").
                            setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).
                            create();
                    alertDialog.show();
                } else {
                    identities.remove(random);
                    identitiesToshow.add(identity);
                    if (identities.size() != 0) {
                        playerNum++;
                        tvPlayerNum.setText("" + playerNum);
                        random = (int) (Math.random() * identities.size());
                        identity = identities.get(random);
                        ivIdentity.setImageResource(R.drawable.idetity_cover);
                        tvIdentity.setText("你猜");
                        ivClicked = false;
                    } else {
                        Intent intent1 = new Intent(IdentitiesActivity.this, ShowIdentitiesActivity.class);
                        intent1.putExtra("identitiesToShow", identitiesToshow);
                        startActivity(intent1);
                    }
                }
            }
        });

    }

    @Override
    protected void initViews() {
        tvPlayerNum = (TextView) findViewById(R.id.TVPlayerNumber);
        tvIdentity = (TextView) findViewById(R.id.TVidentity);
        ivIdentity = (ImageView) findViewById(R.id.IVidentity);
        btnNext = (Button) findViewById(R.id.btnNext);
    }

//    private void showIdentity(String identity) {
//        switch (identity) {
//            case "god1":
//                ivIdentity.setImageResource(R.drawable.god1);
//                tvIdentity.setText("预言家");
//                break;
//            case "god2":
//                ivIdentity.setImageResource(R.drawable.god2);
//                tvIdentity.setText("女巫");
//                break;
//            case "god3":
//                ivIdentity.setImageResource(R.drawable.god3);
//                tvIdentity.setText("猎人");
//                break;
//            case "god4":
//                ivIdentity.setImageResource(R.drawable.god4);
//                tvIdentity.setText("守卫");
//                break;
//            case "god5":
//                ivIdentity.setImageResource(R.drawable.god5);
//                tvIdentity.setText("长老");
//                break;
//            case "god6":
//                ivIdentity.setImageResource(R.drawable.god6);
//                tvIdentity.setText("丘比特");
//                break;
//            case "god7":
//                ivIdentity.setImageResource(R.drawable.god7);
//                tvIdentity.setText("白痴");
//                break;
//            case "god8":
//                ivIdentity.setImageResource(R.drawable.god8);
//                tvIdentity.setText("熊");
//                break;
//            case "god9":
//                ivIdentity.setImageResource(R.drawable.god9);
//                tvIdentity.setText("野孩子");
//                break;
//            case "wolfgod1":
//                ivIdentity.setImageResource(R.drawable.wolfgod1);
//                tvIdentity.setText("狼王");
//                break;
//            case "wolfgod2":
//                ivIdentity.setImageResource(R.drawable.wolfgod2);
//                tvIdentity.setText("白狼王");
//                break;
//            case "wolfgod3":
//                ivIdentity.setImageResource(R.drawable.wolfgod3);
//                tvIdentity.setText("野狼");
//                break;
//            case "wolf":
//                ivIdentity.setImageResource(R.drawable.wolf);
//                tvIdentity.setText("狼人");
//                break;
//            case "villager":
//                ivIdentity.setImageResource(R.drawable.villager);
//                tvIdentity.setText("普通村民");
//                break;
//
//        }
//        Log.w(TAG,Data.getIdentitiesConvertToDrawable().size()+"");
//        Log.w(TAG,Data.getIdentitiesConvertToText().size()+"");
//        Log.w(TAG,"getIdentitiesConvertToText"+Data.getIdentitiesConvertToText().get(identity));
//        Log.w(TAG,"getIdentitiesConvertToDrawable"+Data.getIdentitiesConvertToDrawable().get(identity));
//    }

    private void showIdentity(String identity) {
        tvIdentity.setText(Data.getIdentitiesConvertToText().get(identity));
        ivIdentity.setImageResource(Data.getIdentitiesConvertToDrawable().get(identity));
    }
}
