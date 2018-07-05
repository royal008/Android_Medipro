package com.android.medipro.ui.fragments.ayushList;


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
import com.android.medipro.ui.fragments.findDoctor.SpecialistsAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class AyushListFragment extends Fragment {
    View view;
    ImageView ivBack;
    ListView lvHomeopathy;
    SpecialistsAdapter specialistsAdapter;
    String key;
   Bundle bundle;
    TextView tvTitle;
//    String keyHomeopathy,keyAyurvedic,keySiddha;
//    String[] keyValues;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ayush_list, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);

       // specialistsAdapter = new SpecialistsAdapter(getActivity(),);
        ivBack = (ImageView) view.findViewById(R.id.iv_back);
        lvHomeopathy = (ListView) view.findViewById(R.id.lv_homepathy_list);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);

        bundle = getArguments();
        if (bundle != null) {
             key = bundle.getString("keyValue").toString();
                     tvTitle.setText(key);
            }


            onClick();
        specialistsAdapter = new SpecialistsAdapter(getActivity(),key);
        lvHomeopathy.setAdapter(specialistsAdapter);
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


