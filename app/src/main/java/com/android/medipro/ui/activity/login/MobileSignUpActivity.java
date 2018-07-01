package com.android.medipro.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.medipro.R;
import com.android.medipro.custom_utils.FragmentBeanClass;
import com.android.medipro.ui.activity.main.MenuActivity;
import com.android.medipro.ui.fragments.home.HomeFragment;
import com.android.medipro.ui.fragments.shopping.ShoppingFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class MobileSignUpActivity extends AppCompatActivity {
    TextView tvPhone, tvTimer;
    EditText etOTPFirst,etOPTSecond,etOTPThird,etOPTFourth,etOTPFifth,etOTPSixth;


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

        tvTimer();

    }
    TextWatcher OTPTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String FirstOTP = etOTPFirst.getText().toString().trim();
            String SecOTP = etOPTSecond.getText().toString().trim();
            String ThirdtOTP = etOTPThird.getText().toString().trim();
            String FourthOTP = etOPTFourth.getText().toString().trim();
            String FifthOTP = etOTPFifth.getText().toString().trim();
            String SixthOTP = etOTPSixth.getText().toString().trim();

            if (!FirstOTP.equals("")&&!SecOTP.equals("")&&!ThirdtOTP.equals("")&&!FourthOTP.equals("")&&!FifthOTP.equals("")&&!SixthOTP.equals("")) {
                startActivity(new Intent(MobileSignUpActivity.this, MenuActivity.class));
                finish();

        }
        }


        @Override
        public void afterTextChanged(Editable s) {

        }

    };
    private void tvTimer() {
        int x = 30000;
        new CountDownTimer(x, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(+millisUntilFinished / 1000 + "sec");
            }

            public void onFinish() {
                tvTimer.setText("done!");
            }

        }.start();
    }


    private void getView() {

        tvTimer = (TextView) findViewById(R.id.tv_timer);
        tvPhone = (TextView) findViewById(R.id.tv_phone_number);
        etOTPFirst = (EditText)findViewById(R.id.et_opt_first_digit);
        etOPTSecond = (EditText)findViewById(R.id.et_opt_sec_digit);
        etOTPThird = (EditText)findViewById(R.id.et_opt_third_digit);
        etOPTFourth = (EditText)findViewById(R.id.et_opt_fourth_digit);
        etOTPFifth = (EditText)findViewById(R.id.et_opt_fifth_digit);
        etOTPSixth = (EditText)findViewById(R.id.et_opt_sixth_digit);
    }
}
