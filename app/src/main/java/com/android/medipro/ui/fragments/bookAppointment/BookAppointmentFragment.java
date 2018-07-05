package com.android.medipro.ui.fragments.bookAppointment;


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
import com.android.medipro.ui.fragments.findDoctor.FindDoctorsFragment;


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
    Bundle bundle;
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
        bundle = new Bundle();

        onClick();
        return view;
    }

    private void onClick() {
        lvFindDoctors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    bundle.putString("keyValue","My Favorite Doctors");
                    fbc.setFragment(new FindDoctorsFragment());
                    fbc.getFragment().setArguments(bundle);
                }
                if(position==1){
                    bundle.putString("keyValue","Opthalmologist");
                    fbc.setFragment(new FindDoctorsFragment());
                    fbc.getFragment().setArguments(bundle);
                }
                if(position==2){
                    bundle.putString("keyValue","Dermtologists");
                    fbc.setFragment(new FindDoctorsFragment());
                    fbc.getFragment().setArguments(bundle);
                }
                if(position==3){
                    bundle.putString("keyValue","Cardiologist");
                    fbc.setFragment(new FindDoctorsFragment());
                    fbc.getFragment().setArguments(bundle);
                }
                if(position==4){
                    bundle.putString("keyValue","Gastroenterologists");
                    fbc.setFragment(new FindDoctorsFragment());
                    fbc.getFragment().setArguments(bundle);
                }
                if(position==5){
                    bundle.putString("keyValue","Phychiartist");
                    fbc.setFragment(new FindDoctorsFragment());
                    fbc.getFragment().setArguments(bundle);
                }
                if(position==6){
                    bundle.putString("keyValue","ENT Specialist");
                    fbc.setFragment(new FindDoctorsFragment());
                    fbc.getFragment().setArguments(bundle);
                }
                if(position==7){
                    bundle.putString("keyValue","Gynaecologists");
                    fbc.setFragment(new FindDoctorsFragment());
                    fbc.getFragment().setArguments(bundle);
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }

}
