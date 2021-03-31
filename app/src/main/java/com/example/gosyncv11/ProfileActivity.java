package com.example.gosyncv11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class
ProfileActivity extends AppCompatActivity {
    TextView email, phn, name,user_name;
    TextView startup_name, contact_no, startup_email, motto, about;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button change_name, change_phn_no, change_email, change_username;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        about = (TextView) findViewById(R.id.textView20);
        motto = (TextView) findViewById(R.id.textView17);
        startup_email = (TextView) findViewById(R.id.textView15);
        contact_no = (TextView) findViewById(R.id.textView22);
        startup_name = (TextView) findViewById(R.id.textView11);
        change_name = (Button) findViewById(R.id.button6);
        change_email = (Button) findViewById(R.id.button7);
        change_phn_no = (Button) findViewById(R.id.button8);
        change_username = (Button) findViewById(R.id.button4);
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = user.getUid();
        reference = database.getReference().child("users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String pro_name= snapshot.child(user_id).child("startup_name").getValue().toString();
                    String pro_about= snapshot.child(user_id).child("about").getValue().toString();
                    String pro_contact_no= snapshot.child(user_id).child("contact_no").getValue().toString();
                    String pro_motto= snapshot.child(user_id).child("motto").getValue().toString();
                    startup_name.setText(pro_name);
                    startup_email.setText(user.getEmail());
                    contact_no.setText(pro_contact_no);
                    about.setText(pro_about);
                    motto.setText(pro_motto);
                    //Toast.makeText(ProfileActivity.this, pro_name+" "+ pro_phn_no + " "+ pro_username, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,  ChangeName.class);
                startActivity(intent);
            }
        });
        change_email.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileActivity.this,  ChangeEmail.class);
                startActivity(intent);
            }
        });
        change_phn_no.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePhn_no.class);
                startActivity(intent);
            }
        });
        change_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangeUsername.class);
                startActivity(intent);
            }
        });

    }
}