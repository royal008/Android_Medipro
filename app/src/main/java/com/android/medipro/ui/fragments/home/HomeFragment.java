package com.android.medipro.ui.fragments.home;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.ExpandableGridView;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.ui.activity.main.MenuActivity;

import com.android.medipro.ui.fragments.orderMedicines.OrderMedicinesFragment;
import com.android.medipro.ui.fragments.shopping.ShoppingFragment;
import com.android.medipro.ui.fragments.uploadPrescription.UploadPrescription;
import com.android.medipro.ui.fragments.addreminder.AddReminderFragment;
import com.android.medipro.ui.fragments.ayush.AyushFragment;
import com.android.medipro.ui.fragments.bookAppointment.BookAppointmentFragment;

import com.android.medipro.ui.fragments.healthBank.HealthBankFragment;
import com.android.medipro.ui.fragments.healthCenters.HealthCentersFragment;
import com.android.medipro.ui.fragments.centers.CentersFragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements View.OnClickListener {
    View view;
    AdView adView;
    FragmentBeanClass fbc;
    RequestQueue mQueue;
    LinearLayout llUploadPrescription, llBookAppointment, llMenu, llOrderMedicines, llBookTest, llShopping, llAyush, llInsurance, llHealthCenter, llHealthBank;
    ExpandableGridView gvPopularProducts;

    Bundle bundle;
    private ViewPager vp_slider;
    private LinearLayout ll_dots;
    private TextView[] dots;
    int page_position = 0;
    SliderPageAdapter sliderPagerAdapter;
    ArrayList<String> slider_image_list;



    PopularProductAdapter popularProductAdapter;
    ArrayList alPopProductImage, alPopProductName, alPopProductOldPrice, alPopProductDiscount, alPopProductNewPrice;
    TextView tvViewAllProduct;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);


        MenuActivity.llTopBar.setVisibility(View.VISIBLE);
        MenuActivity.llTopBar.setBackgroundResource(R.color.red);
        MenuActivity.ivMenuLines.setImageResource(R.drawable.menu_lines);
        MenuActivity.tvPlaceName.setText("Guindy");
        MenuActivity.tvPlaceName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_text));
        MenuActivity.tvCityName.setVisibility(View.VISIBLE);
        MenuActivity.ivDownArrow.setVisibility(View.VISIBLE);
        MenuActivity.ivSearch.setVisibility(View.VISIBLE);
        MenuActivity.ivCart.setVisibility(View.VISIBLE);

       // llUploadPrescription = (LinearLayout) view.findViewById(R.id.ll_upload_prescription);
        llBookAppointment = (LinearLayout) view.findViewById(R.id.ll_book_appointment);
        llMenu = (LinearLayout) view.findViewById(R.id.ll_menu);
        gvPopularProducts = (ExpandableGridView) view.findViewById(R.id.gv_popular_products);
        tvViewAllProduct = (TextView) view.findViewById(R.id.tv_view_all_product);
        llOrderMedicines = (LinearLayout) view.findViewById(R.id.ll_order_medicines);
        llBookTest = (LinearLayout) view.findViewById(R.id.ll_book_test);
        llShopping = (LinearLayout) view.findViewById(R.id.ll_shopping);
        llAyush = (LinearLayout) view.findViewById(R.id.ll_ayush);
        llInsurance = (LinearLayout) view.findViewById(R.id.ll_insurance);
        llHealthCenter = (LinearLayout) view.findViewById(R.id.ll_health_center);
        llHealthBank = (LinearLayout) view.findViewById(R.id.ll_health_bank);

        adView = (AdView) view.findViewById(R.id.adView);


        MobileAds.initialize(getActivity(), getString(R.string.admob_app_id));

        alPopProductImage = new ArrayList();
        alPopProductName = new ArrayList();
        alPopProductOldPrice = new ArrayList();
        alPopProductDiscount = new ArrayList();
        alPopProductNewPrice = new ArrayList();
        slider_image_list = new ArrayList<>();

        bundle = new Bundle();

        fbc = new FragmentBeanClass((AppCompatActivity) getActivity(), R.id.fl_container_main);

        for (int i = 0; i <4; i++) {
            alPopProductImage.add(R.drawable.documents);
            alPopProductName.add("Products Name");
            alPopProductOldPrice.add("₹349.0");
            alPopProductDiscount.add("%15.0 OFF");
            alPopProductNewPrice.add("₹297.0");
        }

        popularProductAdapter = new PopularProductAdapter(getActivity(), alPopProductImage, alPopProductName, alPopProductOldPrice, alPopProductDiscount, alPopProductNewPrice);
        gvPopularProducts.setAdapter(popularProductAdapter);
        gvPopularProducts.setExpanded(true);


        // method for initialisation
        init();

