package com.example.ar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    TextInputEditText fullname,email,phoneNo,password,username;
    Button update;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //To get and set the values from the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fullname = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        username = findViewById(R.id.username_);
        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {

            //Editing user details

            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                String name = fullname.getText().toString();
                String user_name = username.getText().toString();
                String Email = email.getText().toString();
                String PhoneNo = phoneNo.getText().toString();
                String Password = password.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name,user_name,Email,PhoneNo,Password);

                reference.child(user_name).setValue(helperClass);
                Intent intent = new Intent(getApplicationContext(),Course.class);
                startActivity(intent);
            }
        });
        showAllUserData();

    }

    // To retrieve the data from the database

    private void showAllUserData() {
        Intent intent = getIntent();
        String full_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_name = intent.getStringExtra("username");
        String user_password = intent.getStringExtra("password");


        fullname.setText(full_name);
        email.setText(user_email);
        phoneNo.setText(user_phoneNo);
        password.setText(user_password);
        username.setText(user_name);
    }
}