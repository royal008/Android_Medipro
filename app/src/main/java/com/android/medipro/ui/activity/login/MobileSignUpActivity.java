package com.android.medipro.ui.activity.login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.medipro.R;
import com.android.medipro.ui.activity.main.MenuActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MobileSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvPhone;
   // ImageView ivModeEdit;
    Button btnContinue;
    EditText etOTPFirst,etOPTSecond,etOTPThird,etOPTFourth,etOTPFifth,etOTPSixth;
     String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_sign_up);

        getView();
        Intent intent = getIntent();
        tvPhone.setText(intent.getStringExtra("PhoneNumber"));

        etOTPFirst.addTextChangedListener(OTPTextWatcher);
        etOPTSecond.addTextChangedListener(OTPTextWatcher);
        etOTPThird.addTextChangedListener(OTPTextWatcher);
        etOPTFourth.addTextChangedListener(OTPTextWatcher);
        etOTPFifth.addTextChangedListener(OTPTextWatcher);
        etOTPSixth.addTextChangedListener(OTPTextWatcher);




       // tvTimer();
        onClick();

    }

    private void onClick() {
        //ivModeEdit.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
    }

    TextWatcher OTPTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            if(etOTPFirst.length()==1)
                {
                    etOPTSecond.requestFocus();
                }
                if(etOPTSecond.length()==1)
                {
                    etOTPThird.requestFocus();
                }
                if(etOTPThird.length()==1)
                {
                    etOPTFourth.requestFocus();
                }
                if(etOPTFourth.length()==1)
                {
                    etOTPFifth.requestFocus();
                }
             if(etOTPFifth.length()==1)
                {
                    etOTPSixth.requestFocus();

                }
                if (etOTPSixth.length()==1&&!etOTPFirst.equals("")&&!etOPTSecond.equals("")&&!etOTPThird.equals("")&&!etOPTFourth.equals("")&&!etOTPFifth.equals("")&&!etOTPSixth.equals("")) {
                    btnContinue.setEnabled(true);
                    btnContinue.setBackgroundColor(getResources().getColor(R.color.button_color_main));

                }

        }



        @Override
        public void afterTextChanged(Editable s) {

        }

    };
//    private void tvTimer() {
//        int x = 30000;
//        new CountDownTimer(x, 1000) {
//            public void onTick(long millisUntilFinished) {
//                tvTimer.setText(+millisUntilFinished / 1000 + "sec");
//            }
//
//            public void onFinish() {
//                tvTimer.setText("done!");
//            }
//
//        }.start();
//    }


    private void getView() {

       // tvTimer = (TextView) findViewById(R.id.tv_timer);
        tvPhone = (TextView) findViewById(R.id.tv_phone_number);
        etOTPFirst = (EditText)findViewById(R.id.et_opt_first_digit);
        etOPTSecond = (EditText)findViewById(R.id.et_opt_sec_digit);
        etOTPThird = (EditText)findViewById(R.id.et_opt_third_digit);
        etOPTFourth = (EditText)findViewById(R.id.et_opt_fourth_digit);
        etOTPFifth = (EditText)findViewById(R.id.et_opt_fifth_digit);
        etOTPSixth = (EditText)findViewById(R.id.et_opt_sixth_digit);
       // ivModeEdit=(ImageView)findViewById(R.id.iv_mode_edit);
        btnContinue=(Button)findViewById(R.id.btn_continue_signup);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.iv_mode_edit:
//                Intent i = new Intent(MobileSignUpActivity.this,MainActivity.class);
//                startActivity(i);
            case R.id.btn_continue_signup:

//                String FirstOTP = etOTPFirst.getText().toString().trim();
//                String SecOTP = etOPTSecond.getText().toString().trim();
//                String ThirdtOTP = etOTPThird.getText().toString().trim();
//                String FourthOTP = etOPTFourth.getText().toString().trim();
//                String FifthOTP = etOTPFifth.getText().toString().trim();
//                String SixthOTP = etOTPSixth.getText().toString().trim();
//
//                otp=FirstOTP+SecOTP+ThirdtOTP+FourthOTP+FifthOTP+SixthOTP;
//                Log.e("otp",otp);
//
//                verifyOtp();
              Intent intent = new Intent(MobileSignUpActivity.this,MenuActivity.class);
                startActivity(intent);
        }
    }
// code for opt verification
//    private void verifyOtp() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://13.232.102.36/api/v1/user/otp/verify";
//
//        StringRequest sr = new StringRequest(Request.Method.POST, url,
//
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//
//                        Log.e("response", s);
//
//                        try {
//
//                            JSONObject object = new JSONObject(s);
//                            Boolean success = object.getBoolean("success");
//                            String message = object.getString("message");
//
//                            if (success) {
//                                Log.e("successMsg", String.valueOf(success));
//                                Intent intent = new Intent(MobileSignUpActivity.this,MenuActivity.class);
//                                startActivity(intent);
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Log.e("Messages Flag", "" + e.toString());
//                        }
//                    }
//                },
//                new com.android.volley.Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Log.e("error", "error: " + error.toString());
//                    }
//                }) {
//            @SuppressLint("LongLogTag")
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("phone", tvPhone.getText().toString());
//                params.put("otp",otp);
//                return params;
//            }
//
//        };
//        queue.add(sr);
//
//    }
}
