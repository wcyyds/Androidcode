package com.example.sqlitetest_03211093;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name, psw, phone;
    Button add, del, change, seeAll;
    TextView content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        psw = findViewById(R.id.psw);
        add = findViewById(R.id.add);
        del = findViewById(R.id.del);
        change = findViewById(R.id.change);
        seeAll = findViewById(R.id.seeall);
        content = findViewById(R.id.content);
        phone = findViewById(R.id.phone);
        final DB db = new DB(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String p = psw.getText().toString();
                String m = phone.getText().toString();
                if (db.add(n, p, m)) {
                    Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String p = psw.getText().toString();
                String m = phone.getText().toString();
                if (db.del(n)) {
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String p = psw.getText().toString();
                String m = phone.getText().toString();
                if (db.change(n, p, m)) {
                    Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.setText("");
                ArrayList a = db.getall();
                for (Object b :
                        a) {
                    String n = content.getText().toString();
                    String str = n + ((User) b).toString() + "\n";
                    content.setText(str);
                }
            }
        });
    }

}