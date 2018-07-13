package com.android.medipro.ui.fragments.profileUpdate;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.medipro.R;
import com.android.medipro.ui.activity.main.MenuActivity;
import com.android.medipro.ui.fragments.uploadPrescription.UploadedDocumentsAdapter;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileUpdateFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView ivBack,ivPhoto;
    LinearLayout llCamera,llGallery;
    private static int Result_code = 1;
    private int Request_Read_Permission = 2;
    private static final int CAMERA_REQUEST = 1888;
    Bitmap image;
    Dialog ProfilePhotoDialog;


    public ProfileUpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_profile_update, container, false);
        MenuActivity.llTopBar.setVisibility(View.GONE);//to hide menu bar
       init();
        onClick();
        return view;

    }

    private void init() {
        ivBack=(ImageView)view.findViewById(R.id.iv_back);
        ivPhoto=(ImageView)view.findViewById(R.id.iv_user_profile);

    }

    private void onClick() {
        ivBack.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);


    }
private void uploadPhoto(){
    ProfilePhotoDialog=new Dialog(getActivity());
    ProfilePhotoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    ProfilePhotoDialog.setContentView(R.layout.upload_photo_layout);
    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    lp.copyFrom(ProfilePhotoDialog.getWindow().getAttributes());
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    lp.gravity = Gravity.END;

    ProfilePhotoDialog.getWindow().setAttributes(lp);
    ProfilePhotoDialog.show();
    llCamera=(LinearLayout) ProfilePhotoDialog.findViewById(R.id.ll_photo_camera);
    llCamera.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    });

    llGallery=(LinearLayout)ProfilePhotoDialog.findViewById(R.id.ll_photo_gallery);
    llGallery.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Request_Read_Permission);
            } else {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, Result_code);
            }


        }
    });
}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Request_Read_Permission) {
            Log.e("gallery", " gallery permission");
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, Result_code);
            }
        }}
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Result_code && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            image = BitmapFactory.decodeFile(String.valueOf(picturePath));
            ivPhoto.setImageBitmap(image);
            ProfilePhotoDialog.dismiss();
        }

        else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            image = (Bitmap) data.getExtras().get("data");
            ivPhoto.setImageBitmap(image);
            ProfilePhotoDialog.dismiss();
            Log.e("camera","image added");
        }

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.iv_user_profile:
                uploadPhoto();

        }

    }
}

