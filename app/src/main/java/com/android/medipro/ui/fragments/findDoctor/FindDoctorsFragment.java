package com.android.medipro.ui.fragments.findDoctor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.medipro.ui.activity.main.MenuActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindDoctorsFragment extends Fragment {
    View view;
    ListView lvPhychiartist;
    SpecialistsAdapter specialistsAdapter;
    ImageView ivBack;
    Bundle bundle;
    TextView tvFindDoctorTitle;
    String keyVal;

    public FindDoctorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_find_doctors, container, false);
        MenuActivity.llTopBar.setVisibility(View.GONE);

        lvPhychiartist=(ListView)view.findViewById(R.id.lv_phychiartist_list);
        tvFindDoctorTitle = (TextView)view.findViewById(R.id.tv_find_doc_title);


        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        bundle = getArguments();
        if (bundle != null) {
            keyVal = bundle.getString("keyValue").toString();
            Log.e("value",keyVal);
            tvFindDoctorTitle.setText(keyVal);
        }
        specialistsAdapter=new SpecialistsAdapter(getActivity(),keyVal);
        lvPhychiartist.setAdapter(specialistsAdapter);
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



