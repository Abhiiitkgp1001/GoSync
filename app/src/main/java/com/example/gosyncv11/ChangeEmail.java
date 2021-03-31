package com.example.gosyncv11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeEmail extends AppCompatActivity {
EditText old_email, new_email, reenter_new_email;
Button SaveData;
FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        old_email = (EditText) findViewById(R.id.editTextTextPersonName);
        new_email = (EditText) findViewById(R.id.editTextTextPersonName2);
        reenter_new_email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        SaveData = (Button) findViewById(R.id.savedata);
        user = FirebaseAuth.getInstance().getCurrentUser();
        String user_uid= user.getUid();
        SaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(old_email.getText().toString().trim().equals(user.getEmail())){
                    if(new_email.getText().toString().trim().equals(reenter_new_email.getText().toString().trim())){
                        user.updateEmail(new_email.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ChangeEmail.this,"Email address updated. Login with new Email",Toast.LENGTH_LONG);
                                    FirebaseAuth.getInstance().signOut();
                                    startActivity(new Intent(ChangeEmail.this,login.class));
                                }
                                else{
                                    Toast.makeText(ChangeEmail.this,"Failed to update",Toast.LENGTH_LONG);
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(ChangeEmail.this,"Email not matched",Toast.LENGTH_LONG);
                    }
                }
                else{
                    Toast.makeText(ChangeEmail.this,"Email not correct",Toast.LENGTH_LONG);
                }
            }
        });
    }
}