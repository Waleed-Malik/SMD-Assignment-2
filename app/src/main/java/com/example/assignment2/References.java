package com.example.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class References extends AppCompatActivity {

    private EditText edtRefereeName, edtRefereePosition, edtRefereeContact;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        edtRefereeName = findViewById(R.id.edtRefereeName);
        edtRefereePosition = findViewById(R.id.edtRefereePosition);
        edtRefereeContact = findViewById(R.id.edtRefereeContact);
        btnSave = findViewById(R.id.btnSaveReference);
        btnCancel = findViewById(R.id.btnCancelReference);

        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("referee_name", edtRefereeName.getText().toString().trim());
            intent.putExtra("referee_position", edtRefereePosition.getText().toString().trim());
            intent.putExtra("referee_contact", edtRefereeContact.getText().toString().trim());
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            edtRefereeName.setText("");
            edtRefereePosition.setText("");
            edtRefereeContact.setText("");
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
    }
}
