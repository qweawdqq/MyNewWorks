package com.example.dllo.mynewworks.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.mynewworks.R;
import com.example.dllo.mynewworks.untils.ToNextUntil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dllo on 16/4/19.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private ArrayList<String> datas;
    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void addInfo(ArrayList<String> datas) {
        this.datas = datas;
    }

    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.
                from(context).inflate(R.layout.item_main_recycle, parent, false));
    }

    @Override
    public void onBindViewHolder(MainAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_main_recycle_tv) TextView textView;
        private int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToNextUntil.toNextActivity(context, datas.get(position));
                }
            });
        }
    }
}
