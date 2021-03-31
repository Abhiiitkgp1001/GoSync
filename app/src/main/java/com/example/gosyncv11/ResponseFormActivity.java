package com.example.gosyncv11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResponseFormActivity extends AppCompatActivity {
    long response_id;
    long res_elements,mem_no;
    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Need> need = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_form);
        recyclerView = findViewById(R.id.recycler_view);
        database =FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        response_id = intent.getIntExtra("ResponseID",0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = user.getUid();
        reference = database.getReference("form_response").child(user_id).child(String.valueOf(response_id));
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot child_snapshot : snapshot.getChildren()){
                        mem_no = child_snapshot.getChildrenCount();
                        Need element = new Need();
                        element.elements.clear();
                        for(int i=0;i<mem_no;i++){
                            String s= child_snapshot.child(String.valueOf(i)).child("id").getValue().toString();
                            Elements e = new Elements();
                            e.id = Integer.parseInt(child_snapshot.child(String.valueOf(i)).child("id").getValue().toString());
                            e.name =child_snapshot.child(String.valueOf(i)).child("name").getValue().toString();
                            element.elements.add(e);
                        }
                        //Toast.makeText(ResponseFormActivity.this,String.valueOf(element.elements.size()),Toast.LENGTH_SHORT).show();
                        need.add(element);
                    }
                    adapter = new Adapter(ResponseFormActivity.this,need);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}