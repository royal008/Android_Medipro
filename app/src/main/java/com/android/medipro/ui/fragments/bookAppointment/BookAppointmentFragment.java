package com.android.medipro.ui.fragments.bookAppointment;


import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.ui.activity.main.MenuActivity;
import com.android.medipro.ui.fragments.cardiologist.CardiologistFragment;
import com.android.medipro.ui.fragments.dermatologists.DermatologistsFragment;
import com.android.medipro.ui.fragments.ENTSpecialist.ENTSpecialistFragment;
import com.android.medipro.ui.fragments.favoriteDoctor.FavoriteDoctorsFragment;
import com.android.medipro.ui.fragments.gastroenterologists.GastroenterologistsFragment;
import com.android.medipro.ui.fragments.gynaecologists.GynaecologistsFragment;
import com.android.medipro.ui.fragments.opthalmologist.OpthalmologistFragment;
import com.android.medipro.ui.fragments.phychiartist.PhychiartistFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookAppointmentFragment extends Fragment {
    View view;
    ListView lvFindDoctors;
    FindDoctorsAdapter findDoctorsAdapter;
    int specialistImgs[]={R.drawable.myfavoritedoctors,R.drawable.ayurvedic_specialist,R.drawable.dermatologists,R.drawable.cardiologist,
    R.drawable.chestspecialists,R.drawable.cosmeticsurgeons,R.drawable.dentists,R.drawable.diabetologists};
    String specialistNames[]={"My Favorite Doctors","Opthalmologist","Dermatologists","Cardiologist","Gastroenterologists",
            "Phychiartist", "Ear-Nose-Throat(ENT) Specialist","Gynaecologists"};
    FragmentBeanClass fbc;
    ImageView ivBack,ivSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_book_appointment, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);
//        MenuActivity.llTopBar.setBackgroundResource(R.drawable.finddoctors);
//        MenuActivity.ivMenuLines.setImageResource(R.drawable.left_arrow);
////        MenuActivity.tvPlaceName.setText(getResources().getString(R.string.find_doctors));
//        MenuActivity.tvPlaceName.setTextSize(0);
//        MenuActivity.tvPlaceName.setTypeface(null, Typeface.NORMAL);
//        MenuActivity.tvPlaceName.setText(getResources().getString(R.string.find_doctors));
////        MenuActivity.tvPlaceName.setText(Html.fromHtml(getResources().getString(R.string.find_doctors)));
//        MenuActivity.tvCityName.setVisibility(View.GONE);
//        MenuActivity.ivDownArrow.setVisibility(View.GONE);
//        MenuActivity.ivSearch.setVisibility(View.VISIBLE);
//        MenuActivity.ivCart.setVisibility(View.VISIBLE);
//        MenuActivity.ivCart.setImageResource(R.drawable.filter_white);

        fbc=new FragmentBeanClass((AppCompatActivity) getActivity(),R.id.fl_container_main);
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        ivSearch=(ImageView)view.findViewById(R.id.iv_search);
        findDoctorsAdapter=new FindDoctorsAdapter(getActivity(),specialistImgs,specialistNames);
        lvFindDoctors=(ListView)view.findViewById(R.id.lv_find_doctors);
        lvFindDoctors.setAdapter(findDoctorsAdapter);

        onClick();
        return view;
    }

    private void onClick() {
        lvFindDoctors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    fbc.setFragment(new FavoriteDoctorsFragment());
                }
                if(position==1){
                    fbc.setFragment(new OpthalmologistFragment());
                }
                if(position==2){
                    fbc.setFragment(new DermatologistsFragment());
                }
                if(position==3){
                    fbc.setFragment(new CardiologistFragment());
                }
                if(position==4){
                    fbc.setFragment(new GastroenterologistsFragment());
                }
                if(position==5){
                    fbc.setFragment(new PhychiartistFragment());
                }
                if(position==6){
                    fbc.setFragment(new ENTSpecialistFragment());
                }
                if(position==7){
                    fbc.setFragment(new GynaecologistsFragment());
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
ivSearch.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent searchIntent = new Intent((Intent.ACTION_SEARCH));
        if (Intent.ACTION_SEARCH.equals(searchIntent.getAction())) {
            String query = searchIntent.getStringExtra(SearchManager.QUERY);
            //doMySearch(query);
        }
    }
});
    }

}