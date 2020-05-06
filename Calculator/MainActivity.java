package com.example.calculator;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TextView t;
    EditText e1,e2;
    Button add,sub,mul,div,write;
    private String filename = "myfile.txt";
    private String filepath = "MyFileStorage";
    File myFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.textView);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        write = (Button) findViewById(R.id.write);

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            write.setEnabled(false);
        }else{
            myFile = new File(getExternalFilesDir(filepath), filename);
        }

        write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String message=e1.getText().toString();
                try {

                    FileOutputStream fout=new FileOutputStream(myFile);
                    fout.write(message.getBytes());
                    fout.close();
                    Toast.makeText(getBaseContext(),"Data Written in SDCARD",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                int n1 = Integer.parseInt(e1.getText().toString());
                int n2 = Integer.parseInt(e2.getText().toString());
                int res = n1+n2;
                t.setText(Integer.toString(res));
                write.performClick();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                int n1 = Integer.parseInt(e1.getText().toString());
                int n2 = Integer.parseInt(e2.getText().toString());
                int res = n1-n2;
                t.setText(Integer.toString(res));
                write.performClick();
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                int n1 = Integer.parseInt(e1.getText().toString());
                int n2 = Integer.parseInt(e2.getText().toString());
                int res = n1 * n2;
                t.setText(Integer.toString(res));
                write.performClick();
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                int n1 = Integer.parseInt(e1.getText().toString());
                int n2 = Integer.parseInt(e2.getText().toString());
                float res = n1/n2;
                t.setText(Float.toString(res));
                write.performClick();
            }
        });
    }

    private boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
