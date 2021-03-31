package com.example.gosyncv11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayFormActivity extends AppCompatActivity {
    long form_no;
    FirebaseDatabase database;
    DatabaseReference reference;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_form);
        ll = findViewById(R.id.linearLayout_form_show);
        database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = user.getUid();
        reference = database.getReference("forms").child(user_id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    form_no = snapshot.getChildrenCount();
                    //Toast.makeText(DisplayFormActivity.this, String.valueOf(snapshot.getChildrenCount()),Toast.LENGTH_SHORT).show();
                    for(int i=0;i<form_no;i++){
                        final View view = getLayoutInflater().inflate(R.layout.display_form_no,null,false);
                        Button form_no_btn = (Button) view.findViewById(R.id.form_id_no);
                        form_no_btn.setText("Form "+(i+1));
                        ll.addView(view);
                        int finalI = i;
                        form_no_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(DisplayFormActivity.this,FormFillActivity.class);
                                intent.putExtra("FormID",finalI+1);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}