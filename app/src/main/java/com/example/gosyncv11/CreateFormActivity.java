package com.example.gosyncv11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateFormActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button add_label,add_tf,add_cb,add_rb,button_submit;
    ArrayList<Elements> elements_name = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);

        linearLayout=findViewById(R.id.layout_list);
        add_label= (Button) findViewById(R.id.button_add_label);
        add_tf= (Button) findViewById(R.id.button_add_text);
        add_cb= (Button) findViewById(R.id.button_add_checkbox);
        add_rb= (Button) findViewById(R.id.button_add_radiobutton);
        button_submit = (Button) findViewById(R.id.button_submit);
        add_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView_label();
            }
        });
        add_tf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView_tf();
            }
        });
        add_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView_cb();
            }
        });
        add_rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView_rb();
            }
        });
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIfValid()){
                    Intent intent = new Intent(CreateFormActivity.this,PreviewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",elements_name);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean checkIfValid() {
        elements_name.clear();
        boolean result= true;
        int row=0;
        for(int i=0;i<linearLayout.getChildCount();i++){
            View view = linearLayout.getChildAt(i);
            switch(view.getId()){
                case R.id.id_label:
                    EditText et= (EditText) view.findViewById((R.id.edit_name));
                    Elements ele = new Elements();
                    ele.setId(0);
                    ele.setName(et.getText().toString());

                    elements_name.add(ele);
                    break;
                case R.id.id_cb:
                    EditText et1= (EditText) view.findViewById((R.id.edit_name_cb));
                    Elements ele1 = new Elements();
                    ele1.setId(1);
                    ele1.setName(et1.getText().toString());
                    elements_name.add(ele1);
                    break;
                case R.id.id_rb:
                    EditText et2= (EditText) view.findViewById((R.id.edit_name_rb));
                    Elements ele2 = new Elements();
                    ele2.setId(2);
                    ele2.setName(et2.getText().toString());

                    elements_name.add(ele2);
                    break;
                case R.id.id_tf:
                    EditText et3= (EditText) view.findViewById((R.id.edit_name_tf));
                    Elements ele3 = new Elements();
                    ele3.setId(3);
                    ele3.setName(et3.getText().toString());

                    elements_name.add(ele3);
                    break;
            }
        }
        return result;
    }


    private void addView_rb() {
        final View labelView = getLayoutInflater().inflate(R.layout.row_radiobutton,null,false);

        EditText editText = (EditText)labelView.findViewById(R.id.edit_name);
        ImageView imageClose = (ImageView)labelView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(labelView);
            }
        });

        linearLayout.addView(labelView);
    }

    private void addView_cb() {
        final View labelView = getLayoutInflater().inflate(R.layout.row_checkbox,null,false);
        EditText editText = (EditText)labelView.findViewById(R.id.edit_name);
        ImageView imageClose = (ImageView)labelView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(labelView);
            }
        });

        linearLayout.addView(labelView);
    }

    private void addView_tf() {
        final View labelView = getLayoutInflater().inflate(R.layout.row_textfield,null,false);

        EditText editText = (EditText)labelView.findViewById(R.id.edit_name);
        ImageView imageClose = (ImageView)labelView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(labelView);
            }
        });

        linearLayout.addView(labelView);
    }


    private void addView_label() {
        final View labelView = getLayoutInflater().inflate(R.layout.row_label,null,false);
        EditText editText = (EditText)labelView.findViewById(R.id.edit_name);
        ImageView imageClose = (ImageView)labelView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(labelView);
            }
        });

        linearLayout.addView(labelView);

    }

    private void removeView(View view){

        linearLayout.removeView(view);

    }
}