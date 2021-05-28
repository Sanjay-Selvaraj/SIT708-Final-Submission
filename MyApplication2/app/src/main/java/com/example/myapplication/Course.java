package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Course extends AppCompatActivity {
    RecyclerView featuredRecycler;
    CardView plant,bird,animal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_course);

        plant = findViewById(R.id.one);
        bird = findViewById(R.id.bird);
        animal = findViewById(R.id.animal);

        plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this, courselist.class);

                intent.putExtra("name", "Plant");
                intent.putExtra("id", "1");

                startActivity(intent);
            }

        });
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this,courselist.class);

                intent.putExtra("name","Animal");
                intent.putExtra("id","101");

                startActivity(intent);

            }
        });
        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course.this,courselist.class);

                intent.putExtra("name","Bird");
                intent.putExtra("id","201");

                startActivity(intent);
            }
        });
    }

    }
