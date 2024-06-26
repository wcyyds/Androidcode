package com.example.calculator_03211093;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btl, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btjia,
            btjian, btdian, btcheng, btchu, btdengyu;
    private EditText et_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btl = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        bt7 = (Button) findViewById(R.id.bt7);
        bt8 = (Button) findViewById(R.id.bt8);
        bt9 = (Button) findViewById(R.id.bt9);
        bt0 = (Button) findViewById(R.id.bt0);
        btjia = (Button) findViewById(R.id.btjia);
        btjian = (Button) findViewById(R.id.btjian);
        btdian = (Button) findViewById(R.id.btdian);
        btcheng = (Button) findViewById(R.id.btcheng);
        btchu = (Button) findViewById(R.id.btchu);
        btdengyu = (Button) findViewById(R.id.btdengyu);
        et_all = (EditText) findViewById(R.id.et_al1);
        btl.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt0.setOnClickListener(this);
        btjia.setOnClickListener(this);
        btjian.setOnClickListener(this);
        btdian.setOnClickListener(this);
        btcheng.setOnClickListener(this);
        btchu.setOnClickListener(this);
        btdengyu.setOnClickListener(this);
        et_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt1){
            et_all.append("1");
        }else if(v.getId() == R.id.bt2){
            et_all.append("2");
        }else if(v.getId() == R.id.bt3){
            et_all.append("3");
        }else if(v.getId() == R.id.bt4){
            et_all.append("4");
        }else if(v.getId() == R.id.bt5){
            et_all.append("5");
        }else if(v.getId() == R.id.bt6){
            et_all.append("6");
        }else if(v.getId() == R.id.bt7){
            et_all.append("7");
        }else if(v.getId() == R.id.bt8){
            et_all.append("8");
        }else if(v.getId() == R.id.bt9){
            et_all.append("9");
        }else if(v.getId() == R.id.bt0){
            et_all.append("0");
        }else if(v.getId() == R.id.btjia){
            et_all.append("+");
        }else if(v.getId() == R.id.btjian){
            et_all.append("-");
        }else if(v.getId() == R.id.btdian){
            et_all.append(".");
        }else if(v.getId() == R.id.btcheng){
            et_all.append("*");
        }else if(v.getId() == R.id.btchu){
            et_all.append("/");
        }else if(v.getId() == R.id.btdengyu){
            String all=et_all.getText().toString();

            if (all.indexOf("+") != -1) {
                String a[] = all.split("\\+");
                double shu1 = Double.valueOf(a[0]);
                double shu2 = Double.valueOf(a[1]);
                System.out.println(a[0]);
                System.out.println(a[1]);
                double alljia = shu1 + shu2;
                et_all.setText(alljia + "");
            }
            if (all.indexOf("-") != -1) {
                String a[] = all.split("\\-");
                int shu1 = Integer.valueOf(a[0]);
                int shu2 = Integer.valueOf(a[1]);
                System.out.println(a[0]);
                System.out.println(a[1]);
                int alljia = shu1 + shu2;
                et_all.setText(alljia + "");
            }
            if (all.indexOf("×") != -1) {
                String a[] = all.split("×");
                int shu1 = Integer.valueOf(a[0]);
                int shu2 = Integer.valueOf(a[1]);
                System.out.println(a[0]);
                System.out.println(a[1]);
                int alljia = shu1 * shu2;
                et_all.setText(alljia + "");
            }
            if (all.indexOf("÷") != -1) {
                String a[] = all.split("\\÷");
                double shu1 = Double.valueOf(a[0]);
                double shu2 = Double.valueOf(a[1]);
                System.out.println(a[0]);
                System.out.println(a[1]);
                double alljia = shu1 / shu2;
                et_all.setText(alljia + "");
            }
        }
    }
}