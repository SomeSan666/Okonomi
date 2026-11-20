
package com.example.okonomi.util;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class DeleteDialogHelper {

    public static void show(
            Context context,
            String title,
            String message,
            Runnable onDelete
    ) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton("キャンセル", null)
                .setPositiveButton("削除", (dialog, which) -> {
                    onDelete.run();
                })
                .show();
    }
}
