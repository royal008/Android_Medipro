package com.android.medipro.ui.activity.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.medipro.R;


/**
 * Created by Deepa on 01-Dec-17.
 */

public class MenuListAdapter extends BaseAdapter {

    Context context;
    String[] menuList;
    int[] menuImages;

    public MenuListAdapter(Context context, String[] menuList, int[] menuImages){
        this.context=context;
        this.menuList=menuList;
        this.menuImages=menuImages;
    }
    @Override
    public int getCount() {
        return menuList.length;
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

    class ViewHolder{
        ImageView ivMenu;
        TextView tvMenu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.cust_menu_list,null);
            viewHolder.ivMenu=(ImageView)view.findViewById(R.id.iv_menu);
            viewHolder.tvMenu=(TextView)view.findViewById(R.id.tv_menu);
            viewHolder.ivMenu.setImageResource(menuImages[position]);
            viewHolder.tvMenu.setText(menuList[position]);

            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }

        return view;
    }
}
