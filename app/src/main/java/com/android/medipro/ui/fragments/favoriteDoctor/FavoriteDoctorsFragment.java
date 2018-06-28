package com.android.medipro.ui.fragments.favoriteDoctor;


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
public class FavoriteDoctorsFragment extends Fragment {
    View view;
    ListView lvFavoriteDoctors;
    SpecialistsAdapter specialistsAdapter;
    ImageView ivBack;


    public FavoriteDoctorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorite_doctors, container, false);
        MenuActivity.llTopBar.setVisibility(View.GONE);

        specialistsAdapter=new SpecialistsAdapter(getActivity());
        lvFavoriteDoctors=(ListView)view.findViewById(R.id.lv_Favorite_doctors_list);
        lvFavoriteDoctors.setAdapter(specialistsAdapter);

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

