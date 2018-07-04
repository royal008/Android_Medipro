package com.android.medipro.ui.fragments.healthInsurance3;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.medipro.R;
import com.android.medipro.ui.activity.main.MenuActivity;
import com.android.medipro.ui.fragments.bookTest.BookTestAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthInsuranceFragment3 extends Fragment {
    View view;
    ImageView ivBack;
    ListView lvInsurance3;
    BookTestAdapter bookTestAdapter;


    public HealthInsuranceFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_health_insurance_fragment3, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);


        bookTestAdapter = new BookTestAdapter(getActivity());
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        lvInsurance3=(ListView)view.findViewById(R.id.lv_insurance3_list);
        lvInsurance3.setAdapter(bookTestAdapter);
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
