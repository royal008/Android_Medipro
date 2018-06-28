package com.android.medipro.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.medipro.R;
import com.android.medipro.ui.activity.main.MenuActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MobileSignUpActivity extends AppCompatActivity {
    EditText etUserName, etMailId, etPassword, etContact;
    Spinner spGender, spBloodGrp, spMonth, spDate, spYear;
    String geners[] = {"Male", "Female"};
    String bloodGrups[] = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
    ArrayAdapter genderAdapter, bloodGroupAdapter, monthsAdapter, dateAdapter, yearsAdapter;
    ArrayList<String> alMonths, alDates, alYears;
    Button btnSignUp;
    ImageView iv_back_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_sign_up);
        genderAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, geners);
        bloodGroupAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, bloodGrups);
        alMonths = new ArrayList<String>();
        alDates = new ArrayList<String>();
        alYears = new ArrayList<String>();
        monthsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, alMonths);
        dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, alDates);
        yearsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, alYears);
        getView();
    }

    private void getView() {
        etUserName = (EditText) findViewById(R.id.et_username);
        etMailId = (EditText) findViewById(R.id.et_mail_id);
        etPassword = (EditText) findViewById(R.id.et_password);
        etContact = (EditText) findViewById(R.id.et_contact_number);
        spGender = (Spinner) findViewById(R.id.sp_gender);
        spBloodGrp = (Spinner) findViewById(R.id.sp_blood_grp);
        spMonth = (Spinner) findViewById(R.id.sp_month);
        spDate = (Spinner) findViewById(R.id.sp_date);
        spYear = (Spinner) findViewById(R.id.sp_year);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
        iv_back_arrow = (ImageView) findViewById(R.id.iv_goback_signup);

        spGender.setAdapter(genderAdapter);
        spBloodGrp.setAdapter(bloodGroupAdapter);


        for (int i = 1; i <= 12; i++) {
            alMonths.add(Integer.toString(i));
        }
        spMonth.setAdapter(monthsAdapter);

        if (!spMonth.getSelectedItem().toString().isEmpty()) {

            for (int i = 1; i <= 31; i++) {
                alDates.add(Integer.toString(i));
            }
            spDate.setAdapter(dateAdapter);
        }
        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 || position % 2 == 0) {
                    alDates.clear();
                    for (int i = 1; i <= 31; i++) {
                        alDates.add(Integer.toString(i));
                    }
                    spDate.setAdapter(dateAdapter);
                }
                if (position == 1) {
                    alDates.clear();
                    for (int i = 1; i <= 29; i++) {
                        alDates.add(Integer.toString(i));
                    }
                    ;
                    spDate.setAdapter(dateAdapter);
                }
//                if(position!=1 && position%2!=0){
//                    alDates.clear();
//                    for (int i = 1; i <= 30; i++) {
//                        alDates.add(Integer.toString(i));
//                    }
//                    spDate.setAdapter(dateAdapter);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            alYears.add(Integer.toString(i));
        }
        spYear.setAdapter(yearsAdapter);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MobileSignUpActivity.this, MenuActivity.class));
            }
        });

        iv_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in_login = new Intent(MobileSignUpActivity.this, MainActivity.class);
                startActivity(in_login);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in_login = new Intent(MobileSignUpActivity.this, MainActivity.class);
        startActivity(in_login);
        finish();
    }
}
