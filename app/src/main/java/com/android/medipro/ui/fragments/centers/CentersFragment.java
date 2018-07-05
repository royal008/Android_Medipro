package com.android.medipro.ui.fragments.centers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class CentersFragment extends Fragment {
    View view;
    ImageView ivBack;
    ListView lvYoga;
   CentersAdapter centersAdapter;
   Bundle bundle;
   TextView tvTitleCenter;
   String key;


    public CentersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_centers, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);


        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        lvYoga=(ListView)view.findViewById(R.id.lv_yoga_list);
        tvTitleCenter=(TextView)view.findViewById(R.id.tv_title_center);

        bundle = getArguments();
        if (bundle != null) {
            key = bundle.getString("keyValue").toString();
            tvTitleCenter.setText(key);
        }

        centersAdapter = new CentersAdapter(getActivity(),key);
        lvYoga.setAdapter(centersAdapter);
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
