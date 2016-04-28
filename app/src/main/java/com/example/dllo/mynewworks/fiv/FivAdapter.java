package com.example.dllo.mynewworks.fiv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.mynewworks.R;
import com.example.dllo.mynewworks.untils.BitMapUntils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/4/26.
 */
public class FivAdapter extends RecyclerView.Adapter<FivAdapter.MyViewHolder> {
    private ArrayList<Integer> datas;
    private Context context;

    public FivAdapter(Context context) {
        this.context = context;
        datas = new ArrayList<>();
        datas.add(R.mipmap.ic_girls_0);
        datas.add(R.mipmap.ic_girls_1);
        datas.add(R.mipmap.ic_girls_2);
        datas.add(R.mipmap.ic_girls_3);
        datas.add(R.mipmap.ic_girls_4);
        datas.add(R.mipmap.ic_girls_5);
        datas.add(R.mipmap.ic_girls_6);
        datas.add(R.mipmap.ic_girls_7);
        datas.add(R.mipmap.ic_girls_8);
        datas.add(R.mipmap.ic_girls_9);
        datas.add(R.mipmap.ic_girls_10);
    }

    @Override
    public FivAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_fiv, parent, false));
    }

    @Override
    public void onBindViewHolder(FivAdapter.MyViewHolder holder, int position) {
       holder.imageView.setImageBitmap(BitMapUntils.setMyBitMap(context, datas.get(position)));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_fiv_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "gogo", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
