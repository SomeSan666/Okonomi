package com.example.okonomi;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BinderActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> pageLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        Button btnAddPage =
                findViewById(R.id.btnAddPage);
        TextView txtPageContent =
                findViewById(R.id.txtPageContent);
        pageLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {

                            if(result.getResultCode() == RESULT_OK){

                                Intent data = result.getData();

                                if(data != null){

                                    String title =
                                            data.getStringExtra(
                                                    "page_title"
                                            );

                                    String content =
                                            data.getStringExtra(
                                                    "page_content"
                                            );
                                    txtPageContent.setText(
                                            title + "\n\n" + content
                                    );

                                }

                            }
                        }
                );
        btnAddPage.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            BinderActivity.this,
                            PageEditorActivity.class
                    );

            pageLauncher.launch(intent);

        });
    }
}