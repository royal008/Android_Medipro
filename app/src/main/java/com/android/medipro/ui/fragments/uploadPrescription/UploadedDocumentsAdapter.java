package com.android.medipro.ui.fragments.uploadPrescription;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.android.medipro.R;

import java.util.ArrayList;


public class UploadedDocumentsAdapter extends BaseAdapter {
    Context context;
    ArrayList<Bitmap> images;
    // ArrayList<Integer> closeBtnImage;
    String from;



//    private static class ViewHolder {
//        ImageView imgIcon;
//
//    }
//
    public UploadedDocumentsAdapter(Context c, ArrayList images) {
        context = c;
        this.images = images;
        // this.closeBtnImage=closeBtnImage;


    }
//
////    public UploadedDocumentsAdapter(Context c,Bitmap bitmap, String from) {
//        context = c;
//       this.bitmap=bitmap;
//        this.from = from;
//
//    }

    @Override
    public int getCount() {
        return images.size();
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
        ImageView images, closeBtn, editbtn;
        FrameLayout frameLayout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(context).inflate(R.layout.cust_grid_upload_prescription, null);
            viewHolder.images = (ImageView) view.findViewById(R.id.iv_img_container);
            viewHolder.closeBtn = (ImageView) view.findViewById(R.id.iv_close);
            viewHolder.editbtn = (ImageView) view.findViewById(R.id.iv_edit);
            viewHolder.frameLayout = (FrameLayout) view.findViewById(R.id.fl_container_main);

            viewHolder.images.setImageBitmap(images.get(position));
            viewHolder.closeBtn.setImageResource(R.drawable.closewithcircle);
            viewHolder.editbtn.setImageResource(R.drawable.edit);


            viewHolder.closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("tag", "item clicked");
                    //FrameLayout frameLayout = (FrameLayout) v.findViewById(R.id.fl_container_main);
                    viewHolder.frameLayout.removeAllViews();
//                    Log.e("tag","item delete");
//                    Toast.makeText(context,"item clicked",Toast.LENGTH_LONG);
                }
            });

        } else

        {
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;
    }
}

