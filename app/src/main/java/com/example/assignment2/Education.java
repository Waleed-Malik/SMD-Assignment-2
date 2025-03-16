package com.example.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Education extends AppCompatActivity {

    private EditText edtSchool, edtDegree, edtGradYear;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        edtSchool = findViewById(R.id.edtSchool);
        edtDegree = findViewById(R.id.edtDegree);
        edtGradYear = findViewById(R.id.edtGradYear);
        btnSave = findViewById(R.id.btnSaveEducation);
        btnCancel = findViewById(R.id.btnCancelEducation);

        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("school", edtSchool.getText().toString().trim());
            intent.putExtra("degree", edtDegree.getText().toString().trim());
            intent.putExtra("grad_year", edtGradYear.getText().toString().trim());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            edtSchool.setText("");
            edtDegree.setText("");
            edtGradYear.setText("");
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
    }
}
