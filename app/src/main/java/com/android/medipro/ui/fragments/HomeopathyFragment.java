package com.android.medipro.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.AyushMainAdapter;
import com.android.medipro.ui.activity.MenuActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeopathyFragment extends Fragment {
    View view;
    ImageView ivBack;
    ListView lvHomeopathy;
    AyushMainAdapter ayushMainAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_homeopathy, container, false);

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
        ayushMainAdapter =new AyushMainAdapter(getActivity());
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        lvHomeopathy=(ListView)view.findViewById(R.id.lv_homepathy_list);
        lvHomeopathy.setAdapter(ayushMainAdapter);
        onClick();
        return view;

    }

    private void onClick() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
    }


