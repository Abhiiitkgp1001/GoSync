package com.example.gosyncv11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
EditText startup_name, motto, about, contact_no;
Button SaveData, add_member;
FirebaseDatabase database;
DatabaseReference reference, ref_member;
LinearLayout ll_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ll_add = findViewById(R.id.ll_add);
        startup_name = (EditText) findViewById(R.id.editTextTextPersonName);
        about = (EditText) findViewById(R.id.editTextTextPersonName2);
        motto = (EditText) findViewById(R.id.editTextTextPersonName5);
        contact_no = (EditText) findViewById(R.id.editTextNumber);
        SaveData = (Button) findViewById(R.id.savedata);
        add_member = (Button) findViewById(R.id.button5);
        database = FirebaseDatabase.getInstance();
        ref_member = database.getReference("Members");
        reference = database.getReference("users");
        String user =  FirebaseAuth.getInstance().getCurrentUser().getUid();
        add_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = getLayoutInflater().inflate(R.layout.add_members,null,false);
                EditText member_name = (EditText)view.findViewById(R.id.member_name);
                EditText member_id = (EditText)view.findViewById(R.id.member_id);
                ImageView imageClose = (ImageView)view.findViewById(R.id.image_remove);
                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeView(view);
                    }
                });

                ll_add.addView(view);
            }
        });
        SaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startup_name_helper = startup_name.getText().toString().trim();
                String motto_helper = motto.getText().toString().trim();
                String about_helper = about.getText().toString().trim();
                long contact_no_helper = Long.parseLong(contact_no.getText().toString().trim());
                UserHelper helper = new UserHelper(startup_name_helper,motto_helper,about_helper,contact_no_helper);
                reference.child(user).setValue(helper);
                for(int i=0;i<ll_add.getChildCount();i++){
                    View view = ll_add.getChildAt(i);
                    EditText m_name = (EditText) view.findViewById(R.id.member_name);
                    EditText m_id = (EditText) view.findViewById(R.id.member_id);
                    Members mem = new Members();
                    String s = m_id.getText().toString().trim();
                    mem.member_name = m_name.getText().toString().trim();
                    mem.member_id = m_id.getText().toString().trim();
                    ref_member.child(user).child(s).setValue(mem);
                }
                Toast.makeText(SignupActivity.this, "Data Saved Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SignupActivity.this, NavigationActivity.class);
                startActivity(intent);

            }
        });
    }

    private void removeView(View view) {
        ll_add.removeView(view);
    }

}