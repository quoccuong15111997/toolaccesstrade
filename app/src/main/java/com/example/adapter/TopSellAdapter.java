package com.example.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accesstradetool.R;
import com.example.model.TopSellAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopSellAdapter extends RecyclerView.Adapter<TopSellAdapter.ViewHolder>{
    private Context context;
    private ArrayList<TopSellAPI> data;

    public TopSellAdapter(Context context, ArrayList<TopSellAPI> data){
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_top_sell,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final TopSellAPI topSellAPI=data.get(i);
        viewHolder.txt_product_name.setText(topSellAPI.getName());
        viewHolder.txt_product_price.setText(topSellAPI.getPrice());
        viewHolder.txt_product_discout.setText(topSellAPI.getDiscount());
        Picasso.with(context).load(topSellAPI.getImage()).into(viewHolder.img_product_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_product_name;
        TextView txt_product_price;
        TextView txt_product_discout;
        ImageView img_product_image;
        CheckBox chkMonHoc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_product_name=itemView.findViewById(R.id.txt_Product_Name);
            txt_product_price=itemView.findViewById(R.id.txt_Product_Price);
            txt_product_discout=itemView.findViewById(R.id.txt_Product_Discount);
            img_product_image=itemView.findViewById(R.id.img_Product_Image);
        }

    }
}
