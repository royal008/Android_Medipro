package com.android.medipro.ui.fragments.ENTSpecialist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.SpecialistsAdapter;
import com.android.medipro.ui.activity.main.MenuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ENTSpecialistFragment extends Fragment {
    View view;
    ListView lvENTSpecialist;
    SpecialistsAdapter specialistsAdapter;
    ImageView ivBack;

    public ENTSpecialistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_entspecialist, container, false);
        MenuActivity.llTopBar.setVisibility(View.GONE);

        specialistsAdapter=new SpecialistsAdapter(getActivity());
        lvENTSpecialist=(ListView)view.findViewById(R.id.lv_ent_specialist_list);
        lvENTSpecialist.setAdapter(specialistsAdapter);

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
