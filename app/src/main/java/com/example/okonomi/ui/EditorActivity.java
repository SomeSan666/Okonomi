package com.example.okonomi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okonomi.R;

public class EditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        EditText editTitle =
                findViewById(R.id.editTitle);

        Button btnSave =
                findViewById(R.id.btnSave);
        btnSave.setOnClickListener(
                v -> {
                    String title =
                            editTitle.getText().toString();
                    /*
                    Toast.makeText(
                            this,
                            title,
                            Toast.LENGTH_SHORT
                    ).show();
                   */
                    Intent resultIntent = new Intent();

                    resultIntent.putExtra(
                            "binder_title",
                            title
                    );

                    setResult(RESULT_OK, resultIntent);

                    finish();
                });

    }
}