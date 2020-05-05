package com.example.myapplication3;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    Button write;
    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myFile;
    TextView t1,t2,t3;
    String name,reg,dept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);
        write = (Button)findViewById((R.id.button2));

        Intent i=getIntent();
        name=i.getStringExtra("name_key");
        reg=i.getStringExtra("reg_key");
        dept=i.getStringExtra("dept_key");

        t1.setText(name);
        t2.setText(reg);
        t3.setText(dept);

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            write.setEnabled(false);
        }else{
                myFile = new File(getExternalFilesDir(filepath), filename);
            }
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = t1.getText().toString();
                String msg1 = t2.getText().toString();
                String msg2 = t3.getText().toString();
                try{
                    FileOutputStream fo = new FileOutputStream(myFile);
                    fo.write(msg.getBytes());
                    fo.write(msg1.getBytes());
                    fo.write(msg2.getBytes());
                    fo.close();
                    Toast.makeText(getBaseContext(), "Data wrote to SD Card", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private static boolean isExternalStorageReadOnly(){
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable(){
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}