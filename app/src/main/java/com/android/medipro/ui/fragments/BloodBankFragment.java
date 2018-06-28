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
public class BloodBankFragment extends Fragment {
View view;
ImageView ivBack;
ListView lvBloodBank;
AyushMainAdapter ayushMainAdapter;

    public BloodBankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_blood_bank, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);


        ayushMainAdapter = new AyushMainAdapter(getActivity());
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        lvBloodBank=(ListView)view.findViewById(R.id.lv_blood_bank_list);
        lvBloodBank.setAdapter(ayushMainAdapter);
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
