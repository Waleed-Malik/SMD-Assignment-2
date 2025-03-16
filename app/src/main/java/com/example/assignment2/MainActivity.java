package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PROFILE_PICTURE = 1;
    private static final int REQUEST_CODE_PERSONAL_DETAILS = 2;
    private static final int REQUEST_CODE_SUMMARY = 3;
    private static final int REQUEST_CODE_EDUCATION = 4;
    private static final int REQUEST_CODE_EXPERIENCE = 5;
    private static final int REQUEST_CODE_CERTIFICATIONS = 6;
    private static final int REQUEST_CODE_REFERENCES = 7;

    private Button btnProfile, btnPersonalDetails, btnSummary, btnEducation, btnExperience, btnCertifications, btnReferences, btnPreview;
    private String name, email, phone, summary, education, experience, certifications, references;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProfile = findViewById(R.id.btnProfilePicture);
        btnPersonalDetails = findViewById(R.id.btnPersonalDetails);
        btnSummary = findViewById(R.id.btnSummary);
        btnEducation = findViewById(R.id.btnEducation);
        btnExperience = findViewById(R.id.btnExperience);
        btnCertifications = findViewById(R.id.btnCertifications);
        btnReferences = findViewById(R.id.btnReferences);
        btnPreview = findViewById(R.id.btnPreview);

        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfilePicture.class);
            startActivityForResult(intent, REQUEST_CODE_PROFILE_PICTURE);
        });

        btnPersonalDetails.setOnClickListener(v -> startActivityForResult(new Intent(MainActivity.this, PersonalDetails.class), REQUEST_CODE_PERSONAL_DETAILS));
        btnSummary.setOnClickListener(v -> startActivityForResult(new Intent(MainActivity.this, Summary.class), REQUEST_CODE_SUMMARY));
        btnEducation.setOnClickListener(v -> startActivityForResult(new Intent(MainActivity.this, Education.class), REQUEST_CODE_EDUCATION));
        btnExperience.setOnClickListener(v -> startActivityForResult(new Intent(MainActivity.this, Experience.class), REQUEST_CODE_EXPERIENCE));
        btnCertifications.setOnClickListener(v -> startActivityForResult(new Intent(MainActivity.this, Certifications.class), REQUEST_CODE_CERTIFICATIONS));
        btnReferences.setOnClickListener(v -> startActivityForResult(new Intent(MainActivity.this, References.class), REQUEST_CODE_REFERENCES));

        btnPreview.setOnClickListener(v -> {
            Intent previewIntent = new Intent(MainActivity.this, Preview.class);
            previewIntent.putExtra("name", name);
            previewIntent.putExtra("email", email);
            previewIntent.putExtra("phone", phone);
            previewIntent.putExtra("summary", summary);
            previewIntent.putExtra("education", education);
            previewIntent.putExtra("experience", experience);
            previewIntent.putExtra("certifications", certifications);
            previewIntent.putExtra("references", references);

            if (imageUri != null) {
                previewIntent.putExtra("imageUri", imageUri.toString());
            }

            startActivity(previewIntent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_CODE_PROFILE_PICTURE:
                    String imageUriString = data.getStringExtra("imageUri");
                    if (imageUriString != null) {
                        imageUri = Uri.parse(imageUriString);

                        getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    break;

                case REQUEST_CODE_PERSONAL_DETAILS:
                    name = data.getStringExtra("name");
                    email = data.getStringExtra("email");
                    phone = data.getStringExtra("phone");
                    break;

                case REQUEST_CODE_SUMMARY:
                    summary = data.getStringExtra("summary");
                    break;

                case REQUEST_CODE_EDUCATION:
                    String school = data.getStringExtra("school");
                    String degree = data.getStringExtra("degree");
                    String gradYear = data.getStringExtra("grad_year");


                    education = formatDetails(school, degree, gradYear);
                    break;

                case REQUEST_CODE_EXPERIENCE:
                    String company = data.getStringExtra("company");
                    String jobTitle = data.getStringExtra("job_title");
                    String workDuration = data.getStringExtra("work_duration");

                    experience = formatDetails(company, jobTitle, workDuration);
                    break;

                case REQUEST_CODE_CERTIFICATIONS:

                    String certName = data.getStringExtra("certification_name");
                    String issuingOrg = data.getStringExtra("issuing_org");
                    String completionYear = data.getStringExtra("completion_year");


                    certifications = formatDetails(certName, issuingOrg, completionYear);
                    break;

                case REQUEST_CODE_REFERENCES:

                    String refereeName = data.getStringExtra("referee_name");
                    String refereePosition = data.getStringExtra("referee_position");
                    String refereeContact = data.getStringExtra("referee_contact");


                    references = formatDetails(refereeName, refereePosition, refereeContact);
                    break;
            }
        }
    }

    private String formatDetails(String part1, String part2, String part3) {
        return (part1 != null ? part1 : "") + ", " +
                (part2 != null ? part2 : "") + ", " +
                (part3 != null ? part3 : "");
    }
}
