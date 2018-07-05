package com.android.medipro.ui.fragments.ayushList;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.medipro.R;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.ui.fragments.doctorInfo.DoctorInfoFragment;



public class AyushListAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    int[] Img;
    String[] Name;

    public AyushListAdapter(Context context) {
        this.context = context;
        this.Img = Img;
        this.Name = Name;
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



    class ViewHolder {


        TextView tvCall;
        LinearLayout llDoctorDetails;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.cust_doctor_list, null);
            viewHolder.tvCall = (TextView) view.findViewById(R.id.tv_book_specialist);


            viewHolder.tvCall.setOnClickListener(this);
            viewHolder.llDoctorDetails.setOnClickListener(this);

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_book_specialist:
                FragmentBeanClass fbc=new FragmentBeanClass((AppCompatActivity) context,R.id.fl_container_main);
                fbc.setFragment(new DoctorInfoFragment());
                break;



        }

    }}



