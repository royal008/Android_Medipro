package com.android.medipro.ui.fragments.profileUpdate;


import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.medipro.ui.activity.main.MenuActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileUpdateFragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView ivBack,ivPhoto;
    LinearLayout llCamera,llGallery,llName,llPhone,llEmail,llGender,llMaritalStatus,llBloodGroup,llWeight,llDOB,llHeight;
    private static int Result_code = 1;
    private int Request_Read_Permission = 2;
    private static final int CAMERA_REQUEST = 1888;
    Bitmap image;
    Dialog ProfilePhotoDialog,AddNameDialog,AddNumberDialog,AddEmailDialog,AddGenderDialog, AddMaritalStatus,AddBloodGroup,AddWeightDialog,AddDOB,AddHeight;
    EditText etName,etNumber,etWeight;
    Spinner feetSpinner,inchesSpinner;
    TextView tvName,tvUpdateName,tvPhone,tvEmail,tvGender,tvMaritalStatus,tvBloodGroup,tvWeight,tvDOB,tvHeight,tvRemovePhoto;
    ArrayAdapter feetAdapter, inchesAdapter;
    ArrayList<String> alfeet,alinches;



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
        llBloodGroup=(LinearLayout)view.findViewById(R.id.ll_blood_group);
        tvBloodGroup=(TextView)view.findViewById(R.id.tv_blood_group);
        llWeight=(LinearLayout)view.findViewById(R.id.ll_weight);
        tvWeight=(TextView)view.findViewById(R.id.tv_weight);
        llDOB=(LinearLayout)view.findViewById(R.id.ll_dob);
        tvDOB=(TextView)view.findViewById(R.id.tv_dob);
        llHeight=(LinearLayout)view.findViewById(R.id.ll_height);
        tvHeight=(TextView)view.findViewById(R.id.tv_height);

    }


    private void onClick() {
        ivBack.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        llName.setOnClickListener(this);
        llPhone.setOnClickListener(this);
        llEmail.setOnClickListener(this);
        llGender.setOnClickListener(this);
        llMaritalStatus.setOnClickListener(this);
        llBloodGroup.setOnClickListener(this);
        llWeight.setOnClickListener(this);
        llDOB.setOnClickListener(this);
        llHeight.setOnClickListener(this);



    }
