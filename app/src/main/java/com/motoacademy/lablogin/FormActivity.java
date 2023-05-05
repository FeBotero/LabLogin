package com.motoacademy.lablogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_FNAME = "package com.motoacademy.lablogin.FNAME";
    public static final String EXTRA_LNAME = "package com.motoacademy.lablogin.LNAME";


    private EditText mFname;
    private  EditText mLname;
    private Button mButtonSave;
    private Button mButtonDisplay;
    private Button mButtonHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomulario);

        mFname = findViewById(R.id.tvFirstName);
        mLname = findViewById(R.id.tvLastName);
        mButtonSave = findViewById(R.id.btSave);
        mButtonDisplay = findViewById(R.id.btDisplay);
        mButtonHome = findViewById(R.id.btHome);

        mButtonSave.setEnabled(false);
        mLname.setEnabled(false);

        mFname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mLname.setEnabled(!editable.toString().isEmpty());
            }
        });
        mLname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mButtonSave.setEnabled(!editable.toString().isEmpty());
            }
        });
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = mFname.getText().toString();
                String lName = mLname.getText().toString();

                MyDataBaseHelper myDB = new MyDataBaseHelper(FormActivity.this);
                myDB.addPerson(fName.trim(),lName.trim());
            }
        });

        mButtonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(FormActivity.this);

                Intent intent = new Intent(FormActivity.this,DisplayActivity.class);
                startActivity(intent);
            }
        });

        mButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FormActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}