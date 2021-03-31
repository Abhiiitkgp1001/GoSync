package com.example.gosyncv11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeUsername extends AppCompatActivity {
    EditText new_user_name;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button SaveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        new_user_name = (EditText) findViewById(R.id.editTextTextPersonName3);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users");
        SaveData = (Button) findViewById(R.id.savedata);
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        SaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(user_id).child("startup_name").setValue(new_user_name
                        .getText().toString().trim());
                Toast.makeText(ChangeUsername.this,"Data Updated",Toast.LENGTH_SHORT);
                Intent intent = new Intent(ChangeUsername.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}