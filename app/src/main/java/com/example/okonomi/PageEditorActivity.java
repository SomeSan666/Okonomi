package com.example.okonomi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PageEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_page_editor
        );
        Toast.makeText(
                this,
                "画像追加予定",
                Toast.LENGTH_SHORT
        ).show();

        EditText editTitle =
                findViewById(R.id.editTitle);

        EditText editContent =
                findViewById(R.id.editContent);

        Button btnSave =
                findViewById(R.id.btnSave);

        Button btnImage =
                findViewById(R.id.btnImage);

        btnImage.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    "画像追加予定",
                    Toast.LENGTH_SHORT
            ).show();
        });

        btnSave.setOnClickListener(v -> {

            String title =
                    editTitle.getText().toString();

            String content =
                    editContent.getText().toString();

            Intent resultIntent =
                    new Intent();

            resultIntent.putExtra(
                    "page_title",
                    title
            );

            resultIntent.putExtra(
                    "page_content",
                    content
            );

            setResult(
                    RESULT_OK,
                    resultIntent
            );

            finish();

        });

    }
}