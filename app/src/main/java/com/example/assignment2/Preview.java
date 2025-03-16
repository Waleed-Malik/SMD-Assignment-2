package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Preview extends AppCompatActivity {

    private static final String TAG = "PreviewActivity";

    private ImageView imgProfile;
    private TextView txtName, txtEmail, txtPhone, txtSummary, txtEducation, txtExperience, txtCertifications, txtReferences;
    private Button btnShareCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        imgProfile = findViewById(R.id.imgProfile);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtSummary = findViewById(R.id.txtSummary);
        txtEducation = findViewById(R.id.txtEducation);
        txtExperience = findViewById(R.id.txtExperience);
        txtCertifications = findViewById(R.id.txtCertifications);
        txtReferences = findViewById(R.id.txtReferences);
        btnShareCV = findViewById(R.id.btnShareCV);

        if (imgProfile == null || txtName == null || txtEmail == null || txtPhone == null ||
                txtSummary == null || txtEducation == null || txtExperience == null ||
                txtCertifications == null || txtReferences == null || btnShareCV == null) {
            Log.e(TAG, "UI elements not found. Check activity_preview.xml for missing IDs.");
            return;
        }

        Intent intent = getIntent();
        if (intent != null) {
            txtName.setText(getIntentData(intent, "name", "N/A"));
            txtEmail.setText(getIntentData(intent, "email", "N/A"));
            txtPhone.setText(getIntentData(intent, "phone", "N/A"));
            txtSummary.setText(getIntentData(intent, "summary", "No summary provided."));
            txtEducation.setText(getIntentData(intent, "education", "No education details."));
            txtExperience.setText(getIntentData(intent, "experience", "No work experience."));
            txtCertifications.setText(getIntentData(intent, "certifications", "No certifications."));
            txtReferences.setText(getIntentData(intent, "references", "No references available."));

            String imageUriString = intent.getStringExtra("imageUri");
            if (imageUriString != null && !imageUriString.isEmpty()) {
                try {
                    Uri imageUri = Uri.parse(imageUriString);
                    getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);  // ✅ Request read permission
                    imgProfile.setImageURI(imageUri);
                } catch (Exception e) {
                    Log.e(TAG, "Error setting image URI", e);
                }
            }
        } else {
            Log.e(TAG, "Intent is null. No data received.");
        }

        btnShareCV.setOnClickListener(v -> shareCV());
    }

    private String getIntentData(Intent intent, String key, String defaultValue) {
        String value = intent.getStringExtra(key);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    private void shareCV() {
        String cvContent = "Name: " + txtName.getText().toString() + "\n" +
                "Email: " + txtEmail.getText().toString() + "\n" +
                "Phone: " + txtPhone.getText().toString() + "\n\n" +
                "Summary:\n" + txtSummary.getText().toString() + "\n\n" +
                "Education:\n" + txtEducation.getText().toString() + "\n\n" +
                "Experience:\n" + txtExperience.getText().toString() + "\n\n" +
                "Certifications:\n" + txtCertifications.getText().toString() + "\n\n" +
                "References:\n" + txtReferences.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My CV");
        shareIntent.putExtra(Intent.EXTRA_TEXT, cvContent);

        startActivity(Intent.createChooser(shareIntent, "Share CV via"));
    }
}
