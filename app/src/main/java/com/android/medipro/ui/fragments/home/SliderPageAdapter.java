package com.android.medipro.ui.fragments.home;

//import android.content.Context;
//import android.graphics.BitmapFactory;
//import android.os.Parcelable;
//import android.support.v4.view.PagerAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.android.medipro.R;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//public class SliderPageAdapter extends PagerAdapter {
//
//    private Context context;
//    private ArrayList<String> imageModelArrayList;
//    private LayoutInflater layoutInflater;
//
//    public SliderPageAdapter(Context context, ArrayList<String> imageModelArrayList) {
//        this.context = context;
//        this.imageModelArrayList = imageModelArrayList;
//    }
//
//
//
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.slidingimages_layout, container, false);
//
//
//        ImageView im_slider = (ImageView) view.findViewById(R.id.imageView);
//        Picasso.with(context.getApplicationContext())
//                .load(imageModelArrayList.get(position))
////                .placeholder(R.mipmap.ic_launcher) // optional
////                .error(R.mipmap.ic_launcher)         // optional
//                .into(im_slider);
//
//
//        container.addView(view);
//
//        return view;
//    }
//
//    @Override
//    public int getCount() {
//        return imageModelArrayList.size();
//    }
//
//
//    @Override
//    public boolean isViewFromObject(View view, Object obj) {
//        return view == obj;
//    }
//
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        View view = (View) object;
//        container.removeView(view);
//    }
//}

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.medipro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SliderPageAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> imageModelArrayList;
    private LayoutInflater layoutInflater;
  //  private Integer [] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};

    public SliderPageAdapter(Context context, ArrayList<String> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slidingimages_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        //imageView.setImageResource(images[position]);

        Picasso.with(context.getApplicationContext())
                .load(imageModelArrayList.get(position))
//                .placeholder(R.mipmap.ic_launcher) // optional
//                .error(R.mipmap.ic_launcher)         // optional
                .into(imageView);



//
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);


    }
}


