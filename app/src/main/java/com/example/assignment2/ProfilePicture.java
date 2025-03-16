package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class ProfilePicture extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView profileImage;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);

        profileImage = findViewById(R.id.profile_image);
        ImageButton pickImageButton = findViewById(R.id.btn_pick_image);
        Button saveButton = findViewById(R.id.btn_save);
        Button cancelButton = findViewById(R.id.btn_cancel);

        pickImageButton.setOnClickListener(v -> openGallery());

        saveButton.setOnClickListener(v -> {
            if (imageUri != null) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("imageUri", imageUri.toString());

                resultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                setResult(RESULT_OK, resultIntent);
            }
            finish();
        });


        cancelButton.setOnClickListener(v -> finish());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            loadProfileImage(imageUri);
        }
    }

    private void loadProfileImage(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            profileImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
