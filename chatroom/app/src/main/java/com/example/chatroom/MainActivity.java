package com.example.chatroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button quit;
    private EditText et_name;
    private EditText et_ip;
    private EditText et_port;
    private TextView my_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取id,连接控件
        login = (Button) findViewById(R.id.login);
        quit = (Button) findViewById(R.id.quit);
        et_name = findViewById(R.id.et_name);
        et_ip = (EditText) findViewById(R.id.et_ip);
        et_port = (EditText) findViewById(R.id.et_port);
        //login点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取一个视图View对象里的字符串
                String name = et_name.getText().toString();
                //如果没输入名字
                if("".equals(name)){
                    Toast.makeText(MainActivity.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
                }else{
//                    Intent intent = new Intent(MainActivity.this,ChoosePicture.class);
//                    //向下一活动传递信息                                        intent.putExtra("name",et_name.getText().toString());
//                    intent.putExtra("ip",et_ip.getText().toString());
//                    intent.putExtra("port",et_port.getText().toString());
//                    try{
//                        startActivity(intent);
//                    }catch(Exception e){
//                        System.out.println("开启失败");
//                        finish();
//                    }
                    Intent intent = new Intent(MainActivity.this,chatroom.class);
                    //intent相当于传播的一种媒介，本行的意思是：使内部代码从MainActivity.this到ChatRoom.class进行转变，但只是设置了目标，还未开始执行
                    intent.putExtra("name",et_name.getText().toString());//这个意思相当于分类处理，在从Activity到ChatRoom，需要将数据送过去，那么就需要对即将传送的数据进行分类处理，使name，ip，port分别对应指定内容
                    intent.putExtra("ip",et_ip.toString());
                    intent.putExtra("port",et_port.toString());
                    startActivity(intent);//开始执行

                }
            }
        });
        //quit点击事件
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用AlertDialog显示一个退出提示框
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("关闭提示");
                dialog.setMessage("确定退出登录？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
            }
        });
    }
}
