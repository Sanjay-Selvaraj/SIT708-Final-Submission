package com.example.ar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class listing extends AppCompatActivity {
  TextView head, sdecription, t1,mdescription;
  Button button;

    //To get and set the values from the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);

        head = findViewById(R.id.ahead);
        sdecription = findViewById(R.id.sdesc);
        t1 = findViewById(R.id.t1);
        mdescription = findViewById(R.id.mdesc);
        button = findViewById(R.id.arbutton);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String id = intent.getStringExtra("id");
        System.out.println(name + id);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Course").child(name).child(id);
        Intent intents = new Intent(this,augmented.class);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            //For reffering database
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             String sname = snapshot.child("Name").getValue().toString();
             String desc = snapshot.child("Desc").getValue().toString();
             String aname = snapshot.child("Aname").getValue().toString();
             String model_url = snapshot.child("Modelurl").getValue().toString();
             head.setText(name);
             sdecription.setText("Learn about "+sname);
             t1.setText(aname);
             mdescription.setText(desc);
             intents.putExtra("model_url",model_url);
             button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     startActivity(intents);
                 }
             });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}