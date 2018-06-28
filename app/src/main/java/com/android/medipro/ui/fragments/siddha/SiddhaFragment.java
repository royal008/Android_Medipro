package com.android.medipro.ui.fragments.siddha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.AyushMainAdapter;
import com.android.medipro.ui.activity.main.MenuActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SiddhaFragment extends Fragment {
    View view;
    ImageView ivBack;
    ListView lvSiddha;
    AyushMainAdapter ayushMainAdapter;

    public SiddhaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_siddha, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);

        ayushMainAdapter = new AyushMainAdapter(getActivity());
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        lvSiddha=(ListView)view.findViewById(R.id.lv_siddha_list);
        lvSiddha.setAdapter(ayushMainAdapter);
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