package com.android.medipro.ui.fragments.gym;


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
import com.android.medipro.ui.fragments.bookTest.BookTestAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class GymFragment extends Fragment {
    View view;
    ImageView ivBack;
    ListView lvGym;
    BookTestAdapter bookTestAdapter;


    public GymFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_gym, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);


        bookTestAdapter = new BookTestAdapter(getActivity());
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        lvGym=(ListView)view.findViewById(R.id.lv_gym_list);
        lvGym.setAdapter(bookTestAdapter);
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
