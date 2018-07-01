package com.android.medipro.ui.activity.login;

import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.medipro.R;
import com.android.medipro.ui.activity.login.MobileSignUpActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etPhoneNumber;
    Button btnContinue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getView();
        etPhoneNumber.addTextChangedListener(PhoneNumberTextWatcher);
        onClick();


    }
TextWatcher PhoneNumberTextWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String PhoneNumber = etPhoneNumber.getText().toString().trim();
     Log.e("length","phone"+PhoneNumber.length());
        if (PhoneNumber.length() == 10) {
            btnContinue.setEnabled(true);
            btnContinue.setBackgroundColor(getResources().getColor(R.color.button_color_main));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

};

    private void getView() {
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        btnContinue=(Button)findViewById(R.id.btn_continue_main);

      }
private void onClick(){
    btnContinue.setOnClickListener(this);
}
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_continue_main:
                Intent i = new Intent(MainActivity.this,MobileSignUpActivity.class);
                String phone = etPhoneNumber.getText().toString();
                Log.e("phone","number"+phone);
                i.putExtra("PhoneNumber",phone);
                startActivity(i);
                finish();
                break;
        }
    }
}
