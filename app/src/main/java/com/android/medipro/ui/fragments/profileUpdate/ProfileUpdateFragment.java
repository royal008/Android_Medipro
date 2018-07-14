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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    LinearLayout llCamera,llGallery,llName,llPhone,llEmail,llGender,llMaritalStatus;
    private static int Result_code = 1;
    private int Request_Read_Permission = 2;
    private static final int CAMERA_REQUEST = 1888;
    Bitmap image;
    Dialog ProfilePhotoDialog,AddNameDialog,AddNumberDialog,AddEmailDialog,AddGenderDialog,AddMaritalStatus;
    EditText etName,etNumber;
    TextView tvName,tvUpdateName,tvPhone,tvEmail,tvGender,tvMaritalStatus;


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
        llName=(LinearLayout)view.findViewById(R.id.ll_name);
        tvName=(TextView)view.findViewById(R.id.tv_profile_name);
        tvUpdateName=(TextView)view.findViewById(R.id.tv_name_update);
        llPhone=(LinearLayout)view.findViewById(R.id.ll_phone);
        tvPhone=(TextView)view.findViewById(R.id.tv_phone);
        llEmail=(LinearLayout)view.findViewById(R.id.ll_email);
        tvEmail=(TextView)view.findViewById(R.id.tv_email);
        llGender=(LinearLayout)view.findViewById(R.id.ll_gender);
        tvGender=(TextView)view.findViewById(R.id.tv_gender);
        llMaritalStatus=(LinearLayout)view.findViewById(R.id.ll_marital_status);
        tvMaritalStatus=(TextView)view.findViewById(R.id.tv_marital_status);
    }

    private void onClick() {
        ivBack.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        llName.setOnClickListener(this);
        llPhone.setOnClickListener(this);
        llEmail.setOnClickListener(this);
        llGender.setOnClickListener(this);
        llMaritalStatus.setOnClickListener(this);


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


private void addName(){
    AddNameDialog=new Dialog(getActivity());
    AddNameDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    AddNameDialog.setContentView(R.layout.profile_name_layout);
    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    lp.copyFrom( AddNameDialog.getWindow().getAttributes());
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    lp.gravity = Gravity.CENTER;
    AddNameDialog.getWindow().setAttributes(lp);
    AddNameDialog.show();

    Button btnName =(Button)AddNameDialog.findViewById(R.id.btn_profile_name);
   btnName.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           etName = (EditText)AddNameDialog.findViewById(R.id.et_name);
           String name= etName.getText().toString();
           tvName.setText(name);
           tvUpdateName.setText(name);
           AddNameDialog.dismiss();
       }
   });

}

    private void addNumber(){
        AddNumberDialog=new Dialog(getActivity());
        AddNumberDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddNumberDialog.setContentView(R.layout.profile_number_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom( AddNumberDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        AddNumberDialog.getWindow().setAttributes(lp);
        AddNumberDialog.show();

        Button btnNumber =(Button)AddNumberDialog.findViewById(R.id.btn_profile_number);
        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNumber = (EditText)AddNumberDialog.findViewById(R.id.et_number);
                String number= etNumber.getText().toString();
                tvPhone.setText(number);
                AddNumberDialog.dismiss();
            }
        });

    }

    private void addEmail(){
        AddEmailDialog=new Dialog(getActivity());
        AddEmailDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddEmailDialog.setContentView(R.layout.profile_email_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom( AddEmailDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        AddEmailDialog.getWindow().setAttributes(lp);
        AddEmailDialog.show();

        Button btnEmail =(Button)AddEmailDialog.findViewById(R.id.btn_profile_email);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EditText etEmail = (EditText)AddEmailDialog.findViewById(R.id.et_email);
                String email= etEmail.getText().toString();
                tvEmail.setText(email);
                AddEmailDialog.dismiss();
            }
        });

    }

    private void addGender(){
        AddGenderDialog=new Dialog(getActivity());
        AddGenderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddGenderDialog.setContentView(R.layout.profile_gender_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom( AddGenderDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        AddGenderDialog.getWindow().setAttributes(lp);
        AddGenderDialog.show();

        Button btnMale =(Button)AddGenderDialog.findViewById(R.id.btn_gender_male);
        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvGender.setText("Male");
                AddGenderDialog.dismiss();
            }
        });

        Button btnFemale =(Button)AddGenderDialog.findViewById(R.id.btn_gender_female);
        btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvGender.setText("Female");
                AddGenderDialog.dismiss();
            }
        });

        Button btnOther =(Button)AddGenderDialog.findViewById(R.id.btn_gender_other);
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvGender.setText("Other");
                AddGenderDialog.dismiss();
            }
        });

    }

    private void addMaritalStatus(){
        AddMaritalStatus=new Dialog(getActivity());
        AddMaritalStatus.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddMaritalStatus.setContentView(R.layout.profile_marital_status_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom( AddMaritalStatus.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        AddMaritalStatus.getWindow().setAttributes(lp);
        AddMaritalStatus.show();

        Button btnSingle =(Button)AddMaritalStatus.findViewById(R.id.btn_single);
        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMaritalStatus.setText("Single");
                AddMaritalStatus.dismiss();
            }
        });

        Button btnMarried =(Button)AddMaritalStatus.findViewById(R.id.btn_married);
        btnMarried.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMaritalStatus.setText("Married");
                AddMaritalStatus.dismiss();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.iv_user_profile:
                uploadPhoto();
                break;
            case  R.id.ll_name:
                addName();
                break;
            case R.id.ll_phone:
                addNumber();
                break;
            case R.id.ll_email:
                addEmail();
                break;
            case R.id.ll_gender:
                addGender();
                break;
            case R.id.ll_marital_status:
                addMaritalStatus();
                break;


        }

    }
}

