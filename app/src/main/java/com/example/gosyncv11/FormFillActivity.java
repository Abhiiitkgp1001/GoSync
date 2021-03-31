package com.example.gosyncv11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FormFillActivity extends AppCompatActivity {
    int form_no;
    int form_res_no;
    long form_elements;
    LinearLayout linearLayout_response;
    FirebaseDatabase database;
    DatabaseReference reference,ref_response;
    ArrayList<Elements> form_response = new ArrayList<>();
    Button save_response;
    EditText mem_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fill);
        Intent intent = getIntent();
        linearLayout_response = findViewById(R.id.linearLayout_response);
        form_no = intent.getIntExtra("FormID",0);
        database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = user.getUid();
        mem_id = (EditText) findViewById(R.id.mem_id);
        String m_id = mem_id.getText().toString().trim();
        save_response = (Button) findViewById(R.id.Response);
        ref_response = database.getReference("form_response").child(user_id).child(String.valueOf(form_no));
        reference = database.getReference("forms").child(user_id).child(String.valueOf(form_no));
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    form_elements = snapshot.getChildrenCount();
                    for(int i=0;i<form_elements;i++){
                        String s_id = String.valueOf(i);
                        String s = snapshot.child(s_id).child("id").getValue().toString();
                        switch (s){
                            case "0":
                                final View labelView = getLayoutInflater().inflate(R.layout.row_label_show,null,false);
                                TextView Text = (TextView)labelView.findViewById(R.id.edit_name_show);
                                Text.setText(snapshot.child(s_id).child("name").getValue().toString());
                                Text.setTextColor(Color.BLACK);
                                linearLayout_response.addView(labelView);
                                break;
                            case "1":
                                final View ch = getLayoutInflater().inflate(R.layout.row_cb_show,null,false);
                                TextView Text_ch = (TextView)ch.findViewById(R.id.edit_name_show_cb);
                                Text_ch.setText(snapshot.child(s_id).child("name").getValue().toString());
                                Text_ch.setTextColor(Color.BLACK);
                                linearLayout_response.addView(ch);
                                break;
                            case "2":
                                final View rb = getLayoutInflater().inflate(R.layout.row_show_rb,null,false);
                                TextView Text_rb = (TextView)rb.findViewById(R.id.edit_name_show_rb);
                                Text_rb.setText(snapshot.child(s_id).child("name").getValue().toString());
                                Text_rb.setTextColor(Color.BLACK);
                                linearLayout_response.addView(rb);
                                break;
                            case "3":
                                final View tf = getLayoutInflater().inflate(R.layout.row_tf_show,null,false);
                                EditText editText = (EditText)tf.findViewById(R.id.edit_name_tf_show);
                                editText.setTextColor(Color.BLACK);
                                linearLayout_response.addView(tf);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                form_response.clear();
                for(int i=0;i<linearLayout_response.getChildCount();i++){
                    View view = linearLayout_response.getChildAt(i);
                    switch(view.getId()){
                        case R.id.id_label_show:
                            TextView tv= (TextView) view.findViewById((R.id.edit_name_show));
                            Elements response = new Elements();
                            response.id=0;
                            response.name= tv.getText().toString().trim();
                            form_response.add(response);
                            break;
                        case R.id.id_cb_show:
                            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_show);
                            TextView tv_cb= (TextView) view.findViewById((R.id.edit_name_show_cb));
                            Elements response_ch = new Elements();
                            if(checkBox.isChecked()){
                                response_ch.id=1;
                                response_ch.name= tv_cb.getText().toString().trim();
                                form_response.add(response_ch);
                            }
                            break;
                        case R.id.id_rb_show:
                            Elements response_rb = new Elements();
                            TextView tv_rb= (TextView) view.findViewById((R.id.edit_name_show_rb));
                            RadioButton radioButton=(RadioButton) view.findViewById(R.id.radio_show);
                            if(radioButton.isChecked()){
                                response_rb.id=2;
                                response_rb.name= tv_rb.getText().toString().trim();
                                form_response.add(response_rb);
                            }
                            break;
                        case R.id.id_tf_show:
                            Elements response_tf = new Elements();
                            EditText editText= (EditText) view.findViewById((R.id.edit_name_tf_show));
                            response_tf.id=3;
                            response_tf.name = editText.getText().toString().trim();
                            form_response.add(response_tf);
                            break;
                    }
                }
                ref_response.child(String.valueOf(mem_id.getText().toString().trim())).setValue(form_response);
            }
        });


    }
}