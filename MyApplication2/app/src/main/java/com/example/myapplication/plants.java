package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class plants extends AppCompatActivity {
  TextView head, sdecription, t1,mdescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);

        head = findViewById(R.id.ahead);
        sdecription = findViewById(R.id.sdesc);
        t1 = findViewById(R.id.t1);
        mdescription = findViewById(R.id.mdesc);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String id = intent.getStringExtra("id");
        System.out.println(name + id);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Course").child(name).child(id);


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             String sname = snapshot.child("Name").getValue().toString();
             String desc = snapshot.child("Desc").getValue().toString();
             String aname = snapshot.child("Aname").getValue().toString();
             head.setText(name);
             sdecription.setText("Learn about "+sname);
             t1.setText(aname);
             mdescription.setText(desc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}