package com.example.gosyncv11;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.View_show> {
    ArrayList<Need> element = new ArrayList<>();
    Context context;
    public Adapter(Context ctx, ArrayList<Need> element) {
        this.context = ctx;
        this.element = element;
    }

    @NonNull
    @Override
    public View_show onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_response, null);
        View_show holder = new View_show(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_show holder, int position) {
        Need res = element.get(position);
        for(int i=0;i<res.elements.size();i++){
            Elements e= new Elements();
            e = res.elements.get(i);
            switch (e.id){
                case 0:
                case 3:
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View label_view = inflater.inflate(R.layout.row_label_show, null);
                    TextView Text = (TextView)label_view.findViewById(R.id.edit_name_show);
                    Text.setText(e.name.toString());
                    Text.setTextColor(Color.BLACK);
                    holder.ll.addView(label_view);
                    break;
                case 1:
                case 2:
                    LayoutInflater inflaters = LayoutInflater.from(context);
                    final View cr_view = inflaters.inflate(R.layout.row, null);
                    TextView text_cr = (TextView)cr_view.findViewById(R.id.name_show);
                    text_cr.setText(e.name.toString());
                    text_cr.setTextColor(Color.BLACK);
                    holder.ll.addView(cr_view);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return element.size();
    }

    public class View_show extends RecyclerView.ViewHolder {
        TextView mem_name, mem_id;
        LinearLayout ll;
        public View_show(@NonNull android.view.View itemView) {
            super(itemView);
            mem_name = (TextView)itemView.findViewById(R.id.member_name);
            mem_id = (TextView)itemView.findViewById(R.id.member_id);
            ll = (LinearLayout)itemView.findViewById(R.id.card_response);
        }
    }
}
