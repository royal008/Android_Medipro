package com.android.medipro.custom_utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.medipro.ui.fragments.DoctorInfoFragment;


/**
 * Created by Deepa on 03-12-2017.
 */

public class AyushMainAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    int[] Img;
    String[] Name;

    public AyushMainAdapter(Context context) {
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

        ImageView ivImage;
        TextView tvName, tvCall;
        LinearLayout llDoctorDetails;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.cust_ayush_list, null);
//                viewHolder.images = (ImageView) view.findViewById(R.id.iv_grid_menu);
//                viewHolder.images.setImageResource(images[position]);
//                viewHolder.tvMenuName = (TextView) view.findViewById(R.id.tv_menu_name);
//                viewHolder.tvMenuName.setText(menuName[position]);
            viewHolder.llDoctorDetails=(LinearLayout)view.findViewById(R.id.ll_doctor_details);
            viewHolder.tvCall = (TextView) view.findViewById(R.id.tv_call);


            viewHolder.tvCall.setOnClickListener(this);
            viewHolder.llDoctorDetails.setOnClickListener(this);
//            viewHolder.tvCall.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("call", "calling");
//                    // Toast.makeText(ActivityCompat,"calling",Toast.LENGTH_LONG).show();
//                    Intent callIntent = new Intent(Intent.ACTION_CALL);
//                    callIntent.setData(Uri.parse("tel:0377778888"));
//                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                       // context.startActivity(callIntent);
//                        return;
//                    }
//                    context.startActivity(callIntent);
//                }
//            });




            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_call:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0377778888"));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                context.startActivity(callIntent);
                break;
            case R.id.ll_doctor_details:
               FragmentBeanClass fbc=new FragmentBeanClass((AppCompatActivity) context,R.id.fl_container_main);
                fbc.setFragment(new DoctorInfoFragment());
                break;


        }

    }}



