package com.example.uiwidgettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;

    private ImageView imageView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView)findViewById(R.id.image_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                /*
                String inputText = editText.getText().toString();
                Toast.makeText(MainActivity.this,inputText,
                        Toast.LENGTH_LONG).show();
                        //这是点击button按钮然后toast显示的是edittext的内容
                 */
                /*
                imageView.setImageResource(R.drawable.img_2);
                //这是点击button，然后变成img_2图片
                 */
                /*
                if(progressBar.getVisibility() == View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility((View.GONE));
                }
                //点击按钮，然后进度条就是ProgressBar来回跳
                 */
                /*
                int progress = progressBar.getProgress();
                progress = progress + 10;
                progressBar.setProgress(progress);
                //这个就是总共有100点击一次加10然后最后加满
                 */
                /*
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.
                        this);
                dialog.setTitle("This is Dialog");
                dialog.setMessage("Some important.");
                dialog .setCancelable(false);
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    }
                });
                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                    }
                });
                dialog.show();
                //这个是先通过Alerdialog.Builder创建了一个alerdialog实例；positive乐观的自信的,
                //然后确定了标题，内容，能不能通过其他地方取消这个东西，然后是两个按钮；negetive消极的有害的
                 */

                ProgressDialog progressDialog = new ProgressDialog
                        (MainActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading......");
                progressDialog.setCancelable(true);
                progressDialog.show();

                break;
            default:
                break;
        }
    }
}