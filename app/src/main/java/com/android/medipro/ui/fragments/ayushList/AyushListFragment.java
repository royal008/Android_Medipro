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


/**
 * A simple {@link Fragment} subclass.
 */
public class AyushListFragment extends Fragment {
    View view;
    ImageView ivBack;
    ListView lvHomeopathy;
    AyushListAdapter ayushMainAdapter;
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

        ayushMainAdapter = new AyushListAdapter(getActivity());
        ivBack = (ImageView) view.findViewById(R.id.iv_back);
        lvHomeopathy = (ListView) view.findViewById(R.id.lv_homepathy_list);
        lvHomeopathy.setAdapter(ayushMainAdapter);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);

        bundle = getArguments();
        if (bundle != null) {
            String key = bundle.getString("keyValue").toString();
                     tvTitle.setText(key);
            }


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


