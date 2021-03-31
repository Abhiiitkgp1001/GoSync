package com.example.gosyncv11;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
public class FormActivity extends Activity  {

    public LinearLayout ll;
    public Button submit;
    public ScrollView mScrollview;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ll = (LinearLayout)findViewById(R.id.linearLayout2);
        submit = (Button)findViewById(R.id.savedata);
        mScrollview = (ScrollView)findViewById(R.id.scrollView1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });



    }

    public void make_dynText(View v){
        TextView tv = new TextView(this);
        tv.setText("worla");
        ll.addView(tv);
        mScrollview.fullScroll(ScrollView.FOCUS_DOWN);
    }

    public void make_dynET(View v){
        EditText et = new EditText(this);
        et.setText("diidy doo");
        ll.addView(et);
        mScrollview.fullScroll(ScrollView.FOCUS_DOWN);
    }

    public void make_ll(View v){
        LinearLayout lor = new LinearLayout(this);
        lor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        lor.setOrientation(LinearLayout.HORIZONTAL);
        CheckBox cb = new CheckBox(this);
        cb.setText("");
        lor.addView(cb);
        EditText et = new EditText(this);
        et.setText("diidy doo");
        lor.addView(et);
        ll.addView(lor);
        mScrollview.fullScroll(ScrollView.FOCUS_DOWN);
    }

}