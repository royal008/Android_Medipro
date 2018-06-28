package com.android.medipro.ui.fragments.healthCenters;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.ui.activity.main.MenuActivity;
import com.android.medipro.ui.fragments.spa.SpaFragment;
import com.android.medipro.ui.fragments.yoga.YogaFragment;
import com.android.medipro.ui.fragments.gym.GymFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthCentersFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView ivBack,ivYoga,ivGym,ivSpa;
    FragmentBeanClass fbc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_health_centers, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);
//        MenuActivity.llTopBar.setBackgroundResource(R.drawable.ad_common);
//        MenuActivity.ivMenuLines.setImageResource(R.drawable.left_arrow);
//        MenuActivity.tvPlaceName.setText("HEALTH CENTERS");
//        MenuActivity.tvPlaceName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.xlarge_text));
//        MenuActivity.tvPlaceName.setTypeface(null, Typeface.NORMAL);
//        MenuActivity.tvCityName.setVisibility(View.GONE);
//        MenuActivity.ivDownArrow.setVisibility(View.GONE);
//        MenuActivity.ivSearch.setVisibility(View.VISIBLE);
//        MenuActivity.ivCart.setVisibility(View.GONE);

        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        ivYoga=(ImageView)view.findViewById(R.id.iv_yoga);
        ivGym=(ImageView)view.findViewById(R.id.iv_gym);
        ivSpa=(ImageView)view.findViewById(R.id.iv_spa);
        onClick();

        return view;
    }
    private void onClick() {
        ivBack.setOnClickListener(this);
        ivYoga.setOnClickListener(this);
        ivGym.setOnClickListener(this);
        ivSpa.setOnClickListener(this);
        fbc=new FragmentBeanClass((AppCompatActivity) getActivity(),R.id.fl_container_main);

//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().popBackStack();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.iv_yoga:
                fbc.setFragment(new YogaFragment());
                break;
            case R.id.iv_gym:
                fbc.setFragment(new GymFragment());
                break;

            case R.id.iv_spa:
                fbc.setFragment(new SpaFragment());
                break;

        }
    }
}
