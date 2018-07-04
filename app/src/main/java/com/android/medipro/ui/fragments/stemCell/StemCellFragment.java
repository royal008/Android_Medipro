package com.android.medipro.ui.fragments.stemCell;


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
public class StemCellFragment extends Fragment {
    View view;
    ImageView ivBack;
    ListView lvStemCell;
    BookTestAdapter bookTestAdapter;



    public StemCellFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_stem_cell, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);


        bookTestAdapter = new BookTestAdapter(getActivity());
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        lvStemCell=(ListView)view.findViewById(R.id.lv_stem_cell_list);
        lvStemCell.setAdapter(bookTestAdapter);
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
