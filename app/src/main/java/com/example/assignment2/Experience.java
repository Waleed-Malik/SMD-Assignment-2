package com.example.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Experience extends AppCompatActivity {

    private EditText edtCompany, edtJobTitle, edtWorkDuration;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        edtCompany = findViewById(R.id.edtCompany);
        edtJobTitle = findViewById(R.id.edtJobTitle);
        edtWorkDuration = findViewById(R.id.edtWorkDuration);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("company", edtCompany.getText().toString().trim());
            intent.putExtra("job_title", edtJobTitle.getText().toString().trim());
            intent.putExtra("work_duration", edtWorkDuration.getText().toString().trim());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            edtCompany.setText("");
            edtJobTitle.setText("");
            edtWorkDuration.setText("");
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
    }
}
