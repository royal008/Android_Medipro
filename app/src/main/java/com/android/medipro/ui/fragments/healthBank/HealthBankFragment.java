package com.android.medipro.ui.fragments.healthBank;


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
import com.android.medipro.ui.fragments.stemCell.StemCellFragment;
import com.android.medipro.ui.fragments.bloodBank.BloodBankFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthBankFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView ivBack,ivStemCell,ivBloodBank;
    FragmentBeanClass fbc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_health_bank, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);
//        MenuActivity.llTopBar.setBackgroundResource(R.drawable.ad_common);
//        MenuActivity.ivMenuLines.setImageResource(R.drawable.left_arrow);
//        MenuActivity.tvPlaceName.setText("HEALTH BANK");
//        MenuActivity.tvPlaceName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.xlarge_text));
//        MenuActivity.tvPlaceName.setTypeface(null, Typeface.NORMAL);
//        MenuActivity.tvCityName.setVisibility(View.GONE);
//        MenuActivity.ivDownArrow.setVisibility(View.GONE);
//        MenuActivity.ivSearch.setVisibility(View.VISIBLE);
//        MenuActivity.ivCart.setVisibility(View.GONE);

        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        ivStemCell=(ImageView)view.findViewById(R.id.iv_stem_cell);
        ivBloodBank=(ImageView)view.findViewById(R.id.iv_blood_bank) ;
        fbc=new FragmentBeanClass((AppCompatActivity) getActivity(),R.id.fl_container_main);

        onClick();

        return view;
    }
    private void onClick() {
        ivBack.setOnClickListener(this);
        ivBloodBank.setOnClickListener(this);
        ivStemCell.setOnClickListener(this);
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
            case R.id.iv_stem_cell:
                fbc.setFragment(new StemCellFragment());
                break;
            case R.id.iv_blood_bank:
                fbc.setFragment(new BloodBankFragment());
                break;
        }

    }
}
