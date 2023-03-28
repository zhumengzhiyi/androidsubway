package com.example.subway2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class chezhanqishi extends AppCompatActivity implements View.OnClickListener {
    private ImageView btn_return;//返回

    List<Bean1> list = new ArrayList<Bean1>();
    private EditText et_qishi;
    private EditText et_zhong;
    private TextView tv_qishi;
    private TextView tv_lxlook;
    private TextView tv_qishilook;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            String ul = "";
            //super.handleMessage(msg);
            if(msg.what==0){
                int count= (Integer) msg.obj;
                tv_lxlook.setText("数据库数量为："+count);
            }
            if(msg.what==1){


                List<Bean1> list = (List<Bean1>) msg.obj;
                int i;
                for(i=0;i<list.size();i++)
                {
                    ul+="路线："+list.get(i).getNol()+"    ,车站名："+list.get(i).getName()+"\n";
                }

                tv_lxlook.setText(ul);
            }
            if(msg.what==2){


                List<Bean1> list = (List<Bean1>) msg.obj;
                int i;
                for(i=0;i<list.size();i++)
                {
                    if(list.get(i).getNol()==null){
                        ul+="      换乘点："+list.get(i).getName()+"\n";
                    }else{
                        ul+="      路线："+list.get(i).getNol()+"    ,车站名："+list.get(i).getName()+"\n";
                    }

                }

                tv_qishilook.setText(ul);
            }
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chezhanqishi);
        et_qishi=findViewById(R.id.et_qishi);
        et_zhong=findViewById(R.id.et_zhong);
        tv_lxlook=findViewById(R.id.tv_lxlook);
        tv_qishilook=findViewById(R.id.tv_qishilook);
        findViewById(R.id.bt_qishi).setOnClickListener(this);

        initView();
    }

    private void initView() {
        btn_return=findViewById(R.id.btn_return);
        btn_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        List<Bean1> list = new ArrayList<Bean1>();
        switch(v.getId()){
            case R.id.bt_qishi:
                String etqishi=et_qishi.getText().toString();
                System.out.println(etqishi);
                String etzhong=et_zhong.getText().toString();
                System.out.println(etzhong);
                QiShi(etqishi,etzhong);
                break;
            case R.id.btn_return:
                finish();
                break;
        }

    }
    /**
     * 点击空白区域隐藏键盘.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (chezhanqishi.this.getCurrentFocus() != null) {
                if (chezhanqishi.this.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(chezhanqishi.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }


    //执行查询数量的方法
    private void doQueryCount(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count=MySqlHelp.getUserSize();
                Message message=Message.obtain();
                message.what=0;
                message.obj=count;
                //向线程发送数据
                handler.sendMessage(message);
            }
        }).start();
    }
    //查询的对象集合
    public  void LuX(String nol1){
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = new Dao1().selectByNol(nol1);
                if(list.size()>0){
                    System.out.println("非空");

                    for (Bean1 u : list) {

                        Log.d("ning", u.toString());

                    }
                }
                Message message=Message.obtain();
                message.what=1;
                message.obj=list;
                handler.sendMessage(message);
            }
        }).start();
    }
    public void QiShi(String qishi,String zhong){
        new Thread(new Runnable() {
            @Override
            public void run() {
                list=new action().HuanZui(qishi,zhong);
                if(list.size()>0){
                    System.out.println("非空");

                    for (Bean1 u : list) {

                        Log.d("ning", u.toString());

                    }
                }
                Message message=Message.obtain();
                message.what=2;
                message.obj=list;
                handler.sendMessage(message);
            }
        }).start();

    }
    /**
     * 隐藏键盘
     * 弹窗弹出的时候把键盘隐藏掉
     */

    protected void hideInputKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    /**
     * 弹起键盘
     */
    protected void showInputKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);
    }

}