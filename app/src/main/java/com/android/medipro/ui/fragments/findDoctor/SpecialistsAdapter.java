package com.android.medipro.ui.fragments.findDoctor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.ui.fragments.doctorDetails.DoctorDetailsFragment;


/**
 * Created by Deepa on 04-12-2017.
 */

public class SpecialistsAdapter extends BaseAdapter {

    Context context;
    int[] specialistImg;
    String[] specialistsName;
    public SpecialistsAdapter(Context context){
        this.context=context;
        this.specialistImg=specialistImg;
        this.specialistsName=specialistsName;
    }
    @Override
    public int getCount() {
        return 6;
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
        ImageView ivSpecialist;
        TextView tvSpecialist,tvBookSpecialist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.cust_doctor_list, null);
//            viewHolder.ivSpecialist = (ImageView) view.findViewById(R.id.iv_specialist);
//            viewHolder.ivSpecialist.setImageResource(specialistImg[position]);
//            viewHolder.tvSpecialist = (TextView) view.findViewById(R.id.tv_specialist);
//            viewHolder.tvSpecialist.setText(specialistsName[position]);
            viewHolder.tvBookSpecialist=(TextView)view.findViewById(R.id.tv_book_specialist);
            viewHolder.tvBookSpecialist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new FragmentBeanClass((AppCompatActivity) context,R.id.fl_container_main).setFragment(new DoctorDetailsFragment());
                }
            });

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }
}

