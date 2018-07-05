package com.android.medipro.ui.fragments.ayush;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.ui.activity.main.MenuActivity;

import com.android.medipro.ui.fragments.ayushList.AyushListFragment;
import com.android.medipro.ui.fragments.findDoctor.FindDoctorsFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AyushFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView ivBack,ivHomeopathy,ivAyurvedic,ivSiddha;
    FragmentBeanClass fbc;
    Bundle bundle;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ayush, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);
//        MenuActivity.llTopBar.setBackgroundResource(R.drawable.ad_common);
//        MenuActivity.ivMenuLines.setImageResource(R.drawable.left_arrow);
//        MenuActivity.tvPlaceName.setText("AYUSH");
//        MenuActivity.tvPlaceName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.xlarge_text));
//        MenuActivity.tvPlaceName.setTypeface(null, Typeface.NORMAL);
//        MenuActivity.tvCityName.setVisibility(View.GONE);
//        MenuActivity.ivDownArrow.setVisibility(View.GONE);
//        MenuActivity.ivSearch.setVisibility(View.GONE);
//        MenuActivity.ivCart.setVisibility(View.GONE);
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        ivHomeopathy=(ImageView)view.findViewById(R.id.iv_homeopathy);
        ivAyurvedic=(ImageView)view.findViewById(R.id.iv_ayurvedic);
        ivSiddha=(ImageView)view.findViewById(R.id.iv_siddha);
        fbc=new FragmentBeanClass((AppCompatActivity) getActivity(),R.id.fl_container_main);

        bundle = new Bundle();


        onClick();
        return view;
    }
    private void onClick() {
        ivBack.setOnClickListener(this);
        ivHomeopathy.setOnClickListener(this);
        ivAyurvedic.setOnClickListener(this);
        ivSiddha.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.iv_homeopathy:
                Log.e("tag","image clicked");
                bundle.putString("keyValue","HOMEOPATHY");
                fbc.setFragment(new AyushListFragment());
                fbc.getFragment().setArguments(bundle);
                break;
            case R.id.iv_ayurvedic:
                Log.e("tag","image clicked");
                bundle.putString("keyValue","AYURVEDIC");
                fbc.setFragment(new AyushListFragment());
                fbc.getFragment().setArguments(bundle);
                break;
            case R.id.iv_siddha:
                Log.e("tag","image clicked");
                bundle.putString("keyValue","SIDDHA");
                fbc.setFragment(new AyushListFragment());
                fbc.getFragment().setArguments(bundle);
                break;


        }

    }
}
