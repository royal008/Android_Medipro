package com.android.medipro.custom_utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.android.medipro.R;

import java.util.ArrayList;

/**
 * Created by Deepa on 04-12-2017.
 */

public class MedicalRecordsAdapter extends BaseAdapter {

    Context context;
    ArrayList alDate, alMonth;

    public MedicalRecordsAdapter(Context context, ArrayList alDate, ArrayList alMonth) {
        this.context = context;
        this.alDate = alDate;
        this.alMonth = alMonth;

    }

    @Override
    public int getCount() {
        return alDate.size();
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

    class ViewHolder {
        TextView tvDate, tvMonth;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.cust_medical_record_list, null);
            viewHolder.tvDate = (TextView) view.findViewById(R.id.tv_date_mr);
            viewHolder.tvMonth = (TextView) view.findViewById(R.id.tv_month_mr);

            viewHolder.tvDate.setText(alDate.get(position).toString());
            viewHolder.tvMonth.setText(alMonth.get(position).toString());

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }
}
