package com.android.medipro.ui.fragments.doctorInfo;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.medipro.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorInfoFragment extends Fragment implements View.OnClickListener,OnMapReadyCallback {
    View view;
    ImageView ivBack,ivShare;
     TextView tvCallDoctor;
    //LatLng dip = new LatLng(12.555, 77.45);
    LatLng dip = new LatLng(12.555, 77.45);
    GoogleMap myMap;
    MapView mMapView;

    public DoctorInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_doctor_info, container, false);
        mMapView=(MapView)view.findViewById(R.id.map);
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        ivShare=(ImageView)view.findViewById(R.id.iv_share);
       // ivCallDoctor=(ImageView)view.findViewById(R.id.iv_);
         tvCallDoctor=(TextView)view.findViewById(R.id.tv_call_doctor);
        try {
            // rootView = inflater.inflate(R.layout.fragment, container, false);
            MapsInitializer.initialize(this.getActivity());
            //  mMapView = (MapView)View.findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        }
        catch (InflateException e){
            Log.e("TAG", "Inflate exception");
        }

        onClick();
//        SupportMapFragment map = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.fragment);
//        map.getMapAsync(this);

        return view;

    }
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState); mMapView.onSaveInstanceState(outState);
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(dip).title("Marathahalli").icon(BitmapDescriptorFactory.defaultMarker()));
        CameraPosition cameraPosition=new CameraPosition(dip,10,15,20);
//        tilt-directly facing the Earth
//        bearing-Direction that the camera is pointing in, in degrees clockwise from north.
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
    }
    private void onClick() {
        ivBack.setOnClickListener(this);
        tvCallDoctor.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        // tvCallDoctor.setOnClickListener(this);
//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().popBackStack();
//            }
//        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.tv_call_doctor:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0377778888"));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                getActivity().startActivity(callIntent);
                break;
            case R.id.iv_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String SharingBody ="Sharing Body";
                String SharingSub ="Sharing Sub";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,SharingSub);
                sharingIntent.putExtra(Intent.EXTRA_TEXT,SharingBody);
                startActivity(Intent.createChooser(sharingIntent,"Share via"));

        }

    }

}

