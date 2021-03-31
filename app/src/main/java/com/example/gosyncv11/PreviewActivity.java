package com.example.gosyncv11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreviewActivity extends AppCompatActivity {
    long form_id=0;
    ArrayList<Elements> elements_show = new ArrayList<>();
    LinearLayout linearLayout_show;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        database = FirebaseDatabase.getInstance();
        String user =  FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference = database.getReference("forms").child(user);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    form_id = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        elements_show = (ArrayList<Elements>) getIntent().getExtras().getSerializable("list");
        linearLayout_show = findViewById(R.id.linearLayout_show);
        save = (Button) findViewById(R.id.Save);
        for(int i=0;i<elements_show.size();i++){
            switch (elements_show.get(i).id){
                case 0:
                    TextView label = new TextView(this);
                    label.setText(elements_show.get(i).name.toString());
                    label.setBackgroundResource(R.drawable.round_grey);
                    label.setTextColor(Color.BLACK);
                    linearLayout_show.addView(label);
                    break;
                case 1:
                    CheckBox ch = new CheckBox(this);
                    ch.setText(elements_show.get(i).name.toString());
                    ch.setTextColor(Color.BLACK);
                    ch.setBackgroundResource(R.drawable.round_grey);
                    linearLayout_show.addView(ch);
                    break;
                case 2:
                    RadioButton rb = new RadioButton(this);
                    rb.setText(elements_show.get(i).name.toString());
                    rb.setBackgroundResource(R.drawable.round_grey);
                    rb.setTextColor(Color.BLACK);
                    linearLayout_show.addView(rb);
                    break;
                case 3:
                    EditText tf = new EditText(this);
                    tf.setBackgroundResource(R.drawable.round_grey);
                    tf.setTextColor(Color.BLACK);
                    linearLayout_show.addView(tf);
                    break;
            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(String.valueOf(form_id+1)).setValue(elements_show);
                Toast.makeText(PreviewActivity.this, String.valueOf(form_id),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
