package com.android.medipro.ui.fragments.insurance;


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
import com.android.medipro.ui.fragments.yoga.YogaFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class InsuranceFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView ivBack,ivInsurance1,ivInsurance2,ivInsurance3;
    FragmentBeanClass fbc;
    Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_insurance, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);
//        MenuActivity.llTopBar.setBackgroundResource(R.drawable.ad_common);
//        MenuActivity.ivMenuLines.setImageResource(R.drawable.left_arrow);
//        MenuActivity.tvPlaceName.setText("INSURANCE");
//        MenuActivity.tvPlaceName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.xlarge_text));
//        MenuActivity.tvPlaceName.setTypeface(null, Typeface.NORMAL);
//        MenuActivity.tvCityName.setVisibility(View.GONE);
//        MenuActivity.ivDownArrow.setVisibility(View.GONE);
//        MenuActivity.ivSearch.setVisibility(View.VISIBLE);
//        MenuActivity.ivCart.setVisibility(View.GONE);

        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        ivInsurance1=(ImageView)view.findViewById(R.id.iv_health_insurance1);
        ivInsurance2=(ImageView)view.findViewById(R.id.iv_health_insurance2);
        ivInsurance3=(ImageView)view.findViewById(R.id.iv_health_insurance3);
        fbc=new FragmentBeanClass((AppCompatActivity) getActivity(),R.id.fl_container_main);

        bundle = new Bundle();

        onClick();

        return view;
    }
    private void onClick() {
        ivBack.setOnClickListener(this);
        ivInsurance1.setOnClickListener(this);
        ivInsurance2.setOnClickListener(this);
        ivInsurance3.setOnClickListener(this);
//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().popBackStack();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()) {
         case R.id.iv_back:
             getFragmentManager().popBackStack();
             break;
         case R.id.iv_health_insurance1:
             bundle.putString("keyValue","INSURANCE");
             fbc.setFragment(new YogaFragment());
             fbc.getFragment().setArguments(bundle);
             break;
         case R.id.iv_health_insurance2:
             bundle.putString("keyValue","INSURANCE");
             fbc.setFragment(new YogaFragment());
             fbc.getFragment().setArguments(bundle);
             break;
         case R.id.iv_health_insurance3:
             bundle.putString("keyValue","INSURANCE");
             fbc.setFragment(new YogaFragment());
             fbc.getFragment().setArguments(bundle);
             break;
     }
    }
}
