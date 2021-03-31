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

public class ChangePhn_no extends AppCompatActivity {
    EditText new_pnh_no;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button SaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phn_no);
        new_pnh_no = (EditText) findViewById(R.id.editTextNumber3);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users");
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        SaveData = (Button) findViewById(R.id.savedata);
        SaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(user_id).child("contact_no").setValue(Integer.parseInt(new_pnh_no
                        .getText().toString().trim()));
                Toast.makeText(ChangePhn_no.this,"Data Updated",Toast.LENGTH_SHORT);
                Intent intent = new Intent(ChangePhn_no.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}