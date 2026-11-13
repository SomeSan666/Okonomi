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

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BinderActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> pageLauncher;
    // ページを複数保存しておくリスト
    private final ArrayList<PageData> pages = new ArrayList<>();
    // 現在表示しているページの番号
    private int currentPageIndex = 0;

    private TextView txtPageContent;

    private TextView txtPageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        Button btnAddPage =
                findViewById(R.id.btnAddPage);
        txtPageContent =
                findViewById(R.id.txtPageContent);

        txtPageNumber = findViewById(R.id.txtPageNumber);

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
                                    // 新しいページをリストに追加
                                    pages.add(new PageData(title, content));

                                    // 追加したページを表示する
                                    currentPageIndex = pages.size() - 1;
                                    showCurrentPage();

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

        // スワイプ操作を検知する
        GestureDetector gestureDetector =
                new GestureDetector(this,
                        new GestureDetector.SimpleOnGestureListener() {

                            @Override
                            public boolean onDown(MotionEvent e) {
                                return true;
                            }

                            @Override
                            public boolean onFling(
                                    MotionEvent e1,
                                    MotionEvent e2,
                                    float velocityX,
                                    float velocityY
                            ) {

                                float distanceX = e2.getX() - e1.getX();
                                float distanceY = e2.getY() - e1.getY();

                                // 横方向のスワイプだけを判定
                                if (Math.abs(distanceX) > Math.abs(distanceY)
                                        && Math.abs(distanceX) > 100
                                        && Math.abs(velocityX) > 100) {

                                    if (distanceX < 0) {
                                        // 左スワイプ：次のページ
                                        showNextPage();
                                    } else {
                                        // 右スワイプ：前のページ
                                        showPreviousPage();
                                    }

                                    return true;
                                }

                                return false;
                            }
                        });

        txtPageContent.setOnTouchListener((v, event) ->
                gestureDetector.onTouchEvent(event)
        );
    }

    // 現在のページを表示する
    private void showCurrentPage() {

        if (pages.isEmpty()) {
            txtPageContent.setText("ページがありません");
            return;
        }

        PageData page = pages.get(currentPageIndex);

        txtPageContent.setText(
                page.title + "\n\n" + page.content
        );
        // ページ番号を更新
        txtPageNumber.setText(
                "Page" + (currentPageIndex + 1)
        );
    }

    // 次のページへ
    private void showNextPage() {

        if (currentPageIndex < pages.size() - 1) {
            currentPageIndex++;
            showCurrentPage();
        }
    }

    // 前のページへ
    private void showPreviousPage() {

        if (currentPageIndex > 0) {
            currentPageIndex--;
            showCurrentPage();
        }
    }

    // ページのデータをまとめるクラス
    private static class PageData {

        String title;
        String content;

        PageData(String title, String content) {
            this.title = title;
            this.content = content;
        }

    }
}