// method for adding indicators
        addBottomDots(0);

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == slider_image_list.size()) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                vp_slider.setCurrentItem(page_position, true);
            }
        };

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 5000);

        onClick();
        return view;
    }



    private void imagebanner(){
       mQueue = Volley.newRequestQueue(getActivity());
       String url = "http://13.232.102.36/api/v1/resources/banner";

       StringRequest sr = new StringRequest(Request.Method.GET, url,
               new com.android.volley.Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.e("Response",response);
                       try {

                           JSONObject object = new JSONObject(response);
                           Boolean success = object.getBoolean("success");
                           Log.e("success", String.valueOf(success));

                           String message = object.getString("message");
                           Log.e("msg",message);

                           JSONArray data = object.getJSONArray("data");
                           for(int i=0;i<data.length();i++){
                               //   data list = new data();
                               JSONObject obj = data.getJSONObject(i);
                               String name = obj.getString("name");
                               Log.e("name",name);
                               String url = obj.getString("url");
                               Log.e("url",url);
                               slider_image_list.add(url);
                           }
                           Log.e("length", String.valueOf(slider_image_list.size()));

                         sliderPagerAdapter.notifyDataSetChanged();
                       } catch (Exception e) {
                           e.printStackTrace();
                           Log.e("Messages Frag", "" + e.toString());
                       }


                   }
               }, new com.android.volley.Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });

       RequestQueue queue = Volley.newRequestQueue(getActivity());
       queue.add(sr);

   }

    private void init() {


        vp_slider = (ViewPager) view.findViewById(R.id.vp_slider);
        ll_dots = (LinearLayout) view.findViewById(R.id.ll_dots);





      imagebanner();


        sliderPagerAdapter = new SliderPageAdapter(getActivity(), slider_image_list);
        vp_slider.setAdapter(sliderPagerAdapter);

        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void addBottomDots(int currentPage) {
        if (isAdded()) {
            if (slider_image_list.size() != 0) {
                dots = new TextView[slider_image_list.size()];

                ll_dots.removeAllViews();
                for (int i = 0; i < dots.length; i++) {
                    dots[i] = new TextView(getActivity());
                    dots[i].setText(Html.fromHtml("&#8226;"));
                    dots[i].setTextSize(35);
                    dots[i].setTextColor(Color.parseColor("#BEBAB8"));
                    ll_dots.addView(dots[i]);
                }

                if (dots.length > 0)
                    dots[currentPage].setTextColor(Color.parseColor("#F24437"));
            }
        }
    }


    private void onClick() {

        llOrderMedicines.setOnClickListener(this);
        llBookTest.setOnClickListener(this);
        llShopping.setOnClickListener(this);
        llAyush.setOnClickListener(this);
        llInsurance.setOnClickListener(this);
        llHealthCenter.setOnClickListener(this);
        llHealthBank.setOnClickListener(this);
//        llUploadPrescription.setOnClickListener(this);
        llBookAppointment.setOnClickListener(this);
        tvViewAllProduct.setOnClickListener(this);


        gvPopularProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    fbc.setFragment(new MedicalRecordsFragment());
//                }
                if (position == 1) {
                    fbc.setFragment(new AddReminderFragment());
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.ll_upload_prescription:
//                fbc.setFragment(new UploadPrescription());
//                break;
            case R.id.ll_order_medicines:
                fbc.setFragment(new OrderMedicinesFragment());
                break;

            case R.id.ll_book_test:
                bundle.putString("keyValue","BOOK TEST");
                fbc.setFragment(new CentersFragment());
                fbc.getFragment().setArguments(bundle);
                break;

            case R.id.ll_shopping:
                fbc.setFragment(new ShoppingFragment());
                break;

            case R.id.ll_ayush:
                fbc.setFragment(new AyushFragment());
                break;
            case R.id.ll_insurance:
                bundle.putString("keyValue","INSURANCE");
                fbc.setFragment(new CentersFragment());
                fbc.getFragment().setArguments(bundle);
              //  fbc.setFragment(new InsuranceFragment());
                break;
            case R.id.ll_health_center:
                fbc.setFragment(new HealthCentersFragment());
                break;

            case R.id.ll_health_bank:
                fbc.setFragment(new HealthBankFragment());
                break;
            case R.id.ll_book_appointment:
                fbc.setFragment(new BookAppointmentFragment());
                break;
            case R.id.tv_view_all_product:
                fbc.setFragment(new OrderMedicinesFragment());
                break;

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId(getString(R.string.banner_home_footer));

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}
