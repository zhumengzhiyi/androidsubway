package com.example.subway2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class mainfirst extends AppCompatActivity implements View.OnClickListener {

    private ImageView btn_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfirst);
        findViewById(R.id.bt_act_qishi).setOnClickListener(this);
        findViewById(R.id.bt_act_luxian).setOnClickListener(this);
        findViewById(R.id.bt_act_chezhan).setOnClickListener(this);
        initView();
    }

    private void initView() {
        btn_return=findViewById(R.id.btn_return);
        btn_return.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_return:
                finish();
                break;
            case R.id.bt_act_qishi:
                startActivity(new Intent(this,chezhanqishi.class));
                break;
            case R.id.bt_act_luxian:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.bt_act_chezhan:
                startActivity(new Intent(this,There.class));
                break;
        }

    }
}