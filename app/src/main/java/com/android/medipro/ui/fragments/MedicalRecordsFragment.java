package com.android.medipro.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.MedicalRecordsAdapter;
import com.android.medipro.ui.activity.MenuActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicalRecordsFragment extends Fragment {
    View view;
    ListView lvMedicalRecords;
    ArrayList alDate,alMonth;
    MedicalRecordsAdapter medicalRecordsAdapter;
    ImageView ivBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_medical_records, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);

        alDate=new ArrayList();
        alMonth=new ArrayList();

        alDate.add("15");
        alDate.add("24");
        alDate.add("08");

        alMonth.add("NOV");
        alMonth.add("AUG");
        alMonth.add("AUG");

        medicalRecordsAdapter=new MedicalRecordsAdapter(getActivity(),alDate,alMonth);
        lvMedicalRecords=(ListView)view.findViewById(R.id.lv_medical_records);
        lvMedicalRecords.setAdapter(medicalRecordsAdapter);

        ivBack=(ImageView)view.findViewById(R.id.iv_back);

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
