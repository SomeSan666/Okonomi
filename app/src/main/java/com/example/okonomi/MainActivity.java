package com.example.okonomi;
import com.example.okonomi.adapter.BinderAdapter;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BinderAdapter adapter;
    private ActivityResultLauncher<Intent> editorLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView =
                findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(
                new GridLayoutManager(this, 2)
        );


        adapter = new BinderAdapter();
        recyclerView.setAdapter(adapter);

        editorLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {

                            if(result.getResultCode() == RESULT_OK){

                                Intent data = result.getData();

                                if(data != null){

                                    String title =
                                            data.getStringExtra(
                                                    "binder_title"
                                            );

                                    Toast.makeText(
                                            this,
                                            "受信:"+title,
                                            Toast.LENGTH_SHORT
                                    ).show();
                                    adapter.addTitle(title);
                                }
                            }
                        }
                );

        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            EditorActivity.class
                    );

            editorLauncher.launch(intent);

        });

    }

    }
