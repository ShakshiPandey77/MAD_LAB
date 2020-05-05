package com.example.graphical;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button back = findViewById(R.id.button2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();
        String shape = (i.getStringExtra("Shape"));
        String color = (i.getStringExtra("Color"));
        String filled = (i.getStringExtra("Filled"));

        Bitmap bg = Bitmap.createBitmap(720,1280,Bitmap.Config.ARGB_8888);

        ImageView img = (ImageView)findViewById(R.id.imageView);
        img.setBackgroundDrawable(new BitmapDrawable(bg));

        Canvas canvas = new Canvas(bg);
        Paint paint = new Paint();

        if (color.equals("RED")){
            paint.setColor(Color.RED);
        }
        else if (color.equals("BLUE")){
            paint.setColor(Color.BLUE);
        }
        else if (color.equals("BLACK")){
            paint.setColor(Color.BLACK);
        }

        if (filled.equals("false")){
            paint.setStyle(Paint.Style.STROKE);
        }

        paint.setTextSize(50);
        assert shape != null;
        canvas.drawText(shape,300,200, paint);


        if (shape.equals("Rectangle")){
            canvas.drawRect(150, 250, 400, 500, paint);
        }
        else if (shape.equals("Circle")){
            canvas.drawCircle(200, 350, 150, paint);
        }
        else if (shape.equals("Oval")){
            canvas.drawOval(150, 300, 400, 500, paint);
        }
        else if (shape.equals("Triangle")){
            if (filled.equals("true")){
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
            }
            else{
                paint.setStyle(Paint.Style.STROKE);

            }
            Point a = new Point(400, 300);
            Point b = new Point(300, 600);
            Point c = new Point(500, 600);

            Path path = new Path();
            path.moveTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.moveTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.moveTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();

            canvas.drawPath(path, paint);
        }

    }
}
