package com.example.gosyncv11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private Button button;
    private TextView signUp, forgot_password;
    private FirebaseAuth auth;
    private EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!= null){
            Intent intent=new Intent(login.this, NavigationActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.emailaddress);
        password = (EditText) findViewById(R.id.Password);
        button = (Button) findViewById(R.id.button);
        signUp = (TextView) findViewById(R.id.textView6);
        forgot_password = (TextView) findViewById(R.id.textView7);
        auth = FirebaseAuth.getInstance();
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent=new Intent(login.this, RegisterActivity.class);
                                          startActivity(intent);
                                      }
                                  }
        );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email = email.getText().toString().trim();
                final String user_password = password.getText().toString().trim();
                if (TextUtils.isEmpty(user_email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(user_password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(user_email,user_password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){

                            Toast.makeText(login.this,"Fail", Toast.LENGTH_SHORT ).show();
                        }
                        else{
                            Intent intent = new Intent(login.this, NavigationActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        });
    }
}