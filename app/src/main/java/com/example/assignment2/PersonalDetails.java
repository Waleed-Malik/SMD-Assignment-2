package com.example.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalDetails extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPhone;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("name", edtName.getText().toString().trim());
            intent.putExtra("email", edtEmail.getText().toString().trim());
            intent.putExtra("phone", edtPhone.getText().toString().trim());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            edtName.setText("");
            edtEmail.setText("");
            edtPhone.setText("");
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
    }
}
