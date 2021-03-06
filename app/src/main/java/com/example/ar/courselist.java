package com.example.ar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class courselist extends AppCompatActivity implements Adapter.oncardlistner{
    ArrayList<String> titles,ids;
    ArrayList<String> images;
    Adapter adapter;
    RecyclerView recyclerView;
    TextView heading;
    String name;

    //To get and set the values from the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);
        recyclerView = findViewById(R.id.recycle);
        heading = findViewById(R.id.heading);
        titles = new ArrayList<>();
        images = new ArrayList<>();
        ids = new ArrayList<String>();
        Intent intents = getIntent();
        name = intents.getStringExtra("name");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Course").child(name);


        heading.setText(name);
        GridLayoutManager Gm = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(Gm);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            //For referring database
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot sname : snapshot.getChildren()){
                   System.out.println(Integer.parseInt(sname.getKey()));
                   images.add(sname.child("image").getValue().toString());
                   titles.add(sname.child("Name").getValue().toString());
                   ids.add(sname.getKey().toString());
               }
                adapter =new Adapter(courselist.this,titles,images,ids,courselist.this);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }
    //For changing interfaces dynamically.
    @Override
    public void oncardclick(int position) {
        titles.get(position);
        System.out.println(ids.get(position));
        Intent intent = new Intent(this, listing.class);
        intent.putExtra("name",name);
        intent.putExtra("id", (ids.get(position)));
       startActivity(intent);
    }
}