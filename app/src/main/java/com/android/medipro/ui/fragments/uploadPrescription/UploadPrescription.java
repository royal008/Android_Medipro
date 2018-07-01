package com.android.medipro.ui.fragments.uploadPrescription;


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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.medipro.R;
import com.android.medipro.ui.activity.main.MenuActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadPrescription extends Fragment implements View.OnClickListener {
    View view;
    GridView gvUploadedDocuments;
    TextView tvContinue, tvGallery, tvCamera;
    UploadedDocumentsAdapter uploadedDocumentsAdapter;
    ImageView ivBack, ivClose;
    private static int Result_code = 1;
    private int Request_Read_Permission = 2;
    private static final int CAMERA_REQUEST = 1888;
    private static final int GALLERY_REQUEST = 100;
    String IMAGE_PATH;
    ArrayList<Bitmap> uploadArrary;
    Boolean picSelected;
    Bitmap image;
  //  int closeBtn;
  private Uri mCapturedImageURI;
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_upload_prescription, container, false);

        MenuActivity.llTopBar.setVisibility(View.GONE);
//        MenuActivity.llTopBar.setBackgroundResource(R.drawable.ad_common);
//        MenuActivity.ivMenuLines.setImageResource(R.drawable.left_arrow);
//        MenuActivity.tvPlaceName.setText("UPLOAD YOUR "+"\n"+"PRESCRIPTION");
//        MenuActivity.tvPlaceName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.xlarge_text));
//        MenuActivity.tvPlaceName.setTypeface(null, Typeface.NORMAL);
//        MenuActivity.tvCityName.setVisibility(View.GONE);
//        MenuActivity.ivDownArrow.setVisibility(View.GONE);
//        MenuActivity.ivSearch.setVisibility(View.GONE);
//        MenuActivity.ivCart.setVisibility(View.GONE);

       // uploadArrary = new ArrayList<Bitmap>();
        uploadArrary = new ArrayList();
       // closeBtnArray = new ArrayList<>();
     //   uploadedDocumentsAdapter = new UploadedDocumentsAdapter(getActivity(),uploadArrary);
        gvUploadedDocuments = (GridView) view.findViewById(R.id.gv_uploaded_documents);
     //   gvUploadedDocuments.setAdapter(uploadedDocumentsAdapter);

        tvContinue = (TextView) view.findViewById(R.id.tv_continue_up);
        ivBack = (ImageView) view.findViewById(R.id.iv_back);
        tvGallery = (TextView) view.findViewById(R.id.tv_gallery);
        ivClose = (ImageView) view.findViewById(R.id.iv_close);
        tvCamera = (TextView) view.findViewById(R.id.tv_camera);
        OnClick();
        // return view;
//        listOfImagesPath = null;
//        listOfImagesPath = RetriveCapturedImagePath();
//        if (listOfImagesPath != null) {
//            gvUploadedDocuments.setAdapter(new UploadedDocumentsAdapter(getActivity(), listOfImagesPath,"camera"));
//        }
        return view;
    }

    private void OnClick() {
        tvGallery.setOnClickListener(this);
        tvContinue.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvCamera.setOnClickListener(this);


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
        }


//        if(requestCode==CAMERA_REQUEST) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                launchCamera();
//            }
//        }
//        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
//           if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.e("camera","camera permission");
//                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
//                Intent cameraIntent = new
//                        Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//           } else {
//              Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
//           }
//
//        }
    }


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
            uploadArrary.add(image);
//            closeBtn=R.drawable.closewithcircle;
//            closeBtnArray.add(closeBtn);
            Log.e("gallery","image added");
                }

        else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            image = (Bitmap) data.getExtras().get("data");
           uploadArrary.add(image);
//            closeBtn=R.drawable.closewithcircle;
//            closeBtnArray.add(closeBtn);
            Log.e("camera","image added");
        }
//          else if (resultCode == Activity.RESULT_CANCELED) {
//            picSelected = false;
//        }
        uploadedDocumentsAdapter = new UploadedDocumentsAdapter(getActivity(),uploadArrary);
       // gvUploadedDocuments = (GridView) view.findViewById(R.id.gv_uploaded_documents);
        gvUploadedDocuments.setAdapter(uploadedDocumentsAdapter);
        }






    private void showSpecifyMedicinesDialog() {
        final Dialog specifyMedicinesDialog = new Dialog(getActivity());
        specifyMedicinesDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        specifyMedicinesDialog.setContentView(R.layout.specify_medicine_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(specifyMedicinesDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;

        specifyMedicinesDialog.getWindow().setAttributes(lp);
        specifyMedicinesDialog.show();
        ImageView ivCancel = (ImageView) specifyMedicinesDialog.findViewById(R.id.iv_cancel);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                specifyMedicinesDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_continue_up:
                showSpecifyMedicinesDialog();
                break;
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.tv_gallery:
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Request_Read_Permission);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, Result_code);
                }

                break;
            case R.id.tv_camera:

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;
        }}




}






