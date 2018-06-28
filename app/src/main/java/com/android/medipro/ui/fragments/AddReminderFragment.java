package com.android.medipro.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.ui.activity.MenuActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddReminderFragment extends Fragment {

    View view;
    ImageView ivBack;
    FragmentBeanClass fbc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_re_minder, container, false);
        MenuActivity.llTopBar.setVisibility(View.GONE);

        fbc=new FragmentBeanClass((AppCompatActivity) getActivity(), R.id.fl_container_main);
        ivBack=(ImageView)view.findViewById(R.id.iv_back);

        onClick();
        return view;
    }

    private void onClick() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbc.goBack();
//                getFragmentManager().popBackStack();

            }
        });
    }

}
