package com.example.okonomi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.okonomi.adapter.BinderAdapter;
import com.example.okonomi.BinderDataProto.Binder;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BinderAdapter adapter;
    private ActivityResultLauncher<Intent> editorLauncher;
    private BookshelfViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new BinderAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(BookshelfViewModel.class);
        viewModel.getBinders().observe(this, binders -> {
            if (binders != null) {
                adapter.setBinders(binders);
            }
        });

        editorLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String title = data.getStringExtra("binder_title");
                            Toast.makeText(this, "受信:" + title, Toast.LENGTH_SHORT).show();

                            viewModel.addBinder(title);
                        }
                    }
                }
        );

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            editorLauncher.launch(intent);
        });
    }
}