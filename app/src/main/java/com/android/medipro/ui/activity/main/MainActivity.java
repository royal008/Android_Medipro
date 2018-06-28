package com.android.medipro.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.medipro.R;
import com.android.medipro.ui.activity.MobileSignUpActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etPhoneNumber;
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getView();
    }

    private void getView() {
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        btnContinue=(Button)findViewById(R.id.btn_continue_main);
        btnContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_continue_main:

                if(etPhoneNumber.length()<10){
                    btnContinue.setEnabled(true);

                }
                startActivity(new Intent(this, MobileSignUpActivity.class));
                finish();
                break;
        }
    }
}