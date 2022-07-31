package com.example.myjisuanqi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private TextView text_out;
    private StringBuffer sb=new StringBuffer();
    private StringBuffer answer =new StringBuffer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        //定义控件的按钮
        textView = (TextView) findViewById(R.id.text_view);
        text_out = (TextView) findViewById(R.id.text_out);

        Button one = (Button) findViewById(R.id.one);
        one.setOnClickListener(this);
        Button two = (Button) findViewById(R.id.two);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.three);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.four);
        four.setOnClickListener(this);
        Button five = (Button) findViewById(R.id.five);
        five.setOnClickListener(this);
        Button six = (Button) findViewById(R.id.six);
        six.setOnClickListener(this);
        Button seven = (Button) findViewById(R.id.seven);
        seven.setOnClickListener(this);
        Button eight = (Button) findViewById(R.id.eight);
        eight.setOnClickListener(this);
        Button nine = (Button) findViewById(R.id.nine);
        nine.setOnClickListener(this);
        Button zero = (Button) findViewById(R.id.zero);
        zero.setOnClickListener(this);
        Button cheng = (Button) findViewById(R.id.cheng);
        cheng.setOnClickListener(this);
        Button chu = (Button) findViewById(R.id.chu);
        chu.setOnClickListener(this);
        Button jia = (Button) findViewById(R.id.jia);
        jia.setOnClickListener(this);
        Button jian = (Button) findViewById(R.id.jian);
        jian.setOnClickListener(this);
        Button zuokuo = (Button) findViewById(R.id.zuokuo);
        zuokuo.setOnClickListener(this);
        Button youkuo = (Button) findViewById(R.id.youkuo);
        youkuo.setOnClickListener(this);
        Button del = (Button) findViewById(R.id.del);
        del.setOnClickListener(this);
        Button deng = (Button) findViewById(R.id.deng);
        deng.setOnClickListener(this);
        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(this);
        Button dian = (Button) findViewById(R.id.dian);
        dian.setOnClickListener(this);

    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zero:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*0");
                } else {
                    sb.append("0");
                }
                textView.setText(sb.toString());
                break;
            case R.id.one:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*1");
                } else {
                    sb.append("1");
                }
                textView.setText(sb.toString());
                break;
            case R.id.two:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*2");
                } else {
                    sb.append("2");
                }
                textView.setText(sb.toString());
                break;
            case R.id.three:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*3");
                } else {
                    sb.append("3");
                }
                textView.setText(sb.toString());
                break;
            case R.id.four:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*4");
                } else {
                    sb.append("4");
                }
                textView.setText(sb.toString());
                break;
            case R.id.five:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*5");
                } else {
                    sb.append("5");
                }
                textView.setText(sb.toString());
                break;
            case R.id.six:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*6");
                } else {
                    sb.append("6");
                }
                textView.setText(sb.toString());
                break;
            case R.id.seven:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*7");
                } else {
                    sb.append("7");
                }
                textView.setText(sb.toString());
                break;
            case R.id.eight:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*8");
                } else {
                    sb.append("8");
                }
                textView.setText(sb.toString());
                break;
            case R.id.nine:
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*9");
                } else {
                    sb.append("9");
                }
                textView.setText(sb.toString());
                break;
            case R.id.jia:
                Log.d("Main","加法错误第一");
                if(sb.length() == 0){
                    Log.d("Main","加法错误1");
                    sb.append("0+");
                }else
                if(sb.charAt(sb.length()-1)=='.'){
                    Log.d("Main","加法错误2");
                    sb.append("0+");
                }else
                if(sb.charAt(sb.length()-1) == '*' ||
                        sb.charAt(sb.length()-1) == '/' ||
                        sb.charAt(sb.length()-1) == '-' ||
                        sb.charAt(sb.length()-1) == '+'){
                    Log.d("Main","加法错误3");
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append('+');
                }else{
                    Log.d("Main","加法错误4");
                    sb.append("+");
                }
                Log.d("Main","加法错误0");
                textView.setText(sb.toString());
                break;
            case R.id.jian:
                if(sb.length() == 0){
                    sb.append('-');
                }else
                if(sb.charAt(sb.length()-1)=='.'){
                    sb.append("0-");
                }else
                if(sb.charAt(sb.length()-1) == '*' ||
                        sb.charAt(sb.length()-1) == '/' ||
                        sb.charAt(sb.length()-1) == '+' ||
                        sb.charAt(sb.length()-1) == '-'){
                    sb.append("(-");
                }else{
                    sb.append("-");
                }
                textView.setText(sb.toString());
                break;
            case R.id.cheng:
                if(sb.length() == 0){
                    sb.append("0*");
                        //Toast.makeText(MainActivity.this, "你第一个输入*干什么", Toast.LENGTH_SHORT).show();
                }else
                if(sb.charAt(sb.length()-1) == '*' ||
                        sb.charAt(sb.length()-1) == '/' ||
                        sb.charAt(sb.length()-1) == '+' ||
                        sb.charAt(sb.length()-1) == '-'){
                    sb.deleteCharAt(sb.length()-1);
                    sb.append("*");
                }else
                if(sb.charAt(sb.length()-1)=='.'){
                    Log.d("Main","加法错误2");
                    sb.append("0*");
                }else{
                    sb.append("*");
                }
                textView.setText(sb.toString());
                break;
            case R.id.chu:
                if(sb.length() == 0){
                    sb.append("0/");
                    //Toast.makeText(MainActivity.this, "你第一个输入/干什么", Toast.LENGTH_SHORT).show();
                }else
                if(sb.charAt(sb.length()-1) == '*' ||
                        sb.charAt(sb.length()-1) == '/' ||
                        sb.charAt(sb.length()-1) == '+' ||
                        sb.charAt(sb.length()-1) == '-'){
                    sb.deleteCharAt(sb.length()-1);
                    sb.append("/");
                }else
                if(sb.charAt(sb.length()-1)=='.'){
                    Log.d("Main","加法错误2");
                    sb.append("0/");
                }else{
                    sb.append("/");
                }
                textView.setText(sb.toString());
                break;
            case R.id.zuokuo:
                if ((sb.length() > 0 && (sb.charAt(sb.length() - 1) >= '0' && sb.charAt(sb.length() - 1) <= '9'))) {
                    sb.append("*(");
                } else if(sb.length() > 0 && sb.charAt(sb.length() - 1) == ')'){
                    sb.append("*(");
                }else{
                    sb.append("(");
                }
                textView.setText(sb.toString());
                break;
            case R.id.youkuo:
                if(sb.charAt(sb.length()-1) == '('){
                    Toast.makeText(MainActivity.this,"你的左括号还没有任何内容", Toast.LENGTH_SHORT).show();
                }else{
                    sb.append(")");
                }
                textView.setText(sb.toString());
                break;
            case R.id.dian:
                boolean diandewenti = false;
                for(int i = sb.length()-1; i > 0; i--){
                    if(sb.charAt(i) == '*' || sb.charAt(i) == '/' ||
                            sb.charAt(i) == '+' || sb.charAt(i) == '-'){
                        break;
                    }
                    if(sb.charAt(i) == '.'){
                        diandewenti = true;
                        break;
                    }
                }
                if (sb.length() == 0 ||sb.charAt(sb.length()-1) == '+'
                        ||sb.charAt(sb.length()-1) == '-' ||sb.charAt(sb.length()-1) == '/'
                        ||sb.charAt(sb.length()-1) == '*' ) {
                    sb.append("0.");
                }else if(diandewenti){
                    Toast.makeText(MainActivity.this,"你就不能少输入一个点？", Toast.LENGTH_SHORT).show();
                }else if(sb.charAt(sb.length() - 1) == ')') {
                    sb.append("*0.");
                }else {
                    sb.append(".");
                }
                textView.setText(sb.toString());
                break;
            case R.id.clear:
                sb=sb.delete(0,sb.length());
                answer = answer.delete(0,answer.length());
                textView.setText(sb.toString());
                text_out.setText(answer.toString());
                break;
            case R.id.del:
                if(sb.length() == 0){
                    Log.d("Main","这是第一个没有的东西");
                    answer = answer.delete(0,answer.length());
                    text_out.setText(answer.toString());
                    Toast.makeText(MainActivity.this, "再点我闪退了",
                            Toast.LENGTH_SHORT).show();
                }else if(sb.length() == 1){
                    answer = answer.delete(0,answer.length());
                    text_out.setText(answer.toString());
                    sb=sb.deleteCharAt(sb.length()-1);
                }else{
                    Log.d("Main","这是第三个没有的东西");
                    sb=sb.deleteCharAt(sb.length()-1);
                }
                Log.d("Main","没有的东西");
                textView.setText(sb.toString());
                break;
            case R.id.deng:
                int zuonumber = 0;
                int younumber = 0;
                for(int i = 0; i < sb.length(); i++){
                    if(sb.charAt(i) == '('){
                        zuonumber++;
                    }
                    if(sb.charAt(i) == ')'){
                        younumber++;
                    }
                }
                if(sb.charAt(sb.length()-1) == '+' || sb.charAt(sb.length()-1) == '-'
                        ||sb.charAt(sb.length()-1) == '*' ||sb.charAt(sb.length()-1) == '/'){
                    sb=sb.deleteCharAt(sb.length()-1);
                    textView.setText(sb.toString());
                }
                while (zuonumber > younumber){
                    sb.append(")");
                    younumber++;
                }
                textView.setText(sb.toString());
                if(zuonumber == younumber){
                    try{
                        answer = answer.delete(0,answer.length());
                        String abc = jisuan.end(sb);
                        answer.append(abc);
                        //这个就是把计算到的答案放到另外一个数组当中，然后
                        //把sb清零，把计算到的答案给sb这样可以继续使用del

                        //要删除的是小数的遇到整数就不可以

                        boolean diandewenti1 = false;
                        for(int i = answer.length()-1; i > 0; i--){
                            Log.d("main","从这里进入有没有小数点");
                            if(answer.charAt(i) == '.'){
                                diandewenti1 = true;
                                break;
                            }
                        }
                        if(diandewenti1){
                            Log.d("main","有没有进去删除0的");
                            for(int i = answer.length()-1;i > 0;i--) {
                                if(answer.charAt(i) == '.'){
                                    answer.deleteCharAt(i);
                                    break;
                                }else if (answer.charAt(i) == '0') {
                                    answer.deleteCharAt(i);
                                }else{
                                    break;
                                }
                            }
                        }
                        Log.d("main", "这里是输出");
                        text_out.setText("="+answer);

                    }catch(Exception e){
                        text_out.setText("Error!!!计算有问题");
                    }

                }else{
                    Toast.makeText(MainActivity.this, "左右括号不相等",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}