package com.example.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Certifications extends AppCompatActivity {

    private EditText edtCertificationName, edtIssuingOrg, edtCompletionYear;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);

        edtCertificationName = findViewById(R.id.edtCertificationName);
        edtIssuingOrg = findViewById(R.id.edtIssuingOrg);
        edtCompletionYear = findViewById(R.id.edtCompletionYear);
        btnSave = findViewById(R.id.btnSaveCertification);
        btnCancel = findViewById(R.id.btnCancelCertification);

        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("certification_name", edtCertificationName.getText().toString().trim());
            intent.putExtra("issuing_org", edtIssuingOrg.getText().toString().trim());
            intent.putExtra("completion_year", edtCompletionYear.getText().toString().trim());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            edtCertificationName.setText("");
            edtIssuingOrg.setText("");
            edtCompletionYear.setText("");
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
    }
}