private void uploadPhoto(){
    ProfilePhotoDialog=new Dialog(getActivity());
    ProfilePhotoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    ProfilePhotoDialog.setContentView(R.layout.profile_photo_layout);
    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    lp.copyFrom(ProfilePhotoDialog.getWindow().getAttributes());
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    lp.gravity = Gravity.END;

    ProfilePhotoDialog.getWindow().setAttributes(lp);
    ProfilePhotoDialog.show();
    tvRemovePhoto=(TextView)ProfilePhotoDialog.findViewById(R.id.tv_remove);
    tvRemovePhoto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         ivPhoto.setImageResource(R.drawable.user_profile_pic);
         ProfilePhotoDialog.dismiss();
        }
    });
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
                tvPhone.setText("+91-"+number);
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

    private void addBloodGroup(){
        AddBloodGroup=new Dialog(getActivity());
        AddBloodGroup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddBloodGroup.setContentView(R.layout.profile_blood_group_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom( AddBloodGroup.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        AddBloodGroup.getWindow().setAttributes(lp);
        AddBloodGroup.show();

        Button btnA =(Button)AddBloodGroup.findViewById(R.id.btn_group_a);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBloodGroup.setText("A+");
                AddBloodGroup.dismiss();
            }
        });

        Button btnNegA =(Button)AddBloodGroup.findViewById(R.id.btn_group_A);
        btnNegA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBloodGroup.setText("A-");
                AddBloodGroup.dismiss();
            }
        });

        Button btnB =(Button)AddBloodGroup.findViewById(R.id.btn_group_b);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBloodGroup.setText("B+");
                AddBloodGroup.dismiss();
            }
        });


        Button btnNegB =(Button)AddBloodGroup.findViewById(R.id.btn_group_negB);
        btnNegB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBloodGroup.setText("B-");
                AddBloodGroup.dismiss();
            }
        });


        Button btnO =(Button)AddBloodGroup.findViewById(R.id.btn_group_o);
        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBloodGroup.setText("O+");
                AddBloodGroup.dismiss();
            }
        });


        Button btnNegO =(Button)AddBloodGroup.findViewById(R.id.btn_group_negO);
        btnNegO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBloodGroup.setText("O-");
                AddBloodGroup.dismiss();
            }
        });

        Button btnAB =(Button)AddBloodGroup.findViewById(R.id.btn_group_ab);
        btnAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBloodGroup.setText("AB+");
                AddBloodGroup.dismiss();
            }
        });

        Button btnNegAB =(Button)AddBloodGroup.findViewById(R.id.btn_group_negAB);
        btnNegAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBloodGroup.setText("AB-");
                AddBloodGroup.dismiss();
            }
        });

    }

    private void addWeight(){
        AddWeightDialog=new Dialog(getActivity());
        AddWeightDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddWeightDialog.setContentView(R.layout.profile_weight_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom( AddWeightDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        AddWeightDialog.getWindow().setAttributes(lp);
        AddWeightDialog.show();

        Button btnWeight =(Button)AddWeightDialog.findViewById(R.id.btn_profile_weigh);
        btnWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etWeight = (EditText)AddWeightDialog.findViewById(R.id.et_weigh);
                String weight= etWeight.getText().toString();
                tvWeight.setText(weight+"kg");
                AddWeightDialog.dismiss();
            }
        });

    }

    private void addDOB() {
        AddDOB = new Dialog(getActivity());
        AddDOB.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddDOB.setContentView(R.layout.profile_dob_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(AddDOB.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        AddDOB.getWindow().setAttributes(lp);
        AddDOB.show();

        Button btnDob = (Button) AddDOB.findViewById(R.id.btn_dob);
        btnDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogfragment = new DatePickerDialogClass();

                dialogfragment.show(getActivity().getFragmentManager(), "Date Picker Dialog");
                AddDOB.dismiss();

            }
        });

    }

    public static class DatePickerDialogClass extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_DEVICE_DEFAULT_DARK,this,year,month,day);
            datepickerdialog.getDatePicker().setMaxDate(new Date().getTime());

            return datepickerdialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){

            TextView textview = (TextView)getActivity().findViewById(R.id.tv_dob);

            textview.setText(day + "/" + (month+1) + "/" + year);
        }

    }

    private void addHeight(){
        AddHeight=new Dialog(getActivity());
        AddHeight.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AddHeight.setContentView(R.layout.profile_height_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom( AddHeight.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        AddHeight.getWindow().setAttributes(lp);
        AddHeight.show();


                feetSpinner = (Spinner)AddHeight.findViewById(R.id.sp_feet);
                inchesSpinner= (Spinner)AddHeight.findViewById(R.id.sp_inches);
                alfeet = new ArrayList<String>();
                alinches = new ArrayList<String>();
                feetAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, alfeet);
                inchesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, alinches);

                for (int i = 0; i <= 9; i++) {
                    alfeet.add(Integer.toString(i));
                }
                feetSpinner.setAdapter(feetAdapter);

                for (int i = 0; i <= 11; i++) {
                    alinches.add(Integer.toString(i));
                }
                inchesSpinner.setAdapter(inchesAdapter);

        Button btnHeight=(Button)AddHeight.findViewById(R.id.btn_profile_height);
        btnHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feet = feetSpinner.getSelectedItem().toString();
                String inches = inchesSpinner.getSelectedItem().toString();
                tvHeight.setText(feet+"\t"+"ft"+"\t"+inches+"\t"+"in");
                AddHeight.dismiss();
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
            case R.id.ll_blood_group:
                addBloodGroup();
                break;
            case R.id.ll_weight:
                addWeight();
                break;
            case R.id.ll_dob:
                addDOB();
                break;
            case R.id.ll_height:
                addHeight();
                break;


        }

    }
}

