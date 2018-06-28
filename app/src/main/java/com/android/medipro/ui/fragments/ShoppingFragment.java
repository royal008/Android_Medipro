package com.android.medipro.ui.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.medipro.R;
import com.android.medipro.custom_utils.ExpandableGridView;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.custom_utils.MainGridViewAdapter;
import com.android.medipro.ui.activity.MenuActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {

    View view;
    ExpandableGridView gvShopping;
    int shoppingImages[]={R.drawable.shopping_surgicalneeds,R.drawable.shopping_fitnessequipments,R.drawable.shopping_food,
    R.drawable.shopping_cosmetics,R.drawable.shopping_eyecare,R.drawable.shopping_mothercare,R.drawable.shopping_oralcare,
    R.drawable.shopping_babycare,R.drawable.shopping_womencare,R.drawable.shopping_skincare,R.drawable.shopping_haircare,
    R.drawable.shopping_petcare,R.drawable.shopping_weightmanagement,R.drawable.shopping_firstaid,R.drawable.shopping_organic,
   };
    String shoppingCategories[]={"Surgical needs","Fitness Equipments","Food","Cosmetics","Eye and Ear Care","Mother Care","Oral Care",
            "Baby Care","Woman Care","Skin Care","Hair Care","Pet Care","Weight Management","First Aid","Organic"};
    MainGridViewAdapter mainGridViewAdapter;
    FragmentBeanClass fbc;
   // LinearLayout llUploadPrescription;
    ImageView ivBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_shopping, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);
//        MenuActivity.llTopBar.setBackgroundResource(R.drawable.ad_common);
//        MenuActivity.ivMenuLines.setImageResource(R.drawable.left_arrow);
//        MenuActivity.tvPlaceName.setText("SHOPPING");
//        MenuActivity.tvPlaceName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.xlarge_text));
//        MenuActivity.tvPlaceName.setTypeface(null, Typeface.NORMAL);
//        MenuActivity.tvCityName.setVisibility(View.GONE);
//        MenuActivity.ivDownArrow.setVisibility(View.GONE);
//        MenuActivity.ivSearch.setVisibility(View.VISIBLE);
//        MenuActivity.ivCart.setVisibility(View.VISIBLE);

       // llUploadPrescription=(LinearLayout)view.findViewById(R.id.ll_upload_prescription);
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        fbc=new FragmentBeanClass((AppCompatActivity) getActivity(),R.id.fl_container_main);
        mainGridViewAdapter=new MainGridViewAdapter(getActivity(),shoppingImages,shoppingCategories,"shopping");
        gvShopping=(ExpandableGridView)view.findViewById(R.id.gv_shopping);
        gvShopping.setAdapter(mainGridViewAdapter);
        onClick();
        return view;
    }

    private void onClick() {
//        llUploadPrescription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fbc.setFragment(new UploadPrescription());
//            }
//        });
        gvShopping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==7){
                    fbc.setFragment(new BabyCareFragment());
                }
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }




}
