package com.android.medipro.ui.fragments.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.medipro.R;


/**
 * Created by Deepa on 02-Dec-17.
 */

public class MainGridViewAdapter extends BaseAdapter {
    Context context;
    int images[];
    String menuName[];
    String type;
    public MainGridViewAdapter(Context context, int images[], String menuName[], String type){
        this.context=context;
        this.images=images;
        this.menuName=menuName;
        this.type=type;
    }
    @Override
    public int getCount() {
        return images.length;
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
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    class ViewHolder{
        ImageView images;
        TextView tvMenuName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
            if(type.equals("menu")) {
                view = LayoutInflater.from(context).inflate(R.layout.cust_main_gridview, null);
                viewHolder.images = (ImageView) view.findViewById(R.id.iv_grid_menu);
                viewHolder.images.setImageResource(images[position]);
                viewHolder.tvMenuName = (TextView) view.findViewById(R.id.tv_menu_name);
                viewHolder.tvMenuName.setText(menuName[position]);

                view.setTag(viewHolder);
            }
            else if(type.equals("shopping")) {
                view = LayoutInflater.from(context).inflate(R.layout.cust_shopping_list, null);
                viewHolder.images = (ImageView) view.findViewById(R.id.iv_grid_menu);
                viewHolder.images.setImageResource(images[position]);
                viewHolder.tvMenuName = (TextView) view.findViewById(R.id.tv_menu_name);
                viewHolder.tvMenuName.setText(menuName[position]);

                view.setTag(viewHolder);
            }
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        return view;
    }
}
