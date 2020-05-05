package com.example.graphical;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner shape = findViewById(R.id.spinner);
        final Spinner color = findViewById(R.id.spinner2);

//        String[] shapes = new String[] {"Circle", "Rectangle", "Oval", "Triangle"};
//        String[] colors = new String[] {"RED", "BLUE", "BLACK"};

        List<String> shapes = new ArrayList<>();
        shapes.add(0, "Choose Shape");
        shapes.add("Circle");
        shapes.add("Rectangle");
        shapes.add("Oval");
        shapes.add("Triangle");

        List<String> colors = new ArrayList<>();
        colors.add(0, "Choose Color");
        colors.add("RED");
        colors.add("BLUE");
        colors.add("BLACK");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, shapes);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, colors);

        shape.setAdapter(arrayAdapter);
        color.setAdapter(arrayAdapter2);


        final RadioButton filled = findViewById(R.id.radioButton);

        Button confirm = (Button) findViewById(R.id.button);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("Shape", String.valueOf(shape.getSelectedItem()));
                i.putExtra("Color", String.valueOf(color.getSelectedItem()));
                i.putExtra("Filled", String.valueOf(filled.isChecked()));

                startActivity(i);

            }
        });





    }
}
