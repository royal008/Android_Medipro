package com.android.medipro.ui.fragments.babyCare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.medipro.R;
import com.android.medipro.ui.fragments.orderMedicines.OrderMedicineAdapter;
import com.android.medipro.ui.activity.main.MenuActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class BabyCareFragment extends Fragment {
    View view;
    ListView lvBabyCare;
    OrderMedicineAdapter orderMedicineAdapter;
    ImageView ivBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_baby_care, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);
//        MenuActivity.llTopBar.setBackgroundResource(R.color.red);
//        MenuActivity.ivMenuLines.setImageResource(R.drawable.left_arrow);
//        MenuActivity.tvPlaceName.setText("Baby Care");
//        MenuActivity.tvPlaceName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_text));
//        MenuActivity.tvPlaceName.setTypeface(null, Typeface.BOLD);
//        MenuActivity.tvCityName.setVisibility(View.GONE);
//        MenuActivity.ivDownArrow.setVisibility(View.GONE);
//        MenuActivity.ivSearch.setVisibility(View.VISIBLE);
//        MenuActivity.ivCart.setVisibility(View.VISIBLE);

        lvBabyCare=(ListView)view.findViewById(R.id.lv_baby_care);
        orderMedicineAdapter=new OrderMedicineAdapter(getActivity());
        lvBabyCare.setAdapter(orderMedicineAdapter);

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
