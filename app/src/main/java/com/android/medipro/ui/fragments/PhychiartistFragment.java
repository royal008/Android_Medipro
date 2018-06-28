package com.android.medipro.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.SpecialistsAdapter;
import com.android.medipro.ui.activity.MenuActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhychiartistFragment extends Fragment {
    View view;
    ListView lvPhychiartist;
    SpecialistsAdapter specialistsAdapter;
    ImageView ivBack;

    public PhychiartistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_phychiartist, container, false);
        MenuActivity.llTopBar.setVisibility(View.GONE);

        specialistsAdapter=new SpecialistsAdapter(getActivity());
        lvPhychiartist=(ListView)view.findViewById(R.id.lv_phychiartist_list);
        lvPhychiartist.setAdapter(specialistsAdapter);

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



