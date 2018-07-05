package com.android.medipro.ui.fragments.centers;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.ui.fragments.doctorDetails.DoctorDetailsFragment;


public class CentersAdapter extends BaseAdapter {
    Context context;
    String key;
    FragmentBeanClass fbc;
    Bundle bundle;
    public CentersAdapter(Context context,String key) {
        this.context = context;
        this.key=key;
    }

    @Override
    public int getCount() {
        return 3;
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
        TextView tvBook;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.cust_centers_list, null);
//                viewHolder.images = (ImageView) view.findViewById(R.id.iv_grid_menu);
//                viewHolder.images.setImageResource(images[position]);
//                viewHolder.tvMenuName = (TextView) view.findViewById(R.id.tv_menu_name);
//                viewHolder.tvMenuName.setText(menuName[position]);
            fbc=new FragmentBeanClass((AppCompatActivity) context,R.id.fl_container_main);

            bundle = new Bundle();
            viewHolder.tvBook=(TextView)view.findViewById(R.id.tv_book);
            viewHolder.tvBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String name = key.toString();
                    bundle.putString("keyValue", key);
                    fbc.setFragment(new DoctorDetailsFragment());
                    fbc.getFragment().setArguments(bundle);

                    }

            });

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }
}

