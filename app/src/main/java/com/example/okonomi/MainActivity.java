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
import java.util.UUID;
public class MainActivity extends AppCompatActivity {
    private BinderAdapter adapter;
    private ActivityResultLauncher<Intent> editorLauncher;
    private BinderRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = new BinderRepository(this);
        RecyclerView recyclerView =
                findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(
                new GridLayoutManager(this, 2)
        );


        adapter = new BinderAdapter();
        recyclerView.setAdapter(adapter);
        loadBinders();
        editorLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {

                            if(result.getResultCode() == RESULT_OK){

                                Intent data = result.getData();

                                if(data != null){

                                    String title =
                                            data.getStringExtra(
                                                    "binder_title");
                                    /*
                                    Toast.makeText(
                                            this,
                                            "受信:"+title,
                                            Toast.LENGTH_SHORT
                                    ).show();
                                    adapter.addTitle(title);

                                     */

                                    if (title != null && !title.trim().isEmpty()) {

                                        // バインダー固有のIDを作る
                                        String id = UUID.randomUUID().toString();

                                        // 作成日時
                                        long now = System.currentTimeMillis();

                                        // 保存するデータを作る
                                        BinderEntity binder = new BinderEntity(
                                                id,
                                                title,
                                                now,
                                                now
                                        );

                                        // Roomに保存
                                        repository.insertBinder(binder, () -> {

                                            // 保存完了後、一覧に追加
                                            runOnUiThread(() -> {
                                                adapter.addBinder(binder);
                                            });

                                        });
                                    }


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

    // Roomからバインダー一覧を読み込む
    private void loadBinders() {

        repository.getAllBinders(binders -> {

            runOnUiThread(() -> {

                adapter.clearBinders();

                for (BinderEntity binder : binders) {
                    adapter.addBinder(binder);
                }

            });

        });
    }
    }
