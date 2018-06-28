package com.android.medipro.custom_utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.medipro.R;

import java.util.ArrayList;

/**
 * Created by Deepa on 02-Dec-17.
 */

public class PopularProductAdapter extends BaseAdapter {
    Context context;
    ArrayList alPopProductImage,alPopProductName,alPopProductOldPrice,alPopProductDiscount,alPopProductNewPrice;

    public PopularProductAdapter(Context context, ArrayList alPopProductImage, ArrayList alPopProductName,
                                 ArrayList alPopProductOldPrice, ArrayList alPopProductDiscount, ArrayList alPopProductNewPrice){
        this.context=context;
        this.alPopProductImage=alPopProductImage;
        this.alPopProductName=alPopProductName;
        this.alPopProductOldPrice=alPopProductOldPrice;
        this.alPopProductDiscount=alPopProductDiscount;
        this.alPopProductNewPrice=alPopProductNewPrice;
    }
    @Override
    public int getCount() {
        return alPopProductImage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder {
        ImageView ivPopProduct;
        TextView tvProductName,tvProductOldPrice,tvProductDiscount,tvProductNewPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.cust_popular_products_grid,null);
            viewHolder.ivPopProduct=(ImageView)view.findViewById(R.id.iv_popular_product);
            viewHolder.tvProductName=(TextView)view.findViewById(R.id.tv_product_name);
            viewHolder.tvProductOldPrice=(TextView)view.findViewById(R.id.tv_previous_price);
            viewHolder.tvProductDiscount=(TextView)view.findViewById(R.id.tv_offer_percentage);
            viewHolder.tvProductNewPrice=(TextView)view.findViewById(R.id.tv_current_price);

            viewHolder.ivPopProduct.setImageResource((Integer) alPopProductImage.get(position));
            viewHolder.tvProductOldPrice.setText(alPopProductOldPrice.get(position).toString());
            viewHolder.tvProductDiscount.setText(alPopProductDiscount.get(position).toString());
            viewHolder.tvProductNewPrice.setText(alPopProductNewPrice.get(position).toString());


            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }

        return view;
    }
}

