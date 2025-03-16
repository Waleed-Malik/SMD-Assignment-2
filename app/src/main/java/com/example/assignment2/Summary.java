package com.example.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Summary extends AppCompatActivity {

    private EditText edtSummary;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        edtSummary = findViewById(R.id.edtSummary);
        btnSave = findViewById(R.id.btnSaveSummary);
        btnCancel = findViewById(R.id.btnCancelSummary);

        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("summary", edtSummary.getText().toString().trim());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            edtSummary.setText("");
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
    }
}
