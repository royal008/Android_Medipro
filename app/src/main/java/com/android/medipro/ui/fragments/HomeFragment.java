package com.android.medipro.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.ExpandableGridView;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.custom_utils.ImageModel;
import com.android.medipro.custom_utils.PopularProductAdapter;
import com.android.medipro.custom_utils.SlidingImage_Adapter;
import com.android.medipro.ui.activity.MenuActivity;
import com.android.medipro.ui.fragments.addreminder.AddReminderFragment;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    View view;
    FragmentBeanClass fbc;
    LinearLayout llUploadPrescription, llBookAppointment, llMenu, llOrderMedicines, llBookTest, llShopping, llAyush, llInsurance, llHealthCenter, llHealthBank;
    ExpandableGridView gvPopularProducts;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    CirclePageIndicator indicator;
    int[] myImageList = {R.drawable.mainmenu, R.drawable.gym, R.drawable.map, R.drawable.spa, R.drawable.yoga};

    PopularProductAdapter popularProductAdapter;
    ArrayList alPopProductImage, alPopProductName, alPopProductOldPrice, alPopProductDiscount, alPopProductNewPrice;
    TextView tvViewAllProduct;

    Context context;

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

        llUploadPrescription = (LinearLayout) view.findViewById(R.id.ll_upload_prescription);
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

        mPager = (ViewPager) view.findViewById(R.id.pager);
        indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);


        alPopProductImage = new ArrayList();
        alPopProductName = new ArrayList();
        alPopProductOldPrice = new ArrayList();
        alPopProductDiscount = new ArrayList();
        alPopProductNewPrice = new ArrayList();
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        fbc = new FragmentBeanClass((AppCompatActivity) getActivity(), R.id.fl_container_main);

        for (int i = 0; i < 4; i++) {
            alPopProductImage.add(R.drawable.documents);
            alPopProductName.add("Products Name");
            alPopProductOldPrice.add("₹349.0");
            alPopProductDiscount.add("%15.0 OFF");
            alPopProductNewPrice.add("₹297.0");
        }

        popularProductAdapter = new PopularProductAdapter(getActivity(), alPopProductImage, alPopProductName, alPopProductOldPrice, alPopProductDiscount, alPopProductNewPrice);
        gvPopularProducts.setAdapter(popularProductAdapter);
        gvPopularProducts.setExpanded(true);

        init();
        onClick();
        return view;
    }

    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        // mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(getActivity(), imageModelArrayList));

        // CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 0, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {


            }
        });

    }


    private void onClick() {

        llOrderMedicines.setOnClickListener(this);
        llBookTest.setOnClickListener(this);
        llShopping.setOnClickListener(this);
        llAyush.setOnClickListener(this);
        llInsurance.setOnClickListener(this);
        llHealthCenter.setOnClickListener(this);
        llHealthBank.setOnClickListener(this);
        llUploadPrescription.setOnClickListener(this);
//

        llBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbc.setFragment(new BookAppointmentFragment());
            }
        });
        tvViewAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbc.setFragment(new OrderMedicinesFragment());
            }
        });

        gvPopularProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    fbc.setFragment(new MedicalRecordsFragment());
                }
                if (position == 1) {
                    fbc.setFragment(new AddReminderFragment());
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_upload_prescription:
                fbc.setFragment(new UploadPrescription());
                break;
            case R.id.ll_order_medicines:
                fbc.setFragment(new OrderMedicinesFragment());
                break;

            case R.id.ll_book_test:
                fbc.setFragment(new BookTestFragment());
                break;

            case R.id.ll_shopping:
                fbc.setFragment(new ShoppingFragment());
                break;

            case R.id.ll_ayush:
                fbc.setFragment(new AyushFragment());
                break;
            case R.id.ll_insurance:
                fbc.setFragment(new InsuranceFragment());
                break;
            case R.id.ll_health_center:
                fbc.setFragment(new HealthCentersFragment());
                break;

            case R.id.ll_health_bank:
                fbc.setFragment(new HealthBankFragment());
                break;
        }

    }


}
